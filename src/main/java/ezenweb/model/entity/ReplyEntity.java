package ezenweb.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="reply")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ReplyEntity {  // 테이블
    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int rno;
    private String rcontent; // varchar(255)

    // FK 필드
    @JoinColumn(name="bno")
    @ManyToOne
    private BoardEntity boardEntity;

    // 단방향 : FK 필드
    @JoinColumn(name="mno")
    @ManyToOne
    private MemberEntity memberEntity;
}
