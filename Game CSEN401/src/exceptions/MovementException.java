package exceptions;

@SuppressWarnings("serial")
public class MovementException extends GameActionException{
	public MovementException() {
		super();
		
	}
	
	public MovementException(String s) {
		super(s);
		
	}

}
