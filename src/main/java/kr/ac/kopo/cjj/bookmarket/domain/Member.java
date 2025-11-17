package kr.ac.kopo.cjj.bookmarket.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.management.relation.Role;

@Entity
@Table(name="member")
@Getter
@Setter
@NoArgsConstructor
public class Member {
    @Id
    @Column(name="num")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long num;

    @Column(unique=true)
    private String memberId;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;
    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member= new Member();
        member.setMemberId(memberFormDto.getMemberId());
        member.setPassword(passwordEncoder.encode(memberFormDto.getPassword()));
        member.setName(memberFormDto.getName());
        member.setPhone(memberFormDto.getPhone());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        member.setRole(Role.USER);
        return member;
    }
}