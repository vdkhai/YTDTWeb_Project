 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmTinhBhyt;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDtDmTinhAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List list = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       list = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmTinhBhyt", "dtdmtinhbhytNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (list != null) {
       for (Object obj : list)
       {
         DtDmTinhBhyt dmtinhbhyt = (DtDmTinhBhyt)obj;
         buf.append("<record MaSo='" + dmtinhbhyt.getDtdmtinhbhytMaso() + "' Ma='" + dmtinhbhyt.getDtdmtinhbhytMa() + "' Ten='" + dmtinhbhyt.getDtdmtinhbhytTen() + "' NgayChinhSua='" + dmtinhbhyt.getDtdmtinhbhytNgaygiocn() + "' DTDMTINHBHYT_NGAYTINH='" + Utils.reFactorString(dmtinhbhyt.getDtdmtinhbhytNgaytinh()) + "' DTDMTINHBHYT_TEN1='" + Utils.reFactorString(dmtinhbhyt.getDtdmtinhbhytTen1()) + "' DTDMTINHBHYT_NGAYTINH1='" + Utils.reFactorString(dmtinhbhyt.getDtdmtinhbhytNgaytinh1()) + "' DTDMTINHBHYT_TEN2='" + Utils.reFactorString(dmtinhbhyt.getDtdmtinhbhytTen2()) + "' DTDMTINHBHYT_NGAYTINH2='" + Utils.reFactorString(dmtinhbhyt.getDtdmtinhbhytNgaytinh2()) + "' DTDMTINHBHYT_TEN3='" + Utils.reFactorString(dmtinhbhyt.getDtdmtinhbhytTen3()) + "' DTDMTINHBHYT_NGAYTINH3='" + Utils.reFactorString(dmtinhbhyt.getDtdmtinhbhytNgaytinh3()) + "' DTDMTINHBHYT_NEW='" + Utils.reFactorString(dmtinhbhyt.getDtdmtinhbhytNew()) + "' DTDMTINHBHYT_NOITINH='" + Utils.reFactorString(dmtinhbhyt.getDtdmtinhbhytNoitinh()) + "' DTDMTINHBHYT_DEFA='" + Utils.reFactorString(dmtinhbhyt.getDtdmtinhbhytDefa()) + "' DT='" + Utils.reFactorString(dmtinhbhyt.getDtdmtinhbhytChon()) + "' />");
       }
     }
     buf.append("</list>");

     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDtDmTinhAction

 * JD-Core Version:    0.7.0.1

 */