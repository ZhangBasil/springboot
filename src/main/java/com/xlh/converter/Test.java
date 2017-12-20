package com.xlh.converter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.OptionalDouble;
import java.util.function.ToIntFunction;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author zhangbin
 * @Type Test
 * @Desc
 * @date 2017-12-18
 * @Version V1.0
 */
public class Test {
//    private static final int TIMES = 10_000_000;
    private static final int TIMES = 1;



    public static void main(String[] args) {

        long[] reflectAsm = new long[10];
        long[] getSet = new long[10];
        long[] spring = new long[10];

        for (int i = 0; i < 1; i++) {
            reflectAsm[i] = reflectAsmTest();
            getSet[i] = getSetTest();
//            spring[i] = springTest();
            System.out.println();
        }
        double reflectAsmAvg  = LongStream.of(reflectAsm).average().orElseThrow(RuntimeException::new);
        double getSetAvg  = LongStream.of(getSet).average().orElseThrow(RuntimeException::new);
        double springAvg  = LongStream.of(spring).average().orElseThrow(RuntimeException::new);

        System.out.println(Arrays.toString(reflectAsm));
        System.out.println(Arrays.toString(getSet));
        System.out.println(Arrays.toString(spring));
        System.out.println(" -------------------- ");
        System.out.println("reflectAsmAvg = " + reflectAsmAvg);
        System.out.println("springAvg = " + springAvg);
        System.out.println("getSetAvg = " + getSetAvg);

    }


    /**
     * spring 属性copy
     *
     */
    private static long springTest() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < TIMES; i++) {
            UserDO userDO = getUserDO();
            UserBO userBO = new UserBO();
            org.springframework.beans.BeanUtils.copyProperties(userDO, userBO);
        }
        long diff = (System.currentTimeMillis() - start);
        System.out.println("spring diff = " + diff);
        return diff;
    }

    private static long getSetTest() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < TIMES; i++) {
            UserDO userDO = getUserDO();
            UserBO userBO = new UserBO();
            userBO.setId(userDO.getId());
            userBO.setName(userDO.getName());
            userBO.setCreateTime(userDO.getCreateTime());
            userBO.setMan(userDO.getMan());
            userBO.setUpdateTime(userDO.getUpdateTime());
        }
        long diff = (System.currentTimeMillis() - start);
        System.out.println("getSet diff = " + diff);
        return diff;
    }

    private static long reflectAsmTest() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < TIMES; i++) {
            UserDO userDO = getUserDO();
            UserBO userBO = new UserBO();
            ReflectAsmUtils.copyProperties(userBO, userDO);
        }
        long diff = (System.currentTimeMillis() - start);
        System.out.println("reflectAsm diff = " + diff);
        return diff;
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
