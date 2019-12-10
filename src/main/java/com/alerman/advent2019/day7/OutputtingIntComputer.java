package com.alerman.advent2019.day7;

public class OutputtingIntComputer {
    String[] splitInput;

    public boolean isHalted() {
        return halted;
    }

    public void setHalted(boolean halted) {
        this.halted = halted;
    }
 long lastOutput = 0;
    boolean halted = false;

    String id = "";
    public OutputtingIntComputer(String id, String[] input)
    {
        splitInput = input.clone();
        this.id = id;
    }

    public long runComputer(long lineInput){
        System.out.println(id + ": " + lineInput);
        int opcodeIndex = 0;
            int steps;
            long returnCode = lineInput;
            while(Integer.parseInt(splitInput[opcodeIndex]) != 99)
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
                        splitInput[Integer.parseInt(splitInput[opcodeIndex + 1])] = String.valueOf(lineInput);
                    }
                    if(opcode == 4)
                    {
                        String output = firstParamImmediate ? splitInput[opcodeIndex + 1] : splitInput[Integer.parseInt(splitInput[opcodeIndex + 1])];
                        //System.out.println(output);
                        returnCode = Integer.parseInt(output);
                        lastOutput = returnCode;
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
            halted=true;
            return returnCode;
    }
}
