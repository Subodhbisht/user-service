package sb.bisht.userservice.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sb.bisht.userservice.Bean.UserResourceBean;
import sb.bisht.userservice.dao.UserRoleDao;
import sb.bisht.userservice.entity.Role;
import sb.bisht.userservice.entity.User;
import sb.bisht.userservice.entity.UserRole;
import sb.bisht.userservice.model.UserSearchCriteria;
import sb.bisht.userservice.service.UserRoleService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRoleDao userRoleDao;
	

	@Override
	public UserRole save(Long userId, Long roleId) {
//		System.out.println("User id & Role id are "+userId+" "+roleId);
//		principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserRole userRole = new UserRole();
		User user = new User();
		user.setUserId(userId);
		
		Role role = new Role();
		role.setRoleId(roleId);

		userRole.setUser(user);
		userRole.setRole(role);
		
		userRole.setRecordCount(0L);
		userRole.setCreatedBy(1L);
		userRole.setCreatedOn(new Date());
		// TODO Auto-generated method stub
		return userRoleDao.save(userRole);
	}

	@Override
	public List<UserResourceBean> findByUser(UserSearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		Map<String, String> mapCriteria = new HashMap<String, String>();
		if(searchCriteria.getUserName()!=null) {
			mapCriteria.put("users.userName", searchCriteria.getUserName());
		}
		if(searchCriteria.getFullName()!=null) {
			mapCriteria.put("users.fullName", searchCriteria.getFullName());
		}
		if(searchCriteria.getCountry()!=null) {
			mapCriteria.put("users.country", searchCriteria.getCountry());
		}
		if(searchCriteria.getRole()!=null) {
			mapCriteria.put("role.roles", searchCriteria.getRole());
		}
		return userRoleDao.findByUser(mapCriteria);
	}

}
