package os.girish.practice.spring.mvc.todoapp.models.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import os.girish.practice.spring.mvc.todoapp.models.Todo;
import os.girish.practice.spring.mvc.todoapp.models.User;

@Repository
public class TodoDaoImplHBNet implements TodoDao {

	@Autowired
	private SessionFactory factory;
	
	private static Logger logger = Logger.getLogger(TodoDaoImplHBNet.class);
	
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

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Todo> getByUser(User user) {
		Session session = factory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Todo> criteriaQuery = builder.createQuery(Todo.class);
		Root<Todo> root = criteriaQuery.from(Todo.class);
		criteriaQuery.select(root);
		criteriaQuery.where(builder.equal(root.get("user"), user));
		Query query = session.createQuery(criteriaQuery);
		return query.getResultList();
	}

	@Transactional
	@Override
	public Todo getById(int id) {
		Session session = factory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Todo> criteriaQuery = builder.createQuery(Todo.class);
		Root<Todo> root = criteriaQuery.from(Todo.class);
		criteriaQuery.where(builder.equal(root.get("id"), id));
		Query query = session.createQuery(criteriaQuery);
		return (Todo) query.getSingleResult();
	}

	@Transactional
	@Override
	public void deleteById(int id) {
		Session session = factory.getCurrentSession();
		Todo todo = session.load(Todo.class, id);
		session.delete(todo);
		logger.debug("Object deleted!");
	}

}
