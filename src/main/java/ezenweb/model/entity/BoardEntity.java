package ezenweb.model.entity;

import ezenweb.model.dto.BoardDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="board")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class BoardEntity extends BaseTime {  // 테이블
    @Id // PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int bno;

    @Column(columnDefinition = "longtext") // longtext
    private String bcontent;

    @Column
    @ColumnDefault("0") // int, default 0
    private int bview;

    // 단방향 : FK 필드
    @JoinColumn(name="mno") // FK 필드명
    @ManyToOne // 해당 필드 참조
    private MemberEntity memberEntity;

    // 양방향 : 댓글 FK
    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<ReplyEntity> replyEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<GalleryEntity> galleryEntityList = new ArrayList<>();

    public BoardDto toDto(){
        return BoardDto.builder()
                .bno(this.bno)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .mno_fk(this.memberEntity.getMno())
                .memail(this.memberEntity.getMemail())
                .cdate(this.getCdate())
                .udate(this.getUdate())
                .gnameList(this.galleryEntityList.stream().map((img)->{return img.getGname();}).collect(Collectors.toList()))
                .build();
    }

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