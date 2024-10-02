package com.alisonadamus.simple_blog;

import com.alisonadamus.simple_blog.entity.User;
import java.util.UUID;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.alisonadamus.simple_blog.entity")
public class SimpleBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleBlogApplication.class, args);
	}

}
