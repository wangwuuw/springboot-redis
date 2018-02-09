package net.cowfish.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @classDesc： 功能描述：（）
 * @author：王武
 * @createTime 2018/2/9
 * @verson: v1.0
 * @copyright: 上海江豚教育科技有限公司
 * @qq:834667820
 */
@Service
public class RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    public void setString(String key, String value){
        setObject(key,value);
    }
    public void setList(String key, List value){
        setObject(key,value);
    }
    public void setObject(String key, Object value) {
        this.setObject(key, value, null);
    }

    public void setObject(String key, Object value, Long time) {
        if (StringUtils.isEmpty(key) || value == null) {
            return;
        }
        if (value instanceof String) {
            // 存放string类型
            String stringValue = (String) value;
            if (time == null) {
                stringRedisTemplate.opsForValue().set(key, stringValue);
            } else {
                stringRedisTemplate.opsForValue().set(key, stringValue, time, TimeUnit.SECONDS);
            }

            return;
        }
        if (value instanceof List) {
            // 存放list類型
            List<String> listValue = (List<String>) value;
            for (String string : listValue) {
                stringRedisTemplate.opsForList().leftPush(key, string);
            }

        }

    }

    public void delKey(String key) {
        stringRedisTemplate.delete(key);
    }

    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);

    }
    public void getList(String key) {

        ListOperations<String, String> stringStringListOperations = stringRedisTemplate.opsForList();
        return ;

    }
}
