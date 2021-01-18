package studentskills.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import studentskills.mytree.TreeHelper;
import studentskills.util.FileProcessor;
import studentskills.util.MyLogger;
import studentskills.util.Results;
import studentskills.util.StudentHelper;
import studentskills.validations.FileNotExistException;

/**
 * @author Kasturi T. More
 *
 */

/**
 * The Driver class
 */
public class Driver {
	/**
	 * An int variable to acceptable number of command line arguments
	 */
	static int debugLevel = -1;
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 7;

	public static void main(String[] args) throws FileNotExistException, InvalidPathException, SecurityException, FileNotFoundException, IOException {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if ((args.length != 7) || (args[0].equals("${input}")) || (args[1].equals("${modify}")) || (args[2].equals("${out1}")) || (args[3].equals("${out2}")) || (args[4].equals("${out3}")) || (args[5].equals("${error}")) || (args[6].equals("${debug}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}
		//System.out.println("Hello World! Lets get started with the assignment");
	
		StudentHelper sh = new StudentHelper();
		sh.checkFile(args[0]);
		sh.checkFile(args[1]);
		Results rs = new Results();

		rs.setErrorFile(args[5]);
		debugLevel = Integer.parseInt(args[6]);
		
		MyLogger.setDebugValue(debugLevel);
		
		TreeHelper replicaTree0 = new TreeHelper(0);
		TreeHelper replicaTree1 = new TreeHelper(1);
		TreeHelper replicaTree2 = new TreeHelper(2);
		
		FileProcessor input1 = new FileProcessor(args[0]);
		sh.insertFileProcessor(input1,replicaTree0, rs);
		//System.out.println("Output after Insertion -----------------");
		//sh.displayResult(replicaTree0,args[2],args[3],args[4],rs);
		
		FileProcessor modFile = new FileProcessor(args[1]);
		sh.modifyFileProcessor(modFile,replicaTree0, rs);
		System.out.println("Output after Modification -----------------");
		sh.displayResult(replicaTree0,args[2],args[3],args[4],rs);
		
	}
}
