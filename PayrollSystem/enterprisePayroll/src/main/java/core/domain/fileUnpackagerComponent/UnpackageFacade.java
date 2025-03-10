package core.domain.fileUnpackagerComponent;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UnpackageFacade {

    public void unpackageFile(String filePath) throws IOException {

        switch ((filePath.substring(filePath.lastIndexOf(".")+1)).toLowerCase()){
            case ("xlsx"):
                unpackageExcelFile(filePath);
                break;
            default:
                throw new FileNotFoundException("Unknown file extension");
        }

    }

    private void unpackageExcelFile(String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        Workbook workbook = WorkbookFactory.create(fileInputStream);

        for(Sheet sheet : workbook){
            excelUnpackageProcess(sheet);
        }
    }

    private void excelUnpackageProcess(Sheet sheet){

        AttendanceDirector attendanceDirector = new AttendanceDirector();
        ExcelWorkAttendanceBuilder excelWorkAttendanceBuilder = new ExcelWorkAttendanceBuilder();

        for(Row row: sheet){
            attendanceDirector.makeWorkAttendance(excelWorkAttendanceBuilder, row);
            WorkAttendance workAttendance = excelWorkAttendanceBuilder.getResult();
            // -------- TO BE DONE -------- For each row, we take the work attendance and save it in each employee.
        }
    }

}
