package com.shasly.user.mapper;

import com.shasly.common.bean.Address;

import java.util.List;

public interface AddressMapper {
    public List<Address> findByUId(int uid) ;
    public Address findById(int id) ;

    public int update(Address address) ;

    public int insert(Address address) ;

    public int delete(int id) ;
}
