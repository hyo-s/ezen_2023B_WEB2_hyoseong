package ezenweb.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class GalleryDto {

    private int gno;
    private String gname;

    // 1. 출력용 게시물 이미지 필드
    private List<String> gnameList = new ArrayList<>();

    // 2. 등록용 게시물 이미지 필드
    private List<MultipartFile> multipartFileList = new ArrayList<>();

}
