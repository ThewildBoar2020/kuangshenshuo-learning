package com.boar.meituan;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class exam3 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String a = sc.nextLine();

//        System.out.println(sort(a));
    }
    public static LinkedList<Integer> sort(int[] a) {
        int n = a.length;
        LinkedList<Integer> list = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        list.add(a[n-2]);
        list.add(a[n-1]);
        int i = n-3;
        while(i>=0) {
            int len = list.size();
            for(int j=0;j<len-1;j++) {
                queue.add(list.remove(0));
            }
            while(!queue.isEmpty()) {
                list.add(queue.poll());
            }
            len = list.size();
            for(int j=0;j<len;j++) {
                queue.add(list.remove(0));
            }
            list.add(a[i]);
            while(!queue.isEmpty()) {
                list.add(queue.poll());
            }
            i--;
        }

            return list;


    }

}
