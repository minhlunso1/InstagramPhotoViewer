package minhna.submission.instagramphotoviewer.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 13-Mar-16.
 */
public class MyUtils {

    public static String calculatePassTime(long createdTime){//createdTime is second
        String passTime = "";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy");
        passTime = formatter.format(new Date(createdTime * 1000L));
        long currentTime = System.currentTimeMillis() / 1000;
        if (currentTime-createdTime<60)
            passTime = currentTime-createdTime + " seconds ago";
        else {
            currentTime/=60;
            createdTime/=60;
            if (currentTime-createdTime<60)
                passTime = currentTime-createdTime + " minuets ago";
            else {
                currentTime/=60;
                createdTime/=60;
                if (currentTime-createdTime<24)
                    passTime = currentTime-createdTime + " hours ago";
                else {
                    currentTime/=24;
                    createdTime/=24;
                    if (currentTime-createdTime<31)
                        passTime = currentTime-createdTime + " days ago";
                }
            }
        }
        return passTime;
    }

    public static String getLikeCount(long number){
        return new DecimalFormat("#,###,###").format(number) + " likes";
    }
}
