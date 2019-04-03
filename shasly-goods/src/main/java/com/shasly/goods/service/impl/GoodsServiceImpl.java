package com.shasly.goods.service.impl;

import com.shasly.common.bean.Goods;
import com.shasly.common.bean.GoodsType;
import com.shasly.goods.service.GoodsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Override
    public List<Goods> findAllGoods() {
        return null;
    }

    @Override
    public Goods findGoodsByGId(Integer gid) {
        return null;
    }

    @Override
    public boolean addGoods(Goods goods) {
        return false;
    }

    @Override
    public boolean removeGoodsByGId(Integer gid) {
        return false;
    }

    @Override
    public long totalCount(Integer tid) {
        return 0;
    }

    @Override
    public List<Goods> paging(Integer typeid, Integer pageIndex, Integer pageSize) {
        return null;
    }

    @Override
    public List<Goods> searchGoodsByName(String name) {
        return null;
    }

    @Override
    public List<GoodsType> findAllGoodsType() {
        return null;
    }

    @Override
    public GoodsType findGoodsTypeByTId(Integer tid) {
        return null;
    }

    @Override
    public boolean addGoodsType(GoodsType goodsType) {
        return false;
    }

    @Override
    public boolean removeGoodsTypeByTId(Integer tid) {
        return false;
    }
}
