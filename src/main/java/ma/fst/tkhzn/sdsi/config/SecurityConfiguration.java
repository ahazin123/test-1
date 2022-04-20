package ma.fst.tkhzn.sdsi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ma.fst.tkhzn.sdsi.services.CustomUserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserService userService;

	@Autowired
	private JWTTokenHelper jWTTokenHelper;

	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		
//		http.authorizeRequests()
//		.antMatchers("/asset/**","/css/**","/img/**","/js/**","/icons/**").permitAll()
//		.antMatchers("/admin/**").hasAuthority("ADMIN")
//		.antMatchers("/superviseur/**").hasAuthority("SUPERVISEUR")
//		.antMatchers("/developpeur/**").hasAuthority("DEVELOPPEUR")
//		.antMatchers("/reset_password","/message").permitAll()
//		.antMatchers("/forgotpass","/gantt","/07_jquery","/api/tasks","/").permitAll()
//		.anyRequest().authenticated()
//		.and().csrf().disable()
//		.formLogin().loginPage("/login4").usernameParameter("username")
//		.passwordParameter("password")
////		 .successHandler(successHandler)
//		 .permitAll()
//		.and()
//		.logout()
//		.logoutSuccessHandler(new LogoutSuccessHandler() {
//			
//			@Override
//			public void onLogoutSuccess(HttpServletRequest request,
//					HttpServletResponse response,Authentication authentication) throws IOException,ServletException {
//				System.out.println("The user"+authentication.getName() + "has logged out .");
//				
//				UrlPathHelper helper = new UrlPathHelper();
//				String context = helper.getContextPath(request);
//				response.sendRedirect(context +"/login4");
//			}
//		})
//		.permitAll()
//		.and()
//		.rememberMe()
//		    .key("AbcdefghiJKLmnoPqrsut0125847963")
//		    .tokenValiditySeconds(3 * 24 * 60 * 60)
//		.and()
//		.exceptionHandling().accessDeniedPage("/403");
		
		
		
//		auth.inMemoryAuthentication().withUser("Pardeep").password(passwordEncoder().encode("test@123"))
//				.authorities("USER", "ADMIN");
//		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());

//		auth.jdbcAuthentication()
//		.dataSource(dataSource)
//		.usersByUsernameQuery("select login as principal,pwd as credentials,enabled from Utilisateur where login =?")
//		.authoritiesByUsernameQuery("select login as principal,role as enabled from Utilisateur where login=?")
//		.passwordEncoder(new BCryptPasswordEncoder())
//		.rolePrefix("ROLE_");
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
		
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint).and()
				.authorizeRequests((request) -> request.antMatchers("/api/auth/login").permitAll()
					//	.antMatchers("/api/responsable/**").hasAuthority("RESPONSABLE")

						.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated())
				.addFilterBefore(new JWTAuthenticationFilter(userService, jWTTokenHelper),
						UsernamePasswordAuthenticationFilter.class);

		http.csrf().disable().cors().and().headers().frameOptions().disable();

	}

}
