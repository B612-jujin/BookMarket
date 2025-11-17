package kr.ac.kopo.cjj.bookmarket.serveice;

import jakarta.transaction.Transactional;
import kr.ac.kopo.cjj.bookmarket.domain.Member;
import kr.ac.kopo.cjj.bookmarket.repository.MemberRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    // 회원 정보 저장
    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    //특정 memberId를 가진 Member Entity만 반환
    public Member getMemberByMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId);
    }

//  Member Entity (회원 정보) 삭제
    public void deleteMember(String memberId) {
        Member member = memberRepository.findByMemberId(memberId)
        memberRepository.deleteById(member.getNum());
    }

//  memberId 중복 검사
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByMemberId(member.getMemberId());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(memberId);
        if (member == null) {
            throw new UsernameNotFoundException(memberId);
        }
        UserDetails userDetails = User.builder()
                .username(member.getMemberId())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
        return userDetails;

    }
}
