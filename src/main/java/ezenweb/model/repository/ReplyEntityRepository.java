package ezenweb.model.repository;

import ezenweb.model.entity.MemberEntity;
import ezenweb.model.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReplyEntityRepository extends JpaRepository<ReplyEntity, Integer> {

}
/*
    리포지토리 만드는 방법
    1. @Repository
    2. extends JpaRepository<조작할엔티티, PK 필드타입>

    리포지토리 이용한 CRUD 메소드
    1. save : 해당 엔티티 객체를 테이블에 삽입

*/