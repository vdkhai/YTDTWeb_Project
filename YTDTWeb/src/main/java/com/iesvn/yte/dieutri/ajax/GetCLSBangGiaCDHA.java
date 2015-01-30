 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DtDmClsBangGiaDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import java.io.PrintStream;
 import java.util.ArrayList;

 public class GetCLSBangGiaCDHA
   extends Action
 {
   public String performAction(String request)
   {
     System.out.println("##### GetCLSBangGiaCDHA #####");


     StringBuffer buf = new StringBuffer();
     buf.append("<list>");
     try
     {
       ArrayList<DtDmClsBangGia> dtDmClsBangGiaList = (ArrayList)DtDmClsBangGiaDelegate.getInstance().getDtDmClsBangGiaByMaSoKhoa(Integer.valueOf(30));

       String result = "";
       result = result + "<RECORD ";
       result = result + " >";

       result = result + "<LISTCLS ";
       result = result + " >";
       for (int i = 0; i < dtDmClsBangGiaList.size(); i++)
       {
         result = result + "<CLS ";
         DtDmClsBangGia dtDmClsBangGia = (DtDmClsBangGia)dtDmClsBangGiaList.get(i);
         String diengiaiCLS = "";
         String dongia = "";
         String maCLS = "";
         String khoaCDHA = "";
         maCLS = dtDmClsBangGia.getDtdmclsbgMa();
         diengiaiCLS = dtDmClsBangGia.getDtdmclsbgDiengiai();
         dongia = dtDmClsBangGia.getDtdmclsbgDongia() + "";
         khoaCDHA = dtDmClsBangGia.getDtdmclsbgCdha();
         result = result + "DIENGIAICLS='" + diengiaiCLS + "' ";
         result = result + "DONGIA='" + dongia + "' ";
         result = result + "MACLS='" + maCLS + "' ";
         result = result + "KHOACDHA='" + khoaCDHA + "' ";
         result = result + " />";
       }
       result = result + " </LISTCLS>";

       result = result + " </RECORD>";
       buf.append(result);
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     buf.append("</list>");

     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetCLSBangGiaCDHA

 * JD-Core Version:    0.7.0.1

 */