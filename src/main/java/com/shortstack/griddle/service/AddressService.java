package com.shortstack.griddle.service;

import com.shortstack.griddle.model.Address;
import com.shortstack.griddle.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        return (List<Address>) addressRepository.findAll();
    }

    public Address findAddress(int id) {
        return addressRepository.findById(id).orElse(null);
    }

    public String createAddress(Address address) {
        addressRepository.save(address);
        return "Address added";
    }

    public Address updateAddress(Address address) {
        Optional<Address> optionalAddress = addressRepository.findById(address.getAddressID());
        Address oldAddress = null;
        if(optionalAddress.isPresent()) {
            oldAddress = optionalAddress.get();
            oldAddress.setAddressID(address.getAddressID());
            oldAddress.setStreet(address.getStreet());
            oldAddress.setCity(address.getCity());
            oldAddress.setState(address.getState());
            oldAddress.setZip(address.getZip());
        } else {
            return new Address();
        }
        return oldAddress;
    }

    public String deleteAddress(int id) {
        addressRepository.deleteById(id);
        return "Address deleted";
    }
}
