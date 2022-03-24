package id.fabiworld.acaraku.service;

import id.fabiworld.acaraku.model.Client;
import id.fabiworld.acaraku.model.enumvalue.GeneralStatus;
import id.fabiworld.acaraku.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public List<Client> findAllActive(){
        return clientRepository.findAll()
                .stream()
                .filter(client -> client.getStatus().equals(GeneralStatus.ACTIVE))
                .collect(Collectors.toList());
    }

    public Client findById(Long id){
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Tidak Ditemukan"));
    }
}
