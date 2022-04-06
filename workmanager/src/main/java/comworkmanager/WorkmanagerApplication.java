package comworkmanager;


import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class WorkmanagerApplication {
	
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(WorkmanagerApplication.class, args);
		//openHomePage();
	}
	
	
	
	private static void openHomePage() throws IOException {
	       Runtime rt = Runtime.getRuntime();
	       rt.exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost:8080");
	    }
	
	
}
