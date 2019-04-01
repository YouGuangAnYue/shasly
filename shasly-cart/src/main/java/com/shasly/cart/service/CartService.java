package com.shasly.cart.service;

import com.shasly.common.bean.Cart;

import java.util.List;

public interface CartService {

    public Cart findByIdAndPId(int id, int pid);

    public boolean add(Cart cart) ;

    public boolean update(Cart cart) ;

    public boolean removeById(int id) ;
    public boolean removeByIdAndPId(int id,int pid) ;
}
