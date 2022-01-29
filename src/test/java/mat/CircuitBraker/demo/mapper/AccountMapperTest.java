package mat.CircuitBraker.demo.mapper;

import mat.CircuitBraker.demo.DTO.AccountRequest;
import mat.CircuitBraker.demo.DTO.AccountResponse;
import mat.CircuitBraker.demo.entity.Account;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class AccountMapperTest {

    @Autowired
    AccountMapper accountMapper;

    @Test
    void map_accountToAccountResponse() {
        Account account = Account.builder().iban("RO").balance(new BigDecimal(10)).currency("RON").build();
        AccountResponse response = accountMapper.map(account);
        assertEquals(response.getIban(), account.getIban());
        assertEquals(response.getBalance(), account.getBalance());
        assertEquals(response.getCurrency(), account.getCurrency());

    }

    @Test
    void map_accountRequestToAccount() {
        AccountRequest source = AccountRequest.builder().iban("RO").balance(new BigDecimal(10)).currency("RON").build();
        Account response = accountMapper.map(source);
        assertEquals(response.getIban(), source.getIban());
        assertEquals(response.getBalance(), source.getBalance());
        assertEquals(response.getCurrency(), source.getCurrency());

    }
}