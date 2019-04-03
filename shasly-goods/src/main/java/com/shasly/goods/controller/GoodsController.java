package com.shasly.goods.controller;

import com.shasly.common.bean.Goods;
import com.shasly.common.bean.GoodsType;
import com.shasly.common.bean.ResultBean;
import com.shasly.goods.service.GoodsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/goods")
public class GoodsController {

    private final GoodsService goodsService ;

    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    /**
     * 根据商品类型，分页显示商品
     */
    @GetMapping(value = "/getgoodslistbytid/{tid}/{pageSize}/{pageNum}")
    @CrossOrigin
    public ResultBean getGoodsListByTId(@PathVariable(value = "tid") Integer tid,
                                        @PathVariable(value = "pageSize") Integer pageSize,
                                        @PathVariable(value = "pageNum") Integer pageNum) {

        if (tid == null) return new ResultBean(false,"商品类型不能为空",null) ;

        return null ;
    }
    /**
     * 显示特定id的商品
     */
    @GetMapping(value = "/getgoodsbygid/{gid}")
    @CrossOrigin
    public ResultBean getGoodsByGId(@PathVariable(value = "gid") Integer gid) {

        Goods goods = goodsService.findGoodsByGId(gid);

        if (goods == null) return new ResultBean(false,"查无该商品",null) ;

        return new ResultBean(true,"查找成功",goods) ;

    }
    /**
     * 商品查询
     */
    @GetMapping(value = "/goodssearch/{name}")
    @CrossOrigin
    public ResultBean goodsSearch(@PathVariable(value = "name") String name) {

        List<Goods> goods = goodsService.searchGoodsByName(name);

        return null ;
    }

    /**
     * 获取商品分类列表
     */
    @GetMapping(value = "/getgoodstypelist")
    @CrossOrigin
    public ResultBean getGoodsTypeList() {
        List<GoodsType> types = goodsService.findAllGoodsType();
        if (types != null && types.size() > 0)
            return new ResultBean(true,"获取商品分类列表成功",types) ;
        return new ResultBean(false,"获取商品分类列表失败",null) ;
    }
}
