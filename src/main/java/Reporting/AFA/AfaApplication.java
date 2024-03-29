package Reporting.AFA;
import Reporting.AFA.Security.Services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@EntityScan("Reporting.AFA.Entity")
@ComponentScan(value = {"Reporting.AFA", "Reporting.AFA.Security"})
@SpringBootApplication
public class AfaApplication {
	public static void main(String[] args) {SpringApplication.run(AfaApplication.class, args);
	}
	//@Bean
	//CommandLineRunner commandLineRunnerUserDetails(AccountService accountService){
		//return args -> {


			//accountService.addNewRole("USER");
			//accountService.addNewRole("ADMIN");
			//accountService.addNewUser("philly","alpha","wann","flingo@gmail.com","1234","1234");

			//accountService.addNewUser("user1","issa","faye","ins@gmail.com","1234","1234");
			//accountService.addNewUser("user2","khady","gueye","khg@gmail.com","1234","1234");


	//accountService.addRoleToUser("pan","ADMIN");
		//	accountService.addRoleToUser("pan","USER");
		//	accountService.addRoleToUser("user1","USER");
			//accountService.addRoleToUser("user2","USER");
		 //  };

	//}
	@Bean
	PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}

}