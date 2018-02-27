package net.nanquanyuhao.clienttest;

import net.nanquanyuhao.domain.User;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by nanquanyuhao on 2018/2/23.
 */
public class SpringRestTestClient {

    public static final String REST_SERVICE_URI = "http://localhost:8080/rest";

    /* GET */
    private static void listAllUsers(){
        System.out.println("Testing listAllUsers API-----------");
        RestTemplate restTemplate = new RestTemplate();

        // 原始代码中的使用方式
        // List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/users/", List.class);
        User[] usersArray = restTemplate.getForObject(REST_SERVICE_URI+"/users", User[].class);
        List<User> usersMap = Arrays.asList(usersArray);

        if(usersMap != null){
            for(User map : usersMap){
                System.out.println("User : id="+ map.getId() + ", Name=" + map.getName());
            }
        } else {
            System.out.println("No user exist----------");
        }
    }

    /* GET */
    private static void getUser() {
        System.out.println("Testing getUser API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(REST_SERVICE_URI+"/users/1", User.class);
        System.out.println(user);
    }

    /* POST */
    private static void createUser() {
        System.out.println("Testing create User API----------");

        RestTemplate restTemplate = new RestTemplate();
        User user = new User(11,"Kenan 9876543");
        restTemplate.postForObject(REST_SERVICE_URI+"/users", user, User.class);
        System.out.println(user);
    }

    /* PUT */
    private static void updateUser() {
        System.out.println("Testing update User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = new User(11,"nanquanyuhao");
        restTemplate.put(REST_SERVICE_URI + "/users/11", user);
        System.out.println(user);
    }

    /* DELETE */
    private static void deleteUser() {
        System.out.println("Testing delete User API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/users/11");
    }

    /**
     * 调用测试主函数
     *
     * @param args
     */
    public static void main(String args[]){
        listAllUsers();
        getUser();
        createUser();
        listAllUsers();
        updateUser();
        listAllUsers();
        deleteUser();
        listAllUsers();
    }
}
