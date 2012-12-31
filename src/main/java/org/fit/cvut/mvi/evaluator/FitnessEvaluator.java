package org.fit.cvut.mvi.evaluator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FitnessEvaluator {

	private String pathToNetLogo;
	private String pathToTemplate;
	private String pathToSetupFile;
	
	/**
	 * NetLogo code for random move. Can be used for moveSheepBody and moveWolfBody arguments.
	 */
	public final static String RANDOM_MOVE = "report list random 360 random-float 1";
	public final static String INTELLIGENT_SHEEP = "report list (360 * (1 / (1 + exp (-1.0 * ((count sheep in-cone 1 120) + (count patches at-points [[1 0] [2 0] [3 0] [4 0] [5 0]] with [pcolor = green])))))) (1 * (1 / (1 + exp (-1.0 * ((count patches at-points [[1 0] [2 0] [3 0] [4 0] [5 0]] with [pcolor = green]) * ((count patches at-points [[0 1] [0 2] [0 3] [0 4] [0 5]] with [pcolor = green]) * ((count sheep-on patches at-points [[-2 -4] [-1 -4] [0 -4] [1 -4] [2 -4] [-2 -3] [-1 -3] [0 -3] [1 -3] [2 -3]]) + (count wolves-on patches at-points [[-2 4] [-1 4] [0 4] [1 4] [2 4] [-2 3] [-1 3] [0 3] [1 3] [2 3]]))))))))";
	public final static String INTELLIGENT_WOLVES = "report list (360 * (1 / (1 + exp (-1.0 * ((((count sheep in-cone 5 120) + (count wolves in-cone 1 120)) + (count sheep in-cone 5 120)) + (count wolves-on patches at-points [[-2 4] [-1 4] [0 4] [1 4] [2 4] [-2 3] [-1 3] [0 3] [1 3] [2 3]])))))) (1 * (1 / (1 + exp (-1.0 * (((count patches at-points [[0 1] [0 2] [0 3] [0 4] [0 5]] with [pcolor = green]) + (count patches at-points [[0 1] [0 2] [0 3] [0 4] [0 5]] with [pcolor = brown])) + (count patches at-points [[0 1] [0 2] [0 3] [0 4] [0 5]] with [pcolor = brown]))))))";
	
	/**
	 * Determines whether or not the temporary .nlogo files should be deleted after execution.
	 */
	public static boolean deleteAfterExecution = true;
	
	/**
	 * Creates new instance of fitness evaluator.
	 * @param pathToNetLogo Path to the NetLogo.jar in the filesystem.
	 * @param pathToTemplate Path to the MI-MVI NetLogo template (sablona.nlogo) in the filesystem.
	 * @param pathToSetupFile Path to the MI-MVI NetLogo experiments setup file (sablona.xml) in the filesystem.
	 */
	public FitnessEvaluator(String pathToNetLogo, String pathToTemplate, String pathToSetupFile) {
		
		this.pathToNetLogo = pathToNetLogo;
		this.pathToTemplate = pathToTemplate;
		this.pathToSetupFile = pathToSetupFile;
	}
	
	/**
	 * Calculates the fitness of sheep w.r.t. given sheep and wolf control codes.
	 * @param moveSheepBody Body of the NetLogo move-sheep reporter.
	 * @param moveWolfBody Body of the NetLogo move-wolf reporter.
	 * @return Sheep fitness.
	 */
	public double evaluateSheepFitness(String moveSheepBody, String moveWolfBody) {
		
		try {
			
			// Create temporary directory if not exists
			File tmpDir = new File("tmp");
			if(!tmpDir.exists()) {
				tmpDir.mkdir();
			}

			String line;
			
			// Concatenate code provided with the template
			File template = new File(this.pathToTemplate);
			BufferedReader templateReader = new BufferedReader(new FileReader(template));

			File nlogoFile = new File("tmp/" + Math.round(1e16*Math.random()) + ".nlogo");
			FileWriter nlogoWriter = new FileWriter(nlogoFile);
			
			nlogoWriter.write("to-report move-sheep\n");
			nlogoWriter.write(moveSheepBody + "\n");
			nlogoWriter.write("end\n\n");
			
			nlogoWriter.write("to-report move-wolf\n");
			nlogoWriter.write(moveWolfBody + "\n");
			nlogoWriter.write("end\n\n");
			
			while((line = templateReader.readLine()) != null) {
				nlogoWriter.write(line + "\n");
			}
			
			templateReader.close();
			nlogoWriter.close();
			
			
			// Execute the process
			Process process = Runtime.getRuntime().exec(buildCommand(nlogoFile));
			
			// Create output reader
			InputStream output = process.getInputStream();
			BufferedReader outputReader = new BufferedReader(new InputStreamReader(output));
			
			// Variables for averaging fitness
			double fitnessSum = 0;
			int fitnessCount = 0;
			
			// Regular expression pattern for matching relevant lines
			Pattern p = Pattern.compile("^(\"\\w+\",){10}\"(\\d+)\",\"(\\d+)\",\"(\\d+)\"$");
			
			// Read the lines until the process finishes its output
			while((line = outputReader.readLine()) != null) {
				
				Matcher m = p.matcher(line);
				if(m.matches()) {
					
					int tick = Integer.parseInt(m.group(2));
					int numSheep = Integer.parseInt(m.group(3));
					int numWolves = Integer.parseInt(m.group(4));
					
					if(numSheep == 0 || numWolves == 0 || tick == 300) {
						
						if(numSheep == 0 || numWolves == 0) {
							fitnessSum += (numWolves == 0 ? 1 : -1) * 100;
							fitnessSum += 10*(numSheep - numWolves);
						}
						else {
							fitnessSum += numSheep - numWolves;
						}
						
						fitnessCount ++;
					}
				}
			}
			
			// Delete the temporary file if set so.
			if(FitnessEvaluator.deleteAfterExecution) {
				nlogoFile.delete();
			}
			
			// Return averaged fitness
			return fitnessSum / fitnessCount;
		}
		catch(Exception ex) {
			
			ex.printStackTrace();
			
			// If anything goes wrong, burn in hell!
			return Double.NEGATIVE_INFINITY;
		}
	}
	
	/**
	 * Calculates the fitness of wolves w.r.t. given sheep and wolf control codes.
	 * @param moveSheepBody Body of the NetLogo move-sheep reporter.
	 * @param moveWolfBody Body of the NetLogo move-wolf reporter.
	 * @return Wolves fitness.
	 */
	public double evaluateWolvesFitness(String moveSheepBody, String moveWolfBody) {
		
		// Fitness of wolves = inverse fitness of sheep.
		return -evaluateSheepFitness(moveSheepBody, moveWolfBody);
	}
	
	private String buildCommand(File nlogoFile) {
	    StringBuilder command = new StringBuilder();
	    command.append("java -Xmx1536m -Dfile.encoding=UTF-8 -cp ");
	    command.append(pathToNetLogo);
	    command.append(" org.nlogo.headless.Main --model ");
	    command.append(nlogoFile.getAbsolutePath());
	    command.append(" --setup-file ");
	    command.append(pathToSetupFile);
	    command.append(" --experiment experiment --table -");

	    return command.toString();
	}

}
