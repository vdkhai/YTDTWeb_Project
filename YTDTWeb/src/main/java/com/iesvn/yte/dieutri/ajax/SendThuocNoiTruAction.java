 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.HsbaKhoa;
 import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNguonChuongTrinh;
 import com.iesvn.yte.entity.DmNhaSanXuat;
 import com.iesvn.yte.entity.DmPhanLoaiThuoc;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmThuoc;
 import java.io.ByteArrayInputStream;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.rmi.RemoteException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import javax.xml.parsers.DocumentBuilder;
 import javax.xml.parsers.DocumentBuilderFactory;
 import javax.xml.parsers.ParserConfigurationException;
 import javax.xml.rpc.ServiceException;
 import org.w3c.dom.Document;
 import org.w3c.dom.NamedNodeMap;
 import org.w3c.dom.Node;
 import org.w3c.dom.NodeList;
 import org.xml.sax.SAXException;

 public class SendThuocNoiTruAction
   extends Action
 {
   public String performAction(String request)
   {
     String okId = "";
     String errorId = "";
     String xml = "";
     Document xmlDoc = null;
     int myBreak = 0;
     System.out.println("xml " + request);
     try
     {
       xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(request.getBytes("UTF-8")));
     }
     catch (ParserConfigurationException ex)
     {
       System.out.println("Error: " + ex.toString());
     }
     catch (SAXException ex)
     {
       System.out.println("Error: " + ex.toString());
     }
     catch (IOException ex)
     {
       System.out.println("Error: " + ex.toString());
     }
     if (xmlDoc != null)
     {
       NodeList listTNT = xmlDoc.getElementsByTagName("THUOC_NOI_TRU");
       if (listTNT.getLength() > 0)
       {
         System.out.println("Thuoc noi tru count " + listTNT.getLength());

         String maPhu = null;
         ArrayList<ThuocNoiTru> listThuoc = new ArrayList();
         for (int i = 0; i < listTNT.getLength(); i++)
         {
           myBreak = 0;
           Node nodeTNT = listTNT.item(i);
           maPhu = nodeTNT.getAttributes().getNamedItem("THUOC_NOI_TRU_MAPHU1").getTextContent();

           ThuocNoiTru tnt = null;
           try
           {
             tnt = getThuocNoiTru(nodeTNT);
             if (tnt != null)
             {
               listThuoc.add(tnt);
               System.out.println("get TNT success");
             }
             else
             {
               errorId = errorId + maPhu + "---";
               System.out.println("errorId " + errorId);
               continue;
             }
           }
           catch (Exception ex)
           {
             ex.printStackTrace();
           }
           System.out.println("-----------");
         }
         if (myBreak == 0) {
           try
           {
             ThuocNoiTruDelegate tntWS = ThuocNoiTruDelegate.getInstance();

             String tntID = "";
             System.out.println("insert tiep don & benh nhan " + tntID);
             if (!"".equals(tntID)) {
               okId = okId + maPhu + "---";
             } else {
               errorId = errorId + maPhu + "---";
             }
           }
           catch (Exception ex)
           {
             System.out.println("Error: " + ex.toString());
           }
         }
       }
       xml = okId + ";;;" + errorId;
       System.out.println("xml response: " + xml);
     }
     return String.format("<result>%s</result>", new Object[] { xml });
   }

   public ThuocNoiTru getThuocNoiTru(Node nodeTNT)
     throws ServiceException, RemoteException, Exception
   {
     ThuocNoiTru tnt = new ThuocNoiTru();
     if (nodeTNT != null)
     {
       String ma = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_MA").getTextContent();
       String sovaovien = nodeTNT.getAttributes().getNamedItem("HSBA_SOVAOVIEN").getTextContent();
       String maphieu = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_MAPHIEU").getTextContent();
       String khoa = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_KHOA").getTextContent();
       String phong = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_PHONG").getTextContent();
       String bosung = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_BOSUNG").getTextContent();
       String ngaygio = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_NGAYGIO").getTextContent();
       String mathuoc = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_MATHUOC").getTextContent();
       String maphong = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_MAPHONG").getTextContent();
       String quocgia = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_QUOCGIA").getTextContent();
       String nguon = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_NGUON").getTextContent();
       String phanloai = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_PHANLOAI").getTextContent();
       String loai = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_LOAI").getTextContent();
       String mien = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_MIEN").getTextContent();
       String nhi = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_NHI").getTextContent();
       String lao = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_LAO").getTextContent();
       String soluong = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_SOLUONG").getTextContent();
       String tra = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_TRA").getTextContent();
       String dongia = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_DONGIA").getTextContent();
       String dongiabh = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_DONGIABH").getTextContent();
       String ngaytt = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_NGAYTT").getTextContent();
       String cum = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_CUM").getTextContent();
       String lanlinh = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_LANLINH").getTextContent();
       String hangsx = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_HANGSX").getTextContent();
       String sodangky = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_SODANGKY").getTextContent();
       String khongthu = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_KHONGTHU").getTextContent();
       String bacsi = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_BACSI").getTextContent();
       String kho = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_KHO").getTextContent();
       String status = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_STATUS").getTextContent();
       String ngayinlinh = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_NGAYINLINH").getTextContent();
       String lanintra = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_LANINTRA").getTextContent();
       String ngayintra = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_NGAYINTRA").getTextContent();
       String dongianhap = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_DONGIANHAP").getTextContent();
       String dongiaban = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_DONGIABAN").getTextContent();
       String nhanviencn = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_NHANVIENCN").getTextContent();
       String thungan = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_THUNGAN").getTextContent();
       String sott = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_STT").getTextContent();
       String tutruc_pdt = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_TUTRUC_PDT").getTextContent();
       String malk = nodeTNT.getAttributes().getNamedItem("THUOCNOITRU_MALK").getTextContent();
       if ((!"".equals(malk)) && (!"null".equalsIgnoreCase(malk))) {
         tnt.setThuocnoitruMalk(malk);
       }
       tnt.setThuocnoitruNgaygiocn(new Date());
       if ((!"".equals(ma)) && (!"null".equalsIgnoreCase(ma))) {
         tnt.setThuocnoitruMa(Integer.valueOf(ma));
       }
       if ((!"".equals(sovaovien)) && (!"null".equalsIgnoreCase(sovaovien)))
       {
         HsbaKhoaDelegate hsbaWS = HsbaKhoaDelegate.getInstance();
         HsbaKhoa hsbaKhoa = hsbaWS.findBySoVaoVienAndKhoaMa(sovaovien, khoa);
         System.out.println("hsbaKhoa: " + hsbaKhoa);
         if (hsbaKhoa == null) {
           return null;
         }
         tnt.setHsbaKhoa(hsbaKhoa);
       }
       else
       {
         return null;
       }
       if ((!"".equals(maphieu)) && (!"null".equalsIgnoreCase(maphieu))) {
         tnt.setThuocnoitruMaphieu(maphieu);
       }
       if ((!"".equals(khoa)) && (!"null".equalsIgnoreCase(khoa)))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmKhoa dmkhoa = (DmKhoa)ws.findByMa(khoa, "DmKhoa", "dmkhoaMa");
         System.out.println("dmkhoa: " + dmkhoa);
         if (dmkhoa == null) {
           return null;
         }
         tnt.setThuocnoitruKhoa(dmkhoa);
       }
       if ((!"".equals(phong)) && (!"null".equalsIgnoreCase(phong))) {
         tnt.setThuocnoitruPhong(phong);
       }
       if ((!"".equals(bosung)) && (!"null".equalsIgnoreCase(bosung))) {
         tnt.setThuocnoitruBosung(bosung);
       }
       if ((!"".equals(ngaygio)) && (!"null".equalsIgnoreCase(ngaygio)))
       {
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         cal.setTime(df.parse(ngaygio));
         tnt.setThuocnoitruNgaygio(cal.getTime());
       }
       if ((!"".equals(mathuoc)) && (!"null".equalsIgnoreCase(mathuoc)))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmThuoc dmthuoc = (DmThuoc)ws.findByMa(mathuoc, "DmThuoc", "dmthuocMa");
         System.out.println("dmthuoc: " + dmthuoc);
         if (dmthuoc == null) {
           return null;
         }
         tnt.setThuocnoitruMathuoc(dmthuoc);
       }
       if ((!"".equals(maphong)) && (!"null".equalsIgnoreCase(maphong)))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DtDmClsBangGia dtdmgiaphong = (DtDmClsBangGia)ws.findByMa(maphong, "DtDmClsBangGia", "dtdmclsbgMa");
         System.out.println("dtdmgiaphong: " + dtdmgiaphong);
         if (dtdmgiaphong == null) {
           return null;
         }
         tnt.setThuocnoitruMaphong(dtdmgiaphong);
       }
       if ((!"".equals(quocgia)) && (!"null".equalsIgnoreCase(quocgia)))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmQuocGia dmquocgia = (DmQuocGia)ws.findByMa(quocgia, "DmQuocGia", "dmquocgiaMa");
         System.out.println("dmquocgia: " + dmquocgia);
         if (dmquocgia == null) {
           return null;
         }
         tnt.setThuocnoitruQuocgia(dmquocgia);
       }
       if ((!"".equals(nguon)) && (!"null".equalsIgnoreCase(nguon)))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmNguonChuongTrinh dtdmnguon = (DmNguonChuongTrinh)ws.findByMa(nguon, "DmNguonChuongTrinh", "dmnctMa");
         System.out.println("dtdmnguon: " + dtdmnguon);
         if (dtdmnguon == null) {
           return null;
         }
         tnt.setThuocnoitruNguon(dtdmnguon);
       }
       if ((!"".equals(phanloai)) && (!"null".equalsIgnoreCase(phanloai)))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmPhanLoaiThuoc dmphanloai = (DmPhanLoaiThuoc)ws.findByMa(phanloai, "DmPhanLoaiThuoc", "dmphanloaithuocMa");
         System.out.println("dmphanloai: " + dmphanloai);
         if (dmphanloai == null) {
           return null;
         }
         tnt.setThuocnoitruPhanloai(dmphanloai);
       }
       if ((!"".equals(loai)) && (!"null".equalsIgnoreCase(loai))) {
         tnt.setThuocnoitruLoai(loai);
       }
       if ((!"".equals(mien)) && (!"null".equalsIgnoreCase(mien))) {
         tnt.setThuocnoitruMien(Boolean.valueOf(mien));
       }
       if ((!"".equals(nhi)) && (!"null".equalsIgnoreCase(nhi))) {
         tnt.setThuocnoitruNhi(Boolean.valueOf(nhi));
       }
       if ((!"".equals(lao)) && (!"null".equalsIgnoreCase(lao))) {
         tnt.setThuocnoitruLao(Boolean.valueOf(lao));
       }
       if ((!"".equals(soluong)) && (!"null".equalsIgnoreCase(soluong))) {
         tnt.setThuocnoitruSoluong(Double.valueOf(soluong));
       }
       if ((!"".equals(tra)) && (!"null".equalsIgnoreCase(tra))) {
         tnt.setThuocnoitruTra(Double.valueOf(tra));
       }
       if ((!"".equals(dongia)) && (!"null".equalsIgnoreCase(dongia))) {
         tnt.setThuocnoitruDongia(Double.valueOf(dongia));
       }
       if ((!"".equals(dongiabh)) && (!"null".equalsIgnoreCase(dongiabh))) {
         tnt.setThuocnoitruDongiabh(Double.valueOf(dongiabh));
       }
       if ((!"".equals(ngaytt)) && (!"null".equalsIgnoreCase(ngaytt)))
       {
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         cal.setTime(df.parse(ngaytt));
         tnt.setThuocnoitruNgaytt(cal.getTime());
       }
       if ((!"".equals(cum)) && (!"null".equalsIgnoreCase(cum))) {
         tnt.setThuocnoitruCum(Short.valueOf(cum));
       }
       if ((!"".equals(lanlinh)) && (!"null".equalsIgnoreCase(lanlinh))) {
         tnt.setThuocnoitruLanlinh(Short.valueOf(lanlinh));
       }
       if ((!"".equals(hangsx)) && (!"null".equalsIgnoreCase(hangsx)))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmNhaSanXuat dmhangsx = (DmNhaSanXuat)ws.findByMa(hangsx, "DmNhaSanXuat", "dmnhasanxuatMa");
         System.out.println("dmhangsx: " + dmhangsx);
         if (dmhangsx == null) {
           return null;
         }
         tnt.setThuocnoitruHangsx(dmhangsx);
       }
       if ((!"".equals(sodangky)) && (!"null".equalsIgnoreCase(sodangky))) {
         tnt.setThuocnoitruSodangky(sodangky);
       }
       if ((!"".equals(khongthu)) && (!"null".equalsIgnoreCase(khongthu))) {
         tnt.setThuocnoitruKhongthu(Boolean.valueOf(khongthu));
       }
       if ((!"".equals(bacsi)) && (!"null".equalsIgnoreCase(bacsi)))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DtDmNhanVien dmbacsi = (DtDmNhanVien)ws.findByMa(bacsi, "DtDmNhanVien", "dtdmnhanvienMa");
         System.out.println("dmbacsi: " + dmbacsi);
         if (dmbacsi == null) {
           return null;
         }
         tnt.setThuocnoitruBacsi(dmbacsi);
       }
       if ((!"".equals(kho)) && (!"null".equalsIgnoreCase(kho)))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmKhoa dmkho = (DmKhoa)ws.findByMa(kho, "DmKhoa", "dmkhoaMa");
         System.out.println("dmkho: " + dmkho);
         if (dmkho == null) {
           return null;
         }
         tnt.setThuocnoitruKho(dmkho);
       }
       if ((!"".equals(status)) && (!"null".equalsIgnoreCase(status))) {
         tnt.setThuocnoitruStatus(status);
       }
       if ((!"".equals(ngayinlinh)) && (!"null".equalsIgnoreCase(ngayinlinh)))
       {
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         cal.setTime(df.parse(ngayinlinh));
         tnt.setThuocnoitruNgayinlinh(cal.getTime());
       }
       if ((!"".equals(lanintra)) && (!"null".equalsIgnoreCase(lanintra))) {
         tnt.setThuocnoitruLanintra(Short.valueOf(lanintra));
       }
       if ((!"".equals(ngayintra)) && (!"null".equalsIgnoreCase(ngayintra)))
       {
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         cal.setTime(df.parse(ngayintra));
         tnt.setThuocnoitruNgayintra(cal.getTime());
       }
       if ((!"".equals(dongianhap)) && (!"null".equalsIgnoreCase(dongianhap))) {
         tnt.setThuocnoitruDongianhap(Double.valueOf(dongianhap));
       }
       if ((!"".equals(dongiaban)) && (!"null".equalsIgnoreCase(dongiaban))) {
         tnt.setThuocnoitruDongiaban(Double.valueOf(dongiaban));
       }
       if ((!"".equals(nhanviencn)) && (!"null".equalsIgnoreCase(nhanviencn)))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DtDmNhanVien dmnhanviencn = (DtDmNhanVien)ws.findByMa(nhanviencn, "DtDmNhanVien", "dtdmnhanvienMa");
         System.out.println("dmnhanviencn: " + dmnhanviencn);
         if (dmnhanviencn == null) {
           return null;
         }
         tnt.setThuocnoitruNhanviencn(dmnhanviencn);
       }
       if ((!"".equals(thungan)) && (!"null".equalsIgnoreCase(thungan)))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DtDmNhanVien dmthungan = (DtDmNhanVien)ws.findByMa(thungan, "DtDmNhanVien", "dtdmnhanvienMa");
         System.out.println("dmthungan: " + dmthungan);
         if (dmthungan == null) {
           return null;
         }
         tnt.setThuocnoitruThungan(dmthungan);
       }
       if ((tutruc_pdt != null) && (!tutruc_pdt.equals(""))) {
         if (tutruc_pdt.equals("0")) {
           tnt.setThuocnoitruTutrucPdt(Integer.valueOf(0));
         } else if (tutruc_pdt.equals("1")) {
           tnt.setThuocnoitruTutrucPdt(Integer.valueOf(1));
         } else {
           tnt.setThuocnoitruTutrucPdt(Integer.valueOf(2));
         }
       }
       System.out.println("stt" + sott);
       if ((sott != null) && (!"".equals(sott)))
       {
         System.out.println("stt2" + sott);
         try
         {
           tnt.setThuocnoitruStt(Integer.valueOf(sott));
         }
         catch (Exception e)
         {
           tnt.setThuocnoitruStt(null);
         }
       }
     }
     else
     {
       return null;
     }
     System.out.println("end");
     return tnt;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.SendThuocNoiTruAction

 * JD-Core Version:    0.7.0.1

 */