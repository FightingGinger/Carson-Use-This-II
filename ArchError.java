import javax.swing.JOptionPane;


public class ArchError
{
	//////////////////////////////////////////////////////////////////////////////////////
	// Displays the given error message in a pop-up window
	//////////////////////////////////////////////////////////////////////////////////////
	public static void DisplayError(String error)
	{
		JOptionPane.showMessageDialog(null, error, "Architecture Project Error", JOptionPane.ERROR_MESSAGE);
	}
}
