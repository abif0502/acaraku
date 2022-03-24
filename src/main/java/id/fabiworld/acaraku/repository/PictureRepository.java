package id.fabiworld.acaraku.repository;

import id.fabiworld.acaraku.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
