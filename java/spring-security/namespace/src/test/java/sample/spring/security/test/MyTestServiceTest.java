package sample.spring.security.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-applicationContext.xml")
public class MyTestServiceTest {
    
    @Test
    @WithMockUser
    public void test() throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String message = "class = " + auth.getClass() + "\n" +
                         "name = " + auth.getName() + "\n" +
                         "credentials = " + auth.getCredentials() + "\n" +
                         "authorities = " + auth.getAuthorities() + "\n" +
                         "principal = " + auth.getPrincipal() + "\n" +
                         "details = " + auth.getDetails();

        System.out.println(message);
    }
}