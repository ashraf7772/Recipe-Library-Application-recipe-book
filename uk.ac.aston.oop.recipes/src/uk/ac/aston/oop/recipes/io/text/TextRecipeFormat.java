package uk.ac.aston.oop.recipes.io.text;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import uk.ac.aston.oop.recipes.io.RecipeFormat;
import uk.ac.aston.oop.recipes.io.exceptions.RecipeLoadingException;
import uk.ac.aston.oop.recipes.io.exceptions.RecipeSavingException;
import uk.ac.aston.oop.recipes.io.json.JSONRecipe;
import uk.ac.aston.oop.recipes.model.Recipe;

public class TextRecipeFormat implements RecipeFormat {

	public static final String RECIPE_HEADER = "OOPRecipe 1.0";
	public static final String SEPARATOR_INSTRUCTIONS = "__INSTRUCTIONS__";

	@Override
	public String getExtension() {
		return "txt";
	}

	@Override
	public String getDescription() {
		return "Raw Text Files (*.txt)";
	}

	@Override
	public Recipe load(File f) throws RecipeLoadingException {
		Recipe r1 = new Recipe();

		// TODO 1. safely open the file in a try-with-resources
		// TODO 2. catch IOException, wrap it and throw it
		// TODO 3. inside the try block, do the following

		/*
			// Line numbers start at 0 by default - make them start at 1
			lnr.setLineNumber(1);
			checkHeader(lnr, f);

			Recipe r = new Recipe();
			readRecipeName(lnr, r);
			readIngredients(lnr, r);
			readInstructions(lnr, r);
		*/
		
		
		
		try(
				  FileReader fr = new FileReader(f, StandardCharsets.UTF_8);
				LineNumberReader lnr = new LineNumberReader(fr);
				) {
			lnr.setLineNumber(1);
			checkHeader(lnr, f);

			//Recipe r1 = new Recipe();
			readRecipeName(lnr, r1);
			readIngredients(lnr, r1);
			readInstructions(lnr, r1);
			


			return r1;
			
				
				
				} catch (IOException e) {
					  throw new RecipeLoadingException("Error while loading recipe file", e);

				}

		//return r1;
	}

	private void readInstructions(LineNumberReader lnr, Recipe r) throws IOException {
		
		ArrayList<String> lines = new ArrayList<String>();
	    String line = lnr.readLine();
	   
	    while (line != null) {
	    	 lines.add(line);
	    	 line = lnr.readLine();
	    	 
	    	}
	    
	    
	    String instructions = String.join(System.lineSeparator(), lines);
		r.instructionsProperty().set(instructions);
		
	 
		 
		
		
	}

	private void readIngredients(LineNumberReader lnr, Recipe r) throws IOException, RecipeLoadingException {
		// TODO read all the lines up to end-of-file or SEPARATOR_INSTRUCTIONS
		
		// TODO set the ingredients property of the recipe

		// TODO if the line is not SEPARATOR_INSTRUCTIONS, throw exception 
		
	    ArrayList<String> lines = new ArrayList<String>();
	    String line = lnr.readLine();
	   
	    while (line != null && !line.equals(SEPARATOR_INSTRUCTIONS) ) {
	    	 lines.add(line);
	    	 line = lnr.readLine();
	    	 
	    	}
	    
	    if(!SEPARATOR_INSTRUCTIONS.equals(line))   {
			  throw new RecipeLoadingException("We cannot load" + line);

	    	
	    }
	    
	    String ingredients = String.join(System.lineSeparator(), lines);
		r.ingredientsProperty().set(ingredients);
	}

	private void readRecipeName(LineNumberReader lnr, Recipe r) throws IOException, RecipeLoadingException {
		// TODO read a line

		// TODO if it's null (end-of-file), throw a recipe loading exception

		// TODO set the name of the recipe
		String line;
		line = lnr.readLine();
		if (line == null) {
			
			  throw new RecipeLoadingException("recipe file ended prematurely before a name could be read " + lnr.getLineNumber());

			
		}
		
		r.nameProperty().set(line.strip());
		
		
		
		
		
	}

	private void checkHeader(LineNumberReader lnr, File f) throws  RecipeLoadingException, IOException {
		// TODO read a line

		// TODO if it's not RECIPE_HEADER, throw a recipe loading exception
		
		String line;
		line = lnr.readLine();
		
		if(!RECIPE_HEADER.equals(line)) {
			  throw new RecipeLoadingException("We cannot load files which are not recipes" + lnr.getLineNumber());

			
		}
		
		
		
		
		
		
	}

	@Override
	public void save(Recipe r, File f) throws RecipeSavingException {
		/* 
		 * TODO If the property contains SEPARATOR_INSTRUCTIONS,
		 * throw a recipe saving exception (cannot safely store the recipe
		 * in this format).
		 */

		/*
		 * TODO 1. open safely the file
		 *
		 * TODO 2. Inside the file, println():
		 *
		 *    - the RECIPE_HEADER
		 *    - the name
		 *    - the ingredients
		 *    - the SEPARATOR_INSTRUCTIONS
		 *    - the instructions
		 */
		//if(!RECIPE_HEADER.equals(line)
		if (r.ingredientsProperty().get().equals(SEPARATOR_INSTRUCTIONS))  {
			  throw new RecipeSavingException("We cannot save files with SEPARATOR_INSTRUCTIONS)");

			}
		
		
		try(
				  FileWriter fw = new FileWriter(f, StandardCharsets.UTF_8);
				  PrintWriter pw = new PrintWriter (fw);
				) {
			pw.println(RECIPE_HEADER);
			pw.println(r.nameProperty().get());
			pw.println(r.ingredientsProperty().get());
			pw.println(SEPARATOR_INSTRUCTIONS);
			pw.println(r.instructionsProperty().get());

			



			
				
				
				} catch (IOException e) {
					  throw new RecipeSavingException("cannot save");

				}
		

	}

}
