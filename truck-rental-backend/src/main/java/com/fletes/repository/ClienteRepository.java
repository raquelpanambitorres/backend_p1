package com.fletes.repository;

import com.fletes.domain.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClienteRepository {

    @Inject
    EntityManager em;

    public void persist(Cliente cliente) {
        em.persist(cliente);
    }

    public Optional<Cliente> findById(Integer id) {
        return Optional.ofNullable(em.find(Cliente.class, id));
    }

    public List<Cliente> findAll() {
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class)
                 .getResultList();
    }

    public void update(Cliente cliente) {
        em.merge(cliente);
    }

    public void remove(Cliente cliente) {
      em.remove(cliente);
    }
}
