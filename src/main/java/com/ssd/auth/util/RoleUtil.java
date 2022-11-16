package com.ssd.auth.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoleUtil {
    public static final String ADMIN = "ADMIN";
    public static final String MANAGER = "MANAGER";
    public static final String USER = "USER";


    /**
     *
     * @return all role list
     */
    public static List<String> getRoles (){
        return new ArrayList<>(Arrays.asList(
                ADMIN,
                MANAGER,
                USER
        ));
    }
}
