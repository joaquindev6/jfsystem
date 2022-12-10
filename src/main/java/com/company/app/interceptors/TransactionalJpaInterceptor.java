package com.company.app.interceptors;

import com.company.app.annotations.TransactionalJpa;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;

@Interceptor
@TransactionalJpa
public class TransactionalJpaInterceptor {

    @Inject
    private EntityManager em;

    //Envuelve el metodo que se esta invocando señalado con la anotacion transactionaljpa
    //La transaccion en JPA - Hibernate es muy importante si no no actualiza ni guarda datos
    @AroundInvoke
    public Object transactional(InvocationContext invocationContext) {
        try {
            em.getTransaction().begin();
            Object object = invocationContext.proceed();
            em.getTransaction().commit();
            return object;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new RuntimeException(ex);
        }
    }
}
