package ezenweb.model.entity;

import ezenweb.model.dto.GalleryDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="gallery")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class GalleryEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gno;
    private String gname;
    @ManyToOne
    @JoinColumn(name = "bno")
    private BoardEntity boardEntity;

    public GalleryDto toDto(){
        return GalleryDto.builder()
                .gno(this.gno)
                .gname(this.gname)
                .build();
    }

}
