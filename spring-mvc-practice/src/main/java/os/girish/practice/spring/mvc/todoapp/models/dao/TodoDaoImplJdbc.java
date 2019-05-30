package os.girish.practice.spring.mvc.todoapp.models.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import os.girish.practice.spring.mvc.todoapp.models.Todo;

@Repository
public class TodoDaoImplJdbc implements TodoDao {

	@Autowired
	private SessionFactory factory;
	
	@Transactional
	public void save(Todo todo) {
		factory.getCurrentSession().saveOrUpdate(todo);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Todo> fetchAll() {
		Session session = factory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Todo> cq = cb.createQuery(Todo.class);
		Root<Todo> root = cq.from(Todo.class);
		cq.select(root);
		Query qry = session.createQuery(cq);
		return qry.getResultList();
	}

}
