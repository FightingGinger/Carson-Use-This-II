/*------------------------Class/Implementation Details--------------------------*
 * Azusa Pacific University, CS 445 - Computer Organization & Architecture		*
 * Source: InputParser.java (InputParser)										*
 * Original Code Author(s): Dan Grissom											*
 * Original Completion/Release Date: October 1, 2014							*
 *																				*
 * Details: Parses an input vector file. This is not meant to be complete, but	*
 * rather is supposed to help get you started on your project. Feel free to		*
 * modify this file in any way accept for the parts that actual parse the file.	*
 * You can modify what you do with the data you save. It was just saved in a 	*
 * quick way to give you an idea of what data was being parsed from the file.	*
 *																				*
 *-----------------------------------------------------------------------------*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class InputVectorParser
{
	// Member variables
	ArrayList<ArrayList<Integer>> inputVectors;
	
	//////////////////////////////////////////////////////////////////////////////////////
	// Constructor
	//////////////////////////////////////////////////////////////////////////////////////
	public InputVectorParser(String fileName)
	{
		// Initialize data structures and values
		inputVectors = new ArrayList<ArrayList<Integer>>();
		
		// Add any other initializations here
		
		ReadFile(fileName);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	// Open and read from ARCH file
	//////////////////////////////////////////////////////////////////////////////////////
	private void ReadFile(String fileName)
	{
		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new FileReader(fileName));
			String line = null;
			int cycleNum = 0;
			
			while ((line = bf.readLine()) != null)
			{

				ArrayList<Integer> vector = new ArrayList<Integer>(); // New input vector
				if (line.charAt(0) == '0' || line.charAt(0) == '1')
				{
					// Iterate through each character
					for (int i = 0; i < line.length(); i++)
					{
						// Sanity check
						if (!(line.charAt(i) == '0' || line.charAt(i) == '1'))
						{
							ArchError.DisplayError(line + "\n\n" + "Input vector line can contain only 0's and 1's");
							return;
						}
						
						// Save each bit into vector

						vector.add(Integer.parseInt(line.substring(i, i+1)));							
					}
					inputVectors.add(vector);
				}
				else if (!(line.isEmpty() || line.startsWith("//")))
				{
					ArchError.DisplayError(line + "\n\n" + "Unspecified line type for Initialization.");
					return;
				}
				
			}			
		} catch (FileNotFoundException ex) {
			ArchError.DisplayError("FileNotFoundException: " + ex.getMessage());
		} catch (IOException ex) {
			ArchError.DisplayError("IOException: " + ex.getMessage());
		} finally {
			// Close the BufferedReader
			try {
				if (bf != null)
					bf.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}