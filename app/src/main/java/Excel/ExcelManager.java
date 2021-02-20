package Excel;

import Patient.Patient;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class ExcelManager {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    Patient patient;


    public ArrayList<Patient> getPatientFromExcel(String excelPath) {

        ArrayList<Patient> allPatients = new ArrayList<>();


        FileInputStream file = null;
        HSSFWorkbook workbook = null;
        HSSFSheet sheet = null;


        try {


            file = new FileInputStream(excelPath);

            workbook = new HSSFWorkbook(file);

            sheet = workbook.getSheetAt(0);


        } catch (IOException e) {
            e.printStackTrace();
        }


        Iterator<Row> rowIterator = sheet.iterator();


        while (rowIterator.hasNext()) {

            Row row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();

            patient = new Patient();

            while (cellIterator.hasNext()) {


                Cell cell = cellIterator.next();


                switch (cell.getColumnIndex()) {

                    case 0:
                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);

                        patient.id = (int) cell.getNumericCellValue();


                        break;

                    case 1: // tc no

                        cell.setCellType(Cell.CELL_TYPE_STRING);

                        patient.tc_no = cell.getStringCellValue();

                        break;

                    case 2: // ad

                        cell.setCellType(Cell.CELL_TYPE_STRING);

                        patient.name = cell.getStringCellValue();


                        break;

                    case 3: // soyad

                        cell.setCellType(Cell.CELL_TYPE_STRING);

                        patient.surname = cell.getStringCellValue();

                        break;


                    case 4:  // doðum tarihi


                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);

                        patient.birthday = simpleDateFormat.format(cell.getDateCellValue());

                        break;

                    case 5: // cinsiyet

                        cell.setCellType(Cell.CELL_TYPE_STRING);

                        patient.sex = cell.getStringCellValue();

                        break;


                    case 6: // baðýmlýlýk

                        cell.setCellType(Cell.CELL_TYPE_STRING);

                        patient.dependency = cell.getStringCellValue();

                        break;

                    case 7: // son durum

                        cell.setCellType(Cell.CELL_TYPE_STRING);

                        patient.final_situation = cell.getStringCellValue();

                        break;


                    case 8: // iþlem tarihi

                        cell.setCellType(Cell.CELL_TYPE_NUMERIC);

                        patient.created_date = simpleDateFormat.format(cell.getDateCellValue());

                        break;


                }

                switch (cell.getCellType()) {

                    case Cell.CELL_TYPE_BOOLEAN:


                        break;


                    case Cell.CELL_TYPE_NUMERIC:


                        if (cell.getCellStyle().getDataFormatString().matches("dd/mm/yyyy;@")) {


                            Date date = cell.getDateCellValue();


                            System.out.println(simpleDateFormat.format(date) + "\t\t");


                        } else {
                            System.out.println(cell.getNumericCellValue());
                        }


                        break;

                    case Cell.CELL_TYPE_STRING:

                        String gelen;


                        gelen = cell.getStringCellValue();
                        System.out.println(gelen);


                        break;

                }


            }


            allPatients.add(patient);
        }


        return allPatients;
    }

    public void getPersonalInformationFromExcel(String excelPath)
    {

    }

    public void getPatientStuffFromExcel(String excelPath)
    {

    }

    public void getPatientdiseasFromExcel()
    {

    }


    public void geVisitInformationFromExcel() {

    }

    public void createExcelFileFromPatient(ExcelBundle bundle)
    {

        String path = "C:\\Users\\Win7\\Desktop\\example\\hasta_bilgileri_2.xls";


        ArrayList<Patient> patients = new ArrayList<>();

        patients = new ExcelManager().getPatientFromExcel(path);


        System.out.println("hasta sayýsý toplamda: " + patients.size());


        for (Patient patient : patients) {
            System.out.println(patient);
        }


        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet();

        Row row = null;
        Cell cell = null;
        for (int m = 0; m < patients.size(); m++) {
            row = sheet.createRow(m);
            for (int i = 0; i < 2; i++) {

                cell = row.createCell(0);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(patients.get(m).name);


                cell = row.createCell(1);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(patients.get(m).surname);


            }
        }


        FileOutputStream out =

                null;
        try {
            out = new FileOutputStream(new File("C:\\Users\\Win7\\Desktop\\\\lale.xls"));
            workbook.write(out);
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
