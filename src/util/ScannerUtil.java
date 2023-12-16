package util;

import java.util.Scanner;

/**
 * @author Abdul Hanan
 * @created_at 16-Dec-2023
 */

public class ScannerUtil {
	public static int getInteger() {
        @SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
        int returnValue;
        try {
        	returnValue = scan.nextInt();
        }
        catch(Exception e){
        	System.out.print("Enter an Integer: ");
        	returnValue = getInteger();
        }
        
        return returnValue;
	}
}
