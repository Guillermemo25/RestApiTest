package com.chakray.userapi.util;

import java.util.regex.Pattern;

public class Validators {
    // Define the regex patterns
    private static final Pattern TAXID_PATTERN = Pattern.compile("^[A-Z]{3,4}\\d{6}[A-Z0-9]{3}$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?\\d{10,12}$");

    /**
     * Checks if the tax id is valid
     * @param taxId The tax id to check
     * @return True if the tax id is valid, false otherwise
     */
    public static boolean isValidTaxId(String taxId) {
        return TAXID_PATTERN.matcher(taxId).matches();
    }
    
    /**
     * Checks if the phone number is valid
     * @param phone The phone number to check
     * @return True if the phone number is valid, false otherwise
     */
    public static boolean isValidPhone(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }
}
