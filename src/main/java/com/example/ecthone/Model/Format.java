package com.example.ecthone.Model;

public enum Format {
    Open,Invite,Private;
    public static Format getFormat(String format) {
        switch (format) {
            case "Open":
                return Open;
                case "Invite":
                    return Invite;
                    case "Private":
                        return Private;
        }
        return null;
    }

    public static String ru(Format format) {
        switch (format) {
            case Open:
                return "Открытое";
            case Invite:
                return "По приглашению";
            case Private:
                return "Приват";
        }
        return null;
    }
}
