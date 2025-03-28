package com.fpoly.kickshop.controller;

import com.fpoly.kickshop.model.CustomerAddress;
import com.fpoly.kickshop.service.CustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class CustomerAddressController {
    @Autowired
    private CustomerAddressService customerAddressService;

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CustomerAddress>> getAddressesByCustomerId(@PathVariable Integer customerId) {
        List<CustomerAddress> addresses = customerAddressService.getAddressesbyCustomerId(customerId);
        System.out.println("addresses");
        System.out.println(addresses);

        if (!addresses.isEmpty()) {
            return ResponseEntity.ok(addresses);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/customer/{customerId}")
    public ResponseEntity<CustomerAddress> addAddress(
            @PathVariable Integer customerId,
            @RequestBody CustomerAddress address) {

        try {
            CustomerAddress savedAddress = customerAddressService.addAddress(customerId, address);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/customer/{customerId}/address/{addressId}")
    public ResponseEntity<CustomerAddress> updateAddress(
            @PathVariable Integer customerId,
            @PathVariable Integer addressId,
            @RequestBody CustomerAddress address) {

        try {
            CustomerAddress updatedAddress = customerAddressService.updateAddress(customerId, addressId, address);
            return ResponseEntity.ok(updatedAddress);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
