package org.example;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Calc {

    public static int run(String exp) {

        //괄호 제거
        exp = stripOuterBrackets(exp);

        if (!exp.contains(" ")) {
            return Integer.parseInt(exp);
        }

        boolean needToMulti = exp.contains(" * ");
        boolean needToPlus = exp.contains(" + ") || exp.contains(" - ");

        // 곱셈, 덧셈 모두 존재 시 복합 연산으로 판단
        boolean needToCompound = needToPlus && needToMulti;

        // 뺄셈을 음수의 덧셈으로 변환
        exp = exp.replace("- ", "+ -");

        // 복합 연산을 하는 경우, 더하기 기호로 수식 분리
        if (needToCompound) {
            String[] bits = exp.split(" \\+ ");

            String newExp = Arrays.stream(bits)
                    .mapToInt(Calc::run)
                    .mapToObj(e -> e + "")
                    .collect(Collectors.joining(" + "));

            return run(newExp);
        }

// 계산 로직
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

    //괄호 제거 함수 로직
    private static String stripOuterBrackets(String exp) {
        for  (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(0) == '(' && exp.charAt(exp.length() - 1) == ')') {
                exp = exp.substring(1, exp.length() - 1);
            }
            //위 함수는 문자열 중 (와 )만 선택하여 제거 후 괄호 안 숫자만 리턴
        }
        return exp;
    }
}