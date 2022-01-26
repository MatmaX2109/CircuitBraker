package mat.CircuitBraker.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import mat.CircuitBraker.demo.DTO.AccountRequest;
import mat.CircuitBraker.demo.DTO.AccountResponse;
import mat.CircuitBraker.demo.config.BaseRestController;
import mat.CircuitBraker.demo.entity.Account;
import mat.CircuitBraker.demo.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@AllArgsConstructor
@RequestMapping("/account")
@RestController
@Tag(name = "account service", description = "Account service")
public class AccountController extends BaseRestController {

    private final AccountService accountService;

    @PostMapping
    @Operation(
            summary = "Save an account",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Saved"),
                    @ApiResponse(responseCode = "403", description = "de pus msg")
            }
    )
    public ResponseEntity<AccountResponse> save(@RequestBody AccountRequest account) {
        return ResponseEntity.created(locationByEntity(accountService.save(account).getId())).build();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get an account",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Get OK"),
                    @ApiResponse(responseCode = "404", description = "de pus msg")
            }
    )
    public ResponseEntity<AccountResponse> findAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.findById(id));
    }
}
