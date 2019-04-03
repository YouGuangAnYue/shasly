package com.shasly.goods.controller;

import com.shasly.common.bean.Goods;
import com.shasly.common.bean.GoodsType;
import com.shasly.common.bean.ResultBean;
import com.shasly.common.jedis.JedisClientPool;
import com.shasly.common.utils.PageBeanUtils;
import com.shasly.goods.service.GoodsService;
import com.shasly.goods.vo.GoodsDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    private final GoodsService goodsService;
    private final JedisClientPool jedisClientPool;

    public GoodsController(GoodsService goodsService, JedisClientPool jedisClientPool) {
        this.goodsService = goodsService;
        this.jedisClientPool = jedisClientPool;
    }

    /**
     * 根据商品类型，分页显示商品
     * @param tid
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping(value = "/getgoodslistbytid/{tid}/{pageSize}/{pageNum}")
    @CrossOrigin
    public ResultBean getGoodsListByTId(@PathVariable(value = "tid") Integer tid,
                                        @PathVariable(value = "pageSize") Integer pageSize,
                                        @PathVariable(value = "pageNum") Integer pageNum) {

        if (tid == null) return new ResultBean(false, "商品类型不能为空", null);
        List<Goods> goodsList = goodsService.findGoodsByTId(tid);
        ResultBean resultBean = PageBeanUtils.goodsResultBean(goodsList, pageNum, pageSize);
        return resultBean;
    }

    @GetMapping(value = "/getallgoodslist/{pageSize}/{pageNum}")
    @CrossOrigin
    public ResultBean getAllGoodsList(@PathVariable(value = "pageSize") Integer pageSize,
                                      @PathVariable(value = "pageNum") Integer pageNum){
        List<Goods> allGoodsList = goodsService.findAllGoods();
        return PageBeanUtils.goodsResultBean(allGoodsList,pageNum,pageSize) ;
    }

    /**
     * 显示特定id的商品
     * @param gid
     * @return
     */
    @GetMapping(value = "/getgoodsbygid/{gid}")
    @CrossOrigin
    public ResultBean getGoodsByGId(@PathVariable(value = "gid") Integer gid) {

        GoodsDetails goodsDetails = goodsService.findGoodsByGId(gid);

        if (goodsDetails == null) return new ResultBean(false, "查无该商品", null);

        return new ResultBean(true, "查找成功", goodsDetails);

    }

    /**
     * 商品查询
     * @param name
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping(value = "/goodssearch/{name}/{pageSize}/{pageNum}")
    @CrossOrigin
    public ResultBean goodsSearch(@PathVariable(value = "name") String name,
                                  @PathVariable(value = "pageSize") Integer pageSize,
                                  @PathVariable(value = "pageNum") Integer pageNum) {

        List<Goods> goodsList = goodsService.searchGoodsByName(name);

        return PageBeanUtils.goodsResultBean(goodsList,pageNum,pageSize);
    }

    /**
     * 获取商品分类列表
     * @return
     */
    @GetMapping(value = "/getgoodstypelist")
    @CrossOrigin
    public ResultBean getGoodsTypeList() {
        List<GoodsType> types = goodsService.findAllGoodsType();
        if (types != null && types.size() > 0)
            return new ResultBean(true, "获取商品分类列表成功", types);
        return new ResultBean(false, "获取商品分类列表失败", null);
    }
}
