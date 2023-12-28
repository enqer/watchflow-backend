package com.example.watchflow.utils;

import org.springframework.stereotype.Service;


public class Number {

    public static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
