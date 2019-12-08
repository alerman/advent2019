package com.alerman.advent2019.day7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Dec07 {
    public static void main(String[] args) throws IOException {
        File file = new File("/home/alerman/git/advent2019/src/main/java/com/alerman/advent2019/day7/input07.txt");
        int input = 0;
        String part1 = "01234";
        String part2 = "56789";
        HashMap<String, Integer> signals = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String baseString = reader.readLine();
            ArrayList<String> permutations = new ArrayList<>();
            int[] instructions = Arrays.stream(baseString.split(",")).mapToInt(Integer::parseInt).toArray();

            permutations("", part2, permutations);
            for (String seq: permutations) {
                HashMap<IntCodeComputer, Integer> settings = new HashMap<>(5);
                ArrayList<IntCodeComputer> computers = new ArrayList<>(5);
                int exitCode = 0;
                for (int i = 0; i < seq.length(); i++) {
                    IntCodeComputer computer = new IntCodeComputer(instructions.clone());
                    computers.add(computer);
                    settings.put(computer, Integer.parseInt(String.valueOf(seq.charAt(i))));
                    computer.setInput(settings.get(computer));
                }
                while (exitCode != 99){
                    for (IntCodeComputer computer : computers){
                        computer.setInput(input);
                        exitCode = computer.run();
                        input = computer.getOutput();
                    }
                }
                signals.put(seq, input);
                input = 0;
            }
            calculateMax(signals);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void calculateMax(HashMap<String, Integer> signals){
        String signal = "";
        int highest = 0;
        for (String seq : signals.keySet()){
            if (signals.get(seq) > highest){
                signal = seq;
                highest = signals.get(seq);
            }
        }
        System.out.println("Highest signal is " + highest + " from sequence " + signal);
    }

    private static void permutations(String prefix, String s, ArrayList<String> list) {
        int n = s.length();
        if (n == 1) {
            list.add(prefix + s);
        } else {
            for (int i = 0; i < n; i++)
                permutations(prefix + s.charAt(i), s.substring(0, i) + s.substring(i+1, n), list);
        }
    }
}