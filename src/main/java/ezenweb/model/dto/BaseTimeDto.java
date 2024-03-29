package ezenweb.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class BaseTimeDto {
    private LocalDateTime cdate;
    private LocalDateTime udate;
}
