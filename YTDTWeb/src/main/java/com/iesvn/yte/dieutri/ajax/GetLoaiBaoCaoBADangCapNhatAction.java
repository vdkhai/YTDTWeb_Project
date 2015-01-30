 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;

 public class GetLoaiBaoCaoBADangCapNhatAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     if ((request != null) && (request.equals("1")))
     {
       buf.append("<list>");
       buf.append("</list>");
       return buf.toString();
     }
     String str1 = "﻿Bệnh án chưa được khoa nào cập nhật";
     String str2 = "Bệnh án đang cập nhật";
     String str3 = "Bệnh án chưa cập nhật ngày ra viện";
     String str4 = "Bệnh án đã cập nhật ngày ra viện";

     buf.append("<list>");

     buf.append("<record MaSo='1' Ma='1' Ten='" + str1 + "' NgayChinhSua='1'  DT='1'/>");
     buf.append("<record MaSo='2' Ma='2' Ten='" + str2 + "' NgayChinhSua='1'  DT='1'/>");
     buf.append("<record MaSo='3' Ma='3' Ten='" + str3 + "' NgayChinhSua='1'  DT='1'/>");
     buf.append("<record MaSo='4' Ma='4' Ten='" + str4 + "' NgayChinhSua='1'  DT='1'/>");

     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetLoaiBaoCaoBADangCapNhatAction

 * JD-Core Version:    0.7.0.1

 */