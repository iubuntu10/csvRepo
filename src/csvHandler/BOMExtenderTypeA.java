package csvHandler;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVReader;

public class BOMExtenderTypeA {

	

	public static void main(String[] args) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		long t1 = System.currentTimeMillis();
		// System.out.println("******************"+ t1
		// +"*********************");

		// Build reader instance
		// Read data.csv
		// Default seperator is comma
		// Default quote character is double quote
		// Start reading from line number 2 (line numbers start from zero)
		CSVReader fgReader = null;
		CSVReader bomReader = null;
		FileWriter fw = null;

		try {
			// //TEST
			// fgReader = new CSVReader(new
			// FileReader("C://Users/Garyg/Desktop/MasterData/FG.csv"), ',',
			// '"', 1);
			// bomReader = new CSVReader(new
			// FileReader("C://Users/Garyg/Desktop/MasterData/BOM.csv"), ',',
			// '"', 1);

			// PROD
			fgReader = new CSVReader(new FileReader("C://Users/Garyg/Desktop/MasterData/FG_Nov21.csv"), '|', '"', 1);
			bomReader = new CSVReader(new FileReader("C://Users/Garyg/Desktop/MasterData/Query_BOM_NOV232017.csv"), '|', '"', 1);
			fw = new FileWriter("C://Users/Garyg/Desktop/MasterData/Results/MaterialMasterData_"+ dateFormat.format(date) +".csv");
			// Read all rows at once
			List<String[]> allFgRows = fgReader.readAll();
			List<String[]> allBomRows = bomReader.readAll();

			// Read CSV line by line and use the string array as you want
			int m = 0;
			int i = 0;
			for (String[] fgRow : allFgRows) {
				String fgItem = fgRow[0];
				System.out.print(".");
//				for (String fgItem : fgRow) {
//					// for (int n = 0; n < m; n++) {
//					// System.out.print(" ");
//					// }
//					// System.out.println("" + ++i + " " + fgItem);
////					System.out.println("" + fgItem);
//					getChildren(i, m, fgItem, allBomRows);
//				}
				
				getChildren(i, m, fgItem, allBomRows,fw);
			}
			long t2 = System.currentTimeMillis();
			
			date = new Date();
			System.out.println("");
			System.out.println(dateFormat.format(date));
			System.out.println("****************** " + (t2 - t1) / 1000 + " seconds *********************");

			// for (String[] ss : allBomRows){
			// System.out.println(ss[9]);
			// for(String s: ss){
			// System.out.println(s);
			// }
			// }

			//
			// // Read CSV line by line and use the string array as you want
			// String[] nextLine;
			// while ((nextLine = reader.readNext()) != null) {
			// if (nextLine != null) {
			// // Verifying the read data here
			// System.out.println(Arrays.toString(nextLine));
			// }
			// }

			fgReader.close();
			bomReader.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		

	}

	public static void getChildren(int i, int m, String fgItem, List<String[]> allBomRows, OutputStreamWriter fw) throws IOException {
		int j = 0;
		m = m + 1;
		ArrayList<String[]> list = new ArrayList<String[]>();
		for (String[] bomRow : allBomRows) {
			if (bomRow[2].trim().equals(fgItem.trim())) {
				String[] sBomRow = new String[] { bomRow[9]};
				list.add(sBomRow);
			}
		}
		if (!list.isEmpty()) {
			/*
			 * @TODO - append parents node
			 */
//			for (int n = 0; n < m; n++) {
//				System.out.print("\t");
//			}

			fw.write("\n");
			fw.write("" + fgItem+"\n");
			
			// String[] ss = new String[list.size()];
			// ss = list.toArray(ss);
			for (String[] s : list) {
				/*
				 * @TODO - append children list
				 */
//				System.out.println("   " + ++j + " " + Arrays.toString(s));
				fw.write(s[0]+"\n");
			}

			for (String s[] : list) {
				
				String s1 = s[0];
//				 for (int n = 0; n < m; n++) {
//				 System.out.print("\t");
//				 }
////				 System.out.println(" " + ++j + " " + s1);
//				System.out.println(" "+s1);
				getChildren(i, m, s1, allBomRows,fw);
			}

		} 
		else {
			if (isLayer(fgItem)) {
				/*
				 * @TODO - append this "BOM without structure" to final csv file.
				 */
//				for (int n = 0; n < m; n++) {
//					System.out.print("\t");
//				}
//				System.out.println("======no structure====");
				fw.write("\n");
				fw.write("" + fgItem + "  ==== No BOM Found ====\n");
//				System.out.println(fgItem);
			} else {
				
			}

		}

	}

	public static Boolean isLayer(String layerName) {
		if (layerName.contains("-")) {
			return false;
		}
		if (layerName.endsWith("88R")) {
			return true;
		}
		if (layerName.endsWith("99R")) {
			return true;
		}
		for (int i = 0; i < 15; ++i) {
			if (i < 10) {
				if (layerName.endsWith("0" + i + "R")) {
					return true;
				}
			} else {
				if (layerName.endsWith("" + i + "R")) {
					return true;
				}
			}
		}
		return false;
	}
}
