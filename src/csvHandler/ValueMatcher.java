package csvHandler;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;

public class ValueMatcher {
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
			source = new CSVReader(new FileReader("C://Users/Garyg/Desktop/MasterData/Parts Location Qty with Trace ID.csv"), '|', '"', 1);
			target = new CSVReader(new FileReader("C://Users/Garyg/Desktop/MasterData/MMNov6Beg Balance-ZETA.csv"), '|', '"', 1);
			
			List<String[]> tLines = target.readAll();
			
			while ((sLine = source.readNext()) != null) {
//				System.out.println(sLine[0]);
				for (String[] tLine : tLines){
					if(sLine[0].contentEquals(tLine[0])){
						String s = tLine[7];
						if (s.contentEquals(" -   ")){
							s = "0";
						}
						System.out.print(s);
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
