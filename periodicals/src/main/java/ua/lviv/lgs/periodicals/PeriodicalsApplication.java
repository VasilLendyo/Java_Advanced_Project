package ua.lviv.lgs.periodicals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"ua.lviv.lgs.periodicals"})
public class PeriodicalsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PeriodicalsApplication.class, args);
	}
}