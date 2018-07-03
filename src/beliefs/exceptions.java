package beliefs;

public class exceptions {
	public static class probabilityException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public probabilityException(String msg){
		      super(msg);
		   }
		}
	
	public static class InvalidInputException extends Exception{
		/**
		 * 
		 */
		private static final long serialVersionUID = -2331119756178917919L;

		public InvalidInputException(String msg){
			super(msg);
		}
	}
}
