package sb.bisht.userservice.service;


import sb.bisht.userservice.entity.Role;
import java.util.List;

public interface RoleService {

	public Role save(Role role);

	public Role update(Role role);

	public void delete(String roleName);
	
	public Role findByName(String roleName);
	
	public List<Role> findAll();
}
