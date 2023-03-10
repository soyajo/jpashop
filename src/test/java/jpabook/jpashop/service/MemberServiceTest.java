package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // junit 을 실행할 때 spring 이랑 같이 실행하는 설정
@SpringBootTest // springboot 띄워놔야 autowired
@Transactional // test transactional 은 자동으로 test 가 end 되면 rollback 함.
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
//    @Rollback(value = false) // insert query 나감
    public void 회원가입() throws Exception {
        // given - 설정
        Member member = new Member();
        member.setName("kim");

        // when - 서비스 로직 실행
        long saveId = memberService.join(member);

        // then - 결과

//        em.flush(); // insert query는 로그에 있는데 rollback 됨.
        assertEquals(member, memberRepository.findOne(saveId));

    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        // given
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        // when
        memberService.join(member1);
        memberService.join(member2); // 예외가 발생해야한다.


        // then
        fail("예외가 발생해야 한다.");

    }
}
