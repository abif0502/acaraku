package id.fabiworld.acaraku.dto.response;

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
