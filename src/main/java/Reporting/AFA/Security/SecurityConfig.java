package Reporting.AFA.Security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

import javax.sql.DataSource;

import static org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive.COOKIES;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private UserDetailsService userDetailsServiceImpl;

    //@Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }


    /*@Bean
    public UserDetailsService users() {
        // The builder will ensure the passwords are encoded before saving in memory
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails user = users
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        UserDetails admin = users
                .username("admin")
                .password("password")
                .roles("USER", "ADMIN")
                .build();ht
        return new InMemoryUserDetailsManager(user, admin);
    }*/

    @Bean
    public SecurityFilterChain configure (HttpSecurity http)throws Exception{
        http.authorizeHttpRequests(

                authorize ->authorize.requestMatchers("/ipsl/**").permitAll()

                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/registration/**","/forgotPass/**").permitAll()
                        .requestMatchers("/ResetPass/{id}").permitAll()
                        .requestMatchers("/ResetPass/**").permitAll()
                        .requestMatchers("/changePassword/**").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/JS/**").permitAll()
                        .requestMatchers("/**").hasRole("USER")
                        .requestMatchers("/favicon.ico", "/resources/**", "/error").permitAll()
                       .requestMatchers(HttpMethod.GET, "/css**").permitAll()
                        .anyRequest().authenticated()

                );
        http
                .csrf(AbstractHttpConfigurer::disable);



        http
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("username")
                        .defaultSuccessUrl("/index")
                        .permitAll()



                );
        http
                .logout((logout) ->logout
                        .addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(COOKIES)))
                                .deleteCookies("JSESSIONID")
                                        .invalidateHttpSession(false)
                                        .logoutUrl("/ipsl/logout")
                                        .logoutSuccessUrl("/login"));



        http.userDetailsService(userDetailsServiceImpl);

        //http.authorizeRequests().anyRequest().authenticated();
        return http.build();

    }






}
