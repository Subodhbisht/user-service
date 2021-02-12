package sb.bisht.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sb.bisht.userservice.entity.Role;
import sb.bisht.userservice.service.RoleService;

import java.util.List;

@RestController
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@PostMapping("/saveRole")
	public ResponseEntity<String> saveRole(@RequestBody Role role){
		Role persistedRole;
		persistedRole = roleService.save(role);
		return new ResponseEntity<String>("User with id ="+persistedRole.getRoleId()+" is created.",HttpStatus.OK);
	}
	
	@PutMapping("/updateRole")
	public ResponseEntity<String> updateRole(@RequestBody Role role){
		roleService.update(role);
		return new ResponseEntity<String>("User with id ="+role.getRoleId()+" is updated.",HttpStatus.OK);
	}
	
	@GetMapping("/role/{roleName}")
	public Role getRole(@PathVariable String roleName) {
		return roleService.findByName(roleName);
	}
	
	@GetMapping("/roles")
	public List<Role> fetchRoles(){
		return roleService.findAll();
	}

}
