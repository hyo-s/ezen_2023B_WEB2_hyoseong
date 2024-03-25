package ezenweb.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="board")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BoardEntity {  // 테이블
    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int bno;

    @Column(columnDefinition = "longtext") // longtext
    private String bcontent;

    @Column
    @ColumnDefault("0") // int, default 0
    private int bview;

    // 단방향 : FK 필드
    @JoinColumn(name="mno_fk") // FK 필드명
    @ManyToOne // 해당 필드 참조
    private MemberEntity memberEntity;

    // 양방향 : 댓글 FK
    @OneToMany(mappedBy = "boardEntity")
    @ToString.Exclude
    @Builder.Default
    private List<ReplyEntity> replyEntityList = new ArrayList<>();

}

/*
    @Column(columnDefinition="longtext")
    private String bcontent;
    private byte byteField;
    private short shortField;
    private long longField;
    private char charField;
    private float floatField;
    private double doubleField;
    private boolean booleanField;
    private Date dateField;
    @Column(columnDefinition="date")
    private String dateField2;
*/