package com.mycompany.calculatethechange.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.io.*;

/**
 *
 * @author Mykyta Tymko
 */
public class Main {

    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(reader);

        try {
            double purchasePrice = Double.parseDouble(in.readLine());
            double cash = Double.parseDouble(in.readLine());
            Main.calculateChange(purchasePrice, cash);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void calculateChange(double purchasePrice, double cash) {
        if (cash < purchasePrice) {
            throw new NullPointerException("ERROR");
        }

        int a = (int) (purchasePrice * 100);
        int b = (int) (cash * 100);
        int value = b - a;

        //0.01 - 1; 0.02 - 2; ...; 50 - 5000
        int[] coins = {1, 2, 5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000};
        int size = coins.length;

        int table[] = new int[value + 1];
        table[0] = 0;
        for (int i = 1; i <= value; i++) {
            table[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= value; i++) {
            for (int j = 0; j < size; j++) {
                if (coins[j] <= i) {
                    int sub_res = table[i - coins[j]];
                    if (sub_res != Integer.MAX_VALUE && sub_res + 1 < table[i]) {
                        table[i] = sub_res + 1;
                    }
                }
            }
        }
        System.out.println(table[value]);
    }

}
