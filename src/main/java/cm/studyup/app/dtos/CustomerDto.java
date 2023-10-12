package cm.studyup.app.dtos;

import cm.studyup.app.validation.UniqueEmail;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long id;

    @NotEmpty
    @NotNull
    private String name;

    @Email
    @NotEmpty
    @UniqueEmail()
    private String email;
}
