package id.fabiworld.acaraku.controller;

import id.fabiworld.acaraku.dto.request.AgendaDTO;
import id.fabiworld.acaraku.dto.response.ResponseData;
import id.fabiworld.acaraku.dto.response.ResponseListData;
import id.fabiworld.acaraku.dto.response.ResponseMessage;
import id.fabiworld.acaraku.model.Agenda;
import id.fabiworld.acaraku.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/agenda")
public class AgendaController {
    private final AgendaService agendaService;

    @Autowired
    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @GetMapping
    public ResponseListData<Agenda> findAll(){
        return new ResponseListData<>(agendaService.findAll());
    }

    @GetMapping("/search")
    public ResponseListData<Agenda> search(@RequestParam("keyword") String keyword){
        return new ResponseListData<>(agendaService.search(keyword));
    }

    @GetMapping("/{id}")
    public ResponseData<Agenda> findById(@PathVariable("id") Long id){
        return new ResponseData<>(agendaService.findById(id));
    }

    @PostMapping("/check-url")
    public ResponseData<Boolean> checkSubUrl(@RequestBody Map<String, String> data){
        return new ResponseData<>(agendaService.checkSubUrl(data.get("url")));
    }

    @PutMapping("/{id}")
    public ResponseMessage<Agenda> update(@PathVariable("id") Long id, @RequestBody AgendaDTO dto){
        return new ResponseMessage<>(agendaService.update(id, dto), "Berhasil Memperbarui Data");
    }

    @PutMapping("/cancel/{id}")
    public ResponseMessage<Agenda> cancelAgenda(@PathVariable("id") Long id){
        return new ResponseMessage<>(agendaService.cancelAgenda(id), "Agenda Telah Dibatalkan");
    }
}
