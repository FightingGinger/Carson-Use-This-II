
public class Gate extends Executable
{
	private GateType gt;
	private String label;
	
	/*
	 * gateType is the gate type (duh) and/or/nand/nor/etc
	 * 
	 * l is the gate's label (AND1, AND2, OR1, OR2, etc...)
	 */
	public Gate(GateType gateType, String l)
	{
		gt = gateType;
		label = l;
		
	}
	
	//OVERRIDDEN BITCH!!!!!!!!!
	public void signalInput(int inputNumber, int value, int runningDelay)
	{
		in[inputNumber] = value; 
		
		//Updates delay to make sure that it is holding the longest circuit delay
		if (runningDelay > delay)
			delay = runningDelay;
		
		//Both inputs are full
		if (in[0] != -1 && in[1] != -1)
			calculate();
	}
	
	//Generic return label method so I can keep it private
	public String getLabel()
	{
		return label;
	}
	
	//Does the calculations on the input. Returns the output as a number ready to be plugged in again
	private void calculate()
	{
		boolean tempOutput = false;
		
		if (gt == GateType.AND)
			tempOutput = convertToBoolean(in[0]) && convertToBoolean(in[1]);
		else if (gt == GateType.OR)
			tempOutput = convertToBoolean(in[0]) || convertToBoolean(in[1]);
		else if (gt == GateType.NAND)
			tempOutput = !(convertToBoolean(in[0]) && convertToBoolean(in[1]));
		else if (gt == GateType.NOR)
			tempOutput = !(convertToBoolean(in[0]) || convertToBoolean(in[1]));
		else if (gt == GateType.XOR)
			tempOutput = convertToBoolean(in[0]) != convertToBoolean(in[1]);
		else if (gt == GateType.NOT)
			tempOutput = !convertToBoolean(in[0]);
		
		output = convertToInt(tempOutput);
	}
	
	//Converts number (1 or 0) to boolean
	private boolean convertToBoolean(int number)
	{
		if (number == 0)
			return false;
		else
			return true;
	}
	
	//Converts boolean (t/f) to an int (1/0)
	private int convertToInt(boolean bool)
	{
		if (bool)
			return 1;
		else
			return 0;
	}
}
