package com.shasly.sms.controller;/*
 *________________********_______________________
 *______________************_____________________
 *______________*************____________________
 *_____________**__***********___________________
 *____________***__******_*****__________________
 *____________***_*******___****_________________
 *___________***__**********_****________________
 *__________****__***********_****_______________
 *________*****___***********__*****_____________
 *_______******___***_********___*****___________
 *_______*****___***___********___******_________
 *______******___***__***********___******_______
 *_____******___****_**************__******______
 *____*******__***** ***************_ ******_____
 *____*******__***** ****************_ ******____
 *___*******__******_*****************_*******___
 *___*******__******_******_*********___******___
 *___*******____**__******___******_____******___
 *___*******________******____*****_____*****____
 *____******________*****_____*****_____****_____
 *_____*****________****______*****_____***______
 *______*****______;***________***______*________
 *________**_______****________****______________
 *
 *          初闻天籁之音,未使心之将来——初音未来
 *
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shasly.common.bean.ResultBean;
import com.shasly.common.jedis.JedisClientPool;
import com.shasly.sms.httpApiDemo.IndustrySMS;
import com.shasly.sms.pojo.ShortReslut;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.io.IOException;

@RestController
@RequestMapping(value = "/sms")
public class SmsController {

    private final JedisClientPool jedisClientPool ;

    public SmsController(JedisClientPool jedisClientPool) {
        this.jedisClientPool = jedisClientPool;
    }

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @CrossOrigin
    @GetMapping("/sendcode/{phone}")
    public ResultBean sendCode(@PathVariable(value = "phone") String phone) {

        ObjectMapper objectMapper = new ObjectMapper();
        Jedis jedis = jedisClientPool.getJedisPool().getResource() ;
        String result = IndustrySMS.execute(phone,jedis);

        try {

            ShortReslut shortReslut = objectMapper.readValue(result, ShortReslut.class);
            System.out.println(shortReslut);

            if (shortReslut.getRespCode().equalsIgnoreCase("00000")) {
                return new ResultBean(true,"发送验证码成功",null) ;
            } else if (shortReslut.getRespCode().equalsIgnoreCase("00006")) {
                return new ResultBean(false,"发送失败",null) ;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResultBean(false,"发送失败",null);
    }
}
