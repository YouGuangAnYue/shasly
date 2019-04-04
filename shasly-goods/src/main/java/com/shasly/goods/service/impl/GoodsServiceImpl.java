package com.shasly.goods.service.impl;

import com.shasly.common.bean.Goods;
import com.shasly.common.bean.GoodsType;
import com.shasly.goods.mapper.GoodsMapper;
import com.shasly.goods.service.GoodsService;
import com.shasly.goods.vo.GoodsDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    private final GoodsMapper goodsMapper ;

    public GoodsServiceImpl(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    @Override
    public List<Goods> findAllGoods() {
        return goodsMapper.findAllGoods();
    }

    @Override
    public GoodsDetails findGoodsByGId(Integer gid) {
        return goodsMapper.findGoodsDetailsByGId(gid);
    }

    @Override
    public boolean addGoods(Goods goods) {
        int len = goodsMapper.insertGoods(goods) ;
        if (len > 0) return true ;
        return false;
    }

    @Override
    public boolean removeGoodsByGId(Integer gid) {
        Goods goods = goodsMapper.findGoodsByGId(gid) ;
        goods.setStatus(0);
        int len = goodsMapper.updateGoods(goods) ;
        if (len > 0) return true ;
        return false;
    }

    @Override
    public long totalCount(Integer tid) {
        return 0;
    }

    @Override
    public List<Goods> findGoodsByTId(Integer tid) {
        GoodsType type = goodsMapper.findGoodsTypeByTId(tid) ;
        List<Goods> goodsList = goodsMapper.findGoodsByTIdAndLevel(tid,type.getLevel()) ;
        return goodsList ;
    }

    @Override
    public List<Goods> searchGoodsByName(String name) {
        name = "\'%" + name + "\'%" ;
        return goodsMapper.findGoodsLikeName(name);
    }

    @Override
    public List<GoodsType> findAllGoodsType() {
        List<GoodsType> list = goodsMapper.findAllGoodsType() ;
        return list;
    }

    @Override
    public GoodsType findGoodsTypeByTId(Integer tid) {
        GoodsType goodsType = goodsMapper.findGoodsTypeByTId(tid) ;
        return goodsType;
    }

    @Override
    public boolean addGoodsType(GoodsType goodsType) {
        int len = goodsMapper.insertGoodsType(goodsType) ;
        if (len > 0) return true ;
        return false;
    }

    @Override
    public boolean removeGoodsTypeByTId(Integer tid) {
        return false;
    }
}
