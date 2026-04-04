package com.fletes.repository;

import java.util.List;
import java.util.Optional;

import com.fletes.domain.Reserva;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ReservaRepository {

    @Inject
    EntityManager em;

    public void persist(Reserva reserva) {
        em.persist(reserva);
    }

    public Optional<Reserva> findById(Integer id) {
        return Optional.ofNullable(em.find(Reserva.class, id));
    }

    public List<Reserva> findAll() {
        return em.createQuery("SELECT r FROM Reserva r", Reserva.class)
                 .getResultList();
    }

    public void update(Reserva reserva) {
        em.merge(reserva);
    }

    public void remove(Reserva reserva) {
        em.remove(reserva);
    }

    public Long count() {
        return em.createQuery("SELECT COUNT(r) FROM Reserva r", Long.class)
                 .getSingleResult();
    }
}