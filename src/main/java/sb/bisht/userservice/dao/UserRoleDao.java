package sb.bisht.userservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sb.bisht.userservice.entity.UserRole;

public interface UserRoleDao extends JpaRepository<UserRole, Long>,CustomUserRoleDao {

	
}
