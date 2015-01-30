 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.CtPhieuKhamDtNgoaiTruDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuKhamDtNgoaiTruDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.CtPhieuKhamDtNgoaiTru;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.PhieuKhamDtNgoaiTru;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.Destroy;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.CONVERSATION)
 @Name("B118_Thamkhamdieutringoaitru")
 @Synchronized(timeout=6000000L)
 public class ThamKhamDieuTriNgoaiTru
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   private static final long serialVersionUID = 10L;
   private int stt;
   private String sMaPhieuKhamDT = "";
   private String ngaySinh = "";
   private String gioi = "";
   private PhieuKhamDtNgoaiTru objPhieuKhamDTNgoaiTru = new PhieuKhamDtNgoaiTru();
   private String sShowDel = "";
   private String sShowPrint = "";
   private String ngay;
   private String chandoan;
   private String dieutri;
   private String bacSiMa;
   private Integer bacSiMaSo_hidden;
   @DataModel
   private List<CtPhieuKhamDtNgoaiTru> listCtPKDTNT;
   @DataModelSelection("listCtPKDTNT")
   private CtPhieuKhamDtNgoaiTru ctpkdtntSelection;
   private CtPhieuKhamDtNgoaiTruDelegate ctpkdtntDAO;
   private static Logger log = Logger.getLogger(ThamKhamAction.class);
   @In(required=false)
   @Out(required=false)
   private String maBanKhamOut;
   @In(required=false)
   @Out(required=false)
   private String maTiepDonOut;
   @In(required=false)
   @Out(required=false)
   private String goToThamKhamDieuTriNgoaiTru;
   @Out(required=false)
   private String bacSiKCB;
   private BenhNhan benhNhan;
   private ThamKham thamkham;
   private String resultHidden = "";

   public void resetValue() {}

   @Begin(nested=true)
   @Factory("goToThamKhamDieuTriNgoaiTru")
   public void init()
     throws Exception
   {
     log.info("***Starting init ***");
     try
     {
       ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
       this.ctpkdtntDAO = CtPhieuKhamDtNgoaiTruDelegate.getInstance();
       this.thamkham = tkDelegate.findByBanKhamVaMaTiepDon(this.maBanKhamOut, this.maTiepDonOut);
       this.benhNhan = this.thamkham.getTiepdonMa().getBenhnhanMa();
       if (this.thamkham != null)
       {
         log.info("*** MABANKHAM: " + this.thamkham.getThamkhamMa());
         this.objPhieuKhamDTNgoaiTru = PhieuKhamDtNgoaiTruDelegate.getInstance().findByMaThamKham(this.thamkham.getThamkhamMa());
         if (this.objPhieuKhamDTNgoaiTru == null)
         {
           log.info("*****KO TIM THAY");
           this.objPhieuKhamDTNgoaiTru = new PhieuKhamDtNgoaiTru();
           this.listCtPKDTNT = new ArrayList();


           String chandoanbenh = "";
           if (this.thamkham.getBenhicd10() != null) {
             chandoanbenh = this.thamkham.getBenhicd10().getDmbenhicdTen();
           }
           if ((this.thamkham.getThamkhamGhichu() != null) && (!this.thamkham.getThamkhamGhichu().equals(""))) {
             chandoanbenh = chandoanbenh + " - " + this.thamkham.getThamkhamGhichu();
           }
           this.objPhieuKhamDTNgoaiTru.setPkdtntChandoan(chandoanbenh);


           this.sShowDel = "false";
           this.sShowPrint = "false";
           this.sMaPhieuKhamDT = "";
         }
         else
         {
           log.info("*****KO TIM THAY");
           this.listCtPKDTNT = this.ctpkdtntDAO.findByPKDTNTMa(this.objPhieuKhamDTNgoaiTru.getPkdtntMa());
           this.sMaPhieuKhamDT = this.objPhieuKhamDTNgoaiTru.getPkdtntMa();
           if ((this.objPhieuKhamDTNgoaiTru.getPkdtntChandoan() != null) && (!this.objPhieuKhamDTNgoaiTru.getPkdtntChandoan().equals("")))
           {
             String chandoanbenh = "";
             if (this.thamkham.getBenhicd10() != null) {
               chandoanbenh = this.thamkham.getBenhicd10().getDmbenhicdTen();
             }
             if ((this.thamkham.getThamkhamGhichu() != null) && (!this.thamkham.getThamkhamGhichu().equals(""))) {
               chandoanbenh = chandoanbenh + " - " + this.thamkham.getThamkhamGhichu();
             }
             this.objPhieuKhamDTNgoaiTru.setPkdtntChandoan(chandoanbenh);
           }
           this.sShowDel = "true";
           this.sShowPrint = "true";
         }
       }
       log.info("*** LIST SIZE: " + this.listCtPKDTNT.size());
       if (this.benhNhan.getDmgtMaso() != null)
       {
         if ("1".equals(this.benhNhan.getDmgtMaso().getDmgtMa())) {
           this.gioi = "r1";
         } else {
           this.gioi = "r2";
         }
       }
       else {
         this.gioi = null;
       }
       setOtherValue();

       destroyService();
     }
     catch (Exception e)
     {
       log.info("***init Exception = " + e.toString());
     }
     log.info("***Finished init ***");
   }

   @End
   public void end() {}

   public String ghiNhan()
     throws Exception
   {
     log.info("*****Begin ghiNhan() *****");
     try
     {
       PhieuKhamDtNgoaiTruDelegate phieuKhamDtNgoaiTruDel = PhieuKhamDtNgoaiTruDelegate.getInstance();

       this.objPhieuKhamDTNgoaiTru.setPkdtntThamkham(this.thamkham);
       String sMaPhieu = "";
       sMaPhieu = phieuKhamDtNgoaiTruDel.capNhatPhieuKhamDtNgoaiTru(this.objPhieuKhamDTNgoaiTru, this.sMaPhieuKhamDT, this.listCtPKDTNT);

       log.info("*******************Ma Phieu: " + sMaPhieu);
       FacesMessages.instance().add(IConstantsRes.RPPKDTNT_INSERT_SUCCESS, new Object[] { sMaPhieu });
       log.info("*********************CAP NHAT THANH CONG  PhieuKhamDtNgoaiTruAction *****");
       this.sShowDel = "true";
       this.sShowPrint = "true";
       init();
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       log.error("*************loi***********" + e.toString());
       return null;
     }
     log.info("*****End ghiNhan() *****");
     return null;
   }

   public void enter()
     throws Exception
   {
     log.info("*****Begin Enter() *****");
     insertRow();
     reset_ctpkdtnt();
     log.info("*****End Enter() *****");
   }

   public void insertRow()
   {
     if ((this.ngay.equals("")) || (this.chandoan.equals("")) || (this.dieutri.equals("")) || (this.bacSiMa.equals("")))
     {
       FacesMessages.instance().add(IConstantsRes.THONGTIN_CHANDOAN, new Object[0]);
     }
     else
     {
       CtPhieuKhamDtNgoaiTru each = new CtPhieuKhamDtNgoaiTru();
       each.setChandoan(this.chandoan);
       each.setCtpkdtntDieutri(this.dieutri);
       if ((this.ngay != null) && (!this.ngay.equals(""))) {
         each.setCtpkdtntNgay(Utils.getDBDate("00:00", this.ngay).getTime());
       }
       DtDmNhanVien bacsi = new DtDmNhanVien();
       bacsi.setDtdmnhanvienMaso(this.bacSiMaSo_hidden);
       log.info("***bacsimaso: " + this.bacSiMaSo_hidden);
       bacsi.setDtdmnhanvienMa(this.bacSiMa);
       each.setBacsi(bacsi);
       each.setPhieuKcbNgoaiTru(this.objPhieuKhamDTNgoaiTru);
       this.listCtPKDTNT.add(each);
     }
   }

   public void delete(int index)
     throws Exception
   {
     log.info("*****Begin delete() *****");
     this.listCtPKDTNT.remove(index);
     reset_ctpkdtnt();
     log.info("*****End delete() *****");
   }

   public void reset_ctpkdtnt()
   {
     this.ngay = "";
     this.dieutri = "";
     this.chandoan = "";
   }

   public void huyPhieu()
   {
     log.info("***** start  huyPhieu() *****");
     if ((this.sMaPhieuKhamDT == null) || (this.sMaPhieuKhamDT.equals(""))) {
       return;
     }
     PhieuKhamDtNgoaiTruDelegate PhieuKhamDtNgoaiTruDel = PhieuKhamDtNgoaiTruDelegate.getInstance();
     PhieuKhamDtNgoaiTru obj = PhieuKhamDtNgoaiTruDel.find(this.sMaPhieuKhamDT);
     if (obj == null) {
       return;
     }
     PhieuKhamDtNgoaiTruDel.removeAllPhieuKhamDtNgoaiTru(obj);
     FacesMessages.instance().add(IConstantsRes.RPPKDTNT_DELETE_SUCCESS, new Object[] { this.sMaPhieuKhamDT });
     log.info("***** XOA THANH CONG *****");
     this.objPhieuKhamDTNgoaiTru = new PhieuKhamDtNgoaiTru();
     this.sMaPhieuKhamDT = "";
     this.sShowDel = "false";
     this.sShowPrint = "false";
     this.listCtPKDTNT = new ArrayList();
     reset_ctpkdtnt();
     log.info("***** end  huyPhieu() *****");
   }

   public String quayLai()
     throws Exception
   {
     log.info("*****Begin quayLai() *****");
     this.goToThamKhamDieuTriNgoaiTru = null;
     log.info("*****End quayLai() *****");
     return "ghinhan";
   }

   @Out(required=false)
   @In(required=false)
   private String reportPathTD = null;
   @Out(required=false)
   @In(required=false)
   private String reportTypeTD = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintTD = null;

   public String thuchienAction()
   {
     XuatReport();
     return "B160_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.reportTypeTD = "thamkhamdieutringoaitru";
     log.info("Vao Method XuatReport benhanvaovien");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/phieukhamdieutringoaitru.jrxml";
       log.info("da thay file templete 5" + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       log.info("da thay file template ");
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       Map<String, Object> params = new HashMap();

       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("BENHVIEN", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("DIENTHOAI", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("HOTENBN", this.benhNhan.getBenhnhanHoten());
       params.put("GIOITINH", this.benhNhan.getDmgtMaso(true).getDmgtTen());
       Date nsDate = this.benhNhan.getBenhnhanNgaysinh();
       if (nsDate != null) {
         params.put("NGAYSINH", sdf.format(nsDate));
       } else {
         params.put("NGAYSINH", this.benhNhan.getBenhnhanNamsinh());
       }
       params.put("DANTOC", this.benhNhan.getDantocMa(true).getDmdantocTen());
       params.put("NGHENGHIEP", this.benhNhan.getBenhnhanNghe(true).getDmnghenghiepTen());
       params.put("DIACHI", this.benhNhan.getBenhnhanDiachi());
       params.put("NOILAMVIEC", this.thamkham.getTiepdonMa(true).getTiepdonMacoquan());
       params.put("MATHEBHYT", this.thamkham.getTiepdonMa(true).getTiepdonSothebh());
       params.put("DOITUONG", this.thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongTen());
       if (this.thamkham.getThamkhamBacsi(true).getDtdmnhanvienTen() != null) {
         params.put("BACSI", this.thamkham.getThamkhamBacsi(true).getDtdmnhanvienTen());
       }
       if ((this.thamkham.getTiepdonMa(true).getTiepdonGiatri1() != null) && (this.thamkham.getTiepdonMa(true).getTiepdonGiatri2() != null))
       {
         params.put("GTSUDUNG1", sdf.format(this.thamkham.getTiepdonMa(true).getTiepdonGiatri1()));
         params.put("GTSUDUNG2", sdf.format(this.thamkham.getTiepdonMa(true).getTiepdonGiatri2()));
       }
       params.put("BAOTIN", this.thamkham.getTiepdonMa(true).getTiepdonBaotin());
       if (this.thamkham.getTiepdonMa(true).getTiepdonMachdoanbd(true).getDmbenhicdTen() != null) {
         params.put("CHANDOANBD", this.thamkham.getTiepdonMa(true).getTiepdonMachdoanbd(true).getDmbenhicdTen());
       }
       if (this.thamkham.getThamkhamNgaygio() != null) {
         params.put("DENKBLUC", Utils.getGioPhutNgay(this.thamkham.getThamkhamNgaygio()));
       }
       params.put("CHAYMAULAU", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntTschaymaulau()));
       params.put("PHANUNGTHUOC", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntTsphanungthuoc()));
       params.put("DIUNG", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntTsbdiung()));
       params.put("CAOHA", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntTsbcaoha()));
       params.put("TIMMACH", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntTsbtm()));
       params.put("TIEUDUONG", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntTsbtieuduong()));
       params.put("DADAY", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntTsbdadayth()));
       params.put("PHOI", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntTsbphoi()));
       params.put("TRUYENNHIEM", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntTsbtruyennhiem()));
       params.put("PTRONGMIENG", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntPtrongmieng()));
       params.put("PNGOAIMIENG", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntPngoaimat()));
       params.put("CTMAU", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntXncongthucmau()));
       params.put("TBHOC", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntXntebaohoc()));
       params.put("CAYVK", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntXncayvk()));
       params.put("NHACHU", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntKhnhachu()));
       params.put("CHUARANG", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntKhchuarang()));
       params.put("NHORANG", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntKhnhorang()));
       params.put("CANKHOP", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntKhcankhop()));
       params.put("PHCODINH", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntKhphuchinhcd()));
       params.put("PHTHAOLAP", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntKhphuhinhtl()));
       params.put("CHINHHINHRM", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntKhchinhhinhrm()));
       params.put("RANGTREEM", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntKhrangtreem()));
       params.put("PHONGNGUASR", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntKhphongnguasr()));
       params.put("PTHAMMAT", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntKhphauthuathm()));

       params.put("NHANXET", this.objPhieuKhamDTNgoaiTru.getPkdtntNhanxet());
       params.put("CHANDOAN", this.objPhieuKhamDTNgoaiTru.getPkdtntChandoan());

       params.put("PHTL", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntPhtl()));
       params.put("PHCD", getSymbol(this.objPhieuKhamDTNgoaiTru.getPkdtntPhcd()));


       params.put("MA_PKDT_NGOAITRU", this.sMaPhieuKhamDT);

       String sDonViTuoi = "";
       if (this.benhNhan.getBenhnhanDonvituoi().shortValue() == 1) {
         sDonViTuoi = "";
       } else if (this.benhNhan.getBenhnhanDonvituoi().shortValue() == 2) {
         sDonViTuoi = IConstantsRes.THANG;
       } else if (this.benhNhan.getBenhnhanDonvituoi().shortValue() == 3) {
         sDonViTuoi = IConstantsRes.NGAY;
       }
       params.put("TUOI", this.benhNhan.getBenhnhanTuoi() + " " + sDonViTuoi);

       log.info("================= ");
       Class.forName("com.mysql.jdbc.Driver");
       log.info("da thay driver mysql");
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         log.info(e.getMessage());
       }
       this.jasperPrintTD = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", "thamkhamdieutringoaitru");
       this.reportPathTD = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.reportPathTD);
       this.index += 1;
       log.info("Index :" + this.index);
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       log.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     log.info("Thoat Method XuatReport");
   }

   private int index = 0;

   private void setOtherValue()
   {
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     if ((this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh() != null) && (!this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().equals(""))) {
       this.ngaySinh = formatter.format(Long.valueOf(this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().getTime()));
     }
   }

   public void displayInfor()
     throws Exception
   {
     try
     {
       if ((this.sMaPhieuKhamDT == null) || (this.sMaPhieuKhamDT.equals(""))) {
         return;
       }
       PhieuKhamDtNgoaiTruDelegate PhieuKhamDtNgoaiTruDel = PhieuKhamDtNgoaiTruDelegate.getInstance();
       this.objPhieuKhamDTNgoaiTru = new PhieuKhamDtNgoaiTru();
       List<PhieuKhamDtNgoaiTru> ls = PhieuKhamDtNgoaiTruDel.findByPhieuKhamDtNgoaiTru(getMaPhieuKhamDtNgoaiTru());
       if ((ls == null) || (ls.size() == 0))
       {
         FacesMessages.instance().add(IConstantsRes.RPPKDTNT_NOT_EXIST, new Object[0]);
         this.sMaPhieuKhamDT = "";
         this.sShowDel = "false";
         this.sShowPrint = "false";
         return;
       }
       this.sShowDel = "true";
       this.sShowPrint = "true";
       this.objPhieuKhamDTNgoaiTru = ((PhieuKhamDtNgoaiTru)ls.get(0));
       this.sMaPhieuKhamDT = this.objPhieuKhamDTNgoaiTru.getPkdtntMa();



       log.info("*****sMaPhieuKhamDT: " + this.sMaPhieuKhamDT);
     }
     catch (Exception e)
     {
       System.out.println("error on function displayInfor" + e);
     }
   }

   public void get_thuoc_info()
   {
     this.dieutri = "";
     if ((this.ngay != null) && (!this.ngay.equals("")))
     {
       if (Utils.getDBDateWithHour(0, this.ngay, true) == null)
       {
         log.info("### BA RHM Ngoai tru -- get_thuoc_info(): ERROR Parse NGAY !! ");
         return;
       }
       Date tuNgay = Utils.getDBDateWithHour(0, this.ngay, true).getTime();
       Date denNgay = Utils.getDBDateWithHour(23, this.ngay, false).getTime();
       String thuocBK = "";
       String thuocBH = "";
       String thuocVe = "";


       ThuocPhongKhamDelegate tpkDelegate = ThuocPhongKhamDelegate.getInstance();
       List<ThuocPhongKham> listThuocPhongKham = tpkDelegate.findByThamKhamVaNgay(this.thamkham.getThamkhamMa(), tuNgay, denNgay);
       if ((listThuocPhongKham != null) && (listThuocPhongKham.size() > 0))
       {
         for (ThuocPhongKham thuoc : listThuocPhongKham) {
           if (thuoc.getThuocphongkhamLoai().equals("3"))
           {
             thuocBH = thuocBH + "- " + thuoc.getThuocphongkhamMathuoc(true).getDmthuocTen();
             if (thuoc.getThuocphongkhamSoluong() != null) {
               thuocBH = thuocBH + ", " + thuoc.getThuocphongkhamSoluong().toString();
             }
             thuocBH = thuocBH + " " + thuoc.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhTen();
             if ((thuoc.getThuocphongkhamChidan() != null) && (!thuoc.getThuocphongkhamChidan().equals(""))) {
               thuocBH = thuocBH + ", " + thuoc.getThuocphongkhamChidan();
             }
             thuocBH = thuocBH + "\n";
           }
           else if (thuoc.getThuocphongkhamLoai().equals("1"))
           {
             thuocBK = thuocBK + "- " + thuoc.getThuocphongkhamMathuoc(true).getDmthuocTen();
             if (thuoc.getThuocphongkhamSoluong() != null) {
               thuocBK = thuocBK + ", " + thuoc.getThuocphongkhamSoluong().toString();
             }
             thuocBK = thuocBK + " " + thuoc.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhTen();
             if ((thuoc.getThuocphongkhamChidan() != null) && (!thuoc.getThuocphongkhamChidan().equals(""))) {
               thuocBK = thuocBK + ", " + thuoc.getThuocphongkhamChidan();
             }
             thuocBK = thuocBK + "\n";
           }
           else if (thuoc.getThuocphongkhamLoai().equals("2"))
           {
             thuocVe = thuocVe + "- " + thuoc.getThuocphongkhamMathuoc(true).getDmthuocTen();
             if (thuoc.getThuocphongkhamSoluong() != null) {
               thuocVe = thuocVe + ", " + thuoc.getThuocphongkhamSoluong().toString();
             }
             thuocVe = thuocVe + " " + thuoc.getThuocphongkhamMathuoc(true).getDmdonvitinhMaso(true).getDmdonvitinhTen();
             if ((thuoc.getThuocphongkhamChidan() != null) && (!thuoc.getThuocphongkhamChidan().equals(""))) {
               thuocVe = thuocVe + ", " + thuoc.getThuocphongkhamChidan();
             }
             thuocVe = thuocVe + "\n";
           }
         }
         if (!thuocBH.equals("")) {
           this.dieutri = (this.dieutri + "+ " + IConstantsRes.THUOC_QUAY_BV + "\n" + thuocBH);
         }
         if (!thuocBK.equals("")) {
           this.dieutri = (this.dieutri + "\n+ " + IConstantsRes.THUOC_BAN_KHAM + "\n" + thuocBK);
         }
         if (!thuocVe.equals("")) {
           this.dieutri = (this.dieutri + "\n+ " + IConstantsRes.THUOC_TOA_VE + "\n" + thuocVe);
         }
       }
       else
       {
         this.dieutri = IConstantsRes.KHONG_DUNG_THUOC;
       }
     }
   }

   public void destroyService()
   {
     try
     {
       log.debug("===== begin destroyService() method");

       log.debug("***** End destroyService() method");
     }
     catch (Exception ex)
     {
       log.debug("*****destroyService Exception: " + ex);
     }
   }

   @Destroy
   public void destroy()
   {
     log.info("************************* destroy PhieuKhamDtNgoaiTru");
   }

   public static void main(String[] args) {}

   public static String getFORMAT_DATE()
   {
     return FORMAT_DATE;
   }

   public static void setFORMAT_DATE(String format_date)
   {
     FORMAT_DATE = format_date;
   }

   public static String getFORMAT_DATE_TIME()
   {
     return FORMAT_DATE_TIME;
   }

   public static void setFORMAT_DATE_TIME(String format_date_time)
   {
     FORMAT_DATE_TIME = format_date_time;
   }

   public BenhNhan getBenhNhan()
   {
     return this.benhNhan;
   }

   public void setBenhNhan(BenhNhan benhNhan)
   {
     this.benhNhan = benhNhan;
   }

   public String getNgaySinh()
   {
     return this.ngaySinh;
   }

   public void setNgaySinh(String ngaySinh)
   {
     this.ngaySinh = ngaySinh;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public int getStt()
   {
     return this.stt;
   }

   public void setStt(int stt)
   {
     this.stt = stt;
   }

   public ThamKham getThamkham()
   {
     return this.thamkham;
   }

   public void setThamkham(ThamKham thamkham)
   {
     this.thamkham = thamkham;
   }

   public String getResultHidden()
   {
     return this.resultHidden;
   }

   public void setResultHidden(String resultHidden)
   {
     this.resultHidden = resultHidden;
   }

   public String getMaBanKhamOut()
   {
     return this.maBanKhamOut;
   }

   public void setMaBanKhamOut(String maBanKhamOut)
   {
     this.maBanKhamOut = maBanKhamOut;
   }

   public String getMaTiepDonOut()
   {
     return this.maTiepDonOut;
   }

   public void setMaTiepDonOut(String maTiepDonOut)
   {
     this.maTiepDonOut = maTiepDonOut;
   }

   public String getGioi()
   {
     return this.gioi;
   }

   public void setGioi(String gioi)
   {
     this.gioi = gioi;
   }

   public String getBacSiKCB()
   {
     return this.bacSiKCB;
   }

   public void setBacSiKCB(String bacSiKCB)
   {
     this.bacSiKCB = bacSiKCB;
   }

   public String getGoToThamKhamDieuTriNgoaiTru()
   {
     return this.goToThamKhamDieuTriNgoaiTru;
   }

   public void setGoToThamKhamDieuTriNgoaiTru(String str)
   {
     this.goToThamKhamDieuTriNgoaiTru = str;
   }

   public String getMaPhieuKhamDtNgoaiTru()
   {
     return this.sMaPhieuKhamDT;
   }

   public void setMaPhieuKhamDtNgoaiTru(String maGiayChuyenVien)
   {
     this.sMaPhieuKhamDT = maGiayChuyenVien;
   }

   public String getShowDel()
   {
     return this.sShowDel;
   }

   public void setShowDel(String showDel)
   {
     this.sShowDel = showDel;
   }

   public String getShowPrint()
   {
     return this.sShowPrint;
   }

   public void setShowPrint(String showPrint)
   {
     this.sShowPrint = showPrint;
   }

   public PhieuKhamDtNgoaiTru getPhieuKhamDTNgoaiTru()
   {
     return this.objPhieuKhamDTNgoaiTru;
   }

   public void setPhieuKhamDTNgoaiTru(PhieuKhamDtNgoaiTru objPhieuKhamDTNgoaiTru)
   {
     this.objPhieuKhamDTNgoaiTru = objPhieuKhamDTNgoaiTru;
   }

   private String getSymbol(Boolean boo)
   {
     if (boo.booleanValue()) {
       return "X";
     }
     return "";
   }

   public String getNgay()
   {
     return this.ngay;
   }

   public void setNgay(String ngay)
   {
     this.ngay = ngay;
   }

   public String getChandoan()
   {
     return this.chandoan;
   }

   public void setChandoan(String chandoan)
   {
     this.chandoan = chandoan;
   }

   public String getBacSiMa()
   {
     return this.bacSiMa;
   }

   public void setBacSiMa(String bacSiMa)
   {
     this.bacSiMa = bacSiMa;
   }

   public Integer getBacSiMaSo_hidden()
   {
     return this.bacSiMaSo_hidden;
   }

   public void setBacSiMaSo_hidden(Integer bacSiMaSo_hidden)
   {
     this.bacSiMaSo_hidden = bacSiMaSo_hidden;
   }

   public List<CtPhieuKhamDtNgoaiTru> getListCtPKDTNT()
   {
     return this.listCtPKDTNT;
   }

   public void setListCtPKDTNT(List<CtPhieuKhamDtNgoaiTru> listCtPKDTNT)
   {
     this.listCtPKDTNT = listCtPKDTNT;
   }

   public CtPhieuKhamDtNgoaiTru getCtpkdtntSelection()
   {
     return this.ctpkdtntSelection;
   }

   public void setCtpkdtntSelection(CtPhieuKhamDtNgoaiTru ctpkdtntSelection)
   {
     this.ctpkdtntSelection = ctpkdtntSelection;
   }

   public String getDieutri()
   {
     return this.dieutri;
   }

   public void setDieutri(String dieutri)
   {
     this.dieutri = dieutri;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.ThamKhamDieuTriNgoaiTru

 * JD-Core Version:    0.7.0.1

 */