package csvHandler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.opencsv.CSVReader;

public class BOMSourceData {
	public static void main(String[] args) {

		CSVReader reader = null;
		FileWriter fw = null;
		String[] line;
		ArrayList<String[]> items = new ArrayList<String[]>();
		ArrayList<ArrayList<String[]>> boms = new ArrayList<ArrayList<String[]>>();
		String prevMat = "";
		String currMat = "";
		String temp;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			System.out.println("Start at: " + date);

			reader = new CSVReader(new FileReader("C://Users/Garyg/Desktop/MasterData/Source_Data_20171017v1.2.csv"), '|', '"', 1);
			fw = new FileWriter("C://Users/Garyg/Desktop/MasterData/Results/Source_Data_" + dateFormat.format(date) + ".csv");
			// fw = new FileWriter("C://Users/Garyg/Desktop/source_data_"+date.getDate()+".csv");

			do {
				line = reader.readNext();
				if (line != null) {
					if (currMat.contentEquals(line[2])) {
						items.add(line);
					} else {
						if (!items.isEmpty()) {
							boms.add(items);
						}

						currMat = line[2];
						items = new ArrayList<String[]>();
						items.add(line);
					}
				} else {
					if (!items.isEmpty()) {
						boms.add(items);
					}
				}

			} while (line != null);

			String header = "ROWID|WORKORDER_TYPE|WORKORDER_BASE_ID|Basic_UoM|Base_Qty|WORKORDER_LOT_ID|WORKORDER_SPLIT_ID|"
					+ "WORKORDER_SUB_ID|OPERATION_SEQ_NO|PIECE_NO|SUBORD_WO_SUB_ID|PART_ID|REFERENCE|STATUS|QTY_PER|CONVERT|"
					+ "QTY_PER_TYPE|FIXED_QTY|SCRAP_PERCENT|DIMENSIONS|DIM_EXPRESSION|USAGE_UM|EFFECTIVE_DATE|DISCONTINUE_DATE|"
					+ "DESCRIPTION|DESCRIPTION2|Description3|COMMODITY_CODE|PRODUCT_CODE|COMMODITY_CODE3|PRODUCT_CODE4\n";
			fw.write(header);

			for (ArrayList<String[]> item : boms) {

				// for(String[] s: item){
				// System.out.println(Arrays.toString(s));
				// }
				// System.out.println();
				for (String[] baseLine : item) {
					if (!baseLine[7].contentEquals("0")) {
						for (String[] compLine : item) {
							if (baseLine[7].contentEquals(compLine[10])) {
								compLine[11] = baseLine[11];
								compLine[18] = baseLine[18];
								compLine[26] = compLine[25];
								compLine[25] = baseLine[25];
								// System.out.println(Arrays.toString(compLine));
							}
						}
					}
				}
				// System.out.println();
				//
				// for(String[] s: item){
				// System.out.println(Arrays.toString(s));
				// }
				// System.out.println();

				item.removeIf(oneItem -> !oneItem[7].contentEquals("0"));
				
				for (String[] eachLine : item) {
//					temp = Arrays.toString(eachLine);
//					System.out.println(temp.substring(1, temp.length() - 1).trim().replaceAll(", ","|"));
//					fw.write(temp.substring(1, temp.length() - 1).trim().replaceAll(", ","|") + "\n");
										
					String each = "";
					for(String s: eachLine ){
						each += s + "|";
					}
//					System.out.println(each);
					fw.write(each +"\n");
				}
				// System.out.println("= = = = = = = =");

			}

			reader.close();
			fw.close();

			date = new Date();
			System.out.println("End at: " + date);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
