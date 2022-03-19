package id.fabiworld.pestaku.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseListData <E>{
    private List<E> data;

    public ResponseListData(List<E> data) {
        this.data = data;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }
}
