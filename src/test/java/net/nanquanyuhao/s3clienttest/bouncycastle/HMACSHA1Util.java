package net.nanquanyuhao.s3clienttest.bouncycastle;

import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Base64;

/**
 * Created by nanquanyuhao on 2018/2/27.
 */
public class HMACSHA1Util {

    public static final String ENCODING = "UTF-8";

    public static String generateHmac(String key, String message) throws Exception {

        byte[] keyBytes = key.getBytes(ENCODING);
        byte[] data = message.getBytes(ENCODING);

        HMac macProvider = new HMac(new SHA1Digest());
        macProvider.init(new KeyParameter(keyBytes));
        macProvider.reset();

        macProvider.update(data, 0, data.length);
        byte[] output = new byte[macProvider.getMacSize()];
        macProvider.doFinal(output, 0);

        byte[] hmac = Base64.encode(output);
        return new String(hmac).replaceAll("\r\n", "");
    }
}
