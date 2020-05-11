package se.rsv.ocp.balthazar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private EntityManager entityManager;


    static class TmpRole implements GrantedAuthority {
        private String role;

        public TmpRole(String role) {
            this.role = role;
        }

        @Override public String getAuthority() {
            return role;
        }
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        Collection<UserDetails> userDetailsCollection = new ArrayList<>();
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        userDetailsCollection.add(User.withUsername("system").password(encoder.encode("wlssystem"))
                .authorities(Arrays.asList(new TmpRole("ADMIN"), new TmpRole("USER")))
                .build());

        userDetailsCollection.add(User.withUsername("user").password(encoder.encode("vinter"))
                .authorities(Arrays.asList(new TmpRole("USER")))
                .build());

        return new InMemoryUserDetailsManager(userDetailsCollection);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * This gets called instead of bases security.
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*
        http
         .authorizeRequests()
         .anyRequest().authenticated()
           .and()
           .formLogin().and()
         .httpBasic();

        http.authorizeRequests()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .anyRequest().fullyAuthenticated()
        .and()
        .formLogin().loginPage("/login").failureUrl("/login?error").permitAll()
        .and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
        */

        http.
                csrf().disable()
                .cors().and()
                .authorizeRequests()
                .antMatchers("*/h2-console_ll/**").hasRole("ADMIN");

        // För att få /h2-console att fungera
        http.headers().frameOptions().sameOrigin();
    }

}
