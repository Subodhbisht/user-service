package sb.bisht.userservice.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import sb.bisht.userservice.dao.UserInfoDao;
import sb.bisht.userservice.entity.User;
import sb.bisht.userservice.exception.UserNotFoundException;
import sb.bisht.userservice.model.UserSearchCriteria;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    private UserInfoDao userRepository;

    @Captor
    ArgumentCaptor<Map<String, String>> mapCaptor;

    @Test
    public void testFindUser() {
        UserSearchCriteria searchCriteria = new UserSearchCriteria();
        searchCriteria.setCountry("India");
        userService.findUsers(searchCriteria);
        Mockito.verify(userRepository).findUser(mapCaptor.capture());
        Assertions.assertThat(mapCaptor.getValue().get("user.country")).isEqualTo("India");
        Assertions.assertThat(mapCaptor.getValue().size()).as("Size must be equals to 1").isEqualTo(1);
    }

    @Test
    public void testGetUserNegativeScenario() {
        Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(null);
        Assertions.assertThatThrownBy(() -> userService.getUser("subodh")).isInstanceOf(UserNotFoundException.class).hasMessage("Invalid User");
    }

    @Test
    public void testGetUserPositiveScenario() throws UserNotFoundException {
        Mockito.when(userRepository.findByUserName(Mockito.anyString())).thenReturn(Mockito.mock(User.class));
        userService.getUser(Mockito.anyString());
        userService.getUser(Mockito.anyString());
        Mockito.verify(userRepository,Mockito.times(2)).findByUserName(Mockito.anyString());
    }

    @Test
    public void testSave(){
        User u = Mockito.mock(User.class);
        Mockito.when(userRepository.save(u)).thenReturn(u);
        User savedUser = userService.save(u).block();
        Assertions.assertThat(savedUser.getRecordCount()).isEqualTo(0);
    }

    @Test
    public void testUpdate(){
        User u = new User();
        u.setRecordCount(0L);
        Mockito.when(userRepository.save(u)).thenReturn(u);
        User savedUser = userService.update(u).block();
        Assertions.assertThat(savedUser.getRecordCount()).isEqualTo(1);
    }
}