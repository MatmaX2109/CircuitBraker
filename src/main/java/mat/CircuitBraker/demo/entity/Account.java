package mat.CircuitBraker.demo.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account extends BaseEntity<Long> {

    @Column
    String iban;

    @Column
    String currency;

    @Column
    BigDecimal balance;
}
