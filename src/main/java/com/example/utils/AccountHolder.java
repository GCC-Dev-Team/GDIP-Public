package com.example.utils;


import com.example.model.entity.Wxuser;

/**
 * 在当前线程中获取用户对象
 */
public class AccountHolder {

    private static final ThreadLocal<Wxuser> tl =new ThreadLocal<>();

    // 存数据
    public static void saveUser(Wxuser user){
        tl.set(user);
    }

    //取数据
    public static Wxuser getUser(){
        return tl.get();
    }

    //删除数据
    public static void removeUser(){
        tl.remove();
    }

}