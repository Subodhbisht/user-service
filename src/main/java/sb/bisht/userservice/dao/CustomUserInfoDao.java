package sb.bisht.userservice.dao;

import sb.bisht.userservice.entity.Role;
import sb.bisht.userservice.entity.User;

import java.util.List;
import java.util.Map;

public interface CustomUserInfoDao {
	
	List<Role> findRoleByUserName(String userName);
	
	public Object delete(String userName);
	
	List<User> findUser(Map<String, String> searchCriteria);
}
