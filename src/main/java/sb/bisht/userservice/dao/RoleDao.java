package sb.bisht.userservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sb.bisht.userservice.entity.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Long>{

	public Role findByroles(String role);
	
	public void deleteByRoles(String role);
}
