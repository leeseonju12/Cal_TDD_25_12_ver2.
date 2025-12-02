package org.example;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Calc {

    public static int run(String exp) {

        if (!exp.contains(" ")) {
            return Integer.parseInt(exp);
        }

        // 뺄셈을 음수의 덧셈으로 변환
        // 원래 +가 없었던 수식은 변환 후에도 더하기 연산 부분이 false로 판단됨
        // 때문에 음수 변환을 연산자 체크 전 수행
        exp = exp.replace("- ", "+ -");

        boolean needToMulti = exp.contains("*");
        boolean needToPlus = exp.contains("+");
        boolean parentheses = exp.contains("( )");

        if (parentheses) {
            String[] bits = exp.split(" \\+ ");
            int sum = 0;

            for (int i = 0; i < i+1; i++) {
                sum += Integer.parseInt(bits[i]);
            }

            return sum;
        }

        // 곱셈, 덧셈 모두 존재 시 복합 연산으로 판단
        boolean needToCompound = needToPlus && needToMulti;

        // 복합 연산을 하는 경우, 더하기 기호로 수식 분리
        if (needToCompound) {
            String[] bits = exp.split(" \\+ ");

////스트림 활용
//            String newExp = Arrays.stream(bits)
//                    .mapToInt(Calc::run)
//                    .mapToObj(e -> e + "")
//                    .collect(Collectors.joining(" + "));

////1
//            StringBuilder sb = new StringBuilder();
//
//            for (int i = 0; i < bits.length; i++) {
//                int result = Calc.run(bits[i]);
//                sb.append(result);
//
//                // 마지막 요소가 아니면 " + " 추가
//                if (i < bits.length - 1) {
//                    sb.append(" + ");
//                }
//            }
//
//            String newExp = sb.toString();

//2
            String newExp = "";

            for (int i = 0; i < bits.length; i++) {
                int result = Calc.run(bits[i]);
                newExp += result;

                if (i < bits.length - 1) {
                    newExp += " + ";
                }
            }
//
            return run(newExp);
            // 재귀 호출로 계산
        }

        if (needToPlus) {
            String[] bits = exp.split(" \\+ ");
            int sum = 0;

            for (int i = 0; i < bits.length; i++) {
                sum += Integer.parseInt(bits[i]);
            }

            return sum;
        }

        else if (needToMulti) {
            String[] bits = exp.split(" \\* ");

            int sum = 1;

            for (int i = 0; i < bits.length; i++) {
                sum *= Integer.parseInt(bits[i]);
            }

            return sum;
        }


        throw new RuntimeException("해석 불가 : 올바른 계산식이 아님");
    }

}