package com.alerman.advent2019.util;

import lombok.Setter;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static java.util.Arrays.copyOf;
import static java.util.stream.Collectors.toSet;

public class IntCodeComputer {

    private static final Set<Integer> SUPPORTED_OPERATIONS = IntStream.rangeClosed(1, 9).boxed().collect(toSet());

    private static final Map<Integer, Consumer<OperationData>> OPERATIONS = Map.of(
            1, (operationData) ->  {
                operationData.setByParameter(3, operationData.getParameter(1) + operationData.getParameter(2));
                operationData.changePointerBy(4);
            },
            2, (operationData) ->  {
                operationData.setByParameter(3, operationData.getParameter(1) * operationData.getParameter(2));
                operationData.changePointerBy(4);
            },
            3, (operationData) -> {
                Scanner scanner = new Scanner(System.in);
                if(scanner.hasNextInt())
                    operationData.setByParameter(1, scanner.nextInt());
                operationData.changePointerBy(2);
            },
            4, (operationData) -> {
                System.err.println(operationData.getParameter(1));
                operationData.changePointerBy(2);
            },
            5, jumpIf(true),
            6, jumpIf(false),
            7, compare(-1),
            8, compare(0),
            9, (operationData) ->  {
                operationData.relativeBase+=operationData.getParameter(1);
                operationData.changePointerBy(2);
            }
    );

    public static void compute(long[] inputCode) {
        OperationData operationData = new OperationData(inputCode, 0, 0);
        while(operationData.pointer<inputCode.length) {
            if(!SUPPORTED_OPERATIONS.contains(operationData.getOptCode())) {
                break;
            }
            OPERATIONS.get(operationData.getOptCode()).accept(operationData);
        }
    }

    private static Consumer<OperationData> jumpIf(boolean parameter) {
        return (operationData) -> {
            if(parameter == (operationData.getParameter(1) != 0)) {
                operationData.setPointer(operationData.getParameter(2));
            } else {
                operationData.changePointerBy(3);
            }
        };
    }

    private static Consumer<OperationData> compare(int mode) {
        return (operationData) -> {
            boolean truthy = Long.compare(operationData.getParameter(1), operationData.getParameter(2)) == mode;
            operationData.setByParameter(3, truthy ? 1 : 0);
            operationData.changePointerBy(4);
        };
    }

    @Setter
    private static class OperationData {
        private long[] code;

        private int pointer;
        private int relativeBase;
        private int operationCode;
        private int parametersMode;

        private OperationData(long[] code, int pointer, int relativeBase) {
            this.code = code;
            this.pointer = pointer;
            this.relativeBase = relativeBase;
            this.setNewCodes();
        }

        private int getOptCode() {
            return operationCode;
        }

        private long getParameter(int parameterIndex) {
            int offset = getOffset(parameterIndex);
            return code[offset];
        }

        public void setByParameter(int parameterIndex, long value) {
            int offset = getOffset(parameterIndex);
            code[offset] = value;
        }

        public void setPointer(Long pointer) {
            this.pointer = pointer.intValue();
            this.setNewCodes();
        }

        public void changePointerBy(int pointer) {
            this.pointer+=pointer;
            this.setNewCodes();
        }

        private void setNewCodes() {
            this.operationCode = (int)code[this.pointer] % 100;
            this.parametersMode = (int)code[this.pointer] / 100;
        }

        private int getOffset(int parameterIndex) {
            int mode = getParameterMode(parameterIndex);
            if(mode == 1) {
                return this.pointer+parameterIndex;
            } else {
                int offset = (mode == 0 ? 0 : relativeBase) + (int) code[this.pointer + parameterIndex];
                if (offset >= code.length)
                    code = copyOf(code, offset + 100);
                return offset;
            }
        }

        private int getParameterMode(int parameterIndex) {
            return (parametersMode / (int)Math.pow(10, parameterIndex-1)) % 10;
        }
    }
}