package com.example.ecthone.Model;

public enum Role {
    User,ORG,SuprimeUser;

    public static Role getRole(String role) {
        switch (role) {
            case "User":
                return Role.User;
            case "Org":
                return Role.ORG;
            case "SuprimeUser":
                return Role.SuprimeUser;
        }
        return null;
    }
}
