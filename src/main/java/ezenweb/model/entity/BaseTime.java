package ezenweb.model.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass //
@EntityListeners(AuditingEntityListener.class)
public class BaseTime {

    // 등록날짜 (레코드/엔티티)
    @CreatedDate // default now()
    private LocalDateTime cdate;

    // 수정날짜 (레코드/엔티티)
    @LastModifiedDate // 마지막 수정날짜
    private LocalDateTime udate;
}
/*
    상속 : 여러 곳에서 공통적인 멤버들
    Auditing : 감시하는 역할
*/