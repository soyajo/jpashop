package jpabook.jpashop;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {

		Hello hello = new Hello();
		hello.setData("hello");
		System.out.println("hello.getData() = " + hello.getData());
		SpringApplication.run(JpashopApplication.class, args);

	}
}
