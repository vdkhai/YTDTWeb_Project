 package com.iesvn.yte.dieutri.ajax;

 import com.iesvn.yte.Action;
 import com.iesvn.yte.dieutri.delegate.CtNhapKhoDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuNhapKhoDelegate;
 import com.iesvn.yte.dieutri.entity.CtNhapKho;
 import com.iesvn.yte.dieutri.entity.DtDmKho;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.PhieuNhapKho;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiThuoc;
 import com.iesvn.yte.entity.DmNguonChuongTrinh;
 import com.iesvn.yte.entity.DmNguonKinhPhi;
 import com.iesvn.yte.entity.DmNhaCungCap;
 import com.iesvn.yte.entity.DmNhaSanXuat;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.ByteArrayInputStream;
 import java.io.IOException;
 import java.rmi.RemoteException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import javax.xml.parsers.DocumentBuilder;
 import javax.xml.parsers.DocumentBuilderFactory;
 import javax.xml.parsers.ParserConfigurationException;
 import javax.xml.rpc.ServiceException;
 import org.apache.log4j.Logger;
 import org.w3c.dom.Document;
 import org.w3c.dom.NamedNodeMap;
 import org.w3c.dom.Node;
 import org.w3c.dom.NodeList;
 import org.xml.sax.SAXException;

 public class SendPhieuNhapKhoAction
   extends Action
 {
   private static Logger logger = Logger.getLogger(SendPhieuNhapKhoAction.class);

   public String performAction(String request)
   {
     logger.debug("-----performAction()-----");
     String okId = "";
     String errorId = "";
     String xml = "";
     Document xmlDoc = null;
     int myBreak = 0;
     logger.debug("client request: " + request);
     try
     {
       xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(request.getBytes("UTF-8")));
     }
     catch (ParserConfigurationException ex)
     {
       logger.error("Error: " + ex.toString());
     }
     catch (SAXException ex)
     {
       logger.error("Error: " + ex.toString());
     }
     catch (IOException ex)
     {
       logger.error("Error: " + ex.toString());
     }
     if (xmlDoc != null)
     {
       NodeList listPNK = xmlDoc.getElementsByTagName("PHIEU_NHAP_KHO");
       if (listPNK.getLength() > 0)
       {
         logger.debug("Phieu nhap kho count " + listPNK.getLength());
         for (int i = 0; i < listPNK.getLength(); i++)
         {
           myBreak = 0;
           Node nodePN = listPNK.item(i);
           String maPhu = nodePN.getAttributes().getNamedItem("MAPHU").getTextContent();
           PhieuNhapKho pnk = null;
           try
           {
             pnk = getPhieuNhap(nodePN);
             if (pnk == null)
             {
               errorId = errorId + maPhu + "---";
               logger.debug("errorId " + errorId);
               continue;
             }
           }
           catch (Exception ex)
           {
             ex.printStackTrace();
           }
           logger.debug("-----------");

           NodeList listCTPN = nodePN.getChildNodes();
           if (listCTPN.getLength() > 0)
           {
             logger.debug("Chi tiet nhap kho count " + listCTPN.getLength());
             ArrayList<CtNhapKho> listCTNK = new ArrayList();
             ArrayList<TonKho> listTk = new ArrayList();
             for (int j = 0; j < listCTPN.getLength(); j++)
             {
               Node nodeCTNK = listCTPN.item(j);
               try
               {
                 TonKho tk = new TonKho();
                 CtNhapKho ctnk = getTonKho(nodeCTNK, tk);
                 if (tk != null)
                 {
                   Double dg = tk.getTonkhoDongia();
                   try
                   {
                     Double thue = pnk.getPhieunhapkhoMucthue();
                     dg = Double.valueOf(dg.doubleValue() + dg.doubleValue() * thue.doubleValue() / 100.0D);
                   }
                   catch (Exception ex) {}
                   ctnk.setCtnhapkhoThutu(Integer.valueOf(Integer.valueOf(j).intValue() + 1));
                   ctnk.setCtnhapkhoDongia(dg.doubleValue());

                   tk.setTonkhoDongia(dg);
                   tk.setDmnguonkinhphiMaso(pnk.getDmnguonkinhphiMaso());
                   tk.setDmnctMaso(pnk.getDmnctMaso());
                   tk.setDtdmkhoMaso(pnk.getDtdmkhoMaso());
                   tk.setDtdmnhanvienCn(pnk.getDtdmnhanvienCn());
                   tk.setDmNhaCungCap(pnk.getDtdmnoibanMa());

                   DmKhoa khoa = new DmKhoa();
                   khoa.setDmkhoaMaso(Integer.valueOf(IConstantsRes.KC_MASO));
                   tk.setDmkhoaMaso(khoa);
                 }
                 listCTNK.add(ctnk);
                 listTk.add(tk);
               }
               catch (Exception e)
               {
                 errorId = errorId + maPhu + "---";
                 logger.debug("errorId " + errorId);
                 myBreak = 1;
                 e.printStackTrace();
               }
             }
             if (myBreak == 0) {
               try
               {
                 PhieuNhapKhoDelegate pnkDelegate = PhieuNhapKhoDelegate.getInstance();
                 String pnkID = "" + pnkDelegate.createPhieuNhap(pnk, listCTNK, listTk);

                 logger.debug("insert phieu nhap kho " + pnkID);
                 if (!pnkID.equals("")) {
                   okId = okId + maPhu + "|" + pnkID + "---";
                 } else {
                   errorId = errorId + maPhu + "---";
                 }
               }
               catch (Exception ex)
               {
                 errorId = errorId + maPhu + "---";
                 logger.debug("Error: " + ex.toString());
               }
             }
           }
         }
         xml = okId + ";;;" + errorId;
         logger.debug("xml response: " + xml);
       }
     }
     return String.format("<result>%s</result>", new Object[] { xml });
   }

   public CtNhapKho getTonKho(Node nodeTK, TonKho tk)
     throws ServiceException, RemoteException, Exception
   {
     logger.debug("-----getTonKho()-----");
     logger.debug(String.format("-----nodeTK: %s", new Object[] { nodeTK }));
     CtNhapKho ctnk = new CtNhapKho();
     if (nodeTK != null)
     {
       String dmtMa = "" + nodeTK.getAttributes().getNamedItem("DMTHUOC_MA").getTextContent();
       String quocGiaMa = "" + nodeTK.getAttributes().getNamedItem("QUOCGIA_MA").getTextContent();
       String hangsxMa = "" + nodeTK.getAttributes().getNamedItem("HANGSX_MA").getTextContent();
       String tonKhoDG = "" + nodeTK.getAttributes().getNamedItem("CTNHAPKHO_DONGIA").getTextContent();
       String tonKhoDGB = "" + nodeTK.getAttributes().getNamedItem("CTNHAPKHO_DONGIABAN").getTextContent();
       String tonKhoNaN = "" + nodeTK.getAttributes().getNamedItem("CTNHAPKHO_NAMNHAP").getTextContent();

       String ngayHD = "" + nodeTK.getAttributes().getNamedItem("CTNHAPKHO_NGAYHANDUNG").getTextContent();
       String thangHD = "" + nodeTK.getAttributes().getNamedItem("CTNHAPKHO_THANGHANDUNG").getTextContent();
       String namHD = "" + nodeTK.getAttributes().getNamedItem("CTNHAPKHO_NAMHANDUNG").getTextContent();
       String soLuong = "" + nodeTK.getAttributes().getNamedItem("CTNHAPKHO_SOLUONG").getTextContent();
       String quyCach = "" + nodeTK.getAttributes().getNamedItem("CTNHAPKHO_QUYCACH").getTextContent();
       String ctnkMa = "" + nodeTK.getAttributes().getNamedItem("CTNHAPKHO_MA").getTextContent();
       String lo = "" + nodeTK.getAttributes().getNamedItem("CTNHAPKHO_LO").getTextContent();
       String sodk = "" + nodeTK.getAttributes().getNamedItem("CTNHAPKHO_SODANGKY").getTextContent();
       if ((!ctnkMa.equalsIgnoreCase("null")) && (!ctnkMa.equals(""))) {
         try
         {
           CtNhapKhoDelegate ctnkDelegate = CtNhapKhoDelegate.getInstance();
           ctnk = ctnkDelegate.find(Integer.valueOf(ctnkMa));
         }
         catch (Exception ex)
         {
           logger.error("-----Error: " + ex.toString());
         }
       }
       ctnk.setCtnhapkhoNgaygiocn(new Date());
       if ((!quyCach.equals("")) && (!quyCach.equalsIgnoreCase("null")))
       {
         logger.debug(String.format("-----quy cach: %s", new Object[] { quyCach }));
         ctnk.setCtnhapkhoQuycach(Integer.valueOf(quyCach));
       }
       else
       {
         ctnk.setCtnhapkhoQuycach(Integer.valueOf("0"));
       }
       if ((!soLuong.equals("")) && (!soLuong.equalsIgnoreCase("null")))
       {
         logger.debug(String.format("-----so luong: %s", new Object[] { soLuong }));
         ctnk.setCtnhapkhoSoluong(Double.valueOf(soLuong));
         tk.setTonkhoNhap(Double.valueOf(soLuong));
         logger.debug("-----so luong nhap: " + tk.getTonkhoNhap());
       }
       if ((!dmtMa.equals("")) && (!dmtMa.equalsIgnoreCase("null"))) {
         try
         {
           DieuTriUtilDelegate utilsDelegate = DieuTriUtilDelegate.getInstance();
           DmThuoc dmt = (DmThuoc)utilsDelegate.findByMa(dmtMa, "DmThuoc", "dmthuocMa");
           if (dmt != null)
           {
             logger.debug(String.format("-----dm thuoc ma: %s", new Object[] { dmt.getDmthuocMa() }));
             tk.setDmthuocMaso(dmt);
             ctnk.setDmthuocMaso(dmt);
           }
         }
         catch (Exception ex)
         {
           logger.error("-----Error: " + ex.toString());
         }
       }
       if ((!quocGiaMa.equals("")) && (!quocGiaMa.equalsIgnoreCase("null"))) {
         try
         {
           DieuTriUtilDelegate utilsDelegate = DieuTriUtilDelegate.getInstance();
           DmQuocGia qg = (DmQuocGia)utilsDelegate.findByMa(quocGiaMa, "DmQuocGia", "dmquocgiaMa");
           if (qg != null)
           {
             logger.debug(String.format("-----quoc gia: %s", new Object[] { qg.getDmquocgiaMa() }));
             tk.setDmquocgiaMaso(qg);
             ctnk.setDmquocgiaMaso(qg);
           }
         }
         catch (Exception ex)
         {
           logger.error("-----Error: " + ex.toString());
         }
       }
       if ((!hangsxMa.equals("")) && (!hangsxMa.equalsIgnoreCase("null"))) {
         try
         {
           DieuTriUtilDelegate utilsDelegate = DieuTriUtilDelegate.getInstance();
           DmNhaSanXuat hangsx = (DmNhaSanXuat)utilsDelegate.findByMa(hangsxMa, "DmNhaSanXuat", "dmnhasanxuatMa");
           if (hangsx != null)
           {
             logger.debug(String.format("-----hang sx: %s", new Object[] { hangsx.getDmnhasanxuatMa() }));
             tk.setDmnhasanxuatMaso(hangsx);
             ctnk.setDmnhasanxuatMaso(hangsx);
           }
         }
         catch (Exception ex)
         {
           logger.error("-----Error: " + ex.toString());
         }
       }
       if ((!tonKhoNaN.equals("")) && (!tonKhoNaN.equalsIgnoreCase("null")))
       {
         logger.debug(String.format("-----nam nhap: %s", new Object[] { tonKhoNaN }));
         tk.setTonkhoNamnhap(tonKhoNaN);
         ctnk.setCtnhapkhoNamnhap(tonKhoNaN);
       }
       if ((!tonKhoDG.equals("")) && (!tonKhoDG.equalsIgnoreCase("null")))
       {
         logger.debug(String.format("-----don gia: %s", new Object[] { tonKhoDG }));
         tk.setTonkhoDongia(new Double(tonKhoDG));
         ctnk.setCtnhapkhoDongia(new Double(tonKhoDG).doubleValue());
       }
       if ((!tonKhoDGB.equals("")) && (!tonKhoDGB.equalsIgnoreCase("null")))
       {
         logger.debug(String.format("-----don gia ban: %s", new Object[] { tonKhoDGB }));
         tk.setTonkhoDongiaban(new Double(tonKhoDGB));
         ctnk.setCtnhapkhoDongiaban(new Double(tonKhoDGB));
       }
       if ((!ngayHD.equals("")) && (!ngayHD.equalsIgnoreCase("null")))
       {
         tk.setTonkhoNgayhandung(ngayHD);
         ctnk.setCtnhapkhoNgayhandung(ngayHD);
       }
       else
       {
         tk.setTonkhoNgayhandung("");
         ctnk.setCtnhapkhoNgayhandung("");
       }
       if ((!thangHD.equals("")) && (!thangHD.equalsIgnoreCase("null")))
       {
         tk.setTonkhoThanghandung(thangHD);
         ctnk.setCtnhapkhoThanghandung(thangHD);
       }
       else
       {
         tk.setTonkhoThanghandung("");
         ctnk.setCtnhapkhoThanghandung("");
       }
       if ((!namHD.equals("")) && (!namHD.equalsIgnoreCase("null")))
       {
         tk.setTonkhoNamhandung(namHD);
         ctnk.setCtnhapkhoNamhandung(namHD);
       }
       else
       {
         tk.setTonkhoNamhandung("");
         ctnk.setCtnhapkhoNamhandung("");
       }
       if ((!lo.equals("")) && (!lo.equalsIgnoreCase("null")))
       {
         tk.setTonkhoLo(lo);
         ctnk.setCtnhapkhoLo(lo);
       }
       else
       {
         tk.setTonkhoLo("");
         ctnk.setCtnhapkhoLo("");
       }
       if ((!sodk.equals("")) && (!sodk.equalsIgnoreCase("null")))
       {
         tk.setTonkhoSodangky(sodk);
         ctnk.setCtnhapkhoSodangky(sodk);
       }
       else
       {
         tk.setTonkhoSodangky("");
         ctnk.setCtnhapkhoSodangky("");
       }
       tk.setTonkhoNgaygiocn(new Date());

       logger.debug("-------------so luong nhap: " + tk.getTonkhoNhap());
     }
     else
     {
       return null;
     }
     return ctnk;
   }

   public PhieuNhapKho getPhieuNhap(Node nodePN)
     throws ServiceException, RemoteException, Exception
   {
     PhieuNhapKho pnk = new PhieuNhapKho();
     if (nodePN != null)
     {
       String loaiMa = "" + nodePN.getAttributes().getNamedItem("LOAI_MA").getTextContent();
       String soHD = "" + nodePN.getAttributes().getNamedItem("PHIEUNHAPKHO_SOHOADON").getTextContent();
       String ngayHD = "" + nodePN.getAttributes().getNamedItem("PHIEUNHAPKHO_NGAYHOADON").getTextContent();
       String chungTu = "" + nodePN.getAttributes().getNamedItem("PHIEUNHAPKHO_CHUNGTU").getTextContent();
       String nguonMa = "" + nodePN.getAttributes().getNamedItem("NGUON_MA").getTextContent();
       String kpMa = "" + nodePN.getAttributes().getNamedItem("KINHPHI_MA").getTextContent();
       String nbMa = "" + nodePN.getAttributes().getNamedItem("NOIBAN_MA").getTextContent();
       String nvTL = "" + nodePN.getAttributes().getNamedItem("NHANVIEN_TIEPLIEU").getTextContent();
       String mucThue = "" + nodePN.getAttributes().getNamedItem("PHIEUNHAPKHO_MUCTHUE").getTextContent();
       String thue = "" + nodePN.getAttributes().getNamedItem("PHIEUNHAPKHO_THUE").getTextContent();
       String tt = "" + nodePN.getAttributes().getNamedItem("PHIEUNHAPKHO_THANHTIEN").getTextContent();
       String khoMa = "" + nodePN.getAttributes().getNamedItem("KHO_MA").getTextContent();
       String pnkMa = "" + nodePN.getAttributes().getNamedItem("PHIEUNHAPKHO_MA").getTextContent();
       String nhanVienCN = "" + nodePN.getAttributes().getNamedItem("DTDMNHANVIEN_CN").getTextContent();
       if ((!pnkMa.equals("")) && (!pnkMa.equalsIgnoreCase("null"))) {
         pnk.setPhieunhapkhoMa(pnkMa);
       }
       if ((!loaiMa.equals("")) && (!loaiMa.equalsIgnoreCase("null"))) {
         try
         {
           DieuTriUtilDelegate utilsDelegate = DieuTriUtilDelegate.getInstance();
           DmLoaiThuoc loai = (DmLoaiThuoc)utilsDelegate.findByMa(loaiMa, "DmLoaiThuoc", "dmloaithuocMa");
           if (loai != null)
           {
             logger.debug("loai: " + loai.getDmloaithuocTen());
             pnk.setDmloaithuocMaso(loai);
           }
         }
         catch (Exception ex)
         {
           logger.error("-----Error: " + ex.toString());
         }
       }
       if ((!nguonMa.equals("")) && (!nguonMa.equalsIgnoreCase("null"))) {
         try
         {
           DieuTriUtilDelegate utilsDelegate = DieuTriUtilDelegate.getInstance();
           DmNguonChuongTrinh nguon = (DmNguonChuongTrinh)utilsDelegate.findByMa(nguonMa, "DmNguonChuongTrinh", "dmnctMa");
           if (nguon != null)
           {
             logger.debug("nguon: " + nguon.getDmnctTen());
             pnk.setDmnctMaso(nguon);
           }
         }
         catch (Exception ex)
         {
           logger.error("-----Error: " + ex.toString());
         }
       }
       if ((!kpMa.equals("")) && (!kpMa.equalsIgnoreCase("null"))) {
         try
         {
           DieuTriUtilDelegate utilsDelegate = DieuTriUtilDelegate.getInstance();
           DmNguonKinhPhi kp = (DmNguonKinhPhi)utilsDelegate.findByMa(kpMa, "DmNguonKinhPhi", "dmnguonkinhphiMa");
           if (kp != null)
           {
             logger.debug("kinh phi: " + kp.getDmnguonkinhphiTen());
             pnk.setDmnguonkinhphiMaso(kp);
           }
         }
         catch (Exception ex)
         {
           logger.error("-----Error: " + ex.toString());
         }
       }
       if ((!nbMa.equals("")) && (!nbMa.equalsIgnoreCase("null"))) {
         try
         {
           DieuTriUtilDelegate utilsDelegate = DieuTriUtilDelegate.getInstance();
           DmNhaCungCap nb = (DmNhaCungCap)utilsDelegate.findByMa(nbMa, "DmNhaCungCap", "dmnhacungcapMa");
           if (nb != null)
           {
             logger.debug("noi ban: " + nb.getDmnhacungcapTen());
             pnk.setDtdmnoibanMa(nb);
           }
         }
         catch (Exception ex)
         {
           logger.error("-----Error: " + ex.toString());
         }
       }
       if ((!nvTL.equals("")) && (!nvTL.equalsIgnoreCase("null"))) {
         try
         {
           DieuTriUtilDelegate utilsDelegate = DieuTriUtilDelegate.getInstance();
           DtDmNhanVien nv = (DtDmNhanVien)utilsDelegate.findByMa(nvTL, "DtDmNhanVien", "dtdmnhanvienMa");
           if (nv != null)
           {
             logger.debug("nhan vien: " + nv.getDtdmnhanvienTen());

             pnk.setDtdmnhanvienTieplieu(nv);
           }
         }
         catch (Exception ex)
         {
           logger.error("-----Error: " + ex.toString());
         }
       }
       if ((!khoMa.equals("")) && (!khoMa.equalsIgnoreCase("null"))) {
         try
         {
           DieuTriUtilDelegate utilsDelegate = DieuTriUtilDelegate.getInstance();
           DtDmKho kho = (DtDmKho)utilsDelegate.findByMa(khoMa, "DtDmKho", "dtdmkhoMa");
           if (kho != null)
           {
             logger.debug("kho: " + kho.getDtdmkhoTen());

             pnk.setDtdmkhoMaso(kho);
           }
         }
         catch (Exception ex)
         {
           logger.error("-----Error: " + ex.toString());
         }
       }
       if ((!ngayHD.equals("")) && (!ngayHD.equalsIgnoreCase("null")))
       {
         Calendar cal = Calendar.getInstance();
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         try
         {
           cal.setTime(df.parse(ngayHD));
           pnk.setPhieunhapkhoNgayhoadon(cal.getTime());
         }
         catch (Exception ex)
         {
           logger.error("-----Error: " + ex.toString());
         }
       }
       if ((!nhanVienCN.equals("")) && (!nhanVienCN.equalsIgnoreCase("null"))) {
         try
         {
           DieuTriUtilDelegate utilsDelegate = DieuTriUtilDelegate.getInstance();
           DtDmNhanVien nv = (DtDmNhanVien)utilsDelegate.findByMa(nhanVienCN, "DtDmNhanVien", "dtdmnhanvienMa");
           if (nv != null)
           {
             logger.debug(String.format("-----nhan vien cap nhat: %s", new Object[] { nv.getDtdmnhanvienMa() }));
             pnk.setDtdmnhanvienCn(nv);
           }
         }
         catch (Exception ex)
         {
           logger.error("-----Error: " + ex.toString());
         }
       }
       if ((!soHD.equals("")) && (!soHD.equalsIgnoreCase("null"))) {
         pnk.setPhieunhapkhoSohoadon(soHD);
       }
       if ((!chungTu.equals("")) && (!chungTu.equalsIgnoreCase("null"))) {
         pnk.setPhieunhapkhoChungtu(chungTu);
       }
       if ((!mucThue.equals("")) && (!mucThue.equalsIgnoreCase("null"))) {
         pnk.setPhieunhapkhoMucthue(Double.valueOf(mucThue));
       }
       if ((!thue.equals("")) && (!thue.equalsIgnoreCase("null"))) {
         pnk.setPhieunhapkhoThue(Double.valueOf(thue));
       }
       if ((!tt.equals("")) && (!tt.equalsIgnoreCase("null"))) {
         pnk.setPhieunhapkhoThanhtien(Double.valueOf(tt));
       }
       pnk.setPhieunhapkhoNgaygiocn(new Date());
     }
     else
     {
       return null;
     }
     return pnk;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.ajax.SendPhieuNhapKhoAction

 * JD-Core Version:    0.7.0.1

 */