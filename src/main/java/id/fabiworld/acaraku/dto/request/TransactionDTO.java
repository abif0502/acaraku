package id.fabiworld.acaraku.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private Long clientId;
    private Long styleId;
    private String title;
    private String description;
    private String address;
    private String coordinate;
    private Long bgmId;
    private Long typeId;
    private List<ScheduleDTO> schedules;
    private List<PictureDTO> pictures;
}
