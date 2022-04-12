package id.fabiworld.acaraku.repository;

import id.fabiworld.acaraku.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Long> {
    @Query("SELECT p FROM Picture p WHERE p.agenda.id = ?1")
    List<Picture> findByAgenda(Long idAgenda);

    @Query("SELECT p FROM Picture p WHERE p.agenda.id = ?1 AND p.status = 'ACTICVE'")
    List<Picture> findByAgendaActive(Long idAgenda);
}
