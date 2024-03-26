package ezenweb.model.dto;

import ezenweb.example.Member;
import ezenweb.model.entity.MemberEntity;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class MemberDto {
    private int mno;
    private String memail;
    private String mpassword;
    private String mname;
    private String mrol;

    // Dto -> Entity
    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .mno(this.mno)
                .memail(this.memail)
                .mpassword(this.mpassword)
                .mname(this.mname)
                .mrol(this.mrol)
                .build();
    }
}
