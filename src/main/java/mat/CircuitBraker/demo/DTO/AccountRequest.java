package mat.CircuitBraker.demo.DTO;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AccountRequest {

    String iban;

    String currency;

    BigDecimal balance;
}
