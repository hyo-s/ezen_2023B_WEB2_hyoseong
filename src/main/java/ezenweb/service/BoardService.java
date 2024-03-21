package ezenweb.service;

import ezenweb.model.entity.BoardEntity;
import ezenweb.model.repository.BoardEntityRepository;
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

    @Transactional
    // 1. Create
    public boolean postBoard(){

        // 1. 엔티티 객체 생성
        BoardEntity boardEntity = BoardEntity.builder()
                .bno(1)
                .btitle("JPA테스트중")
                .build();

        // 2. 리포지토리를 이용한 엔티티를 테이블에 대입
        boardEntityRepository.save(boardEntity);

        return false;
    }

    @Transactional
    // 2. Read
    public List<Object> getBoard(){
        // 1. 리포지토리를 이용한 모든 엔티티를 호출
        List<BoardEntity> list = boardEntityRepository.findAll();
        System.out.println("list = " + list);
        return null;
    }

    @Transactional
    // 3. Update
    public boolean putBoard(){
        BoardEntity boardEntity = boardEntityRepository.findById(1).get();
        boardEntity.setBtitle("JPA 수정테스트중");
        return false;
    }

    @Transactional
    // 4. Delete
    public boolean deleteBoard(){
        boardEntityRepository.deleteById(1);
        return false;
    }

}
