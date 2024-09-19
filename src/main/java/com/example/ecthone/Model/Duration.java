package com.example.ecthone.Model;

public enum Duration {
    Constant,Onetime,Ntime;
    public static Duration getDuration(String duration) {
        switch (duration) {
            case "Constant":
                return Constant;
                case "Onetime":
                    return Onetime;
                    case "Ntime":
                        return Ntime;
        }
        return null;
    }
    public static String ru(Duration duration) {
        switch (duration) {
            case Onetime:
                return "Однократно";
            case Ntime:
                return "Многократно";
            case Constant:
                return "Вечно";
        }
        return null;
    }
}
