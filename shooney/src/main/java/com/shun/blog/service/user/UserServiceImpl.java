package com.shun.blog.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.dao.user.UserRepository;
import com.shun.blog.model.common.Paging;
import com.shun.blog.model.user.State;
import com.shun.blog.model.user.User;

@Service("userService")
@Transactional(propagation=Propagation.REQUIRED, transactionManager="txManager", noRollbackFor={NullPointerException.class})
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public User findById(int id) {
		return userRepository.findById(id);
	}
	@Override
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return user;
	}
	
	@Override
	public User findByNickname(String nickname) {
		User user = userRepository.findByNickname(nickname);
		return user;
	}
	
	@Override
	public void insert(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setState(State.ACTIVE.getState());
		userRepository.save(user);
	}
	
	@Override
	public void update(User user) {
		User entity = userRepository.findByEmail(user.getEmail());
		if (entity != null) {
			if(user.getPassword()!=null) {
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
		} 
		entity.setState(user.getState());
		entity.setUserProfiles(user.getUserProfiles());
	}
	
	@Override
	public void deleteByEmail(String email) {
		userRepository.deleteByEmail(email);
	}
	
	@Override
	public List<User> findAllUsers(Paging paging) {
		return userRepository.findAllUsers(paging);
	}
	
	@Override
	public int getCount(Paging paging) {
		return userRepository.getCount(paging);
	}

	@Override
	//=> Or 일때 하나만이라도 true이면 true이다., And 일때 하나만이라도 false이면 false이다.
	public boolean isUserEmailUnique(String email) {
		User user = findByEmail(email);
		return (user == null || ((email == null && user.getEmail() != email)));
	}

	@Override
	public boolean isUserNicknameUnique(String nickname) {
		User user = findByNickname(nickname);
		return (user == null || ((nickname == null && user.getNickname() != nickname )));
	}
}