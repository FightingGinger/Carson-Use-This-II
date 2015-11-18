
public class Executable 
{

	protected int[] in = {-1, -1}; //Holds the two inputs for the circuit. If neither are -1, then both inputs are present
	protected int output = -1; //Holds the output for the circuit
	protected int delay = -1; //Holds the biggest delay for the circuit
	
	//Not sure if I need this yet or not
	public void Exectuable()
	{
		
	}
	
	/*
	 * Sets input value(1 or 0) for the input at inputNumber
	 * 
	 * Examines running delay to see if it is longer than any other 
	 * 	delays to store for computing the critical path later
	 * 
	 * if all inputs have been signaled (no -1s) then we signal inputs 
	 * 	connected to the gates output
	 */
	public void signalInput(int inputNumber, int value, int runningDelay)
	{
		in[inputNumber] = value; 
		
		//Updates delay to make sure that it is holding the longest circuit delay
		if (runningDelay > delay)
			delay = runningDelay;
	}
}
