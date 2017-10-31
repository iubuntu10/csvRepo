package csvHandler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import com.opencsv.CSVReader;

public class LessQtyRemover {
	public static void main(String[] args){
		
		
		CSVReader reader = null;
		FileWriter fw = null;
		String[] line;
		String currMat = "";
		String temp;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();			
			System.out.println("Start at: "+dateFormat.format(date));
			
			reader = new CSVReader(new FileReader("C://Users/Garyg/Desktop/MasterData/LEQty.csv"), ',', '"', 1);
			fw = new FileWriter("C://Users/Garyg/Desktop/LEQty_"+ dateFormat.format(date) +".csv");
			while ((line = reader.readNext()) != null) {
				if (!currMat.contentEquals(line[0])){
					temp = Arrays.toString(line);
//					System.out.println(temp.substring(1, temp.length() - 1).trim().replaceAll(", ",","));
					fw.write(temp.substring(1, temp.length() - 1)+"\n");
					currMat = line[0];
				}
			}
			
			reader.close();
			fw.close();
			
			date = new Date();
			System.out.println("End at: "+dateFormat.format(date));
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
