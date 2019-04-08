package com.shasly.goods.service;

import com.shasly.common.bean.Goods;
import com.shasly.common.bean.GoodsType;
import com.shasly.goods.vo.GoodsDetails;

import java.util.List;

public interface GoodsService {

	List<Goods> findAllGoods(Integer pageSize,Integer pageNum) ;

	GoodsDetails findGoodsByGId(Integer gid) ;

	boolean addGoods(String token,Goods goods) ;

	boolean removeGoodsByGId(Integer gid) ;

	long totalCount(Integer tid) ;

	List<Goods> findGoodsByTId(Integer tid,Integer pageSize,Integer pageNum) ;
	
	List<Goods> searchGoodsByName(String name,Integer pageSize,Integer pageNum);

	List<GoodsType> findAllGoodsType() ;

	GoodsType findGoodsTypeByTId(Integer tid);

	boolean addGoodsType(String token ,GoodsType goodsType) ;

	boolean removeGoodsTypeByTId(Integer tid) ;
	
}
