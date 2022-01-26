package mat.CircuitBraker.demo.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class AccountResponse {

    Long id;

    String iban;

    String currency;

    BigDecimal balance;

    LocalDateTime lastUpdate;

}
