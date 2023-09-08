package com.mysite.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String main () {
		
		System.out.println("인덱스 페이지 성공");
		
		return "index";
	}
	
}
