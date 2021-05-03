package sb.bisht.userservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import sb.bisht.userservice.controller.UserControllerTest;
import sb.bisht.userservice.controller.UserRestControllerTest;
import sb.bisht.userservice.service.UserServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		UserServiceTest.class,
		UserControllerTest.class,
		UserRestControllerTest.class
})
public class UserServiceApplicationTests {

	@Test
	void contextLoads() {

	}

}
