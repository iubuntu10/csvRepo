package csvHandler;

import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import com.opencsv.CSVReader;

public class UnbalanceFinder {
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
		String cHeader = "";
		boolean isNewMat = false;
		double total = 0;
		int counter = 0;
		


		
		try {
			NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
		    Number number = format.parse("-1,234");
		    
		    
			source = new CSVReader(new FileReader("C://Users/Garyg/Desktop/MasterData/mb51eq110.csv"), '|', '"', 1);
//			target = new CSVReader(new FileReader("C://Users/Garyg/Desktop/MasterData/ConvVisual2SAPMatlNum-ALL-EQ1.csv"), '|', '"', 1);
			
//			List<String[]> tLines = target.readAll();
			
			while ((sLine = source.readNext()) != null) {
//				counter ++;
//				System.out.println(counter);
				if (!(cHeader.contentEquals(sLine[0]))){
										
					if (total != 0){
						System.out.println(cHeader);
					}
					
					isNewMat = true;

					number = format.parse(sLine[6]);
					total = number.doubleValue();
					
				} else {
					isNewMat = false;
				}
				
				if(!isNewMat){
					number = format.parse(sLine[6]);
					total += number.doubleValue();
				} else {
					cHeader = sLine[0];
				}
				
				

			}
			
			source.close();
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
