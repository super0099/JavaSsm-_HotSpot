package com.yxm.common;

import com.yxm.vo.Dome1;

public class main {
    public static void main(String [] strings){
        System.out.println("--------------基本数据类型------------");
        String a = "1111";
        String b= a;
        a="ssss";
        System.out.println(a);
        System.out.println(b);
        System.out.println("----------------------对象(引用类型)----------");
        Dome1 dome1 = new Dome1();
        dome1.name="马云";
        dome1.age=40;
        Dome1 dome2 = dome1;
        dome2.name ="马化腾";
        dome2.age = 41;
        System.out.println(dome1.name+dome1.age);
        System.out.println(dome2);
        System.out.println("----------数组------------");
        int[] A = new int[]{1,2,3};
        int[] B = A;
        A[0] = 7;
        A[1] = 8;
        A[2] = 9;
        System.out.print("数组A:");
        for (int o: A) {
            System.out.print(o);
        }
        System.out.print("数组B:");
        for (int o: B) {
            System.out.print(o);
        }
    }
}
