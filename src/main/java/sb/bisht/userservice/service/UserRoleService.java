package sb.bisht.userservice.service;

import sb.bisht.userservice.Bean.UserResourceBean;
import sb.bisht.userservice.entity.UserRole;
import sb.bisht.userservice.model.UserSearchCriteria;

import java.util.List;

public interface UserRoleService {

	public UserRole save(Long userId, Long roleId);
	
	public List<UserResourceBean> findByUser(UserSearchCriteria searchCriteria);
}
