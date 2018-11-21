package crdm.nomenclature.rest.exception;

public class NotFoundException extends RuntimeException{
	
	static final long serialVersionUID = 1L;

	public NotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NotFoundException(String arg0) {
		super(arg0);
	}

	public NotFoundException(Throwable arg0) {
		super(arg0);
	}
	
	
}
