package com.cmx.mall.mapper;

import com.cmx.mall.model.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IAddressMapper {

    List<Address> findAddressByUsername(String username);

    void savaAddress(Address address);

    Address findById(Integer id);

    void updateAddress(Address address);

    Boolean delById(Integer addressId);

    Address findIsDefault();

    void updateIsDefault(Address address);
}
