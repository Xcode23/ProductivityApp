package com.example.bass.productivityapp;

public class Utils {

    public static String toType(int num){
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

    public static int fromType(String str) {
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

    public static String toTimeQuantity(int num){
        switch(num){
            case 0:
                return "Days";
            case 1:
                return "Weeks";
            case 2:
                return "Months";
            case 3:
                return "Years";
            default:
                return "Wrong Time!";
        }
    }

    public static int fromTimeQuantity(String str){
        switch(str){
            case "Days":
                return 0;
            case "Weeks":
                return 1;
            case "Months":
                return 2;
            case "Years":
                return 3;
            default:
                return -1;
        }
    }

    public static String toStatus(int num){
        switch(num){
            case 0:
                return "Hold";
            case 1:
                return "Active";
            case 2:
                return "Complete";
            default:
                return "Wrong Status!";
        }
    }

    public static int fromStatus(String str){
        switch(str){
            case "Hold":
                return 0;
            case "Active":
                return 1;
            case "Complete":
                return 2;
            default:
                return -1;
        }
    }

}
