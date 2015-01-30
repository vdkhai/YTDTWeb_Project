 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;

 public class GetLoaiCapNhatMienGiamAction
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
     String capnhatmiengiam_ngaygiuongdieutri = "Ngày giường điều trị";
     String capnhatmiengiam_phantramchiphi = "% chi phí";
     String capnhatmiengiam_sotiencuthe = "Số tiền cụ thể";
     String capnhatmiengiam_phantramtienmau = "% tiền máu";
     String capnhatmiengiam_tienkythuatcao = "Tiền kỹ thuật cao";


     buf.append("<list>");

     buf.append("<record MaSo='1' Ma='1' Ten='" + capnhatmiengiam_ngaygiuongdieutri + "' NgayChinhSua='1'  DT='1'/>");
     buf.append("<record MaSo='2' Ma='2' Ten='" + capnhatmiengiam_phantramchiphi + "' NgayChinhSua='1'  DT='1'/>");
     buf.append("<record MaSo='3' Ma='3' Ten='" + capnhatmiengiam_sotiencuthe + "' NgayChinhSua='1'  DT='1'/>");
     buf.append("<record MaSo='4' Ma='4' Ten='" + capnhatmiengiam_phantramtienmau + "' NgayChinhSua='1'  DT='1'/>");
     buf.append("<record MaSo='5' Ma='5' Ten='" + capnhatmiengiam_tienkythuatcao + "' NgayChinhSua='1'  DT='1'/>");


     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetLoaiCapNhatMienGiamAction

 * JD-Core Version:    0.7.0.1

 */