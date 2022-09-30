package com.mita.loan.rest;

import com.mita.loan.dto.transaction.AcceptLoan;
import com.mita.loan.dto.transaction.ApplyLoan;
import com.mita.loan.dto.transaction.RejectLoan;
import com.mita.loan.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/transaction")
@Tag(name = "Transaction")
public class TransactionRestController {

    @Autowired
    private TransactionService transactionService;

    @Operation(summary = "Apply Loan")
    @PostMapping("/apply")
    public ResponseEntity<Object> applyLoan(@Valid @RequestBody ApplyLoan dto,
                                            BindingResult bindingResult){

        if(!bindingResult.hasErrors()){
            Integer respondId = transactionService.addTransaction(dto);
            dto.setId(respondId);

            return new ResponseEntity<>("Successful Apply Loan", HttpStatus.CREATED);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Validation Failed, Http Request is not validated.");
        }
    }

    @Operation(summary = "Reject Loan")
    @PostMapping("/reject")
    public ResponseEntity<Object> reject(@Valid @RequestBody RejectLoan dto,
                                         BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            Integer respondId = transactionService.reject(dto);
            dto.setTransactionId(respondId);

            return new ResponseEntity<>("Successful Reject Loan", HttpStatus.CREATED);
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Validation Failed, Http Request is not validated.");
    }

    @Operation(summary = "Accept Loan")
    @PostMapping("/accept")
    public ResponseEntity<Object> accept(@Valid @RequestBody AcceptLoan dto,
                                         BindingResult bindingResult){

        if(!bindingResult.hasErrors()){
            Integer respondId = transactionService.accept(dto);
            dto.setTransactionId(respondId);

            return new ResponseEntity<>("Successful Accept Loan", HttpStatus.CREATED);
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Validation Failed, Http Request is not validated.");

    }
}
