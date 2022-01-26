package mat.CircuitBraker.demo.mapper;


import mat.CircuitBraker.demo.DTO.AccountRequest;
import mat.CircuitBraker.demo.DTO.AccountResponse;
import mat.CircuitBraker.demo.entity.Account;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountResponse map(Account source);

    Account map(AccountRequest source);
}
