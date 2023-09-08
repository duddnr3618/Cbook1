package com.mysite.book.member.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
	
	@NotBlank(message = "이름을 입력해주세요")
	private String name;
	
	@NotEmpty(message = "이메일을 입력해주세요")
	private String email;
	
	@NotEmpty(message = "비밀번호를 입력해주세요")
	@Length(min = 4 , max = 12 , message = "비밀번호는 4~12사이의 값을 입력해주세요")
	private String password;
	
	@NotEmpty(message = "주소를 입력해주세요")
	private String address;
	

}
