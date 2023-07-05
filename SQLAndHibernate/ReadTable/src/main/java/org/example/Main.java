package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry ssr =
                new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata md = new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory sf = md.getSessionFactoryBuilder().build();
        Session session = sf.openSession();

        Course course = session.get(Course.class, 1);
        System.out.println(course.getName());
        System.out.println(course.getStudentsCount());

        sf.close();


    }
}