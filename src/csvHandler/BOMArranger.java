package csvHandler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

public class BOMArranger {
	public static void main(String[] args){
		String validFromDate = "20171001";
		String validToDate = "20991231";
		String bomUsage = "1";
		String bomStatus = "1";
		String changeNum = "CCC_INITIAL";
		String plant = "6200";
		String authGrp = "";
		boolean isHeaderNeed;
		
		CSVReader reader = null;
		String[] line;
		String currentHeader = "";
		try {
			reader = new CSVReader(new FileReader("C://Users/Garyg/Desktop/MasterData/Query_BOM_20171020.csv"), ',', '"', 1);
			while ((line = reader.readNext()) != null) {
				
				if (!currentHeader.contentEquals(line[3])){
					isHeaderNeed = true;
				} else {
					isHeaderNeed = false;
				}				
				
				if(isHeaderNeed){
					currentHeader = line[3];
//				    System.out.println(line[0]);
					if (line[3].startsWith("81")){
						authGrp = "Z100";
					}else{
						authGrp = "Z200";
					}
					String header = "H,,"+
									currentHeader+","+
									bomUsage+","+
									line[2]+","+
									changeNum+","+
									plant+","+
									line[5]+","+
									line[4]+","+
									validFromDate+","+
									validToDate+","+
									authGrp+","+
									bomStatus;					
					System.out.println(header);
				}
				
				String bomItem = "L"+","+
								 line[10]+","+
								 "L"+","+
								 line[13]+","+
								 line[12]+","+
								 line[18]+","+
								 line[14]+","+
								 line[15]+","+
								 line[21]+",,,,,,,"+
								 validFromDate+","+
								 validToDate+",,,,,,,"+
								 line[29];							 
				System.out.println(bomItem);
				
			}
			reader.close();
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
