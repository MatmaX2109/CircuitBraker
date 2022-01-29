package mat.CircuitBraker.demo;

import mat.CircuitBraker.demo.DTO.AccountResponse;
import mat.CircuitBraker.demo.DTO.ExRates;
import mat.CircuitBraker.demo.service.AccountService;
import mat.CircuitBraker.demo.service.ExRatesService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class DemoApplicationTests {

	@Autowired
	AccountService accountService;

	@MockBean
	ExRatesService exRatesService;

	@Test
	void E2E() {

		Map<String, BigDecimal> rate = new HashMap<>();
		rate.put("RON", new BigDecimal(5));

		ExRates exRates = ExRates.builder()
				.base("EUR")
				.rates(rate)
				.build();

		when(exRatesService.getExRates()).thenReturn(exRates);

		AccountResponse response = accountService.findByIdInEuro((long)1);
		assertEquals(response.getBalance().compareTo(new BigDecimal(50)), 0);

	}

}
