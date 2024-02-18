package it.omsu.config;

import it.omsu.service.UserService;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    UserService userService;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public SecurityFilterChain resourceServerFilterChain(HttpSecurity http)  throws Exception {
        http
                .authorizeHttpRequests(
                        (authorize) -> authorize
                                .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                                .requestMatchers("/**").permitAll()
                                .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(login -> login
                        .loginPage("/login").permitAll());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .csrf()
//                .disable()
//                .authorizeRequests()
//
//                .antMatchers("/registration").not().fullyAuthenticated()
//
//                .antMatchers("/admin/**").hasRole("ADMIN")
//
//                .antMatchers("/", "/resources/**", "/collector/**").permitAll()
//
//                .anyRequest().authenticated()
//                .and()
//
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/collector")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//                .logoutSuccessUrl("/collector");
//    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }
}