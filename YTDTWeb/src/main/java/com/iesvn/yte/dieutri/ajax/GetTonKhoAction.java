 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmNhaSanXuat;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmThuoc;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.StringTokenizer;
 import org.apache.log4j.Logger;

 public class GetTonKhoAction
   extends Action
 {
   private static Logger log = Logger.getLogger(GetTonKhoAction.class);

   public String performAction(String request)
     throws Exception
   {
     log.debug("-----Begin getTonKho()-----" + request);
     StringBuffer buf = new StringBuffer();
     String maHang = "";
     String kinhphiMa = "";
     String nguonMa = "";
     String khoaXuat = "";
     StringTokenizer sToken = new StringTokenizer(request, ";");
     if (sToken != null)
     {
       maHang = sToken.nextToken();
       kinhphiMa = sToken.nextToken();
       nguonMa = sToken.nextToken();
       khoaXuat = sToken.nextToken();
     }
     log.debug("Ma thuoc :" + maHang);
     log.debug("Kinh phi :" + kinhphiMa);
     log.debug("Nguon ma :" + nguonMa);
     log.debug("Khoa xuat :" + khoaXuat);

     maHang = maHang.trim();
     kinhphiMa = kinhphiMa.trim();
     nguonMa = nguonMa.trim();
     khoaXuat = khoaXuat.trim();

     ArrayList<TonKho> listTonKho = new ArrayList();
     try
     {
       TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();

       List<TonKho> listTk = tkDelegate.findDanhSachTonKho(maHang, kinhphiMa, nguonMa, khoaXuat);
       if ((listTk != null) && (listTk.size() > 0)) {
         for (TonKho tk : listTk)
         {
           log.debug(String.format("-----Ton kho ma: %s", new Object[] { tk.getTonkhoMa() }));
           listTonKho.add(tk);
         }
       }
     }
     catch (Exception e)
     {
       log.error(String.format("-----Error: %s", new Object[] { e.toString() }));
     }
     buf.append("<list>");
     buf.append("<record  MaHang='' TenHang=''  QuyCach=''  DonVi=''  TonKho=''  DonGia='' />");
     if (listTonKho != null)
     {
       ArrayList<TonKhoKey> arrayKey = new ArrayList();
       for (TonKho tonkho : listTonKho)
       {
         String hamluong = tonkho.getDmthuocMaso().getDmthuocHamluong();
         if (hamluong == null) {
           hamluong = "";
         }
         String hanDung = tonkho.getTonkhoNgayhandung() + "/" + tonkho.getTonkhoThanghandung() + "/" + tonkho.getTonkhoNamhandung();
         if ((tonkho.getTonkhoNgayhandung().equals("")) || (tonkho.getTonkhoThanghandung().equals("")) || (tonkho.getTonkhoNamhandung().equals(""))) {
           hanDung = "";
         }
         String dvt = tonkho.getDmthuocMaso(true).getDmdonvitinhMaso(true).getDmdonvitinhTen();
         if (dvt == null) {
           dvt = "";
         }
         String tenThuoc = tonkho.getDmthuocMaso(true).getDmthuocTen();
         if (tenThuoc == null) {
           tenThuoc = "";
         }
         String tenQG = tonkho.getDmquocgiaMaso(true).getDmquocgiaTen();
         if (tenQG == null) {
           tenQG = "";
         }
         String tenHSX = tonkho.getDmnhasanxuatMaso(true).getDmnhasanxuatTen();
         if (tenHSX == null) {
           tenHSX = "";
         }
         String key = tonkho.getDmthuocMaso().getDmthuocMa() + tonkho.getDmthuocMaso().getDmdonvitinhMaso().getDmdonvitinhTen() + tonkho.getTonkhoDongia();


         TonKhoKey tonKhoKey = contains(arrayKey, key);
         if (tonKhoKey != null)
         {
           TonKhoKey localTonKhoKey1 = tonKhoKey;
                        localTonKhoKey1.tonKho = Double.valueOf(localTonKhoKey1.tonKho.doubleValue() + tonkho.getTonkhoTon().doubleValue());
         }
         else
         {
           TonKhoKey tonKhoNew = new TonKhoKey();
           tonKhoNew.key = key;
           tonKhoNew.maHang = tonkho.getDmthuocMaso().getDmthuocMa();
           tonKhoNew.tenHang = tonkho.getDmthuocMaso().getDmthuocTen();
           tonKhoNew.donVi = tonkho.getDmthuocMaso().getDmdonvitinhMaso().getDmdonvitinhTen();
           tonKhoNew.donGia = tonkho.getTonkhoDongia();

           log.info("-----ham luong: " + hamluong);
           tonKhoNew.hamLuong = hamluong;
           tonKhoNew.nhaSx = tenHSX;
           tonKhoNew.qg = tenQG;
           tonKhoNew.dvt = dvt;
           tonKhoNew.hd = hanDung;
           if ((tonkho.getTonkhoTon() != null) && (tonkho.getTonkhoTon().doubleValue() > 0.0D))
           {
             tonKhoNew.tonKho = tonkho.getTonkhoTon();
             arrayKey.add(tonKhoNew);
           }
         }
       }
       for (TonKhoKey tonkhoKey : arrayKey) {
         buf.append("<record  MaHang='" + tonkhoKey.maHang + "' TenHang='" + tonkhoKey.tenHang + "' QuyCach='" + tonkhoKey.quyCach + "' DonVi='" + tonkhoKey.donVi + "' TonKho='" + tonkhoKey.tonKho + "' DonGia='" + tonkhoKey.donGia + "' NSX='" + tonkhoKey.nhaSx + "' hamLuong='" + tonkhoKey.hamLuong + "' qg='" + tonkhoKey.qg + "' dvt='" + tonkhoKey.dvt + "' hanDung='" + tonkhoKey.hd + "' />");
       }
     }
     buf.append("</list>");
     log.debug("-----End getTonKho()-----" + buf.toString());
     return buf.toString();
   }

   private TonKhoKey contains(ArrayList<TonKhoKey> arrayKey, String key)
   {
     for (TonKhoKey tonkho : arrayKey) {
       if ((key != null) && (tonkho != null) && (tonkho.key != null) && (!tonkho.key.equals("")) && (tonkho.key.equals(key))) {
         return tonkho;
       }
     }
     return null;
   }

   public class TonKhoKey
   {
     public String key = "";
     public String maHang = "";
     public String tenHang = "";
     public Integer quyCach = new Integer(0);
     public String donVi = "";
     public Double donGia = new Double(0.0D);
     public Double tonKho = new Double(0.0D);
     public String hamLuong = "";
     public String dvt = "";
     public String nhaSx = "";
     public String qg = "";
     public String hd = "";

     public TonKhoKey() {}
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.GetTonKhoAction

 * JD-Core Version:    0.7.0.1

 */