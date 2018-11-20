package crdm.nomenclature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crdm.nomenclature.entity.Section;


@Repository
public class SectionDAOImpl implements SectionDAO{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Section> all() {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createQuery("from Section order by name", Section.class).getResultList();
	}

	@Override
	public Section save(Section section) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(section);
		return section;
	}

	@Override
	public Section find(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		
		return session.find(Section.class, id);
	}

	@Override
	public void delete(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Section section = session.find(Section.class, id);
		
		session.delete(section);
		
	}

}
