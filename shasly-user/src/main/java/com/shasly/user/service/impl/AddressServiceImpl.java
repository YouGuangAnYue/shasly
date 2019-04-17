package com.shasly.user.service.impl;

import com.shasly.common.bean.Address;
import com.shasly.user.mapper.AddressMapper;
import com.shasly.user.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressMapper addressMapper ;

    public AddressServiceImpl(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public boolean update(Address address) {
        int len = addressMapper.update(address) ;
        if (len > 0) {
            return true ;
        }
        return false ;
    }

    @Override
    public boolean add(Address address) {
        address.setDef(0);
        int len = addressMapper.insert(address) ;
        if (len > 0) {
            return true ;
        }
        return false ;
    }

    @Override
    public List<Address> findAddressByUId(String uid) {
        return addressMapper.findAddressByUId(uid);
    }

    @Override
    public boolean deleteByUIdAndAId(Integer aid, String uid) {
        int len = addressMapper.deleteByUIdAndAId(aid,uid) ;
        if (len > 0) {
            return true ;
        }
        return false ;
    }
}
