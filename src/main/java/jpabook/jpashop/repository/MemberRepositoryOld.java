package jpabook.jpashop.repository;

import javax.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor // final인 애들을 생성자 안에 주입시킨다 , 최신버전이면 @Autowired 를 사용하지 않아도 빈 주입이 된다.
public class MemberRepositoryOld {

    // @PersistenceContext -> 스프링 부트가 붙이면 @Autowired 로 인젝션 가능 -> 이러면 @RequiredArgsConstructor 사용해서 코드 단축 가능
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
