package pl.jdomanski.k47.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	// == fields ==
	private final UserRepository userRepository;

	// == constructors ==
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	// == public methods ==
	public User findUserByUsername(String username) {
		 return userRepository.findById(username).orElse(null);
	}
}
