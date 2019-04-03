package com.shasly.cart.service;

import com.shasly.common.bean.CartDetail;
import com.shasly.common.bean.CartList;

import java.util.List;

public interface CartService {

    List<CartList> getCartListByCId(Integer cid) ;

    public CartDetail findByCIdAndGId(Integer cid, Integer gid);

    public boolean addCartDetail(CartDetail cartDetail) ;

    public boolean updateCartDetail(CartDetail cartDetail) ;

    public boolean removeByCId(Integer cid) ;
    public boolean removeByCIdAndGId(Integer cid,Integer gid) ;

    Integer findCIdByUId(Integer uid);

    boolean insertCartDetail(String token,CartDetail cartDetail);

    List<CartList> findCartListByToken(String token);

    boolean updateCartDetailNumber(String token,Integer gid ,Integer number);

    boolean clearCart(String token);

}
