package net.nanquanyuhao.s3clienttest;

import net.nanquanyuhao.s3clienttest.rgwadmin.RgwAdminSingle;
import org.twonote.rgwadmin4j.model.UsageInfo;
import org.twonote.rgwadmin4j.model.User;
import org.twonote.rgwadmin4j.model.usage.Summary;

import java.util.List;

/**
 * Created by nanquanyuhao on 2018/3/6.
 */
public class RgwAdminClientTest {

    public static void main(String[] args) throws Exception {

        List<User> users = RgwAdminSingle.getUsers();

        User user = RgwAdminSingle.getUser("testuser");

        User tmp = RgwAdminSingle.createUser("tmp");
        User tmp2 = RgwAdminSingle.modifyUserBucketCount("tmp", 123123);
        RgwAdminSingle.setUserQuota("tmp", 123, 87654);
        RgwAdminSingle.transferBucketOwner("my-new-bucket", "testuser");
        // 此处删除 Bucket 不同于 S3 的 JavaSDK ，Bucket 内即使有内容也可以删除
        // RgwAdminSingle.removeBucket("my-new-bucket");
        RgwAdminSingle.removeUser("tmp");

        // show the usage report for a given user
        UsageInfo userUsage = RgwAdminSingle.getUserUsage("testuser");
        // userUsage.getSummary().stream().peek(System.out::println);

        System.out.println(user);
        System.out.println(tmp);
        System.out.println(tmp2);
        System.out.println(userUsage);
        // user.getS3Credentials().stream().peek(n -> System.out.println(n));

        // List<S3Credential> list = user.getS3Credentials();
        // for (S3Credential s : list){
        //     System.out.println(s);
        // }
        // list.forEach(n -> System.out.println(n));
        // list.forEach(System.out::println);
    }
}
