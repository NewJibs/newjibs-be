package com.ssafy.newjibs.member.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.newjibs.member.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByEmail(String email);

	Boolean existsByEmail(String email);

	@EntityGraph(attributePaths = "authorities")// eager
	Optional<Member> findOneWithAuthoritiesByEmail(String email);

	@Query("SELECT m " +
		"FROM Member m JOIN m.authorities a " +
		"WHERE m.activated = true " +
		"AND a.authorityName = 'ROLE_USER' " +
		"AND NOT EXISTS (" +
		"    SELECT ma " +
		"    FROM Member ma JOIN ma.authorities adm " +
		"    WHERE ma = m AND adm.authorityName = 'ROLE_ADMIN'" +
		") " +
		"GROUP BY m " +
		"HAVING COUNT(m) = 1 " +
		"ORDER BY m.point DESC")
	List<Member> findTop10MembersByPoint();


}
