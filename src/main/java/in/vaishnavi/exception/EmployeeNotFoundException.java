package in.vaishnavi.exception;

public class EmployeeNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID=1L;
	
	public EmployeeNotFoundException() {
		super();
	}
	public EmployeeNotFoundException(String message) {
		super(message);
		System.out.println("Message--"+message);
	}

}
