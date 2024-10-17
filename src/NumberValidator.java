//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.time.DateTimeException;
import java.time.LocalDate;

public class NumberValidator {
    public NumberValidator() {
    }

    public static void main(String[] args) {
        String number = "190910799824";
        if (isValidSamordningsnummer(number)) {
            System.out.println("Giltigt samordningsnummer.");
        } else if (isValidPersonnummer(number)) {
            System.out.println("Giltigt personnummer.");
        } else if (isValidOrganisationsnummer(number)) {
            System.out.println("Giltigt organisationsnummer.");
        } else {
            System.out.println("Ogiltigt nummer.");
        }

    }

    public static boolean isValidPersonnummer(String number) {
        number = number.replaceAll("[^\\d]", "");
        if (number.length() != 10 && number.length() != 12) {
            return false;
        } else {
            int year = Integer.parseInt(number.substring(0, 2));
            int month = Integer.parseInt(number.substring(2, 4));
            int day = Integer.parseInt(number.substring(4, 6));
            if (number.length() == 12) {
                int century = Integer.parseInt(number.substring(0, 2));
                year += (century - 19) * 100;
            } else {
                year += 1900;
            }

            return isValidDate(year, month, day) && day <= 31 ? isValidLuhn(number.substring(number.length() - 10)) : false;
        }
    }

    public static boolean isValidSamordningsnummer(String number) {
        number = number.replaceAll("[^\\d]", "");
        if (number.length() != 10 && number.length() != 12) {
            return false;
        } else {
            int year = Integer.parseInt(number.substring(0, 2));
            int month = Integer.parseInt(number.substring(2, 4));
            int day = Integer.parseInt(number.substring(4, 6));
            day -= 60;
            if (number.length() == 12) {
                int century = Integer.parseInt(number.substring(0, 2));
                year += (century - 19) * 100;
            } else {
                year += 1900;
            }

            return day >= 1 && day <= 31 && isValidDate(year, month, day) ? isValidLuhn(number.substring(number.length() - 10)) : false;
        }
    }

    public static boolean isValidOrganisationsnummer(String number) {
        number = number.replaceAll("[^\\d]", "");
        if (number.length() == 10) {
            int firstDigit = Character.getNumericValue(number.charAt(0));
            int middleDigits = Integer.parseInt(number.substring(2, 4));
            if (firstDigit >= 2 && middleDigits >= 20) {
                return isValidLuhn(number);
            }
        }

        return false;
    }

    private static boolean isValidLuhn(String number) {
        int sum = 0;
        boolean alternate = false;

        for(int i = number.length() - 1; i >= 0; --i) {
            int digit = Character.getNumericValue(number.charAt(i));
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            alternate = !alternate;
        }

        return sum % 10 == 0;
    }

    private static boolean isValidDate(int year, int month, int day) {
        try {
            LocalDate.of(year, month, day);
            return true;
        } catch (DateTimeException var4) {
            return false;
        }
    }
}
