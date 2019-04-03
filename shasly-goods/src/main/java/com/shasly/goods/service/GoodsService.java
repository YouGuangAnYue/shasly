package com.shasly.goods.service;

import com.shasly.common.bean.Goods;
import com.shasly.common.bean.GoodsType;

import java.util.List;

public interface GoodsService {

	List<Goods> findAllGoods() ;
	Goods findGoodsByGId(Integer gid) ;
	boolean addGoods(Goods goods) ;
	boolean removeGoodsByGId(Integer gid) ;
	long totalCount(Integer tid) ;
	
	List<Goods> paging(Integer typeid, Integer pageIndex, Integer pageSize) ;
	List<Goods> searchGoodsByName(String name);

	List<GoodsType> findAllGoodsType() ;
	GoodsType findGoodsTypeByTId(Integer tid);
	boolean addGoodsType(GoodsType goodsType) ;
	boolean removeGoodsTypeByTId(Integer tid) ;
	
}
