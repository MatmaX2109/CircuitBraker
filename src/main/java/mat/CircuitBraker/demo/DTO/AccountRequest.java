package mat.CircuitBraker.demo.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class AccountRequest {

    @Schema(name = "iban", type = "String", description = "IBAN", required = true, example = "ROXXXXXXXX")
    String iban;

    @Schema(name = "currency", type = "String", description = "Currency", required = true, example = "RON")
    String currency;

    @Schema(name = "balance", type = "BigDecimal", description = "Balance", required = true, example = "10")
    BigDecimal balance;
}
