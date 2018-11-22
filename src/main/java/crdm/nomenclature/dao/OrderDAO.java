package crdm.nomenclature.dao;

import java.util.List;

import crdm.nomenclature.entity.Command;

public interface OrderDAO {
	
	public List<Command> all();
	
	public List<Command> approvedList(); 
	
	public void approve(Integer id); 
	
	public Command save(Command order);
	
	public Command find(Integer id);
	
	void delete(Integer id);
}
