package com.yuxin.zhouyang.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Stream流式计算
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class Student{
    private int SID;
    private String name;
    private int age;
}
public class StreamDemo {
    public static void main(String[] args) {
        Student s1 = new Student(1, "张晨", 28);
        Student s2 = new Student(2, "张超", 29);
        Student s3 = new Student(3, "张晨", 30);
        Student s4 = new Student(4, "张勇", 31);
        Student s5 = new Student(5, "张圆圆", 32);
        Student s6 = new Student(6, "张甜甜", 33);
        List<Student> list = Arrays.asList(s1, s2, s3, s4, s5, s6);
        /**
         * 找出年龄是偶数的
         *      年龄大于30的
         *            名字里面有晨的
         */
        list.stream()
                .filter(student -> student.getAge()%2==0)
                .filter(student -> student.getAge() >= 28)
                .filter(student -> student.getName().contains("晨"))
                .map(student -> student.getAge()*2)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}
