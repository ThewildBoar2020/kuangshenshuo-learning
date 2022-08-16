package com.boar.meituan;

public class exam2 {
        public static void main(String[] args) {
            int n=2,m=2,k=4;
            String step1 = "SDWA";
            char[] step2 = step1.toCharArray();
            String[] stepstring = new String[step1.length()];

            int[][] hoomsize = new int[n][m];
            int hang = 0,lie = 0;
            int co = 0;
            int homekong = n*m;

            for(int i=0; i<step1.length(); i++){
                stepstring[i] = String.valueOf(step2[i]);
                if (stepstring[i].equals("W")){
                    hoomsize[hang-1][lie] = hoomsize[hang-1][lie]+1;
                    hang = hang-1;
                    lie = lie;

                }else if(stepstring[i].equals("A")){
                    hoomsize[hang][lie-1] = hoomsize[hang][lie-1]+1;
                    hang = hang;
                    lie = lie-1;
                }else if(stepstring[i].equals("S")){
                    hoomsize[hang+1][lie] = hoomsize[hang+1][lie]+1;
                    hang = hang+1;
                    lie = lie;
                }else{
                    hoomsize[hang][lie+1] = hoomsize[hang][lie+1]+1;
                    hang = hang;
                    lie = lie+1;
                }

            }

            int count=0;

            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++ ){
                    if(hoomsize[i][j]==0){
                        count++;
                    }
                }
            }
            if(count>0){
                System.out.println("No");
                System.out.println(count);
            }else{
                System.out.println("Yes");
            }



        }


    }

