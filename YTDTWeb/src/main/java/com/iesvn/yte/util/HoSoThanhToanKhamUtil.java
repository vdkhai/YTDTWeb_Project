 package com.iesvn.yte.util;

 import com.iesvn.yte.dieutri.delegate.ClsKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmMqlBhytDelegate;
 import com.iesvn.yte.dieutri.delegate.HoanUngKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.HsThtoankDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.MienGiamDelegate;
 import com.iesvn.yte.dieutri.delegate.TamUngKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocDongYNgoaiTruDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmCls;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmKhoiBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiMien;
 import com.iesvn.yte.dieutri.entity.DtDmMqlBhyt;
 import com.iesvn.yte.dieutri.entity.HsThtoank;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.MienGiam;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.ThuocDongYNgoaiTru;
 import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmThuoc;
 import java.text.DecimalFormat;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.List;
 import java.util.StringTokenizer;
 import org.apache.log4j.Logger;

 public class HoSoThanhToanKhamUtil
 {
   private static Logger log = Logger.getLogger(HoSoThanhToanKhamUtil.class);
   public Double miengiam = new Double(0.0D);
   public Double thatthu = new Double(0.0D);
   public int perbhytchi = 0;
   public Double bhytchi = new Double(0.0D);
   public Double thanhtien1 = new Double(0.0D);
   public int perbntra = 0;
   public Double bntra = new Double(0.0D);
   public TiepDon tiepDon;
   public Double DaThanhToan = new Double(0.0D);
   public Double mienTE = new Double(0.0D);
   public Double khongThu = new Double(0.0D);
   Double thuocTrongDM = new Double(0.0D);
   Double thuocNDM = new Double(0.0D);
   Double vTTHTrongDM = new Double(0.0D);
   Double vTTHNDM = new Double(0.0D);
   Double cLSMauTrongDM = new Double(0.0D);
   Double clsMauNDM = new Double(0.0D);
   Double tienCongKham = new Double(0.0D);
   Double chanDoanHinhAnh = new Double(0.0D);
   Double dichVuKTThongThuong = new Double(0.0D);
   Double dichVuKTC = new Double(0.0D);
   Double chiPhiVC = new Double(0.0D);
   Double xetNghiemTDCN = new Double(0.0D);
   Double dichTruyen = new Double(0.0D);
   Double cLSPhongTrongDM = new Double(0.0D);
   Double clsPhongNDM = new Double(0.0D);
   Double clsNDM = new Double(0.0D);
   Double clsTrongDM = new Double(0.0D);
   Double chiphiPT = new Double(0.0D);
   Double chiphiTT = new Double(0.0D);
   Double ung = new Double(0.0D);
   Double tra = new Double(0.0D);
   Double soDu = new Double(0.0D);
   public double minktc = 0.0D;
   public double maxktc = 0.0D;
   public int tylebhyt = 0;
   public int tylebhyt2 = 0;
   public double tyleminktc = 0.0D;
   public double tylemaxktc = 0.0D;
   public int tyleKTC = 0;
   public double gioiHanTyLe = 0.0D;
   DtDmKhoiBhyt khoiBHYT = null;
   private double tongChiPhiCLSBhTra = 0.0D;
   private double tongChiPhiCLSBnTra = 0.0D;
   private double tongTienThuoc = 0.0D;
   private double tongChiPhiVCBhTra = 0.0D;
   private double tongChiPhiVCBnTra = 0.0D;
   private double tongChiPhiKTCBhTra = 0.0D;
   private double tongChiPhiKTCBnTra = 0.0D;
   private double tongChiPhiThuocBhTra = 0.0D;
   private double tongChiPhiThuocBnTra = 0.0D;
   private boolean bDoituongUutien = false;
   private boolean bVuottuyen = false;
   private double tongMienGiam = 0.0D;
   private double tongChiPhiCLSBhTra_CapCuu = 0.0D;
   private double tongChiPhiVCBhTra_CapCuu = 0.0D;
   private double tongChiPhiKTCBhTra_CapCuu = 0.0D;
   private double tongChiPhiThuocBhTra_CapCuu = 0.0D;
   public int tylebhyt_CapCuu = 0;
   public int tylebhyt2_CapCuu = 0;
   private boolean bCapCuu = false;
   public double chiphiNguonKhacTra = 0.0D;
   Short tuyen = null;
   private HsThtoank hosoTTK;

   private void reset()
   {
     this.thatthu = new Double(0.0D);
     this.perbhytchi = 0;
     this.bhytchi = new Double(0.0D);
     this.thanhtien1 = new Double(0.0D);
     this.perbntra = 0;
     this.bntra = new Double(0.0D);
     this.khongThu = new Double(0.0D);

     this.mienTE = new Double(0.0D);
   }

   private boolean checkDTUuTien(String maDT)
   {
     boolean dtUutien = false;
     String dsUuTien = IConstantsRes.DANH_SACH_KHOI_BHYT_KHAC_TUYEN_VUOT_TUYEN_KO_CAN_GIAY_CHUYEN_VIEN;
     if ((dsUuTien != null) && (!dsUuTien.equals("")))
     {
       StringTokenizer sTk = new StringTokenizer(dsUuTien, ",");
       ArrayList<String> arrayDS = new ArrayList();
       while (sTk.hasMoreTokens())
       {
         String khoi = sTk.nextToken();
         arrayDS.add(khoi);
       }
       if (arrayDS.contains(maDT)) {
         dtUutien = true;
       }
     }
     return dtUutien;
   }

   private boolean checkDTDuocNguonKhacTraPhi(String maDT)
   {
     log.info("Begin checkDTDuocNguonKhacTraPhi, maDT = " + maDT);
     boolean duocNguonKhacTraPhi = false;
     if ((IConstantsRes.NGUON_KHAC_TRA_CHI_PHI != null) && (IConstantsRes.NGUON_KHAC_TRA_CHI_PHI.equalsIgnoreCase("YES")))
     {
       String dsNguonKhacTraPhi = IConstantsRes.DOI_TUONG_DUOC_NGUON_KHAC_TRA_PHI;
       if ((dsNguonKhacTraPhi != null) && (!dsNguonKhacTraPhi.equals("")))
       {
         StringTokenizer sTk = new StringTokenizer(dsNguonKhacTraPhi, ",");
         ArrayList<String> arrayDS = new ArrayList();
         while (sTk.hasMoreTokens())
         {
           String khoi = sTk.nextToken();
           arrayDS.add(khoi);
         }
         if (arrayDS.contains(maDT)) {
           duocNguonKhacTraPhi = true;
         }
       }
     }
     log.info("End checkDTDuocNguonKhacTraPhi, duocNguonKhacTraPhi = " + duocNguonKhacTraPhi);
     return duocNguonKhacTraPhi;
   }

   public HoSoThanhToanKhamUtil(TiepDon tiepDon)
   {
     this.tiepDon = tiepDon;
     this.khoiBHYT = tiepDon.getKhoibhytMa();
     if (this.khoiBHYT != null)
     {
       if (this.khoiBHYT.getDtdmkhoibhytMinKTC() != null) {
         this.minktc = this.khoiBHYT.getDtdmkhoibhytMinKTC().doubleValue();
       }
       if (this.khoiBHYT.getDtdmkhoibhytMaxKTC() != null) {
         this.maxktc = this.khoiBHYT.getDtdmkhoibhytMaxKTC().doubleValue();
       }
       if (this.khoiBHYT.getDtdmkhoibhytTylebhyt1() != null) {
         this.tylebhyt = this.khoiBHYT.getDtdmkhoibhytTylebhyt1().shortValue();
       }
       log.info("tylebhyt-----------------------" + this.tylebhyt);
       if (this.khoiBHYT.getDtdmkhoibhytTylebhyt2() != null) {
         this.tylebhyt2 = this.khoiBHYT.getDtdmkhoibhytTylebhyt2().shortValue();
       }
       if (this.khoiBHYT.getDtdmkhoibhytTLMinKTC() != null) {
         this.tyleminktc = this.khoiBHYT.getDtdmkhoibhytTLMinKTC().doubleValue();
       }
       if (this.khoiBHYT.getDtdmkhoibhytTLMaxKTC() != null) {
         this.tylemaxktc = this.khoiBHYT.getDtdmkhoibhytTLMaxKTC().doubleValue();
       }
       if (this.khoiBHYT.getDtdmkhoibhytTylektc() != null) {
         this.tyleKTC = this.khoiBHYT.getDtdmkhoibhytTylektc().shortValue();
       }
       if (this.khoiBHYT.getDtdmkhoibhytGioiHanTyLe() != null) {
         this.gioiHanTyLe = this.khoiBHYT.getDtdmkhoibhytGioiHanTyLe().doubleValue();
       }
       String strSotheBHYT = tiepDon.getTiepdonSothebh() == null ? "" : tiepDon.getTiepdonSothebh();
       if (strSotheBHYT.trim().length() > 3)
       {
         String strMaQuyenloi = strSotheBHYT.substring(2, 3);
         DtDmMqlBhytDelegate maQLDel = DtDmMqlBhytDelegate.getInstance();
         DtDmMqlBhyt maQL = maQLDel.findByMa(strMaQuyenloi);
         if (maQL != null)
         {
           this.minktc = (maQL.getDtdmmqlbhytMinktc() == null ? 0.0D : maQL.getDtdmmqlbhytMinktc().doubleValue());
           this.maxktc = (maQL.getDtdmmqlbhytMaxktc() == null ? 0.0D : maQL.getDtdmmqlbhytMaxktc().doubleValue());
           this.tylebhyt = (maQL.getDtdmmqlbhytTylebhyt1() == null ? 0 : maQL.getDtdmmqlbhytTylebhyt1().shortValue());
           this.tylebhyt2 = (maQL.getDtdmmqlbhytTylebhyt2() == null ? 0 : maQL.getDtdmmqlbhytTylebhyt2().shortValue());
           this.tyleminktc = (maQL.getDtdmmqlbhytTlminktc() == null ? 0.0D : maQL.getDtdmmqlbhytTlminktc().doubleValue());
           this.tylemaxktc = (maQL.getDtdmmqlbhytTlmaxktc() == null ? 0.0D : maQL.getDtdmmqlbhytTlmaxktc().doubleValue());
           this.tyleKTC = (maQL.getDtdmmqlbhytTylektc() == null ? 0 : maQL.getDtdmmqlbhytTylektc().shortValue());
           this.gioiHanTyLe = (maQL.getDtdmmqlbhytGhtyle() == null ? 0.0D : maQL.getDtdmmqlbhytGhtyle().doubleValue());
         }
       }
       if ("BH".equals(tiepDon.getDoituongMa(true).getDmdoituongMa()))
       {
         this.tuyen = tiepDon.getTiepdonTuyen();
         log.info("---tuyen():" + this.tuyen);


         Integer tuoi = tiepDon.getBenhnhanMa(true).getBenhnhanTuoi();
         if (tuoi == null) {
           tuoi = Integer.valueOf(0);
         }
         Short donvituoi = tiepDon.getBenhnhanMa(true).getBenhnhanDonvituoi();
         if (donvituoi == null) {
           donvituoi = Short.valueOf((short)0);
         }
         String tuoikhongxettuyen = IConstantsRes.TUOI_KHONG_XET_TUYEN;
         int iTuoiKoXetTuyen = 200;
         if ((tuoikhongxettuyen != null) && (!tuoikhongxettuyen.equals(""))) {
           iTuoiKoXetTuyen = Integer.parseInt(tuoikhongxettuyen);
         }
         if ((tuoi.intValue() >= iTuoiKoXetTuyen) && (donvituoi.shortValue() == 1))
         {
           this.bDoituongUutien = false;
           if ((this.tuyen != null) && ((this.tuyen.shortValue() == 1) || (this.tuyen.shortValue() == 2))) {
             this.bDoituongUutien = true;
           }
         }
         else if ((this.tuyen != null) && (this.tuyen.shortValue() == 3))
         {
           this.bDoituongUutien = false;
         }
         else
         {
           this.bDoituongUutien = checkDTUuTien(this.khoiBHYT.getDtdmkhoibhytMa());
         }
         log.info("bDoituongUutien = " + this.bDoituongUutien + ", tuyen = " + this.tuyen);
         if (!this.bDoituongUutien) {
           if ((this.tuyen != null) && ((this.tuyen.shortValue() == 2) || (this.tuyen.shortValue() == 3)))
           {
             if ((tiepDon.getTiepdonCoGiayGioiThieu() == null) || ((tiepDon.getTiepdonCoGiayGioiThieu() != null) && (!tiepDon.getTiepdonCoGiayGioiThieu().booleanValue())))
             {
               this.bVuottuyen = true;
               this.tylebhyt = 50;
               this.tylebhyt2 = 50;
               if ((IConstantsRes.TYLE_BH_VUOT_TUYEN.trim().length() > 0) && (IConstantsRes.TYLE_BH2_VUOT_TUYEN.trim().length() > 0)) {
                 try
                 {
                   this.tylebhyt = Integer.parseInt(IConstantsRes.TYLE_BH_VUOT_TUYEN);
                   this.tylebhyt2 = Integer.parseInt(IConstantsRes.TYLE_BH2_VUOT_TUYEN);
                 }
                 catch (Exception ex)
                 {
                   this.tylebhyt = 50;
                   this.tylebhyt2 = 50;
                 }
               }
             }
             log.info("tyle vuot tuyen = " + this.tylebhyt);
           }
         }
       }
     }
     else
     {
       this.khoiBHYT = new DtDmKhoiBhyt();
     }
     Date ngayTiepDon_tmp = tiepDon.getTiepdonNgaygio();
     if (ngayTiepDon_tmp == null) {
       return;
     }
     this.ngayTiepDon = Calendar.getInstance();
     this.ngayTiepDon.setTime(ngayTiepDon_tmp);
     this.ngayTiepDon.set(11, 0);
     this.ngayTiepDon.set(12, 0);
     this.ngayTiepDon.set(13, 0);



     Date ngayTiepDon3_tmp = tiepDon.getTiepdonGiatri3();
     if (ngayTiepDon3_tmp != null)
     {
       this.ngayTiepDon3 = Calendar.getInstance();
       this.ngayTiepDon3.setTime(ngayTiepDon3_tmp);
     }
     Date ngayTiepDon4_tmp = tiepDon.getTiepdonGiatri4();
     if (ngayTiepDon4_tmp != null)
     {
       this.ngayTiepDon4 = Calendar.getInstance();
       this.ngayTiepDon4.setTime(ngayTiepDon4_tmp);
     }
   }

   Calendar ngayTiepDon = null;
   Calendar ngayTiepDon3 = null;
   Calendar ngayTiepDon4 = null;

   private void tinhTamUngKham()
   {
     TamUngKhamDelegate tamUngKham = TamUngKhamDelegate.getInstance();
     this.ung = tamUngKham.getTongTamUng(this.tiepDon.getTiepdonMa());
   }

   private void tinhHoanUngKham()
   {
     HoanUngKhamDelegate hoanUngKham = HoanUngKhamDelegate.getInstance();
     this.tra = hoanUngKham.getTongHoanUng(this.tiepDon.getTiepdonMa());
   }

   private List<ClsKham> listCtkq_temp = null;
   private List<ThuocPhongKham> listCtTPK_temp = null;

   public Double getClsNDM()
   {
     return this.clsNDM;
   }

   public void setClsNDM(Double clsNDM)
   {
     this.clsNDM = clsNDM;
   }

   public Double getClsTrongDM()
   {
     return this.clsTrongDM;
   }

   public void setClsTrongDM(Double clsTrongDM)
   {
     this.clsTrongDM = clsTrongDM;
   }

   public List<ThuocPhongKham> getListCtTPK_temp()
   {
     return this.listCtTPK_temp;
   }

   public void setListCtTPK_temp(List<ThuocPhongKham> listCtTPK_temp)
   {
     this.listCtTPK_temp = listCtTPK_temp;
   }

   public List<ClsKham> getListCtkq_temp()
   {
     return this.listCtkq_temp;
   }

   public void setListCtkq_temp(List<ClsKham> listCtkq_temp)
   {
     this.listCtkq_temp = listCtkq_temp;
   }

   private List<ClsKham> capnhatDongiattCls(List<ClsKham> listClsKham)
   {
     if (listClsKham.size() > 0)
     {
       int solan = 0;
       for (int i = 0; i < listClsKham.size(); i++) {
         if ((((ClsKham)listClsKham.get(i)).getClskhamDongiatt() == 0) && (((ClsKham)listClsKham.get(i)).getClskhamThanhtien() == 0))
         {
           ((ClsKham)listClsKham.get(i)).setClskhamDongiatt(roundDoubleNumber(((ClsKham)listClsKham.get(i)).getClskhamDongia(), IConstantsRes.PATTERN_LAM_TRON_DON_GIA).intValue());

           solan = (((ClsKham)listClsKham.get(i)).getClskhamLan() == null ? 0 : ((ClsKham)listClsKham.get(i)).getClskhamLan().intValue()) - (((ClsKham)listClsKham.get(i)).getClskhamTra() == null ? 0 : ((ClsKham)listClsKham.get(i)).getClskhamTra().intValue());
           ((ClsKham)listClsKham.get(i)).setClskhamThanhtien(roundDoubleNumber(new Double(((ClsKham)listClsKham.get(i)).getClskhamDongiatt() * solan), IConstantsRes.PATTERN_LAM_TRON_THANH_TIEN).intValue());
         }
       }
     }
     return listClsKham;
   }

   private List<ThuocPhongKham> capnhatDongiattTpk(List<ThuocPhongKham> listTpk)
   {
     if (listTpk.size() > 0)
     {
       Double thanhTien = null;
       for (int i = 0; i < listTpk.size(); i++)
       {
         ((ThuocPhongKham)listTpk.get(i)).setThuocphongkhamDongiatt(roundDoubleNumber(((ThuocPhongKham)listTpk.get(i)).getThuocphongkhamDongia(), IConstantsRes.PATTERN_LAM_TRON_DON_GIA));

         thanhTien = Double.valueOf((((ThuocPhongKham)listTpk.get(i)).getThuocphongkhamSoluong().doubleValue() - (((ThuocPhongKham)listTpk.get(i)).getThuocphongkhamTra() == null ? 0.0D : ((ThuocPhongKham)listTpk.get(i)).getThuocphongkhamTra().doubleValue())) * ((ThuocPhongKham)listTpk.get(i)).getThuocphongkhamDongiatt().doubleValue());

         ((ThuocPhongKham)listTpk.get(i)).setThuocphongkhamThanhtien(roundDoubleNumber(thanhTien, IConstantsRes.PATTERN_LAM_TRON_THANH_TIEN));
       }
     }
     return listTpk;
   }

   private List<ThuocDongYNgoaiTru> capnhatDongiattThuocDongYNgoaiTru(List<ThuocDongYNgoaiTru> listThuocDongY)
   {
     if (listThuocDongY.size() > 0)
     {
       Double thanhTien = null;
       for (int i = 0; i < listThuocDongY.size(); i++) {
         if ((((ThuocDongYNgoaiTru)listThuocDongY.get(i)).getThuocdongyDongiatt().doubleValue() == 0.0D) && (((ThuocDongYNgoaiTru)listThuocDongY.get(i)).getThuocdongyThanhtien().doubleValue() == 0.0D))
         {
           ((ThuocDongYNgoaiTru)listThuocDongY.get(i)).setThuocdongyDongiatt(roundDoubleNumber(new Double(((ThuocDongYNgoaiTru)listThuocDongY.get(i)).getThuocdongyDongia()), IConstantsRes.PATTERN_LAM_TRON_DON_GIA));

           thanhTien = Double.valueOf(((ThuocDongYNgoaiTru)listThuocDongY.get(i)).getThuocdongySoluong() * ((ThuocDongYNgoaiTru)listThuocDongY.get(i)).getThuocdongyDongiatt().doubleValue());

           ((ThuocDongYNgoaiTru)listThuocDongY.get(i)).setThuocdongyThanhtien(roundDoubleNumber(thanhTien, IConstantsRes.PATTERN_LAM_TRON_THANH_TIEN));
         }
       }
     }
     return listThuocDongY;
   }

   private Double roundDoubleNumber(Double dNum, String pattern)
   {
     if (dNum == null) {
       return new Double(0.0D);
     }
     DecimalFormat twoDigit = new DecimalFormat(pattern);
     return new Double(twoDigit.format(dNum.doubleValue()));
   }

   public void tinhToanChoHSTKKham(HsThtoank hsttk)
   {
     log.info("----- tinhToanChoHSTKKham -----");


     ClsKhamDelegate clsKhamDelegate = ClsKhamDelegate.getInstance();

     Calendar myCal = Calendar.getInstance();
     myCal.set(2011, 11, 15, 0, 0, 0);
     Date dateUpdate = myCal.getTime();

     this.listCtkq_temp = clsKhamDelegate.findByTiepdonma(this.tiepDon.getTiepdonMa());

     ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
     this.listCtTPK_temp = tpkDelegate.findByMaTiepDon(this.tiepDon.getTiepdonMa(), "1");


     List<ThuocPhongKham> listCtTPK_tempBHYT = tpkDelegate.findByMaTiepDon(this.tiepDon.getTiepdonMa(), "3");
     if (this.tiepDon.getTiepdonNgaygio().before(dateUpdate)) {}
     this.tongChiPhiCLSBhTra = 0.0D;
     this.tongChiPhiCLSBnTra = 0.0D;

     this.tongChiPhiVCBhTra = 0.0D;
     this.tongChiPhiVCBnTra = 0.0D;
     this.tongChiPhiKTCBhTra = 0.0D;
     this.tongChiPhiKTCBnTra = 0.0D;
     this.tongChiPhiThuocBhTra = 0.0D;
     this.tongChiPhiThuocBnTra = 0.0D;

     boolean mienphiVC = false;
     this.tongChiPhiCLSBhTra_CapCuu = 0.0D;
     this.tongChiPhiVCBhTra_CapCuu = 0.0D;
     this.tongChiPhiKTCBhTra_CapCuu = 0.0D;
     this.tongChiPhiThuocBhTra_CapCuu = 0.0D;
     this.chiphiNguonKhacTra = 0.0D;
     String maDoituong = this.tiepDon.getDoituongMa() == null ? "" : this.tiepDon.getDoituongMa().getDmdoituongMa();
     this.bCapCuu = false;
     if (maDoituong.equals("BH"))
     {
       List<ThamKham> listTk = ThamKhamDelegate.getInstance().findAllByMaTiepDon(this.tiepDon.getTiepdonMa());
       if (listTk != null) {
         try
         {
           for (ThamKham tk : listTk) {
             if (tk.getThamkhamBankham().getDtdmbankhamMa().startsWith("CC"))
             {
               this.bCapCuu = true;
               break;
             }
           }
         }
         catch (Exception ex)
         {
           ex.printStackTrace();
         }
       }
     }
     String strSotheBHYT = this.tiepDon.getTiepdonSothebh() == null ? "" : this.tiepDon.getTiepdonSothebh();
     log.info("So the BH = " + strSotheBHYT);
     if (strSotheBHYT.trim().length() > 3)
     {
       String strMaQuyenloi = strSotheBHYT.substring(2, 3);
       DtDmMqlBhytDelegate maQLDel = DtDmMqlBhytDelegate.getInstance();
       DtDmMqlBhyt maQL = maQLDel.findByMa(strMaQuyenloi);
       log.info("maQL = " + maQL);
       if (maQL != null)
       {
         this.minktc = (maQL.getDtdmmqlbhytMinktc() == null ? 0.0D : maQL.getDtdmmqlbhytMinktc().doubleValue());
         this.maxktc = (maQL.getDtdmmqlbhytMaxktc() == null ? 0.0D : maQL.getDtdmmqlbhytMaxktc().doubleValue());
         if ((!this.bVuottuyen) || (this.bCapCuu)) {
           this.tylebhyt = (maQL.getDtdmmqlbhytTylebhyt1() == null ? 0 : maQL.getDtdmmqlbhytTylebhyt1().shortValue());
         }
         this.tylebhyt2 = (maQL.getDtdmmqlbhytTylebhyt2() == null ? 0 : maQL.getDtdmmqlbhytTylebhyt2().shortValue());
         this.tyleminktc = (maQL.getDtdmmqlbhytTlminktc() == null ? 0.0D : maQL.getDtdmmqlbhytTlminktc().doubleValue());
         this.tylemaxktc = (maQL.getDtdmmqlbhytTlmaxktc() == null ? 0.0D : maQL.getDtdmmqlbhytTlmaxktc().doubleValue());
         this.tyleKTC = (maQL.getDtdmmqlbhytTylektc() == null ? 0 : maQL.getDtdmmqlbhytTylektc().shortValue());
         this.gioiHanTyLe = (maQL.getDtdmmqlbhytGhtyle() == null ? 0.0D : maQL.getDtdmmqlbhytGhtyle().doubleValue());
         mienphiVC = maQL.getDtdmmqlbhytVanchuyen() == null ? false : maQL.getDtdmmqlbhytVanchuyen().booleanValue();
       }
     }
     this.tylebhyt_CapCuu = this.tylebhyt;
     this.tylebhyt2_CapCuu = this.tylebhyt2;



     Date ngayBatDauBh = this.tiepDon.getTiepdonGiatri3();
     if (ngayBatDauBh == null) {
       ngayBatDauBh = this.tiepDon.getTiepdonGiatri1();
     }
     Date ngayHetHanBh = this.tiepDon.getTiepdonGiatri4();
     if (ngayHetHanBh == null) {
       ngayHetHanBh = this.tiepDon.getTiepdonGiatri2();
     }
     if (ngayBatDauBh != null) {
       ngayBatDauBh = Utils.removeHourFromDate(ngayBatDauBh);
     }
     if (ngayHetHanBh != null) {
       ngayHetHanBh = Utils.removeHourFromDate(ngayHetHanBh);
     }
     tinhChiPhiCLS(this.listCtkq_temp, maDoituong, mienphiVC, ngayBatDauBh, ngayHetHanBh, hsttk.getHsthtoankNgaygiott());

     tinhChiPhiThuoc(this.listCtTPK_temp, Utils.XU_TRI_THUOC_TAI_BAN_KHAM, maDoituong, ngayBatDauBh, ngayHetHanBh);


     tinhChiPhiThuoc(listCtTPK_tempBHYT, Utils.KE_TOA_QUAY_BENH_VIEN, maDoituong, ngayBatDauBh, ngayHetHanBh);


     ThuocDongYNgoaiTruDelegate thuocDYNTDel = ThuocDongYNgoaiTruDelegate.getInstance();
     List<ThuocDongYNgoaiTru> listThuocDYNgoaiTru = thuocDYNTDel.findByMaTiepDon(this.tiepDon.getTiepdonMa());
     if ((maDoituong.equals("BH")) && (IConstantsRes.DONG_Y_APPLY_ALL.equals("1"))) {
       if (listThuocDYNgoaiTru.size() > 0)
       {
         if (this.tiepDon.getTiepdonNgaygio().before(dateUpdate)) {
           listThuocDYNgoaiTru = capnhatDongiattThuocDongYNgoaiTru(listThuocDYNgoaiTru);
         }
         for (ThuocDongYNgoaiTru each : listThuocDYNgoaiTru)
         {
           Date ngayNhanThuocDY = Utils.removeHourFromDate(each.getThuocdongyNgaygiocn());
           if ((ngayBatDauBh != null) && (ngayHetHanBh != null) && (!ngayNhanThuocDY.before(ngayBatDauBh)) && (!ngayNhanThuocDY.after(ngayHetHanBh)))
           {
             if (this.bCapCuu) {
               this.tongChiPhiThuocBhTra_CapCuu += each.getThuocdongyThanhtien().doubleValue();
             } else {
               this.tongChiPhiThuocBhTra += each.getThuocdongyThanhtien().doubleValue();
             }
             this.thuocTrongDM = Double.valueOf(this.thuocTrongDM.doubleValue() + each.getThuocdongyThanhtien().doubleValue());
           }
         }
       }
     }
     double tongChiPhiThuocCLS = this.tongChiPhiCLSBhTra + this.tongChiPhiThuocBhTra;
     double tongChiPhiThuocCLS_CapCuu = this.tongChiPhiCLSBhTra_CapCuu + this.tongChiPhiThuocBhTra_CapCuu;
     double tyleBhApDung = 0.0D;
     double tyleBhApDung_CapCuu = 0.0D;

     tyleBhApDung = this.tylebhyt;
     tyleBhApDung_CapCuu = this.tylebhyt_CapCuu;
     if (((!this.bVuottuyen) || (this.bCapCuu)) && (tongChiPhiThuocCLS < this.gioiHanTyLe))
     {
       tyleBhApDung = this.tylebhyt2;
       tyleBhApDung_CapCuu = this.tylebhyt2_CapCuu;
     }
     double tongChiPhiThuocCLSBhTra = roundDoubleNumber(new Double(tongChiPhiThuocCLS * (tyleBhApDung / 100.0D)), IConstantsRes.PATTERN_LAM_TRON_THANH_TIEN).doubleValue();
     double tongChiPhiThuocCLSBhTra_CapCuu = roundDoubleNumber(new Double(tongChiPhiThuocCLS_CapCuu * (tyleBhApDung_CapCuu / 100.0D)), IConstantsRes.PATTERN_LAM_TRON_THANH_TIEN).doubleValue();

     double tongChiPhiThuocCLSBnTra = tongChiPhiThuocCLS - tongChiPhiThuocCLSBhTra;

     double tongChiPhiThuocCLSBnTra_CapCuu = tongChiPhiThuocCLS_CapCuu - tongChiPhiThuocCLSBhTra_CapCuu;
     tongChiPhiThuocCLSBnTra += tongChiPhiThuocCLSBnTra_CapCuu;
     if (tongChiPhiThuocCLSBnTra < 0.0D) {
       tongChiPhiThuocCLSBnTra = 0.0D;
     }
     if (("BH".equals(this.tiepDon.getDoituongMa(true).getDmdoituongMa())) && (this.tuyen != null) && (this.tuyen.shortValue() == 1) && (!this.bDoituongUutien)) {
       if (checkDTDuocNguonKhacTraPhi(this.khoiBHYT.getDtdmkhoibhytMa())) {
         this.chiphiNguonKhacTra = tongChiPhiThuocCLSBnTra;
       }
     }
     double tongChiPhiBnTra = tongChiPhiThuocCLSBnTra + this.tongChiPhiCLSBnTra + this.tongChiPhiVCBnTra + this.tongChiPhiKTCBnTra + this.tongChiPhiThuocBnTra;


     double tongChiPhiBhTra = tongChiPhiThuocCLSBhTra + tongChiPhiThuocCLSBhTra_CapCuu + this.tongChiPhiVCBhTra + this.tongChiPhiKTCBhTra + this.tongChiPhiVCBhTra_CapCuu + this.tongChiPhiKTCBhTra_CapCuu;


     capNhatThuocPhongKham(this.listCtTPK_temp, Utils.XU_TRI_THUOC_TAI_BAN_KHAM, maDoituong, ngayBatDauBh, ngayHetHanBh, tyleBhApDung, tyleBhApDung_CapCuu);

     capNhatThuocPhongKham(listCtTPK_tempBHYT, Utils.KE_TOA_QUAY_BENH_VIEN, maDoituong, ngayBatDauBh, ngayHetHanBh, tyleBhApDung, tyleBhApDung_CapCuu);



     capNhatClsKham(this.listCtkq_temp, maDoituong, ngayBatDauBh, ngayHetHanBh, tyleBhApDung, tyleBhApDung_CapCuu);

     this.listCtkq_temp = clsKhamDelegate.findByTiepdonma(this.tiepDon.getTiepdonMa());
     Double thanhtien;
     boolean bChiPhiCapCuu;
     if ((maDoituong.equals("BH")) && (IConstantsRes.DONG_Y_APPLY_ALL.equals("1")) && (listThuocDYNgoaiTru.size() > 0))
     {
       thanhtien = Double.valueOf(0.0D);
       bChiPhiCapCuu = false;
       for (ThuocDongYNgoaiTru each : listThuocDYNgoaiTru)
       {
         try
         {
           bChiPhiCapCuu = each.getThuocdongyThamkham().getThamkhamBankham().getDtdmbankhamMa().startsWith("CC");
         }
         catch (Exception ex)
         {
           log.info("ERROR : " + ex.toString());
           ex.printStackTrace();
           bChiPhiCapCuu = false;
         }
         Date ngayNhanThuocDY = Utils.removeHourFromDate(each.getThuocdongyNgaygiocn());
         thanhtien = Double.valueOf(each.getThuocdongySoluong() * each.getThuocdongyDongia());
         if ((ngayBatDauBh == null) || (ngayHetHanBh == null))
         {
           each.setThuocdongyTylebh(new Double(0.0D));
           each.setThuocdongyTienbntra(thanhtien);
         }
         else if ((ngayNhanThuocDY.before(ngayBatDauBh)) || (ngayNhanThuocDY.after(ngayHetHanBh)))
         {
           each.setThuocdongyTylebh(new Double(0.0D));
           each.setThuocdongyTienbntra(thanhtien);
         }
         else if (bChiPhiCapCuu)
         {
           each.setThuocdongyTylebh(Double.valueOf(tyleBhApDung_CapCuu));
           each.setThuocdongyTienbntra(Double.valueOf(thanhtien.doubleValue() - roundDoubleNumber(Double.valueOf(thanhtien.doubleValue() * (tyleBhApDung_CapCuu / 100.0D)), IConstantsRes.PATTERN_LAM_TRON_THANH_TIEN).doubleValue()));
         }
         else
         {
           each.setThuocdongyTylebh(Double.valueOf(tyleBhApDung));
           each.setThuocdongyTienbntra(Double.valueOf(thanhtien.doubleValue() - roundDoubleNumber(Double.valueOf(thanhtien.doubleValue() * (tyleBhApDung / 100.0D)), IConstantsRes.PATTERN_LAM_TRON_THANH_TIEN).doubleValue()));
         }
         thuocDYNTDel.edit(each);
       }
     }
     String maMienGiam = "";
     Date ngayBatDauMG = null;
     Date ngayKetThucMG = null;
     Hsba hsba = HsbaDelegate.getInstance().findByTiepDonMa(this.tiepDon.getTiepdonMa());
     double phantramMienGiam = 0.0D;
     if (hsba != null)
     {
       List<MienGiam> listMienGiam = MienGiamDelegate.getInstance().getBySoVaoVien(hsba.getHsbaSovaovien());
       if ((listMienGiam != null) &&
         (listMienGiam.size() > 0))
       {
         maMienGiam = ((MienGiam)listMienGiam.get(0)).getMiengiamLoaimien().getDtdmloaimienMa();

         ngayBatDauMG = ((MienGiam)listMienGiam.get(0)).getMiengiamNgayd();







         ngayBatDauMG = Utils.removeHourFromDate(ngayBatDauMG);

         ngayKetThucMG = ((MienGiam)listMienGiam.get(0)).getMiengiamNgayc();







         ngayKetThucMG = Utils.removeHourFromDate(ngayKetThucMG);
         phantramMienGiam = ((MienGiam)listMienGiam.get(0)).getMiengiamTyle() == null ? 0.0D : ((MienGiam)listMienGiam.get(0)).getMiengiamTyle().doubleValue();
         this.tongMienGiam += (((MienGiam)listMienGiam.get(0)).getMiengiamSotien() == null ? 0.0D : ((MienGiam)listMienGiam.get(0)).getMiengiamSotien().doubleValue());
       }
     }
     if (!maMienGiam.equals("3")) {
       if (maMienGiam.equals("2")) {
         this.tongMienGiam += tongChiPhiBnTra * phantramMienGiam / 100.0D;
       } else {
         this.tongMienGiam += tinhTongMienGiamCls(this.listCtkq_temp, maMienGiam, ngayBatDauMG, ngayKetThucMG) * phantramMienGiam / 100.0D;
       }
     }
     log.info("In HoSoThanhToanKhamUtil, thuocTrongDM = " + this.thuocTrongDM);
     hsttk.setHsthtoankThuoc(this.thuocTrongDM);
     hsttk.setHsthtoankThuocndm(this.thuocNDM);
     hsttk.setHsthtoankVtth(this.vTTHTrongDM);
     hsttk.setHsthtoankVtthndm(this.vTTHNDM);


     hsttk.setHsthtoankPhong(this.cLSPhongTrongDM);
     hsttk.setHsthtoankPhongndm(this.clsPhongNDM);

     log.info("In HoSoThanhToanKhamUtil, tongChiPhiBnTra = " + tongChiPhiBnTra + ", DaThanhToan = " + this.DaThanhToan);
     hsttk.setHsthtoankBntra(Double.valueOf(tongChiPhiBnTra));



     hsttk.setHsthtoankBhyt(Double.valueOf(tongChiPhiBhTra));
     log.info("In HoSoThanhToanKhamUtil, tongChiPhiBhTra = " + tongChiPhiBhTra);
     hsttk.setHsthtoankTongchi(Double.valueOf(tongChiPhiBnTra + tongChiPhiBhTra));


     hsttk.setHsthtoankXetgiam(Double.valueOf(this.tongMienGiam));
     hsttk.setHsthtoankTylebh(new Short("" + new Double(tyleBhApDung).intValue()));

     hsttk.setHsthtoankMiente(this.mienTE);


     log.info("In HoSoThanhToanKhamUtil, clsTrongDM = " + this.clsTrongDM);
     hsttk.setHsthtoankCls(this.clsTrongDM);
     log.info("In HoSoThanhToanKhamUtil, clsNDM = " + this.clsNDM);
     hsttk.setHsthtoankClsndm(this.clsNDM);


     hsttk.setHsthtoankMau(Double.valueOf(this.clsMauNDM.doubleValue() + this.cLSMauTrongDM.doubleValue()));
     log.info("In HoSoThanhToanKhamUtil, tienCongKham = " + this.tienCongKham);
     hsttk.setHsthtoankCongkham(this.tienCongKham);
     hsttk.setHsthtoankXntdcn(this.xetNghiemTDCN);
     hsttk.setHsthtoankCdha(this.chanDoanHinhAnh);

     hsttk.setHsthtoankDvktc(this.dichVuKTC);
     hsttk.setHsthtoankCpvc(this.chiPhiVC);

     log.info("chiphiPT = " + this.chiphiPT);
     hsttk.setHsthtoankPhauthuat(this.chiphiPT);
     log.info("chiphiTT = " + this.chiphiTT);
     hsttk.setHsthtoankDvkttt(this.chiphiTT);



     tinhTamUngKham();
     tinhHoanUngKham();

     log.info("ung:" + this.ung);
     log.info("tra:" + this.tra);

     hsttk.setHsthtoankTamung(this.ung);
     hsttk.setHsthtoankHoanung(this.tra);


     this.soDu = Double.valueOf(tongChiPhiBnTra - this.ung.doubleValue() + this.tra.doubleValue() - this.DaThanhToan.doubleValue());
     log.info("In HoSoThanhToanKhamUtil, soDu = " + this.soDu);
     hsttk.setHsthtoankThtoan(this.soDu);
     hsttk.setHsthtoankKhongthu(this.khongThu);

     hsttk.setHsthtoankNdm(Double.valueOf(this.thuocNDM.doubleValue() + this.vTTHNDM.doubleValue() + this.clsNDM.doubleValue()));
     hsttk.setHsthtoankDm(Double.valueOf(this.thuocTrongDM.doubleValue() + this.vTTHTrongDM.doubleValue() + this.clsTrongDM.doubleValue()));
     hsttk.setHsthtoankNguonkhactra(Double.valueOf(this.chiphiNguonKhacTra));



     log.info("Ma tiep don = " + this.tiepDon.getTiepdonMa());
     HsThtoankDelegate hsDel = HsThtoankDelegate.getInstance();
     HsThtoank hsTmp = hsDel.findBytiepdonMaLast(this.tiepDon.getTiepdonMa());

     this.hosoTTK = new HsThtoank();
     if (hsTmp != null)
     {
       if (hsTmp.getHsthtoankNgaygiott() == null)
       {
         log.info("Cap nhat ho so = " + hsTmp);
         hsTmp.setHsthtoankThuoc(hsttk.getHsthtoankThuoc());
         hsTmp.setHsthtoankThuocndm(hsttk.getHsthtoankThuocndm());
         hsTmp.setHsthtoankVtth(hsttk.getHsthtoankVtth());
         hsTmp.setHsthtoankVtthndm(hsttk.getHsthtoankVtthndm());
         hsTmp.setHsthtoankPhong(hsttk.getHsthtoankPhong());
         hsTmp.setHsthtoankPhongndm(hsttk.getHsthtoankPhongndm());
         hsTmp.setHsthtoankBntra(hsttk.getHsthtoankBntra());
         hsTmp.setHsthtoankBhyt(hsttk.getHsthtoankBhyt());
         hsTmp.setHsthtoankTongchi(hsttk.getHsthtoankTongchi());
         hsTmp.setHsthtoankXetgiam(hsttk.getHsthtoankXetgiam());
         hsTmp.setHsthtoankTylebh(hsttk.getHsthtoankTylebh());
         hsTmp.setHsthtoankMiente(hsttk.getHsthtoankMiente());
         hsTmp.setHsthtoankCls(hsttk.getHsthtoankCls());
         hsTmp.setHsthtoankClsndm(hsttk.getHsthtoankClsndm());
         hsTmp.setHsthtoankMau(hsttk.getHsthtoankMau());
         hsTmp.setHsthtoankCongkham(hsttk.getHsthtoankCongkham());
         hsTmp.setHsthtoankXntdcn(hsttk.getHsthtoankXntdcn());
         hsTmp.setHsthtoankCdha(hsttk.getHsthtoankCdha());
         hsTmp.setHsthtoankDvktc(hsttk.getHsthtoankDvktc());
         hsTmp.setHsthtoankCpvc(hsttk.getHsthtoankCpvc());
         hsTmp.setHsthtoankPhauthuat(hsttk.getHsthtoankPhauthuat());
         hsTmp.setHsthtoankDvkttt(hsttk.getHsthtoankDvkttt());
         hsTmp.setHsthtoankTamung(hsttk.getHsthtoankTamung());
         hsTmp.setHsthtoankHoanung(hsttk.getHsthtoankHoanung());
         hsTmp.setHsthtoankThtoan(hsttk.getHsthtoankThtoan());
         hsTmp.setHsthtoankKhongthu(hsttk.getHsthtoankKhongthu());
         hsTmp.setHsthtoankNdm(hsttk.getHsthtoankNdm());
         hsTmp.setHsthtoankDm(hsttk.getHsthtoankDm());
         hsTmp.setHsthtoankNgaygiott(hsttk.getHsthtoankNgaygiott());
         hsTmp.setHsthtoankDatt(hsttk.getHsthtoankDatt());
         hsTmp.setHsthtoankNguonkhactra(hsttk.getHsthtoankNguonkhactra());
         hsDel.edit(hsTmp);
       }
       else
       {
         log.info("Ho so da thanh toan khong cap nhat");
         this.hosoTTK = hsttk;
       }
     }
     else
     {
       log.info("Tao thong tin ho so mo ");
       hsttk.setTiepdonMa(this.tiepDon);
       if (hsttk.getHsthtoankMa() != null) {
         hsDel.edit(hsttk);
       } else {
         hsDel.create(hsttk);
       }
     }
   }

   private void tinhChiPhiCLS(List<ClsKham> clslist, String maDoituong, boolean mienphiVC, Date ngayBatDauBh, Date ngayHetHanBh, Date ngayTT)
   {
     log.info("Begin tinhChiPhiCLS(), ngayTT = " + ngayTT);
     boolean bChiPhiCapCuu = false;
     if ((clslist == null) || (clslist.size() == 0)) {
       return;
     }
     if (this.tiepDon == null) {
       return;
     }
     if ((clslist != null) && (clslist.size() > 0))
     {
       ClsKhamDelegate clsKhamDel = ClsKhamDelegate.getInstance();
       try
       {
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         Date ngayThucHienCLS = null;
         for (ClsKham clskham : clslist) {
           if ((clskham.getClskhamStatus() == null) || (!clskham.getClskhamStatus().equals("1")))
           {
             Short lanKham = clskham.getClskhamLan();
             if (lanKham == null) {
               lanKham = Short.valueOf((short)1);
             }
             clskham.setClskhamLan(lanKham);
             log.info("lanKham:" + lanKham);

             Short tra = clskham.getClskhamTra();
             if (tra == null) {
               tra = Short.valueOf((short)0);
             }
             if ((clskham.getClskhamDatt() != null) && (clskham.getClskhamDatt().booleanValue() == true))
             {
               this.tongChiPhiCLSBnTra += (clskham.getClskhamDongiabntra() == null ? 0.0D : clskham.getClskhamDongiabntra().doubleValue());

               this.DaThanhToan = Double.valueOf(this.DaThanhToan.doubleValue() + (clskham.getClskhamDongiabntra() == null ? 0.0D : clskham.getClskhamDongiabntra().doubleValue()));
               tinhChiPhiTheoMaCLS(clskham.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa(), clskham.getClskhamDongia() == null ? 0.0D : clskham.getClskhamDongia().doubleValue(), clskham.getClskhamNdm() != null ? clskham.getClskhamNdm().booleanValue() : true, lanKham.shortValue(), tra.shortValue());
               if ((clskham.getClskhamNdm() != null) && (clskham.getClskhamNdm().booleanValue() == true)) {
                 this.clsNDM = Double.valueOf(this.clsNDM.doubleValue() + clskham.getClskhamDongia().doubleValue() * (lanKham.shortValue() - tra.shortValue()));
               } else {
                 this.clsTrongDM = Double.valueOf(this.clsTrongDM.doubleValue() + clskham.getClskhamDongia().doubleValue() * (lanKham.shortValue() - tra.shortValue()));
               }
             }
             else
             {
               clskham.setClskhamNgaygiott(ngayTT);
               if (lanKham.shortValue() > tra.shortValue())
               {
                 try
                 {
                   bChiPhiCapCuu = clskham.getClskhamThamkham().getThamkhamBankham().getDtdmbankhamMa().startsWith("CC");
                 }
                 catch (Exception ex)
                 {
                   log.info("ERROR : " + ex.toString());
                   ex.printStackTrace();
                   bChiPhiCapCuu = false;
                 }
                 if ((clskham.getClskhamNdm() != null) && (clskham.getClskhamNdm().booleanValue() == true)) {
                   this.clsNDM = Double.valueOf(this.clsNDM.doubleValue() + clskham.getClskhamDongia().doubleValue() * (lanKham.shortValue() - tra.shortValue()));
                 } else {
                   this.clsTrongDM = Double.valueOf(this.clsTrongDM.doubleValue() + clskham.getClskhamDongia().doubleValue() * (lanKham.shortValue() - tra.shortValue()));
                 }
                 ngayThucHienCLS = clskham.getClskhamNgaygio() == null ? null : df.parse(df.format(clskham.getClskhamNgaygio()));
                 ngayThucHienCLS = Utils.removeHourFromDate(ngayThucHienCLS);
                 if ((clskham.getClskhamKtcao() != null) && (clskham.getClskhamKtcao().booleanValue() == true)) {
                   tinhChiPhiClsKTC(clsKhamDel, clskham, maDoituong, lanKham.shortValue(), tra.shortValue(), ngayBatDauBh, ngayHetHanBh, ngayThucHienCLS, bChiPhiCapCuu);
                 } else if ((clskham.getClskhamMaloai() != null) && (clskham.getClskhamMaloai().getDtdmclsMaso().intValue() == 12)) {
                   tinhChiPhiClsVanChuyen(clsKhamDel, clskham, maDoituong, lanKham.shortValue(), tra.shortValue(), mienphiVC, ngayBatDauBh, ngayHetHanBh, ngayThucHienCLS, bChiPhiCapCuu);
                 } else {
                   tinhChiPhiCLSThuong(clskham, maDoituong, lanKham.shortValue(), tra.shortValue(), ngayBatDauBh, ngayHetHanBh, ngayThucHienCLS, bChiPhiCapCuu);
                 }
                 tinhChiPhiTheoMaCLS(clskham.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa(), clskham.getClskhamDongia() == null ? 0.0D : clskham.getClskhamDongia().doubleValue(), clskham.getClskhamNdm() != null ? clskham.getClskhamNdm().booleanValue() : true, lanKham.shortValue(), tra.shortValue());
               }
             }
           }
         }
       }
       catch (Exception ex)
       {
         ex.printStackTrace();
       }
     }
   }

   private void tinhChiPhiCLSThuong(ClsKham clskham, String maDoituong, int lanKham, int tra, Date ngayBatDauBh, Date ngayHetHanBh, Date ngayThucHienCLS, boolean bChiPhiCapCuu)
   {
     if (maDoituong.equals("MP"))
     {
       this.tongChiPhiCLSBnTra += clskham.getClskhamDongia().doubleValue() * (lanKham - tra);
     }
     else if (maDoituong.equals("TP"))
     {
       this.tongChiPhiCLSBnTra += clskham.getClskhamDongia().doubleValue() * (lanKham - tra);
     }
     else if ((ngayBatDauBh == null) || (ngayHetHanBh == null))
     {
       this.tongChiPhiCLSBnTra += clskham.getClskhamDongia().doubleValue() * (lanKham - tra);
     }
     else if ((ngayThucHienCLS.before(ngayBatDauBh)) || (ngayThucHienCLS.after(ngayHetHanBh)))
     {
       this.tongChiPhiCLSBnTra += clskham.getClskhamDongia().doubleValue() * (lanKham - tra);
     }
     else if ((clskham.getClskhamNdm() != null) && (clskham.getClskhamNdm().booleanValue() == true))
     {
       this.tongChiPhiCLSBnTra += clskham.getClskhamDongia().doubleValue() * (lanKham - tra);
     }
     else if ((clskham.getClskhamYeucau() != null) && (clskham.getClskhamYeucau().booleanValue() == true))
     {
       this.tongChiPhiCLSBnTra += clskham.getClskhamPhandv().doubleValue() * (lanKham - tra);
       if (bChiPhiCapCuu) {
         this.tongChiPhiCLSBhTra_CapCuu += clskham.getClskhamDongiabh().doubleValue() * (lanKham - tra);
       } else {
         this.tongChiPhiCLSBhTra += clskham.getClskhamDongiabh().doubleValue() * (lanKham - tra);
       }
     }
     else if (bChiPhiCapCuu)
     {
       this.tongChiPhiCLSBhTra_CapCuu += clskham.getClskhamDongiabh().doubleValue() * (lanKham - tra);
     }
     else
     {
       this.tongChiPhiCLSBhTra += clskham.getClskhamDongia().doubleValue() * (lanKham - tra);
     }
   }

   private void tinhChiPhiClsVanChuyen(ClsKhamDelegate clsKhamDel, ClsKham clskham, String maDoituong, int lanKham, int tra, boolean mienphiVC, Date ngayBatDauBh, Date ngayHetHanBh, Date ngayThucHienCLS, boolean bChiPhiCapCuu)
   {
     clskham.setClskhamDongiabntra(Double.valueOf(clskham.getClskhamDongia().doubleValue() * (lanKham - tra)));
     if (maDoituong.equals("MP"))
     {
       this.tongChiPhiVCBnTra += clskham.getClskhamDongia().doubleValue() * (lanKham - tra);
     }
     else if (maDoituong.equals("TP"))
     {
       this.tongChiPhiVCBnTra += clskham.getClskhamDongia().doubleValue() * (lanKham - tra);
     }
     else if ((ngayBatDauBh == null) || (ngayHetHanBh == null))
     {
       this.tongChiPhiVCBnTra += clskham.getClskhamDongia().doubleValue() * (lanKham - tra);
     }
     else if ((ngayThucHienCLS.before(ngayBatDauBh)) || (ngayThucHienCLS.after(ngayHetHanBh)))
     {
       this.tongChiPhiVCBnTra += clskham.getClskhamDongia().doubleValue() * (lanKham - tra);
     }
     else if ((clskham.getClskhamNdm() != null) && (clskham.getClskhamNdm().booleanValue() == true))
     {
       this.tongChiPhiVCBnTra += clskham.getClskhamDongia().doubleValue() * (lanKham - tra);
     }
     else if ((this.bDoituongUutien) || (bChiPhiCapCuu))
     {
       if (mienphiVC)
       {
         if (bChiPhiCapCuu) {
           this.tongChiPhiVCBhTra_CapCuu += clskham.getClskhamDongia().doubleValue() * (lanKham - tra);
         } else {
           this.tongChiPhiVCBhTra += clskham.getClskhamDongia().doubleValue() * (lanKham - tra);
         }
         clskham.setClskhamDongiabntra(new Double(0.0D));
       }
       else
       {
         this.tongChiPhiVCBnTra += clskham.getClskhamDongia().doubleValue() * (lanKham - tra);
       }
     }
     else if (this.bVuottuyen)
     {
       this.tongChiPhiVCBnTra += clskham.getClskhamDongia().doubleValue() * (lanKham - tra);
     }
     else if (mienphiVC)
     {
       this.tongChiPhiVCBhTra += clskham.getClskhamDongia().doubleValue() * (lanKham - tra);

       clskham.setClskhamDongiabntra(new Double(0.0D));
     }
     else
     {
       this.tongChiPhiVCBnTra += clskham.getClskhamDongia().doubleValue() * (lanKham - tra);
     }
     clsKhamDel.edit(clskham);
   }

   private void tinhChiPhiClsKTC(ClsKhamDelegate clsKhamDel, ClsKham clskham, String maDoituong, int lanKham, int tra, Date ngayBatDauBh, Date ngayHetHanBh, Date ngayThucHienCLS, boolean bChiPhiCapCuu)
   {
     clskham.setClskhamDongiabntra(Double.valueOf(clskham.getClskhamDongia().doubleValue() * (lanKham - tra)));
     if (maDoituong.equals("MP"))
     {
       this.tongChiPhiKTCBnTra += clskham.getClskhamDongia().doubleValue() * (lanKham - tra);
     }
     else if (maDoituong.equals("TP"))
     {
       this.tongChiPhiKTCBnTra += clskham.getClskhamDongia().doubleValue() * (lanKham - tra);
     }
     else if ((ngayBatDauBh == null) || (ngayHetHanBh == null))
     {
       this.tongChiPhiKTCBnTra += clskham.getClskhamDongia().doubleValue() * (lanKham - tra);
     }
     else if ((ngayThucHienCLS.before(ngayBatDauBh)) || (ngayThucHienCLS.after(ngayHetHanBh)))
     {
       this.tongChiPhiKTCBnTra += clskham.getClskhamDongia().doubleValue() * (lanKham - tra);
     }
     else if ((clskham.getClskhamNdm() != null) && (clskham.getClskhamNdm().booleanValue() == true))
     {
       this.tongChiPhiKTCBnTra += clskham.getClskhamDongia().doubleValue() * (lanKham - tra);
     }
     else if ((clskham.getClskhamYeucau() != null) && (clskham.getClskhamYeucau().booleanValue() == true))
     {
       this.tongChiPhiKTCBnTra += clskham.getClskhamPhandv().doubleValue() * (lanKham - tra);
       double bnTraTmp = tinhTheoTyleKTC(clskham.getClskhamDongiabh() == null ? 0.0D : clskham.getClskhamDongiabh().doubleValue(), lanKham, tra, bChiPhiCapCuu);

       clskham.setClskhamDongiabntra(Double.valueOf(clskham.getClskhamPhandv().doubleValue() * (lanKham - tra) + bnTraTmp));
     }
     else
     {
       double bnTraTmp = tinhTheoTyleKTC(clskham.getClskhamDongiabh() == null ? 0.0D : clskham.getClskhamDongiabh().doubleValue(), lanKham, tra, bChiPhiCapCuu);

       clskham.setClskhamDongiabntra(Double.valueOf(bnTraTmp));
     }
     clsKhamDel.edit(clskham);
   }

   private double tinhTheoTyleKTC(double donGiaThucHienCLS, int lanKham, int tra, boolean bChiPhiCapCuu)
   {
     if ((this.bVuottuyen) && (!this.bDoituongUutien) && (!bChiPhiCapCuu))
     {
       this.tyleminktc = this.tylebhyt;
       this.tyleKTC = this.tylebhyt;
       this.tylemaxktc = 0.0D;
     }
     double giaBhTra = 0.0D;
     double giaBnTra = 0.0D;
     if (donGiaThucHienCLS < this.minktc)
     {
       giaBhTra = donGiaThucHienCLS * this.tyleminktc / 100.0D * (lanKham - tra);
       giaBnTra = donGiaThucHienCLS * (lanKham - tra) - giaBhTra;
     }
     else if ((donGiaThucHienCLS >= this.minktc) && (donGiaThucHienCLS <= this.maxktc))
     {
       giaBhTra = donGiaThucHienCLS * this.tyleKTC / 100.0D * (lanKham - tra);
       giaBnTra = donGiaThucHienCLS * (lanKham - tra) - giaBhTra;
     }
     else if (donGiaThucHienCLS > this.maxktc)
     {
       if ((this.bVuottuyen) && (!this.bDoituongUutien) && (!bChiPhiCapCuu)) {
         this.maxktc = this.minktc;
       }
       double trenMaxKTC = donGiaThucHienCLS - this.maxktc;
       double giaBhTraTrenMax = trenMaxKTC * this.tylemaxktc / 100.0D * (lanKham - tra);
       double giaBnTraTrenMax = trenMaxKTC * (lanKham - tra) - giaBhTraTrenMax;

       giaBhTra = this.maxktc * this.tyleKTC / 100.0D * (lanKham - tra);
       giaBnTra = this.maxktc * (lanKham - tra) - giaBhTra;

       giaBhTra += giaBhTraTrenMax;
       giaBnTra += giaBnTraTrenMax;
     }
     this.tongChiPhiKTCBnTra += giaBnTra;
     if (bChiPhiCapCuu) {
       this.tongChiPhiKTCBhTra_CapCuu += giaBhTra;
     } else {
       this.tongChiPhiKTCBhTra += giaBhTra;
     }
     return giaBnTra;
   }

   private void tinhChiPhiTheoMaCLS(String maCls, double dongia, boolean ngoaiDM, int lanKham, int tra)
   {
     log.info("tinhChiPhiTheoMaCLS(), maCls = " + maCls);
     if (maCls.equalsIgnoreCase("GI"))
     {
       if (ngoaiDM) {
         this.clsPhongNDM = Double.valueOf(this.clsPhongNDM.doubleValue() + dongia * (lanKham - tra));
       } else {
         this.cLSPhongTrongDM = Double.valueOf(this.cLSPhongTrongDM.doubleValue() + dongia * (lanKham - tra));
       }
     }
     else if (maCls.equalsIgnoreCase("MA"))
     {
       if (ngoaiDM) {
         this.clsMauNDM = Double.valueOf(this.clsMauNDM.doubleValue() + dongia * (lanKham - tra));
       } else {
         this.cLSMauTrongDM = Double.valueOf(this.cLSMauTrongDM.doubleValue() + dongia * (lanKham - tra));
       }
     }
     else if (maCls.equalsIgnoreCase("KH")) {
       this.tienCongKham = Double.valueOf(this.tienCongKham.doubleValue() + dongia * (lanKham - tra));
     } else if (maCls.equalsIgnoreCase("CD")) {
       this.chanDoanHinhAnh = Double.valueOf(this.chanDoanHinhAnh.doubleValue() + dongia * (lanKham - tra));
     } else if (maCls.equalsIgnoreCase("TT")) {
       this.chiphiTT = Double.valueOf(this.chiphiTT.doubleValue() + dongia * (lanKham - tra));
     } else if (maCls.equalsIgnoreCase("PT")) {
       this.chiphiPT = Double.valueOf(this.chiphiPT.doubleValue() + dongia * (lanKham - tra));
     } else if (maCls.equalsIgnoreCase("KT")) {
       this.dichVuKTC = Double.valueOf(this.dichVuKTC.doubleValue() + dongia * (lanKham - tra));
     } else if (maCls.equalsIgnoreCase("VC")) {
       this.chiPhiVC = Double.valueOf(this.chiPhiVC.doubleValue() + dongia * (lanKham - tra));
     } else if (maCls.equalsIgnoreCase("XN")) {
       this.xetNghiemTDCN = Double.valueOf(this.xetNghiemTDCN.doubleValue() + dongia * (lanKham - tra));
     }
   }

   public void capNhatClsKham(List<ClsKham> clslist, String maDoituong, Date ngayBatDauBh, Date ngayHetHanBh, double tyleBhApDung, double tyleBhApDung_CapCuu)
   {
     if (clslist.size() < 1) {
       return;
     }
     ClsKhamDelegate clsKhamDel = ClsKhamDelegate.getInstance();
     Short lanKham = Short.valueOf((short)0);
     Short tra = Short.valueOf((short)0);
     try
     {
         SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
         Date ngayThucHienCLS = null;

       boolean bChiPhiCapCuu = false;
       for (ClsKham clskham : clslist) {
         if (((clskham.getClskhamDatt() == null) || (clskham.getClskhamDatt().booleanValue() != true)) &&



           ((clskham.getClskhamKtcao() == null) || (clskham.getClskhamKtcao().booleanValue() != true)) && ((clskham.getClskhamMaloai() == null) || (clskham.getClskhamMaloai().getDtdmclsMaso().intValue() != 12)))
         {
           lanKham = clskham.getClskhamLan();
           if (lanKham == null) {
             lanKham = Short.valueOf((short)0);
           }
           clskham.setClskhamLan(lanKham);

           tra = clskham.getClskhamTra();
           if (tra == null) {
             tra = Short.valueOf((short)0);
           }
           ngayThucHienCLS = clskham.getClskhamNgaygio() == null ? null : df.parse(df.format(clskham.getClskhamNgaygio()));
           ngayThucHienCLS = Utils.removeHourFromDate(ngayThucHienCLS);
           double giaCLS = clskham.getClskhamDongia().doubleValue() * (lanKham.shortValue() - tra.shortValue());
           try
           {
             bChiPhiCapCuu = clskham.getClskhamThamkham().getThamkhamBankham().getDtdmbankhamMa().startsWith("CC");
           }
           catch (Exception ex)
           {
             log.info("ERROR : " + ex.toString());
             ex.printStackTrace();
             bChiPhiCapCuu = false;
           }
           if (maDoituong.equalsIgnoreCase("BH"))
           {
             if ((ngayBatDauBh == null) || (ngayHetHanBh == null))
             {
               clskham.setClskhamDongiabntra(Double.valueOf(giaCLS));
             }
             else if (((clskham.getClskhamNdm() != null) && (clskham.getClskhamNdm().booleanValue() == true)) || (ngayThucHienCLS.before(ngayBatDauBh)) || (ngayThucHienCLS.after(ngayHetHanBh)))
             {
               clskham.setClskhamDongiabntra(Double.valueOf(giaCLS));
             }
             else if ((clskham.getClskhamYeucau() != null) && (clskham.getClskhamYeucau().booleanValue() == true))
             {
               double giaCLSDv = clskham.getClskhamPhandv().doubleValue() * (lanKham.shortValue() - tra.shortValue());
               double giaCLSBh = clskham.getClskhamDongiabh().doubleValue() * (lanKham.shortValue() - tra.shortValue());
               if (bChiPhiCapCuu) {
                 giaCLS = giaCLSDv + (giaCLSBh - roundDoubleNumber(new Double(giaCLSBh * (tyleBhApDung_CapCuu / 100.0D)), IConstantsRes.PATTERN_LAM_TRON_THANH_TIEN).doubleValue());
               } else {
                 giaCLS = giaCLSDv + (giaCLSBh - roundDoubleNumber(new Double(giaCLSBh * (tyleBhApDung / 100.0D)), IConstantsRes.PATTERN_LAM_TRON_THANH_TIEN).doubleValue());
               }
               clskham.setClskhamDongiabntra(Double.valueOf(giaCLS));
             }
             else
             {
               clskham.setClskhamDongiabntra(Double.valueOf(giaCLS - roundDoubleNumber(new Double(giaCLS * (tyleBhApDung / 100.0D)), IConstantsRes.PATTERN_LAM_TRON_THANH_TIEN).doubleValue()));
             }
           }
           else {
             clskham.setClskhamDongiabntra(Double.valueOf(giaCLS));
           }
           clsKhamDel.edit(clskham);
         }
       }
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
     }
   }

   public void capNhatThuocPhongKham(List<ThuocPhongKham> tpklist, String loaiThuocPhongKham, String maDoituong, Date ngayBatDauBh, Date ngayHetHanBh, double tyleBhApDung, double tyleBhApDung_CapCuu)
   {
     if (tpklist.size() < 1) {
       return;
     }
     ThuocPhongKhamDelegate tpkDel = ThuocPhongKhamDelegate.getInstance();



     boolean bChiPhiCapCuu = false;
     for (ThuocPhongKham tpk : tpklist) {
       if ((tpk.getThuocphongkhamDatt() == null) || (tpk.getThuocphongkhamDatt().booleanValue() != true))
       {
         double giaThuoc = tpk.getThuocphongkhamThanhtien().doubleValue();
         Date ngayNhanThuoc = tpk.getThuocphongkhamNgaygio();
         try
         {
           bChiPhiCapCuu = tpk.getThuocphongkhamThamkham().getThamkhamBankham().getDtdmbankhamMa().startsWith("CC");
         }
         catch (Exception ex)
         {
           log.info("ERROR : " + ex.toString());
           ex.printStackTrace();
           bChiPhiCapCuu = false;
         }
         ngayNhanThuoc = Utils.removeHourFromDate(ngayNhanThuoc);
         if (Utils.XU_TRI_THUOC_TAI_BAN_KHAM.equals(loaiThuocPhongKham))
         {
           if (maDoituong.equalsIgnoreCase("BH"))
           {
             if ((ngayBatDauBh == null) || (ngayHetHanBh == null)) {
               tpk.setThuocphongkhamTienbntra(Double.valueOf(giaThuoc));
             } else if ((tpk.getThuocdongyNgoaiTru() != null) && (IConstantsRes.DONG_Y_APPLY_ALL.equals("1")) && (!ngayNhanThuoc.before(ngayBatDauBh)) && (!ngayNhanThuoc.after(ngayHetHanBh))) {
               tpk.setThuocphongkhamTienbntra(new Double(0.0D));
             } else if ((ngayNhanThuoc.before(ngayBatDauBh)) || (ngayNhanThuoc.after(ngayHetHanBh)) || ((tpk.getThuocphongkhamNdm() != null) && (tpk.getThuocphongkhamNdm().booleanValue() == true)) || ((tpk.getThuocphongkhamYeucau() != null) && (tpk.getThuocphongkhamYeucau().booleanValue() == true))) {
               tpk.setThuocphongkhamTienbntra(Double.valueOf(giaThuoc));
             } else if (bChiPhiCapCuu) {
               tpk.setThuocphongkhamTienbntra(Double.valueOf(giaThuoc - roundDoubleNumber(new Double(giaThuoc * (tyleBhApDung_CapCuu / 100.0D)), IConstantsRes.PATTERN_LAM_TRON_THANH_TIEN).doubleValue()));
             } else {
               tpk.setThuocphongkhamTienbntra(Double.valueOf(giaThuoc - roundDoubleNumber(new Double(giaThuoc * (tyleBhApDung / 100.0D)), IConstantsRes.PATTERN_LAM_TRON_THANH_TIEN).doubleValue()));
             }
           }
           else {
             tpk.setThuocphongkhamTienbntra(Double.valueOf(giaThuoc));
           }
         }
         else if (maDoituong.equalsIgnoreCase("BH"))
         {
           if ((ngayBatDauBh == null) || (ngayHetHanBh == null)) {
             tpk.setThuocphongkhamTienbntra(Double.valueOf(giaThuoc));
           } else if ((ngayNhanThuoc.before(ngayBatDauBh)) || (ngayNhanThuoc.after(ngayHetHanBh)) || ((tpk.getThuocphongkhamNdm() != null) && (tpk.getThuocphongkhamNdm().booleanValue() == true)) || ((tpk.getThuocphongkhamYeucau() != null) && (tpk.getThuocphongkhamYeucau().booleanValue() == true))) {
             tpk.setThuocphongkhamTienbntra(Double.valueOf(giaThuoc));
           } else if (bChiPhiCapCuu) {
             tpk.setThuocphongkhamTienbntra(Double.valueOf(giaThuoc - roundDoubleNumber(new Double(giaThuoc * (tyleBhApDung_CapCuu / 100.0D)), IConstantsRes.PATTERN_LAM_TRON_THANH_TIEN).doubleValue()));
           } else {
             tpk.setThuocphongkhamTienbntra(Double.valueOf(giaThuoc - roundDoubleNumber(new Double(giaThuoc * (tyleBhApDung / 100.0D)), IConstantsRes.PATTERN_LAM_TRON_THANH_TIEN).doubleValue()));
           }
         }
         else {
           tpk.setThuocphongkhamTienbntra(Double.valueOf(giaThuoc));
         }
         tpk.setThuocphongkhamTylebh(Double.valueOf(tyleBhApDung));
         tpkDel.edit(tpk);
       }
     }
   }

   private double tinhTongMienGiamCls(List<ClsKham> clslist, String maMienGiam, Date ngayBatDauMG, Date ngayKetThucMG)
   {
     if ((clslist.size() < 1) || (maMienGiam.trim().length() < 1) || (ngayBatDauMG == null) || (ngayKetThucMG == null)) {
       return 0.0D;
     }
     double mienGiamCls = 0.0D;
     SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
     try
     {
       for (ClsKham clskham : clslist) {
         if ((clskham.getClskhamDatt() == null) || (clskham.getClskhamDatt().booleanValue() != true))
         {
           Date ngayThHienCLS = df.parse(df.format(clskham.getClskhamNgaygiocn()));







           ngayThHienCLS = Utils.removeHourFromDate(ngayThHienCLS);
           String maCLS = clskham.getClskhamMahang().getDtdmclsbgMaloai().getDtdmclsMa();
           if (((ngayThHienCLS.after(ngayBatDauMG)) || (ngayThHienCLS.equals(ngayBatDauMG))) && ((ngayThHienCLS.before(ngayKetThucMG)) || (ngayThHienCLS.equals(ngayKetThucMG)))) {
             if (((maMienGiam.equalsIgnoreCase("02B")) && (!maCLS.equalsIgnoreCase("MA")) && (!maCLS.equalsIgnoreCase("GI"))) || ((maMienGiam.equalsIgnoreCase("1")) && (maCLS.equalsIgnoreCase("GI"))) || ((maMienGiam.equalsIgnoreCase("4")) && (maCLS.equalsIgnoreCase("MA"))) || ((maMienGiam.equalsIgnoreCase("6")) && (maCLS.equalsIgnoreCase("KT"))) || (maMienGiam.equalsIgnoreCase("ALL"))) {
               mienGiamCls += clskham.getClskhamDongiabntra().doubleValue();
             }
           }
         }
       }
     }
     catch (Exception e)
     {
       log.info("ERROR : " + e.toString());
       e.printStackTrace();
       mienGiamCls = 0.0D;
     }
     return mienGiamCls;
   }

   private void tinhChiPhiThuoc(List<ThuocPhongKham> listTpk, String loaiThuocPhongKham, String maDoituong, Date ngayBatDauBh, Date ngayHetHanBh)
   {
     if ((listTpk == null) || (listTpk.size() == 0)) {
       return;
     }
     boolean bChiPhiCapCuu = false;
     for (ThuocPhongKham tpk : listTpk)
     {
       Date ngayNhanThuoc = Utils.removeHourFromDate(tpk.getThuocphongkhamNgaygio());
       if ((!maDoituong.equalsIgnoreCase("BH")) || (!IConstantsRes.DONG_Y_APPLY_ALL.equals("1")) || (tpk.getThuocdongyNgoaiTru() == null) || (ngayBatDauBh == null) || (ngayHetHanBh == null) || (ngayNhanThuoc.before(ngayBatDauBh)) || (ngayNhanThuoc.after(ngayHetHanBh)))
       {
         double giaThuoc = tpk.getThuocphongkhamThanhtien().doubleValue();
         try
         {
           bChiPhiCapCuu = tpk.getThuocphongkhamThamkham().getThamkhamBankham().getDtdmbankhamMa().startsWith("CC");
         }
         catch (Exception ex)
         {
           log.info("ERROR : " + ex.toString());
           ex.printStackTrace();
           bChiPhiCapCuu = false;
         }
         if (Utils.XU_TRI_THUOC_TAI_BAN_KHAM.equals(loaiThuocPhongKham))
         {
           if ((tpk.getThuocphongkhamMaphieuh() != null) && (!tpk.getThuocphongkhamMaphieuh().equals(""))) {
             continue;
           }
           if ((tpk.getThuocphongkhamDatt() != null) && (tpk.getThuocphongkhamDatt().booleanValue() == true))
           {
             this.tongChiPhiThuocBnTra += (tpk.getThuocphongkhamTienbntra() == null ? 0.0D : tpk.getThuocphongkhamTienbntra().doubleValue());

             this.DaThanhToan = Double.valueOf(this.DaThanhToan.doubleValue() + (tpk.getThuocphongkhamTienbntra() == null ? 0.0D : tpk.getThuocphongkhamTienbntra().doubleValue()));
           }
           else if (((tpk.getThuocphongkhamNdm() != null) && (tpk.getThuocphongkhamNdm().booleanValue() == true)) || ((tpk.getThuocphongkhamYeucau() != null) && (tpk.getThuocphongkhamYeucau().booleanValue() == true)))
           {
             this.tongChiPhiThuocBnTra += giaThuoc;
           }
           else if ((tpk.getThuocphongkhamKhongthu() != null) && (tpk.getThuocphongkhamKhongthu().booleanValue() == true))
           {
             this.tongMienGiam += giaThuoc;
           }
           else if (maDoituong.equalsIgnoreCase("BH"))
           {
             if ((ngayBatDauBh == null) || (ngayHetHanBh == null)) {
               this.tongChiPhiThuocBnTra += giaThuoc;
             } else if ((ngayNhanThuoc.before(ngayBatDauBh)) || (ngayNhanThuoc.after(ngayHetHanBh))) {
               this.tongChiPhiThuocBnTra += giaThuoc;
             } else if (bChiPhiCapCuu) {
               this.tongChiPhiThuocBhTra_CapCuu += giaThuoc;
             } else {
               this.tongChiPhiThuocBhTra += giaThuoc;
             }
           }
           else if (maDoituong.equalsIgnoreCase("MP"))
           {
             if ((tpk.getThuocphongkhamMien() == null) || (tpk.getThuocphongkhamMien().booleanValue() != true)) {
               this.tongChiPhiThuocBnTra += giaThuoc;
             }
           }
           else
           {
             this.tongChiPhiThuocBnTra += giaThuoc;
           }
         }
         else if (((tpk.getThuocphongkhamNdm() != null) && (tpk.getThuocphongkhamNdm().booleanValue() == true)) || ((tpk.getThuocphongkhamYeucau() != null) && (tpk.getThuocphongkhamYeucau().booleanValue() == true)))
         {
           this.tongChiPhiThuocBnTra += giaThuoc;
         }
         else if ((tpk.getThuocphongkhamKhongthu() != null) && (tpk.getThuocphongkhamKhongthu().booleanValue() == true))
         {
           this.tongMienGiam += giaThuoc;
         }
         else if (maDoituong.equalsIgnoreCase("BH"))
         {
           if ((ngayBatDauBh == null) || (ngayHetHanBh == null)) {
             this.tongChiPhiThuocBnTra += giaThuoc;
           } else if ((ngayNhanThuoc.before(ngayBatDauBh)) || (ngayNhanThuoc.after(ngayHetHanBh))) {
             this.tongChiPhiThuocBnTra += giaThuoc;
           } else if (bChiPhiCapCuu) {
             this.tongChiPhiThuocBhTra_CapCuu += giaThuoc;
           } else {
             this.tongChiPhiThuocBhTra += giaThuoc;
           }
         }
         else if (maDoituong.equalsIgnoreCase("MP"))
         {
           if ((tpk.getThuocphongkhamMien() == null) || (tpk.getThuocphongkhamMien().booleanValue() != true)) {
             this.tongChiPhiThuocBnTra += giaThuoc;
           }
         }
         else
         {
           this.tongChiPhiThuocBnTra += giaThuoc;
         }
         if (((tpk.getThuocphongkhamNdm() != null) && (tpk.getThuocphongkhamNdm().booleanValue() == true)) || ((tpk.getThuocphongkhamYeucau() != null) && (tpk.getThuocphongkhamYeucau().booleanValue() == true)))
         {
           if ((tpk.getThuocphongkhamMathuoc() != null) && (tpk.getThuocphongkhamMathuoc().getDmthuocPlbhyt() != null) && (tpk.getThuocphongkhamMathuoc().getDmthuocPlbhyt().intValue() == 10)) {
             this.vTTHNDM = Double.valueOf(this.vTTHNDM.doubleValue() + giaThuoc);
           } else {
             this.thuocNDM = Double.valueOf(this.thuocNDM.doubleValue() + giaThuoc);
           }
         }
         else if ((tpk.getThuocphongkhamMathuoc() != null) && (tpk.getThuocphongkhamMathuoc().getDmthuocPlbhyt() != null) && (tpk.getThuocphongkhamMathuoc().getDmthuocPlbhyt().intValue() == 10)) {
           this.vTTHTrongDM = Double.valueOf(this.vTTHTrongDM.doubleValue() + giaThuoc);
         } else {
           this.thuocTrongDM = Double.valueOf(this.thuocTrongDM.doubleValue() + giaThuoc);
         }
       }
     }
   }

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }

   public Double getMiengiam()
   {
     return this.miengiam;
   }

   public void setMiengiam(Double miengiam)
   {
     this.miengiam = miengiam;
   }

   public Double getThatthu()
   {
     return this.thatthu;
   }

   public void setThatthu(Double thatthu)
   {
     this.thatthu = thatthu;
   }

   public int getPerbhytchi()
   {
     return this.perbhytchi;
   }

   public void setPerbhytchi(int perbhytchi)
   {
     this.perbhytchi = perbhytchi;
   }

   public Double getBhytchi()
   {
     return this.bhytchi;
   }

   public void setBhytchi(Double bhytchi)
   {
     this.bhytchi = bhytchi;
   }

   public Double getThanhtien1()
   {
     return this.thanhtien1;
   }

   public void setThanhtien1(Double thanhtien1)
   {
     this.thanhtien1 = thanhtien1;
   }

   public int getPerbntra()
   {
     return this.perbntra;
   }

   public void setPerbntra(int perbntra)
   {
     this.perbntra = perbntra;
   }

   public Double getBntra()
   {
     return this.bntra;
   }

   public void setBntra(Double bntra)
   {
     this.bntra = bntra;
   }

   public TiepDon getTiepDon()
   {
     return this.tiepDon;
   }

   public void setTiepDon(TiepDon tiepDon)
   {
     this.tiepDon = tiepDon;
   }

   public Double getThuocTrongDM()
   {
     return this.thuocTrongDM;
   }

   public void setThuocTrongDM(Double thuocTrongDM)
   {
     this.thuocTrongDM = thuocTrongDM;
   }

   public Double getThuocNDM()
   {
     return this.thuocNDM;
   }

   public void setThuocNDM(Double thuocNDM)
   {
     this.thuocNDM = thuocNDM;
   }

   public Double getVTTHTrongDM()
   {
     return this.vTTHTrongDM;
   }

   public void setVTTHTrongDM(Double trongDM)
   {
     this.vTTHTrongDM = trongDM;
   }

   public Double getVTTHNDM()
   {
     return this.vTTHNDM;
   }

   public void setVTTHNDM(Double vtthndm)
   {
     this.vTTHNDM = vtthndm;
   }

   public Double getCLSMauTrongDM()
   {
     return this.cLSMauTrongDM;
   }

   public void setCLSMauTrongDM(Double mauTrongDM)
   {
     this.cLSMauTrongDM = mauTrongDM;
   }

   public Double getClsMauNDM()
   {
     return this.clsMauNDM;
   }

   public void setClsMauNDM(Double clsMauNDM)
   {
     this.clsMauNDM = clsMauNDM;
   }

   public Double getUng()
   {
     return this.ung;
   }

   public void setUng(Double ung)
   {
     this.ung = ung;
   }

   public Double getTra()
   {
     return this.tra;
   }

   public void setTra(Double tra)
   {
     this.tra = tra;
   }

   public Double getSoDu()
   {
     return this.soDu;
   }

   public void setSoDu(Double soDu)
   {
     this.soDu = soDu;
   }

   public Double getKhongThu()
   {
     return this.khongThu;
   }

   public void setKhongThu(Double khongThu)
   {
     this.khongThu = khongThu;
   }

   public Double getCLSPhongTrongDM()
   {
     return this.cLSPhongTrongDM;
   }

   public void setCLSPhongTrongDM(Double phongTrongDM)
   {
     this.cLSPhongTrongDM = phongTrongDM;
   }

   public Double getClsPhongNDM()
   {
     return this.clsPhongNDM;
   }

   public void setClsPhongNDM(Double clsPhongNDM)
   {
     this.clsPhongNDM = clsPhongNDM;
   }

   public double getMinktc()
   {
     return this.minktc;
   }

   public void setMinktc(double minktc)
   {
     this.minktc = minktc;
   }

   public double getMaxktc()
   {
     return this.maxktc;
   }

   public void setMaxktc(double maxktc)
   {
     this.maxktc = maxktc;
   }

   public int getTylebhyt()
   {
     return this.tylebhyt;
   }

   public void setTylebhyt(int tylebhyt)
   {
     this.tylebhyt = tylebhyt;
   }

   public int getTylebhyt2()
   {
     return this.tylebhyt2;
   }

   public void setTylebhyt2(int tylebhyt2)
   {
     this.tylebhyt2 = tylebhyt2;
   }

   public double getTyleminktc()
   {
     return this.tyleminktc;
   }

   public void setTyleminktc(double tyleminktc)
   {
     this.tyleminktc = tyleminktc;
   }

   public double getTylemaxktc()
   {
     return this.tylemaxktc;
   }

   public void setTylemaxktc(double tylemaxktc)
   {
     this.tylemaxktc = tylemaxktc;
   }

   public int getTyleKTC()
   {
     return this.tyleKTC;
   }

   public void setTyleKTC(int tyleKTC)
   {
     this.tyleKTC = tyleKTC;
   }

   public double getGioiHanTyLe()
   {
     return this.gioiHanTyLe;
   }

   public void setGioiHanTyLe(double gioiHanTyLe)
   {
     this.gioiHanTyLe = gioiHanTyLe;
   }

   public DtDmKhoiBhyt getKhoiBHYT()
   {
     return this.khoiBHYT;
   }

   public void setKhoiBHYT(DtDmKhoiBhyt khoiBHYT)
   {
     this.khoiBHYT = khoiBHYT;
   }

   public Double getMienTE()
   {
     return this.mienTE;
   }

   public void setMienTE(Double mienTE)
   {
     this.mienTE = mienTE;
   }

   public boolean isbVuottuyen()
   {
     return this.bVuottuyen;
   }

   public void setbVuottuyen(boolean bVuottuyen)
   {
     this.bVuottuyen = bVuottuyen;
   }

   public double getTongChiPhiCLSBhTra()
   {
     return this.tongChiPhiCLSBhTra;
   }

   public void setTongChiPhiCLSBhTra(double tongChiPhiCLSBhTra)
   {
     this.tongChiPhiCLSBhTra = tongChiPhiCLSBhTra;
   }

   public double getTongChiPhiThuocBhTra()
   {
     return this.tongChiPhiThuocBhTra;
   }

   public void setTongChiPhiThuocBhTra(double tongChiPhiThuocBhTra)
   {
     this.tongChiPhiThuocBhTra = tongChiPhiThuocBhTra;
   }

   public double getTongChiPhiCLSBhTra_CapCuu()
   {
     return this.tongChiPhiCLSBhTra_CapCuu;
   }

   public void setTongChiPhiCLSBhTra_CapCuu(double tongChiPhiCLSBhTraCapCuu)
   {
     this.tongChiPhiCLSBhTra_CapCuu = tongChiPhiCLSBhTraCapCuu;
   }

   public double getTongChiPhiVCBhTra_CapCuu()
   {
     return this.tongChiPhiVCBhTra_CapCuu;
   }

   public void setTongChiPhiVCBhTra_CapCuu(double tongChiPhiVCBhTraCapCuu)
   {
     this.tongChiPhiVCBhTra_CapCuu = tongChiPhiVCBhTraCapCuu;
   }

   public double getTongChiPhiKTCBhTra_CapCuu()
   {
     return this.tongChiPhiKTCBhTra_CapCuu;
   }

   public void setTongChiPhiKTCBhTra_CapCuu(double tongChiPhiKTCBhTraCapCuu)
   {
     this.tongChiPhiKTCBhTra_CapCuu = tongChiPhiKTCBhTraCapCuu;
   }

   public double getTongChiPhiThuocBhTra_CapCuu()
   {
     return this.tongChiPhiThuocBhTra_CapCuu;
   }

   public void setTongChiPhiThuocBhTra_CapCuu(double tongChiPhiThuocBhTraCapCuu)
   {
     this.tongChiPhiThuocBhTra_CapCuu = tongChiPhiThuocBhTraCapCuu;
   }

   public HsThtoank getHosoTTK()
   {
     return this.hosoTTK;
   }

   public void setHosoTTK(HsThtoank hosoTTK)
   {
     this.hosoTTK = hosoTTK;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.util.HoSoThanhToanKhamUtil

 * JD-Core Version:    0.7.0.1

 */