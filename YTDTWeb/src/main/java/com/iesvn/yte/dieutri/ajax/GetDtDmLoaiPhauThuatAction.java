 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiPhauThuat;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDtDmLoaiPhauThuatAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listLoaiPt = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listLoaiPt = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmLoaiPhauThuat", "dtdmloaiptNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listLoaiPt != null) {
       for (Object obj : listLoaiPt)
       {
         DtDmLoaiPhauThuat dvt = (DtDmLoaiPhauThuat)obj;
         buf.append("<record MaSo='" + dvt.getDtdmloaiptMaso() + "' Ma='" + dvt.getDtdmloaiptMa() + "' Ten='" + dvt.getDtdmloaiptTen() + "' DTDMLOAIPT_PHAUTHUAT='" + Utils.reFactorString(dvt.getDtdmloaiptPhauthuat()) + "' DTDMLOAIPT_MABYTP='" + Utils.reFactorString(dvt.getDtdmloaiptMabytp()) + "' DTDMLOAIPT_THUTHUAT='" + Utils.reFactorString(dvt.getDtdmloaiptThuthuat()) + "' DTDMLOAIPT_MABYTT='" + Utils.reFactorString(dvt.getDtdmloaiptMabytt()) + "' NgayChinhSua='" + Utils.reFactorString(dvt.getDtdmloaiptNgaygiocn()) + "' DT='" + Utils.reFactorString(dvt.getDtdmloaiptChon()) + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDtDmLoaiPhauThuatAction

 * JD-Core Version:    0.7.0.1

 */