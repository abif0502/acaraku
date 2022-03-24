package id.fabiworld.acaraku.repository;

import id.fabiworld.acaraku.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
