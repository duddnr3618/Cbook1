package com.mysite.book.member.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.book.member.dto.MemberDto;
import com.mysite.book.member.entity.Member;
import com.mysite.book.member.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;
	
	private final PasswordEncoder passwordEncoder;

	//회원가입 폼
	@GetMapping("/new")
	public String memberForm (Model model) {
		
		model.addAttribute("memberFormDto" , new MemberDto());
		
		return "member/memberForm";
	}
	
	//회원가입 처리 및 오류처리
	@PostMapping("/new")
	public String memberForm(@Valid MemberDto memberDto,
			BindingResult bindingResult , Model model) {
		
		// 로그인 오류 검사
		if (bindingResult.hasErrors()) {
			return "member/memberForm";
		}
		
		try {
			Member member = Member.createMember(memberDto, passwordEncoder);
			memberService.saveMember(member);
		} catch(IllegalStateException e) {
			model.addAttribute("errorMessage" ,e.getMessage());
			return "member/memberForm";
		}
		// 콘솔에 출력
		//log.info("-----------------> post 성공" + memberFormDto);
		return "redirect:/";
	}
	
	//로그인 폼
	@GetMapping("/login")
	public String login () {
		
		return "member/memberLogin";
	}
	
	// 로그인 에러 폼
	@GetMapping("login/error")
	public String loginError(Model model) {
		System.out.println(" >>>>>> 로그인 실패 콘솔 확인");
		model.addAttribute("loginErrorMsg" , "아이디 혹은 패스워드가 잘못되었습니다.");
		
		return "member/memberLogin";

	}
	
	
	
		
}
