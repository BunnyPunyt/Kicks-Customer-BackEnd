package com.fpoly.kickshop.repository;

import com.fpoly.kickshop.model.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerAdressRepository extends JpaRepository<CustomerAddress, Integer> {
    List<CustomerAddress> findByCustomer_Id(Integer customerId);
}
