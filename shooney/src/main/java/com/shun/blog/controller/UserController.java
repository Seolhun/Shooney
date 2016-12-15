package com.shun.blog.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.shun.blog.controller.common.CommonFn;
import com.shun.blog.model.common.Paging;
import com.shun.blog.model.user.State;
import com.shun.blog.model.user.User;
import com.shun.blog.model.user.UserProfile;
import com.shun.blog.model.user.UserProfileType;
import com.shun.blog.service.user.UserProfileService;
import com.shun.blog.service.user.UserService;

@Controller
@RequestMapping("/")
@SessionAttributes({"roles"})
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@Autowired
	CommonFn cFn;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	// final private String emailPattern =
	// "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,3})$";
	// final private String idPattern = "^[A-Za-z0-9].{1,9}";
	// final private String pwdPattern =
	// "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+=-~`]).{8,20})";
	// final private String namePattern = ".[가-힣]{1,14}";
	// final private String phonePattern = "\\d{10,11}";

	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public String listUsers(ModelMap model, HttpServletRequest request) {
		// 파라미터 호출 및 유효성 검사
		int cPage = cFn.checkVDInt(request.getParameter("cp"), 1);
		int sType = cFn.checkVDInt(request.getParameter("sty"), 0);
		String question = cFn.checkVDQuestion(request.getParameter("sty"));
		String sDate = cFn.checkVDQuestion(request.getParameter("sda"));
		int limit = cFn.checkVDInt(request.getParameter("li"), 20);
		Paging paging = new Paging(cPage, sType, question, sDate, limit);

		// 전체 게시판 갯수 확인
		int totalCount = userService.getCount(paging);
		paging.setTotalCount(totalCount);
		cFn.setPaging(paging);
		List<User> users = userService.findAllUsers(paging);

		model.addAttribute("users", users);
		model.addAttribute("paging", paging);
		model.addAttribute("loggedinuser", getPrincipal());
		return "user/userlist";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(ModelMap model, HttpServletResponse res) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("role", UserProfileType.values());
		return "user/signup";
	}

	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	public String signupDo(@Valid User user, BindingResult result, ModelMap model) {
		logger.info("Request POST : Parameter = " + user);
		String mapping = "user/signup";
		if (result.hasErrors()) {
			return mapping;
		}

		// 이메일, 닉네임 유니크 비교 메소드
		if (!userService.isUserEmailUnique(user.getEmail())) {
			FieldError emailError = new FieldError("user", "email", messageSource.getMessage("non.unique.email",
					new String[] { user.getEmail() }, Locale.getDefault()));
			result.addError(emailError);
			return mapping;
		}

		if (!userService.isUserNicknameUnique(user.getNickname())) {
			FieldError nicknameError = new FieldError("user", "nickname", messageSource
					.getMessage("non.unique.nickname", new String[] { user.getNickname() }, Locale.getDefault()));
			result.addError(nicknameError);
			return mapping;
		}

		// 유저 권한 넣기(프론트에서 값을 받지 않기때문에 백엔드에서 넣어준다.)
		Set<UserProfile> upSet = new HashSet<>();
		UserProfile up = new UserProfile();
		up.setId(UserProfileType.PLAYER.ordinal()+1);
		up.setType(UserProfileType.PLAYER.getType());
		upSet.add(up);
		user.setUserProfiles(upSet);

		userService.saveUser(user);

		model.addAttribute("success", "User " + user.getEmail() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "result/success";
	}

	@RequestMapping(value = { "/admin/edit-{email}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String email, ModelMap model) {
		User user = userService.findByEmail(email);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		return "user/signup";
	}

	@RequestMapping(value = { "/admin/edit-{email}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String email) {
		logger.info("Request POST : Parameter = " + user);
		if (result.hasErrors()) {
			model.addAttribute("edit", true);
			return "user/signup";
		}

		userService.updateUser(user);

		model.addAttribute("success", "User " + user.getNickname() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "result/success";
	}

	@RequestMapping(value = { "/admin/up-{email}" }, method = RequestMethod.GET)
	public String updateUser(@PathVariable String email, @RequestParam String s) {
		// userService.deleteUserByEmail(email);
		User user=userService.findByEmail(email);
		for(State e : State.values()){
			if(s.equals(e.getState().substring(0, 1).toLowerCase())){
				user.setState(e.getState());
			}
		}
		userService.updateUser(user);
		
		// userService.deleteUserByEmail(email);
		return "redirect:/admin/list";
	}
	
	@RequestMapping(value = { "/admin/allup/{type}" }, method = RequestMethod.GET)
	public String allWork(@PathVariable String type, @RequestParam String key) {
		// userService.deleteUserByEmail(email);
		String[] keys=key.split(",");
		for(int i=0;i<keys.length;i++){
			try {
				int id=cFn.checkVDInt(keys[i], 0);
				User user=userService.findById(id);
				for(State e : State.values()){
					if(type.equals(e.getState().substring(0, 1).toLowerCase())){
						user.setState(e.getState());
						userService.updateUser(user);
					}	
				}
			} catch (Exception e) {
				logger.error("ERROR : Admin user Error");
			}
		}
		
		// userService.deleteUserByEmail(email);
		return "redirect:/admin/list";
	}

	// 선언하면 모델값으로 쉽게 넘길 수 있음
	@ModelAttribute("roles")
	public UserProfileType[] initializeProfiles() {
		return UserProfileType.values();
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		return "fail";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {
			return "user/login";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			// new SecurityContextLogoutHandler().logout(request, response,
			// auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}
}