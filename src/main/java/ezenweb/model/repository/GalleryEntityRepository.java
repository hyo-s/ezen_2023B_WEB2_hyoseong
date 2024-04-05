package ezenweb.model.repository;

import ezenweb.model.entity.GalleryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GalleryEntityRepository extends JpaRepository<GalleryEntity, Integer> {

}
