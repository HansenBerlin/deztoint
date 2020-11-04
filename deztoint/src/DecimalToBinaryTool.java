import javax.swing.*;

public class DecimalToBinaryTool 
{
    private static String bitSequence = "";

    public static void main(String[] args) 
    {
        long valueToCalculate = 0;

		String userInput = JOptionPane.showInputDialog("Bitte Ganzzahl eingeben!");		
        calculateDecimalToBinary(Integer.parseInt(userInput));		
        
        JOptionPane.showMessageDialog(new JFrame(), valueToCalculate + " in Binärschreibweise: " + bitSequence + "\n" +
            bitSequence + " enthält " + printBitCount() + " zusammenhängende Bits.", "Decimal To Binary Tool", JOptionPane.PLAIN_MESSAGE);
	}
    
    
	static String calculateDecimalToBinary(long valueToConvert)
	{
        long decimalValue = 1;

        //
        // Beispiel Input war 8 (also binär 1000)
        //

        // Der Dezimalwert 0 entspricht dem Binärwert 0, also 
        // bei Input 0 = Output 0 und Funktion beenden
	    if(valueToConvert == 0)
	    {
            System.out.print("0"); 
            return "";
        }
        // BEISPIEL: überspringen 

        // Solange decimalValue kleiner als der Input ist wird durch die Linksverschiebung der Wert
        // verdoppelt, bis decimalValue größer als der Input ist. Die Verdopplung findet im Binärsystem statt,
        // also:

        // binär    decimal
        // 1        =       1   verschiebe nach links, Ergebnis:
        // 10       =       2   verschiebe nach links, Ergebnis:
        // 100      =       4   verschiebe nach links, Ergebnis:
        // 1000     =       8   verschiebe nach links, Ergebnis:
        // usw.  

        while(decimalValue<valueToConvert)
        {
            decimalValue <<= 1;
        }  
        // BEISPIEL: dV ist 1
        // BEISPIEL: wird 2 4 8 (4 Loops)
        // BEISPIEL: Abbruch
        // BEISPIEL: decimalValue ist jetzt 8

        // Nun wird einmal nach rechts verschoben, also der Binärwert halbiert, kann aber raus, Anpassung s.o
        // decimalValue>>=1;

        // Und nun solange, bis der decimalValue wieder 0 ist:
        // eine 1 ausgegeben wenn der input und der decimalValue nicht null sind
        // oder ansonsten eine 0 ausgegeben bzw. rechts an die Ausgabe angefügt
        // danach gibt es eine Rechtsverschiebung und der decimalValue wird halbiert  
        
        // BEISPIEL: 
        // schreibe 1
        // dV = 4
        // schreibe 0
        // dV = 2
        // schreibe 0
        // dV = 1
        // schreibe 0
        // dV = 0
        // Abbruch

        // Bitwise Erklärung:
        // erstmal Addition klären für binärzahlen
        // dann ist es klar:
        //      1000
        //     &1000
        //     =1000
        // denn: wenn true plus true dann true. also ist 1 und 1 gleich 1
        // und: wenn true plus false (oder false plus false), false. ALso ist 0 und 0 gleich 0
        // Im ersten Durchlauf ist also das Ergebnis 1000 (bzw 8 als Dezimalzahl), was NICHT 0 ist. ALso wird eine 1 geschrieben
        // decimalValue wird nun durch die Verschiebung auf 4 gesetzt (oder 0100)
        // Im zweiten Durchlauf wird folgendes verglichen:
        //      1000
        //     &0100
        //     =0000
        // 0000 binär ist auch 0 dezimal, die Bedingung a ist ungleich 0 wird also NICHT erfüllt deshalb wird eine 1 geschrieben
	    while(decimalValue != 0)
	    {
            //if((valueToConvert&decimalValue) != 0) System.out.print("1");
        	//else System.out.print("0");
            //decimalValue>>=1;
            if((valueToConvert & decimalValue) != 0) bitSequence += "1";
        	else bitSequence += "0";
            decimalValue >>= 1;
        }
        
        return bitSequence;
	}

    
    static int printBitCount()
    {
        int maximumCount = 0;
        int currentCount = 0;

        for (char singlBitInformation : bitSequence.toCharArray()) 
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
}
