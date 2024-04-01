package ezenweb.service;

import ezenweb.example.Board;
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

import java.util.ArrayList;
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
    public List<BoardDto> getBoard(){
        // 1. 리포지토리를 이용한 모든 엔티티를 호출
        List<BoardEntity> boardEntityList = boardEntityRepository.findAll();
        // 2. Entity --> Dto 변환
        List<BoardDto> boardDtoList = new ArrayList<>();
            // 꺼내온 엔티티 리스트를 순회한다
        for (int i=0; i<boardEntityList.size(); i++){
            // 하나씩 엔티티를 꺼낸다
            BoardEntity boardEntity = boardEntityList.get(i);
            // 해당 엔티티를 Dto로 변환한다.
            BoardDto boardDto = boardEntity.toDto();
            // 변환된 Dto를 리스트에 담는다.
            boardDtoList.add(boardDto);

        }

        return boardDtoList;
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
