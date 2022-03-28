package com.gxm.dms.util;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Utils {
    private static final String EMAIL_RULE ="^(\\w+((-\\w+)|(.\\w+)))+\\w+((-\\w+)|(.\\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+)*.[A-Za-z0-9]+$";
    public static boolean checkEmail(String email) {
        return Pattern.compile(EMAIL_RULE).matcher(email).matches();
    }
}
