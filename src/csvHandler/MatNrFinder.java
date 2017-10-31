package csvHandler;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;

public class MatNrFinder {
	public static void main(String[] args){
		long t1 = System.currentTimeMillis();
		// System.out.println("******************"+ t1
		// +"*********************");

		// Build reader instance
		// Read data.csv
		// Default seperator is comma
		// Default quote character is double quote
		// Start reading from line number 2 (line numbers start from zero)
		CSVReader source = null;
		CSVReader target = null;

		String[] sLine;
		
		try {
			source = new CSVReader(new FileReader("C://Users/Garyg/Desktop/MasterData/source.csv"), ',', '"', 1);
			target = new CSVReader(new FileReader("C://Users/Garyg/Desktop/MasterData/target.csv"), ',', '"', 1);
			
			List<String[]> tLines = target.readAll();
			
			while ((sLine = source.readNext()) != null) {
//				System.out.print(sLine[0]);
				for (String[] tLine : tLines){
					if(sLine[0].contentEquals(tLine[1])){
						System.out.print(tLine[2]);
						break;
					}
				}
				System.out.println();
			}
			
			source.close();
			target.close();
			
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

}
