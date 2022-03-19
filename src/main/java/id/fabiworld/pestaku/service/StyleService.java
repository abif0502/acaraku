package id.fabiworld.pestaku.service;

import id.fabiworld.pestaku.dto.request.StyleDTO;
import id.fabiworld.pestaku.model.Style;
import id.fabiworld.pestaku.model.enumvalue.GeneralStatus;
import id.fabiworld.pestaku.repository.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StyleService {

    private final StyleRepository styleRepository;

    @Autowired
    public StyleService(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    public List<Style> findAll(){
        return styleRepository.findAll();
    }

    public Style findById(Long id){
        return styleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Tidak Ditemukan"));
    }

    public List<Style> findAllActive(){
        return styleRepository.findAll().stream().filter((s) -> s.getStatus().equals(GeneralStatus.ACTIVE))
                .collect(Collectors.toList());
    }

    public Style findByIdActive(Long id){
        var data = styleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Tidak Ditemukan"));
        if(data.getStatus().equals(GeneralStatus.ACTIVE)){
            return data;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data Sudah Tidak Aktif");
    }

    public Style create(StyleDTO styleDto){
        var data = Style.builder()
                .name(styleDto.getName())
                .price(styleDto.getPrice())
                .discount(styleDto.getDiscount())
                .createdDate(LocalDateTime.now())
                .status(GeneralStatus.ACTIVE)
                .build();
        return styleRepository.save(data);
    }

    public Style update(Long id, StyleDTO styleDTO){
        var data = findById(id);
        data.setId(id);
        data.setName(styleDTO.getName());
        data.setPrice(styleDTO.getPrice());
        data.setDiscount(styleDTO.getDiscount());
        data.setModifiedDate(LocalDateTime.now());
        data.setStatus(styleDTO.getStatus());
        return styleRepository.save(data);
    }

    public Style delete(Long id){
        var data = findById(id);
        styleRepository.deleteById(id);
        return data;
    }
}
