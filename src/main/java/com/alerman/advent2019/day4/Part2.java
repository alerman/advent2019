package com.alerman.advent2019.day4;

public class Part2 {
    public static void main(String[] args) {
        int count = 0;
        for(int i=206938; i<679128; i++)
        {
            String repr = String.valueOf(i);
            char[] reprArray = repr.toCharArray();
            boolean candidate = false;
            for(int j=1;j<6;j++)
            {
                if(reprArray[j-1] == reprArray[j])
                {
                    if(j==1)
                    {
                        candidate = reprArray[2] != reprArray[j];
                        continue;
                    }
                    if(!candidate && j>1 && j<5)
                    {
                        candidate = reprArray[j+1] != reprArray[j] && reprArray[j-2] != reprArray[j];

                    }
                    if(!candidate && j == 5)
                    {
                        candidate = reprArray[3] != reprArray[j];
                    }
                }
                if(reprArray[j-1]>reprArray[j])
                {
                    candidate = false;
                    break;
                }
            }
            if(candidate)
            {
                count++;
            }
        }

        System.out.println(count);
    }
}
