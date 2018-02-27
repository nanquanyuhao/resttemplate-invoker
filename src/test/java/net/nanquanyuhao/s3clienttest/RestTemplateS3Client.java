package net.nanquanyuhao.s3clienttest;

import net.nanquanyuhao.s3clienttest.bouncycastle.HMACSHA1Util;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by nanquanyuhao on 2018/2/23.
 */
public class RestTemplateS3Client {

    public static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    public static final String ENCODING = "utf-8";

    public static final String ACCESS_KEY = "ZXV8QX824OJI16G1OPGO";
    public static final String SECRET_KEY = "PGYW9UlUU1fcQgpAOx9bNYwtGjiKo9zhudSOMxeJ";
    // public static final String ACCESS_KEY = "AKIAIOSFODNN7EXAMPLE";
    // public static final String SECRET_KEY = "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY";

    /**
     * 全局数组
     */
    public final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    public static String buildStringToSign(){

        StringBuffer sb = new StringBuffer("GET");
        sb.append("\n").append("\n").append("\n")
                .append(createDateFormatString())
                // .append("Wed, 28 Mar 2007 01:29:59 +0000")
                .append("\n").append("/");

        return sb.toString();
    }

    public static String createDateFormatString(){

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE',' d MMM yyyy HH:mm:ss '+0000'", Locale.US);
        // 设置时区为 UTC
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String str = sdf.format(c.getTime());

        return str;
    }

    public static void main(String[] args) throws Exception {

        String stringToSign = buildStringToSign();
        String signature = HMACSHA1Util.generateHmac(SECRET_KEY, stringToSign);
        String result = "AWS " + ACCESS_KEY + ":" + signature;

        System.out.println("StringToSign：" + stringToSign);
        System.out.println("HMAC-SHA1 加密：" + signature);
        System.out.println("最终签名：" + result);
    }
}
