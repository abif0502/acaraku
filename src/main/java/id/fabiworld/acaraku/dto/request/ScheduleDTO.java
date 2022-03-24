package id.fabiworld.acaraku.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {
    private String activityName;
    private String startTime;
    private String endTime;
}
