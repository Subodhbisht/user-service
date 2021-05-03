package sb.bisht.userservice.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Mono;
import sb.bisht.userservice.entity.User;
import sb.bisht.userservice.exception.UserNotFoundException;
import sb.bisht.userservice.service.UserService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    UserService userService;

    @Test
    public void getUserByUserName_thenStatus200() throws Exception {
        Mono<User> user = Mono.just(Mockito.mock(User.class));
        Mockito.when(userService.getUser(Mockito.anyString())).thenReturn(user);
        mvc.perform(get("/user/subodh"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUserThrowingException_thenStatus400() throws Exception {
        Mono<User> user = Mono.just(Mockito.mock(User.class));
        Mockito.when(userService.getUser(Mockito.anyString())).thenThrow(UserNotFoundException.class);
        mvc.perform(get("/user/subodh"))
                .andExpect(status().isBadRequest());
    }
}
