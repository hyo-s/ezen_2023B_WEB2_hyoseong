package ezenweb.model.dto;

import ezenweb.example.Member;
import ezenweb.model.entity.BaseTime;
import ezenweb.model.entity.MemberEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
                // new BCryptPasswordEncoder().encode(암호화 할 데이터)
                .mpassword(new BCryptPasswordEncoder().encode(this.mpassword))
                .mname(this.mname)
                .build();
        // 암호화 : 정보를 이해할 수 없도록 --> 사람이 이해할 수 없도록 변경
            // 이해할 수 없도록 자기만의 방법으로 변경
            // 스프링 시큐리티 제외하는 방법 : Bcrypt 암호화 제공

        // this ?? : 해당 메소드를 호출하는 인스턴스
    }
}
