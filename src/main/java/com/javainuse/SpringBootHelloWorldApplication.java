package com.javainuse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@Controller
public class SpringBootHelloWorldApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHelloWorldApplication.class, args);
	}

	@RequestMapping("/greeting")
	public String home() {
		return "Hi";
	}

	@RequestMapping("/")
	public String index() {
		return "index.html";
	}

	@RequestMapping("/index")
	public String index1() {
		return "index.html";
	}
}

