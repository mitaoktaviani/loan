package com.mita.loan.rest;

import com.mita.loan.dto.transaction.AcceptLoan;
import com.mita.loan.dto.transaction.ApplyLoan;
import com.mita.loan.dto.transaction.RejectLoan;
import com.mita.loan.entity.Transaction;
import com.mita.loan.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @Operation(summary = "List of All Transaction")
    @GetMapping("/list")
    public ResponseEntity<Object> listAllTransaction(){

        List<Transaction> transactionList = transactionService.getAllTransaction();

        if(transactionList == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pending transaction does not exist");
        }
        return new ResponseEntity<>(transactionList,HttpStatus.OK);
    }

    @Operation(summary = "List of Pending Transaction")
    @GetMapping("/pendingTransaction")
    public ResponseEntity<Object> listPendingTransaction(){

        List<Transaction> transactionList = transactionService.getAllTransaction();

        List<Transaction> pendingTransaction = transactionList.stream()
                .filter(t -> t.isPending()==true)
                .collect(Collectors.toList());

        if(pendingTransaction == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pending transaction does not exist");
        }
        return new ResponseEntity<>(pendingTransaction,HttpStatus.OK);
    }

    @Operation(summary = "List of Reject Transaction")
    @GetMapping("/rejectTransaction")
    public ResponseEntity<Object> listRejectTransaction(){

        List<Transaction> transactionList = transactionService.getAllTransaction();

        List<Transaction> rejectTransaction = transactionList.stream()
                .filter(t -> t.isReject()==true)
                .collect(Collectors.toList());

        if(rejectTransaction == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pending transaction does not exist");
        }
        return new ResponseEntity<>(rejectTransaction,HttpStatus.OK);
    }

    @Operation(summary = "List of Accept Transaction",description = "Access only by creditor")
    @GetMapping("/acceptTransaction")
    public ResponseEntity<Object> listAcceptTransaction(){

        List<Transaction> transactionList = transactionService.getAllTransaction();

        List<Transaction> acceptTransaction = transactionList.stream()
                .filter(t -> t.isAccept()==true)
                .collect(Collectors.toList());

        if(acceptTransaction == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pending transaction does not exist");
        }
        return new ResponseEntity<>(acceptTransaction,HttpStatus.OK);
    }


}
