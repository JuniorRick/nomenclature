package crdm.nomenclature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crdm.nomenclature.dao.RequestDAO;
import crdm.nomenclature.entity.Request;


@Service
public class RequestServiceImpl implements RequestService {

	@Autowired
	private RequestDAO requestDAO;
	
	@Override
	@Transactional
	public List<Request> requestList() {
		return requestDAO.requestList();
	}

	@Override
	@Transactional
	public Request save(Request request) {
		return requestDAO.save(request);
	}

	@Override
	@Transactional
	public Request find(Integer id) {
		return requestDAO.find(id);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		requestDAO.delete(id);
	}

	@Override
	@Transactional
	public List<Request> approvedList() {
		return requestDAO.approvedList();
	}

	@Override
	@Transactional
	public Integer count(Boolean approved) {
		return requestDAO.count(approved);
	}

}
