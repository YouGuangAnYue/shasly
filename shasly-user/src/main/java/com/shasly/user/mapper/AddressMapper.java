package com.shasly.user.mapper;

import com.shasly.common.bean.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {

    int update(Address address) ;

    int insert(Address address) ;

    List<Address> findAddressByUId(String uid);

    int deleteByUIdAndAId(@Param("aid") Integer aid,@Param("uid") String uid);
}
