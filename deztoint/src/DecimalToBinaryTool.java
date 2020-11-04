import javax.swing.*;

/**
 * Author:    Hannes
 * Created:   04.11.2020
 * 
 * CC BY
 **/

public class DecimalToBinaryTool 
{
    // Deklarieren der Variable die ich später ausgeben möchte
    private static String binaryValue = "";

    public static void main(String[] args) 
    {
        // Abfrage des Inputs und speichern in valueToCalculate.
        // Der Input wird von der Methode "validateUserInput" geprüft (s.u.)        
        long valueToCalculate = validateUserInput();

        // Hier wird die Methode aufgerufen, die den Wert von valueToCalculate umrechnet 
        calculateDecimalToBinary(valueToCalculate);		
        
        // Hier angekommen wurde durch die Methode calculateDecimalToBinary in der Variablen
        // bitSequence das Ergebnis bereits gespeichert. Jetzt muss noch alles ausgegeben werden, 
        // konsequenterweise mittels einer GUI, da die Abfrage ja auch dadurch erfolgt ist. 
        // Die dritte Methode printBitCount wird direkt in der Ausgabe abgerufen und ihr Wert ausgegeben 
        JOptionPane.showMessageDialog(new JFrame(), valueToCalculate + " in Binärschreibweise: " + binaryValue + "\n" +
        "Dieser Wert enthält " + printBitCount() + " zusammenhängende Bits.", "Decimal To Binary Tool", JOptionPane.PLAIN_MESSAGE);  
	}
    
    
	static String calculateDecimalToBinary(long valueToConvert)
	{
        long decimalValue = 1;
        
        // BEISPIEL // wir nehmen an der übergebene Wert valueToConvert war 12 (also binär 1100)        

        // Nur im einen Fall, dass der umzurechnende Wert 0 Beträgt soll auch das Ergebnis 0 sein 
        // und die Methode beendet werden. Denn 0 in Dezimalschreibweise ist auch 0 in Binärschreibweise
	    if(valueToConvert == 0)
	    {
            binaryValue += "0";
            return "";
        }
        // BEISPIEL // Überspringt diese Methode, weil 12 != 0 ist


        // Solange decimalValue kleiner als valueToConvert ist wird durch die Linksverschiebung der Stelle
        // im Binärsystem der Wert verdoppelt, bis decimalValue schließlich größer als valueToConvert ist. 
        while(decimalValue<valueToConvert)
        {
            decimalValue <<= 1;
        }
        // BEISPIEL //
        // Schleifendurchlauf      decimalValue der VOR Schleifendurchlauf geprüft wird       decimalValue NACH Durchlauf        enspricht Binärwert       
        //                  1                                                         1                                 2                         10
        //                  2                                                         2                                 4                        100                   
        //                  3                                                         4                                 8                       1000
        //                  4                                                         8                                16                      10000
        //           ABBBRUCH                                                        16                                 -                          -

        // Mit dieser Funktion soll nun aus dem ermittelten Decimal Value ein String in
        // Form eines Binärwertes erzeugt werden. Zunächst wird geprüft, ob decimalValue
        // gleich 0 ist, solange dies nicht der Fall ist, läft die Schleife.
        // In der ersten Abfrage (valueToConvert & decimalValue) passiert folgendes:
        // Der Binärwert beider Variablen wird verglichen. Dies passiert für jede einzelne
        // Stelle. Nach der Logik des "AND" wird nur ein true (1) zurückgegeben wenn
        // beide Werte auch true (1) sind. Wenn der ermittelte Binärwert nicht 0 ist, wird 
        // dem string binaryValue eine 1 hinzugefügt.
        // 
        // BEISPIEL //
        // valueToConvert an diesem Punkt:          12
        // decimalValue an diesem Punkt:            16
        // Umrechnung binär valueToConvert:      01100
        // Umrechnung binär decimalValue:        10000
        // Vergleich:                            00000   -->  Da es keinen Vergleich 1 & 1 = 1 gibt, ist der Wert 0
        // Der Else Block wird ausgelöst und binaryValue ist nun 0
        // Nun wird noch eine Stelle nach rechts verschoben, so dass decimalValue jetzt 8 ist
        //
        // zur Verdeutlichung noch der zweite Durchlauf (weil ja decimalValue nach wie vor NICHT 0 ist):
        // valueToConvert an diesem Punkt:          12
        // decimalValue an diesem Punkt:             8
        // Umrechnung binär valueToConvert:       1100
        // Umrechnung binär decimalValue:         1000
        // Vergleich:                             1000   -->  1 und 1 an der ersten Stelle ist 1, die Bedingung ist also erfüllt (!= 0)
        // Der If Block wird ausgelöst und binaryValue ist nun 0 + die nun hinzugefügte 1, also 01
        // Rechtsverschiebung, decimalValue ist jetzt 4
        //
        // USW., bis decimalValue 0 ist, die Schleife damit beendet wird und binary value mit dem 
        // Wert 01100 zurückgegeben und gleichzeitig in der ganz oben angelegten Variablen gespeichert wird 
	    while(decimalValue != 0)
	    {
            if((valueToConvert & decimalValue) != 0) binaryValue += "1";
        	else binaryValue += "0";
            decimalValue >>= 1;
        }
        
        return binaryValue;
	}

    
    // Da die Originalfunktion ähnlich wie die obige funktioniert hat, hier eine andere und
    // besser nachvollziehbare Herangehensweise. Die Methode nimmt keine Argumente an und
    // gibt einen int zurück, der der Anzahl von zusammenhängenden Einsen im BinärString entspricht
    static int printBitCount()
    {
        // Dekl der Variablen. Die Initialisierung muss man nicht machen, da aber hier später
        // der Wert als Bedingung abgefragt wird und wir Fehler vermeiden wollen, wwerden sie auf 0 gesetzt
        int maximumCount = 0;
        int currentCount = 0;

        // die Schleife durchläuft alle Elemente (also Zeichen/Chars) in einem Element,
        // hier dem String mit den Binärwerten. Also bei unserem Beispiel 12 fünfmal, da
        // in dem String der Wert 01100 gespeichert ist
        // Die Funktionsweise ist einfach: Wenn du eine 1 findest, erhöhe currentCount um 1
        // Prüfe dann ob currentCount höher ist als der bisherige Rekordwert maximumCount.
        // Wenn ja, überschreibe maxCount mit currentCount
        // Wenn du eine 0 findest prüfe genau das gleiche, setze aber anschließend 
        // currentCount auf 0 zurück. der Wert von maxCount kann also nie sinken
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


    // Kleine Helfermethode zur Abfrage des Inputs. Die Schleife läuft einfach so lange
    // und nervt mit der Abfrage bis eine gültige Zahlenfolge eingegeben wird ("\\d+")
    // Sobald die Abfrage gültig ist endet die Schleife und der Wert wird zurückgegeben
    static long validateUserInput() 
    {
        String userInput = "";
        while(!userInput.matches("\\d+"))
        {
            userInput = JOptionPane.showInputDialog("Bitte gültige Ganzzahl eingeben!");	
        }
        // Kleine Spielerei, relevant für alles unter den beiden dicken grünen Strichen
        if(userInput.matches("101010"))
        {
            startBinaryConverter(); 
            System.exit(0);
        } 
        return Long.parseLong(userInput);
    }



/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // Wie oben, nur dass jetzt ausschließlich 1 und 0 als gültige Eingaben akzeptiert werden
    static long validateUserInputOnlyBinaryFormat()
    {
        String userInput = "";
        while(!userInput.matches("[0-1]+"))
        {
            userInput = JOptionPane.showInputDialog("Bitte Binärzahl im Format 100100110 eingeben!");	
        }
        return Long.parseLong(userInput);
    }

    // Umwandlung von Binär zu Dezimal mit anderem Ansatz als oben. Sieht erstmal schlimmer aus als es ist:
    // Die Eingabe wird erstmal umgedreht (reverse), aus 1100 (also dezimal 12) wird dann zB 0011. Diese Folge
    // wird dann als Array gespeichert (haben wir bald), kurz gesagt ist das eine Kette von Variablen die einen
    // unterschiedlichen Wert haben können, aber den gleichen Datentyp haben müssen. Die Werte werden dann nacheinander
    // an den Positionen im Array gespeichert, die Index heißen. Gezählt wird immer ab 0 aufsteigend. Wir haben also
    // jetzt:
    // Index   Wert
    //     0   0
    //     0   0
    //     0   1
    //     0   1
    //
    // Im Loop, der solange läuft bis i so groß wie die Arraylänge ist wird nun einfach jedesmal wenn an der
    // Indexposition die wir gerade untersuchen eine 1 gefunden wird 2 hoch der Indexnummer zu decimalReturnValue
    // hinzugefügt. Anschließend wir der Wert zurückgegeben. 
    static long convertFromBinaryToDecimal(String binaryNumberInput)
    {
        long decimalReturnValue = 0;
        String reverseInput = new StringBuilder(new String(binaryNumberInput)).reverse().toString();
        char[] singleBits = reverseInput.toCharArray();

        for(int i = 0; i < singleBits.length ; i++)
        {
            if(singleBits[i] == '1') decimalReturnValue += Math.pow(2, i);
        }
        return decimalReturnValue;
    }

    // Dies ist einfach nur die Methode um den Input und die Ausgabe aufzurufen, sobald jemand bei der Abfrage nach der Dezimalzahl 101010 eingibt
    static void startBinaryConverter()
    {
        long binaryToCalculate = validateUserInputOnlyBinaryFormat();
        JOptionPane.showMessageDialog(new JFrame(), binaryToCalculate + " als Ganzzahl: " + convertFromBinaryToDecimal(Long.toString(binaryToCalculate)), "Binary to Decimal Tool", JOptionPane.PLAIN_MESSAGE);
    }
}
