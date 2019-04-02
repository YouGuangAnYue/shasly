package com.shasly.user.service;

import com.shasly.common.bean.Address;

import java.util.List;

public interface AddressService {
    public List<Address> findByUId(int uid) ;
    public Address findById(int id) ;

    public boolean update(Address address) ;

    public boolean add(Address address) ;

    public boolean remove(int id) ;
}
