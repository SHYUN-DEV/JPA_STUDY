package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();



        try {
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("A~yoHello");
//            em.persist(member);

//            Member findMember = em.find(Member.class,1L);
//            findMember.setName("YOYOYOYO");
//            System.out.println("findMemberId = " + findMember.getId());
//            System.out.println("findMemberName = " + findMember.getName());

            List<Member> members = em.createQuery("select m from Member as m", Member.class)
                                .getResultList();

            for (Member member : members) {
                System.out.println("Name: " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }


        emf.close();
    }
}
