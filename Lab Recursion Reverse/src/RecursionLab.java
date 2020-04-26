import java.io.*;

public class RecursionLab{
    
    public static void reversePrint (String inString){
       
		if (inString.length() == 0)		// if string is not empty
		{
			return;
		}
		else {
			System.out.println(inString.charAt(inString.length() - 1));
			
		}
    }
    
    public static String reverseString(String inString) {
    		if (inString.length() == 0) {
    			return "";
    		}
    		else {
    			char lastChar = inString.charAt(inString.length() - 1);
    			String reverse = inString.substring(0, inString.length() - 1);
    			String reversedFinal = reverseString(reverse);
    			return lastChar + reversedFinal;
    		
    		}
    }

	    
    public static void main(String[] args){
        String inString = "abcde";

		// test reversePrint
		reversePrint(inString);		
		
		// test reverseString
		String revString = reverseString(inString);
		System.out.println(revString);
    }
}
