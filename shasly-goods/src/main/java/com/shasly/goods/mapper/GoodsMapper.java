package com.shasly.goods.mapper;

import com.shasly.common.bean.Goods;
import com.shasly.common.bean.GoodsType;
import com.shasly.goods.vo.GoodsDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {

    GoodsType findGoodsTypeByTId(Integer tid);

    List<Goods> findGoodsByTIdAndLevel(@Param("tid") Integer tid, @Param("level") Integer level);

    List<GoodsType> findAllGoodsType();

    int insertGoodsType(GoodsType goodsType);

    int insertGoods(Goods goods);

    List<Goods> findAllGoods();

    GoodsDetails findGoodsDetailsByGId(Integer gid);

    List<Goods> findGoodsLikeName(String name);

    int updateGoods(Goods goods);

    Goods findGoodsByGId(Integer gid);
}
