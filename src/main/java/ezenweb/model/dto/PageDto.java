package ezenweb.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class PageDto {
    private int page;
    private int count;
    private List<Object> data;
}
