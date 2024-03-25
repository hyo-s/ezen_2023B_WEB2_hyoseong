package ezenweb.example;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Member {
    private int mno;
    private String id;
    private String name;
    @ToString.Exclude @Builder.Default
    private List<Board> boardList = new ArrayList<>();
    @ToString.Exclude @Builder.Default
    private List<Reply> replyList = new ArrayList<>();
}
