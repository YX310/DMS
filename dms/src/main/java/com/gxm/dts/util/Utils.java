package com.gxm.dts.util;

import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

public class Utils {
    private static final String EMAIL_RULE ="^(\\w+((-\\w+)|(.\\w+)))+\\w+((-\\w+)|(.\\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+)*.[A-Za-z0-9]+$";
    public static boolean checkEmail(String email) {
        return Pattern.compile(EMAIL_RULE).matcher(email).matches();
    }

    public static String compareResult(String title, String source, String target) {
        String res = "";
        if(source == null || ("").equals(source)) {
            if (target == null) return res;
            if (!("").equals(target)) return "\n\n" + "填写了 " + title  + "\n" + target + "\n";
        } else {
            if (target == null || ("").equals(target)) return "\n\n" + "删除了 " + title  + "\n" + source + "\n";
            if (!source.equals(target)) {
                res =  "\n\n" + title + "\n" + "由 " + source + " 变更为 " + target ;
            }
        }
        return res;
    }

    public static void applyInfo(HttpSession session, Model model, String info) {
        Object object = session.getAttribute(info);
        if (object != null) {
            model.addAttribute(info, object);
            session.setAttribute(info, null);
        }
    }
}
