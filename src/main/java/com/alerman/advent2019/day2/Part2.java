package com.alerman.advent2019.day2;

public class Part2 {
    public static void main(String[] args) {
        String input = "1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,13,1,19,1,6,19,23,2,23,6,27,1,5,27,31,1,10,31,35,2,6,35,39,1,39,13,43,1,43,9,47,2,47,10,51,1,5,51,55,1,55,10,59,2,59,6,63,2,6,63,67,1,5,67,71,2,9,71,75,1,75,6,79,1,6,79,83,2,83,9,87,2,87,13,91,1,10,91,95,1,95,13,99,2,13,99,103,1,103,10,107,2,107,10,111,1,111,9,115,1,115,2,119,1,9,119,0,99,2,0,14,0";
        input.replaceAll(" ", "");

        int opcodeIndex = 0;
        for (int i = 0; i <= 99; i++) {
            for (int j = 0; j <= 99; j++) {
                String[] splitInput = input.split(",");
                splitInput[1] = String.valueOf(i);
                splitInput[2] = String.valueOf(j);

                if(19690720 == runIntcodeComputer(splitInput, 0))
                {
                    System.out.println(i);
                    System.out.println(j);
                }
            }
        }

    }

    private static int runIntcodeComputer(String[] splitInput, int opcodeIndex) {
        while (Integer.valueOf(splitInput[opcodeIndex]) != 99) {
            int opcode = Integer.valueOf(splitInput[opcodeIndex]);
            boolean multiply = false;
            if (opcode != 1) {
                multiply = true;
            }

            int first = Integer.valueOf(splitInput[Integer.valueOf(splitInput[opcodeIndex + 1])]);
            int second = Integer.valueOf(splitInput[Integer.valueOf(splitInput[opcodeIndex + 2])]);
            int answerIndex = Integer.valueOf(splitInput[opcodeIndex + 3]);

            if (multiply) {
                splitInput[answerIndex] = String.valueOf(first * second);
            } else {
                splitInput[answerIndex] = String.valueOf(first + second);
            }

            opcodeIndex = opcodeIndex + 4;

        }
        return Integer.valueOf(splitInput[0]);
    }
}
