package ezenweb.service;

import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.MemberEntityRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberEntityRepository memberEntityRepository;

// ======================== 회원가입 ======================== //
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
        // 1. 리포지토리를 통해 모든 회원 엔티티 호출
        List<MemberEntity> memberEntityList = memberEntityRepository.findAll();
        // 2. Dto와 동일한 아이디 / 패스워드 찾는다.
        for(int i=0; i<memberEntityList.size();i++){
            MemberEntity m = memberEntityList.get(i);
            // 3. 만약에 아이디가 동일하면 (엔티티와 DTO)
            if(m.getMemail().equals(memberDto.getMemail())){
                // 4. 만약에 비밀번호가 동일하면 (엔티티와 DTO)
                if(m.getMpassword().equals(memberDto.getMpassword())){
                    // 5. 세션저장
                    request.getSession().setAttribute("loginInfo", memberDto);
                    return true;
                }   // IF 2 end
            }    // IF 1 end
        }   // FOR end
        return false;
    }   // FUNCTION END
// ======================== 로그아웃 (세션삭제) ======================== //
    public boolean doLogOutGet(){
        request.getSession().setAttribute("loginInfo",null);
        return true;
    }
// ======================== 현재 로그인 회원정보 호출 (세션호출) ======================== //
    public MemberDto doLoginInfo(){
        Object object = request.getSession().getAttribute("loginInfo");
        if (object != null){
            return (MemberDto)object;
        }
        return null;
    }
}
