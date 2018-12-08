package crdm.nomenclature.dao;

import java.util.List;

import crdm.nomenclature.entity.Request;

public interface RequestDAO {
	
	public List<Request> all();
	
	public Request save(Request request);
	
	public Request find(Integer id);
	
	void delete(Integer id);
}
