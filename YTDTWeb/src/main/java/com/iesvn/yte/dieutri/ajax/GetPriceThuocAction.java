 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmNguonChuongTrinh;
 import com.iesvn.yte.entity.DmNguonKinhPhi;
 import com.iesvn.yte.entity.DmNhaSanXuat;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.util.List;

 public class GetPriceThuocAction
   extends Action
 {
   public String performAction(String request)
     throws Exception
   {
     StringBuffer buf = new StringBuffer();
     List objDmTonKho = null;

     String mathuoc = request.substring(0, request.indexOf("___"));
     request = request.substring(request.indexOf("___") + 3);

     String maKhoa = request;





     int pos = mathuoc.indexOf("_");
     if (pos > 0) {
       mathuoc = mathuoc.substring(0, pos) + "\\" + mathuoc.substring(pos);
     }
     System.out.println("ma thuoc:" + mathuoc);
     System.out.println("ma Khoa:" + maKhoa);
     try
     {
       TonKhoDelegate delegate = TonKhoDelegate.getInstance();

       objDmTonKho = delegate.findTonKhoByDtmMaAndKhoMa(mathuoc, maKhoa);
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
     buf.append("<list>");
     buf.append("<record MaSo='' NamNhap='' NgayHanDung='' ThangHanDung='' NamHanDung=''  DonGia=''  DonGiaBH=''  DonGiaYC='' KhongThu='' HSX='' MaSoQG='' QG='' Ton='' NCT='' NCT_Ma='' NKP=''  NKP_Ma='' MaLK='' NgayChinhSua='' DT='' />");
     if (objDmTonKho != null) {
       for (Object obj : objDmTonKho)
       {
         TonKho tonKho = (TonKho)obj;

         Double donGia = tonKho.getTonkhoDongia();
         if (donGia == null) {
           donGia = new Double(0.0D);
         }
         Double donGiaBH = tonKho.getDmthuocMaso().getDmthuocDongiabh();
         if (donGiaBH == null) {
           donGiaBH = new Double(0.0D);
         }
         Boolean YC = tonKho.getDmthuocMaso().getDmthuocYeucau();
         if (YC == null) {
           YC = new Boolean(false);
         }
         Boolean khongThu = tonKho.getDmthuocMaso().getDmthuocKhongthu();
         if (khongThu == null) {
           khongThu = new Boolean(false);
         }
         Boolean mienPhi = tonKho.getDmthuocMaso().getDmthuocMien();
         if (mienPhi == null) {
           mienPhi = new Boolean(false);
         }
         Boolean trongDm = tonKho.getDmthuocMaso().getDmthuocIndanhmuc();
         Boolean ndm = Boolean.valueOf(false);
         if ((trongDm == null) || (!trongDm.booleanValue())) {
           ndm = new Boolean(true);
         } else {
           ndm = new Boolean(false);
         }
         String maHSX = tonKho.getDmnhasanxuatMaso(true).getDmnhasanxuatMa();
         String maQG = tonKho.getDmquocgiaMaso(true).getDmquocgiaMa();
         Integer maSoQG = tonKho.getDmquocgiaMaso(true).getDmquocgiaMaso();
         Double ton = tonKho.getTonkhoTon();

         System.out.print("tonKho.getTonkhoTon():" + tonKho.getTonkhoTon());
         System.out.print("tonKho.getTonkhoTon():" + tonKho.getTonkhoTon());
         System.out.print("tonKho.getTonkhoTon():" + tonKho.getTonkhoTon());
         System.out.print("tonKho.getTonkhoTon():" + tonKho.getTonkhoTon());
         System.out.print("tonKho.getTonkhoTon():" + tonKho.getTonkhoTon());
         if ((ton != null) && (ton.doubleValue() > 0.0D))
         {
           String hamluong = tonKho.getDmthuocMaso().getDmthuocHamluong();
           if (hamluong == null) {
             hamluong = "";
           }
           String dvt = tonKho.getDmthuocMaso(true).getDmdonvitinhMaso(true).getDmdonvitinhTen();
           if (dvt == null) {
             dvt = "";
           }
           String tenThuoc = tonKho.getDmthuocMaso(true).getDmthuocTen();
           if (tenThuoc == null) {
             tenThuoc = "";
           }
           String tenQG = tonKho.getDmquocgiaMaso(true).getDmquocgiaTen();
           if (tenQG == null) {
             tenQG = "";
           }
           String tenHSX = tonKho.getDmnhasanxuatMaso(true).getDmnhasanxuatTen();
           if (tenHSX == null) {
             tenHSX = "";
           }
           String hd = "";
           if ((tonKho.getTonkhoNgayhandung() != null) && (tonKho.getTonkhoThanghandung() != null) && (tonKho.getTonkhoNamhandung() != null)) {
             hd = tonKho.getTonkhoNgayhandung() + "/" + tonKho.getTonkhoThanghandung() + "/" + tonKho.getTonkhoNamhandung();
           } else if ((tonKho.getTonkhoThanghandung() != null) && (tonKho.getTonkhoNamhandung() != null)) {
             hd = tonKho.getTonkhoThanghandung() + "/" + tonKho.getTonkhoNamhandung();
           }
           String NamNhap = tonKho.getTonkhoNamnhap() == null ? "" : tonKho.getTonkhoNamnhap();
           String NgayHanDung = tonKho.getTonkhoNgayhandung() == null ? "" : tonKho.getTonkhoNgayhandung();
           String ThangHanDung = tonKho.getTonkhoThanghandung() == null ? "" : tonKho.getTonkhoThanghandung();
           String NamHanDung = tonKho.getTonkhoNamhandung() == null ? "" : tonKho.getTonkhoNamhandung();

           buf.append("<record MaSo='" + tonKho.getTonkhoMa() + "' NamNhap='" + NamNhap + "' NgayHanDung='" + NgayHanDung + "' ThangHanDung='" + ThangHanDung + "' NamHanDung='" + NamHanDung + "' DonGia='" + Utils.formatNumber(donGia, "##.####") + "' DonGiaBH='" + Utils.formatNumber(donGiaBH, "##.####") + "' DonGiaMP='" + mienPhi + "' DonGiaYC='" + YC + "' NDM='" + ndm + "' KhongThu='" + khongThu + "' HSX='" + maHSX + "' MaSoQG='" + maSoQG + "' QG='" + maQG + "' NCT='" + tonKho.getDmnctMaso().getDmnctMaso() + "' NCT_Ma='" + tonKho.getDmnctMaso().getDmnctMa() + "' NKP='" + tonKho.getDmnguonkinhphiMaso().getDmnguonkinhphiMaso() + "' NKP_Ma='" + tonKho.getDmnguonkinhphiMaso().getDmnguonkinhphiMa() + "' MaLK='" + tonKho.getTonkhoMalienket() + "' Ton='" + Utils.formatNumber(Double.valueOf(ton.doubleValue()), "##.##") + "' NgayChinhSua='" + "" + "' DT='" + "" + "' HamLuong='" + hamluong + "' Dvt='" + dvt + "' tenThuoc='" + tenThuoc + "' tenQG='" + tenQG + "' tenHSX='" + tenHSX + "' hanDung='" + hd + "' />");
         }
       }
     }
     buf.append("</list>");
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetPriceThuocAction

 * JD-Core Version:    0.7.0.1

 */