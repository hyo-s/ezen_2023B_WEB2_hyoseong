package ezenweb.model.dto;

import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class BoardDto extends BaseTimeDto {

    private int bno;
    private String bcontent;
    private int bview;
    private int mno_fk;
    private String memail;

    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .bcontent(this.bcontent)
                .build();
    }
}
