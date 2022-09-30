package com.mita.loan.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class RestSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public static PasswordEncoder getPasswordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/api/**").csrf().disable()
            .authorizeRequests()
                .antMatchers("/api/account/authenticate","/api/account/register").permitAll()
                .antMatchers("/api/account/addCreditor").hasAuthority("Super Admin")
                .antMatchers("/api/loanOffers/add","/api/loanOffers/update","/api/loanOffers/delete").hasAnyAuthority("Super Admin","Creditor")
                .antMatchers("/api/loanOffers/list","api/loanOffers/{loanName}").hasAnyAuthority("Super Admin","Creditor","Debtor")
                .antMatchers("/api/transaction/apply").hasAuthority("Debtor")
                .antMatchers("/api/transaction/reject","/api/transaction/accept").hasAuthority("Creditor")
                .anyRequest().authenticated()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
