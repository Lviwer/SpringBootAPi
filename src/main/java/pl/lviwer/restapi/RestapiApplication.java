package pl.lviwer.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class RestapiApplication {

	public static void main(String[] args) {
		System.out.println("{bcrypt}" + new BCryptPasswordEncoder().encode("test")); //password for 1 user
		SpringApplication.run(RestapiApplication.class, args);
	}

}
