package Last_Project.membership.controller;

import Last_Project.membership.config.auth.PrincipalDetails;
import Last_Project.membership.model.User;
import Last_Project.membership.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/test/login")
    public @ResponseBody String Testlogin(
            Authentication authentication,
            @AuthenticationPrincipal PrincipalDetails userDetails) { //DI(의존성 주입)
        System.out.println("/test/login ====================");
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("authentication"+principalDetails.getUser());

        System.out.println("userDetails :" + userDetails.getUser());
        return "세션 정보 확인하기";
    }

    @GetMapping("/test/oauth/login")
    public @ResponseBody String TestOAuthlogin(
            Authentication authentication,
            @AuthenticationPrincipal OAuth2User oauth) { //DI(의존성 주입)
        System.out.println("/test/oauth/login ====================");
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("authentication" + oAuth2User.getAttributes());
        System.out.println("oauth2User" + oauth.getAttributes());

        return "OAuth세션 정보 확인하기";
    }
    @GetMapping("/")
    public String index() {

        return "member_Page";
    }

    //OAuth 로그인을 해도 PrincipalDetails
    //일반 로그인을 해도 PrincipalDetails
    @GetMapping("/user")
    public String user( @AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("principalDetails :" + principalDetails.getUser());
        return "userInfo";
    }

    @GetMapping("/admin")
    public String admin() {
        return "adminPage";
    }

    // SecurityConfig 파일 생성 후 작동 안함
    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @GetMapping("/mainForm")
    public String index(User user) {
        return "mainForm";
    }

    @GetMapping("/logoutForm")
    public String main(User user, Model model){
        model.addAttribute("message","로그아웃 되었습니다.");
        model.addAttribute("searchUrl", "/mainForm");
        return "message";
    }

    @PostMapping("/logout")
    public void logout(User user, Model model, HttpSession session) throws Exception{
    }


    @PostMapping("/join")
    public String join(User user, Model model) {
        System.out.println(user);
        user.setRole("ROLE_USER");
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user); //회원가입 굿. 비밀번호:1234 -> 시큐리티로 로그인 할 수 없다. 이유는 패스워드가 암호화가 안되어서.
        model.addAttribute("message","회원가입이 되었습니다.");
        model.addAttribute("searchUrl", "/loginForm");
        return "message";
    }


    //회원관리 페이지 만들기 최적화!!
    @Secured("ROLE_ADMIN") //어드민 권한이 있는 사람만 가능
    @GetMapping("/info")
    public @ResponseBody String info(){
        return "개인정보";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')") //여러개 권한 넣을 때 "hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')" 이런식으로 하면됨
    @GetMapping("/data")
    public @ResponseBody String data(){
        return "데이터 정보";
    }
}
