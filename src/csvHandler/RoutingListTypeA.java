package csvHandler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.opencsv.CSVReader;

public class RoutingListTypeA {
	public static void main(String[] args){
		int groupCounter = 1;
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
		String currentActivity = "";
		String preActivityBomItem = "";
		String temp = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			DateFormat NewDateFormat = new SimpleDateFormat("yyyyMMddHHMMSS");
			Date date = new Date();
			System.out.println("Start at: "+date);			
			
			reader = new CSVReader(new FileReader("C://Users/Garyg/Desktop/MasterData/Routing_test2.csv"), '|', '"', 0);
//			fw = new FileWriter("C://Users/Garyg/Desktop/MasterData/Results/Routing_Reformat"+"_"+NewDateFormat.format(date)+".csv");
			while ((line = reader.readNext()) != null) {
				
				if (!(currentHeader.contentEquals(line[1]) && !currentActivity.contentEquals(line[2]))){
					if (currentHeader.contentEquals(line[1])){
						groupCounter++;						
					}else{
						groupCounter = 1;
					}			
					isHeaderNeed = true;
				} else {
					isHeaderNeed = false;
				}
							
				
				if(isHeaderNeed){
					currentHeader = line[1];
//				    System.out.println(line[0]);
					
					String header = "H"+s+
									currentHeader+s+
									plant+s+
									changeNum+s+
									groupCounter+s+
									line[14]+s+
									taskListUsage+s+
									status+s+s+
									line[13]+s+s;
					System.out.println(header);
					if (!preActivityBomItem.contentEquals("") && groupCounter > 1){
						System.out.println(preActivityBomItem);
					}
//					fw.write(header+"\n");
				}
				

				String bomItem = "S"+s+
								 line[2]+s+
								 line[3]+s+
								 plant+s+								 
								 line[4]+s+s+								 
							     line[5]+s+s+
							     line[6]+s+
							     line[13]+s+
							     line[7]+s+
							     hr+s+
							     line[15]+s+
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
							     line[19];					 
				System.out.println(bomItem);
				
//				if (!(currentHeader.contentEquals(line[1]) && !currentActivity.contentEquals(line[2]))){
//					if (currentHeader.contentEquals(line[1])){
//						if (!currentActivity.contentEquals(line[2])){
//							preActivityBomItem = temp;
//						}
//					}
//				}
				
				if (!currentActivity.contentEquals(line[2])){
					preActivityBomItem = temp;
				}
				
				currentActivity = line[2];
				temp = bomItem;
//				fw.write(bomItem+"\n");
				
			}
			reader.close();
//			fw.close();

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
