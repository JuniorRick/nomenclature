package crdm.nomenclature.dao;

import java.util.List;

import crdm.nomenclature.entity.Command;

public interface OrderDAO {
	
	public List<Command> all();
	
	public Command save(Command order);
	
	public Command find(Integer id);
	
	void delete(Integer id);

	public List<Command> requests();

}
