package id.fabiworld.acaraku.repository;

import id.fabiworld.acaraku.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.agenda.id = ?1")
    List<Comment> findByAgenda(Long idAgenda);

    @Query("SELECT c FROM Comment c WHERE c.agenda.id = ?1 AND c.status = 'ACTIVE'")
    List<Comment> findByAgendaActive(Long idAgenda);
}
