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
            //비영속
            Member member = new Member();
            member.setId(2L);
            member.setName("A~yoHello");


            //영속
            System.out.println("===before persist====");
            em.persist(member);
            System.out.println("===after persist====");

            //selct문 안나감 위에 persist를 했으므로 db가 아닌 1차캐시에서 가져온다
            Member findMember = em.find(Member.class, 2L);
            System.out.println("findMember ID" + findMember.getName());
            System.out.println("findMember name" + findMember.getName());

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }


        emf.close();
    }
}
