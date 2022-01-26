package mat.CircuitBraker.demo.service;

import lombok.RequiredArgsConstructor;
import mat.CircuitBraker.demo.DTO.AccountRequest;
import mat.CircuitBraker.demo.DTO.AccountResponse;
import mat.CircuitBraker.demo.entity.ExRates;
import mat.CircuitBraker.demo.mapper.AccountMapper;
import mat.CircuitBraker.demo.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    private final ExRatesService exRatesService;

    public AccountResponse save(final AccountRequest account){
        return accountMapper.map(accountRepository.save(accountMapper.map(account)));
    }

    public AccountResponse findById(final Long id){

        ExRates test = exRatesService.getExRates();
        System.out.println(test);
        return accountRepository.findById(id).map(accountMapper::map).orElseThrow(() -> {
            throw new EntityNotFoundException("Account not found.");
        });
    }



}
