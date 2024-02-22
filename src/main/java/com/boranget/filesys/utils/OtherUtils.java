package com.boranget.filesys.utils;

import java.util.UUID;

public class OtherUtils {
    public static String getRandomId(){
        String id = UUID.randomUUID().toString();
        return id.replace("-","");
    }
}
