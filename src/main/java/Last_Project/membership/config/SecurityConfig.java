package Last_Project.membership.config;

import Last_Project.membership.config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//1. 코드 받기(인증) 2.엑세스 토큰(권한)
//3. 사용자 프로필 정보 가져오고
//4-1 정보를 토대로 회원가입을 자동으로 진행 시켜야함
//4-2 (이메일, 전화번호, 이름, 아이디,) 쇼핑몰 -> (집주소), 백화점몰 -> (vip등급, 일반등급)

@Configuration
@EnableWebSecurity  //스프링 시큐리티 필터가 스프링 필터체인에 등록됌
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) //secured 어노테이션 활성화, preAuthorize 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;


    //해당 메소드의 리턴되는 오브젝트를 IOC로 등록해줌.
    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/user/**").authenticated() //인증만 되면 들어갈 수 있는 주소!!
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login") // /login 주소가 호출되면 시큐리티가 낚아채서 대신 로그인 진행
                .defaultSuccessUrl("/")
                .and()
                .oauth2Login()
                .loginPage("/loginForm")
                .userInfoEndpoint()
                .userService(principalOauth2UserService);
                //구글 로그인이 완료된 뒤 후처리 필요. Tip 코드X (액세스토큰 + 사용자 프로필정보 O)

    }
}
