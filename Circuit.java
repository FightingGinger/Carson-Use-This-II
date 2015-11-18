import java.util.ArrayList;

public class Circuit extends Executable
{
	private ArrayList<String> gateConnection = new ArrayList<String>();
	private ArrayList<String> input = new ArrayList<String>();
	private ArrayList<Gate> gates = new ArrayList<Gate>();
	private int totalDelay = 0;
	
	
	public Circuit()
	{
		//Awkward constructor that does nothing
	}
	
	public void addInput(String circuitInput, String inputGate, String port)
	{
		input.add(inputGate + "," + circuitInput + "," + port);
	}
	
	public void addConnection(String inputGate, String outputGate, String port)
	{
		gateConnection.add(inputGate + "," + outputGate + "," + port);
	}
	
	public void addOutput(String inputGate, String outputGate, String port)
	{
		gateConnection.add(inputGate + "," + outputGate + "," + port);
	}
	
	public void addGate(Gate gate)
	{
		gates.add(gate);
	}
	
	public void compute(GatePropagationParser gpp, int inputs[])
	{
		int inputGateIndex = 0;
		int outputGateIndex = 0;
		int indexOfFinalOutput = 0;
		int index = 0;
		int initialInput = -1;
		
		addPropagationTimes(gpp);
		
		//Sets all the input gates
		for (index = 0; index < input.size(); index++)
		{
			String[] temp = input.get(index).split(",");
			
			if(temp[1].equals("A"))
				initialInput = inputs[0];
			else if(temp[1].equals("B"))
				initialInput = inputs[1];
			else if(temp[1].equals("C"))
				initialInput = inputs[2];
			
			for (int i = 0; i < gates.size(); i++)
			{
				if (gates.get(i).getLabel().equals(temp[0]))
				{
					inputGateIndex = i;
					break;
				}
			}
			Gate g = gates.get(inputGateIndex);
			
			g.signalInput(Integer.parseInt(temp[2]), initialInput, 0);
			
			gates.set(inputGateIndex, g);
		}
		
		//Gets final output gate		
		for(index = 0; index < gateConnection.size(); index++)
		{
			String[] temp = gateConnection.get(index).split(",");
			
			if(temp[0].equals("Z"))
			{
				indexOfFinalOutput = index;
				break;
			}
		}
		
		while (gates.get(indexOfFinalOutput).output == -1)
		{
			for (index = 0; index < gateConnection.size() - 1; index++)
			{
				String[] temp = gateConnection.get(index).split(",");
				
				for (int i = 0; i < gates.size(); i++)
				{
					if (gates.get(i).getLabel().equals(temp[0]))
						inputGateIndex = i;
					if (gates.get(i).getLabel().equals(temp[1]))
						outputGateIndex = i;
				}
				Gate gin = gates.get(inputGateIndex);
				Gate gout = gates.get(outputGateIndex);
				
				if(gout.output == -1)
					continue;
				else
				{
					gin.signalInput(Integer.parseInt(temp[2]), gout.output, 0);
				}
			}
		}
		
		System.out.println(gates.get(indexOfFinalOutput).output);
	}
	
	private void addPropagationTimes(GatePropagationParser gpp)
	{
		
	}
}
