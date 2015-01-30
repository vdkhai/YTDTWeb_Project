 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.entity.TmpDataBhyt;
 import com.iesvn.yte.util.ConvertUnicodeToTCVN3;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import org.apache.log4j.Logger;
 import org.apache.poi.hssf.usermodel.HSSFCell;
 import org.apache.poi.hssf.usermodel.HSSFCellStyle;
 import org.apache.poi.hssf.usermodel.HSSFDataFormat;
 import org.apache.poi.hssf.usermodel.HSSFRow;
 import org.apache.poi.hssf.usermodel.HSSFSheet;
 import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 import org.apache.poi.poifs.filesystem.POIFSFileSystem;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.security.Restrict;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.CONVERSATION)
 @Name("B3342_XuatDuLieuBHYT")
 @Synchronized(timeout=6000000L)
 public class B3342_XuatDuLieuBHYT
   implements Serializable
 {
   private static Logger log = Logger.getLogger(B3342_XuatDuLieuBHYT.class);
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private String tungay = null;
   private String denngay = null;
   private String ngayhientai;
   private String chonloaibc = "";
   private String fileExport = null;
   private boolean reportFinish = false;
   int indexComm = 0;

   @Restrict("#{s:hasRole('NV_VienPhi') or s:hasRole('QT_VienPhi')}")
   @Begin(join=true)
   public String init()
     throws Exception
   {
     log.info("***Starting init ***");
     emtyData();
     log.info("***Finished init ***");

     SimpleDateFormat format = new SimpleDateFormat(Utils.FORMAT_DATE);
     this.ngayhientai = format.format(new Date());

     return "BaoCaoVienPhi_BaoCaoBHYT_XuatDuLieuBHYT";
   }

   @End
   public void endFunct() {}

   public void emtyData()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();
     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);
     this.tungay = sdf.format(currentDate);
     this.denngay = sdf.format(currentDate);
     setChonloaibc("l1");
   }

   public void resetValue()
   {
     emtyData();
   }

   public void xuatDuLieuBHYT()
   {
     log.info("Vao Method XuatDuLieuBHYT");
     try
     {
       Class.forName("com.mysql.jdbc.Driver");
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         log.info(e.getMessage());
         FacesMessages.instance().add(IConstantsRes.XUAT_DULIEU_BHYT_ERROR, new Object[0]);
         return;
       }
       if (conn == null)
       {
         FacesMessages.instance().add(IConstantsRes.XUAT_DULIEU_BHYT_ERROR, new Object[0]);
         return;
       }
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Date fromDate = sdf.parse(this.tungay);
       Date toDate = sdf.parse(this.denngay);
       POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(IConstantsRes.PATH_BASE + "/templates/bc25abhyt.xls"));
       HSSFWorkbook wbIn = new HSSFWorkbook(fs);

       FileOutputStream fileOut = new FileOutputStream(IConstantsRes.PATH_BASE + "/templates/result/bc25abhyt.xls");
       HSSFWorkbook wbOut = new HSSFWorkbook();
       if (this.chonloaibc.equals("l1"))
       {
         if (!exportDataNgoaiTru(wbIn, wbOut, fromDate, toDate, conn))
         {
           FacesMessages.instance().add(IConstantsRes.XUAT_DULIEU_BHYT_ERROR, new Object[0]);
           return;
         }
         if (!exportDataNoiTru(wbIn, wbOut, fromDate, toDate, false, this.indexComm, conn)) {
           FacesMessages.instance().add(IConstantsRes.XUAT_DULIEU_BHYT_ERROR, new Object[0]);
         }
       }
       else if (this.chonloaibc.equals("l2"))
       {
         if (!exportDataNgoaiTru(wbIn, wbOut, fromDate, toDate, conn)) {
           FacesMessages.instance().add(IConstantsRes.XUAT_DULIEU_BHYT_ERROR, new Object[0]);
         }
       }
       else if (!exportDataNoiTru(wbIn, wbOut, fromDate, toDate, true, 0, conn))
       {
         FacesMessages.instance().add(IConstantsRes.XUAT_DULIEU_BHYT_ERROR, new Object[0]);
         return;
       }
       wbOut.write(fileOut);
       fileOut.close();
       if (conn != null) {
         conn.close();
       }
       this.fileExport = "/templates/result/bc25abhyt.xls";
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatDuLieuBHYT!!!");
       FacesMessages.instance().add(IConstantsRes.XUAT_DULIEU_BHYT_ERROR + e.getMessage(), new Object[0]);
       e.printStackTrace();
     }
     setReportFinish(true);
     FacesMessages.instance().add(IConstantsRes.XUAT_DULIEU_BHYT_SUCCESS, new Object[0]);
     log.info("Thoat Method XuatDuLieuBHYT");
   }

   public boolean exportDataNgoaiTru(HSSFWorkbook wbIn, HSSFWorkbook wbOut, Date fromDate, Date toDate, Connection conn)
   {
     log.info("Vao Method exportDataNgoaiTru");
     try
     {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       String strTuNgay = sdf.format(fromDate);
       String strDenNgay = sdf.format(toDate);
       StringBuffer strQuery = new StringBuffer();
       strQuery.append("SELECT TD.TIEPDON_MA tiepdon, ");
       strQuery.append("BN.BENHNHAN_HOTEN hoten, BN.BENHNHAN_NAMSINH namsinh, GIOI.DMGT_MA gioitinh, ");
       strQuery.append("TD.TIEPDON_SOTHEBH mathe, DMTINH.DMTINH_MABHYT tinhkcb, (RIGHT(BENHVIEN.DMBENHVIEN_MA,3)) makcb, ");
       strQuery.append("BENH.DMBENHICD_MA mabenh, HS.HSTHTOANK_NGAYGIOTT ngayvao, HS.HSTHTOANK_NGAYGIOTT ngayra, ");
       strQuery.append("BENH.DMBENHICD_TEN tenbenh, BENHVIEN.DMBENHVIEN_MA noikham, ");
       strQuery.append("month(HS.HSTHTOANK_NGAYGIOTT) thangqt, year(HS.HSTHTOANK_NGAYGIOTT) namqt, TD.TIEPDON_GIATRI1 gtritu,");
       strQuery.append("TD.TIEPDON_GIATRI2 gtriden, concat(BN.BENHNHAN_DIACHI,',',XA.DMXA_TEN,',',HUYEN.DMHUYEN_TEN,',',TINH.DMTINH_TEN) diachi, 'NGOAI' loaikcb, 'CSKCB' noittoan, ");
       strQuery.append("HS.HSTHTOANK_MA sophieu, TK.THAMKHAM_BANKHAM makhoa, ");
       strQuery.append("(select DTDMMQLBHYT_VANCHUYEN from dt_dm_mql_bhyt where (DTDMMQLBHYT_MA = substr(TD.TIEPDON_SOTHEBH,3,1))) vanchuyen, ");
       strQuery.append("TD.TIEPDON_TUYEN dieutri, coalesce(TD.TIEPDON_CO_GIAY_GIOI_THIEU,0) giaygt,");
       strQuery.append("HS.HSTHTOANK_XNTDCN AS xetnghiem, HS.HSTHTOANK_CDHA cdha, ");
       strQuery.append("(if (HS.HSTHTOANK_THUOC is null, 0 , HS.HSTHTOANK_THUOC) + if (HS.HSTHTOANK_THUOCNDM is null, 0 , HS.HSTHTOANK_THUOCNDM ) ) thuoc, ");
       strQuery.append("HS.HSTHTOANK_MAU mau, ");
       strQuery.append("(coalesce(HS.HSTHTOANK_DVKT_TT,0) + coalesce(HS.HSTHTOANK_PHAUTHUAT,0)) pttt, ");
       strQuery.append("(if (HS.HSTHTOANK_VTTH is null, 0 , HS.HSTHTOANK_VTTH) + if (HS.HSTHTOANK_VTTHNDM is null, 0 ,HS. HSTHTOANK_VTTHNDM ) ) vtth, ");
       strQuery.append("'' vttthe, ");
       strQuery.append("HS.HSTHTOANK_DV_KTC ktc, ");
       strQuery.append("HS.HSTHTOANK_CONGKHAM tienkham,  ");
       strQuery.append("HS.HSTHTOANK_CP_VC chiphivanchuyen, ");
       strQuery.append("HS.HSTHTOANK_TONGCHI tongchi, HS.HSTHTOANK_BNTRA bntra, HS.HSTHTOANK_BHYT bhyt, ");
       strQuery.append("1 songaydieutri ");
       strQuery.append("FROM HS_THTOANK HS LEFT JOIN TIEP_DON TD ON HS.TIEPDON_MA = TD.TIEPDON_MA  ");
       strQuery.append("LEFT JOIN dm_tinh DMTINH ON TD.TINHBHYT_MA = DMTINH.DMTINH_MASO ");
       strQuery.append("INNER JOIN tham_kham TK ON TK.TIEPDON_MA = TD.TIEPDON_MA ");
       strQuery.append("LEFT JOIN BENH_NHAN BN ON TD.BENHNHAN_MA = BN.BENHNHAN_MA ");
       strQuery.append("LEFT JOIN DM_XA XA ON BN.XA_MA = XA.DMXA_MASO ");
       strQuery.append("LEFT JOIN DM_HUYEN HUYEN ON BN.HUYEN_MA = HUYEN.DMHUYEN_MASO ");
       strQuery.append("LEFT JOIN DM_TINH TINH ON BN.TINH_MA = TINH.DMTINH_MASO ");
       strQuery.append("LEFT JOIN DM_GIOI_TINH GIOI ON BN.DMGT_MASO = GIOI.DMGT_MASO ");
       strQuery.append("LEFT JOIN DM_BENH_ICD BENH ON TK.BENHICD10 = BENH.DMBENHICD_MASO ");
       strQuery.append("LEFT JOIN DM_DOI_TUONG DOITUONG ON TD.DOITUONG_MA = DOITUONG.DMDOITUONG_MASO ");
       strQuery.append("LEFT JOIN DT_DM_KHOI_BHYT KHOIBHYT ON TD.KHOIBHYT_MA = KHOIBHYT.DTDMKHOIBHYT_MA ");
       strQuery.append("LEFT JOIN DT_DM_NHOM_BHYT NHOMBHYT ON KHOIBHYT.DTDMNHOMBHYT_MASO = NHOMBHYT.DTDMNHOMBHYT_MASO ");
       strQuery.append("LEFT JOIN DM_BENH_VIEN BENHVIEN  ON TD.KCBBHYT_MA = BENHVIEN.DMBENHVIEN_MASO ");
       strQuery.append("WHERE TD.DOITUONG_MA = 2 AND HS.HSTHTOANK_NGAYGIOTT is not null ");
       strQuery.append("AND DATE(HS.HSTHTOANK_NGAYGIOTT) >= DATE('" + strTuNgay + "') AND DATE(HS.HSTHTOANK_NGAYGIOTT) <= DATE('" + strDenNgay + "') ");
       strQuery.append("AND NHOMBHYT.DTDMPHLOAIBHYT_MASO = 6 ");
       strQuery.append("AND TD.TIEPDON_BANKHAM is not null ");
       strQuery.append("GROUP BY TD.TIEPDON_MA ");
       strQuery.append("ORDER BY TD.TIEPDON_MA");

       PreparedStatement pstmt = conn.prepareStatement(strQuery.toString());
       ResultSet rs = pstmt.executeQuery();
       List lstTmpDataNgoaitru = new ArrayList();
       SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
       while (rs.next())
       {
         TmpDataBhyt dataNoiTru = new TmpDataBhyt();
         dataNoiTru.setTiepDon(rs.getString("tiepdon"));
         dataNoiTru.setHoTen(rs.getString("hoten"));
         dataNoiTru.setNamSinh(rs.getString("namsinh") == null ? new Integer("0") : new Integer(rs.getString("namsinh")));
         dataNoiTru.setGioiTinh(Short.valueOf(rs.getString("gioitinh") == null ? new Short("0").shortValue() : Short.parseShort(rs.getString("gioitinh"))));
         dataNoiTru.setMaThe(rs.getString("mathe") == null ? "" : rs.getString("mathe"));
         dataNoiTru.setMaDkbd(rs.getString("tinhkcb") + (rs.getString("makcb") == null ? "" : rs.getString("makcb")));
         dataNoiTru.setMaBenh(rs.getString("mabenh") == null ? "" : rs.getString("mabenh"));
         dataNoiTru.setNgayVao(rs.getDate("ngayvao") == null ? sdf2.format(new Date()) : sdf2.format(rs.getDate("ngayvao")));
         dataNoiTru.setNgayRa(rs.getDate("ngayra") == null ? sdf2.format(new Date()) : sdf2.format(rs.getDate("ngayra")));

         dataNoiTru.setNgayDtr(new Integer("" + Utils.getSoNgayDieuTri(rs.getDate("ngayvao") == null ? new Date() : rs.getDate("ngayvao"), rs.getDate("ngayra") == null ? new Date() : rs.getDate("ngayra"))));
         dataNoiTru.setTienXetnghiem(Double.valueOf(rs.getDouble("xetnghiem")));
         dataNoiTru.setTienCdha(Double.valueOf(rs.getDouble("cdha")));
         dataNoiTru.setTienThuoc(Double.valueOf(rs.getDouble("thuoc")));
         dataNoiTru.setTienMau(Double.valueOf(rs.getDouble("mau")));
         dataNoiTru.setTienPttt(Double.valueOf(rs.getDouble("pttt")));
         dataNoiTru.setTienVtytth(Double.valueOf(rs.getDouble("vtth")));
         dataNoiTru.setTienVtyttthe(new Double(0.0D));
         dataNoiTru.setTienDvktc(Double.valueOf(rs.getDouble("ktc")));
         dataNoiTru.setTienKtg(new Double(0.0D));

         dataNoiTru.setTienKham(Double.valueOf(rs.getDouble("tienkham")));
         dataNoiTru.setTienVanchuyen(Double.valueOf(rs.getDouble("chiphivanchuyen")));
         dataNoiTru.setTongChi(Double.valueOf(rs.getDouble("tongchi")));

         Double tienBNTra = Double.valueOf(rs.getDouble("bntra") < 1.0E-007D ? new Double(0.0D).doubleValue() : rs.getDouble("bntra"));
         dataNoiTru.setTienBntra(tienBNTra);
         dataNoiTru.setTienBhxh(Double.valueOf(rs.getDouble("bhyt")));
         dataNoiTru.setTienNgoaids(Double.valueOf(rs.getInt("vanchuyen") == 1 ? rs.getDouble("chiphivanchuyen") : new Double(0.0D).doubleValue()));

         int dieutri = rs.getInt("dieutri");

         int cogiaygioithieu = rs.getInt("giaygt");
         if (dieutri == 1) {
           dataNoiTru.setLydoVv(Integer.valueOf(1));
         } else if (cogiaygioithieu == 1) {
           dataNoiTru.setLydoVv(Integer.valueOf(1));
         } else {
           dataNoiTru.setLydoVv(Integer.valueOf(0));
         }
         dataNoiTru.setBenhKhac(rs.getString("tenbenh") == null ? "" : rs.getString("tenbenh"));

         String maBV = rs.getString("noikham");
         String noiKham = " ";
         String maNoiKham = "";
         if (maBV != null)
         {
           String maTinh = maBV.substring(0, maBV.indexOf("."));
           maNoiKham = maBV.substring(maBV.indexOf(".") + 1, maBV.length());
           noiKham = maTinh + maNoiKham;
         }
         dataNoiTru.setNoiKcb(noiKham);
         dataNoiTru.setMaKhoa(Integer.valueOf(rs.getInt("makhoa")));
         dataNoiTru.setThangQt(Integer.valueOf(rs.getInt("thangqt")));
         dataNoiTru.setNamQt(Integer.valueOf(rs.getInt("namqt")));
         dataNoiTru.setGtTu(rs.getDate("gtritu") == null ? "" : sdf2.format(rs.getDate("gtritu")));
         dataNoiTru.setGtDen(rs.getDate("gtriden") == null ? "" : sdf2.format(rs.getDate("gtriden")));
         dataNoiTru.setDiaChi(rs.getString("diachi") == null ? "" : rs.getString("diachi"));
         dataNoiTru.setGiamDinh("");
         dataNoiTru.setXuatToan("");
         dataNoiTru.setLydoXuattoan("");
         dataNoiTru.setDaTuyen(Integer.valueOf(0));
         dataNoiTru.setVuotTran(Integer.valueOf(0));
         dataNoiTru.setLoaiKcb(rs.getString("loaikcb"));
         dataNoiTru.setNoiThanhtoan(rs.getString("noittoan"));
         dataNoiTru.setSoPhieu(rs.getString("sophieu"));
         if (dieutri == 1) {
           dataNoiTru.setTuyen("A");
         } else if (dieutri == 2) {
           dataNoiTru.setTuyen("B");
         } else {
           dataNoiTru.setTuyen("C");
         }
         lstTmpDataNgoaitru.add(dataNoiTru);
       }
       log.info("lstTmpDataNgoaitru.size: " + lstTmpDataNgoaitru.size());

       HSSFSheet worksheetNGT = wbOut.createSheet("Ngoai Tru");

       HSSFSheet sheetNGT = wbIn.getSheetAt(0);
       HSSFRow rowheadIn = sheetNGT.getRow(0);
       HSSFCellStyle cellStyleIn = wbIn.getCellStyleAt((short)0);
       HSSFCellStyle cellStyleOut = wbOut.createCellStyle();
       cellStyleOut.setFillBackgroundColor((short)10);
       int socot = rowheadIn.getPhysicalNumberOfCells();
       HSSFRow rowHeadOut = worksheetNGT.createRow(0);
       for (short i = 0; i < socot; i = (short)(i + 1))
       {
         HSSFCell cell = rowHeadOut.createCell(i);
         cell.setCellValue(rowheadIn.getCell(i).toString());
         cell.setCellStyle(cellStyleOut);
       }
       int index = 1;
       exportData(index, wbOut, worksheetNGT, lstTmpDataNgoaitru);
     }
     catch (Exception er)
     {
       er.printStackTrace();
       return false;
     }
     log.info("Thoat Method exportDataNgoaiTru");
     return true;
   }

   public boolean exportDataNoiTru(HSSFWorkbook wbIn, HSSFWorkbook wbOut, Date fromDate, Date toDate, boolean xoabangtam, int index, Connection conn)
   {
     log.info("Vao Method exportDataNoiTru");
     try
     {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       String strTuNgay = sdf.format(fromDate);
       String strDenNgay = sdf.format(toDate);
       StringBuffer strQuery = new StringBuffer();
       strQuery.append("SELECT HSBA.HSBA_SOVAOVIEN benhan, ");
       strQuery.append("BN.BENHNHAN_HOTEN hoten, BN.BENHNHAN_NAMSINH namsinh, GIOI.DMGT_MA gioitinh, ");
       strQuery.append("HSBABHYT.HSBABHYT_SOTHEBH mathe, DMTINH.DMTINH_MABHYT tinhkcb, (RIGHT(BENHVIEN.DMBENHVIEN_MA,3)) makcb, ");
       strQuery.append("BENH.DMBENHICD_MA mabenh, HSBA.HSBA_NGAYGIOVAOV ngayvao, HSBA.HSBA_NGAYGIORAV ngayra, ");
       strQuery.append("BENH.DMBENHICD_TEN tenbenh, BENHVIEN.DMBENHVIEN_MA noikham, ");
       strQuery.append("month(HSBA.HSBA_NGAYGIORAV) thangqt, year(HSBA.HSBA_NGAYGIORAV) namqt, HSBABHYT.HSBABHYT_GIATRI0 gtritu, ");
       strQuery.append("HSBABHYT.HSBABHYT_GIATRI1 gtriden, concat(BN.BENHNHAN_DIACHI,',',XA.DMXA_TEN,',',HUYEN.DMHUYEN_TEN,',',TINH.DMTINH_TEN) diachi, 'NOI' loaikcb, 'CSKCB' noittoan, ");
       strQuery.append("HS.HSTHTOAN_MA sophieu, HSBA.HSBA_KHOARAV makhoa, ");
       strQuery.append("(select DTDMMQLBHYT_VANCHUYEN from dt_dm_mql_bhyt where DTDMMQLBHYT_MASO = substring(HSBABHYT.HSBABHYT_SOTHEBH,3,1)) vanchuyen, ");
       strQuery.append(" HSBABHYT.HSBABHYT_TUYEN dieutri, coalesce(HSBABHYT.HSBABHYT_CO_GIAY_CHUYEN_VIEN,0) giaycv, ");
       strQuery.append("HS.HSTHTOAN_XNTDCN  xetnghiem, HS.HSTHTOAN_CDHA cdha, ");
       strQuery.append("(if (HS.HSTHTOAN_THUOC is null, 0 , HS.HSTHTOAN_THUOC) + if (HS.HSTHTOAN_THUOCNDM is null, 0 , HS.HSTHTOAN_THUOCNDM ) ) AS thuoc, ");
       strQuery.append("HS.HSTHTOAN_MAU AS mau, ");
       strQuery.append("(if (HS.HSTHTOAN_DVKT_TT is null, 0 , HS.HSTHTOAN_DVKT_TT) + if (HS.HSTHTOAN_PHAUTHUAT is null, 0 , HS.HSTHTOAN_PHAUTHUAT ) ) AS pttt, ");
       strQuery.append("(if (HS.HSTHTOAN_VTTH is null, 0 , HS.HSTHTOAN_VTTH) + if (HS.HSTHTOAN_VTTHNDM is null, 0 , HS.HSTHTOAN_VTTHNDM ) ) AS vtth, ");
       strQuery.append("'' vttthe, ");
       strQuery.append("HS.HSTHTOAN_DV_KTC AS ktc, ");
       strQuery.append("(if (HS.HSTHTOAN_PHONG is null, 0 , HS.HSTHTOAN_PHONG) + if (HS.HSTHTOAN_PHONGNDM is null, 0 , HS.HSTHTOAN_PHONGNDM ) ) AS giuong, ");
       strQuery.append("HS.HSTHTOAN_CP_VC AS chiphivanchuyen, ");
       strQuery.append("HS.HSTHTOAN_TONGCHI tongchi, HS.HSTHTOAN_BNTRA bntra, HS.HSTHTOAN_BHYT bhxh, HS.HSTHTOAN_NDM ngoaiDS, ");
       strQuery.append("(DATEDIFF(IF( hsba.HSBA_NGAYGIORAV IS NULL, DATE(NOW()),DATE(hsba.HSBA_NGAYGIORAV) ),IF( hsba.HSBA_NGAYGIOVAOV IS NULL,DATE(NOW()),DATE(hsba.HSBA_NGAYGIOVAOV) ))) songaydieutri ");
       strQuery.append("FROM HS_THTOAN HS LEFT JOIN HSBA ON HS.HSBA_SOVAOVIEN = HSBA.HSBA_SOVAOVIEN ");
       strQuery.append("LEFT JOIN BENH_NHAN BN ON HSBA.BENHNHAN_MA = BN.BENHNHAN_MA ");
       strQuery.append("LEFT JOIN DM_XA XA ON BN.XA_MA = XA.DMXA_MASO ");
       strQuery.append("LEFT JOIN DM_HUYEN HUYEN ON BN.HUYEN_MA = HUYEN.DMHUYEN_MASO ");
       strQuery.append("LEFT JOIN DM_TINH TINH ON BN.TINH_MA = TINH.DMTINH_MASO ");
       strQuery.append("LEFT JOIN DM_GIOI_TINH GIOI ON BN.DMGT_MASO = GIOI.DMGT_MASO ");
       strQuery.append("LEFT JOIN HSBA_BHYT HSBABHYT ON HSBABHYT.HSBA_SOVAOVIEN = HSBA.HSBA_SOVAOVIEN ");
       strQuery.append("LEFT JOIN DM_DOI_TUONG DOITUONG ON HSBA.DOITUONG_MA = DOITUONG.DMDOITUONG_MASO ");
       strQuery.append("LEFT JOIN DM_BENH_ICD BENH ON HSBA.HSBA_MACHDRAVIEN = BENH.DMBENHICD_MASO ");
       strQuery.append("LEFT JOIN DT_DM_KHOI_BHYT KHOIBHYT ON HSBABHYT.HSBABHYT_KHOIBH = KHOIBHYT.DTDMKHOIBHYT_MASO ");
       strQuery.append("LEFT JOIN DT_DM_NHOM_BHYT NHOMBHYT ON KHOIBHYT.DTDMNHOMBHYT_MASO = NHOMBHYT.DTDMNHOMBHYT_MASO ");
       strQuery.append("LEFT JOIN DM_BENH_VIEN BENHVIEN ON HSBABHYT.HSBABHYT_MAKCB = BENHVIEN.DMBENHVIEN_MASO ");
       strQuery.append("LEFT JOIN DM_BENH_VIEN BENHVIEN_GOI ON HSBA.HSBA_DONVIGOI = BENHVIEN_GOI.DMBENHVIEN_MASO ");
       strQuery.append("LEFT JOIN DM_TINH DMTINH ON HSBABHYT.HSBABHYT_TINHBH = DMTINH.DMTINH_MASO ");
       strQuery.append("WHERE HSBA.DOITUONG_MA = 2 AND HSBA.HSBA_NGAYGIORAV Is Not Null ");
       strQuery.append("AND DATE(HSBA.HSBA_NGAYGIORAV) >= DATE('" + strTuNgay + "') AND DATE(HSBA.HSBA_NGAYGIORAV) <= DATE('" + strDenNgay + "') ");
       strQuery.append("AND NHOMBHYT.DTDMPHLOAIBHYT_MASO = 6 ");
       strQuery.append("GROUP BY HSBA.HSBA_SOVAOVIEN ");
       strQuery.append("ORDER BY HSBA.HSBA_SOVAOVIEN");

       PreparedStatement pstmt = conn.prepareStatement(strQuery.toString());
       ResultSet rs = pstmt.executeQuery();
       List lstTmpDataNoitru = new ArrayList();
       SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");
       while (rs.next())
       {
         TmpDataBhyt dataNoiTru = new TmpDataBhyt();
         dataNoiTru.setTiepDon(rs.getString("benhan"));
         dataNoiTru.setHoTen(rs.getString("hoten"));
         dataNoiTru.setNamSinh(rs.getString("namsinh") == null ? new Integer("0") : new Integer(rs.getString("namsinh")));
         dataNoiTru.setGioiTinh(Short.valueOf(rs.getString("gioitinh") == null ? new Short("0").shortValue() : Short.parseShort(rs.getString("gioitinh"))));
         dataNoiTru.setMaThe(rs.getString("mathe") == null ? "" : rs.getString("mathe"));
         dataNoiTru.setMaDkbd(rs.getString("tinhkcb") + (rs.getString("makcb") == null ? "" : rs.getString("makcb")));
         dataNoiTru.setMaBenh(rs.getString("mabenh") == null ? "" : rs.getString("mabenh"));
         dataNoiTru.setMaBenh(rs.getString("mabenh") == null ? "" : rs.getString("mabenh"));
         dataNoiTru.setNgayVao(rs.getDate("ngayvao") == null ? sdf2.format(new Date()) : sdf2.format(rs.getDate("ngayvao")));
         dataNoiTru.setNgayRa(rs.getDate("ngayra") == null ? sdf2.format(new Date()) : sdf2.format(rs.getDate("ngayra")));

         dataNoiTru.setNgayDtr(new Integer("" + Utils.getSoNgayDieuTri(rs.getDate("ngayvao") == null ? new Date() : rs.getDate("ngayvao"), rs.getDate("ngayra") == null ? new Date() : rs.getDate("ngayra"))));
         dataNoiTru.setTienXetnghiem(Double.valueOf(rs.getDouble("xetnghiem")));
         dataNoiTru.setTienCdha(Double.valueOf(rs.getDouble("cdha")));
         dataNoiTru.setTienThuoc(Double.valueOf(rs.getDouble("thuoc")));
         dataNoiTru.setTienMau(Double.valueOf(rs.getDouble("mau")));
         dataNoiTru.setTienPttt(Double.valueOf(rs.getDouble("pttt")));
         dataNoiTru.setTienVtytth(Double.valueOf(rs.getDouble("vtth")));
         dataNoiTru.setTienVtyttthe(new Double(0.0D));
         dataNoiTru.setTienDvktc(Double.valueOf(rs.getDouble("ktc")));
         dataNoiTru.setTienKtg(new Double(0.0D));

         dataNoiTru.setTienKham(Double.valueOf(rs.getDouble("giuong")));
         dataNoiTru.setTienVanchuyen(Double.valueOf(rs.getDouble("chiphivanchuyen")));
         dataNoiTru.setTongChi(Double.valueOf(rs.getDouble("tongchi")));

         Double tienBNTra = Double.valueOf(rs.getDouble("bntra") < 1.0E-007D ? new Double(0.0D).doubleValue() : rs.getDouble("bntra"));
         dataNoiTru.setTienBntra(tienBNTra);
         dataNoiTru.setTienBhxh(Double.valueOf(rs.getDouble("bhxh")));
         dataNoiTru.setTienNgoaids(Double.valueOf(rs.getInt("vanchuyen") == 1 ? rs.getDouble("chiphivanchuyen") : new Double(0.0D).doubleValue()));

         int dieutri = rs.getInt("dieutri");

         int cogiaygioithieu = rs.getInt("giaycv");
         if (dieutri == 1) {
           dataNoiTru.setLydoVv(Integer.valueOf(1));
         } else if (cogiaygioithieu == 1) {
           dataNoiTru.setLydoVv(Integer.valueOf(1));
         } else {
           dataNoiTru.setLydoVv(Integer.valueOf(0));
         }
         dataNoiTru.setBenhKhac(rs.getString("tenbenh") == null ? "" : rs.getString("tenbenh"));

         String maBV = rs.getString("noikham");
         String noiKham = " ";
         String maNoiKham = "";
         if (maBV != null)
         {
           String maTinh = maBV.substring(0, maBV.indexOf("."));
           maNoiKham = maBV.substring(maBV.indexOf(".") + 1, maBV.length());
           noiKham = maTinh + maNoiKham;
         }
         dataNoiTru.setNoiKcb(noiKham);
         dataNoiTru.setMaKhoa(Integer.valueOf(rs.getInt("makhoa")));
         dataNoiTru.setThangQt(Integer.valueOf(rs.getInt("thangqt")));
         dataNoiTru.setNamQt(Integer.valueOf(rs.getInt("namqt")));
         dataNoiTru.setGtTu(rs.getDate("gtritu") == null ? "" : sdf2.format(rs.getDate("gtritu")));
         dataNoiTru.setGtDen(rs.getDate("gtriden") == null ? "" : sdf2.format(rs.getDate("gtriden")));
         dataNoiTru.setDiaChi(rs.getString("diachi") == null ? "" : rs.getString("diachi"));
         dataNoiTru.setGiamDinh("");
         dataNoiTru.setXuatToan("");
         dataNoiTru.setLydoXuattoan("");
         dataNoiTru.setDaTuyen(Integer.valueOf(0));
         dataNoiTru.setVuotTran(Integer.valueOf(0));
         dataNoiTru.setLoaiKcb(rs.getString("loaikcb"));
         dataNoiTru.setNoiThanhtoan(rs.getString("noittoan"));
         dataNoiTru.setSoPhieu(rs.getString("sophieu"));
         if (dieutri == 1) {
           dataNoiTru.setTuyen("A");
         } else if (dieutri == 2) {
           dataNoiTru.setTuyen("B");
         } else {
           dataNoiTru.setTuyen("C");
         }
         lstTmpDataNoitru.add(dataNoiTru);
       }
       log.info("lstTmpDataNoitru.size: " + lstTmpDataNoitru.size());

       HSSFSheet worksheetInNT = wbIn.getSheetAt(0);
       HSSFRow rowheadInNT = worksheetInNT.getRow(0);
       HSSFCellStyle cellStyleIn = wbIn.getCellStyleAt((short)0);
       HSSFCellStyle cellStyleOut = wbOut.createCellStyle();
       cellStyleOut.setFillBackgroundColor(cellStyleIn.getFillForegroundColor());
       int socot = rowheadInNT.getPhysicalNumberOfCells();
       HSSFSheet worksheetOutNT = wbOut.createSheet("Noi Tru");
       HSSFRow rowHeadOutNT = worksheetOutNT.createRow(0);
       for (short i = 0; i < socot; i = (short)(i + 1))
       {
         HSSFCell cell = rowHeadOutNT.createCell(i);
         cell.setCellValue(rowheadInNT.getCell(i).toString());
         cell.setCellStyle(cellStyleOut);
       }
       index++;
       exportData(index, wbOut, worksheetOutNT, lstTmpDataNoitru);
     }
     catch (Exception er)
     {
       er.printStackTrace();
       return false;
     }
     log.info("Thoat Method exportDataNoiTru");
     return true;
   }

   public void exportData(int index, HSSFWorkbook wbOut, HSSFSheet worksheet, List<TmpDataBhyt> lstTmpData)
   {
     log.info("Vao Method exportData");
     ConvertUnicodeToTCVN3 cvtUnicodeToTcvn3 = new ConvertUnicodeToTCVN3();
     for (TmpDataBhyt tmpData : lstTmpData)
     {
       HSSFRow row = worksheet.createRow((short)index);
       row.createCell((short)0).setCellValue(index);
       String strHoTen = tmpData.getHoTen().toLowerCase();
       strHoTen = printCapitalized(strHoTen);
       strHoTen = cvtUnicodeToTcvn3.convertCAP(strHoTen);

       row.createCell((short)1).setCellValue(strHoTen);
       row.createCell((short)2).setCellValue(tmpData.getNamSinh().intValue());
       row.createCell((short)3).setCellValue(tmpData.getGioiTinh().shortValue());
       row.createCell((short)4).setCellValue(tmpData.getMaThe());
       row.createCell((short)5).setCellValue(tmpData.getMaDkbd());
       row.createCell((short)6).setCellValue(tmpData.getMaBenh());

       HSSFDataFormat format = wbOut.createDataFormat();
       HSSFCellStyle cellStyle = wbOut.createCellStyle();
       cellStyle.setDataFormat(format.getFormat("MM/dd/yyyy"));
       SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");
       Date ngayvaoDate = new Date();
       Date ngayraDate = new Date();
       Date giatriTuDate = new Date();
       Date giatriDenDate = new Date();
       try
       {
         ngayvaoDate = sf.parse(tmpData.getNgayVao());
         ngayraDate = sf.parse(tmpData.getNgayRa());
         giatriTuDate = sf.parse(tmpData.getGtTu());
         giatriDenDate = sf.parse(tmpData.getGtDen());
       }
       catch (Exception er)
       {
         System.out.println("Loi dinh dang ngay gio sai");
       }
       HSSFCell cell7 = row.createCell((short)7);
       cell7.setCellValue(ngayvaoDate);
       cell7.setCellStyle(cellStyle);

       HSSFCell cell8 = row.createCell((short)8);
       cell8.setCellValue(ngayraDate);
       cell8.setCellStyle(cellStyle);

       row.createCell((short)9).setCellValue(tmpData.getNgayDtr().intValue());
       row.createCell((short)10).setCellValue(tmpData.getTienXetnghiem().doubleValue());
       row.createCell((short)11).setCellValue(tmpData.getTienCdha().doubleValue());
       row.createCell((short)12).setCellValue(tmpData.getTienThuoc().doubleValue());
       row.createCell((short)13).setCellValue(tmpData.getTienMau().doubleValue());
       row.createCell((short)14).setCellValue(tmpData.getTienPttt().doubleValue());
       row.createCell((short)15).setCellValue(tmpData.getTienVtytth().doubleValue());
       row.createCell((short)16).setCellValue(tmpData.getTienDvktc().doubleValue());
       row.createCell((short)17).setCellValue(tmpData.getTienKtg().doubleValue());
       row.createCell((short)18).setCellValue(tmpData.getTienKham().doubleValue());
       row.createCell((short)19).setCellValue(tmpData.getTienVanchuyen().doubleValue());
       row.createCell((short)20).setCellValue(tmpData.getTongChi().doubleValue());
       row.createCell((short)21).setCellValue(tmpData.getTienBntra().doubleValue());
       row.createCell((short)22).setCellValue(tmpData.getTienBhxh().doubleValue());
       row.createCell((short)23).setCellValue(tmpData.getTienNgoaids().doubleValue());
       row.createCell((short)24).setCellValue(tmpData.getLydoVv().intValue());
       String strBenhKhac = tmpData.getBenhKhac();
       if (strBenhKhac != null) {
         strBenhKhac = cvtUnicodeToTcvn3.convert(strBenhKhac);
       }
       row.createCell((short)25).setCellValue(strBenhKhac);
       String noiKCB = IConstantsRes.MASO_CO_SO_KCB;

       noiKCB = noiKCB.replace(".", "");
       row.createCell((short)26).setCellValue(noiKCB);
       row.createCell((short)27).setCellValue(tmpData.getThangQt().intValue());
       row.createCell((short)28).setCellValue(tmpData.getNamQt().intValue());

       HSSFCell cell29 = row.createCell((short)29);
       cell29.setCellValue(giatriTuDate);
       cell29.setCellStyle(cellStyle);

       HSSFCell cell30 = row.createCell((short)30);
       cell30.setCellValue(giatriDenDate);
       cell30.setCellStyle(cellStyle);

       String strDiachi = tmpData.getDiaChi();
       if (strDiachi != null)
       {
         strDiachi = strDiachi.toLowerCase();
         strDiachi = printCapitalized(strDiachi);
         strDiachi = cvtUnicodeToTcvn3.convertCAP(strDiachi);
       }
       row.createCell((short)31).setCellValue(strDiachi);
       row.createCell((short)32).setCellValue(tmpData.getGiamDinh());
       row.createCell((short)33).setCellValue(tmpData.getXuatToan());
       row.createCell((short)34).setCellValue(tmpData.getLydoXuattoan());
       row.createCell((short)35).setCellValue(tmpData.getDaTuyen().intValue());
       row.createCell((short)36).setCellValue(tmpData.getVuotTran().intValue());
       row.createCell((short)37).setCellValue(tmpData.getLoaiKcb());
       row.createCell((short)38).setCellValue(tmpData.getNoiThanhtoan());

       String sophieu = "";
       if ((tmpData.getSoPhieu() != null) && (!tmpData.getSoPhieu().equals(""))) {
         sophieu = tmpData.getSoPhieu().substring(4);
       }
       row.createCell((short)39).setCellValue(sophieu);
       if (tmpData.getMaKhoa() == null) {
         row.createCell((short)40).setCellValue(0.0D);
       } else {
         row.createCell((short)40).setCellValue(tmpData.getMaKhoa().intValue());
       }
       row.createCell((short)41).setCellValue(tmpData.getTuyen());
       row.createCell((short)42).setCellValue(tmpData.getTiepDon());
       index++;
     }
     this.indexComm = index;
     log.info("Thoat Method exportDataNoiTru");
   }

   private String printCapitalized(String str)
   {
     char prevCh = '.';
     StringBuffer result = new StringBuffer();
     for (int i = 0; i < str.length(); i++)
     {
       char ch = str.charAt(i);
       if ((Character.isLetter(ch)) && (!Character.isLetter(prevCh))) {
         result.append(Character.toUpperCase(ch));
       } else {
         result.append(ch);
       }
       prevCh = ch;
     }
     return result.toString();
   }

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }

   public void nhaplai()
     throws Exception
   {
     ResetForm();
   }

   private void ResetForm()
   {
     log.info("Begining ResetForm(): ");
     emtyData();
     log.info("End ResetForm(): ");
   }

   public String getThoigian_thang()
   {
     return this.thoigian_thang;
   }

   public void setThoigian_thang(String thoigian_thang)
   {
     this.thoigian_thang = thoigian_thang;
   }

   public String getThoigian_nam()
   {
     return this.thoigian_nam;
   }

   public void setThoigian_nam(String thoigian_nam)
   {
     this.thoigian_nam = thoigian_nam;
   }

   public String getTungay()
   {
     return this.tungay;
   }

   public void setTungay(String tungay)
   {
     this.tungay = tungay;
   }

   public String getDenngay()
   {
     return this.denngay;
   }

   public void setDenngay(String denngay)
   {
     this.denngay = denngay;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setChonloaibc(String chonloaibc)
   {
     this.chonloaibc = chonloaibc;
   }

   public String getChonloaibc()
   {
     return this.chonloaibc;
   }

   public void setFileExport(String fileExport)
   {
     this.fileExport = fileExport;
   }

   public String getFileExport()
   {
     return this.fileExport;
   }

   public void setReportFinish(boolean reportFinish)
   {
     this.reportFinish = reportFinish;
   }

   public boolean isReportFinish()
   {
     return this.reportFinish;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.B3342_XuatDuLieuBHYT

 * JD-Core Version:    0.7.0.1

 */