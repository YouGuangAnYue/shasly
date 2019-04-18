package com.shasly.cart.mapper;

import com.shasly.common.bean.CartDetail;
import com.shasly.common.bean.CartList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {

    int insertCartDetail(CartDetail cart) ;

    int updateNumberById(@Param("id") Integer id, @Param("number") Integer number) ;

    int deleteById(int id) ;

    int getCidByUid(Integer uid);

    List<CartList> getCartListByCid(int cid);

    int updateNumberByCIdAndGId(@Param("cid") String cid,@Param("gid") Integer gid,@Param("number") Integer number);

    CartDetail findCartDetailByCIdAndGId(@Param(value = "cid") String cid,@Param(value = "gid") Integer gid);

    int deleteByCId(int cid);

    Integer findCIdByUId(Integer uid);
}
