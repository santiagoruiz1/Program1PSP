import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class ReadFiles {
	
	private String path;
	
	public ReadFiles(String file_path){
		path = file_path;
	}
	
	public LinkedList<String> OpenFile() throws IOException{
		FileReader fr = new FileReader(path);
		BufferedReader buffr = new BufferedReader(fr);
		
		int numberOfLines = readLines();
		LinkedList<String> list = new LinkedList<String>();
		
		for (int i = 0; i < numberOfLines; i++) {
			list.add(buffr.readLine());
		}
		
		buffr.close();
		return list;
	}
	
	int readLines() throws IOException{
		FileReader file_to_read = new FileReader(path);
		BufferedReader bf = new BufferedReader(file_to_read);
		
		String aLine;
		int numberOfLines = 0;
		while((aLine = bf.readLine())!= null){
			numberOfLines++;
		}
		bf.close();
		return numberOfLines;
	}
	
	public static double calculateMean(LinkedList<String> list){
		double sum = 0.0;
		double mean = 0.0;
		for (int i = 0; i < list.size(); i++) {
			sum = sum + Double.parseDouble(list.get(i));
		}
		mean = sum/list.size();
		return mean;
	}
	
	public static double calculateStdDev(LinkedList<String> list){
		double num = 0.0;
		double stdDev = 0.0;
		double mean = calculateMean(list);
		for (int i = 0; i < list.size(); i++) {
			num = num + (Math.pow((Double.parseDouble(list.get(i))-mean), 2));
		}
		stdDev = Math.sqrt(num/(list.size()-1));
		return stdDev;
	}
	
	public static void main(String[] args) throws IOException{
		String file_name = "./datos3.txt";
		double mean,stdDev;
		
		try {
			ReadFiles file = new ReadFiles(file_name);
			LinkedList<String> dates = file.OpenFile();
			System.out.println("Los datos son:");
			for (int i = 0; i < dates.size(); i++) {
				System.out.println(dates.get(i));				
			}
			mean = calculateMean(dates);
			stdDev = calculateStdDev(dates);
			System.out.println("\nLa media es: " + mean);
			System.out.println("La desviación estandar es: " + stdDev);
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
