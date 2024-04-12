package ezenweb.service;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.MemberEntityRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MemberService implements UserDetailsService, OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    // (Social) 소셜 회원 로그인 커스텀
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest = " + userRequest);
        return null;
    }

    // (Security) 일반 회원 로그인 커스텀
    @Override
    public UserDetails loadUserByUsername(String memail) throws UsernameNotFoundException {

            // 로그인 창에서 입력받은 아이디
        System.out.println("username = " + memail);
            // 입력받은 아이디로 실제 아이디와 (암호화된) 패스워드 (meamil 이용한 회원 엔티티 찾기)
        MemberEntity memberEntity = memberEntityRepository.findByMemail(memail);
        // 만약에 입력한 이메일의 엔티티가 없으면
        if( memberEntity == null){
            // 강제 예외 발생
            throw new UsernameNotFoundException("없는 아이디");
        }
            // ROLE
            // GrantedAuthority : 권한의 의미를 저장하는 클래스
            // SimpleGrantedAuthority("ROLE_권한") : 문자형식의 권한 저장
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("ROLE_"+memberEntity.getMrol())); // ROLE_등급명

            // UserDetails 반환 [ 실제 아이디와 실제 패스워드 반환 ] Token에 입력받은 아이디/패스워드 검증하기 위한 실제 정보 반환
        UserDetails userDetails = User.builder()
                .username(memberEntity.getMemail()) // 실제 아이디
                .password(memberEntity.getMpassword()) // 실제 비밀번호(암호화)
                .authorities(authorityList) // ROLE 등급
                .build();
        return userDetails;
    }

    @Autowired
    MemberEntityRepository memberEntityRepository;


// ======================== 회원가입 ======================== //
// (Security) 패스워드 암호화
    public boolean doSignUpPost(MemberDto memberDto){
        // 1. Entity 이용한 레코드 저장방법
        // 엔티티를 만든다.
        // 리포지토리 통한 엔티티 저장 (엔티티 저장 성공 시 성공한 엔티티 반환)
        MemberEntity savedEntity = memberEntityRepository.save(memberDto.toEntity());
        // 엔티티가 생성 되었는지 아닌지 확인 ( PK )
        if(savedEntity.getMno() > 0) return true;
        return false;
    }
// ======================== 로그인 (세션저장) ======================== //
    // * 로그인 했다는 증거 / 기록
    @Autowired
    private HttpServletRequest request;

    public boolean doLoginPost(MemberDto memberDto){
        // 1.
//        MemberEntity result1 = memberEntityRepository.findByMemailAndMpassword(memberDto.getMemail(), memberDto.getMpassword());
//        System.out.println("result1 = " + result1);
        // 2.
//        boolean result2 = memberEntityRepository.existsByMemailAndMpassword(memberDto.getMemail(), memberDto.getMpassword());
//        System.out.println("result2 = " + result2);
        // 3.
        MemberEntity result3 = memberEntityRepository.findByLoginSQL(memberDto.getMemail(), memberDto.getMpassword());

        if(result3 == null)return false;

        // 세션부여
        request.getSession().setAttribute("loginInfo", result3.toDto());

        return true;
    }   // FUNCTION END
// ======================== 로그아웃 (세션삭제) ======================== //
    public boolean doLogOutGet(){
        request.getSession().setAttribute("loginInfo",null);
        return true;
    }
// ======================== 현재 로그인 회원정보 호출 (세션호출) ======================== //
    public MemberDto doLoginInfo(){
        // (Security) 사용 전
//        Object object = request.getSession().getAttribute("loginInfo");
//        if (object != null){
//            return (MemberDto)object;
//        }
//        return null;
        // (Security) 사용 후 Principal : 본인/주역/주체자 : 브라우저마다 1개
        // 1.
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("object = " + object);
        // 2. 만약에 로그인 상태가 아니면
        if(object.equals("anonymousUser")){
            // anonymous : 익명 <--> 비로그인
            return null;
        }
        // 3. 로그인 상태이면 UserDetails 타입 변환
        UserDetails userDetails = (UserDetails)object;
        // 4. 로그인 성공한 엔티티 찾기
        MemberEntity memberEntity = memberEntityRepository.findByMemail(userDetails.getUsername());
        return MemberDto.builder().mno(memberEntity.getMno()).memail(memberEntity.getMemail()).mname(memberEntity.getMname()).build();
    }
// ======================== 아이디 중복검사 ======================== //
    public boolean doFindIdGet(String memail){
        List<MemberEntity> memberEntityList = memberEntityRepository.findAll();
        for(int i=0; i<memberEntityList.size();i++){
            MemberEntity m = memberEntityList.get(i);
            if(m.getMemail().equals(memail)){
                return true;
            }
        }
        return false;
    }

    public boolean getFindMemail(String memail){
        // 1. 모든 엔티티에서 해당 필드의 값을 찾는다.
//        List<MemberEntity> memberEntityList = memberEntityRepository.findAll();
//            for(int i=0; i<memberEntityList.size(); i++){
//                MemberEntity memberEntity = memberEntityList.get(i);
//                if(memberEntity.getMemail().equals(memail)) {
//                    System.out.println("memberEntity = " + memberEntity);
//                }
//            }

        // 2. 리포지토리 추상메소드 이용하는 방법
//        MemberEntity result1 = memberEntityRepository.findByMemail(memail);
//        System.out.println("result1 = " + result1);
        // 3. 특정 필드의 조건으로 존재여부 검색
        boolean result2 = memberEntityRepository.existsByMemail(memail);
        // 4. Native SQL
//        MemberEntity result3 = memberEntityRepository.findByMemailSQL(memail);
//        System.out.println("result3 = " + result3);
        return result2;
    }
// ======================== 내가 쓴글 ======================== //
    public List<Map<Object,Object>> findByMyBoardList(){

        // 1. 세션에서 로그인된 회원번호 찾는다
        MemberDto loginDto = doLoginInfo();
        if(loginDto == null)return null;

//        // ============ 양방향 ============ //
//            // 1. 로그인된 회원번호를 이용한 엔티티 찾기
//        Optional<MemberEntity> optionalMemberEntity = memberEntityRepository.findById(loginDto.getMno());
//            // 2. 만약에 엔티티가 존재하면
//        if(optionalMemberEntity.isPresent()){ // .isPresent() 검색 결과가 존재하면
//            // 3. Optional에서 엔티티 꺼내기
//            MemberEntity memberEntity = optionalMemberEntity.get();
//            // 4. 내가 쓴 글
//            List<BoardEntity> result1 = memberEntity.getBoardEntityList();
//            System.out.println("result1 = " + result1);
//            // 5. 내가 쓴글 리스트 엔티티 리스트를 -> DTO 리스트로 변환
//            List<BoardDto> boardDtoList = new ArrayList<>();
//            result1.forEach((entity)->{
//                boardDtoList.add(entity.toDto());
//            });
//            return boardDtoList;
//        }else {
//            return null;
//        }
        // ============ 단방향 ============ //
        List<Map<Object, Object>> result2 = memberEntityRepository.findByMyBoard(loginDto.getMno());
        return result2;
    }

}
/*
    Optional 클래스
        해당 객체가 null 일수도 있고 아닐 수 있다.
        검색 결과가 없을경우 null 반환될때 패키징
        메소드

*/
