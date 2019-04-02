import org.junit.Test;
import redis.clients.jedis.Jedis;

public class TestMain {
    @Test
    public void jedisTest(){
        Jedis jedis = new Jedis("39.105.211.35",6379) ;
        //jedis.auth("lvyifan");
        String ping = jedis.ping();
        System.out.println(ping);
        jedis.set("k1","v1");
        System.out.println(jedis.get("k1"));
    }
}
