package mat.CircuitBraker.demo.repository;

import mat.CircuitBraker.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
