package id.fabiworld.acaraku.service;

import id.fabiworld.acaraku.dto.request.TransactionDTO;
import id.fabiworld.acaraku.model.Agenda;
import id.fabiworld.acaraku.model.Client;
import id.fabiworld.acaraku.model.Picture;
import id.fabiworld.acaraku.model.Transaction;
import id.fabiworld.acaraku.model.enumvalue.AgendaStatus;
import id.fabiworld.acaraku.model.enumvalue.GeneralStatus;
import id.fabiworld.acaraku.model.enumvalue.TransactionStatus;
import id.fabiworld.acaraku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final ClientRepository clientRepository;
    private final StyleRepository styleRepository;
    private final BgmRepository bgmRepository;
    private final TypeRepository typeRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, ClientRepository clientRepository, StyleRepository styleRepository, BgmRepository bgmRepository, TypeRepository typeRepository) {
        this.transactionRepository = transactionRepository;
        this.clientRepository = clientRepository;
        this.styleRepository = styleRepository;
        this.bgmRepository = bgmRepository;
        this.typeRepository = typeRepository;
    }

    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }

    public List<Transaction> findAllActive(){
        return transactionRepository.findAllActive();
    }

    public List<Transaction> findByClient(Client client){
        return transactionRepository.findByClient(client);
    }

    public Transaction create(TransactionDTO dto){
        var client = clientRepository.findById(dto.getClientId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User Tidak Terdaftar"));
        var style = styleRepository.findById(dto.getStyleId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Tidak Ditemukan atau Tidak Aktif"));
        var type = typeRepository.findById(dto.getTypeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Tidak Ditemukan atau Tidak Aktif"));
        var bgm = bgmRepository.findById(dto.getBgmId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Tidak Ditemukan atau Tidak Aktif"));

        var transaction = Transaction.builder()
                .client(client)
                .style(style)
                .createdDate(LocalDateTime.now())
                .status(TransactionStatus.ORDERED)
                .totalPrice(style.getPrice() - (style.getPrice() * style.getDiscount()))
                .build();

        Set<Picture> pictures = new HashSet<>();
        dto.getPictures().forEach((p) -> {
            pictures.add(Picture.builder()
                            .file(p.getFile())
                            .status(GeneralStatus.ACTIVE)
                    .build());
        });

        var agenda = Agenda.builder()
                .title(dto.getTitle())
                .address(dto.getAddress())
                .coordinate(dto.getCoordinate())
                .type(type)
                .bgm(bgm)
                .pictures(pictures)
                .description(dto.getDescription())
                .status(AgendaStatus.ON_SCHEDULE)
                .transaction(transaction)
                .build();
        transaction.setAgenda(agenda);
        return transactionRepository.save(transaction);
    }

    public Transaction uploadReceipt(byte[] file, Long idTransaction){
        var transaction = transactionRepository.findById(idTransaction)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Tidak Ditemukan atau Tidak Aktif"));
        transaction.setId(idTransaction);
        transaction.setReceipt(file);
        transaction.setStatus(TransactionStatus.PAID);
        transaction.setModifiedDate(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

}
