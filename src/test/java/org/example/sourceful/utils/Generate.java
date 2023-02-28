package org.example.sourceful.utils;

import com.github.javafaker.Faker;

public final class Generate {

    private static final Faker FAKER = new Faker();

    public static String name() {
        return FAKER.name().fullName();
    }

    public static String email() {
        return FAKER.internet().emailAddress();
    }

    public static String subject() {
        return FAKER.lorem().sentence();
    }

    public static String message() {
        return FAKER.lorem().paragraph();
    }
}
