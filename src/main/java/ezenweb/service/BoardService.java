package ezenweb.service;

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

@Service
public class BoardService {

    @Autowired
    private BoardEntityRepository boardEntityRepository;

    @Autowired
    private MemberEntityRepository memberEntityRepository;

    @Autowired
    private ReplyEntityRepository replyEntityRepository;

    @Transactional
    // 1. Create
    public boolean postBoard(){

        // ============= 1. [테스트] 회원가입 ============= //
            // 엔티티 객체 생성
        MemberEntity memberEntity = MemberEntity.builder()
                .memail("qwe@qwe.qwe")
                .mpassword("1234")
                .mname("유재석")
                .build();
            // 엔티티를 DB에 저장
        MemberEntity saveMemberEntity =  memberEntityRepository.save(memberEntity);

        // ============= 2. [테스트] 글쓰기 ============= //
            // 엔티티 객체 생성
        BoardEntity boardEntity = BoardEntity.builder()
                .bcontent("게시물글입니다.")
                .build();
            // FK 대입
        boardEntity.setMemberEntity(saveMemberEntity);
            // 엔티티를 DB에 저장
        BoardEntity saveBoardEntity = boardEntityRepository.save(boardEntity);

        // ============= 3. [테스트] 댓글작성 ============= //
            // 엔티티 객체 생성
        ReplyEntity replyEntity = ReplyEntity.builder()
                .rcontent("댓글입니다.")
                .build();
            // FK 대입 [작성자]
        replyEntity.setMemberEntity(saveMemberEntity);
            // FK 대입 [게시물]
        replyEntity.setBoardEntity(saveBoardEntity);
            // 엔티티를 DB에 저장
        replyEntityRepository.save(replyEntity);

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
