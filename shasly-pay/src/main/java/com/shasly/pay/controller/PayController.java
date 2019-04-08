package com.shasly.pay.controller;

import com.shasly.common.bean.ResultBean;
import com.shasly.pay.service.PayService;
import com.shasly.pay.utils.PaymentUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/pay")
public class PayController {

    private final PayService payService ;

    public PayController(PayService payService) {
        this.payService = payService;
    }

    @PostMapping(value = "/yibaopay")
    public ResultBean yiBaoPay(String oid, String pd_FrpId) {
        // 接受数据

        BigDecimal money = payService.findOrderByOId(oid) ;
        // 按照第三方需要的数据

        String p0_Cmd = "Buy";
        String p1_MerId = "10001126856";
        String p2_Order = oid;
        String p3_Amt = "0.01"; //money
        String p4_Cur = "CNY";
        String p5_Pid = "shasly";
        String p6_Pcat = "shasly";
        String p7_Pdesc = "shasly";
        // 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
        // 第三方支付可以访问网址
        String p8_Url = "http://localhost:8080/shasly/order/callbackorder/" + oid;
        String p9_SAF = "";
        String pa_MP = "";
        String pr_NeedResponse = "1";
        // 加密hmac 需要密钥
        String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
        String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
                p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);

        // 请求
        StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
        sb.append("p0_Cmd=").append(p0_Cmd).append("&");
        sb.append("p1_MerId=").append(p1_MerId).append("&");
        sb.append("p2_Order=").append(p2_Order).append("&");
        sb.append("p3_Amt=").append(p3_Amt).append("&");
        sb.append("p4_Cur=").append(p4_Cur).append("&");
        sb.append("p5_Pid=").append(p5_Pid).append("&");
        sb.append("p6_Pcat=").append(p6_Pcat).append("&");
        sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
        sb.append("p8_Url=").append(p8_Url).append("&");
        sb.append("p9_SAF=").append(p9_SAF).append("&");
        sb.append("pa_MP=").append(pa_MP).append("&");
        sb.append("pd_FrpId=").append(pd_FrpId).append("&");
        sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
        sb.append("hmac=").append(hmac);

        return new ResultBean(true,"支付成功",sb);
    }
}
