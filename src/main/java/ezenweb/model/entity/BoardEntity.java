package ezenweb.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity {  // 테이블

    @Id
    private int bno;        // 게시물번호
    private String btitle;  // 게시물제목
}
