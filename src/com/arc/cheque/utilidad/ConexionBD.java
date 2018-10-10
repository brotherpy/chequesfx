/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arc.cheque.utilidad;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author Jorge Fabio
 */
public class ConexionBD {

    private static SessionFactory sessionFactory;

    public static void setUp() {
        final StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        try {
            sessionFactory = new MetadataSources(serviceRegistry)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
