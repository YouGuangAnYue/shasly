package com.shasly.cart.service.impl;/*
 *________________********_______________________
 *______________************_____________________
 *______________*************____________________
 *_____________**__***********___________________
 *____________***__******_*****__________________
 *____________***_*******___****_________________
 *___________***__**********_****________________
 *__________****__***********_****_______________
 *________*****___***********__*****_____________
 *_______******___***_********___*****___________
 *_______*****___***___********___******_________
 *______******___***__***********___******_______
 *_____******___****_**************__******______
 *____*******__***** ***************_ ******_____
 *____*******__***** ****************_ ******____
 *___*******__******_*****************_*******___
 *___*******__******_******_*********___******___
 *___*******____**__******___******_____******___
 *___*******________******____*****_____*****____
 *____******________*****_____*****_____****_____
 *_____*****________****______*****_____***______
 *______*****______;***________***______*________
 *________**_______****________****______________
 *
 *          初闻天籁之音,未使心之将来——初音未来
 *
 */

import com.shasly.cart.mapper.CartMapper;
import com.shasly.cart.service.CartService;
import com.shasly.common.bean.Cart;
import com.shasly.common.bean.CartList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartMapper cartMapper ;

    public CartServiceImpl(CartMapper cartMapper) {
        this.cartMapper = cartMapper ;
    }

    /**
     * 获取购物车清单
     */
    public List<CartList> getCartList(Integer uid) {
        //获取对应购物车id
        int cid = cartMapper.getCidByUid(uid) ;

        return cartMapper.getCartListByCid(cid) ;
    }

    @Override
    public boolean add(Cart cart) {
        int len = cartMapper.insert(cart) ;
        if (len > 0) {
            return true ;
        }
        return false ;
    }

    @Override
    public boolean removeById(int id) {
        int len = cartMapper.deleteById(id) ;
        if (len > 0) {
            return true ;
        }
        return false ;
    }

    @Override
    public boolean removeByIdAndPId(int id ,int pid) {
        int len = cartMapper.deleteByIdAndPId(id, pid) ;
        if (len > 0) {
            return true ;
        }
        return false ;
    }

    @Override
    public Cart findByIdAndPId(int id, int pid) {

        return cartMapper.findByIdAndPId(id, pid) ;
    }

    @Override
    public boolean update(Cart cart) {
        int len = cartMapper.update(cart) ;
        if (len > 0) {
            return true ;
        }
        return false ;
    }

}
