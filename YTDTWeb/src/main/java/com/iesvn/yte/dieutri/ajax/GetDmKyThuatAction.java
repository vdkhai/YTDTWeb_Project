 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmCls;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiPhauThuat;
 import com.iesvn.yte.util.Utils;
 import java.util.List;

 public class GetDmKyThuatAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDmKyThuat = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();

       listDmKyThuat = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DtDmClsBangGia", "dtdmclsbgNgaygiocn");
     }
     catch (Exception ex) {}
     buf.append("<list>");
     if (listDmKyThuat != null) {
       for (Object obj : listDmKyThuat)
       {
         DtDmClsBangGia kythuat = (DtDmClsBangGia)obj;
         String maLoai = "";
         if (kythuat.getDtdmclsbgMaloai() != null) {
           maLoai = String.valueOf(kythuat.getDtdmclsbgMaloai().getDtdmclsMaso());
         }
         String maPhanLoai = "";
         if (kythuat.getDtdmclsbgPhanloai() != null) {
           maPhanLoai = String.valueOf(kythuat.getDtdmclsbgPhanloai().getDtdmclsMaso());
         }
         String maLoaiPTTT = "";
         if (kythuat.getDtdmclsbgLoaipttt() != null) {
           maLoaiPTTT = String.valueOf(kythuat.getDtdmclsbgLoaipttt().getDtdmloaiptMaso());
         }
         String strRecord = "<record \tMaSo='" + kythuat.getDtdmclsbgMaso() + "' Ma='" + kythuat.getDtdmclsbgMa() + "' Ten='" + kythuat.getDtdmclsbgDiengiai() + "' DTDMCLSBG_MA2='" + Utils.reFactorString(kythuat.getDtdmclsbgMa2()) + "' DTDMCLSBG_PHANDV='" + Utils.reFactorString(kythuat.getDtdmclsbgPhandv()) + "' DTDMCLSBG_DONGIA='" + Utils.reFactorString(kythuat.getDtdmclsbgDongia()) + "' DTDMCLSBG_DONGIAMP='" + Utils.reFactorString(kythuat.getDtdmclsbgDongiamp()) + "' DTDMCLSBG_DONGIABH='" + Utils.reFactorString(kythuat.getDtdmclsbgDongiabh()) + "' DTDMCLSBG_DONGIA2='" + Utils.reFactorString(kythuat.getDtdmclsbgDongia2()) + "' DTDMCLSBG_DONGIA3='" + Utils.reFactorString(kythuat.getDtdmclsbgDongia3()) + "' DTDMCLSBG_DONGIAYC='" + Utils.reFactorString(kythuat.getDtdmclsbgDongiayc()) + "' DTDMCLSBG_DONGIANN='" + Utils.reFactorString(kythuat.getDtdmclsbgDongiann()) + "' DTDMCLSBG_KHACTUYEN='" + Utils.reFactorString(kythuat.getDtdmclsbgKhactuyen()) + "' DTDMCLSBG_VATTU='" + Utils.reFactorString(kythuat.getDtdmclsbgVattu()) + "' DTDMCLSBG_BAOTRI='" + Utils.reFactorString(kythuat.getDtdmclsbgBaotri()) + "' DTDMCLSBG_DIENNUOC='" + Utils.reFactorString(kythuat.getDtdmclsbgDiennuoc()) + "' DTDMCLSBG_PHANBIET='" + Utils.reFactorString(kythuat.getDtdmclsbgPhanBiet()) + "' DMCLSBG_LOAI='" + Utils.reFactorString(kythuat.getDtdmclsbgLoai()) + "' DTDMCLSBG_CONG='" + Utils.reFactorString(kythuat.getDtdmclsbgCong()) + "' DTDMCLSBG_OXY='" + Utils.reFactorString(kythuat.getDtdmclsbgOxy()) + "' DMCLSBG_NDM='" + Utils.reFactorString(kythuat.getDtdmclsbgNDM()) + "' DMCLSBG_MIEN='" + Utils.reFactorString(kythuat.getDtdmclsbgMien()) + "' DTDMCLSBG_MALOAI='" + maLoai + "' DTDMCLSBG_PHANLOAI='" + maPhanLoai + "' NgayChinhSua='" + kythuat.getDtdmclsbgNgaygiocn() + "' DT='" + Utils.reFactorString(kythuat.getDtdmclsbgChon()) + "' DMCLSBG_LOAIPTTT='" + maLoaiPTTT + "'" + " />";




















































         buf.append(strRecord);
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDmKyThuatAction

 * JD-Core Version:    0.7.0.1

 */