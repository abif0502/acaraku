package id.fabiworld.acaraku.dto.request;

import id.fabiworld.acaraku.model.enumvalue.AgendaStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgendaDTO {
    public String title;
    public String description;
    public String address;
    public String coordinate;
    public String subUrl;
    public Long bgmId;
    public AgendaStatus status;
    public Long typeId;
}
