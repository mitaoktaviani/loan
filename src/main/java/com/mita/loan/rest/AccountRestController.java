package com.mita.loan.rest;

import com.mita.loan.dto.account.JwtToken;
import com.mita.loan.dto.account.RequestTokenDTO;
import com.mita.loan.dto.account.ResponseTokenDTO;
import com.mita.loan.dto.creditor.UpsertCreditorDTO;
import com.mita.loan.dto.debtor.UpsertDebtorDTO;
import com.mita.loan.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/account")
@Tag(name = "Account")
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Operation(summary = "Authenticate")
    @PostMapping("/authenticate")
    public ResponseTokenDTO post(@RequestBody RequestTokenDTO dto) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
//            authenticationManager.authenticate(token);

            Authentication authentication = authenticationManager.authenticate(token);
            System.out.println("Is authenticate: " + authentication.isAuthenticated());
            System.out.println("Principal: " + authentication.getPrincipal());
            System.out.println("Credential: " + authentication.getCredentials());
        } catch (Exception exception) {
            System.out.println("Can not authenticate!");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not authenticate", exception);
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
        System.out.println("userDetails: " + userDetails.getUsername() + ", " + userDetails.getPassword());

        String role = accountService.getAccountRole(dto.getUsername());
        String token = jwtToken.generateToken(dto.getSubject(), dto.getUsername(), dto.getSecretKey(), role, dto.getAudience());
        ResponseTokenDTO response = new ResponseTokenDTO(dto.getUsername(), role, token);
        return response;
    }

    @Operation(summary = "Register As Debtor")
    @PostMapping("/register")
    public ResponseEntity<Object> register(@Valid @RequestBody UpsertDebtorDTO dto,
                                           BindingResult bindingResult){

        if(!bindingResult.hasErrors()){
            Integer respondId = accountService.registerDebtor(dto);
            dto.setId(respondId);

            return new ResponseEntity<>("Successful register As Debtor",HttpStatus.CREATED);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Validation Failed, Http Request is not validated.");
        }
    }

    @Operation(summary = "Add new Creditor")
    @PostMapping("/addCreditor")
    public ResponseEntity<Object> addCreditor(@Valid @RequestBody UpsertCreditorDTO dto,
                                           BindingResult bindingResult){

        if(!bindingResult.hasErrors()){
            Integer respondId = accountService.registerCreditor(dto);
            dto.setId(respondId);

            return new ResponseEntity<>("Successful add new Creditor",HttpStatus.CREATED);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Validation Failed, Http Request is not validated.");
        }

    }



}
