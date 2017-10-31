package csvHandler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.opencsv.CSVReader;

public class BOMArrangerTypeA {
	public static void main(String[] args){
		String validFromDate = "20171001";
		String validToDate = "20991231";
		String bomUsage = "1";
		String bomStatus = "1";
		String changeNum = "CCC_INITIAL";
		String plant = "6200";
		String authGrp = "";
		boolean isHeaderNeeded;
		
		CSVReader reader = null;
		FileWriter fw = null;
		String[] line;
		String currentHeader = "";
		String d = "|";
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			DateFormat NewDateFormat = new SimpleDateFormat("yyyyMMddHHMMSS");
			Date date = new Date();
			System.out.println("Start at: "+date);
			
			
			reader = new CSVReader(new FileReader("C://Users/Garyg/Desktop/MasterData/Results/Source_Data_" + dateFormat.format(date) + ".csv"), '|', '"', 1);			
			fw = new FileWriter("C://Users/Garyg/Desktop/MasterData/Results/BOM_reformat"+"_"+NewDateFormat.format(date)+".csv");
			while ((line = reader.readNext()) != null) {
				
				if (!currentHeader.contentEquals(line[2])){
					isHeaderNeeded = true;
				} else {
					isHeaderNeeded = false;
				}
				
				if(isHeaderNeeded){
					currentHeader = line[2];
//				    System.out.println(line[0]);
//					if (line[3].startsWith("81")){
//						authGrp = "Z100";
//					}else{
//						authGrp = "Z200";
//					}
					String header = "H||"+
									currentHeader+"|"+
									bomUsage+"|"+
									currentHeader+"|"+
									changeNum+"|"+
									plant+"|"+
									line[4]+"|"+
									line[3]+"|"+
									validFromDate+"|"+
									validToDate+"|"+
									authGrp+"|"+
									bomStatus;					
					System.out.println(header);
					fw.write(header+"\n");
				}
				
				String bomItem = "L"+"|"+
								 line[9]+"|"+
								 "L"+"|"+
								 "|"+
								 line[11]+"|"+
								 line[15]+"|"+
								 line[21]+"|"+
								 line[12]+"|"+
								 line[18]+"|||||||"+
								 validFromDate+"|"+
								 validToDate+"|||||||"+
								 line[26];							 
				System.out.println(bomItem);
				fw.write(bomItem+"\n");				
			}
			
			reader.close();
			fw.close();
			
			date = new Date();
			System.out.println("End at: "+date);
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
