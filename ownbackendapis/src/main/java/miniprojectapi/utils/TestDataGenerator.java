package miniprojectapi.utils;

import java.util.UUID;

public class TestDataGenerator {

    public static String genFirstName() {
        return UUID.randomUUID().toString().substring(0, 5);
    }

    public static String genLastName() {
        return UUID.randomUUID().toString().substring(0, 5);
    }

    public static String genEmail() {
        return UUID.randomUUID().toString().substring(0, 5) + "@test.com";
    }

    public static String genMobile() {
        return "9" + (int) (Math.random() * 1000000000);
    }

    public static String genDateOfBirth() {
        return "1990-01-01";
    }

    public static String genGender() {
        return "Male";
    }

    public static String genCountry() {
        return "India";
    }

    public static String randomCoursename() {
        return "Computer Science";
    }

    public static String randomCourseCode() {
        return "MCS-" + (int) (Math.random() * 1000 + 100);
    }

    public static String randomLevel() {
        return "Postgraduate";
    }

    public static String randomDuration() {
        return (int) (Math.random() * 100000) + " Years";
    }

    public static String randomIntake() {
        return "September 2026";
    }

    public static int randomTuitionFee() {
        return (int) (Math.random() * 10000);
    }

    public static String randomCurrency() {
        return "GBP";
    }

    public static String randomUniversity() {
        return "University of London";
    }
}
