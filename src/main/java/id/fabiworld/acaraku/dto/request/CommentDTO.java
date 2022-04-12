package id.fabiworld.acaraku.dto.request;

import id.fabiworld.acaraku.model.enumvalue.AttendStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private String name;
    private String phoneNumber;
    private String description;
    private AttendStatus attend;
}
