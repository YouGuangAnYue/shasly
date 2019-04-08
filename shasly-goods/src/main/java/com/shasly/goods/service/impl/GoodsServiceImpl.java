package com.shasly.goods.service.impl;

import com.github.pagehelper.PageHelper;
import com.shasly.common.bean.Goods;
import com.shasly.common.bean.GoodsType;
import com.shasly.common.bean.User;
import com.shasly.common.jedis.JedisClientPool;
import com.shasly.common.utils.PageBeanUtils;
import com.shasly.goods.mapper.GoodsMapper;
import com.shasly.goods.service.GoodsService;
import com.shasly.goods.vo.GoodsDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    private final GoodsMapper goodsMapper ;
    private final JedisClientPool jedisClientPool ;

    public GoodsServiceImpl(GoodsMapper goodsMapper, JedisClientPool jedisClientPool) {
        this.goodsMapper = goodsMapper;
        this.jedisClientPool = jedisClientPool;
    }

    @Override
    public List<Goods> findAllGoods(Integer pageSize,Integer pageNum) {

        return goodsMapper.findAllGoods();
    }

    @Override
    public GoodsDetails findGoodsByGId(Integer gid) {

        GoodsDetails goodsDetails = goodsMapper.findGoodsDetailsByGId(gid);
        Goods goods = goodsMapper.findGoodsByGId(gid);
        if (goods == null) return null ;
        GoodsType type_1 = goodsMapper.findGoodsTypeByTId(goods.getTid_1()) ;
        GoodsType type_2 = goodsMapper.findGoodsTypeByTId(goods.getTid_2()) ;
        GoodsType type_3 = goodsMapper.findGoodsTypeByTId(goods.getTid_3()) ;
        goodsDetails.setType_1(type_1);
        goodsDetails.setType_2(type_2);
        goodsDetails.setType_3(type_3);

        return goodsDetails ;
    }

    /**
     * 添加商品
     * @param goods
     * @return
     */
    @Override
    public boolean addGoods(String token,Goods goods) {
        if (getUserRole(token) == 0){
            int len = goodsMapper.insertGoods(goods) ;
            if (len > 0) return true ;
        }
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
    public List<Goods> findGoodsByTId(Integer tid,Integer pageSize,Integer pageNum) {
        GoodsType type = goodsMapper.findGoodsTypeByTId(tid) ;
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> goodsList = goodsMapper.findGoodsByTIdAndLevel(tid,type.getLevel()) ;
        return goodsList ;
    }

    @Override
    public List<Goods> searchGoodsByName(String name,Integer pageSize,Integer pageNum) {
        name = "%" + name + "%" ;
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> goodsList = goodsMapper.findGoodsLikeName(name);
        return goodsList ;
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

    /**
     * 添加商品类型
     * @param token
     * @param goodsType
     * @return
     */
    @Override
    public boolean addGoodsType(String token, GoodsType goodsType) {
        Integer role = getUserRole(token) ;
        if (role.intValue() == 0) {
            int len = goodsMapper.insertGoodsType(goodsType);
            if (len > 0) return true;
            return false;
        }
        return false ;
    }

    @Override
    public boolean removeGoodsTypeByTId(Integer tid) {
        return false;
    }

    /**
     * 获取用户等级
     * @param token
     * @return
     */
    public Integer getUserRole(String token){
        String uid = jedisClientPool.get(token) ;
        User user = goodsMapper.findUserByUId(Integer.parseInt(uid)) ;
        return user.getRole() ;
    }
}
