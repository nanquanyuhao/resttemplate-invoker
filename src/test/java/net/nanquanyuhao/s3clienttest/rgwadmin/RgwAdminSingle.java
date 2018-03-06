package net.nanquanyuhao.s3clienttest.rgwadmin;

import com.google.common.collect.ImmutableMap;
import org.twonote.rgwadmin4j.RgwAdmin;
import org.twonote.rgwadmin4j.RgwAdminBuilder;
import org.twonote.rgwadmin4j.model.BucketInfo;
import org.twonote.rgwadmin4j.model.UsageInfo;
import org.twonote.rgwadmin4j.model.User;

import java.util.List;

/**
 * Created by nanquanyuhao on 2018/3/6.
 */
public class RgwAdminSingle {

    private static final String ACCESS_KEY = "TGEBYEBML0P0FPF1UG7W";
    private static final String SECRET_KEY = "BipJNmBfubD8Um2zOCBbERdR4FPjQcgnkVMNRDIj";
    private static final String ENDPOINT = "http://192.168.235.111/admin";

    private static RgwAdminSingle newInstance = null;

    private RgwAdmin RGW_ADMIN;

    private RgwAdminSingle() {
        RGW_ADMIN =
                new RgwAdminBuilder()
                        .accessKey(ACCESS_KEY)
                        .secretKey(SECRET_KEY)
                        .endpoint(ENDPOINT)
                        .build();

        System.out.println("初始化连接");
    }

    public static RgwAdminSingle getNewInstance(){
        if (newInstance == null) {
            synchronized (RgwAdminSingle.class) {
                if (newInstance == null) {
                    newInstance = new RgwAdminSingle();
                }
            }
        }
        return newInstance;
    }

    /**
     * 获取全部用户
     * @return
     */
    public static List<User> getUsers() {
        return RgwAdminSingle.getNewInstance().RGW_ADMIN.listUserInfo();
    }

    /**
     * 获取单一用户
     * @param userId
     * @return
     */
    public static User getUser(String userId) {
        return RgwAdminSingle.getNewInstance().RGW_ADMIN.getUserInfo(userId).get();
    }

    /**
     * 创建一个用户
     *
     * @param userId
     * @return
     */
    public static User createUser(String userId) {
        return RgwAdminSingle.getNewInstance().RGW_ADMIN.createUser(userId);
    }

    /**
     * 删除一个用户
     * @param userId
     */
    public static void removeUser(String userId) {
        RgwAdminSingle.getNewInstance().RGW_ADMIN.removeUser(userId);
    }

    /**
     * 修改用户最大Bucket数
     * @param userId
     * @param bucketCount
     * @return
     */
    public static User modifyUserBucketCount(String userId, int bucketCount) {
        return RgwAdminSingle.getNewInstance().RGW_ADMIN.modifyUser(userId, ImmutableMap.of("max-buckets", String.valueOf(bucketCount)));
    }

    /**
     * 设置用户的最大限额
     * @param userId 用户ID
     * @param objects 最大对象数目
     * @param usage 最大容量，单位kb
     */
    public static void setUserQuota(String userId, long objects, long usage) {

        RgwAdminSingle.getNewInstance().RGW_ADMIN.setUserQuota(userId, objects, usage);
    }

    /**
     * 给 Bucket 设置新的用户
     * @param bucketName
     * @param newUserId
     */
    public static void transferBucketOwner(String bucketName, String newUserId){
        RgwAdmin ra = RgwAdminSingle.getNewInstance().RGW_ADMIN;
        BucketInfo bucketInfo = ra.getBucketInfo(bucketName).get();
        ra.linkBucket(bucketName, bucketInfo.getId(), newUserId);
    }

    /**
     * 移除 Bucket
     * @param bucketName
     */
    public static void removeBucket(String bucketName){
        RgwAdminSingle.getNewInstance().RGW_ADMIN.removeBucket(bucketName);
    }

    /**
     * 获取用户使用量
     * @param userId
     * @return
     */
    public static UsageInfo getUserUsage(String userId){
        return RgwAdminSingle.getNewInstance().RGW_ADMIN.getUserUsage(userId).get();
    }
}
