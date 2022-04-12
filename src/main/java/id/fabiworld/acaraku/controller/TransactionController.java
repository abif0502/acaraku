package id.fabiworld.acaraku.controller;

import id.fabiworld.acaraku.dto.request.TransactionDTO;
import id.fabiworld.acaraku.dto.response.ResponseData;
import id.fabiworld.acaraku.dto.response.ResponseListData;
import id.fabiworld.acaraku.dto.response.ResponseMessage;
import id.fabiworld.acaraku.model.Transaction;
import id.fabiworld.acaraku.model.enumvalue.TransactionStatus;
import id.fabiworld.acaraku.service.TransactionService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseListData<Transaction> findAll(){
        return new ResponseListData<>(transactionService.findAll());
    }

    @GetMapping("/active")
    public ResponseListData<Transaction> findAllActive(){
        return new ResponseListData<>(transactionService.findAllActive());
    }

    @GetMapping("/all/{client}")
    public ResponseListData<Transaction> findByClient(@PathVariable("client") Long id){
        return new ResponseListData<>(transactionService.findByClient(id));
    }

    @GetMapping("/all/{client}/active")
    public ResponseListData<Transaction> findByClientActive(@PathVariable("client") Long id){
        return new ResponseListData<>(transactionService.findByClientActive(id));
    }

    @GetMapping("/{id}")
    public ResponseData<Transaction> findById(@PathVariable("id") Long id){
        return new ResponseData<>(transactionService.findById(id));
    }

    @PostMapping
    public ResponseMessage<Transaction> create(@RequestBody TransactionDTO dto){
        return new ResponseMessage<>(transactionService.create(dto), "Berhasil Membuat Acara, silahkan lakukan pembayaran untuk mengkonfirmasi");
    }

    @PostMapping("/paid/{id}")
    public ResponseMessage<Transaction> uploadReceipt(@PathVariable("id") Long id, @ModelAttribute("file") MultipartFile file){
        try {
            byte[] image = Base64.encodeBase64(file.getBytes());
            return new ResponseMessage<>(transactionService.uploadReceipt(image, id), "Berhasil Mengunggah Bukti Transaksi");
        } catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gagal Mengunggah File");
        }
    }

    @PostMapping("/change-agenda-status")
    public ResponseMessage<Transaction> changeStatus(@RequestBody Map<String, Object> data){
        return new ResponseMessage<>(transactionService.changeStatus((Long)data.get("idTransaction"),
                TransactionStatus.parse((String)data.get("status"))),
                "Berhasil Memperbarui Status");
    }
}
