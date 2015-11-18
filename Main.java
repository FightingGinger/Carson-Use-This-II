import java.util.ArrayList;

/*------------------------Class/Implementation Details--------------------------*
 * Azusa Pacific University, CS 445 - Computer Organization & Architecture		*
 * Source: CircuitParser.java (HardwareParser)									*
 * Original Code Author(s): 													*
 * Original Completion/Release Date: October 1, 2014							*
 *																				*
 * Details: Main file to run your simulator from.								*
 *																				*
 *-----------------------------------------------------------------------------*/

public class Main
{

	public static void main(String[] args)
	{
		/////////////////////////////////////////////////////////////////
		// Name/project info
		System.out.println("Josh Dubisz & Carson Hall & Daniel Nishijima");
		System.out.println("CS445 Project 2 - Boolean Gate Simulator");
		System.out.println("");

		/////////////////////////////////////////////////////////////////		
		// Simple main which just reads in the 3 files
		// You'll need to modify the parsers to save the
		// data read from files however you'd like. Do not
		// assume the way it is done is ideal for you. Do 
		// what makes sense to you.
		CircuitParser cp = new CircuitParser("Circuit.txt");
		GatePropagationParser gpp = new GatePropagationParser("PropagationTimes.txt");
		InputVectorParser ivp = new InputVectorParser("InputVectors.txt");
		
		Circuit circuit = cp.circuit;
		for (int i = 0; i < ivp.inputVectors.size(); i++)
		{
			int[] inputs = {Integer.parseInt(ivp.inputVectors.get(i).get(0).toString()), Integer.parseInt(ivp.inputVectors.get(i).get(1).toString()), Integer.parseInt(ivp.inputVectors.get(i).get(2).toString())};
			circuit.compute(gpp, inputs);
		}
		
		System.out.println("Exiting Boolean Gate Simulator");

	}

}
