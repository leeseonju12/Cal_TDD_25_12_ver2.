package org.example;

public class Calc {

    public static int run(String exp) { // 10 + 5 * 2

        boolean needToMulti = exp.contains("*");
        boolean needToPlus = exp.contains("+");

        boolean needToCompound = needToPlus && needToMulti;

        exp = exp.replace("- ", "+ -");

        if (needToCompound) {
            String[] bits = exp.split(" \\+ ");

            return Integer.parseInt(bits[0]) + run(bits[1]);
        }

        if (needToPlus) {
            String[] bits = exp.split(" \\+ ");
            int sum = 0;

            for (int i = 0; i < bits.length; i++) {
                sum += Integer.parseInt(bits[i]);
            }

            return sum;

        } else if (needToMulti) {
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