package ezenweb.config;

import ezenweb.example.Member;
import ezenweb.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {   // 시큐리티를 커스텀 하는 곳

    @Autowired
    MemberService memberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        // 1. 특정 HTTP 요청에 따른 부여된 권한/상태 확인 후 Path/Resource 제한
        http.authorizeHttpRequests(authorizeRequest->
                authorizeRequest
                        // GET HTTP : 해당 주소에 접근할 때 인증된 회원이면 허가
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/board")).authenticated()
                        // GET HTTP : 해당 주소에 접근할 때 인증된 회원이면서 ROLE이 USER이면 허가 [한개 hasRole 여러개 hasAnyRole]
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/board/write")).hasAnyRole("USER")
                        // GET HTTP : 해당 주소에 접근할 때 인증된 회원이면서 ROLE이 TEAM1이거나 TEAM2 면 허가
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/chat")).hasAnyRole("TEAM1","TEAM2")
                        // GET HTTP : 그외 PATH(/**)는 모두 허가
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/**")).permitAll()
        );

        // 2. 로그인 커스텀
        // http.formLogin(AbstractHttpConfigurer :: disable); // 시큐리티가 제공하는 로그인 폼을 사용안함
        http.formLogin(login ->
                login
                        .loginPage("/member/login") // 로그인 할 VIEW URL 정의
                        .loginProcessingUrl("/member/login/post.do") // 로그인을 처리할 URL 정의
                        .usernameParameter("memail") // 로그인에 사용할 ID 변수명
                        .passwordParameter("mpassword") // 로그인에 사용할 PASSWORD 변수명
//                        .defaultSuccessUrl("/") // 로그인 성공하면 반환될 URL
//                        .failureForwardUrl("/member/login") //로그인 실패하면 반환될 URL
//                        .successHandler(((request, response, authentication) -> { }) // 성공했을 때 로직 HttpServlet
                        .successHandler((request, response, authentication) -> {
                            System.out.println("authentication = " + authentication);
                            response.setContentType("application/json;utf-8");
                            response.getWriter().print("true"); // @ResponseBody 역할
                        })
//                        .failureHandler((request, response, exception) -> { }) // 실패했을 때 로직 HttpServlet
                        .failureHandler((request, response, exception) -> {
                            System.out.println("exception = " + exception.getMessage());
                            response.setContentType("application/json;utf-8");
                            response.getWriter().print("false");
                        })
        );

        // 3. 로그아웃 커스텀
        http.logout(logout->
                logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout/get.do"))
                        .logoutSuccessUrl("/") // 로그아웃 성공시 이동할 URL
                        .invalidateHttpSession(true) // 세션 초기화
        );
        // 4. CSRF(POST, PUT 요청금지) 공격 방지 특정 URL만 POST,PUT이 가능 하도록
        http.csrf(AbstractHttpConfigurer :: disable); // CSRF 사용안함 (개발작업시)
        // 5. 로그인 처리에 필요한 서비스를 등록
        http.userDetailsService(memberService);

        // 6. Oauth2 (소셜 로그인)
        http.oauth2Login(httpSecurityOAuth2LoginConfigurer -> {
          httpSecurityOAuth2LoginConfigurer
                  .loginPage("/member/login") // Oauth2 소셜 로그인할 주소
                  .userInfoEndpoint(userInfoEndpointConfig ->
                      userInfoEndpointConfig.userService(memberService));
        });
        // EndPoint : 종착점
        // Session : (톰캣)HttpServletSession, (JS)SessionStorage, (WS)WebSocketSession

        return http.build();
    }

    // 2. 시큐리티가 패스워드 검증할 때 사용할 암호화 객체
    @Bean // 해당 메소드를 스프링 컨테이너에 등록
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
