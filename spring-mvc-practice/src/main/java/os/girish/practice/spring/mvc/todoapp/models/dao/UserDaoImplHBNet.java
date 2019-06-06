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

import os.girish.practice.spring.mvc.todoapp.models.User;

@Repository
public class UserDaoImplHBNet implements UserDao {

	@Autowired
	private SessionFactory factory;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<User> getAllUsers() {
		Session session = factory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> user = cq.from(User.class);
		cq.select(user);
		Query qry = session.createQuery(cq);
		return qry.getResultList();
	}

	@Override
	public User findByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public User findByUserNamePassword(String userId, String password) {
		Session session = factory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> user = cq.from(User.class);
		cq.select(user).where(cb.equal(user.get("userId"), userId)).where(cb.equal(user.get("password"), password));
		Query qry = session.createQuery(cq);
		return (User) qry.getSingleResult();
	}
}
