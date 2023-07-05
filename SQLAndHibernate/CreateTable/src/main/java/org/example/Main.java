package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        //new CreateTable().create();

        try {
            StandardServiceRegistry ssr =
                    new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();
            SessionFactory sf = md.getSessionFactoryBuilder().build();
            Session session = sf.openSession();

            String hql1 = "From " + PurchaseList.class.getSimpleName();
            Query query = session.createQuery(hql1);
            List<PurchaseList> pl = query.list();

            writeLinkedPurchaseList(pl, session);

            sf.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public  static void writeLinkedPurchaseList(List<PurchaseList> pl, Session session) {

        pl.forEach(el -> {
            String hql2 = "From " + Student.class.getSimpleName()
                + String.format(" Where name = '%s'", el.getStudentName());
            List<Student> studentList = session.createQuery(hql2).getResultList();

            String hql3 = "From " + Course.class.getSimpleName()
                    + String.format(" Where name = '%s'", el.getCourseName());
            List<Course> courseList = session.createQuery(hql3).getResultList();

            Transaction transaction = session.beginTransaction();
            LinkedPurchaseList lpl = new LinkedPurchaseList();
            lpl.setId(new Key(studentList.get(0).getId(), courseList.get(0).getId()));
            session.save(lpl);
            transaction.commit();
        });
    }
}