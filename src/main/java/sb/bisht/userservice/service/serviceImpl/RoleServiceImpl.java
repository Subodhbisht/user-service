package sb.bisht.userservice.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sb.bisht.userservice.dao.RoleDao;
import sb.bisht.userservice.entity.Role;
import sb.bisht.userservice.service.RoleService;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleDao roleRepo;
	

	@Override
	public Role save(Role role) {
//		principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		System.out.println("Created By :"+principal.getUserId()+" "+principal.getUserName());
		role.setRecordCount(0L);
		role.setCreatedBy(1L);
		role.setCreatedOn(new Date());
		return roleRepo.save(role);
	}

	@Override
	public Role update(Role role) {
//		principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		role.setRecordCount(role.getRecordCount()+1);
		role.setModifiedBy(1L);
		role.setModifiedOn(new Date());
		return roleRepo.save(role);
	}

	@Override
	public void delete(String roleName) {
		roleRepo.deleteByRoles(roleName);
	}

	@Override
	public Role findByName(String roleName) {
		// TODO Auto-generated method stub
		return roleRepo.findByroles(roleName);
	}

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return roleRepo.findAll();
	}

}
