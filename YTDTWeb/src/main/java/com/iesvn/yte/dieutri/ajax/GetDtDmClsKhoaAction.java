 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmClsKhoa;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.util.List;

 public class GetDtDmClsKhoaAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listObj = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listObj = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmClsKhoa", "dtdmclsKhoaNgaygiocn");
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
     System.out.print("GetDtDmClsKhoaAction request:" + request);

     buf.append("<list>");
     if (listObj != null) {
       for (Object obj : listObj)
       {
         DtDmClsKhoa clsKhoa = (DtDmClsKhoa)obj;
         buf.append("<record MaSo='" + clsKhoa.getDtdmclskhoaMaso() + "' DTDMCLS_MASO='" + clsKhoa.getDtdmclsMaso().getDtdmclsbgMaso() + "' DMKHOA_MASO='" + clsKhoa.getDmkhoaMaso().getDmkhoaMaso() + "' NgayChinhSua='" + Utils.reFactorString(clsKhoa.getDtdmclsKhoaNgaygiocn()) + "' DT='" + Utils.reFactorString(clsKhoa.getDtdmclsKhoaChon()) + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDtDmClsKhoaAction

 * JD-Core Version:    0.7.0.1

 */