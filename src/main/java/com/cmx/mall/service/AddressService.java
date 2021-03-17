package com.cmx.mall.service;

import com.cmx.mall.mapper.IAddressMapper;
import com.cmx.mall.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private IAddressMapper addressMapper;

    public List<Address> findAddressByUsername(String username) {
        List<Address> addressList = addressMapper.findAddressByUsername(username);
        return addressList;
    }
}
