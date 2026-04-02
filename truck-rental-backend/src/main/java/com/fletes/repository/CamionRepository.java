package com.fletes.repository;

import com.fletes.domain.Camion;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CamionRepository {

    @Inject
    EntityManager em;

    public void persist(Camion camion) {
        em.persist(camion);
    }

    public Optional<Camion> findById(Integer id) {
        return Optional.ofNullable(em.find(Camion.class, id));
    }

    public List<Camion> findAllActivos() {
        return em.createQuery("SELECT c FROM Camion c WHERE c.estado = true", Camion.class)
                 .getResultList();
    }

    public List<Camion> findAll() {
        return em.createQuery("SELECT c FROM Camion c", Camion.class)
                 .getResultList();
    }

    public void update(Camion camion) {
        em.merge(camion);
    }
}
