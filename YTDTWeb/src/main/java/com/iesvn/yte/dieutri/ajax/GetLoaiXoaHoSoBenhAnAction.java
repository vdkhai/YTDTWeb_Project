 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;

 public class GetLoaiXoaHoSoBenhAnAction
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
     String str1 = "Ngày giờ chuyển khoa";
     String str2 = "Ngày xuất viện";
     String str3 = "Hồ sơ bệnh án";


     buf.append("<list>");

     buf.append("<record MaSo='1' Ma='1' Ten='" + str1 + "' NgayChinhSua='1'  DT='1'/>");
     buf.append("<record MaSo='2' Ma='2' Ten='" + str2 + "' NgayChinhSua='1'  DT='1'/>");
     buf.append("<record MaSo='3' Ma='3' Ten='" + str3 + "' NgayChinhSua='1'  DT='1'/>");


     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetLoaiXoaHoSoBenhAnAction

 * JD-Core Version:    0.7.0.1

 */