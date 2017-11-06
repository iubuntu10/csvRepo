package csvHandler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.opencsv.CSVReader;

public class RoutingList {
	public static void main(String[] args){
		String groupCounter = "";
		String taskListUsage = "1";
		String changeNum = "CCC_INITIAL";
		String plant = "6200";
		String status = "4";
		String hr = "HR";
		String s = "|";
		boolean isHeaderNeed;
		
		CSVReader reader = null;
		FileWriter fw = null;
		String[] line;
		String currentHeader = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			DateFormat NewDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			System.out.println("Start at: "+date);			
			
			reader = new CSVReader(new FileReader("C://Users/Garyg/Desktop/MasterData/Routing_Q110_11022017r.csv"), '|', '"', 0);
			fw = new FileWriter("C://Users/Garyg/Desktop/MasterData/Results/Routing_Reformat"+"_"+NewDateFormat.format(date)+".csv");
			while ((line = reader.readNext()) != null) {				
				if (!(currentHeader.contentEquals(line[1]) && groupCounter.contentEquals(line[3]))){
					isHeaderNeed = true;
				} else {
					isHeaderNeed = false;
				}				
				
				if(isHeaderNeed){
					currentHeader = line[1];
					groupCounter = line[3];
					
					String header = "H"+s+
									currentHeader+s+
									plant+s+
									changeNum+s+
									groupCounter+s+
									line[15]+s+
									taskListUsage+s+
									status+s+s+
									line[14]+s+s;
//					System.out.println(header);
					fw.write(header+"\n");
				}
				
				String bomItem = "S"+s+
								 line[2]+s+
								 line[4]+s+
								 plant+s+								 
								 line[5]+s+s+								 
							     line[6]+s+s+
							     line[7]+s+
							     line[14]+s+
							     line[8]+s+
							     hr+s+
							     line[16]+s+
							     line[9]+s+
							     hr+s+
							     line[17]+s+
							     line[10]+s+
							     hr+s+
							     line[18]+s+
							     line[11]+s+
							     hr+s+
							     line[19]+s+
							     line[12]+s+
							     hr+s+
							     line[20];						 
//				System.out.println(bomItem);
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
