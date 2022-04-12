package id.fabiworld.acaraku.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String instagram;
    private String facebook;
    private String youtube;
}
