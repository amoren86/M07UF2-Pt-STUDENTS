package cat.institutmarianao.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cat.institutmarianao.domain.User;
import cat.institutmarianao.repository.UserRepository;
import cat.institutmarianao.security.domain.UserPrincipal;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.get(username);
		if (user == null) {
			return null;
		}
		return new UserPrincipal(user);
	}

}
