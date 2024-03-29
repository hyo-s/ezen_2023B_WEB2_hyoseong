package ezenweb.model.dto;

import ezenweb.example.Member;
import ezenweb.model.entity.BaseTime;
import ezenweb.model.entity.MemberEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class MemberDto extends BaseTimeDto {
    private int mno;
    private String memail;
    private String mpassword;
    private String mname;
    private String mrol;

    // Dto -> Entity
    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .memail(this.memail)
                .mpassword(this.mpassword)
                .mname(this.mname)
                .build();
    }
}
