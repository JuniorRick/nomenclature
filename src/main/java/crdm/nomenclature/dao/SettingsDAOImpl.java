package crdm.nomenclature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import crdm.nomenclature.entity.Settings;

@Repository
public class SettingsDAOImpl implements SettingsDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Settings all() {
		Session session = sessionFactory.getCurrentSession();
		List<Settings> settings = session.createQuery("from Settings", Settings.class).getResultList();
		
		if(settings == null || settings.isEmpty()) {
			return null;
		}
		return settings.get(0);
	}

	@Override
	public Settings save(Settings settings) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(settings);
		
		return settings;
	}

}
