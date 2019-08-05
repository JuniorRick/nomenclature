package crdm.nomenclature.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import crdm.nomenclature.entity.Contract;
import crdm.nomenclature.entity.Purchase;

public class XLSGenerator {

	public ByteArrayInputStream depositToXLS(List<Purchase> list, Contract contract) throws IOException {
		String[] columns = {"Bun", "Cantitatea ramasa in contract", "Cantitatea depozitata"};
		try (
				Workbook workbook = new XSSFWorkbook();
				ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		) {

			Sheet sheet = workbook.createSheet("Deposit");
			sheet.setColumnWidth(0, 10000);
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,2));
			sheet.setHorizontallyCenter(true);
			sheet.setColumnWidth(1, 10000); 
			sheet.setColumnWidth(2, 10000);
			
			Row header = sheet.createRow(0);
			
			CellStyle headerStyle = workbook.createCellStyle();
			headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			XSSFFont font = ((XSSFWorkbook) workbook).createFont();
			font.setFontName("Arial");
			font.setFontHeightInPoints((short) 12);
			font.setBold(true);
			
			Cell headerCell = header.createCell(0);
			headerCell.setCellValue(contract.getProvider().getName() + " ["
					+ contract.getNumber() + "]");
			
			headerCell.setCellStyle(headerStyle);
			
			header = sheet.createRow(1);
			headerStyle.setFont(font);
			
			
			for(int col = 0; col < columns.length; col++) {
				headerCell = header.createCell(col);
				headerCell.setCellValue(columns[col]);
				headerCell.setCellStyle(headerStyle);
				
			}

			int rowIndex = 2;
			int cellIndex = 0;
			
			Map<String, List<Purchase>> purchasesByGood = list.stream().collect(Collectors.groupingBy(item -> item.getGood().getGood()));

			
			for(Map.Entry<String, List<Purchase>> entry: purchasesByGood.entrySet()) {
				System.out.println(entry.getKey());
				Row row = sheet.createRow(rowIndex++);
				Cell cell = row.createCell(cellIndex++);
				cell.setCellValue(entry.getKey());
				
				cell = row.createCell(cellIndex++);
				cell.setCellValue(entry.getValue().get(0).getGood().getRemainder());
				
				cell = row.createCell(cellIndex++);
				cell.setCellValue(entry.getValue().stream().map(item -> item.getQuantity()).reduce(0f, Float::sum));
				cellIndex = 0;
			}

			workbook.write(outByteStream);

			return new ByteArrayInputStream(outByteStream.toByteArray());
		}

	}

}
