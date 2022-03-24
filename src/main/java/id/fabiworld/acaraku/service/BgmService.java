package id.fabiworld.acaraku.service;

import id.fabiworld.acaraku.model.Bgm;
import id.fabiworld.acaraku.model.enumvalue.GeneralStatus;
import id.fabiworld.acaraku.repository.BgmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BgmService {
    private final BgmRepository bgmRepository;

    @Autowired
    public BgmService(BgmRepository bgmRepository) {
        this.bgmRepository = bgmRepository;
    }

    public List<Bgm> findAll(){
        return bgmRepository.findAll();
    }

    public List<Bgm> findAllActive(){
        return bgmRepository.findAll()
                .stream()
                .filter((bgm -> bgm.getStatus().equals(GeneralStatus.ACTIVE)))
                .collect(Collectors.toList());
    }

    public Bgm findById(Long id){
        return bgmRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Tidak Ditemukan"));
    }

    public Bgm create(Bgm bgm){
        bgm.setStatus(GeneralStatus.ACTIVE);
        bgm.setCreatedDate(LocalDateTime.now());
        return bgmRepository.save(bgm);
    }

    public Bgm update(Bgm bgm, Long id){
        var data = findById(id);
        bgm.setId(id);
        bgm.setModifiedDate(LocalDateTime.now());
        data = bgm;
        return bgmRepository.save(data);
    }

    public Bgm delete(Long id){
        var data = findById(id);
        bgmRepository.deleteById(id);
        return data;
    }
}
