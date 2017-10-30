package csvHandler;


import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

import com.opencsv.CSVReader;

public class BOMStructureTypeA {
	public static void main(String[] args) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		
		long t1 = System.currentTimeMillis();
		
		CSVReader fgReader = null;
		CSVReader bomReader = null;
		String[] sLine;

		try {
			fgReader = new CSVReader(new FileReader("C://Users/Garyg/Desktop/MasterData/FG_20170927.csv"), ',', '"', 1);
			bomReader = new CSVReader(new FileReader("C://Users/Garyg/Desktop/MasterData/Query_BOM_20171017.csv"), ',', '"', 1);

			// Read all rows at once
//			List<String[]> allFgRows = fgReader.readAll();
			List<String[]> allBomRows = bomReader.readAll();

			// Read CSV line by line and use the string array as you want
			int m = 0;
			int i = 0;
			while ((sLine = fgReader.readNext()) != null) {
				getChildren(i, m, sLine[2], allBomRows, sLine[2]);
			}

			fgReader.close();
			bomReader.close();
			
			long t2 = System.currentTimeMillis();
			
			date = new Date();
			System.out.println(dateFormat.format(date));
			System.out.println("****************** " + (t2 - t1) / 1000 + " seconds *********************");

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void getChildren(int i, int m, String fgItem, List<String[]> allBomRows, String layer00R) {
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
			
//			System.out.println();
						
//			System.out.println(fgItem);
						
			// String[] ss = new String[list.size()];
			// ss = list.toArray(ss);
//			for (String[] s : list) {
//				/*
//				 * @TODO - append children list
//				 */
////				System.out.println("   " + ++j + " " + Arrays.toString(s));
//				System.out.println(s[0]);
//			}

			for (String s[] : list) {
				
				String s1 = s[0];
//				 for (int n = 0; n < m; n++) {
//				 System.out.print("\t");
//				 }
//				 System.out.println(" " + ++j + " " + s1);
				System.out.println(layer00R +","+s1);
				getChildren(i, m, s1, allBomRows, layer00R);
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
