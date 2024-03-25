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
public class Board {
    private int bno;
    private String title;
    private String content;
    private Member writer;
    @ToString.Exclude @Builder.Default
    private List<Reply> replyList = new ArrayList<>();
}
