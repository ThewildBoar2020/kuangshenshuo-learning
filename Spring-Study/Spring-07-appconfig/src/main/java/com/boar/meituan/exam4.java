package com.boar.meituan;
import java.util.Scanner;
public class exam4 {
    public static void main(String[] args) {
                Scanner scanner = new Scanner(System.in);
                int n= scanner.nextInt();
                int [] nums=new int[n];
                 for (int i = 0; i < n; i++) {
                     nums[i]=scanner.nextInt();
                  }
                 int count = 0;
                 for(int i = 0; i < nums.length; i++)
                     for(int j = i+1; j <  nums.length; j++)
                         for(int k = j+1; k < nums.length; k++)
                             if(nums[i]-nums[j] == 2*nums[j]-nums[k])
                                 count++;
                        System.out.println(count);


            }
        }






