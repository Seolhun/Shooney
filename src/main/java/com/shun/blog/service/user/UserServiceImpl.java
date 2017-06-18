package com.shun.blog.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shun.blog.model.common.CommonState;
import com.shun.blog.model.common.Paging;
import com.shun.blog.model.user.User;
import com.shun.blog.repository.user.UserRepository;

@Service("userService")
@Transactional(transactionManager="txManager")
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;;
	
	@Autowired
	public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository=userRepository;
	}
	
	@Override
	public void insert(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setState(CommonState.ACTIVE.getName());
		userRepository.insert(user);
	}
	
	@Override
	public User selectById(Long id) {
		User user=userRepository.selectById(id);
		return user;
	}
	@Override
	public User selectByEmail(String email) {
		User user = userRepository.selectByEmail(email);
		return user;
	}
	
	@Override
	public User selectByNickname(String nickname) {
		User user = userRepository.selectByNickname(nickname);
		return user;
	}
	
	@Override
	public void update(User user) {
		User dbUser = userRepository.selectById(user.getId());
		if (user != null) {
			if(user.getPassword()!=null && user.getPassword().length()>=4) {
				dbUser.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			if(user.getState()!=null){
				dbUser.setState(user.getState());	
			}
			if(user.getUserProfiles().size()>0){
				dbUser.setUserProfiles(user.getUserProfiles());		
			}
		}
	}
	
	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
	
	@Override
	public void deleteByEmail(String email) {
		userRepository.deleteByEmail(email);
	}
	
	@Override
	public List<User> selectList(Paging paging) {
		return userRepository.selectList(paging);
	}
	
	@Override
	public int getCount(Paging paging) {
		return userRepository.getCount(paging);
	}

	@Override
	//=> Or 일때 하나만이라도 true이면 true이다., And 일때 하나만이라도 false이면 false이다.
	public boolean isUserEmailUnique(String email) {
		User user = selectByEmail(email);
		return (user == null || ((email == null && user.getEmail() != email)));
	}

	@Override
	public boolean isUserNicknameUnique(String nickname) {
		User user = selectByNickname(nickname);
		return (user == null || ((nickname == null && user.getNickname() != nickname )));
	}
}