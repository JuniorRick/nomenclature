package crdm.nomenclature.service;

import java.util.List;

import crdm.nomenclature.entity.Command;

public interface OrderService {
	
	public List<Command> all();
	
	public Command save(Command order);

	public Command find(Integer id);

	void delete(Integer id);

	public List<Command> requests();


}
