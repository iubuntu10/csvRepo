package csvHandler;

import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.opencsv.CSVReader;

public class ValueDistributor {
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
		Map<Double, Double> map = null;
		
		try {

			NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
		    Number number;
			
			source = new CSVReader(new FileReader("C://Users/Garyg/Desktop/MasterData/Parts Location Qty with Trace ID_DONE.csv"), '|', '"', 1);
//			target = new CSVReader(new FileReader("C://Users/Garyg/Desktop/MasterData/MMNov6Beg Balance-ZETA.csv"), '|', '"', 1);
			
//			List<String[]> tLines = target.readAll();
			
			while ((sLine = source.readNext()) != null) {
				if (!(cHeader.contentEquals(sLine[0]))){
					
					if (total != 0){
						System.out.println(cHeader);
					}
					
					isNewMat = true;
					
					if (map!=null){
						for (Double key: map.keySet()){
							total += key;
						}
					}
					
					
					
					map = new LinkedHashMap<Double,Double>();
					
					
					
				} else {
					isNewMat = false;
				}
				
				if(!isNewMat){
					
				} else {
					cHeader = sLine[0];
				}
				
				
			}
			
			source.close();
//			target.close();
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

}
