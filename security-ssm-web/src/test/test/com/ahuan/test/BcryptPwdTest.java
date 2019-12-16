package com.ahuan.test;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BcryptPwdTest {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }
    @Test
    public void test(){
        String user = encodePassword("user");
        System.out.println(user);
    }

    @Test
    public void test1(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("1");
        list.add("1");
        list.add("1");
        list.stream().filter(s -> {
            System.out.println(s);
           return true;
        }).collect(Collectors.joining());
    }
}
