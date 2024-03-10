//SecurityConfig.java
package capstone.bank_api.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable());

		http.authorizeHttpRequests(auth -> auth
			.requestMatchers("/auth").authenticated()
			.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
			.anyRequest().permitAll());

		http.httpBasic(Customizer.withDefaults());

		return http.build();
	}

}
