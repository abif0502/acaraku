package id.fabiworld.acaraku.service;

import id.fabiworld.acaraku.model.Type;
import id.fabiworld.acaraku.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TypeService {
    private final TypeRepository typeRepository;

    @Autowired
    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<Type> findAll(){
        return typeRepository.findAll();
    }

    public Type findById(Long id){
        return typeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Tidak Ditemukan"));
    }

    public Type create(Type type){
        type.setCreatedDate(LocalDateTime.now());
        return typeRepository.save(type);
    }

    public Type update(Type type, Long id){
        var data = findById(id);
        type.setId(id);
        type.setModifiedDate(LocalDateTime.now());
        data = type;
        return typeRepository.save(data);
    }

    public Type delete(Long id){
        var data = findById(id);
        typeRepository.deleteById(id);
        return data;
    }


}
