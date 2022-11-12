package ca.sheridancollege.perdomod.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig {

        // @Autowired
        // private LoginAccessDeniedHandler accessDeniedHandler;

        @Autowired
        private UserDetailsService userDetailsService;

        @Bean
        public BCryptPasswordEncoder encoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public AccessDeniedHandler accessDeniedHandler() {
                return new LoginAccessDeniedHandler();
        }

        @Bean
        public AuthenticationManager authManager(HttpSecurity http, UserDetailsService userDetailsService)
                        throws Exception {
                return http.getSharedObject(AuthenticationManagerBuilder.class)
                                .userDetailsService(userDetailsService)
                                .passwordEncoder(encoder())
                                .and()
                                .build();
        }

        // Enable JDBC authentication
        // @Autowired
        // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //         auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
        // }

        // @Bean
        // public UserDetailsService userDetailsService() {
        // InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        // manager.createUser(User.withUsername("frank@email.com")
        // .password("{noop}password")
        // .roles("USER")
        // .build());

        // manager.createUser(User.withUsername("guest@email.com")
        // .password("{noop}password")
        // .roles("GUEST")
        // .build());

        // return manager;
        // }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                // TODO: remove these lines when you are done testing
                http.csrf().disable();
                http.headers().frameOptions().disable();

                http.authorizeRequests()
                                .antMatchers("/secure/**").hasRole("USER")
                                .antMatchers("/", "/js/**", "/css/**", "/images/**", "/permission-denied", "/signup", "/addUser").permitAll()
                                // TODO: remove this line when you are done testing
                                .antMatchers("/h2-console/**").permitAll()
                                .antMatchers("/**").denyAll()
                                .anyRequest().authenticated()
                                .and().formLogin()
                                .loginPage("/login").permitAll()
                                // Redirect to the secure page after login
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/", true)
                                .and().logout()
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/login?logout").permitAll()
                                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler());

                return http.build();
        }

}
