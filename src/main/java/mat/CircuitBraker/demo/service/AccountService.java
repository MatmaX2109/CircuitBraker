package mat.CircuitBraker.demo.service;

import lombok.RequiredArgsConstructor;
import mat.CircuitBraker.demo.DTO.AccountRequest;
import mat.CircuitBraker.demo.DTO.AccountResponse;
import mat.CircuitBraker.demo.DTO.ExRates;
import mat.CircuitBraker.demo.exceptions.ServiceDownException;
import mat.CircuitBraker.demo.mapper.AccountMapper;
import mat.CircuitBraker.demo.repository.AccountRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    private final ExRatesService exRatesService;

    public AccountResponse save(final AccountRequest account){
        return accountMapper.map(accountRepository.save(accountMapper.map(account)));
    }

    public AccountResponse findByIdInEuro(final Long id){

        AccountResponse accountResponse = findById(id);

        ExRates exRates = exRatesService.getExRates();
        if(exRates == null){
            throw new ServiceDownException("No rates available");
        }

        BigDecimal multiply = exRates.getRates().get(accountResponse.getCurrency());
        accountResponse.setBalance(accountResponse.getBalance().multiply(multiply));
        accountResponse.setCurrency(exRates.getBase());

        return accountResponse;
    }


    public AccountResponse findById(final Long id) {

        return accountRepository.findById(id).map(accountMapper::map).orElseThrow(() -> {
            throw new EntityNotFoundException("Account requested not found.");
        });

    }

}
