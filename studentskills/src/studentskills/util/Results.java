package studentskills.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import studentskills.util.MyLogger.DebugLevel;

/**
 * The Results class to implement witetofile and writetostdout
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	
	private static String outputString="", errStr="";
	private String outputFileName1,errorFile;
	private DebugLevel debugLevel;
	
	public Results() {
		this.debugLevel = DebugLevel.RESULTS;
		MyLogger.writeMessage("in Results class constructor", debugLevel);
	}
	
	
	public void setOutputString(String s) {
		outputString = s;
	}
	
	/**
	 * Sets OutputFileName
	 *
	 * @param  String  filename
	 * @return NULL	   
	 */
	public void setOutputFileName1(String out1) {
		outputFileName1 = out1;
	}
	
	
	/**
	 * Get Output
	 *
	 * @param  NULL
	 * @return String  outputstring	   
	 */
	public static String getoutputString() {
		return outputString;
	}
	
	/**
	 * Sets OutputFileName
	 *
	 * @param  String  filename
	 * @return NULL	   
	 */
	public void setErrorFile(String out1) {
		errorFile = out1;
	}
	
	/**
	 * appends result
	 *
	 * @param  String	Output of line processor
	 * @return NULL   
	 */
	public void storeErr(String lineToAdd) {
		errStr += lineToAdd + "\n";
	}
	
	/**
	 * appends result
	 *
	 * @param  String	Output of line processor
	 * @return NULL   
	 */
	public void store(String lineToAdd) {
		outputString += lineToAdd;
	}
	
	/**
	 * Writes generated output on console.
	 *
	 * @param  null
	 * @return null
	 */
	@Override
	public void writeToStdout() {
		// TODO Auto-generated method stub
		System.out.println(outputString);
	}

	
	/**
	 * Writes generated output to output.txt
	 *
	 * @param  null
	 * @return null
	 */
	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub
		BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(outputFileName1, false));
            writer.write(outputString);
            writer.close();
            
            writer = new BufferedWriter(new FileWriter(errorFile, false));
            writer.write(errStr);
            writer.close();
            
        } catch(IOException e) {
            System.err.println("IO Exception: Filename was not a proper name.");
            System.exit(1);
        } finally {
            try {
                writer.close();
            } catch(IOException e) {
                System.err.println("BufferedWriter not found.");
                System.exit(1);
            }
        }
	}
	
	
	/**
     * Default toString, needed for debugging here.
     * @return String with values that will be stored by Result
     */
    public String toString() {
        return ("Output String: " + outputString + "\nOutput Filename: " + outputFileName1 + "\n");
    }
	
}
