package crdm.nomenclature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crdm.nomenclature.dao.OrderDAO;
import crdm.nomenclature.entity.Command;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDAO;
	
	@Override
	@Transactional
	public List<Command> all() {
		return orderDAO.all();
	}

	@Override
	@Transactional
	public Command save(Command order) {
		return orderDAO.save(order);
	}
	
	@Override
	@Transactional
	public Command find(Integer id) {
		return orderDAO.find(id);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		orderDAO.delete(id);
	}

	@Override
	@Transactional
	public List<Command> requests() {
		// TODO Auto-generated method stub
		return orderDAO.requests();
	}

}
