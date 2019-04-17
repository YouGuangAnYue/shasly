import com.shasly.sms.common.SmsCodeUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class TestMain {
    @Test
    public void test1(){
        String nonce_str = SmsCodeUtils.getNonce_str(6);
        System.out.println(nonce_str);
        Jedis jedis = new Jedis("39.105.211.35",6379) ;
        jedis.setex("abc",10,"hahaha") ;
        System.out.println(jedis.get("111"));
        jedis.close();
    }

    @Test
    public void errTest(){
        try{
            err();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    public void err(){
        throw new RuntimeException("我报错了") ;
    }
}
