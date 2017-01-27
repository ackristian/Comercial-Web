package com.sulasoftwares.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sulasoftwares.model.Cliente;
import com.sulasoftwares.repository.filter.ClienteFilter;
import com.sulasoftwares.service.NegocioException;
import com.sulasoftwares.util.jpa.Transactional;

public class Clientes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
    private EntityManager manager;
	
	public Cliente guardar(Cliente cliente){
		return manager.merge(cliente);
	}
	
	
	@Transactional
	public void remover(Cliente cliente){
		try{
		cliente = porId(cliente.getId());
		manager.remove(cliente);
		manager.flush();
		}catch(PersistenceException e){
			throw new NegocioException("Cliente não pode ser excluido.");
		}
	}

	public Cliente porId(Long id) {
		
		return manager.find(Cliente.class, id);
	}
	
	public List<Cliente> porNome(String nome) {
		return this.manager.createQuery("from Cliente " +
				"where upper(nome) like :nome", Cliente.class)
				.setParameter("nome", nome.toUpperCase() + "%")
				.getResultList();
	}

	public Cliente porDocumento(String string) {
			try{
			return manager.createQuery("from Cliente where upper(docomentoPessoal) = :docomentoPessoal",Cliente.class)
					.setParameter("docomentoPessoal", string.toUpperCase())
					.getSingleResult();
				}catch(NoResultException e){
					return null;
			}
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> filtrados(ClienteFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cliente.class);
		
			if(StringUtils.isNotBlank(filtro.getDocomentoPessoal())){
				criteria.add(Restrictions.eq("docomentoPessoal", filtro.getDocomentoPessoal()));
			}
		   if(StringUtils.isNotBlank(filtro.getNome())){
			   criteria.add(Restrictions.ilike("nome", filtro.getNome(),MatchMode.ANYWHERE));
		   }
		   
		   return criteria.addOrder(Order.asc("nome")).list();
	}
}
