 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmKcbBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmTinhBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmTuyenKcb;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetKCBBHYTAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDtDmKcbBhyt = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDtDmKcbBhyt = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmKcbBhyt", "dtdmkcbbhytNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listDtDmKcbBhyt != null) {
       for (Object obj : listDtDmKcbBhyt)
       {
         DtDmKcbBhyt kcb = (DtDmKcbBhyt)obj;
         String ten = Utils.findAndreplace(kcb.getDtdmkcbbhytTen());


         String tinhBhyt = "";
         if (kcb.getDtdmtinhbhytMaso() != null) {
           tinhBhyt = kcb.getDtdmtinhbhytMaso().getDtdmtinhbhytMaso().toString();
         }
         String tuyenKcb = "";
         if (kcb.getDtdmtuyenkcbMaso() != null) {
           tuyenKcb = kcb.getDtdmtuyenkcbMaso().getDtdmtuyenkcbMaso().toString();
         }
         buf.append("<record MaSo='" + kcb.getDtdmkcbbhytMaso() + "' Ma='" + kcb.getDtdmkcbbhytMa() + "' Ten='" + ten + "' DTDMKCBBHYT_GHICHU='" + kcb.getDtdmkcbbhytGhichu() + "' DTDMKCBBHYT_MAUTHE='" + kcb.getDtdmkcbbhytMauthe() + "' DT='" + Utils.reFactorString(kcb.getDtdmkcbbhytChon()) + "' DTDMTINHBHYT_MASO='" + tinhBhyt + "' DTDMTUYENKCB_MASO='" + tuyenKcb + "' DTDMKCBBHYT_DEFA='" + Utils.reFactorString(kcb.getDtdmkcbbhytDefa()) + "' NgayChinhSua='" + kcb.getDtdmkcbbhytNgaygiocn() + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetKCBBHYTAction

 * JD-Core Version:    0.7.0.1

 */