package utils;

import java.util.UUID;

/**
 * Created by josea.wolff on 8/12/18.
 */
public class GenerateUid {

    public static String getUid(){
        String uUID = UUID.randomUUID().toString();
        String uid = uUID.replaceAll("-","");
        uid = uid.substring(0,24);
        return uid;
    }
}
