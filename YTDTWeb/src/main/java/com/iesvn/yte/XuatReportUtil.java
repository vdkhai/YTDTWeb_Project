 package com.iesvn.yte;

 import java.util.Date;
 import net.sf.jasperreports.engine.JRExporterParameter;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.export.JExcelApiExporter;
 import net.sf.jasperreports.engine.export.JRHtmlExporter;
 import net.sf.jasperreports.engine.export.JRPdfExporter;
 import net.sf.jasperreports.engine.export.JRRtfExporter;
 import net.sf.jasperreports.engine.export.JRXmlExporter;

 public class XuatReportUtil
 {
   public static String ReportUtil(JasperPrint jasperPrint, int index, String resultPath, String loaiFile, String tenTk)
   {
     String resultReportName = null;
     try
     {
       if (loaiFile.compareToIgnoreCase("HTML") == 0)
       {
         JRHtmlExporter htmlExporter = new JRHtmlExporter();
         htmlExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
         htmlExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
         resultPath = resultPath + tenTk + "_Result.html";
         resultReportName = tenTk + "_Result.html";
         htmlExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, resultPath);
         htmlExporter.exportReport();
       }
       else if (loaiFile.compareToIgnoreCase("DOC") == 0)
       {
         JRRtfExporter rtfExporter = new JRRtfExporter();
         rtfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
         rtfExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
         resultPath = resultPath + tenTk + "_Result.doc";
         resultReportName = tenTk + "_Result.doc";
         rtfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, resultPath);
         rtfExporter.exportReport();
       }
       else if (loaiFile.compareToIgnoreCase("EXCEL") == 0)
       {
         JExcelApiExporter xlsExporter = new JExcelApiExporter();
         xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
         xlsExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
         resultPath = resultPath + tenTk + "_Result.xls";
         resultReportName = tenTk + "_Result.xls";
         xlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, resultPath);
         xlsExporter.exportReport();
       }
       else if (loaiFile.compareToIgnoreCase("PDF") == 0)
       {
         String tempStr = resultPath;
         JRPdfExporter pdfExporter = new JRPdfExporter();
         pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
         pdfExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
         resultReportName = tenTk + "_Result" + index + new Date().getTime() + ".pdf";
         resultPath = resultPath + resultReportName;





         pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, resultPath);
         pdfExporter.exportReport();
       }
       else if (loaiFile.compareToIgnoreCase("XML") == 0)
       {
         JRXmlExporter xmlExporter = new JRXmlExporter();
         xmlExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
         xmlExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
         resultPath = resultPath + tenTk + "_Result.xml";
         resultReportName = tenTk + "_Result.xml";
         xmlExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, resultPath);
         xmlExporter.exportReport();
       }
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     return resultPath;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.XuatReportUtil

 * JD-Core Version:    0.7.0.1

 */