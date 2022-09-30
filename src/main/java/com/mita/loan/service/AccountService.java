package com.mita.loan.service;

import com.mita.loan.dto.creditor.UpsertCreditorDTO;
import com.mita.loan.dto.debtor.UpsertDebtorDTO;

public interface AccountService {
    public String getAccountRole(String username);

    boolean checkExistingAccount(String username);

    Integer registerDebtor(UpsertDebtorDTO dto);

    Integer registerCreditor(UpsertCreditorDTO dto);
}
