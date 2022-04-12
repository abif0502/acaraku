package id.fabiworld.acaraku.repository;

import id.fabiworld.acaraku.model.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
    @Query("SELECT a FROM Agenda a WHERE a.title LIKE %?1% OR a.description LIKE %?1%")
    List<Agenda> search(String keyword);

    @Query("SELECT a FROM Agenda a WHERE a.subUrl = ?1")
    Optional<Agenda> checkSubUrl(String prefix);
}
