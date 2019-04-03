package com.shasly.user.service;

import com.shasly.common.bean.Address;

import java.util.List;

public interface AddressService {

    public boolean update(Address address) ;

    public boolean add(Address address) ;

    List<Address> findAddressByUId(String uid);

    boolean deleteByUIdAndAId(Integer aid, String uid);
}
