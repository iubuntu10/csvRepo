package my_md.finder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.monitorjbl.xlsx.StreamingReader;

/**
 * Hello world!
 *
 */
public class ExcelReader {
	public static void main(String[] args) {
		// System.out.println("Hello World!");
		FileInputStream myFgFis = null;
		FileInputStream myBomFis = null;
		
		Workbook myBomWb = null;
		//XSSFWorkbook myBomWb = null;
		int i = 0;
		try {
			// File fgList = new File("C://Users/Garyg/Desktop/MasterData/Material_list.xlsx");
			File bomList = new File("C://Users/Garyg/Desktop/MasterData/BOM_test.xlsx");

			// myFgFis = new FileInputStream(fgList);
			//myFgWb = new XSSFWorkbook(myFgFis);

			// OPCPackage opcPackageFg = OPCPackage.open(fgList);
			// myFgWb = new XSSFWorkbook(opcPackageFg);

			myBomFis = new FileInputStream(bomList);
			// myBomWb = new XSSFWorkbook(myBomFis);

			// OPCPackage pkg = OPCPackage.open(bomList);
			// myBomWb = new XSSFWorkbook(pkg);

			//XSSFSheet myFgSheet = myFgWb.getSheetAt(0);

			//XSSFSheet myBomSheet = myBomWb.getSheetAt(0);

			myBomWb = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(myBomFis);
			//myBomWb = WorkbookFactory.create(bomList);
			Sheet myBomSheet1 = myBomWb.getSheetAt(0);
			Sheet myBomSheet2 = myBomWb.getSheetAt(1);
			// Iterator<Row> rowIteratorFg = myFgSheet.iterator();
			// Iterator<Row> rowIteratorBom = myBomSheet.iterator();

			for (Row rowFg : myBomSheet1) {
				for (Cell cellFg : rowFg) {
					int j=0;
					String cellFgContent = cellFg.getStringCellValue();
					System.out.println(""+ ++i + " " +cellFgContent);
					
					for (Row rowBom : myBomSheet2) {
						String cellBomContent = rowBom.getCell(2).getStringCellValue();
//						System.out.println("  "+ ++j +" "+cellBomContent);
						if (cellBomContent.equals(cellFgContent)){
							System.out.println("  "+ ++j +" "+cellBomContent);
						}
						//System.out.println();
					}
				}
				System.out.println();
			}
			

			/*
			 * while (rowIteratorFg.hasNext()) { int j = 0; Row rowFg =
			 * rowIteratorFg.next(); String cellFg =
			 * rowFg.getCell(0).getStringCellValue(); System.out.println(++i +
			 * " " + cellFg);
			 * 
			 * 
			 * Iterator<Row> rowIteratorBom = myBomSheet.iterator();
			 * 
			 * while (rowIteratorBom.hasNext()) { Row rowBom =
			 * rowIteratorBom.next(); String cellBom =
			 * rowBom.getCell(2).getStringCellValue(); if
			 * (cellBom.equals(cellFg)) { String secondLayer =
			 * rowBom.getCell(9).getStringCellValue();
			 * System.out.println("     " + ++j + " " + secondLayer); } }
			 * System.out.println(); }
			 */
			System.out.println();
			System.out.println("============================");
			System.out.println();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
		}
	}
}
			
