package com.shun.blog.controller.user;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shun.blog.model.common.AjaxResult;
import com.shun.blog.model.common.CommonState;
import com.shun.blog.model.common.Paging;
import com.shun.blog.model.user.User;
import com.shun.blog.model.user.UserProfile;
import com.shun.blog.model.user.UserProfileType;
import com.shun.blog.service.common.CommonService;
import com.shun.blog.service.user.UserService;

@Controller
@RequestMapping(value = "/admin/user")
public class AdminUserController {
	private UserService userService;
	private CommonService commonService;
	private MessageSource messageSource;
	
	@Autowired
	public AdminUserController(UserService userService, CommonService commonService, MessageSource messageSource) {
		this.userService = userService;
		this.commonService=commonService;
		this.messageSource=messageSource;
	}

	private static final Logger LOG = LoggerFactory.getLogger(AdminUserController.class);

	/**
	 * 유저 리스트
	 * 
	 * @param -
	 * @return String - view
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listUsers(ModelMap model, HttpServletRequest request) {
		//paging Data 가져오기.
		Paging paging=commonService.beforePagingGetData(request);
		commonService.setAndValidationPaging(paging);
		
		// 전체 게시판 갯수 확인
		int totalCount = userService.getCount(paging);
		paging.setTotalCount(totalCount);
		
		List<User> users = userService.selectList(paging);
		
		model.addAttribute("users", users);
		model.addAttribute("paging", paging);
		model.addAttribute("userProfile", UserProfileType.values());
		model.addAttribute("state", CommonState.values());
		return "user/admin-user-list";
	}

	/**
	 * 유저 수정
	 * 
	 * @param User user
	 * @return String - view
	 * @throws Exception
	 */
	@RequestMapping(value = { "/modify/{email}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String email, ModelMap model) {
		User user = userService.selectByEmail(email);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		return "user/signup";
	}

	/**
	 * 유저 수정
	 * 
	 * @param User user
	 * @return String - view
	 * @throws Exception
	 */
	@RequestMapping(value = { "/modify/{email}" }, method = RequestMethod.POST)
	public String updateUser(User user, ModelMap model, @PathVariable String email, Principal principal, HttpServletRequest request, BindingResult result) throws Exception{
		String mapping = "user/signup";
		model.addAttribute("edit", true);
		if(commonService.validPattern(user.getEmail(), "email")){
			commonService.validCheckAndSendError(messageSource, result, request, user.getEmail(), "user", "email", "INVALID-EMAIL");
			return mapping;
		} else if(commonService.validPattern(user.getNickname(), "name")){
			commonService.validCheckAndSendError(messageSource, result, request, user.getNickname(), "user", "nickname", "INVALID-NAME");
			return mapping;
		} else if (result.hasErrors()) {
			return mapping;
		}
		
		userService.update(user);
		model.addAttribute("success", "User " + user.getNickname() + " updated successfully");
		model.addAttribute("loggedinuser", principal.getName());
		return "result/success";
	}
	
	/**
	 * 단일 선택 유저 권한 업데이트.
	 * 
	 * @param User user
	 * @return String - view
	 * @throws Exception
	 */
	@RequestMapping(value = { "/update/role/{email}" }, method = RequestMethod.GET)
	public String updateRoleUser(@PathVariable String email, @RequestParam(required=true) String type) {
		User user=userService.selectByEmail(email);
		for(UserProfileType userProfileType : UserProfileType.values()){
			if(type.equals(userProfileType.getType())){
				user.setState(userProfileType.getType());
			}
		}
		userService.update(user);
		// userService.deleteUserByEmail(email);
		return "redirect:/admin/user/list";
	}
	
	/**
	 * 단일 선택 유저 상태 업데이트.
	 * 
	 * @param User user
	 * @return String - view
	 * @throws Exception
	 */
	@RequestMapping(value = { "/update/state/{email}" }, method = RequestMethod.GET)
	public String updateStateUser(@PathVariable String email, @RequestParam(required=true) String type) {
		User user=userService.selectByEmail(email);
		for(CommonState state : CommonState.values()){
			if(type.equals(state.getName())){
				user.setState(state.getName());
			}
		}
		userService.update(user);
		// userService.deleteUserByEmail(email);
		return "redirect:/admin/user/list";
	}
	
	/**
	 * 체크된 모든 유저 업데이트.
	 * 
	 * @param String boardType
	 * @return AjaxResult
	 * @throws Exception
	 */
	@RequestMapping(value = { "/all/update" }, method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult allWork(HttpServletRequest request, HttpServletResponse response, AjaxResult ajaxResult) {
		// userService.deleteUserByEmail(email);
		String key = "";
		String roleType = "";
		String stateType = "";
		try {
			key = request.getParameter("key");
			stateType = request.getParameter("stateType").trim();
			roleType = request.getParameter("roleType").trim();
		} catch (NullPointerException e) {
			LOG.info("NullPoint Error : Ajax Function");
		}
		
		String[] keys = key.split(",");
		for (int i = 0; i < keys.length; i++) {
			try {
				Long id = commonService.checkVDLong(keys[i], 0);
				User user = new User();
				user.setId(id);
				
				//유저 상태 부여				
				if (stateType != "") {
					for (CommonState commonState : CommonState.values()) {
						if (stateType.equals(commonState.getName())) {
							user.setState(commonState.getName());
						}
					}
				}
				
				//유저 권한 부여
				if (roleType != "") {
					for (UserProfileType userProfileType : UserProfileType.values()) {
						if (roleType.equals(userProfileType.getName())) {
							Set<UserProfile> upSet = new HashSet<>();
							UserProfile userProfile = new UserProfile();
							
							userProfile.setId(userProfileType.ordinal() + 1);
							userProfile.setType(userProfileType.getName());
							upSet.add(userProfile);
							user.setUserProfiles(upSet);
						}
					}
				}
				
				userService.update(user);
			} catch (Exception e) {
				LOG.error("error : Admin User all update Error");
				e.printStackTrace();
				ajaxResult.setResult("fail");
				return ajaxResult;
			}
		}
		
		ajaxResult.setResult("success");
		return ajaxResult;
	}
}