package com.tlkg.order.commons;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

/**
 * redis CacheManager
 *
 * @author lishanwei.
 *
 */
@SuppressWarnings({"cast"})
@Service
public class RedisCacheManager
{
    private static Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);
    
    private static JedisPool jedisPool;
    
    private Jedis getJedis()
    {
        Jedis jedis = null;
        if(jedisPool == null){
            jedisPool =  (JedisPool)AppUtils.getBean("jedisPool");
        }
        try
        {
            jedis = jedisPool.getResource();
        }
        catch (JedisException e)
        {
            logger.warn("getResource.", e);
            returnResource(jedis);
            throw e;
        }
        return jedis;
    }
    
    /**
     * 加锁.
     *
     * @param key
     * @param expireTime 超时时间单位秒.
     * @return 锁定的value,返回null值加锁失败,供释放锁时使用.
     */
    public String tryLock(String key, int expireTime)
    {
        Jedis jedis = this.getJedis();
        try
        {
            String value = Long.toString(System.currentTimeMillis() + (expireTime * 1000));
            String result = jedis.set(key, value, "NX", "EX", expireTime);
            if (result == null)
            {
                return null;
            }
            return value;
        }
        finally
        {
            jedis.close();
        }
    }
    
    /**
     * 指定缓存的过期时间
     *
     * @param cacheName
     * @param key
     * @param value
     * @param seconds
     */
    public void setExpires(String cacheName, String key, String value, int seconds)
    {
        // //long time = new Date().getTime();
        Jedis jedis = this.getJedis();
        try
        {
            String expiresKey = cacheName.concat("~").concat(key);
            jedis.setex(expiresKey, seconds, value);
        }
        catch (Exception e)
        {
            RedisCacheManager.logger.error("CacheManager setExpires method error:\n" + e.getMessage());
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }
    
    /**
     *
     * @param cacheName
     * @param key
     * @param value
     * @param seconds
     */
    public void setExpires(String cacheName, String key, Object value, int seconds)
    {
        Jedis jedis = this.getJedis();
        try
        {
            String expiresKey = cacheName.concat("~").concat(key);
            jedis.setex(expiresKey.getBytes(), seconds, serialize(value));
        }
        catch (Exception e)
        {
            RedisCacheManager.logger.error("CacheManager setExpires method error:\n" + e.getMessage());
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }
    
    /**
    *
    * @param cacheName
    * @param key
    * @param value
    * @param seconds
    */
   public void setExpiresAtDate(String cacheName, String key, Object value, Date date)
   {
       Jedis jedis = this.getJedis();
       try
       {
           String expiresKey = cacheName.concat("~").concat(key);
//           int ss = date.getTime() - new Date().getTime();
//           jedis.setex(expiresKey.getBytes(), ss, serialize(value));
           jedis.set(expiresKey.getBytes(), serialize(value));
           jedis.expireAt(expiresKey.getBytes(), date.getTime());
       }
       catch (Exception e)
       {
           RedisCacheManager.logger.error("CacheManager setExpires method error:\n" + e.getMessage());
       }
       finally
       {
           if (jedis != null)
           {
               jedis.close();
           }
       }
   }
    
    /**
     *
     * @param cacheName
     * @param key
     * @return
     */
    public Object getExpiresObject(String cacheName, String key)
    {
        Object result = null;
        Jedis jedis = this.getJedis();
        try
        {
            String expiresKey = cacheName.concat("~").concat(key);
            byte[] objBytes = jedis.get(expiresKey.getBytes());
            result = unserialize(objBytes);
        }
        catch (Exception e)
        {
            result = null;
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
            
        }
        
        return result;
    }
    
    /**
     *
     * @param cacheName
     * @param key
     * @return
     */
    public Object getExpires(String cacheName, String key)
    {
        String result = "";
        Jedis jedis = this.getJedis();
        try
        {
            String expiresKey = cacheName.concat("~").concat(key);
            result = jedis.get(expiresKey);
        }
        catch (Exception e)
        {
            result = "";
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
            
        }
        return result;
    }
    
    /**
     * 删除指定名称缓存下指定key的值
     *
     * @param cacheName 参数：缓存名称
     * @param key 参数：key
     */
    public void remove(String cacheName, String key)
    {
        Jedis jedis = this.getJedis();
        try
        {
            key = cacheName.concat("~").concat(key);
            Set<String> set = jedis.keys(key);
            String[] kk = new String[set.size()];
            jedis.del(set.toArray(kk));
            
        }
        catch (Exception e)
        {
            RedisCacheManager.logger.error("CacheManager remove method error:\n" + e.getMessage());
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }
    
    public void del(String key)
    {
        Jedis jedis = this.getJedis();
        try
        {
            Set<String> set = jedis.keys(key);
            String[] kk = new String[set.size()];
            jedis.del(set.toArray(kk));
        }
        catch (Exception e)
        {
            RedisCacheManager.logger.error("CacheManager del method error:\n" + e.getMessage());
        }
        finally
        {
            jedis.close();
        }
    }
    
    // lpush
    public void lpush(String queneName, String str)
    {
        Jedis jedis = getJedis();
        try
        {
            jedis.lpush(queneName, str);
        }
        catch (Exception e)
        {
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }
    
    // rpop
    public String rpop(String queneName)
    {
        Jedis jedis = getJedis();
        String value = null;
        try
        {
            value = jedis.rpop(queneName);
        }
        catch (Exception e)
        {
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
        return value;
    }
    
    public boolean isExistKey(String key)
    {
        Jedis jedis = getJedis();
        try
        {
            if (jedis.exists(key))
                return true;
        }
        catch (Exception e)
        {
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
        return false;
    }
    
    /**
     * redis存放object
     * 
     * @param key
     * @param obj
     * @return
     */
    public Object setObject(String key, Object obj)
    {
        Jedis jedis = getJedis();
        try
        {
            jedis.set(key.getBytes(), serialize(obj));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return obj;
        }
        finally
        {
            returnResource(jedis);
        }
        return obj;
    }
    
    /**
     * 从redis获取Object
     * 
     * @param key
     * @return
     */
    public Object getObject(String key)
    {
        Jedis jedis = getJedis();
        Object obj = null;
        try
        {
            byte[] objByte = jedis.get(key.getBytes());
            if(objByte != null){
                obj = unserialize(objByte);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return obj;
        }
        finally
        {
            returnResource(jedis);
        }
        
        return obj;
    }
    
    /**
     * 释放jedis资源
     * 
     * @param jedis
     */
    public static void returnResource(final Jedis jedis)
    {
        try
        {
            if (jedis != null)
            {
                jedisPool.returnResource(jedis);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            jedisPool.returnBrokenResource(jedis);
        }
    }
    
    public static byte[] serialize(Object object)
    {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try
        {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        }
        catch (Exception e)
        {
            //
            System.out.println();
        }
        return null;
    }
    
    public static Object unserialize(byte[] bytes)
        throws Exception
    {
        ByteArrayInputStream bais = null;
        bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
    }
    
    /**
     * 获取一个map的所有数据
     * @param key
     * @param field
     * @return
     */
    public  Map<String, String> hGetAll(String key) {
        Jedis jedis = getJedis();
        try {
            return jedis.hgetAll(key);
        } catch(Exception ex) {
            ex.printStackTrace();
            return new HashMap<String, String>();
        } finally {
            returnResource(jedis);
        }
    }
    
    /**
     * 获取缓存
     * @return
     */
    public Set<String> getKeys(String prefix) {
        Jedis jedis = getJedis();
        try {
            return jedis.keys(prefix+"*");
        } catch(Exception ex) {
            ex.printStackTrace();
            return new HashSet<String>();
        } finally {
            returnResource(jedis);
        }
    }
    
    /**
     * 在redis里插入list的数据类型的数据 (取出当前list所有数据,然后在头部插入新增数据)
     * @param key String
     * @param value Object
     * @return
     */
    public void setExpire(String key, int time){
        Jedis jedis=getJedis();
        try{
            jedis.expire(key,time);
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            returnResource(jedis);
        }
    }
    
    /**
     * 设置缓存
     * @return
     */
    public String set(String key, String value)
    {
        Jedis jedis = getJedis();
        try
        {
            jedis.set(key, value);
            return value;
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return value;
        }
        finally
        {
            returnResource(jedis);
        }
    }
}
