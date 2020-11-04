import javax.swing.*;

 // Und für die Übersicht nochmal ohne Kommentare

public class DecimalToBinaryWithoutComments 
{
    private static String binaryValue = "";

    public DecimalToBinaryWithoutComments()
    {
        long valueToCalculate = validateUserInput();
        calculateDecimalToBinary(valueToCalculate);	        
        
        JOptionPane.showMessageDialog(new JFrame(), valueToCalculate + " in Binärschreibweise: " + binaryValue + "\n" +
        binaryValue + " enthält " + printBitCount() + " zusammenhängende Bits.", "Decimal To Binary Tool", JOptionPane.PLAIN_MESSAGE);
	}
    
    
	static String calculateDecimalToBinary(long valueToConvert)
	{
        long decimalValue = 1;

	    if(valueToConvert == 0)
	    {
            binaryValue += "0";
            return "";
        }
        
        while(decimalValue<valueToConvert)
        {
            decimalValue <<= 1;
        }
       
	    while(decimalValue != 0)
	    {
            if((valueToConvert & decimalValue) != 0) binaryValue += "1";
        	else binaryValue += "0";
            decimalValue >>= 1;
        }
        
        return binaryValue;
	}

    
   
    static int printBitCount()
    {
        int maximumCount = 0;
        int currentCount = 0;
        
        for (char singlBitInformation : binaryValue.toCharArray()) 
        {            
            if(singlBitInformation == '1')
            {
                currentCount++;
                if(currentCount > maximumCount) maximumCount = currentCount;
            } 
            else
            {
                if(currentCount > maximumCount) maximumCount = currentCount;
                currentCount = 0;
            }            
        }
        return maximumCount;
    }
    
    static long validateUserInput()
    {
        String userInput = "";
        while(!userInput.matches("\\d+"))
        {
            userInput = JOptionPane.showInputDialog("Bitte gültige Ganzzahl eingeben!");	
        }
        return Long.parseLong(userInput);
    }
}
