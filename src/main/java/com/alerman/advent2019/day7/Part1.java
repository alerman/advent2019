package com.alerman.advent2019.day7;

import com.alerman.advent2019.day5.Part2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        List<String> permutations = Files.readAllLines(Paths.get("/home/alerman/git/advent2019/src/main/java/com/alerman/advent2019/day7/permutations.txt"));
        String input = "3,8,1001,8,10,8,105,1,0,0,21,34,43,64,85,98,179,260,341,422,99999,3,9,1001,9,3,9,102,3,9,9,4,9,99,3,9,102,5,9,9,4,9,99,3,9,1001,9,2,9,1002,9,4,9,1001,9,3,9,1002,9,4,9,4,9,99,3,9,1001,9,3,9,102,3,9,9,101,4,9,9,102,3,9,9,4,9,99,3,9,101,2,9,9,1002,9,3,9,4,9,99,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,2,9,4,9,99,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1002,9,2,9,4,9,99,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,101,1,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,2,9,4,9,3,9,101,1,9,9,4,9,3,9,101,1,9,9,4,9,99,3,9,101,1,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,1,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,99,3,9,101,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,1002,9,2,9,4,9,3,9,102,2,9,9,4,9,3,9,101,2,9,9,4,9,3,9,102,2,9,9,4,9,3,9,1001,9,2,9,4,9,3,9,1002,9,2,9,4,9,3,9,1001,9,1,9,4,9,3,9,102,2,9,9,4,9,99";

        long max = 0;
        for(String permutation : permutations)
        {
            char[] permArray = permutation.toCharArray();
            long result = 0;
            for(int i=0;i<permArray.length;i++) {
                int phase = Integer.parseInt(String.valueOf(permArray[i]));
                result = outputtingIntComputer(input, phase, result);
            }

            max = Math.max(max,result);
        }


        System.out.println(max);
    }


    public static long outputtingIntComputer(String input, int phase, long lineInput) throws IOException {
        boolean phaseUsed = false;
        input.replaceAll(" ","");
        String[] splitInput = input.split(",");
        int opcodeIndex = 0;
        int steps = 4;
        long returnCode = lineInput;
        while(Integer.valueOf(splitInput[opcodeIndex]) != 99)
        {
            // int opcode = Integer.valueOf(splitInput[opcodeIndex]);
            boolean firstParamImmediate = false;
            boolean secondParamImmediate = false;
            char[] opcodeArr = splitInput[opcodeIndex].toCharArray();
            if(opcodeArr.length >2)
            {
                if(opcodeArr.length == 3 && opcodeArr[0] == '1'){
                    firstParamImmediate = true;
                }
                if(opcodeArr.length == 4)
                {
                    firstParamImmediate = opcodeArr[1] == '1';
                    secondParamImmediate = opcodeArr[0] == '1';
                }
            }

            String opcodeString = opcodeArr.length>=2 ? String.valueOf(opcodeArr[opcodeArr.length-2 ]) + String.valueOf(opcodeArr[opcodeArr.length - 1]) : String.valueOf(opcodeArr[0]);
            int opcode = Integer.parseInt(opcodeString);

            if(opcode>2 && opcode<5)
            {
                steps = 2;
            } else {
                if(opcode==5 ||opcode == 6)
                {
                    steps = 3;
                }else {
                    steps = 4;
                }
            }
            boolean multiply = false;
            if(opcode <=2) {
                if (opcode == 2) {
                    multiply = true;
                }

                int first = firstParamImmediate? Integer.parseInt(splitInput[opcodeIndex + 1]) :  Integer.parseInt(splitInput[Integer.parseInt(splitInput[opcodeIndex + 1])]);
                int second = secondParamImmediate ? Integer.parseInt(splitInput[opcodeIndex + 2]) : Integer.parseInt(splitInput[Integer.parseInt(splitInput[opcodeIndex + 2])]);
                int answerIndex = Integer.parseInt(splitInput[opcodeIndex + 3]);

                if (multiply) {
                    splitInput[answerIndex] = String.valueOf(first * second);
                } else {
                    splitInput[answerIndex] = String.valueOf(first + second);
                }
            } else {
                if(opcode == 3)
                {

                    //System.out.print("Requesting Input: ");
                    //BufferedReader reader =
                    //       new BufferedReader(new InputStreamReader(System.in));

                    // Reading data using readLine
                    //String readInput = reader.readLine();
                    //System.out.println("INPUTTING: input: "+phase + " " + lineInput +" usePhase: " + !phaseUsed);
                    splitInput[Integer.parseInt(splitInput[opcodeIndex + 1])] = phaseUsed ? String.valueOf(lineInput) : String.valueOf(phase);
                    phaseUsed = true;
                }
                if(opcode == 4)
                {
                    String output = firstParamImmediate ? splitInput[opcodeIndex + 1] : splitInput[Integer.parseInt(splitInput[opcodeIndex + 1])];
                    //System.out.println(output);
                    returnCode = Integer.parseInt(output);
                }

                if(opcode == 5)
                {
                    //jump instruction
                    int first = firstParamImmediate? Integer.parseInt(splitInput[opcodeIndex + 1]) :  Integer.parseInt(splitInput[Integer.parseInt(splitInput[opcodeIndex + 1])]);
                    if(first != 0){
                        steps=0;
                        opcodeIndex = secondParamImmediate ? Integer.parseInt(splitInput[opcodeIndex + 2]) : Integer.parseInt(splitInput[Integer.parseInt(splitInput[opcodeIndex + 2])]);
                    }
                }
                if(opcode == 6)
                {
                    //jump instruction
                    int first = firstParamImmediate? Integer.parseInt(splitInput[opcodeIndex + 1]) :  Integer.parseInt(splitInput[Integer.parseInt(splitInput[opcodeIndex + 1])]);
                    if(first == 0){
                        steps=0;
                        opcodeIndex = secondParamImmediate ? Integer.parseInt(splitInput[opcodeIndex + 2]) : Integer.parseInt(splitInput[Integer.parseInt(splitInput[opcodeIndex + 2])]);
                    }
                }
                if(opcode == 7)
                {
                    int first = firstParamImmediate? Integer.parseInt(splitInput[opcodeIndex + 1]) :  Integer.parseInt(splitInput[Integer.parseInt(splitInput[opcodeIndex + 1])]);
                    int second = secondParamImmediate ? Integer.parseInt(splitInput[opcodeIndex + 2]) : Integer.parseInt(splitInput[Integer.parseInt(splitInput[opcodeIndex + 2])]);
                    int answerIndex = Integer.parseInt(splitInput[opcodeIndex + 3]);
                    splitInput[answerIndex] = first < second ? "1" : "0";
                }
                if(opcode == 8)
                {
                    int first = firstParamImmediate? Integer.parseInt(splitInput[opcodeIndex + 1]) :  Integer.parseInt(splitInput[Integer.parseInt(splitInput[opcodeIndex + 1])]);
                    int second = secondParamImmediate ? Integer.parseInt(splitInput[opcodeIndex + 2]) : Integer.parseInt(splitInput[Integer.parseInt(splitInput[opcodeIndex + 2])]);
                    int answerIndex = Integer.parseInt(splitInput[opcodeIndex + 3]);
                    splitInput[answerIndex] = first == second ? "1" : "0";

                }
            }
            opcodeIndex = opcodeIndex + steps;

        }

       // System.out.println(splitInput);
        return returnCode;
    }
}
