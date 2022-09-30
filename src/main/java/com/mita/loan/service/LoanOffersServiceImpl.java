package com.mita.loan.service;

import com.mita.loan.dto.loanOffers.UpsertLoanOffersDTO;
import com.mita.loan.entity.Categories;
import com.mita.loan.entity.LoanOffers;
import com.mita.loan.repository.CategoriesRepository;
import com.mita.loan.repository.LoanOffersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanOffersServiceImpl implements LoanOffersService{

    @Autowired
    private LoanOffersRepository loanOffersRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public Integer addLoanOffers(UpsertLoanOffersDTO dto) {

        Optional<Categories> theCategory = categoriesRepository.findById(dto.getCategoryId());
        Categories categories = null;
        if(theCategory.isPresent()){
            categories = theCategory.get();}

        LoanOffers loanOffers = new LoanOffers();
        loanOffers.setId(dto.getId());
        loanOffers.setLoanName(dto.getLoanName());
        loanOffers.setAmount(dto.getAmount());
        loanOffers.setLoanTerm(dto.getLoanTerm());
        loanOffers.setInterestRate(dto.getInterestRate());
        loanOffers.setRepayment(dto.getRepayment());
        loanOffers.setTotalCost(dto.getTotalPayment());
        loanOffers.setCategories(categories);
        loanOffers.setCategoryId(categories.getId());

        loanOffersRepository.save(loanOffers);

        return loanOffers.getId();
    }

    @Override
    public List<LoanOffers> getAllLoanOffers() {
        List<LoanOffers> loanOffersList = loanOffersRepository.findAll();
        return loanOffersList;
    }

    @Override
    public LoanOffers getLoanOffersByName(String loanName) {
        LoanOffers loanOffers = loanOffersRepository.getByName(loanName);
        return loanOffers;
    }

    @Override
    public LoanOffers getLoanOffers(Integer id) {
        Optional<LoanOffers> theLoanOffers = loanOffersRepository.findById(id);

        LoanOffers loanOffers = null;
        if(theLoanOffers.isPresent()){
            loanOffers = theLoanOffers.get();
        }
        return loanOffers;
    }

    @Override
    public LoanOffers updateLoanOffers(Integer id,UpsertLoanOffersDTO dto) {
        Optional<LoanOffers> theLoanOffers = loanOffersRepository.findById(id);

        LoanOffers loanOffers = null;
        if(theLoanOffers.isPresent()){
            loanOffers = theLoanOffers.get();
        }

        Optional<Categories> theCategory = categoriesRepository.findById(dto.getCategoryId());
        Categories categories = null;
        if(theLoanOffers.isPresent()){
            categories = theCategory.get();}

        if(dto.getCategoryId()!=0){
            loanOffers.setCategoryId(categories.getId());
            loanOffers.setCategories(categories);
        }

        if(dto.getLoanName()!=null){
            loanOffers.setLoanName(dto.getLoanName());}
        if(dto.getAmount()!=null){
            loanOffers.setAmount(dto.getAmount());}
        if(dto.getLoanTerm()!=null){
            loanOffers.setLoanTerm(dto.getLoanTerm());}
        if(dto.getInterestRate()!=null){
            loanOffers.setInterestRate(dto.getInterestRate());}
        if(dto.getRepayment()!=null){
            loanOffers.setRepayment(dto.getRepayment());}
        if(dto.getTotalPayment()!=null){
            loanOffers.setTotalCost(dto.getTotalPayment());}

        loanOffersRepository.save(loanOffers);
        return loanOffers;
    }

    @Override
    public void deleteLoanOffer(UpsertLoanOffersDTO dto) {
        loanOffersRepository.deleteById(dto.getId());
    }


}
