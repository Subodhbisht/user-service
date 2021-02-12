package sb.bisht.userservice.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import sb.bisht.userservice.entity.User;

public interface UserInfoDao extends JpaRepository<User, Long>, CustomUserInfoDao{
	
	public User findByUserName(String userName);
}
