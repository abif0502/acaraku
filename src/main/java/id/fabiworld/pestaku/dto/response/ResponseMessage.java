package id.fabiworld.pestaku.dto.response;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

public class ResponseMessage<E> extends ResponseData<E>{
    private String message;

    public ResponseMessage(E data, String message) {
        super(data);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
