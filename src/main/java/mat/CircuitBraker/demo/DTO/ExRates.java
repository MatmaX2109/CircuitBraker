package mat.CircuitBraker.demo.DTO;


import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExRates{

    private String success;
    private Date timestamp;
    private String base;
    private String date;
    private Map<String, BigDecimal> rates;

}
