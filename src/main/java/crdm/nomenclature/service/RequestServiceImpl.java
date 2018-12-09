package crdm.nomenclature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crdm.nomenclature.dao.RequestDAO;
import crdm.nomenclature.entity.Command;
import crdm.nomenclature.entity.Request;


@Service
public class RequestServiceImpl implements RequestService {

	@Autowired
	private RequestDAO requestDAO;
	
	@Override
	@Transactional
	public List<Request> all() {
		return requestDAO.all();
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
		Request request = requestDAO.find(id);
		List<Command> orders = request.getOrders();
		
		for(Command order: orders) {
			Float remainder = order.getQuantity() + 
			order.getPurchase().getRemainder();
			
			order.getPurchase().setRemainder(remainder);
		}

		requestDAO.delete(id);
	}

}
