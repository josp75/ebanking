package cm.studyup.app.dtos;

import cm.studyup.app.enums.AccountStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Data
public class CurrentAccountDto extends BankAccountDto {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDto customerDto;
    private double overDraft;
}
