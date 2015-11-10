/*------------------------Class/Implementation Details--------------------------*
 * Azusa Pacific University, CS 445 - Computer Organization & Architecture		*
 * Source: GateType.java (GateType)												*
 * Original Code Author(s): Dan Grissom											*
 * Original Completion/Release Date: October 1, 2014							*
 *																				*
 * Details: Simply defines an ENUM type for the different primitive gate types.	*
 *																				*
 *-----------------------------------------------------------------------------*/

//////////////////////////////////////////////////////////////////////////////////////
// Simple enum class for the different gate types
//////////////////////////////////////////////////////////////////////////////////////
public enum GateType
{
	// ENUM types
	AND,
	OR,
	NOT,
	NAND,
	NOR,
	XOR;
	
	//////////////////////////////////////////////////////////
	// Takes in a string and returns the enum type
	//////////////////////////////////////////////////////////
	static GateType getTypeFromString(String type)
	{
		if (type.toUpperCase().trim().equals("AND"))
			return AND;
		else if (type.toUpperCase().trim().equals("OR"))
			return OR;
		else if (type.toUpperCase().trim().equals("NOT"))
			return NOT;
		else if (type.toUpperCase().trim().equals("NAND"))
			return NAND;
		else if (type.toUpperCase().trim().equals("NOR"))
			return NOR;
		else
			return XOR;
	}
}
