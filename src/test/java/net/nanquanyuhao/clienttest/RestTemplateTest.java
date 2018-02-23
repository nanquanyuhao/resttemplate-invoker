package net.nanquanyuhao.clienttest;

import net.nanquanyuhao.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by nanquanyuhao on 2018/2/23.
 */
public class RestTemplateTest {

    public static void main(String[] args) {
        RestTemplate template = new RestTemplate();

        User user = template.getForObject("http://localhost:8080/rest/users/1", User.class);

        System.out.println(user);
        System.out.println(user.getId());
        System.out.println(user.getName());

        ResponseEntity<User> re = template.getForEntity("http://localhost:8080/rest/users/1", User.class);

        System.out.println(re.getStatusCode());
        System.out.println(re.getBody());
    }
}
