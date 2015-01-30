 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmBaoQuanThuoc;
 import com.iesvn.yte.entity.DmCachDungThuoc;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmNhaSanXuat;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.entity.DmPhanNhomThuoc;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.util.List;

 public class GetDanhMucThuocAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List listDMT = null;
     try
     {
       DieuTriUtilDelegate dtutilDelegate = DieuTriUtilDelegate.getInstance();
       listDMT = dtutilDelegate.findByNgayGioCN(Double.valueOf(Double.parseDouble(request)), "DmThuoc", "dmthuocNgaygiocn");
     }
     catch (Exception ex)
     {
       System.out.println(ex);
     }
     buf.append("<list>");
     try
     {
       if (listDMT != null) {
         for (Object obj : listDMT)
         {
           DmThuoc dmt = (DmThuoc)obj;

           String dviTinh = "";
           if (dmt.getDmdonvitinhMaso() != null) {
             dviTinh = String.valueOf(dmt.getDmdonvitinhMaso().getDmdonvitinhMaso());
           }
           String tenDVTinh = "";
           if (dmt.getDmdonvitinhMaso() != null) {
             tenDVTinh = dmt.getDmdonvitinhMaso().getDmdonvitinhTen();
           }
           String sBaoQuan = "";
           if (dmt.getDmbaoquanMaso() != null) {
             sBaoQuan = String.valueOf(dmt.getDmbaoquanMaso().getDmbaoquanthuocMaso());
           }
           String sPhanNhom = "";
           if (dmt.getDmphannhomthuocMaso() != null) {
             sPhanNhom = String.valueOf(dmt.getDmphannhomthuocMaso().getDmnhombcthuocMaso());
           }
           String sPhanNhom2 = "";
           if (dmt.getDmphannhomthuocMaso2() != null) {
             sPhanNhom2 = String.valueOf(dmt.getDmphannhomthuocMaso2().getDmnhombcthuocMaso());
           }
           String sCachDung = "";
           if (dmt.getDmcachdungMaso() != null) {
             sCachDung = String.valueOf(dmt.getDmcachdungMaso().getDmcachdungthuocMaso());
           }
           String hamLuong = "";
           if (dmt.getDmthuocHamluong() != null) {
             hamLuong = dmt.getDmthuocHamluong();
           }
           String sNhaSX = "";
           if (dmt.getDmnhasanxuatMaso() != null) {
             sNhaSX = String.valueOf(dmt.getDmnhasanxuatMaso().getDmnhasanxuatMaso());
           }
           String sPhanLoai1 = "";
           if (dmt.getDmphanloaithuocMaso() != null) {
             sPhanLoai1 = String.valueOf(dmt.getDmphanloaithuocMaso().getDmphanloaithuocMaso());
           }
           String sPhanLoai2 = "";
           if (dmt.getDmphanloaithuocMaso2() != null) {
             sPhanLoai2 = String.valueOf(dmt.getDmphanloaithuocMaso2().getDmphanloaithuocMaso());
           }
           String sNuocDef = "";
           if (dmt.getDmthuocNuocdefa() != null) {
             sNuocDef = String.valueOf(dmt.getDmthuocNuocdefa().getDmquocgiaMaso());
           }
           String strRecord = "<record   MaSo='" + dmt.getDmthuocMaso() + "' Ma='" + dmt.getDmthuocMa() + "' Ten='" + dmt.getDmthuocTen() + "' DMDONVITINH_MASO='" + dviTinh + "' NgayChinhSua='" + dmt.getDmthuocNgaygiocn() + "' DMBAOQUAN_MASO='" + sBaoQuan + "' DMPHANNHOMTHUOC_MASO='" + sPhanNhom + "' DMCACHDUNG_MASO='" + sCachDung + "' DMTHUOC_TENKH='" + Utils.reFactorString(dmt.getDmthuocTenkh()) + "' DMTHUOC_MAPHU='" + Utils.reFactorString(dmt.getDmthuocMaphu()) + "' DMTHUOC_SETS='" + Utils.reFactorString(dmt.getDmthuocSets()) + "' DMTHUOC_HAMLUONG='" + hamLuong + "' DMTHUOC_DUNGTICH='" + Utils.reFactorString(dmt.getDmthuocDungtich()) + "' DMPHANNHOMTHUOC_MASO2='" + sPhanNhom2 + "' DMTHUOC_CAPPHAT='" + Utils.reFactorString(dmt.getDmthuocCapphat()) + "' DMTHUOC_NCHU='" + Utils.reFactorString(dmt.getDmthuocNchu()) + "' DMTHUOC_TONMAX='" + Utils.reFactorString(dmt.getDmthuocTonmax()) + "' DMTHUOC_TONMIN='" + Utils.reFactorString(dmt.getDmthuocTonmin()) + "' DMTHUOC_THUVO='" + Utils.reFactorString(dmt.getDmthuocThuvo()) + "' DMTHUOC_YEUCAU='" + Utils.reFactorString(dmt.getDmthuocYeucau()) + "' DMTHUOC_YCU='" + Utils.reFactorString(dmt.getDmthuocYcu()) + "' DMTHUOC_HOICHAN='" + Utils.reFactorString(dmt.getDmthuocHoichan()) + "' DMTHUOC_TRUONGKHOA='" + Utils.reFactorString(dmt.getDmthuocTruongkhoa()) + "' DMTHUOC_NUOC='" + Utils.reFactorString(dmt.getDmthuocNuoc()) + "' DMTHUOC_MIEN='" + Utils.reFactorString(dmt.getDmthuocMien()) + "' DMTHUOC_DAM='" + Utils.reFactorString(dmt.getDmthuocDam()) + "' DMTHUOC_CORTI='" + Utils.reFactorString(dmt.getDmthuocCorti()) + "' DMTHUOC_KHONGTHU='" + Utils.reFactorString(dmt.getDmthuocKhongthu()) + "' DMTHUOC_TYLEBH='" + Utils.reFactorString(dmt.getDmthuocTylebh()) + "' DMTHUOC_DUTRU='" + Utils.reFactorString(dmt.getDmthuocDutru()) + "' DMTHUOC_DONGGOI='" + Utils.reFactorString(dmt.getDmthuocDonggoi()) + "' DMTHUOC_PHATBN='" + Utils.reFactorString(dmt.getDmthuocPhatbn()) + "' DMTHUOC_DONGIABH='" + Utils.reFactorString(dmt.getDmthuocDongiabh()) + "' DMTHUOC_PHANKHOC='" + Utils.reFactorString(dmt.getDmthuocPhankhol()) + "' DMTHUOC_PHANKHOL='" + Utils.reFactorString(dmt.getDmthuocPhankhol()) + "' DMTHUOC_PHANKHOB='" + Utils.reFactorString(dmt.getDmthuocPhankhob()) + "' DMTHUOC_THUTU='" + Utils.reFactorString(dmt.getDmthuocThutu()) + "' DMTHUOC_LIEU='" + Utils.reFactorString(dmt.getDmthuocLieu()) + "' DMTHUOC_DACBIET='" + Utils.reFactorString(dmt.getDmthuocDacbiet()) + "' DMTHUOC_MAHANG2='" + Utils.reFactorString(dmt.getDmthuocMahang2()) + "' DMTHUOC_NUOCDEFA='" + sNuocDef + "' DMNHASANXUAT_MASO='" + sNhaSX + "' DMTHUOC_KETOA='" + Utils.reFactorString(dmt.getDmthuocKetoa()) + "' DMTHUOC_LINHGOP='" + Utils.reFactorString(dmt.getDmthuocLinhgop()) + "' DMTHUOC_INDANHMUC='" + Utils.reFactorString(dmt.getDmthuocIndanhmuc()) + "' DMTHUOC_INPLINH='" + Utils.reFactorString(dmt.getDmthuocInplinh()) + "' DMTHUOC_INYLENH='" + Utils.reFactorString(dmt.getDmthuocInylenh()) + "' DMTHUOC_GCHU='" + Utils.reFactorString(dmt.getDmthuocGchu()) + "' DMTHUOC_NGOAITRU='" + Utils.reFactorString(dmt.getDmthuocNgoaitru()) + "' DMTHUOC_NOITRU='" + Utils.reFactorString(dmt.getDmthuocNoitru()) + "' DMTHUOC_TREEM='" + Utils.reFactorString(dmt.getDmthuocTreem()) + "' DMTHUOC_NGAYBOSUNG='" + Utils.reFactorString(dmt.getDmthuocNgaybosung()) + "' QL='" + Utils.reFactorString(dmt.getDmthuocQl()) + "' DT='" + Utils.reFactorString(dmt.getDmthuocDt()) + "' DP='" + Utils.reFactorString(dmt.getDmthuocDp()) + "' DMPHANLOAITHUOC_MASO='" + sPhanLoai1 + "' DMPHANLOAITHUOC_MASO2='" + sPhanLoai2 + "' DMTHUOC_DONGGIA_DAUTHAU='" + Utils.reFactorString(dmt.getDmthuocDonGiaDauThau()) + "' DMTHUOC_SODK='" + Utils.reFactorString(dmt.getDmthuocSoDangKy()) + "' DMTHUOC_KHONGXUAT='" + Utils.reFactorString(dmt.getDmthuocKhongXuat()) + "' />";
































































           buf.append(strRecord);
         }
       }
     }
     catch (Exception ex)
     {
       System.out.println(ex);
     }
     buf.append("</list>");
     System.out.println("buf.toString():" + buf.toString());
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetDanhMucThuocAction

 * JD-Core Version:    0.7.0.1

 */