package ezenweb.service;

import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MemberService {

    @Autowired
    MemberEntityRepository memberEntityRepository;

    public boolean doSignUp(MemberDto memberDto){

        // 1. Entity 이용한 레코드 저장방법
        // 엔티티를 만든다.
        // 리포지토리 통한 엔티티 저장
        memberEntityRepository.save(memberDto.toEntity());

        return false;
    }
}
