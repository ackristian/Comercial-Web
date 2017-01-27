package com.sulasoftwares.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.sulasoftwares.model.Categoria;
import com.sulasoftwares.repository.filter.CategoriaFilter;
import com.sulasoftwares.service.NegocioException;
import com.sulasoftwares.util.jpa.Transactional;

public class Categorias implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public List<Categoria> raizes() {
		return manager.createQuery("from Categoria where categoriaPai is null", Categoria.class).getResultList();
	}
	
	public List<Categoria> subCategoriasDe(Categoria categoriaPai) {
		return manager.createQuery("from Categoria where categoriaPai = :raiz",
				Categoria.class)
				.setParameter("raiz", categoriaPai)
				.getResultList();
	}

	public Categoria porId(Long id) {
		
		return manager.find(Categoria.class, id);
	}

	public Categoria guardar(Categoria categoria) {
		
		return manager.merge(categoria);
	}
	
	@SuppressWarnings("unchecked")
	public List<Categoria> filtrados(CategoriaFilter filtro){
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Categoria.class);
		
		   if(StringUtils.isNotBlank(filtro.getDescricao())){
			   criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(),MatchMode.ANYWHERE));
		   }
		   
		   return criteria.addOrder(Order.asc("descricao")).list();
	}
	
	@Transactional
	public void remover(Categoria categoria) {
		try {
            categoria = porId(categoria.getId());
            manager.remove(categoria);
            manager.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Categoria " + categoria.getDescricao() + " não pode ser excluído.");
        }
		
	}
	
}
