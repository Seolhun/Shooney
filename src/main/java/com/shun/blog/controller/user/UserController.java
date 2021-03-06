package com.shun.blog.controller.user;

import com.shun.blog.model.menu.Menu;
import com.shun.blog.model.user.User;
import com.shun.blog.model.user.UserProfile;
import com.shun.blog.model.user.UserProfileType;
import com.shun.blog.service.common.CommonService;
import com.shun.blog.service.menu.MenuService;
import com.shun.blog.service.user.UserProfileService;
import com.shun.blog.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@SessionAttributes({"roles"})
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private UserService userService;
    private UserProfileService userProfileService;
    private MessageSource messageSource;
    private PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
    private AuthenticationTrustResolver authenticationTrustResolver;
    private CommonService commonService;
    private MenuService menuService;

    @Autowired
    UserController(UserService userService, UserProfileService userProfileService, MessageSource messageSource, PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices,
                   AuthenticationTrustResolver authenticationTrustResolver, CommonService commonService, MenuService menuService) {
        this.userService = userService;
        this.userProfileService = userProfileService;
        this.messageSource = messageSource;
        this.persistentTokenBasedRememberMeServices = persistentTokenBasedRememberMeServices;
        this.authenticationTrustResolver = authenticationTrustResolver;
        this.commonService = commonService;
        this.menuService = menuService;
    }

    /**
     * 회원가입 페이지 이동
     * <p>
     * param -
     * return String  -view
     * throws Exception
     */
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(ModelMap model, Authentication auth) throws Exception {
        model.addAttribute("user", new User());
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", auth.getName());
        model.addAttribute("role", UserProfileType.values());
        return "user/signup";
    }

    /**
     * 회원가입 전송
     * <p>
     * param User user
     * return String  -view
     * throws Exception
     */
    @RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
    public String signupDo(@Valid User user, BindingResult result, ModelMap model, Authentication auth, HttpServletRequest request) throws Exception {
        LOG.info("param {}", user.toString());
        String mapping = "user/signup";

        // 이메일, 닉네임 유니크 비교 메소드
        if (!userService.isUserEmailUnique(user.getEmail())) {
            commonService.validCheckAndSendError(messageSource, result, request, user.getEmail(), "user", "email", "NON-UNIQUE-USER-EMAIL");
            return mapping;
        } else if (!userService.isUserNicknameUnique(user.getNickname())) {
            commonService.validCheckAndSendError(messageSource, result, request, user.getNickname(), "user", "nickname", "NON-UNIQUE-USER-NICKNAME");
            return mapping;
        } else if (commonService.validPattern(user.getEmail(), "email")) {
            commonService.validCheckAndSendError(messageSource, result, request, user.getEmail(), "user", "email", "INVALID-EMAIL");
            return mapping;
        } else if (commonService.validPattern(user.getPassword(), "password")) {
            commonService.validCheckAndSendError(messageSource, result, request, user.getPassword(), "user", "password", "INVALID-PASSWORD");
            return mapping;
        } else if (commonService.validPattern(user.getNickname(), "name")) {
            commonService.validCheckAndSendError(messageSource, result, request, user.getNickname(), "user", "nickname", "INVALID-NAME");
            return mapping;
        } else if (result.hasErrors()) {
            return mapping;
        }

        // 유저 권한 넣기(프론트에서 값을 받지 않기때문에 백엔드에서 넣어준다.)
        Set<UserProfile> upSet = new HashSet<>();
        UserProfile up = new UserProfile();
        up.setId(UserProfileType.GUEST.ordinal() + 1);
        up.setType(UserProfileType.GUEST.getType());
        upSet.add(up);
        user.setUserProfiles(upSet);

        userService.insert(user);
        model.addAttribute("success", "User " + user.getEmail() + " registered successfully");
        model.addAttribute("loggedinuser", auth.getName());
        return "result/success";
    }

    /**
     * 로그인
     * <p>
     * param String error
     * return String  -view
     * throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(required = false) String error, HttpServletRequest request, Model model) {
        Menu menu = commonService.setMenuConfig(request);
        List<Menu> menuList = menuService.findAllMenu(menu, menu.getMenuType());
        model.addAttribute("menuList", menuList);

        if (isCurrentAuthenticationAnonymous()) {
            if (error != null) {
                Exception exception = (Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
                model.addAttribute("errorMsg", getErrorMessage(exception, error));
            }
            return "user/login";
        }

        return "redirect:/";
    }

    /**
     * 로그아웃
     * <p>
     * param -
     * return String  -view
     */
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

    // 선언하면 모델값으로 쉽게 넘길 수 있음

    /**
     * 디비 권한 리스트 가져오기
     * <p>
     * param -
     * return List<UserProfile>
     * throws Exception
     */
    @ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() throws Exception {
        return userProfileService.selectList();
    }

    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

    // 커스텀 된 로그인 에러 메세지
    private String getErrorMessage(Exception exception, String error) {
        String errorMsg = "";
        if (exception instanceof LockedException) {
            //, BindingResult result, HttpServletRequest request, String objectValidValue, String fieldObjectName, String fieldName, String messagePropertyName
//			commonService.validCheckAndSendError(messageSource, result, request, objectValidValue, fieldObjectName, fieldName, messagePropertyName);
            errorMsg = "현재 계정이 잠겼습니다.";
        } else if (exception instanceof DisabledException) {
//			commonService.validCheckAndSendError(messageSource, result, request, objectValidValue, fieldObjectName, fieldName, messagePropertyName);
            errorMsg = "현재 계정이 이용 불가능합니다.";
        } else if (error.equals("anonymousUser")) {
//			commonService.validCheckAndSendError(messageSource, result, request, objectValidValue, fieldObjectName, fieldName, messagePropertyName);
            errorMsg = "로그인이 되어있지 않습니다. 로그인해주세요.";
        } else {
//			commonService.validCheckAndSendError(messageSource, result, request, objectValidValue, fieldObjectName, fieldName, messagePropertyName);
            errorMsg = "계정과 비밀번호를 올바르게 입력해주세요,.";
        }
        return errorMsg;
    }
}