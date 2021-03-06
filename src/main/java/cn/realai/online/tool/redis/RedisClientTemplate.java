package cn.realai.online.tool.redis;

import java.util.concurrent.TimeUnit;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.realai.online.common.Constant;

@Service
public class RedisClientTemplate {

	private static Logger logger = LoggerFactory.getLogger(RedisClientTemplate.class);
	
	@Autowired
    private RedissonClient redissonClient;  //RedissonClient已经由配置类生成，这里自动装配即可
	
	@Autowired
    private JedisClusterClient jedisClusterClient;

	/*
	 * 设置key-value
	 * @param key
	 * @param value
	 * @param expiredTime 过期时间 单位秒 如果永久不过期,此值传-1
	 * @return
	 */
    public boolean set(String key, Object value, int expiredTime) {
        try {
	        String str = jedisClusterClient.getJedisCluster().set(key, String.valueOf(value));
	        if (expiredTime != -1) { 
	        	jedisClusterClient.getJedisCluster().expire(key, expiredTime);
	        }
	        if ("OK".equals(str)) {
	        	return true;
	        }
        } catch (Exception ex){
        	logger.error("setToRedis:{Key:"+key+",value"+value+"}",ex);
        }
        return false;
    }

    /*
	 * 设置key-value 默认过期时间为一个星期
	 * @param key
	 * @param value
	 * @return
	 */
    public boolean set(String key, Object value) {
        return set(key, value, Constant.REDIS_EXPIRED_TIME);
    }
    
    /*
     * 通过key获取value
     */
    public String get(String key){
        String str = null;
        try {
             str = jedisClusterClient.getJedisCluster().get(key);
        }catch (Exception ex){
        	logger.error("getRedis:{Key:"+key+"}",ex);
        }
        return str;
    }
    
    /*
     * 通过key获取value,并转换成指定类型
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T get(String key, Class clazz){
    	T obj = null;
        try {
             String str = jedisClusterClient.getJedisCluster().get(key);
             obj = (T) JSON.parseObject(str, clazz.getClass());
        }catch (Exception ex){
        	logger.error("getRedis:{Key:"+key+"}",ex);
        }
        return obj;
    }
    
    /*
	 * 删除key
	 * @param key
	 * @return
	 */
    public boolean delete(String key){
        try {
	        jedisClusterClient.getJedisCluster().del(key);
	        return true;
        } catch (Exception ex){
        	logger.error("setToRedis:{Key:" + key,ex);
        }
        return false;
    }
    
    //lock(), 拿不到lock就不罢休，不然线程就一直block
    public RLock lock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock();
        return lock;
    }
 
    //leaseTime为加锁时间，单位为秒
    public RLock lock(String lockKey, long leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(leaseTime, TimeUnit.SECONDS);
        return lock;
    }
    
    //timeout为加锁时间，时间单位由unit确定
    public RLock lock(String lockKey, TimeUnit unit ,long timeout) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(timeout, unit);
        return lock;
    }
    //tryLock()，马上返回，拿到lock就返回true，不然返回false。
    //带时间限制的tryLock()，拿不到lock，就等一段时间，超时返回false.
    public boolean tryLock(String lockKey, TimeUnit unit, long waitTime, long leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }
    
    public void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
    }
    
    public void unlock(RLock lock) {
        lock.unlock();
    }

}
