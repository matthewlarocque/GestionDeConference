
package com.meeting.manager.service;

import com.meeting.manager.model.User;
import com.meeting.manager.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	JdbcTemplate	jdbcTemplate;

	@Autowired
    UserRepository userRepository;

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> user=userRepository.findByUsernameAndStatus(username,"1");
		if(user.size()==0){
			return null;
		}
		return user.get(0);
	}
	public User checkUserByUsername(String username) throws UsernameNotFoundException {
		List<User> user=userRepository.findByUsername(username);
		if(user.size()==0){
			return null;
		}
		return user.get(0);
	}
}
