package com.mita.loan.repository;

import com.mita.loan.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDetailRepository extends JpaRepository<TransactionDetail,Integer> {
}
