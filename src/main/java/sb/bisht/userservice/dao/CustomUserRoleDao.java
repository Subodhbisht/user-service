package sb.bisht.userservice.dao;

import sb.bisht.userservice.Bean.UserResourceBean;

import java.util.List;
import java.util.Map;

public interface CustomUserRoleDao {

	public List<UserResourceBean> findByUser(Map<String, String> mapCriteria);
}
