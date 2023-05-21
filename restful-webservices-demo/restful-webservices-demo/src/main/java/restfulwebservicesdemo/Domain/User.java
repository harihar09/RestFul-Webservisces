package restfulwebservicesdemo.Domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
@Getter
@AllArgsConstructor
public class User {
    private Integer id;
    @NotBlank(message = "must not be blank")
    private String name;
    @Past(message = "should be in past")
    private LocalDate date;
}
