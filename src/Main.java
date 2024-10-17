//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ange ett personnummer, samordningsnummer eller organisationsnummer:");
        String inputNumber = scanner.nextLine().trim();
        identifyAndValidateNumber(inputNumber);
        scanner.close();
    }

    public static void identifyAndValidateNumber(String number) {
        String cleanedNumber = number.replaceAll("[^\\d]", "");
        if (cleanedNumber.length() == 10) {
            if (Character.getNumericValue(cleanedNumber.charAt(0)) >= 2) {
                if (NumberValidator.isValidOrganisationsnummer(cleanedNumber)) {
                    System.out.println("Giltigt organisationsnummer.");
                } else {
                    System.out.println("Ogiltigt organisationsnummer.");
                }
            } else if (cleanedNumber.length() != 10 && cleanedNumber.length() != 12) {
                System.out.println("Ogiltigt nummerformat.");
            } else {
                String dayPart = cleanedNumber.substring(cleanedNumber.length() - 8, cleanedNumber.length() - 6);
                int day = Integer.parseInt(dayPart);
                if (day >= 61 && day <= 91) {
                    if (NumberValidator.isValidSamordningsnummer(cleanedNumber)) {
                        System.out.println("Giltigt samordningsnummer.");
                    } else {
                        System.out.println("Ogiltigt samordningsnummer.");
                    }
                } else if (day >= 1 && day <= 31) {
                    if (NumberValidator.isValidPersonnummer(cleanedNumber)) {
                        System.out.println("Giltigt personnummer.");
                    } else {
                        System.out.println("Ogiltigt personnummer.");
                    }
                } else {
                    System.out.println("Ogiltigt nummer.");
                }
            }
        } else if (cleanedNumber.length() == 12) {
            if (NumberValidator.isValidPersonnummer(cleanedNumber)) {
                System.out.println("Giltigt personnummer.");
            } else {
                System.out.println("Ogiltigt personnummer.");
            }
        } else {
            System.out.println("Ogiltigt nummerformat.");
        }

    }
}
