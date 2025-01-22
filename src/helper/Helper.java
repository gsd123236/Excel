
public class Helper {


    //Column name provide method
    public static String [] HEADERS={
            "Id",
            "Title",
            "description",
            "coverImage"
    };
  //Excel Sheet name provide methode
    public  static String SHEET_NAME="category_data";

    // Data Get to mysql methode
     public static ByteArrayInputStream dataToExcel(List<Category> list) throws IOException {

         // create work book

         Workbook workbook=new SXSSFWorkbook();
         ByteArrayOutputStream out=new ByteArrayOutputStream();
         try {


             // create sheet

             Sheet sheet=workbook.createSheet(SHEET_NAME);

             //Create Row

             Row row=sheet.createRow(0);
             for(int i=0;i<HEADERS.length;i++){
                 Cell cell=row.createCell(i);
                 cell.setCellValue(HEADERS[i]);
//                 sheet.setColumnWidth(i, 50 * 256);
             }
             for (int i = 1; i < HEADERS.length; i++) {
                 sheet.setColumnWidth(i, 30 * 256); // Skip 'Id' column
             }


             //create row value

             int rowIndex=1;
             // Using for each loop

             for(Category c: list){
                 Row dataRow=sheet.createRow(rowIndex);
                 //row index increment
                 rowIndex++;


                 dataRow.createCell(0).setCellValue(c.getId());
                 dataRow.createCell(1).setCellValue(c.getTitle());
                 dataRow.createCell(2).setCellValue(c.getDescription());
                 dataRow.createCell(3).setCellValue(c.getCoverImage());


             }
         workbook.write(out);
             return  new ByteArrayInputStream(out.toByteArray());

         }catch (IOException e){
             e.printStackTrace();
             System.out.println("Fail to import data excel");
         } finally {
            workbook.close();
            out.close();

         }
         return null;
     }

}
