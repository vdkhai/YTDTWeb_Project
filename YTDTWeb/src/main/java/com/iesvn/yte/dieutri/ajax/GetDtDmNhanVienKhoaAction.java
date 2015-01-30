 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.DtDmNhanvienKhoa;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDtDmNhanVienKhoaAction
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
       listObj = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmNhanvienKhoa", "dtdmnhanvienKhoaNgaygiocn");
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
     buf.append("<list>");
     if (listObj != null) {
       for (Object obj : listObj)
       {
         DtDmNhanvienKhoa nvKhoa = (DtDmNhanvienKhoa)obj;
         buf.append("<record MaSo='" + nvKhoa.getDtdmnhanvienkhoaMaso() + "' DTDMNHANVIEN_MASO='" + nvKhoa.getDtdmnhanvienMaso().getDtdmnhanvienMaso() + "' DMKHOA_MASO='" + nvKhoa.getDmkhoaMaso().getDmkhoaMaso() + "' NgayChinhSua='" + Utils.reFactorString(nvKhoa.getDtdmnhanvienKhoaNgaygiocn()) + "' DT='" + Utils.reFactorString(nvKhoa.getDtdmnhanvienKhoaChon()) + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDtDmNhanVienKhoaAction

 * JD-Core Version:    0.7.0.1

 */