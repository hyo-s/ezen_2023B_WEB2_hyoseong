package ezenweb.service;

import ezenweb.example.Board;
import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.dto.PageDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.GalleryEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.entity.ReplyEntity;
import ezenweb.model.repository.BoardEntityRepository;
import ezenweb.model.repository.GalleryEntityRepository;
import ezenweb.model.repository.MemberEntityRepository;
import ezenweb.model.repository.ReplyEntityRepository;
import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {

    @Autowired
    private BoardEntityRepository boardEntityRepository;

    @Autowired
    private GalleryEntityRepository galleryEntityRepository;

    @Autowired
    private MemberEntityRepository memberEntityRepository;

    @Autowired
    private ReplyEntityRepository replyEntityRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private FileService fileService;

    @Transactional
    // 1. Create
    public boolean postBoard(BoardDto boardDto){

        // 0. 로그인 여부 확인
        MemberDto loginDto = memberService.doLoginInfo();
        if(loginDto==null) return false;

        // 1. 로그인된 회원 엔티티 찾기
        Optional<MemberEntity> optionalMemberEntity = memberEntityRepository.findById(loginDto.getMno());

        // 2. 찾은 엔티티가 존재하지 않으면
        if(!optionalMemberEntity.isPresent()) return false;

        // 3. 엔티티 꺼내기
        MemberEntity memberEntity = optionalMemberEntity.get();

        // 4. 글 쓰기
        BoardEntity saveBoard = boardEntityRepository.save(boardDto.toEntity());

        // 5. 사진 저장하기
        List<GalleryEntity> galleryEntityList = boardDto.getMultipartFileList().stream().map((img)->{
            return GalleryEntity.builder()
                    .gname(fileService.fileUpload(img))
                    .boardEntity(saveBoard).build();}).collect(Collectors.toList());

        for (int i=0; i<galleryEntityList.size(); i++){
            GalleryEntity galleryEntity = galleryEntityRepository.save(galleryEntityList.get(i));
            if (galleryEntity.getGno()<0){
                return false;
            }
        }

        if(saveBoard.getBno()>=1){
            saveBoard.setMemberEntity(memberEntity);
            return true;
        }
        return false;
    }

    @Transactional
    // 2. Read
    public PageDto getBoard(int page, int view){
    // ===================== FOR ===================== //
//        // 1. 리포지토리를 이용한 모든 엔티티를 호출
//        List<BoardEntity> boardEntityList = boardEntityRepository.findAll();
//        // 2. Entity --> Dto 변환
//        List<BoardDto> boardDtoList = new ArrayList<>();
//            // 꺼내온 엔티티 리스트를 순회한다
//        for (int i=0; i<boardEntityList.size(); i++){
//            // 하나씩 엔티티를 꺼낸다
//            BoardEntity boardEntity = boardEntityList.get(i);
//            // 해당 엔티티를 Dto로 변환한다.
//            BoardDto boardDto = boardEntity.toDto();
//            // 변환된 Dto를 리스트에 담는다.
//            boardDtoList.add(boardDto);
//        }

    // ===================== MAP ===================== //
        // 1. Pageable 인터페이스 이용한 페이징 처리
            // pageNumber : 0 부터 시작하기 때문에 1페이지 일때 0 페이지 반환
        Pageable pageable = PageRequest.of(page-1,view);
            // 1. 페이징 처리된 엔티티 호출
        Page<BoardEntity> boardEntityPage = boardEntityRepository.findAll(pageable);
            // 1. 전체 페이지 수
        System.out.println("boardEntityPage = " + boardEntityPage.getTotalPages());
            // 2. 전체 게시물 수
        System.out.println("boardEntityPage = " + boardEntityPage.getTotalElements());

        List<Object> boardDtoList = boardEntityPage.stream().map((entity)->{return entity.toDto();}).collect(Collectors.toList());
            // PageDto 구성
        PageDto pageDto = PageDto.builder().data(boardDtoList).page(page).count(boardEntityPage.getTotalPages()).build();
        return pageDto;
    }

    @Transactional
    // 3. Update
    public boolean putBoard(){
        BoardEntity boardEntity = boardEntityRepository.findById(1).get();
        boardEntity.setBcontent("JPA 수정테스트중");
        return false;
    }

    @Transactional
    // 4. Delete
    public boolean deleteBoard(int bno){

        MemberDto loginDto = memberService.doLoginInfo();
        if(loginDto==null) return false;
        Optional<MemberEntity> optionalMemberEntity = memberEntityRepository.findById(loginDto.getMno());
        if(!optionalMemberEntity.isPresent()) return false;
        MemberEntity memberEntity = optionalMemberEntity.get();
        BoardEntity boardEntity = boardEntityRepository.findById(bno).get();
        if(boardEntity.getMemberEntity().getMno()==memberEntity.getMno()){
            boardEntityRepository.delete(boardEntity);
        }else {
            return false;
        }
        return true;
    }
}
