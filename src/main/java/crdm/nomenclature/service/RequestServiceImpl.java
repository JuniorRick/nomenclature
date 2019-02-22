package crdm.nomenclature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crdm.nomenclature.dao.RequestDAO;
import crdm.nomenclature.entity.Request;


@Service
@Transactional
public class RequestServiceImpl implements RequestService {

	@Autowired
	private RequestDAO requestDAO;
	
	@Override
	public List<Request> requestList() {
		return requestDAO.requestList();
	}

	@Override
	public Request save(Request request) {
		return requestDAO.save(request);
	}

	@Override
	public Request find(Integer id) {
		return requestDAO.find(id);
	}

	@Override
	public void delete(Integer id) {
		requestDAO.delete(id);
	}

	@Override
	public List<Request> approvedList() {
		return requestDAO.approvedList();
	}

	@Override
	public Integer count(Boolean approved) {
		return requestDAO.count(approved);
	}

	@Override
	public List<Request> depositedList() {
		return requestDAO.depositedList();
	}

}
