package mat.CircuitBraker.demo.service;

import mat.CircuitBraker.demo.DTO.AccountResponse;
import mat.CircuitBraker.demo.entity.Account;
import mat.CircuitBraker.demo.entity.ExRates;
import mat.CircuitBraker.demo.exceptions.ServiceDownException;
import mat.CircuitBraker.demo.mapper.AccountMapper;
import mat.CircuitBraker.demo.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

//@SpringBootTest
//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    AccountService accountService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    AccountMapper accountMapper;

    @Mock
    ExRatesService exRatesService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test(expected = EntityNotFoundException.class)
    public void findById_ThrowException() {

        Account account = new Account();
        when(accountRepository.findById((long)1)).thenReturn(java.util.Optional.of(account));
        accountService.findById((long) 1);
    }

    @Test
    public void findById_ok() {

        final String IBAN = "ROXXX";

        Account account = Account.builder().iban("RO").balance(new BigDecimal(10)).currency("RON").build();
        when(accountRepository.findById((long)1)).thenReturn(java.util.Optional.of(account));
        when(accountMapper.map(account)).thenReturn(AccountResponse.builder().iban(IBAN).build());
        AccountResponse test = accountService.findById((long) 1);
        assertEquals(test.getIban(), IBAN);
    }

    @Test
    public void findByIdInEuro_ok() {


        final String IBAN = "ROXXX";
        Account account = Account.builder().iban("RO").balance(new BigDecimal(10)).currency("RON").build();
        when(accountRepository.findById((long)1)).thenReturn(java.util.Optional.of(account));
        when(accountMapper.map(account)).thenReturn(AccountResponse.builder().iban(IBAN).build());

        AccountResponse accountResponse  = AccountResponse.builder().iban("RO").balance(new BigDecimal(10)).currency("RON").build();
        when(accountService.findById((long)1)).thenReturn(accountResponse);


        Map<String, BigDecimal> rate = new HashMap<>();
        rate.put("RON", new BigDecimal(5));

        ExRates exRates = ExRates.builder()
                .base("EUR")
                .rates(rate)
                .build();

        when(exRatesService.getExRates()).thenReturn(exRates);

        AccountResponse test = accountService.findByIdInEuro((long) 1);
        assertEquals(test.getBalance(), new BigDecimal(50));
    }

    @Test(expected = ServiceDownException.class)
    public void findByIdInEuro_noRates() {

        final String IBAN = "ROXXX";
        Account account = Account.builder().iban("RO").balance(new BigDecimal(10)).currency("RON").build();
        when(accountRepository.findById((long)1)).thenReturn(java.util.Optional.of(account));
        when(accountMapper.map(account)).thenReturn(AccountResponse.builder().iban(IBAN).build());

        AccountResponse accountResponse  = AccountResponse.builder().iban("RO").balance(new BigDecimal(10)).currency("RON").build();
        when(accountService.findById((long)1)).thenReturn(accountResponse);


        Map<String, BigDecimal> rate = new HashMap<>();
        rate.put("RON", new BigDecimal(5));

        ExRates exRates = null;

        when(exRatesService.getExRates()).thenReturn(exRates);

        accountService.findByIdInEuro((long) 1);

    }
}