package com.mita.loan.rest;

import com.mita.loan.dto.transactionDetail.PaidInstalmentDTO;
import com.mita.loan.service.TransactionDetailService;
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
@RequestMapping("/api/instalment")
@Tag(name = "Instalment")
public class PaidRestController {

    @Autowired
    private TransactionDetailService transactionDetailService;

    @Operation(summary = "Paid Instalment")
    @PostMapping("/paid")
    public ResponseEntity<Object> paid(@Valid @RequestBody PaidInstalmentDTO dto,
                                       BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Validation Failed, Http Request is not validated.");
        }else{
            Integer respondId = transactionDetailService.paidLoan(dto);
            dto.setTransactionDetailID(respondId);

            return new ResponseEntity<>("Successful paid instalment",HttpStatus.CREATED);
        }
    }
}
