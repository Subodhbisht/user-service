package sb.bisht.userservice.service;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sb.bisht.userservice.entity.User;
import sb.bisht.userservice.exception.UserNotFoundException;
import sb.bisht.userservice.model.UserSearchCriteria;

import java.util.List;

public interface UserService {

	public Mono<User> save(User user);

	public Mono<User> update(User user);

	public void delete(String userName);
	
	public Flux<User> findUsers(UserSearchCriteria searchCriteria);
	
	public Mono<User> getUser(String userName) throws UserNotFoundException;
	
	
}
