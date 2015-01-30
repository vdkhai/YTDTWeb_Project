 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmTramYTeBhyt;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDtDmTramYTeBhytAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDtDmTramYTeBhyt = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDtDmTramYTeBhyt = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmTramYTeBhyt", "dtdmtramytebhytNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listDtDmTramYTeBhyt != null) {
       for (Object obj : listDtDmTramYTeBhyt)
       {
         DtDmTramYTeBhyt dtDmTramYTeBhyt = (DtDmTramYTeBhyt)obj;



         buf.append("<record  MaSo='" + dtDmTramYTeBhyt.getDtdmtramytebhytMaso() + "'" + " Ma='" + dtDmTramYTeBhyt.getDtdmtramytebhytMa() + "'" + " Ten='" + dtDmTramYTeBhyt.getDtdmtramytebhytTen() + "'" + " NgayChinhSua='" + dtDmTramYTeBhyt.getDtdmtramytebhytNgaygiocn() + "'" + " DT='" + Utils.reFactorString(dtDmTramYTeBhyt.getDtdmtramytebhytChon()) + "'" + "/>");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDtDmTramYTeBhytAction

 * JD-Core Version:    0.7.0.1

 */