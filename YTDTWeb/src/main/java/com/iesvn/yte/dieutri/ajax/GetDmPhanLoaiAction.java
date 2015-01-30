 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.util.List;

 public class GetDmPhanLoaiAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDmPhanLoai = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDmPhanLoai = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DmPhanLoaiThuoc", "dmphanloaithuocNgaygiocn");
     }
     catch (Exception ex)
     {
       System.out.println(ex);
     }
     buf.append("<list>");
     if (listDmPhanLoai != null) {
       for (Object obj : listDmPhanLoai)
       {
         DmPhanLoaiThuoc phanLoai = (DmPhanLoaiThuoc)obj;
         String sLoai = "";
         if (phanLoai.getDmloaithuocMaso() != null) {
           sLoai = String.valueOf(phanLoai.getDmloaithuocMaso().getDmloaithuocMaso());
         }
         buf.append("<record MaSo='" + phanLoai.getDmphanloaithuocMaso() + "' Ma='" + phanLoai.getDmphanloaithuocMa() + "' Ten='" + phanLoai.getDmphanloaithuocTen() + "' DMLOAITHUOC_MASO='" + sLoai + "' DMPHANLOAITHUOC_NHOM2='" + Utils.reFactorString(phanLoai.getDmphanloaithuocNhom2()) + "' DMPHANLOAITHUOC_NHOM3='" + Utils.reFactorString(phanLoai.getDmphanloaithuocNhom3()) + "' DMPHANLOAITHUOC_DUNGTICH='" + Utils.reFactorString(phanLoai.getDmphanloaithuocDungtich()) + "' DMPHANLOAITHUOC_GHICHU='" + Utils.reFactorString(phanLoai.getDmphanloaithuocGhichu()) + "' DMPHANLOAITHUOC_THUTUBC='" + Utils.reFactorString(phanLoai.getDmphanloaithuocThutubc()) + "' NgayChinhSua='" + phanLoai.getDmphanloaithuocNgaygiocn() + "' DMPHANLOAITHUOC_LOAI='" + Utils.reFactorString(phanLoai.getDmphanloaithuocLoai()) + "' DT='" + Utils.reFactorString(phanLoai.getDmphanloaithuocDt()) + "' QL='" + Utils.reFactorString(phanLoai.getDmphanloaithuocQl()) + "' DP='" + Utils.reFactorString(phanLoai.getDmphanloaithuocDp()) + "' />");
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDmPhanLoaiAction

 * JD-Core Version:    0.7.0.1

 */