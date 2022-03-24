package id.fabiworld.acaraku.dto.request;


import id.fabiworld.acaraku.model.enumvalue.GeneralStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StyleDTO {
    private String name;
    private Double price;
    private Double discount;
    private GeneralStatus status;
}
