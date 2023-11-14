package uk.ac.aston.oop.recipes.io.exceptions;

public class RecipeLoadingException extends Exception {
	
	
	public RecipeLoadingException (String message) {
		 super(message);
	}
	
	public RecipeLoadingException (String message, Throwable t) {
		 super(message, t);
		
	}


}
