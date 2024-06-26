package ezenweb.model.entity;

import ezenweb.model.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

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
@DynamicInsert // Default (@ColumnDefault) 값을 적용할 때 사용
public class MemberEntity extends BaseTime {
    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int mno;

    @Column(length = 50, unique = true) // varchar(50) unique
    private String memail;

    @Column(length = 255) // varchar(30)
    private String mpassword;

    @Column(length = 20, nullable = false) // varchar(20)
    private String mname;

    @Column(name="mrol") // varchar(255), not null
    @ColumnDefault("'USER'") // 문자"''", 숫자"" // default 'USER'
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

    // Entity -> Dto
    public MemberDto toDto(){
        return MemberDto.builder()
                .mno(this.mno)
                .memail(this.memail)
                .mname(this.mname)
                .mrol(this.mrol)
                .build();
    }
}
