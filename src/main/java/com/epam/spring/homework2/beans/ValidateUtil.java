package com.epam.spring.homework2.beans;

public class ValidateUtil {

    private static boolean validateName(String name) {
        if (name == null) {
            return false;
        }
        return true;
    }

    private static boolean validateValue(int value) {
        if (value < 0) {
            return false;
        }
        return true;
    }

    public static String vaidateResult(String name, int value) {
        StringBuilder sb = new StringBuilder();

        boolean nameIsCorrect = validateName(name);
        if (nameIsCorrect == false) {
            sb.append("has empty name");
        }
        boolean valueIsCorrect = validateValue(value);
        if (valueIsCorrect == false) {
            if (!"".equals(sb)) {
                sb.append(", ")
                        .append("has value less then 0")
                        .append("!");
                return sb.toString();
            }
            return sb.append("has value less then 0!").toString();
        }
        return sb.toString();
    }
}
