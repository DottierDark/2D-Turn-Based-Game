package exceptions;

@SuppressWarnings("serial")
public class NoAvailableResourcesException extends GameActionException {
	public NoAvailableResourcesException() {
		super();
		
	}
	
	public NoAvailableResourcesException(String s) {
		super(s);
		
	}
}
