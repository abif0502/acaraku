package id.fabiworld.acaraku.service;

import id.fabiworld.acaraku.dto.request.CommentDTO;
import id.fabiworld.acaraku.model.Comment;
import id.fabiworld.acaraku.model.enumvalue.GeneralStatus;
import id.fabiworld.acaraku.repository.AgendaRepository;
import id.fabiworld.acaraku.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final AgendaRepository agendaRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, AgendaRepository agendaRepository) {
        this.commentRepository = commentRepository;
        this.agendaRepository = agendaRepository;
    }

    public List<Comment> findByAgenda(Long idAgenda){
        return commentRepository.findByAgenda(idAgenda);
    }

    public List<Comment> findByAgendaActive(Long idAgenda){
        return commentRepository.findByAgendaActive(idAgenda);
    }

    public Comment findById(Long id){
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Tidak Ditemukan"));
    }

    public Comment post(Long idAgenda, CommentDTO dto){
        LocalDateTime dateNow = LocalDateTime.now();
        var agenda = agendaRepository.findById(idAgenda)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Agenda Tidak Ditemukan"));

        Comment comment = Comment.builder()
                .name(dto.getName())
                .phoneNumber(dto.getPhoneNumber())
                .description(dto.getDescription())
                .attend(dto.getAttend())
                .status(GeneralStatus.ACTIVE)
                .createdDate(dateNow)
                .modifiedDate(dateNow)
                .agenda(agenda)
                .build();
        return commentRepository.save(comment);
    }

    public Comment deactivate(Long id){
        var comment = findById(id);
        comment.setStatus(GeneralStatus.NONACTIVE);
        comment.setModifiedDate(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    public Comment delete(Long id){
        var comment = findById(id);
        commentRepository.deleteById(id);
        return comment;
    }
}
