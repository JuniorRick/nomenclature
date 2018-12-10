package crdm.nomenclature.dao;

import java.util.List;

import crdm.nomenclature.entity.Request;

public interface RequestDAO {
	
	public List<Request> requestList();
	
	public Request save(Request request);
	
	public Request find(Integer id);
	
	void delete(Integer id);

	public List<Request> approvedList();
}
