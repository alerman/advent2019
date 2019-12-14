package com.alerman.advent2019.day7;

import com.google.common.collect.Lists;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Part2 {
    public static boolean halted = false;
    public static void main(String[] args) throws IOException {
        String input = "3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54," +
                "-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4," +
                "53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10";
        long max = 0;

        OutputtingIntComputer A = new OutputtingIntComputer("A",input.split(","));
        A.runComputer(9);
        OutputtingIntComputer B = new OutputtingIntComputer("B",input.split(","));
        B.runComputer(7);
        OutputtingIntComputer C = new OutputtingIntComputer("C",input.split(","));
        C.runComputer(8);
        OutputtingIntComputer D = new OutputtingIntComputer("D",input.split(","));
        D.runComputer(5);
        OutputtingIntComputer E = new OutputtingIntComputer("E",input.split(","));
        E.runComputer(6);
        List<OutputtingIntComputer> oics = Lists.newArrayList(A,B,C,D,E);
        long result = 0;
int i = 0;
                while(oics.stream().noneMatch(OutputtingIntComputer::isHalted)){
                    i = i % 5;

                    OutputtingIntComputer oic = oics.get(i);
                    result = oic.runComputer(result);
                    i++;
                }



        System.out.println(result);
    }
}
