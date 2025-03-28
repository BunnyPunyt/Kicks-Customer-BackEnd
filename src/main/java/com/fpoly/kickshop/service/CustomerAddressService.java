package com.fpoly.kickshop.service;

import com.fpoly.kickshop.model.CustomerAddress;
import com.fpoly.kickshop.repository.CustomerAdressRepository;
import com.fpoly.kickshop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerAddressService {
    @Autowired
    private CustomerAdressRepository customerAdressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerAddress> getAddressesbyCustomerId(Integer customerId) {
    return customerAdressRepository.findByCustomer_Id(customerId);}

    public CustomerAddress addAddress(Integer customerId, CustomerAddress address) {
        // Kiểm tra xem khách hàng có tồn tại không
        return customerRepository.findById(customerId).map(customer -> {
            address.setCustomer(customer);
            return customerAdressRepository.save(address);
        }).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public CustomerAddress updateAddress(Integer customerId, Integer addressId, CustomerAddress updatedAddress) {
        return customerAdressRepository.findById(addressId).map(existingAddress -> {
            if (!existingAddress.getCustomer().getId().equals(customerId)) {
                throw new RuntimeException("Customer ID does not match with the address owner");
            }

            // Cập nhật thông tin
            existingAddress.setCity(updatedAddress.getCity());
            existingAddress.setDistrict(updatedAddress.getDistrict());
            existingAddress.setWard(updatedAddress.getWard());
            existingAddress.setStreet(updatedAddress.getStreet());
            existingAddress.setIsDefault(updatedAddress.getIsDefault());

            return customerAdressRepository.save(existingAddress);
        }).orElseThrow(() -> new RuntimeException("Address not found"));
    }
}
