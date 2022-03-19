package id.fabiworld.pestaku.controller;

import id.fabiworld.pestaku.dto.request.StyleDTO;
import id.fabiworld.pestaku.dto.response.ResponseData;
import id.fabiworld.pestaku.dto.response.ResponseListData;
import id.fabiworld.pestaku.dto.response.ResponseMessage;
import id.fabiworld.pestaku.model.Style;
import id.fabiworld.pestaku.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/style")
public class StyleController {
    private final StyleService styleService;

    @Autowired
    public StyleController(StyleService styleService) {
        this.styleService = styleService;
    }

    @GetMapping
    public ResponseListData<Style> findAll(){
        return new ResponseListData<>(styleService.findAll());
    }

    @GetMapping("/active")
    public ResponseListData<Style> findAllActive(){
        return new ResponseListData<>(styleService.findAllActive());
    }

    @GetMapping("/{id}")
    public ResponseData<Style> findById(@PathVariable("id") Long id){
        return new ResponseData<>(styleService.findById(id));
    }

    @GetMapping("/active/{id}")
    public ResponseData<Style> findByIdActive(@PathVariable("id") Long id){
        return new ResponseData<>(styleService.findByIdActive(id));
    }

    @PostMapping
    public ResponseMessage<Style> create(@RequestBody StyleDTO style){

        return new ResponseMessage<>(styleService.create(style), "Data Berhasil Ditambahkan");
    }

    @PutMapping("/{id}")
    public ResponseMessage<Style> update(@RequestBody StyleDTO style, @PathVariable("id") Long id){
        return new ResponseMessage<>(styleService.update(id, style), "Data Berhasil Diperbaharui");
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<Style> delete(@PathVariable("id") Long id){
        return new ResponseMessage<>(styleService.delete(id), "Data Berhasil Dihapus");
    }

}
