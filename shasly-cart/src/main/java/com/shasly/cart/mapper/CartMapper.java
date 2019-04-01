package com.shasly.cart.mapper;

import com.shasly.common.bean.Cart;
import com.shasly.common.bean.CartList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {

    public int insert(Cart cart) ;

    public int updateNumberById(@Param("id") Integer id,@Param("number") Integer number) ;

    public int deleteById(int id) ;

    public int getCidByUid(Integer uid);

    List<CartList> getCartListByCid(int cid);
}
