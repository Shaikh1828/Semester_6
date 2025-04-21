package com.example.jwt;

import com.example.vowel.VowelCounter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Scanner;

public class JwtGenerator {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateJwt(String name) {
        return Jwts.builder()
                .setSubject(name)
                .signWith(key)
                .compact();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        String jwt = generateJwt(name);
        System.out.println("Generated JWT: " + jwt);

        //the following codes use external maven project!!
        int vowelCount = VowelCounter.countVowels(name);
        System.out.println("Your name has " + vowelCount + " vowels.");
    }
}
