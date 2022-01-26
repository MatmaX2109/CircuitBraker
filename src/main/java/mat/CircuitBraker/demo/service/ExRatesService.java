package mat.CircuitBraker.demo.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import mat.CircuitBraker.demo.entity.ExRates;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class ExRatesService {

    RestTemplate restTemplate = new RestTemplate();

//    final static String baseUrl = "http://api.exchangeratesapi.io/v1/latest?access_key=4e841514a5efeadd79726805c2bf1430";
    final static String baseUrl = "";


    //    @Cacheable(value = "exRates")
    @HystrixCommand(fallbackMethod = "pingFallback")
    public ExRates getExRates() {
        URI uri = null;
        try {
            uri = new URI(baseUrl);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ResponseEntity<ExRates> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, ExRates.class);
        return responseEntity.getBody();
    }
    private ExRates pingFallback() {
        return null ;

    }

    //    @Scheduled(cron = "0 0/30 * * * ?")
//    @CacheEvict(value = "exRates")
    public void clearAppsCache() {
    }

}
