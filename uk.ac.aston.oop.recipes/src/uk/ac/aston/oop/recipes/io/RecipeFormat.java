package uk.ac.aston.oop.recipes.io;

import java.io.File;

import uk.ac.aston.oop.recipes.io.exceptions.RecipeLoadingException;
import uk.ac.aston.oop.recipes.io.exceptions.RecipeSavingException;
import uk.ac.aston.oop.recipes.model.Recipe;

public interface RecipeFormat {

	String getExtension();

	String getDescription();

	// TODO define your custom checked exceptions for loading/saving recipes 
	
	Recipe load(File f) throws RecipeLoadingException;

	void save(Recipe r, File f) throws RecipeSavingException;

}
