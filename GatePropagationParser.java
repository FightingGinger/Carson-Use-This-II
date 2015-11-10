/*------------------------Class/Implementation Details--------------------------*
 * Azusa Pacific University, CS 445 - Computer Organization & Architecture		*
 * Source: GatePropagationParser.java (GatePropagationParser)					*
 * Original Code Author(s): Dan Grissom											*
 * Original Completion/Release Date: October 1, 2014							*
 *																				*
 * Details: Parses a gate prop. input file. This is not meant to be complete,	* 
 * but rather is supposed to help get you started on your project. Feel free to	*
 * modify this file in any way accept for the parts that actual parse the file.	*
 * You can modify what you do with the data you save. It was just saved in a 	*
 * quick way to give you an idea of what data was being parsed from the file.	*
 *																				*
 *-----------------------------------------------------------------------------*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GatePropagationParser
{
	// Member variables
	private int andPropTime;
	private int orPropTime;
	private int notPropTime;
	private int nandPropTime;
	private int norPropTime;
	private int xorPropTime;
	
	//////////////////////////////////////////////////////////////////////////////////////
	// Constructor
	//////////////////////////////////////////////////////////////////////////////////////
	public GatePropagationParser(String fileName)
	{
		// Initialize data structures and values
		andPropTime = 0;
		orPropTime = 0;
		notPropTime = 0;
		nandPropTime = 0;
		norPropTime = 0;
		xorPropTime = 0;
		
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
				String lineU = line.toUpperCase();

				if (lineU.startsWith(("AND (")) || lineU.startsWith(("AND(")))
				{
					// Parse parameters and ensure we have the proper number of parameters
					String paramStr = line.substring(line.indexOf("(")+1, line.indexOf(")"));
					String[] params = paramStr.split(",");
					for (int i = 0; i < params.length; i++)
						params[i] = params[i].trim();
					if (params.length != 1)
					{
						ArchError.DisplayError(line + "\n\n" + "AND must have 1 parameters: ([propagationTimeInNanoSeconds])");
						return;
					}
					
					// Save params
					andPropTime = Integer.parseInt(params[0]);
				}
				else if (lineU.startsWith(("OR (")) || lineU.startsWith(("OR(")))
				{
					// Parse parameters and ensure we have the proper number of parameters
					String paramStr = line.substring(line.indexOf("(")+1, line.indexOf(")"));
					String[] params = paramStr.split(",");
					for (int i = 0; i < params.length; i++)
						params[i] = params[i].trim();
					if (params.length != 1)
					{
						ArchError.DisplayError(line + "\n\n" + "OR must have 1 parameters: ([propagationTimeInNanoSeconds])");
						return;
					}
					
					// Save params
					orPropTime = Integer.parseInt(params[0]);
				}
				else if (lineU.startsWith(("NOT (")) || lineU.startsWith(("NOT(")))
				{
					// Parse parameters and ensure we have the proper number of parameters
					String paramStr = line.substring(line.indexOf("(")+1, line.indexOf(")"));
					String[] params = paramStr.split(",");
					for (int i = 0; i < params.length; i++)
						params[i] = params[i].trim();
					if (params.length != 1)
					{
						ArchError.DisplayError(line + "\n\n" + "NOT must have 1 parameters: ([propagationTimeInNanoSeconds])");
						return;
					}
					
					// Save params
					notPropTime = Integer.parseInt(params[0]);
				}
				else if (lineU.startsWith(("NAND (")) || lineU.startsWith(("NAND(")))
				{
					// Parse parameters and ensure we have the proper number of parameters
					String paramStr = line.substring(line.indexOf("(")+1, line.indexOf(")"));
					String[] params = paramStr.split(",");
					for (int i = 0; i < params.length; i++)
						params[i] = params[i].trim();
					if (params.length != 1)
					{
						ArchError.DisplayError(line + "\n\n" + "NAND must have 1 parameters: ([propagationTimeInNanoSeconds])");
						return;
					}
					
					// Save params
					nandPropTime = Integer.parseInt(params[0]);
				}
				else if (lineU.startsWith(("NOR (")) || lineU.startsWith(("NOR(")))
				{
					// Parse parameters and ensure we have the proper number of parameters
					String paramStr = line.substring(line.indexOf("(")+1, line.indexOf(")"));
					String[] params = paramStr.split(",");
					for (int i = 0; i < params.length; i++)
						params[i] = params[i].trim();
					if (params.length != 1)
					{
						ArchError.DisplayError(line + "\n\n" + "NOR must have 1 parameters: ([propagationTimeInNanoSeconds])");
						return;
					}
					
					// Save params
					norPropTime = Integer.parseInt(params[0]);
				}
				else if (lineU.startsWith(("XOR (")) || lineU.startsWith(("XOR(")))
				{
					// Parse parameters and ensure we have the proper number of parameters
					String paramStr = line.substring(line.indexOf("(")+1, line.indexOf(")"));
					String[] params = paramStr.split(",");
					for (int i = 0; i < params.length; i++)
						params[i] = params[i].trim();
					if (params.length != 1)
					{
						ArchError.DisplayError(line + "\n\n" + "XOR must have 1 parameters: ([propagationTimeInNanoSeconds])");
						return;
					}
					
					// Save params
					xorPropTime = Integer.parseInt(params[0]);
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