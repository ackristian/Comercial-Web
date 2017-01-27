package com.sulasoftwares.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.sulasoftwares.model.Grupo;

public class Grupos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
    private EntityManager manager;

    public Grupo grupoPorId(Long id) {
        return manager.find(Grupo.class, id);
    }

    public Grupo grupoPorNome(String nome) {
        try {
            return manager.createQuery("from Grupo where upper(nome) = :nome", Grupo.class)
                    .setParameter("nome", nome.toUpperCase())
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Grupo> todos() {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Grupo.class);

        return criteria.addOrder(Order.asc("nome")).list();

    }
}