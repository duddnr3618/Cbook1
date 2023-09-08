package com.mysite.book.member.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysite.book.member.entity.Member;
import com.mysite.book.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

	private final MemberRepository memberRepository;
	
	public Member saveMember (Member member) {
		
		validateDuplicate(member);
		
		return memberRepository.save(member);
		
		
	}

	
	// 중복확인
	private void validateDuplicate(Member member) {
		// TODO Auto-generated method stub
		Member findMember = memberRepository.findByEmail(member.getEmail());
		if(findMember != null) {
			throw new IllegalStateException("이미 등록된 사용자 입니다.");
		}
			
		
		
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Member member = memberRepository.findByEmail(email);
		
		if(member==null) {
			throw new UsernameNotFoundException ("해당사용자가 없습니다." + email );
		}
		
		return User.builder()
								.username(member.getEmail())
								.password(member.getPassword())
								.roles(member.getRole().toString())
								.build();
		
	}
	
}
