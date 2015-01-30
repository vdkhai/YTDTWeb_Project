 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.TiepDonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmLyDoCv;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.DtDmPlBhyt;
 import com.iesvn.yte.dieutri.entity.HsThtoank;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhTruyenNhiem;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmDiaDiem;
 import com.iesvn.yte.entity.DmDieuTri;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKetQuaDieuTri;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmPhuongThucGayTaiNan;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmTaiNan;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.HoSoThanhToanKhamUtil;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.ByteArrayInputStream;
 import java.io.IOException;
 import java.io.PrintStream;
 import java.rmi.RemoteException;
 import java.text.SimpleDateFormat;
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

 public class SendTiepDonAction
   extends Action
 {
   public String performAction(String request)
   {
     String okId = "";
     String errorId = "";
     String xml = "";
     Document xmlDoc = null;
     int myBreak = 0;
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
       NodeList listBN = xmlDoc.getElementsByTagName("BENH_NHAN");
       if (listBN.getLength() > 0) {
         for (int i = 0; i < listBN.getLength(); i++)
         {
           myBreak = 0;
           Node nodeBN = listBN.item(i);
           String maPhu = nodeBN.getAttributes().getNamedItem("BENH_NHAN_MAPHU").getTextContent();
           BenhNhan bn = null;
           try
           {
             bn = getBenhNhan(nodeBN);
             if (bn != null)
             {
               System.out.println("get BN success");
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
           NodeList listTD = nodeBN.getChildNodes();
           TiepDon td = null;
           if (listTD.getLength() > 0)
           {
             Node nodeTD = listTD.item(0);
             try
             {
               if (getTiepDon(nodeTD) != null)
               {
                 td = getTiepDon(nodeTD);
                 td.setBenhnhanMa(bn);
               }
               else
               {
                 errorId = errorId + maPhu + "---";
                 System.out.println("errorId" + errorId);
                 myBreak = 1;
               }
             }
             catch (Exception ex)
             {
               ex.printStackTrace();
             }
           }
           if (myBreak == 0) {
             try
             {
               TiepDonDelegate tdDelegate = TiepDonDelegate.getInstance();







               DieuTriUtilDelegate delegate = DieuTriUtilDelegate.getInstance();
               DtDmNhanVien nhanvienThuNgan = (DtDmNhanVien)delegate.findByMa(IConstantsRes.THU_NGAN_MA, "DtDmNhanVien", "dtdmnhanvienMa");
               td.setTiepdonThungan(nhanvienThuNgan);


               td.setTiepdonCum(IConstantsRes.CUM_TIEP_DON);





               String bnID = tdDelegate.createTiepDon(td);
               if (!"".equals(bnID))
               {
                 okId = okId + maPhu + ",,," + bnID + "---";

                 HsThtoank hsttk = new HsThtoank();
                 hsttk.setTiepdonMa(td);
                 tinhToanChoHSTKKham(td, hsttk);
               }
               else
               {
                 errorId = errorId + maPhu + "---";
               }
             }
             catch (Exception ex)
             {
               System.out.println("Error: " + ex.toString());
               errorId = errorId + maPhu + "---";
             }
           }
         }
       }
       xml = okId + ";;;" + errorId;
     }
     return String.format("<result>%s</result>", new Object[] { xml });
   }

   private void tinhToanChoHSTKKham(TiepDon td, HsThtoank hsttk)
   {
     HoSoThanhToanKhamUtil hsthtoankUtilTmp = new HoSoThanhToanKhamUtil(td);
     hsthtoankUtilTmp.tinhToanChoHSTKKham(hsttk);
     Utils.setInforForHsThToan(hsttk);
   }

   public BenhNhan getBenhNhan(Node nodeBN)
     throws ServiceException, RemoteException, Exception
   {
     BenhNhan bn = new BenhNhan();
     if (nodeBN != null)
     {
       String ma = nodeBN.getAttributes().getNamedItem("BENHNHAN_MA").getTextContent();
       String hoten = nodeBN.getAttributes().getNamedItem("BENHNHAN_HOTEN").getTextContent();
       String ngaysinh = nodeBN.getAttributes().getNamedItem("BENHNHAN_NGAYSINH").getTextContent();
       String gioi = nodeBN.getAttributes().getNamedItem("BENHNHAN_GIOI").getTextContent();
       String tuoi = nodeBN.getAttributes().getNamedItem("BENHNHAN_TUOI").getTextContent();
       String namsinh = nodeBN.getAttributes().getNamedItem("BENHNHAN_NAMSINH").getTextContent();

       String donvituoi = nodeBN.getAttributes().getNamedItem("BENHNHAN_DONVITUOI").getTextContent();
       String cmnd = nodeBN.getAttributes().getNamedItem("BENHNHAN_CMND").getTextContent();
       String dantoc = nodeBN.getAttributes().getNamedItem("DANTOC_MA").getTextContent();
       String quocgia = nodeBN.getAttributes().getNamedItem("QUOCGIA_MA").getTextContent();
       String tinh = nodeBN.getAttributes().getNamedItem("TINH_MA").getTextContent();
       String huyen = nodeBN.getAttributes().getNamedItem("HUYEN_MA").getTextContent();
       String xa = nodeBN.getAttributes().getNamedItem("XA_MA").getTextContent();
       String diachi = nodeBN.getAttributes().getNamedItem("BENHNHAN_DIACHI").getTextContent();
       String nghenghiep = nodeBN.getAttributes().getNamedItem("BENHNHAN_NGHE").getTextContent();

       String nhanvien = nodeBN.getAttributes().getNamedItem("NHANVIEN_MA").getTextContent();


       bn.setBenhnhanNgaygiocn(new Date());
       if (!"".equals(ma)) {
         bn.setBenhnhanMa(ma);
       }
       if (!"".equals(hoten)) {
         bn.setBenhnhanHoten(hoten);
       } else {
         return null;
       }
       if (!"".equals(ngaysinh))
       {
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         cal.setTime(df.parse(ngaysinh));
         bn.setBenhnhanNgaysinh(cal.getTime());
       }
       if ((!"".equals(gioi)) && (gioi != null) && (!gioi.equals("null")))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmGioiTinh gt = (DmGioiTinh)ws.findByMa(gioi, "DmGioiTinh", "dmgtMa");


         bn.setDmgtMaso(gt);
       }
       if (!"".equals(tuoi)) {
         bn.setBenhnhanTuoi(Integer.valueOf(tuoi));
       }
       if (!"".equals(donvituoi)) {
         bn.setBenhnhanDonvituoi(Short.valueOf(donvituoi));
       }
       if (!"".equals(cmnd)) {
         bn.setBenhnhanCmnd(cmnd);
       }
       if (!"".equals(dantoc))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmDanToc dmdantoc = (DmDanToc)ws.findByMa(dantoc, "DmDanToc", "dmdantocMa");
         if (dmdantoc == null) {
           return null;
         }
         bn.setDantocMa(dmdantoc);
       }
       if (!"".equals(quocgia))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmQuocGia dmquocgia = (DmQuocGia)ws.findByMa(quocgia, "DmQuocGia", "dmquocgiaMa");
         if (dmquocgia == null) {
           return null;
         }
         bn.setQuocgiaMa(dmquocgia);
       }
       if (!"".equals(tinh))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmTinh dmtinh = (DmTinh)ws.findByMa(tinh, "DmTinh", "dmtinhMa");
         if (dmtinh == null) {
           return null;
         }
         bn.setTinhMa(dmtinh);
       }
       if (!"".equals(huyen))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmHuyen dmhuyen = (DmHuyen)ws.findByMa(huyen, "DmHuyen", "dmhuyenMa");
         if (dmhuyen == null) {
           return null;
         }
         bn.setHuyenMa(dmhuyen);
       }
       if (!"".equals(xa))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmXa dmxa = (DmXa)ws.findByMa(xa, "DmXa", "dmxaMa");
         if (dmxa == null) {
           return null;
         }
         bn.setXaMa(dmxa);
       }
       if (!"".equals(diachi)) {
         bn.setBenhnhanDiachi(diachi);
       }
       if (!"".equals(nghenghiep))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmNgheNghiep dmnghe = (DmNgheNghiep)ws.findByMaSo(Integer.valueOf(Integer.parseInt(nghenghiep)), "DmNgheNghiep", "dmnghenghiepMaso");
         if (dmnghe == null) {
           return null;
         }
         bn.setBenhnhanNghe(dmnghe);
       }
       if (!"".equals(nhanvien))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DtDmNhanVien dtdmnhanvien = (DtDmNhanVien)ws.findByMa(nhanvien, "DtDmNhanVien", "dtdmnhanvienMa");
         if (dtdmnhanvien == null) {
           return null;
         }
         bn.setNhanvienMa(dtdmnhanvien);
       }
       bn.setBenhnhanNamsinh(namsinh);
     }
     else
     {
       return null;
     }
     return bn;
   }

   public TiepDon getTiepDon(Node nodeTD)
     throws ServiceException, RemoteException, Exception
   {
     TiepDon td = new TiepDon();
     if (nodeTD != null)
     {
       String matiepdon = nodeTD.getAttributes().getNamedItem("TIEPDON_MA").getTextContent();
       String maphieuk = nodeTD.getAttributes().getNamedItem("TIEPDON_MAPHIEUK").getTextContent();
       String ngaygio = nodeTD.getAttributes().getNamedItem("TIEPDON_NGAYGIO").getTextContent();
       String doituong = nodeTD.getAttributes().getNamedItem("DOITUONG_MA").getTextContent();
       String phanloai = nodeTD.getAttributes().getNamedItem("TIEPDON_PHANLOAI").getTextContent();
       String tainan = nodeTD.getAttributes().getNamedItem("TAINAN_MA").getTextContent();
       String donvigoi = nodeTD.getAttributes().getNamedItem("TIEPDON_DONVIGOI").getTextContent();
       String khoibhyt = nodeTD.getAttributes().getNamedItem("KHOIBHYT_MA").getTextContent();
       String tinhbhyt = nodeTD.getAttributes().getNamedItem("TINHBHYT_MA").getTextContent();
       String dpbhyt = nodeTD.getAttributes().getNamedItem("DPBHYT_MA").getTextContent();
       String coquan = nodeTD.getAttributes().getNamedItem("TIEPDON_MACOQUAN").getTextContent();
       String sothebh = nodeTD.getAttributes().getNamedItem("TIEPDON_SOTHEBH").getTextContent();
       String nambhyt = nodeTD.getAttributes().getNamedItem("TIEPDON_NAMBHYT").getTextContent();
       String sonambh = nodeTD.getAttributes().getNamedItem("TIEPDON_SONAMBH").getTextContent();
       String tuyen = nodeTD.getAttributes().getNamedItem("TIEPDON_TUYEN").getTextContent();
       String kcbbhyt = nodeTD.getAttributes().getNamedItem("KCBBHYT_MA").getTextContent();
       String giatri1 = nodeTD.getAttributes().getNamedItem("TIEPDON_GIATRI1").getTextContent();
       String giatri2 = nodeTD.getAttributes().getNamedItem("TIEPDON_GIATRI2").getTextContent();

       String khaisinh = nodeTD.getAttributes().getNamedItem("TIEPDON_KHAISINH").getTextContent();
       String chungsinh = nodeTD.getAttributes().getNamedItem("TIEPDON_CHUNGSINH").getTextContent();
       String sothete = nodeTD.getAttributes().getNamedItem("TIEPDON_SOTHETE").getTextContent();
       String thengheo = nodeTD.getAttributes().getNamedItem("TIEPDON_THENGHEO").getTextContent();
       String bankham = nodeTD.getAttributes().getNamedItem("TIEPDON_BANKHAM").getTextContent();
       String thutu = nodeTD.getAttributes().getNamedItem("TIEPDON_THUTU").getTextContent();
       String thutus = nodeTD.getAttributes().getNamedItem("TIEPDON_THUTUS").getTextContent();
       String loaikham = nodeTD.getAttributes().getNamedItem("TIEPDON_LOAIKHAM").getTextContent();
       String bntra = nodeTD.getAttributes().getNamedItem("TIEPDON_BNTRA").getTextContent();
       String doituongbhyt = nodeTD.getAttributes().getNamedItem("TIEPDON_DOITUONGBHYT").getTextContent();
       String tylebh = nodeTD.getAttributes().getNamedItem("TIEPDON_TYLEBH").getTextContent();
       String bacsi = nodeTD.getAttributes().getNamedItem("TIEPDON_BACSI").getTextContent();
       String nguoiduyet = nodeTD.getAttributes().getNamedItem("TIEPDON_NGUOIDUYET").getTextContent();
       String kyhieu = nodeTD.getAttributes().getNamedItem("TIEPDON_KYHIEU").getTextContent();
       String quyen = nodeTD.getAttributes().getNamedItem("TIEPDON_QUYEN").getTextContent();
       String so = nodeTD.getAttributes().getNamedItem("TIEPDON_BIENLAI").getTextContent();






       String maphieud = nodeTD.getAttributes().getNamedItem("TIEPDON_MAPHIEUD").getTextContent();
       String nhanviencn = nodeTD.getAttributes().getNamedItem("TIEPDON_NHANVIENCN").getTextContent();
       String thungan = nodeTD.getAttributes().getNamedItem("TIEPDON_THUNGAN").getTextContent();
       String phuongtien = nodeTD.getAttributes().getNamedItem("PHUONGTIEN_MA").getTextContent();
       String nguyennhan = nodeTD.getAttributes().getNamedItem("NGUYENHAN_MA").getTextContent();
       String diadiem = nodeTD.getAttributes().getNamedItem("DIADIEM_MA").getTextContent();

       String lydovaovien = nodeTD.getAttributes().getNamedItem("TIEPDON_LYDOVAOV").getTextContent();
       String chandoantt = nodeTD.getAttributes().getNamedItem("TIEPDON_MACHDOANB0").getTextContent();
       String dgchandoantt = nodeTD.getAttributes().getNamedItem("TIEPDON_DGCHDOANB0").getTextContent();
       String chandoanbd = nodeTD.getAttributes().getNamedItem("TIEPDON_MACHDOANBD").getTextContent();
       String dgchandoanbd = nodeTD.getAttributes().getNamedItem("TIEPDON_DGCHDOANBD").getTextContent();
       String madich = nodeTD.getAttributes().getNamedItem("TIEPDON_MADICH").getTextContent();
       String vuottuyen = nodeTD.getAttributes().getNamedItem("TIEPDON_VUOTTUYEN").getTextContent();
       String trongluong = nodeTD.getAttributes().getNamedItem("TIEPDON_TRLUONG").getTextContent();
       String chieucao = nodeTD.getAttributes().getNamedItem("TIEPDON_CHIEUCAO").getTextContent();
       String nhommau = nodeTD.getAttributes().getNamedItem("TIEPDON_NHOMMAU").getTextContent();
       String rh = nodeTD.getAttributes().getNamedItem("TIEPDON_RH").getTextContent();
       String giuong = nodeTD.getAttributes().getNamedItem("TIEPDON_GIUONG").getTextContent();
       String baotin = nodeTD.getAttributes().getNamedItem("TIEPDON_BAOTIN").getTextContent();
       String ketqua = nodeTD.getAttributes().getNamedItem("KETQUA_MA").getTextContent();
       String dieutri = nodeTD.getAttributes().getNamedItem("DIEUTRI_MA").getTextContent();
       String nghe = nodeTD.getAttributes().getNamedItem("TIEPDON_NGHE").getTextContent();
       String para1 = nodeTD.getAttributes().getNamedItem("TIEPDON_PARA1").getTextContent();
       String para2 = nodeTD.getAttributes().getNamedItem("TIEPDON_PARA2").getTextContent();
       String para3 = nodeTD.getAttributes().getNamedItem("TIEPDON_PARA3").getTextContent();
       String para4 = nodeTD.getAttributes().getNamedItem("TIEPDON_PARA4").getTextContent();
       String chuyenkhoa = nodeTD.getAttributes().getNamedItem("TIEPDON_CHKHOA").getTextContent();
       String chuyenvien = nodeTD.getAttributes().getNamedItem("TIEPDON_CHVIEN").getTextContent();
       String ngaygiora = nodeTD.getAttributes().getNamedItem("TIEPDON_NGAYGIORA").getTextContent();
       String tinhtrangra = nodeTD.getAttributes().getNamedItem("TIEPDON_TITRANGRA").getTextContent();
       String bschuyen = nodeTD.getAttributes().getNamedItem("TIEPDON_BSCHUYEN").getTextContent();
       String lydochvien = nodeTD.getAttributes().getNamedItem("TIEPDON_LYDOCHVI").getTextContent();
       String taikham = nodeTD.getAttributes().getNamedItem("TIEPDON_TAIKHAM").getTextContent();
       String loidan = nodeTD.getAttributes().getNamedItem("TIEPDON_LOIDAN").getTextContent();
       String tuvong = nodeTD.getAttributes().getNamedItem("TIEPDON_TUVONG").getTextContent();
       String cum = nodeTD.getAttributes().getNamedItem("TIEPDON_CUM").getTextContent();

       String status = nodeTD.getAttributes().getNamedItem("TIEPDON_STATUS").getTextContent();
       String ngaytt = nodeTD.getAttributes().getNamedItem("TIEPDON_NGAYTT").getTextContent();

       String giatri3 = nodeTD.getAttributes().getNamedItem("TIEPDON_GIATRI3").getTextContent();
       String giatri4 = nodeTD.getAttributes().getNamedItem("TIEPDON_GIATRI4").getTextContent();
       String moc1 = nodeTD.getAttributes().getNamedItem("TIEPDON_MOC1").getTextContent();
       String moc2 = nodeTD.getAttributes().getNamedItem("TIEPDON_MOC2").getTextContent();
       String moc3 = nodeTD.getAttributes().getNamedItem("TIEPDON_MOC3").getTextContent();
       String coGiayGioithieu = nodeTD.getAttributes().getNamedItem("TIEPDON_CO_GIAY_GIOI_THIEU").getTextContent();
       String khamDaLieu = nodeTD.getAttributes().getNamedItem("TIEPDON_KHAM_DA_LIEU").getTextContent();



       String sott = nodeTD.getAttributes().getNamedItem("TIEPDON_THUTU").getTextContent();
       if (!"".equals(sott)) {
         td.setTiepdonSoTT(Integer.valueOf(Integer.parseInt(sott)));
       }
       if ((coGiayGioithieu != null) && ((coGiayGioithieu.equals("1")) || (coGiayGioithieu.equalsIgnoreCase("true")) || (coGiayGioithieu.equalsIgnoreCase("on")))) {
         td.setTiepdonCoGiayGioiThieu(Boolean.valueOf(true));
       } else {
         td.setTiepdonCoGiayGioiThieu(Boolean.valueOf(false));
       }
       if ((khamDaLieu != null) && ((khamDaLieu.equals("1")) || (khamDaLieu.equalsIgnoreCase("true")) || (khamDaLieu.equalsIgnoreCase("on")))) {
         td.setTiepdonKhamDaLieu(Boolean.valueOf(true));
       } else {
         td.setTiepdonKhamDaLieu(Boolean.valueOf(false));
       }
       if (!"".equals(matiepdon)) {
         td.setTiepdonMa(matiepdon);
       }
       if (!"".equals(maphieuk)) {
         td.setTiepdonMaphieuk(maphieuk);
       }
       if (!"".equals(ngaygio))
       {
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
         cal.setTime(df.parse(ngaygio));
         td.setTiepdonNgaygio(cal.getTime());
       }
       if (td.getTiepdonNgaygio() == null) {
         td.setTiepdonNgaygio(new Date());
       }
       if (!"".equals(doituong))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmDoiTuong dtdmdoituong = (DmDoiTuong)ws.findByMa(doituong, "DmDoiTuong", "dmdoituongMa");




         td.setDoituongMa(dtdmdoituong);
       }
       if ("".equals(phanloai)) {
         td.setTiepdonPhanloai(phanloai);
       }
       if (!"".equals(tainan))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmTaiNan dtdmtainan = (DmTaiNan)ws.findByMa(tainan, "DmTaiNan", "dmtainanMa");





         td.setTainanMa(dtdmtainan);
       }
       if (!"".equals(donvigoi))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmBenhVien benhviengoi = (DmBenhVien)ws.findByMa(donvigoi, "DmBenhVien", "dmbenhvienMa");




         td.setTiepdonDonvigoi(benhviengoi);
       }
       if (!"".equals(khoibhyt))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DtDmKhoiBhyt khoi = (DtDmKhoiBhyt)ws.findByMa(khoibhyt, "DtDmKhoiBhyt", "dtdmkhoibhytMa");




         td.setKhoibhytMa(khoi);
       }
       if (!"".equals(tinhbhyt))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmTinh tinhbh = (DmTinh)ws.findByMa(tinhbhyt, "DmTinh", "dmtinhBHYT");




         td.setTinhbhytMa(tinhbh);
       }
       if (!"".equals(dpbhyt)) {
         td.setDpbhytMa(dpbhyt);
       }
       if (!"".equals(coquan)) {
         td.setTiepdonMacoquan(coquan);
       }
       if (!"".equals(sothebh)) {
         td.setTiepdonSothebh(sothebh);
       }
       if (!"".equals(nambhyt)) {
         td.setTiepdonNambhyt(nambhyt);
       }
       if (!"".equals(sonambh)) {
         td.setTiepdonSonambh(Short.valueOf(sonambh));
       }
       if (!"".equals(kcbbhyt))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmBenhVien kcb = (DmBenhVien)ws.findByMa(kcbbhyt, "DmBenhVien", "dmbenhvienMa");
         td.setKcbbhytMa(kcb);
         if (kcbbhyt.equals(IConstantsRes.MASO_CO_SO_KCB)) {
           tuyen = "1";
         } else if (IConstantsRes.CAP_TRIEN_KHAI_PHAN_MEM.toUpperCase().equals("TINH"))
         {
           if (kcbbhyt.substring(0, 2).equals(IConstantsRes.TINH_BHYT_DEFAULT)) {
             tuyen = "2";
           } else {
             tuyen = "3";
           }
         }
         else if ((IConstantsRes.CAP_TRIEN_KHAI_PHAN_MEM.toUpperCase().equals("HUYEN")) && (kcb != null))
         {
           if ((kcb.getDmhuyenMaso() != null) && (kcb.getDmhuyenMaso(true).getDmhuyenMaso().toString().equals(IConstantsRes.MASO_HUYEN_TRIEN_KHAI))) {
             tuyen = "2";
           } else {
             tuyen = "3";
           }
         }
         else {
           tuyen = "3";
         }
       }
       else
       {
         tuyen = "3";
       }
       if (!"".equals(tuyen)) {
         td.setTiepdonTuyen(Short.valueOf(tuyen));
       }
       if (!"".equals(giatri1))
       {
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         cal.setTime(df.parse(giatri1));
         td.setTiepdonGiatri1(cal.getTime());
       }
       if (!"".equals(giatri2))
       {
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         cal.setTime(df.parse(giatri2));
         td.setTiepdonGiatri2(cal.getTime());
       }
       if (!"".equals(giatri3))
       {
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         cal.setTime(df.parse(giatri3));
         td.setTiepdonGiatri3(cal.getTime());
       }
       if (!"".equals(giatri4))
       {
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         cal.setTime(df.parse(giatri4));
         td.setTiepdonGiatri4(cal.getTime());
       }
       if (!"".equals(moc1))
       {
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         cal.setTime(df.parse(moc1));
         td.setTiepdonMoc1(cal.getTime());
       }
       if (!"".equals(moc2))
       {
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         cal.setTime(df.parse(moc2));
         td.setTiepdonMoc2(cal.getTime());
       }
       if (!"".equals(moc3))
       {
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         cal.setTime(df.parse(moc3));
         td.setTiepdonMoc3(cal.getTime());
       }
       if (!"".equals(khaisinh)) {
         td.setTiepdonKhaisinh(khaisinh);
       }
       if (!"".equals(chungsinh)) {
         td.setTiepdonChungsinh(chungsinh);
       }
       if (!"".equals(sothete)) {
         td.setTiepdonSothete(sothete);
       }
       if (!"".equals(thengheo)) {
         td.setTiepdonThengheo(thengheo);
       }
       if (!"".equals(bankham))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DtDmBanKham bankhama = (DtDmBanKham)ws.findByMa(bankham, "DtDmBanKham", "dtdmbankhamMa");





         td.setTiepdonBankham(bankhama);
       }
       else
       {
         return null;
       }
       if (!"".equals(thutu)) {
         td.setTiepdonThutu(Short.valueOf(thutu));
       }
       if (!"".equals(thutus)) {
         td.setTiepdonThutus(Short.valueOf(thutus));
       }
       if (!"".equals(loaikham)) {
         td.setTiepdonLoaikham(loaikham);
       } else {
         return null;
       }
       if (!"".equals(bntra)) {
         td.setTiepdonBntra(Integer.valueOf(bntra));
       }
       if (!"".equals(doituongbhyt))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DtDmPlBhyt doituongbh = (DtDmPlBhyt)ws.findByMa(doituongbhyt, "DtDmPlBhyt", "dtdmphloaibhytMa");




         td.setTiepdonDoituongbhyt(doituongbh);
       }
       if (!"".equals(tylebh)) {
         td.setTiepdonTylebh(Short.valueOf(tylebh));
       }
       if (!"".equals(bacsi))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DtDmNhanVien dtnhanvien = (DtDmNhanVien)ws.findByMa(bacsi, "DtDmNhanVien", "dtdmnhanvienMa");




         td.setTiepdonBacsi(dtnhanvien);
       }
       if (!"".equals(nguoiduyet))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DtDmNhanVien dtnguoiduyet = (DtDmNhanVien)ws.findByMa(nguoiduyet, "DtDmNhanVien", "dtdmnhanvienMa");




         td.setTiepdonNguoiduyet(dtnguoiduyet);
       }
       if (!"".equals(kyhieu)) {
         td.setTiepdonKyhieu(kyhieu);
       }
       if (!"".equals(quyen)) {
         td.setTiepdonQuyen(quyen);
       }
       if (!"".equals(so)) {
         td.setTiepdonBienlai(so);
       }
       if (!"".equals(maphieud)) {
         td.setTiepdonMaphieud(maphieud);
       }
       if (!"".equals(nhanviencn))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DtDmNhanVien nvcn = (DtDmNhanVien)ws.findByMa(nhanviencn, "DtDmNhanVien", "dtdmnhanvienMa");




         td.setTiepdonNhanviencn(nvcn);
       }
       if (!"".equals(thungan))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DtDmNhanVien nvtn = (DtDmNhanVien)ws.findByMa(thungan, "DtDmNhanVien", "dtdmnhanvienMa");



         td.setTiepdonNhanviencn(nvtn);
       }
       if (!"".equals(phuongtien))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmPhuongThucGayTaiNan ptma = (DmPhuongThucGayTaiNan)ws.findByMa(phuongtien, "DmPhuongThucGayTaiNan", "dmptgtnMa");




         td.setDmptgtnMaso(ptma);
       }
       if (!"".equals(nguyennhan)) {
         td.setNguyenhanMa(nguyennhan);
       }
       if (!"".equals(diadiem))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmDiaDiem dtdiadiem = (DmDiaDiem)ws.findByMa(diadiem, "DmDiaDiem", "dmdiadiemMa");



         td.setDiadiemMa(dtdiadiem);
       }
       if (!"".equals(lydovaovien)) {
         td.setTiepdonLydovaov(lydovaovien);
       }
       if (!"".equals(chandoantt))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmBenhIcd cdtt = (DmBenhIcd)ws.findByMa(chandoantt, "DmBenhIcd", "dmbenhicdMa");



         td.setTiepdonMachdoanb0(cdtt);
       }
       if (!"".equals(dgchandoantt)) {
         td.setTiepdonDgchdoanb0(dgchandoantt);
       }
       if (!"".equals(chandoanbd))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmBenhIcd cdbd = (DmBenhIcd)ws.findByMa(chandoanbd, "DmBenhIcd", "dmbenhicdMa");



         td.setTiepdonMachdoanbd(cdbd);
       }
       if (!"".equals(dgchandoanbd)) {
         td.setTiepdonDgchdoanbd(dgchandoanbd);
       }
       if (!"".equals(madich))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmBenhTruyenNhiem dich = (DmBenhTruyenNhiem)ws.findByMa(madich, "DmBenhTruyenNhiem", "dmbtnMa");



         td.setTiepdonMadich(dich);
       }
       if (!"".equals(vuottuyen)) {
         td.setTiepdonVuottuyen(vuottuyen);
       }
       if (!"".equals(trongluong)) {
         td.setTiepdonTrluong(Double.valueOf(trongluong));
       }
       if (!"".equals(chieucao)) {
         td.setTiepdonChieucao(Double.valueOf(chieucao));
       }
       if (!"".equals(nhommau)) {
         td.setTiepdonNhommau(nhommau);
       }
       if (!"".equals(rh)) {
         td.setTiepdonRh(rh);
       }
       if (!"".equals(giuong)) {
         td.setTiepdonGiuong(giuong);
       }
       if (!"".equals(baotin)) {
         td.setTiepdonBaotin(baotin);
       }
       if (!"".equals(ketqua))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmKetQuaDieuTri kq = (DmKetQuaDieuTri)ws.findByMa(ketqua, "DmKetQuaDieuTri", "dmkqdtMa");



         td.setKetquaMa(kq);
       }
       if (!"".equals(dieutri))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmDieuTri dt = (DmDieuTri)ws.findByMa(dieutri, "DmDieuTri", "dmdieutriMa");



         td.setDieutriMa(dt);
       }
       if (!"".equals(nghe)) {
         td.setTiepdonNghe(nghe);
       }
       if (!"".equals(para1)) {
         td.setTiepdonPara1(para1);
       }
       if (!"".equals(para2)) {
         td.setTiepdonPara2(para2);
       }
       if (!"".equals(para3)) {
         td.setTiepdonPara3(para3);
       }
       if (!"".equals(para4)) {
         td.setTiepdonPara4(para4);
       }
       if (!"".equals(chuyenkhoa))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmKhoa ck = (DmKhoa)ws.findByMa(chuyenkhoa, "DmKhoa", "dmkhoaMa");



         td.setTiepdonChkhoa(ck);
       }
       if (!"".equals(chuyenvien))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmBenhVien cv = (DmBenhVien)ws.findByMa(chuyenvien, "DmBenhVien", "dmbenhvienMa");



         td.setTiepdonChvien(cv);
       }
       if (!"".equals(ngaygiora))
       {
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
         cal.setTime(df.parse(ngaygiora));
         td.setTiepdonNgaygiora(cal.getTime());
       }
       if (!"".equals(tinhtrangra)) {
         td.setTiepdonTitrangra(tinhtrangra);
       }
       if (!"".equals(bschuyen))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DtDmNhanVien bsc = (DtDmNhanVien)ws.findByMa(bschuyen, "DtDmNhanVien", "dtdmnhanvienMa");



         td.setTiepdonBschuyen(bsc);
       }
       if (!"".equals(lydochvien))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DtDmLyDoCv lydocv = (DtDmLyDoCv)ws.findByMa(lydochvien, "DtDmLyDoCv", "dtdmlydocvMa");



         td.setTiepdonLydochvi(lydocv);
       }
       if (!"".equals(taikham))
       {
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         cal.setTime(df.parse(taikham));
         td.setTiepdonTaikham(cal.getTime());
       }
       if (!"".equals(loidan)) {
         td.setTiepdonLoidan(loidan);
       }
       if (!"".equals(tuvong))
       {
         DieuTriUtilDelegate ws = DieuTriUtilDelegate.getInstance();
         DmBenhIcd tv = (DmBenhIcd)ws.findByMa(tuvong, "DmBenhIcd", "dmbenhicdMa");



         td.setTiepdonTuvong(tv);
       }
       if (!"".equals(cum)) {
         td.setTiepdonCum(cum);
       }
       if (!"".equals(status)) {
         td.setTiepdonStatus(status);
       }
       if (!"".equals(ngaytt))
       {
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         cal.setTime(df.parse(ngaytt));
         td.setTiepdonNgaytt(cal.getTime());
       }
       td.setTiepdonNgaygiocn(new Date());
     }
     else
     {
       return null;
     }
     return td;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.SendTiepDonAction

 * JD-Core Version:    0.7.0.1

 */