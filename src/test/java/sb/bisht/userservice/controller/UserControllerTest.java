package sb.bisht.userservice.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import sb.bisht.userservice.entity.User;
import sb.bisht.userservice.model.UserSearchCriteria;
import sb.bisht.userservice.service.UserService;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Mock
    User user;

    @Autowired
    UserController userController;

    @MockBean
    UserService userService;

    @Test
    public void testGetUsers(){
        UserSearchCriteria u = Mockito.any(UserSearchCriteria.class);
        Flux<User> users = Flux.just(user);
        userController.getUsers(u);
        verify(userService, Mockito.times(1)).findUsers(u);
    }
}
