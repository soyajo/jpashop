package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor // final인 애들을 생성자 안에 주입시킨다 , 최신버전이면 @Autowired 를 사용하지 않아도 빈 주입이 된다.
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     * @param member
     * @return
     */
    @Transactional
    public long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //exception
        // name을 uk로 잡아놓는게 좋다. 디비 멀티쓰레드 관점 이슈 문제
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     * @return
     */
    @Transactional(readOnly = true) // 읽기 전용으로 만듬, 성능 좋아짐.
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }


    /**
     * 회원 단일 조회
     * @param memberId
     * @return
     */
    @Transactional(readOnly = true) // 읽기 전용으로 만듬, 성능 좋아짐.
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
}
