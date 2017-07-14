package com.xlh.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangbin
 * @Type Test
 * @Desc
 * @date 2017-07-12
 * @Version V1.0
 */
public class Test {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

//        CompletableFuture<String> stringCompletableFuture = CompletableFuture.runAsync(() -> {
//            try {
//                Thread.sleep(1000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("======= 异步执行结束 =====");
//        }).thenApply(s -> {
//            System.out.println(" ========== 异步执行结束 执行如下逻辑============== ");
//            return "abc";
//        });
//        System.out.println(" =========== 同步执行结束 =============== ");
//
//        Thread.sleep(2000L);
//
//        System.out.println("stringCompletableFuture = " + stringCompletableFuture.get());



        //
//        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> "aaa");
//
//        System.out.println("numberCompletableFuture = " + stringCompletableFuture.get());

//        String[] strings = { "aa", "bb", "cc", "dd" };
//
//        Arrays.stream(strings).map(s -> {
//            if (s.equals("aa")) {
//                return "123";
//            }
//            return "55555";
//        }).forEach(s -> System.out.println("s = " + s));


    }


}
