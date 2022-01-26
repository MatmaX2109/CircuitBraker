package mat.CircuitBraker.demo.config;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class BaseRestController {

  @Operation(hidden = true)
  protected URI locationByEntity(Long entityId){
        return ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(entityId).toUri();
    }
}
