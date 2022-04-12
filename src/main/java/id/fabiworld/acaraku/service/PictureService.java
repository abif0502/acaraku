package id.fabiworld.acaraku.service;

import id.fabiworld.acaraku.dto.request.PictureDTO;
import id.fabiworld.acaraku.model.Picture;
import id.fabiworld.acaraku.model.enumvalue.GeneralStatus;
import id.fabiworld.acaraku.repository.AgendaRepository;
import id.fabiworld.acaraku.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PictureService {
    private final PictureRepository pictureRepository;
    private final AgendaRepository agendaRepository;

    @Autowired
    public PictureService(PictureRepository pictureRepository, AgendaRepository agendaRepository) {
        this.pictureRepository = pictureRepository;
        this.agendaRepository = agendaRepository;
    }

    public List<Picture> findByAgenda(Long idAgenda){
        return pictureRepository.findByAgenda(idAgenda);
    }

    public List<Picture> findByAgendaActive(Long idAgenda){
        return pictureRepository.findByAgendaActive(idAgenda);
    }

    public Picture findById(Long id){
        return pictureRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Gambar Tidak Ditemukan"));
    }

    public Picture add(Long idAgenda, PictureDTO dto){
        var agenda = agendaRepository.findById(idAgenda)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Agenda Tidak Ditemukan"));
        Picture picture = Picture.builder()
                .file(dto.getFile())
                .status(GeneralStatus.ACTIVE)
                .agenda(agenda)
                .build();
        return pictureRepository.save(picture);
    }

    public Picture change(Long id, PictureDTO dto){
        var picture = findById(id);
        picture.setFile(dto.getFile());
        return pictureRepository.save(picture);
    }

    public String deactivate(Long id){
        var picture = findById(id);
        picture.setStatus(GeneralStatus.NONACTIVE);
        pictureRepository.save(picture);
        return "Berhasil Menghapus Gambar";
    }

    public String delete(Long id){
        findById(id);
        pictureRepository.deleteById(id);
        return "Berhasil Menghapus Gambar";
    }
}
