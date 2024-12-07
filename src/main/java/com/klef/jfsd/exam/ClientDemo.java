package com.klef.jfsd.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        // Setup Hibernate session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            // Insert Operation
            transaction = session.beginTransaction();
            Department dept = new Department();
            dept.setName("Computer Science");
            dept.setLocation("Building A");
            dept.setHodName("Dr. Smith");
            session.save(dept);
            transaction.commit();
            System.out.println("Department inserted successfully!");

            // Delete Operation using HQL
            transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Department where id = :deptId");
            query.setParameter("deptId", 1); // Replace with an existing Department ID
            int result = query.executeUpdate();
            transaction.commit();
            System.out.println("Number of departments deleted: " + result);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }
    }
}
