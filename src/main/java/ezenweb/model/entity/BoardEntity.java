package ezenweb.model.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="board")
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity {  // 테이블

    @Id // PK ( Not Null + Unique )
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int bno;        // 게시물번호
    @Column(name="필드명", length=10, nullable=false, unique=true)
    private String btitle;  // 게시물제목
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
}
