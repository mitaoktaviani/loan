package com.mita.loan.rest;

import com.mita.loan.dto.loanOffers.UpsertLoanOffersDTO;
import com.mita.loan.entity.LoanOffers;
import com.mita.loan.service.LoanOffersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/loanOffers")
@Tag(name = "Loan Offers")
public class LoanOffersRestController {

    @Autowired
    private LoanOffersService loanOffersService;

    @Operation(summary = "Add Loan Offers")
    @PostMapping("/add")
    public ResponseEntity<Object> addLoanOffers(@Valid @RequestBody UpsertLoanOffersDTO dto,
                                                BindingResult bindingResult){

        if(!bindingResult.hasErrors()){
            Integer respondId = loanOffersService.addLoanOffers(dto);
            dto.setId(respondId);

            return new ResponseEntity<>("Successful add Loan Offers", HttpStatus.CREATED);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Validation Failed, Http Request is not validated.");
        }
    }

    @Operation(summary = "List of Loan Offers")
    @GetMapping("/list")
    public ResponseEntity<Object> list(){

        List<LoanOffers> loanOffersList = loanOffersService.getAllLoanOffers();

        Stream<LoanOffers> stream = loanOffersList.stream().sorted();

        if(loanOffersList==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loan Offers is Not Found");
        }
        return new ResponseEntity<>(stream,HttpStatus.OK);
    }

    @Operation(summary = "Search Loan Offers By Loan Name")
    @GetMapping("/{loanName}")
    public ResponseEntity<Object> search(@PathVariable String loanName){

        LoanOffers loanOffer = loanOffersService.getLoanOffersByName(loanName);

        if(loanOffer == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loan Offers is Not Found");
        }
        return new ResponseEntity<>(loanOffer, HttpStatus.OK);
    }

    @Operation(summary = "Update Loan Offers")
    @PutMapping("/update")
    public ResponseEntity<Object> edit(@RequestBody UpsertLoanOffersDTO dto,
                                       BindingResult bindingResult){

        LoanOffers loanOffers = loanOffersService.getLoanOffers(dto.getId());

        if(loanOffers==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loan Offers is not Found");
        }else if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Validation Failed, Http Request Body is not validated.");
        }else{
            LoanOffers roleMenu1 = loanOffersService.updateLoanOffers(dto.getId(), dto);
            return new ResponseEntity<>(roleMenu1, HttpStatus.OK);
        }
    }

    @Operation(summary = "Delete Loan Offer", description = "Delete loan offer")
    @ApiResponse(responseCode = "200",
            content = {@Content(
                    mediaType = "application/json",
                    schema = @Schema( implementation = LoanOffers.class)
            )})
    @DeleteMapping(value={"/delete"})
    public ResponseEntity<Object> deleteRoleMenu(@RequestBody UpsertLoanOffersDTO dto){

        LoanOffers loanOffers = loanOffersService.getLoanOffers(dto.getId());
        if(loanOffers == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loan Offer is not Found");
        }else{
            loanOffersService.deleteLoanOffer(dto);

            return ResponseEntity.status(HttpStatus.OK).body("Successful Delete Loan Offers");
        }
    }
}
