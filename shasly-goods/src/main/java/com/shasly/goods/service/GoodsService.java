package com.shasly.goods.service;

import com.shasly.common.bean.Goods;
import com.shasly.common.bean.GoodsType;
import com.shasly.goods.vo.GoodsDetails;

import java.util.List;

public interface GoodsService {

	List<Goods> findAllGoods() ;

	GoodsDetails findGoodsByGId(Integer gid) ;

	boolean addGoods(Goods goods) ;

	boolean removeGoodsByGId(Integer gid) ;

	long totalCount(Integer tid) ;

	List<Goods> findGoodsByTId(Integer tid) ;
	
	List<Goods> searchGoodsByName(String name);

	List<GoodsType> findAllGoodsType() ;

	GoodsType findGoodsTypeByTId(Integer tid);

	boolean addGoodsType(GoodsType goodsType) ;

	boolean removeGoodsTypeByTId(Integer tid) ;
	
}
