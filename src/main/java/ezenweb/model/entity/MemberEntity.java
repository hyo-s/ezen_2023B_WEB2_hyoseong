package ezenweb.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="member")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MemberEntity {
    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int mno;

    @Column(length = 50, unique = true) // varchar(50) unique
    private String memail;

    @Column(length = 30) // varchar(30)
    private String mpassword;

    @Column(length = 20, nullable = false) // varchar(20)
    private String mname;

    @Column(name="mrol") // varchar(255), not null
    @ColumnDefault("'user'") // 문자"''", 숫자"" // default 'user'
    private String mrol;


    // 양방향 : 게시물 FK @OneToMany(mappedBy = "FK 자바 필드명")
    @OneToMany(mappedBy = "memberEntity")
    @ToString.Exclude // 해당 객체 호출 시 해당 필드는 호출하지 않는다.
    @Builder.Default // Builder 사용 시 해당 필드의 초기값을 Builder 초기값으로 사용
    private List<BoardEntity> boardEntityList = new ArrayList<>();

    // 양방향 : 댓글 FK
    @OneToMany(mappedBy = "memberEntity")
    @ToString.Exclude
    @Builder.Default
    private List<ReplyEntity> replyEntityList = new ArrayList<>();
}
