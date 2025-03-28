package com.fpoly.kickshop.service;


import com.fpoly.kickshop.model.Customer;
import com.fpoly.kickshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Customer registerCustomer(String name, String email, String password, String phone) {
        if (customerRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email was exist!");
        }

        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPassword(passwordEncoder.encode(password)); // Mã hóa mật khẩu
        customer.setPhone(phone);
        customer.setStatus(true);

        return customerRepository.save(customer);
    }

    public Customer loadCustomerByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Khách hàng không tồn tại!"));
    }

    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer findById(Integer id) {
        Customer customer = customerRepository.findById(id).get();
        return  customer;
    }

    public Customer getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id).get();
        return  customer;
    }
}
