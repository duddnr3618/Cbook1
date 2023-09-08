package com.mysite.book.member.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.mysite.book.member.constant.Role;
import com.mysite.book.member.dto.MemberDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Member")
public class Member {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	@Column(name = "member_id")
	private Long id;
	
	@Column(unique = true)
	private String name;
	
	private String email;
	
	private String password;
	
	private String address;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	
	public static Member createMember(MemberDto memberDto , PasswordEncoder passwordEncoder) {
		Member member = new Member () ;
		member.setName(memberDto.getName());
		member.setEmail(memberDto.getEmail());
		member.setAddress(memberDto.getAddress());
		member.setRole(Role.USER);
		
		// DB에 저장될 비밀번호 암호화
		String password = passwordEncoder.encode(memberDto.getPassword());
		member.setPassword(password);
		
		return member;
	}
	
}
