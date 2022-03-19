package id.fabiworld.pestaku.repository;

import id.fabiworld.pestaku.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
