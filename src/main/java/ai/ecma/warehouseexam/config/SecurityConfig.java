package ai.ecma.warehouseexam.config;

import ai.ecma.warehouseexam.security.JwtFilter;
import ai.ecma.warehouseexam.service.interfaces.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final JwtFilter jwtFilter;


    public SecurityConfig(@Lazy PasswordEncoder passwordEncoder,
                          @Lazy AuthService authService,
                          @Lazy JwtFilter jwtFilter) {
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
        this.jwtFilter = jwtFilter;


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/api/auth/**").permitAll()
                .anyRequest()
                .authenticated()

        ;

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);



        ;
//        http.addFilterBefore(jwtFIlter)
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder
                .userDetailsService(authService)
                .passwordEncoder(passwordEncoder);
        ;
    }



//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//
//                .inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder.encode("root123"))
//                .roles("ADMIN")    //.authorities("DELETE_ARTICLE","ADD_ARTICLE","VIEW_ARTICLE","EDIT_ARTICLE")
//                .and()
//                .withUser("manager").password(passwordEncoder.encode("root1234"))
//                .roles("MANAGER")    //.authorities("ADD_ARTICLE","VIEW_ARTICLE")
//                .and()
//                .withUser("user").password(passwordEncoder.encode("root12"))
//                .roles("USER");    //.authorities("VIEW_ARTICLE","ADD_ARTICLE");
//    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

