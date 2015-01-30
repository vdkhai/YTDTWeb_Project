 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmNhaSanXuat;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.Utils;
 import java.text.DecimalFormat;
 import java.util.List;
 import java.util.StringTokenizer;
 import org.apache.log4j.Logger;

 public class GetTonKhoDuocPhamAction
   extends Action
 {
   private static Logger log = Logger.getLogger(GetTonKhoDuocPhamAction.class);

   public String performAction(String request)
     throws Exception
   {
     log.info("-----Begin getTonKho()-----" + request);
     StringBuffer buf = new StringBuffer();
     String maHang = "";
     String kinhphiMa = "";
     String nguonMa = "";
     String khoaXuat = "";
     StringTokenizer sToken = new StringTokenizer(request, ";");
     if ((sToken != null) && (sToken.countTokens() >= 4))
     {
       maHang = sToken.nextToken().trim();
       kinhphiMa = sToken.nextToken().trim();
       nguonMa = sToken.nextToken().trim();
       khoaXuat = sToken.nextToken().trim();
     }
     List<TonKho> listTk = null;
     try
     {
       TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
       listTk = tkDelegate.findDanhSachTonKho(maHang.toUpperCase(), kinhphiMa, nguonMa, khoaXuat);
     }
     catch (Exception e)
     {
       log.error(String.format("-----Error: %s", new Object[] { e.toString() }));
     }
     buf.append("<list>");
     if (listTk != null) {
       for (TonKho tonkho : listTk) {
         if (tonkho.getTonkhoTon().doubleValue() > 0.0D)
         {
           String ten = tonkho.getDmthuocMaso().getDmthuocTen();
           ten = Utils.findAndreplace(ten);

           String donViTinhTen = "";
           if (tonkho.getDmthuocMaso().getDmdonvitinhMaso() != null) {
             donViTinhTen = tonkho.getDmthuocMaso().getDmdonvitinhMaso().getDmdonvitinhTen();
           } else {
             donViTinhTen = " ";
           }
           DecimalFormat df = new DecimalFormat("###.##");
           String ton = df.format(tonkho.getTonkhoTon().doubleValue());
           String dg = df.format(tonkho.getTonkhoDongia());
           String mahang = tonkho.getDmthuocMaso().getDmthuocMa();
           String hamluong = tonkho.getDmthuocMaso().getDmthuocHamluong();
           if (hamluong == null) {
             hamluong = "";
           }
           String nuocSx = tonkho.getDmquocgiaMaso().getDmquocgiaTen();
           String hangSx = tonkho.getDmnhasanxuatMaso().getDmnhasanxuatTen();


           String hd = "";
           if ((tonkho.getTonkhoNgayhandung() != null) && (tonkho.getTonkhoThanghandung() != null) && (tonkho.getTonkhoNamhandung() != null)) {
             hd = tonkho.getTonkhoNgayhandung() + "/" + tonkho.getTonkhoThanghandung() + "/" + tonkho.getTonkhoNamhandung();
           } else if ((tonkho.getTonkhoThanghandung() != null) && (tonkho.getTonkhoNamhandung() != null)) {
             hd = tonkho.getTonkhoThanghandung() + "/" + tonkho.getTonkhoNamhandung();
           }
           buf.append("<record  MaHang='" + mahang + "' TenHang='" + ten + "' DonVi='" + donViTinhTen + "' TonKho='" + ton + "' DonGia='" + dg + "' TonKhoMa='" + tonkho.getTonkhoMa() + "' HamLuong='" + hamluong + "' NuocSx='" + nuocSx + "' HangSx='" + hangSx + "' HanDung='" + hd + "' Malk='" + tonkho.getTonkhoMalienket() + "' />");
         }
       }
     }
     buf.append("</list>");
     log.info("-----End getTonKho()-----" + buf.toString());
     return buf.toString();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetTonKhoDuocPhamAction

 * JD-Core Version:    0.7.0.1

 */