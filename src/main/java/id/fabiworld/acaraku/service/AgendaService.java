package id.fabiworld.acaraku.service;

import id.fabiworld.acaraku.dto.request.AgendaDTO;
import id.fabiworld.acaraku.model.Agenda;
import id.fabiworld.acaraku.model.enumvalue.AgendaStatus;
import id.fabiworld.acaraku.repository.AgendaRepository;
import id.fabiworld.acaraku.repository.BgmRepository;
import id.fabiworld.acaraku.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AgendaService {
    private final AgendaRepository agendaRepository;
    private final BgmRepository bgmRepository;
    private final TypeRepository typeRepository;

    @Autowired
    public AgendaService(AgendaRepository agendaRepository, BgmRepository bgmRepository, TypeRepository typeRepository) {
        this.agendaRepository = agendaRepository;
        this.bgmRepository = bgmRepository;
        this.typeRepository = typeRepository;
    }

    public List<Agenda> findAll(){
        return agendaRepository.findAll();
    }

    public List<Agenda> search(String keyword){
        return agendaRepository.search(keyword);
    }

    public Agenda findById(Long id){
        return agendaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Agenda Tidak Ditemukan"));
    }

    public Boolean checkSubUrl(String prefix){
        return agendaRepository.checkSubUrl(prefix).isPresent();
    }

    public Agenda update(Long id, AgendaDTO dto){
        var agenda = findById(id);
        if (checkSubUrl(dto.getSubUrl())) throw new ResponseStatusException(HttpStatus.CONFLICT, "Sub URL tidak tersedia");

        var type = typeRepository.findById(dto.getTypeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Tidak Ditemukan atau Tidak Aktif"));
        var bgm = bgmRepository.findById(dto.getBgmId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Tidak Ditemukan atau Tidak Aktif"));

        agenda.setTitle(dto.getTitle());
        agenda.setAddress(dto.getAddress());
        agenda.setCoordinate(dto.getCoordinate());
        agenda.setSubUrl(dto.getSubUrl());
        agenda.setStatus(dto.getStatus());
        agenda.setType(type);
        agenda.setBgm(bgm);

        return agendaRepository.save(agenda);
    }

    public Agenda cancelAgenda(Long id){
        var agenda = findById(id);
        agenda.setStatus(AgendaStatus.CANCELED);

        return agendaRepository.save(agenda);
    }
}
