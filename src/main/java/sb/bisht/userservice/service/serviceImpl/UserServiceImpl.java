package sb.bisht.userservice.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sb.bisht.userservice.dao.UserInfoDao;
import sb.bisht.userservice.entity.User;
import sb.bisht.userservice.exception.UserNotFoundException;
import sb.bisht.userservice.model.UserSearchCriteria;
import sb.bisht.userservice.service.UserService;

import javax.transaction.Transactional;
import java.util.*;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserInfoDao userRepository;
	
//	@Autowired
//	private PasswordEncoder encoder;
//
//	private UserPrincipal principal;
	
	@Override
	@Transactional
	public Mono<User> save(User user) {
//		principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		System.out.println("Created By :"+principal.getUser().getUserName());
		user.setRecordCount(0L);
		user.setCreatedBy(1L);
		user.setCreatedOn(new Date());
		return Mono.just(userRepository.save(user));
	}

	@Override
	@Transactional
	public Mono<User> update(User user) {
//		principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		user.setRecordCount(1L);
		user.setModifiedBy(1L);
		user.setModifiedOn(new Date());
		return Mono.just(userRepository.save(user));
	}

	@Override
	public void delete(String userName) {
		// TODO Auto-generated method stub

	}

	@Override
	public Flux<User> findUsers(UserSearchCriteria searchCriteria) {
		
		//userName, fullName, country
		Map<String, String> mapCriteria = new HashMap<String, String>();
		if(searchCriteria.getUserName()!=null) {
			mapCriteria.put("user.userName", searchCriteria.getUserName());
		}
		if(searchCriteria.getFullName()!=null) {
			mapCriteria.put("user.fullName", searchCriteria.getFullName());
		}
		if(searchCriteria.getCountry()!=null) {
			mapCriteria.put("user.country", searchCriteria.getCountry());
		}
		return Flux.fromIterable(userRepository.findUser(mapCriteria));
	}

	@Override
	public Mono<User> getUser(String userName) throws UserNotFoundException {
		User user = userRepository.findByUserName(userName);
		if(user == null){
			throw new UserNotFoundException("Invalid User");
		}
		return Mono.just(user);
	}

	/*@Override
	public UserPrincipal loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println("InsideXXXXXXXXXXXXX "+userName);
		User user = userRepository.findByUserName(userName) ;
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		List<Role> roles = userRepository.findRoleByUserName(userName);
		for (Role role : roles) {
			System.out.println("Role is "+role.getRoles());
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+role.getRoles());
			authorities.add(authority);
		}
		return new UserPrincipal(user, authorities);
	}*/

}
