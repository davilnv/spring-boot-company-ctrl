package br.com.davilnv.companyctrl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CompanyCtrlApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyCtrlApplication.class, args);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {

		return new BCryptPasswordEncoder();

	}

}
