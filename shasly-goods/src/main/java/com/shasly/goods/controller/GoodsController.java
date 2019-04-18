package com.shasly.goods.controller;

import com.shasly.common.bean.Goods;
import com.shasly.common.bean.GoodsType;
import com.shasly.common.bean.ResultBean;
import com.shasly.common.exception.GoodsException;
import com.shasly.common.jedis.JedisClientPool;
import com.shasly.common.utils.PageBeanUtils;
import com.shasly.goods.service.GoodsService;
import com.shasly.goods.vo.GoodsDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
     *
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
        if (pageSize == null) pageSize = PageBeanUtils.pageSize;
        if (pageNum == null) pageNum = 1;
        List<Goods> goodsList = null;
        try {
            goodsList = goodsService.findGoodsByTId(tid, pageSize, pageNum);
            return PageBeanUtils.goodsResultBean(goodsList, pageNum, pageSize);
        } catch (GoodsException e1) {
            return new ResultBean(false, e1.getMessage(), null);
        } catch (Exception e2) {
            return new ResultBean(false, "服务器繁忙", e2.getMessage());
        }

    }

    /**
     * 分页查看所有商品
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping(value = "/getallgoodslist/{pageSize}/{pageNum}")
    @CrossOrigin
    public ResultBean getAllGoodsList(@PathVariable(value = "pageSize") Integer pageSize,
                                      @PathVariable(value = "pageNum") Integer pageNum) {
        if (pageSize == null) pageSize = PageBeanUtils.pageSize;
        if (pageNum == null) pageNum = 1;
        try {
            List<Goods> allGoodsList = goodsService.findAllGoods(pageSize, pageNum);
            return PageBeanUtils.goodsResultBean(allGoodsList, pageNum, pageSize);
        } catch (Exception e) {
            return new ResultBean(false,"服务器忙",e.getMessage()) ;
        }
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
     *
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
        if (pageSize == null) pageSize = PageBeanUtils.pageSize;
        if (pageNum == null) pageNum = 1;
        String _name = null ;
        try {
            _name = URLDecoder.decode(name,"UTF-8") ;
        } catch (UnsupportedEncodingException e) {
            return new ResultBean(false,"文字编码错误，请修改为UTF-8",null) ;
        }
        List<Goods> goodsList = goodsService.searchGoodsByName(_name, pageSize, pageNum);
        return PageBeanUtils.goodsResultBean(goodsList, pageNum, pageSize);
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

    /**
     * 添加商品类型
     * @param goodsType
     * @return
     */
    @PostMapping(value = "/addgoodstype")
    @CrossOrigin
    public ResultBean addGoodsType(@CookieValue(value = "token", required = false) String token,
                                   @RequestBody GoodsType goodsType) {
        boolean b = goodsService.addGoodsType(token, goodsType);
        if (b)
            return new ResultBean(true, "添加成功", null);
        else
            return new ResultBean(false, "添加失败", null);
    }

    @PostMapping(value = "/addgoods")
    @CrossOrigin
    public ResultBean addGoods(@CookieValue(value = "token", required = false) String token, @RequestBody Goods goods) {
        boolean b = goodsService.addGoods(token, goods);
        if (b)
            return new ResultBean(true, "添加商品成功", null);
        else
            return new ResultBean(false, "添加商品失败", null);
    }
}
