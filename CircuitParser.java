/*------------------------Class/Implementation Details--------------------------*
 * Azusa Pacific University, CS 445 - Computer Organization & Architecture		*
 * Source: CircuitParser.java (HardwareParser)									*
 * Original Code Author(s): Dan Grissom											*
 * Original Completion/Release Date: October 1, 2014							*
 *																				*
 * Details: Parses a circuit input file. This is not meant to be complete, but	*
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


public class CircuitParser
{
	// Member variables
	private int numInputs;
	private int numOutputs;
	private ArrayList<String> inputLabels;
	private ArrayList<String> outputLabels;
	private ArrayList<Gate> Gates;
	
	//////////////////////////////////////////////////////////////////////////////////////
	// Constructor
	//////////////////////////////////////////////////////////////////////////////////////
	public CircuitParser(String fileName)
	{
		// Initialize data structures and values
		numInputs = 0;
		numOutputs = 0;
		inputLabels = new ArrayList<String>();
		outputLabels = new ArrayList<String>();
		
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

				if (lineU.startsWith(("IO (")) || lineU.startsWith(("IO(")))
				{
					// Parse parameters and ensure we have the proper number of parameters
					String paramStr = line.substring(line.indexOf("(")+1, line.indexOf(")"));
					String[] params = paramStr.split(",");
					for (int i = 0; i < params.length; i++)
						params[i] = params[i].trim();
					if (params.length != 2)
					{
						ArchError.DisplayError(line + "\n\n" + "IO must have 2 parameters: ([numInputs], [numOutputs])");
						return;
					}
					
					// Save params
					numInputs = Integer.parseInt(params[0]);
					numOutputs = Integer.parseInt(params[1]);
				}
				else if (lineU.startsWith(("IOLABELS (")) || lineU.startsWith(("IOLABELS(")))
				{
					// Do a basic error check to make sure we know how many labels we have for in/output
					if (numInputs + numOutputs == 0)
						ArchError.DisplayError("Please put the IO() tag before the IOLABELS() tag in the input file."); 
					
					// Parse parameters and ensure we have the proper number of parameters
					String paramStr = line.substring(line.indexOf("(")+1, line.indexOf(")"));
					String[] params = paramStr.split(",");
					for (int i = 0; i < params.length; i++)
						params[i] = params[i].trim();
					if (params.length != numInputs + numOutputs)
					{

						ArchError.DisplayError(line + "\n\n" + "IOLABELS must have " + (numInputs + numOutputs) + " (as specified by your IO() tag) parameters: ([inLabel1],..., [outLabel1],...)");
						return;
					}
					
					// Save params
					for (int i = 0; i < params.length; i++)
					{
						if (i < numInputs)
							inputLabels.add(params[i]);
						else
							outputLabels.add(params[i]);
					}					
				}
				else if (lineU.startsWith(("GATE (")) || lineU.startsWith(("GATE(")))
				{
					// Parse parameters and ensure we have the proper number of parameters
					String paramStr = line.substring(line.indexOf("(")+1, line.indexOf(")"));
					String[] params = paramStr.split(",");
					for (int i = 0; i < params.length; i++)
						params[i] = params[i].trim();
					if (params.length != 2)
					{
						ArchError.DisplayError(line + "\n\n" + "GATE must have 2 parameters: ([type], [gateLabel])");
						return;
					}
					
					// TODO: Here you'll need to create an instance of your Gate class (which you must first write)
					// and add to a data-structure of all your gates
					GateType gt = GateType.getTypeFromString(params[0]);
					String label = params[1];
					Gate g = new Gate(gt, label); // IMPLEMENTED
					Gates.add(g); //Adds the gate to the array list
					
				}
				else if (lineU.startsWith(("CONN (")) || lineU.startsWith(("CONN(")))
				{
					// Parse parameters and ensure we have the proper number of parameters
					String paramStr = line.substring(line.indexOf("(")+1, line.indexOf(")"));
					String[] params = paramStr.split(",");
					for (int i = 0; i < params.length; i++)
						params[i] = params[i].trim();
					if (params.length != 4)
					{
						ArchError.DisplayError(line + "\n\n" + "CONN must have 4 parameters: ([outGateLabel], [outGatePortNum], [inGateLabel], [inGatePortNum])");
						return;
					}
					
					// TODO: Here you'll need to create your wires. Since you are required to keep your circuit of gates as a tree
					// (i.e., your gates should have children, and potentially parents), you may not need to create an explicit
					// data-structure to represent wires)					
					String outGateLabel = params[0];
					int outGatePortNum = Integer.parseInt(params[1]);
					String inGateLabel = params[2];
					int inGatePortNum = Integer.parseInt(params[3]);
					// Here, you'll need to either save this information as a new data-structure, or modify your
					// current Gate class to be able to represent the connections (HINT: something like outGate.children.add(inGate))
					
				}
				else if (lineU.startsWith(("INPUT (")) || lineU.startsWith(("INPUT(")))
				{
					// Parse parameters and ensure we have the proper number of parameters
					String paramStr = line.substring(line.indexOf("(")+1, line.indexOf(")"));
					String[] params = paramStr.split(",");
					for (int i = 0; i < params.length; i++)
						params[i] = params[i].trim();
					if (params.length != 3)
					{
						ArchError.DisplayError(line + "\n\n" + "INPUT must have 3 parameters: ([inCircuitLabel], [inGateLabel], [inGatePortNum])");
						return;
					}
					
					// TODO: Here you'll need to create a wire from the inputs of the circuit to the inputs of a gate. Since you
					// are required to keep your circuit of gates as a tree (i.e., your gates should have children, and potentially
					// parents), you may not need to create an explicit data-structure to represent wires).					
					String inputCircuitLabel = params[0];
					String inGateLabel = params[1];
					int inGatePortNum = Integer.parseInt(params[2]);
					// Here, you'll need to either save this information as a new data-structure, or modify your
					// current Circuit class to be able to represent the connections (HINT: something like circuit.inputAt[inputCircuitLabel].children.add(inGate))
				}
				else if (lineU.startsWith(("OUTPUT (")) || lineU.startsWith(("OUTPUT(")))
				{
					// Parse parameters and ensure we have the proper number of parameters
					String paramStr = line.substring(line.indexOf("(")+1, line.indexOf(")"));
					String[] params = paramStr.split(",");
					for (int i = 0; i < params.length; i++)
						params[i] = params[i].trim();
					if (params.length != 3)
					{
						ArchError.DisplayError(line + "\n\n" + "OUTPUT must have 3 parameters: ([outGateLabel], [outGatePortNum], [outCircuitLabel])");
						return;
					}
					
					// TODO: Here you'll need to create a wire from the output of a gate to the output of the circuit. Since you
					// are required to keep your circuit of gates as a tree (i.e., your gates should have children, and potentially
					// parents), you may not need to create an explicit data-structure to represent wires).					
					String outGateLabel = params[0];
					int outGatePortNum = Integer.parseInt(params[1]);
					String outputCircuitLabel = params[2];					
					// Here, you'll need to either save this information as a new data-structure, or modify your
					// current Circuit/Gate class to be able to represent the connections (HINT: something like outGate.circuitOutChildren.add(outputCircuitLabel))
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
