package ezenweb.model.repository;

import ezenweb.model.entity.GalleryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GalleryEntityRepository extends JpaRepository<GalleryEntity, Integer> {
}
