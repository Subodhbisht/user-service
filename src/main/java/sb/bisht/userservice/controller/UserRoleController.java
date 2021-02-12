package sb.bisht.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sb.bisht.userservice.Bean.UserResourceBean;
import sb.bisht.userservice.entity.UserRole;
import sb.bisht.userservice.model.UserRoleModel;
import sb.bisht.userservice.model.UserSearchCriteria;
import sb.bisht.userservice.service.UserRoleService;

import java.util.List;

@RestController
public class UserRoleController {

	@Autowired
	UserRoleService userRoleService;
	
	@PostMapping("/saveUserRole")
	public UserRole save(@RequestBody UserRoleModel model) {
		return userRoleService.save(model.getUserId(),model.getRoleId());
	}
	
	@GetMapping("/userRoles")
	public List<UserResourceBean> getUserRoles(@RequestBody UserSearchCriteria criteria){
		return userRoleService.findByUser(criteria);
	}
}
