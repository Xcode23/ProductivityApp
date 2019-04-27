package com.example.bass.productivityapp;

public class Utils {

    public static String ToType(int num){
        switch(num){
            case 0:
                return "Work";
            case 1:
                return "Hobby";
            case 2:
                return "Improv";
            default:
                return "Wrong Type!";
        }
    }

    public static int FromType(String str) {
        switch (str) {
            case "Work":
                return 0;
            case "Hobby":
                return 1;
            case "Improv":
                return 2;
            default:
                return -1;
        }
    }

    public static String ToTimeQuantity(int num){
        switch(num){
            case 0:
                return "DAY";
            case 1:
                return "WEEK";
            case 2:
                return "MONTH";
            case 3:
                return "YEAR";
            default:
                return "Wrong Time!";
        }
    }

    public static int FromTimeQuantity(String str){
        switch(str){
            case "DAY":
                return 0;
            case "WEEK":
                return 1;
            case "MONTH":
                return 2;
            case "YEAR":
                return 3;
            default:
                return -1;
        }
    }
}
