package com.shasly.common.utils;

import com.github.pagehelper.PageInfo;
import com.shasly.common.bean.Goods;
import com.shasly.common.bean.PageBean;
import com.shasly.common.bean.ResultBean;

import java.util.List;

public class PageBeanUtils {

    public static int pageSize = 8;

    //分页返回商品信息
    public static ResultBean goodsResultBean(List<Goods> goodsList, Integer pageNum, Integer pageSize) {

        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        pageSize = pageSize == null ? PageBeanUtils.pageSize : pageSize;
        PageBean pageBean = new PageBean(pageInfo.getTotal(), pageNum, pageSize);
        pageBean.setData(goodsList);
        ResultBean resultBean = new ResultBean();
        if (goodsList == null || goodsList.size() == 0) {
            resultBean.setMsg("没有相应的商品");
        } else {
            resultBean.setMsg("查询物品成功");
        }
        resultBean.setData(pageBean);

        return resultBean;
    }

    //返回结果信息
    public static ResultBean getResultBean(List list) {

        ResultBean resultBean = new ResultBean();
        if (list == null || list.size() == 0) {
            resultBean.setMsg("没有查询结果");
        } else {
            resultBean.setMsg("查询成功");
        }
        resultBean.setData(list);

        return resultBean;
    }

    //通用返回分页
    public static ResultBean baseResultBean(List list, Integer pageNum, Integer pageSize) {

        PageInfo pageInfo = new PageInfo(list);
        pageSize = pageSize == null ? PageBeanUtils.pageSize : pageSize;
        PageBean pageBean = new PageBean(pageInfo.getTotal(), pageNum, pageSize);
        pageBean.setData(list);
        ResultBean resultBean = new ResultBean();
        if (list == null || list.size() == 0) {
            resultBean.setMsg("返回结果失败");
        } else {
            resultBean.setMsg("返回成功");
        }
        resultBean.setData(pageBean);

        return resultBean;
    }
}
