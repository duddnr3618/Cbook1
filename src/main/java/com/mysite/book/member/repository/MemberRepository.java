package com.mysite.book.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mysite.book.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{

	Member findByEmail(String email);
	
}
