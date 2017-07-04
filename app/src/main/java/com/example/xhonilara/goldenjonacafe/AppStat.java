package com.example.xhonilara.goldenjonacafe;

/**
 * Created by xhonilara on 16/05/17.
 */

public class AppStat {

    public static boolean IS_ADMIN = true;

    public static final String ADMIN_EMAIL_1 = "xhonilara@gmail.com";
    public static final String ADMIN_EMAIL_2 = "jona.lara@gmail.com";
    public static final String ADMIN_PSW_1 = "bargolden";
    public static final String ADMIN_PSW_2 = "bargolden";

    public static void setIsAdmin(boolean b){
        IS_ADMIN = b;
    }

    public static boolean isAdmin() {
        return IS_ADMIN;
    }
}
