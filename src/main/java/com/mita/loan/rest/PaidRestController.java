package com.mita.loan.rest;

import com.mita.loan.dto.paymentHistory.PaymentHistoryDTO;
import com.mita.loan.dto.transactionDetail.PaidInstalmentDTO;
import com.mita.loan.service.PaymentHistoryService;
import com.mita.loan.service.TransactionDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/instalment")
@Tag(name = "Instalment")
public class PaidRestController {

    @Autowired
    private TransactionDetailService transactionDetailService;

    @Autowired
    private PaymentHistoryService paymentHistoryService;

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

    @Operation(summary = "History Paid Instalment")
    @GetMapping("/history")
    public ResponseEntity<Object> historyInstalment(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        List<PaymentHistoryDTO> history = paymentHistoryService.getAllHistory(username);

        if(history == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(username+ " does not have history paid instalment");
        }
        return new ResponseEntity<>(history,HttpStatus.OK);
    }
}
