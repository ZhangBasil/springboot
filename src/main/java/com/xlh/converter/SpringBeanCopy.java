package com.xlh.converter;

import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangbin
 * @Type SpringBeanCopy
 * @Desc
 * @date 2017-12-19
 * @Version V1.0
 */
public class SpringBeanCopy {
    public static void main(String[] args) {
        Map<String, Object> map = new ConcurrentHashMap<>();
        map.put("abc", 123);
        map.put("abc", 123);
        map.put(null, "adsf");
        System.out.println("map = " + map);


        UserDO userDO = getUserDO();
        List<UserDO> userDOList = new ArrayList<>();
        userDOList.add(userDO);

        UserBO userBO = new UserBO();
        List<UserBO> userBOList = new ArrayList<>();



        BeanUtils.copyProperties(userDO, userBO);

        System.out.println("userBO = " + userBO);

    }



    private static UserDO getUserDO() {
        UserDO userDO = new UserDO();
        userDO.setId(100L);
        userDO.setName("张三");
        userDO.setCreateTime(new Date());
        userDO.setUpdateTime(LocalDateTime.now());
        userDO.setMan(false);
        userDO.setSelf(userDO);
        return userDO;
    }
}
