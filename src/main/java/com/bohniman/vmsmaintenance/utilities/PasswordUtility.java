package com.bohniman.vmsmaintenance.utilities;


import java.util.regex.Pattern;

/**
 * PasswordUtility
 */
public class PasswordUtility {

    private final Pattern hasUppercase = Pattern.compile("[A-Z]");
    private final Pattern hasLowercase = Pattern.compile("[a-z]");
    private final Pattern hasNumber = Pattern.compile("\\d");
    private final Pattern hasSpecialChar = Pattern.compile("[^a-zA-Z0-9 ]");

    public String validateNewPass(String pass1, String pass2) {
        if (pass1 == null || pass2 == null) {
            return "<center>Please fill up both the fields</center>";
        }

        StringBuilder retVal = new StringBuilder();

        if (pass1.isEmpty() || pass2.isEmpty()) {
            return "<center>Please fill up both the fields</center>";
        }

        if (pass1.equals(pass2)) {
            if (pass1.length() < 8) {
                retVal.append("<li>Password is too short. Needs to have minimum 8 characters </li>");
            }

            if (!hasUppercase.matcher(pass1).find()) {
                retVal.append("<li>Password needs at least one upper case character</li>");
            }

            if (!hasLowercase.matcher(pass1).find()) {
                retVal.append("<li>Password needs a lowercase </li>");
            } 

            if (!hasNumber.matcher(pass1).find()) {
                retVal.append("<li>Password needs atleast one number </li>");
            }

            if (!hasSpecialChar.matcher(pass1).find()) {
                retVal.append("<li>Password needs atleast one special character i.e. !,@,#, etc. </li>");
            }
        } else {
            retVal.append("<li>Confirm password and new password mismatch.</li>");
        }
        if (retVal.length() == 0) {
            retVal.append("SUCCESS");
        }

        return retVal.toString();
    }
}