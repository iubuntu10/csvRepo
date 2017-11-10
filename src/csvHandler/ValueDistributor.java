package csvHandler;

import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
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
		double qty = 0;
		double totalQty = 0;
		double totalValue = 0;
		double eachValue = 0;
		int counter = 0;
		ArrayList<Double> arr = new ArrayList<Double>();
		
		try {

			NumberFormat format = NumberFormat.getInstance();
		    Number number;
			
			source = new CSVReader(new FileReader("C://Users/Garyg/Desktop/MasterData/Parts Location Qty with Trace ID_DONE.csv"), '|', '"', 1);
//			target = new CSVReader(new FileReader("C://Users/Garyg/Desktop/MasterData/MMNov6Beg Balance-ZETA.csv"), '|', '"', 1);
			
//			List<String[]> tLines = target.readAll();
			
			 do {
				 //read each line
				sLine = source.readNext(); 

				//if it's last mat
				if (sLine == null){
//					System.out.println(cHeader);
					for (Double batchQty: arr){
						totalQty += batchQty;
					}
//					System.out.println("value per unit = "+totalValue/totalQty);
					for (Double batchQty: arr){
						eachValue = batchQty*(totalValue/totalQty);
						System.out.println(String.format("%,.2f", eachValue));
					}
				//if it's a new mat
				}else if (!(cHeader.contentEquals(sLine[0]))){
					isNewMat = true;
					
					number = format.parse(sLine[5]);
					qty = number.doubleValue();
					
					//print the list, and init the array
					if (!arr.isEmpty()){
//						System.out.println(cHeader);
						for (Double batchQty: arr){
							totalQty += batchQty;
						}
//						System.out.println("value per unit = "+totalValue/totalQty);
						for (Double batchQty: arr){
							eachValue = batchQty*(totalValue/totalQty);						
							System.out.println(String.format("%,.2f", eachValue));
						}
						totalQty = 0;
						arr = new ArrayList<Double>();
					}
					
					cHeader = sLine[0];					
					totalValue = format.parse(sLine[8]).doubleValue();
				//or it'd be same mat
				} else {
					isNewMat = false;
					number = format.parse(sLine[5]);
					qty = number.doubleValue();
				}
				
				arr.add(qty);
				
			} while (sLine != null);
			
			source.close();
//			target.close();
			
		}catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

}
