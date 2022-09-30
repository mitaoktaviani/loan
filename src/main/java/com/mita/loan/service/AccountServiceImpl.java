package com.mita.loan.service;

import com.mita.loan.configuration.ApplicationUserDetails;
import com.mita.loan.configuration.RestSecurityConfiguration;
import com.mita.loan.dto.creditor.UpsertCreditorDTO;
import com.mita.loan.dto.debtor.UpsertDebtorDTO;
import com.mita.loan.entity.Account;
import com.mita.loan.entity.Creditor;
import com.mita.loan.entity.Debtor;
import com.mita.loan.repository.AccountRepository;
import com.mita.loan.repository.CreditorRepository;
import com.mita.loan.repository.DebtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DebtorRepository debtorRepository;

    @Autowired
    private CreditorRepository creditorRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> nullableEntity = accountRepository.findById(username);

        Account account = nullableEntity.get();

        return new ApplicationUserDetails(account);
    }

    @Override
    public String getAccountRole(String username) {
        Optional<Account> nullableEntity = accountRepository.findById(username);

        Account account = nullableEntity.get();
        return account.getRole();
    }

    @Override
    public boolean checkExistingAccount(String username) {
        Long totalUser = accountRepository.countUsername(username);

        return (totalUser > 0) ? true : false;
    }

    @Override
    public Integer registerDebtor(UpsertDebtorDTO dto) {

        PasswordEncoder passwordEncoder = RestSecurityConfiguration.getPasswordEncoder();
        String hashPassword = passwordEncoder.encode(dto.getPassword());

        String role = "Debtor";

        Account account = new Account(dto.getUsername(),
                hashPassword,
                role);
        Debtor debtor = new Debtor();
        debtor.setId(dto.getId());
        debtor.setAccount(account);
        debtor.setFirstName(dto.getFirstName());
        debtor.setLastName(dto.getLastName());
        debtor.setAddress(dto.getAddress());
        debtor.setAge(dto.getAge());
        debtor.setCompany(dto.getCompany());
        debtor.setContactNumber(dto.getContactNumber());
        debtor.setProfession(dto.getProfession());
        debtor.setGender(dto.getGender());
        debtor.setIdCardNumber(dto.getIdCardNumber());

        debtorRepository.save(debtor);

        return debtor.getId();
    }

    @Override
    public Integer registerCreditor(UpsertCreditorDTO dto) {
        PasswordEncoder passwordEncoder = RestSecurityConfiguration.getPasswordEncoder();
        String hashPassword = passwordEncoder.encode(dto.getPassword());

        String role = "Creditor";

        Account account = new Account(dto.getUsername(),
                hashPassword,
                role);

        Creditor creditor = new Creditor();
        creditor.setId(dto.getId());
        creditor.setAccount(account);
        creditor.setFirstName(dto.getFirstName());
        creditor.setLastName(dto.getLastName());
        creditor.setJobTitle(dto.getJobTitle());
        creditor.setAddress(dto.getAddress());

        creditorRepository.save(creditor);

        return creditor.getId();
    }
}
