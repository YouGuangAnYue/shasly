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
    public List<Address> findByUId(int uid) {
        return addressMapper.findByUId(uid) ;
    }

    @Override
    public Address findById(int id) {
        return addressMapper.findById(id) ;
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
        int len = addressMapper.insert(address) ;
        if (len > 0) {
            return true ;
        }
        return false ;
    }

    @Override
    public boolean remove(int id) {
        int len = addressMapper.delete(id) ;
        if (len > 0) {
            return true ;
        }
        return false ;
    }
}
