package csvHandler;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVReader;

public class BOMShrinker {

	public static void main(String[] args) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
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
		String[] sLine;

		try {
			// //TEST
			// fgReader = new CSVReader(new
			// FileReader("C://Users/Garyg/Desktop/MasterData/FG.csv"), ',',
			// '"', 1);
			// bomReader = new CSVReader(new
			// FileReader("C://Users/Garyg/Desktop/MasterData/BOM.csv"), ',',
			// '"', 1);

			// PROD
			fgReader = new CSVReader(new FileReader("C://Users/Garyg/Desktop/MasterData/Active_List_20171020.csv"), ',','"', 1);
			bomReader = new CSVReader(new FileReader("C://Users/Garyg/Desktop/MasterData/Query_BOM_Oct172017 by Jamie.csv"), ',', '"', 1);

			// Read all rows at once
			List<String[]> allBomRows = bomReader.readAll();

			while ((sLine = fgReader.readNext()) != null) {
				String temp;
				for (String[] allBomRow : allBomRows) {
					if (sLine[0].contentEquals(allBomRow[2])) {
						temp = Arrays.toString(allBomRow);
						System.out.println(temp.substring(1, temp.length() - 1));
					}
				}
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
}