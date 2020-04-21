package com.yuxin.zhouyang;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子引用
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class User{
    private String name;
    private int age;
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User z3 = new User("zhangsan", 18);
        User ls = new User("lisi", 20);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);
        System.out.println(atomicReference.compareAndSet(z3,ls)+"\t"+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(z3,ls)+"\t"+atomicReference.get().toString());
    }
}
