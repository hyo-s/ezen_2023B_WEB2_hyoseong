package ezenweb.service;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.entity.ReplyEntity;
import ezenweb.model.repository.BoardEntityRepository;
import ezenweb.model.repository.MemberEntityRepository;
import ezenweb.model.repository.ReplyEntityRepository;
import jakarta.persistence.Entity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardEntityRepository boardEntityRepository;

    @Autowired
    private MemberEntityRepository memberEntityRepository;

    @Autowired
    private ReplyEntityRepository replyEntityRepository;

    @Autowired
    private MemberService memberService;

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

        if(saveBoard.getBno()>=1){
            saveBoard.setMemberEntity(memberEntity);
            return true;
        }
        return false;
    }

    @Transactional
    // 2. Read
    public List<Object> getBoard(){
        // 1. 리포지토리를 이용한 모든 엔티티를 호출
        List<BoardEntity> list = boardEntityRepository.findAll();
        System.out.println("list = " + list);
        System.out.println("작성자 = " + list.get(0).getMemberEntity().getMemail());
        return null;
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
    public boolean deleteBoard(){
        boardEntityRepository.deleteById(1);
        return false;
    }

}
