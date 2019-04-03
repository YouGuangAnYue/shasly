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
import com.shasly.common.bean.CartDetail;
import com.shasly.common.bean.CartList;
import com.shasly.common.jedis.JedisClientPool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private final CartMapper cartMapper ;
    private final JedisClientPool jedisClientPool ;

    public CartServiceImpl(CartMapper cartMapper,JedisClientPool jedisClientPool) {
        this.cartMapper = cartMapper ;
        this.jedisClientPool = jedisClientPool;
    }


    @Override
    public List<CartList> getCartListByCId(Integer cid) {
        return cartMapper.getCartListByCid(cid);
    }

    @Override
    public CartDetail findByCIdAndGId(Integer cid, Integer gid) {
        return null;
    }

    @Override
    public boolean addCartDetail(CartDetail cartDetail) {
        return false;
    }

    @Override
    public boolean updateCartDetail(CartDetail cartDetail) {
        return false;
    }

    @Override
    public boolean removeByCId(Integer cid) {
        return false;
    }

    @Override
    public boolean removeByCIdAndGId(Integer cid, Integer gid) {
        return false;
    }

    @Override
    public Integer findCIdByUId(Integer uid) {
        return null;
    }

    @Override
    public boolean insertCartDetail(String token,CartDetail cartDetail) {
        String cid = getCId(token) ;
        cartDetail.setCid(Integer.parseInt(cid));
        CartDetail _cartDetail = cartMapper.findCartDetailByCIdAndGId(cid,cartDetail.getGid());
        //如果商品已存在
        if (_cartDetail == null){
            int len = cartMapper.insertCartDetail(cartDetail);
            if (len > 0) return true;
            return false;
        }
        //则将数量+1 ；
        _cartDetail.setNumber(_cartDetail.getNumber() + 1);
        return updateCartDetail(_cartDetail) ;
    }

    @Override
    public List<CartList> findCartListByToken(String token) {
        String cid = getCId(token) ;
        List<CartList> list = cartMapper.getCartListByCid(Integer.parseInt(cid));
        return list;
    }

    @Override
    public boolean updateCartDetailNumber(String token,Integer gid, Integer number) {
        String cid = getCId(token) ;
        CartDetail cartDetail = cartMapper.findCartDetailByCIdAndGId(cid, gid);
        //如果购物车中商品数量不足，则失败
        if (cartDetail.getNumber() == 1 && number < 0)
            return false ;
        int len = cartMapper.updateNumberById(cartDetail.getId(),number) ;
        if (len > 0)
            return true ;

        return false;
    }

    @Override
    public boolean clearCart(String token) {

        String cid = getCId(token) ;
        int len = cartMapper.deleteByCId(Integer.parseInt(cid)) ;

        if (len > 0)
            return true ;

        return false ;
    }

    /**
     * 获取购物车id
     * @param token
     * @return
     */
    private String getCId(String token) {
        String uid = jedisClientPool.get(token);
        String cid = jedisClientPool.get(uid + "cart") ;
        if (cid == null){
            cid = "" + findCIdByUId(Integer.parseInt(uid)) ;
            jedisClientPool.setex(uid + "cart",cid,30*60) ;
        }
        return cid ;
    }

}
