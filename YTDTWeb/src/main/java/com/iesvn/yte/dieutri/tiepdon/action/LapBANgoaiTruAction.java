 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.BenhAnNgoaiTruDelegate;
 import com.iesvn.yte.dieutri.delegate.CtBenhAnNgoaiTruDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocPhongKhamDelegate;
 import com.iesvn.yte.dieutri.entity.BenhAnNgoaiTru;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsKham;
 import com.iesvn.yte.dieutri.entity.CtBenhAnNgoaiTru;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmBenhVien;
 import com.iesvn.yte.entity.DmDanToc;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKetQuaDieuTri;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmQuocGia;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
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
 @Name("B121_10_Lapbangoaitru")
 @Synchronized(timeout=6000000L)
 public class LapBANgoaiTruAction
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   private static final long serialVersionUID = 10L;
   private int stt;
   private String ngaySinh;
   private String thoiGian;
   private String gioThamKham;
   private String ngay;
   private String dienbienbenh;
   private String ylenh;
   private String bacSiMa;
   private Integer bacSiMaSo_hidden;
   @DataModel
   private List<CtBenhAnNgoaiTru> listCtBANT;
   @DataModelSelection("listCtBANT")
   private CtBenhAnNgoaiTru ctbantSelection;
   private CtBenhAnNgoaiTruDelegate ctbantDAO;
   private static Logger log = Logger.getLogger(ThamKhamAction.class);
   @In(required=false)
   @Out(required=false)
   private String maBanKhamOut;
   @In(required=false)
   @Out(required=false)
   private String maTiepDonOut;
   @In(required=false)
   @Out(required=false)
   private String goToLapBANgoaiTru;
   private SimpleDateFormat formatter;
   private BenhNhan benhNhan;
   private ThamKham thamkham;
   private String resultHidden = "";
   private BenhAnNgoaiTru baNgoaiTru = null;

   public void resetValue() {}

   @Begin(nested=true)
   @Factory("goToLapBANgoaiTru")
   public void init()
     throws Exception
   {
     log.info("***Starting init, maTiepDonOut = " + this.maTiepDonOut);
     try
     {
       this.dienbienbenh = "";
       this.ylenh = "";
       ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();
       this.ctbantDAO = CtBenhAnNgoaiTruDelegate.getInstance();
       this.thamkham = tkDelegate.findByBanKhamVaMaTiepDon(this.maBanKhamOut, this.maTiepDonOut);
       this.benhNhan = this.thamkham.getTiepdonMa().getBenhnhanMa();
       if (this.thamkham.getThamkhamBacsi() != null) {
         this.bacSiMa = this.thamkham.getThamkhamBacsi().getDtdmnhanvienMa();
       }
       BenhAnNgoaiTru baNgoaiTruTemp = null;
       try
       {
         if (this.thamkham.getTiepdonMa().getDoituongMa().getDmdoituongMa().equalsIgnoreCase("BH"))
         {
           log.info("SotheBH = " + this.thamkham.getTiepdonMa().getTiepdonSothebh() + ", Ma BN = " + this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanMa());
           baNgoaiTruTemp = BenhAnNgoaiTruDelegate.getInstance().findBySotheBHAndMaBNAndBanKham(this.thamkham.getTiepdonMa().getTiepdonSothebh(), this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanMa(), this.thamkham.getThamkhamBankham().getDtdmbankhamMaso());
         }
         else
         {
           log.info("Ma BN = " + this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanMa());
           baNgoaiTruTemp = BenhAnNgoaiTruDelegate.getInstance().findBySotheBHAndMaBNAndBanKham(null, this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanMa(), this.thamkham.getThamkhamBankham().getDtdmbankhamMaso());
         }
         log.info("baNgoaiTruTemp = " + baNgoaiTruTemp);
       }
       catch (Exception e)
       {
         log.info("error:" + e);
       }
       if (baNgoaiTruTemp != null)
       {
         this.baNgoaiTru = baNgoaiTruTemp;
         this.listCtBANT = this.ctbantDAO.findByBANTMa(this.baNgoaiTru.getBantMa());

         log.info("%%%%%%%%%%%%%%%%%%%%%baNgoaiTru.getBantMa(): " + this.baNgoaiTru.getBantMa());
       }
       else
       {
         this.baNgoaiTru = new BenhAnNgoaiTru();
         this.listCtBANT = new ArrayList();
       }
       setOtherValue();


       get_thuoc_info();

       destroyService();
       this.goToLapBANgoaiTru = "";
     }
     catch (Exception e)
     {
       log.info("***init Exception = " + e);
     }
     log.info("***Finished init ***");
   }

   @End
   public void end()
   {
     this.goToLapBANgoaiTru = null;
   }

   public void enter()
     throws Exception
   {
     log.info("*****Begin Enter() *****");
     insertRow();
     reset_ctbant();
     log.info("*****End Enter() *****");
   }

   private void insertRow()
   {
     CtBenhAnNgoaiTru each = new CtBenhAnNgoaiTru();
     each.setCtbantDienbienbenh(this.dienbienbenh);
     each.setCtbantYlenh(this.ylenh);
     if ((this.ngay != null) && (!this.ngay.equals(""))) {
       each.setCtbantNgay(Utils.getDBDate("00:00", this.ngay).getTime());
     }
     DtDmNhanVien bacsi = new DtDmNhanVien();
     if ((this.bacSiMaSo_hidden != null) && (!this.bacSiMaSo_hidden.equals("")))
     {
       bacsi.setDtdmnhanvienMaso(this.bacSiMaSo_hidden);
       bacsi.setDtdmnhanvienMa(this.bacSiMa);
       each.setBacsi(bacsi);
     }
     else
     {
       each.setBacsi(null);
     }
     each.setBenhAnNgoaiTru(this.baNgoaiTru);
     this.listCtBANT.add(each);
   }

   public void delete(int index)
     throws Exception
   {
     log.info("*****Begin delete() *****");
     this.listCtBANT.remove(index);
     reset_ctbant();
     log.info("*****End delete() *****");
   }

   public void reset_ctbant()
   {
     this.dienbienbenh = "";
     this.ylenh = "";
   }

   public String ghiNhanAjax()
     throws Exception
   {
     log.info("***Starting ghinhan, ma BANT = " + this.baNgoaiTru.getBantMa());


     this.baNgoaiTru.setBantThamkham(this.thamkham);
     if ((this.tuNgay != null) && (!this.tuNgay.equals("")))
     {
       SimpleDateFormat df = new SimpleDateFormat(Utils.FORMAT_DATE);
       Date dTN = df.parse(this.tuNgay);
       this.baNgoaiTru.setBantDttungay(dTN);
     }
     if ((this.denNgay != null) && (!this.denNgay.equals("")))
     {
       SimpleDateFormat df = new SimpleDateFormat(Utils.FORMAT_DATE);
       Date dDN = df.parse(this.denNgay);
       this.baNgoaiTru.setBantDtdenngay(dDN);
     }
     String maBANT = "";
     this.baNgoaiTru.setBantBenhnhan(this.thamkham.getTiepdonMa().getBenhnhanMa());
     this.baNgoaiTru.setBantBankham(this.thamkham.getThamkhamBankham());
     if (this.thamkham.getTiepdonMa().getDoituongMa().getDmdoituongMa().equalsIgnoreCase("BH")) {
       this.baNgoaiTru.setBantSothebh(this.thamkham.getTiepdonMa().getTiepdonSothebh());
     }
     maBANT = BenhAnNgoaiTruDelegate.getInstance().capNhatBenhAnNgoaiTru(this.baNgoaiTru, this.baNgoaiTru.getBantMa(), this.listCtBANT);
     if ((maBANT != null) && (!maBANT.equals(""))) {
       this.baNgoaiTru.setBantMa(maBANT);
     }
     FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
     log.info("***Finished ghinhan **");
     return "/B1_Tiepdon/B121_10_Lapbangoaitru";
   }

   public String quayLai()
     throws Exception
   {
     log.info("*****Begin quayLai() *****");
     this.goToLapBANgoaiTru = null;
     log.info("*****End quayLai() *****");
     return "ghinhan";
   }

   public void lapBAmoi()
   {
     this.baNgoaiTru = new BenhAnNgoaiTru();
     this.listCtBANT = new ArrayList();
     reset_ctbant();
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
     this.reportTypeTD = "benhanvaovien";
     log.info("Vao Method XuatReport benhanvaovien");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/benhanngoaitru.jrxml";
       String sub0Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/benhanngoaitru_sub1.jrxml";
       String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/benhanngoaitru_sub2.jrxml";
       String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/benhanngoaitru_sub3.jrxml";
       log.info("da thay file templete 5" + pathTemplate);

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       JasperReport sub0Report = JasperCompileManager.compileReport(sub0Template);
       JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);
       JasperReport sub2Report = JasperCompileManager.compileReport(sub2Template);
       log.info("da thay file template ");
       Map<String, Object> params = new HashMap();

       params.put("sub1", sub0Report);
       params.put("sub2", sub1Report);
       params.put("sub3", sub2Report);

       BenhAnNgoaiTru baNgoaiTruTemp = BenhAnNgoaiTruDelegate.getInstance().find(this.baNgoaiTru.getBantMa());
       if (baNgoaiTruTemp != null) {
         params.put("SOVAOVIEN", baNgoaiTruTemp.getBantSovaovien());
       }
       params.put("soyte", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("benhvien", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("sodt", this.thamkham.getThamkhamBankham(true).getDtdmbankhamTen());

       params.put("HoTen", this.benhNhan.getBenhnhanHoten());
       SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
       log.info("****NGAYSINH: " + this.benhNhan.getBenhnhanNgaysinh());
       log.info("****NAM SINH: " + this.benhNhan.getBenhnhanNamsinh());
       if (this.benhNhan.getBenhnhanNgaysinh() != null) {
         params.put("NgaySinh", sdf.format(this.benhNhan.getBenhnhanNgaysinh()));
       } else if (this.benhNhan.getBenhnhanNamsinh() != null) {
         params.put("NgaySinh", this.benhNhan.getBenhnhanNamsinh());
       }
       log.info("****NGAYSINH: " + this.benhNhan.getBenhnhanNgaysinh());
       log.info("****NAM SINH: " + this.benhNhan.getBenhnhanNamsinh());
       params.put("Tuoi", this.benhNhan.getBenhnhanTuoi());

       params.put("DonViTuoi", this.benhNhan.getBenhnhanDonvituoi() == null ? new Integer(1) : new Integer(this.benhNhan.getBenhnhanDonvituoi().shortValue()));
       params.put("Gioi", this.benhNhan.getDmgtMaso(true).getDmgtTen());

       DieuTriUtilDelegate dele = DieuTriUtilDelegate.getInstance();

       DmDanToc danToc = (DmDanToc)dele.findByMa(this.benhNhan.getDantocMa(true).getDmdantocMa(), "DmDanToc", "dmdantocMa");
       if (danToc != null)
       {
         params.put("DanToc", danToc.getDmdantocTen());
         params.put("MaDanToc", danToc.getDmdantocMa());
       }
       else
       {
         params.put("DanToc", "");
         params.put("MaDanToc", "");
       }
       String sDiaChi = "";
       if (this.thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen() != null) {
         sDiaChi = sDiaChi + this.thamkham.getTiepdonMa().getBenhnhanMa().getXaMa(true).getDmxaTen() + ",";
       }
       if (this.benhNhan.getBenhnhanDiachi() != null) {
         params.put("DiaChi", this.benhNhan.getBenhnhanDiachi() + " " + sDiaChi.replace(",", ""));
       } else {
         params.put("DiaChi", "" + sDiaChi.replace(",", ""));
       }
       DmHuyen huyen = (DmHuyen)dele.findByMa(this.benhNhan.getHuyenMa(true).getDmhuyenMa(), "DmHuyen", "dmhuyenMa");
       if (huyen != null)
       {
         params.put("Huyen", huyen.getDmhuyenTen());
         params.put("MaHuyen", huyen.getDmhuyenMa());
       }
       else
       {
         params.put("Huyen", "");
         params.put("MaHuyen", "");
       }
       DmTinh tinh = (DmTinh)dele.findByMa(this.benhNhan.getTinhMa(true).getDmtinhMa(), "DmTinh", "dmtinhMa");
       if (tinh != null)
       {
         params.put("Tinh", tinh.getDmtinhTen());
         params.put("MaTinh", tinh.getDmtinhMa());
       }
       else
       {
         params.put("Tinh", "");
         params.put("MaTinh", "");
       }
       DmQuocGia quocGia = (DmQuocGia)dele.findByMa(this.benhNhan.getQuocgiaMa(true).getDmquocgiaMa(), "DmQuocGia", "dmquocgiaMa");
       if ((quocGia != null) && (!"VNA".equals(quocGia.getDmquocgiaMa())))
       {
         params.put("NgoaiKieu", quocGia.getDmquocgiaTen());
         params.put("MaNgoaiKieu", quocGia.getDmquocgiaMa());
       }
       else
       {
         params.put("NgoaiKieu", "");
         params.put("MaNgoaiKieu", "");
       }
       DmNgheNghiep ngheNghiep = (DmNgheNghiep)dele.findByMa(this.benhNhan.getBenhnhanNghe(true).getDmnghenghiepMa(), "DmNgheNghiep", "dmnghenghiepMa");
       if (ngheNghiep != null)
       {
         params.put("NgheNghiep", ngheNghiep.getDmnghenghiepTen());
         params.put("MaNgheNghiep", ngheNghiep.getDmnghenghiepMa());
       }
       else
       {
         params.put("NgheNghiep", "");
         params.put("MaNgheNghiep", "");
       }
       String doiTuongMa = this.thamkham.getTiepdonMa(true).getDoituongMa(true).getDmdoituongMa();
       if ((doiTuongMa != null) && (doiTuongMa.equals("BH")))
       {
         params.put("DoiTuong_BHYT", "X");
         params.put("DoiTuong_ThuPhi", "");
         params.put("DoiTuong_Mien", "");
         params.put("DoiTuong_Khac", "");
       }
       else if ((doiTuongMa != null) && (doiTuongMa.equals("TP")))
       {
         params.put("DoiTuong_BHYT", "");
         params.put("DoiTuong_ThuPhi", "X");
         params.put("DoiTuong_Mien", "");
         params.put("DoiTuong_Khac", "");
       }
       else if ((doiTuongMa != null) && (doiTuongMa.equals("MP")))
       {
         params.put("DoiTuong_BHYT", "");
         params.put("DoiTuong_ThuPhi", "");
         params.put("DoiTuong_Mien", "X");
         params.put("DoiTuong_Khac", "");
       }
       else
       {
         params.put("DoiTuong_BHYT", "");
         params.put("DoiTuong_ThuPhi", "");
         params.put("DoiTuong_Mien", "");
         params.put("DoiTuong_Khac", "X");
       }
       params.put("NoiLamViec", this.thamkham.getTiepdonMa().getTiepdonMacoquan());


       params.put("QuaTrinhBenhLy", this.baNgoaiTru.getBantQtbenhly());
       params.put("TienSuBenhBanThan", this.baNgoaiTru.getBantTiensubenhbt());
       params.put("TienSuBenhGiaDinh", this.baNgoaiTru.getBantTiensubenhgd());
       params.put("ToanThan", this.baNgoaiTru.getBantToanthan());
       params.put("CacBoPhan", this.baNgoaiTru.getBantCacbophan());
       params.put("XetNghiem", this.baNgoaiTru.getBantXetnghiem());
       params.put("DaXuLy", this.baNgoaiTru.getBantDaxuly());
       params.put("TuNgay", this.baNgoaiTru.getBantDttungay());
       params.put("DenNgay", this.baNgoaiTru.getBantDtdenngay());

       params.put("HuyetApMin", this.thamkham.getTiepdonMa(true).getTiepdonHamin());
       params.put("HuyetApMax", this.thamkham.getTiepdonMa(true).getTiepdonHamax());

       params.put("Mach", this.thamkham.getTiepdonMa(true).getTiepdonMach());
       params.put("NhietDo", this.thamkham.getTiepdonMa(true).getTiepdonNhietdo());
       params.put("NhipTho", this.thamkham.getTiepdonMa(true).getTiepdonNhiptho());
       params.put("CANNANG", this.thamkham.getTiepdonMa(true).getTiepdonTrluong());
       if ((this.thamkham.getTiepdonMa().getTiepdonSothebh() != null) && (!this.thamkham.getTiepdonMa().getTiepdonSothebh().equals("")))
       {
         String maBV = this.thamkham.getTiepdonMa(true).getKcbbhytMa(true).getDmbenhvienMa();
         if (maBV == null) {
           maBV = "";
         }
         params.put("SoTheBHYT", this.thamkham.getTiepdonMa().getTiepdonSothebh() + "-" + maBV.replace(".", "-"));

         log.info("SoTheBHYT:" + params.get("SoTheBHYT"));
       }
       else
       {
         params.put("SoTheBHYT", "");
       }
       Date giaTri2 = this.thamkham.getTiepdonMa(true).getTiepdonGiatri2();
       if (giaTri2 != null) {
         params.put("BHYTGIATRIDEN", giaTri2);
       }
       if (this.thamkham.getTiepdonMa().getTiepdonBaotin() != null) {
         params.put("NguoiBaoTin", this.thamkham.getTiepdonMa().getTiepdonBaotin());
       } else {
         params.put("NguoiBaoTin", "");
       }
       params.put("NgayGioVaoVien", this.thamkham.getTiepdonMa(true).getTiepdonNgaygio());

       String diengiaitt = null;
       if (diengiaitt == null) {
         diengiaitt = "";
       }
       DmBenhIcd benhtuyentruoc = (DmBenhIcd)dele.findByMa(this.thamkham.getTiepdonMa(true).getTiepdonMachdoanbd(true).getDmbenhicdMa(), "DmBenhIcd", "dmbenhicdMa");
       log.info("****benhtuyentruoc:" + benhtuyentruoc);
       if (benhtuyentruoc != null)
       {
         params.put("ChanDoanTuyenTruoc", benhtuyentruoc.getDmbenhicdMa() + " - " + benhtuyentruoc.getDmbenhicdTen() + " " + diengiaitt);
         log.info("*****ChanDoanTuyenTruoc: " + benhtuyentruoc.getDmbenhicdMa() + " - " + benhtuyentruoc.getDmbenhicdTen() + " " + diengiaitt);
       }
       else
       {
         if (diengiaitt.equals("")) {
           diengiaitt = "";
         }
         params.put("ChanDoanTuyenTruoc", diengiaitt);
       }
       params.put("MaTiepDon", this.thamkham.getTiepdonMa(true).getTiepdonMa());

       params.put("DONVITRIENKHAI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);

       params.put("KHOAKHAMBENH", this.thamkham.getThamkhamBankham(true).getDmkhoaMaso().getDmkhoaTen());
       if (this.baNgoaiTru.getBantQtbenhlidbls() != null) {
         params.put("QTBLDBLS", this.baNgoaiTru.getBantQtbenhlidbls());
       }
       if (this.thamkham.getTiepdonMa(true).getTiepdonLydovaov() != null) {
         params.put("LIDOVAOVIEN", this.thamkham.getTiepdonMa(true).getTiepdonLydovaov());
       }
       if (this.thamkham.getThamkhamKetqua(true).getDmkqdtTen() != null) {
         params.put("KETQUADT", this.thamkham.getThamkhamKetqua(true).getDmkqdtTen());
       }
       params.put("MA_BENH_AN_NGOAI_TRU", this.baNgoaiTru.getBantMa());

       log.info("%%%%%%%%%%%%%%%%%%%%%baNgoaiTru.getBantMa():" + this.baNgoaiTru.getBantMa());

       int tongsoluong = 0;
       if (this.baNgoaiTru.getBantPpdieutri() != null) {
         params.put("PPDT", this.baNgoaiTru.getBantPpdieutri());
       }
       if (this.baNgoaiTru.getBantPpdieutri() != null) {
         params.put("TTNBRV", this.baNgoaiTru.getBantTinhtrangnguoibenh());
       }
       if (this.baNgoaiTru.getBantHuongdt() != null) {
         params.put("HDTCCDTT", this.baNgoaiTru.getBantHuongdt());
       }
       if (this.baNgoaiTru.getBantSlxquang() != null)
       {
         params.put("XQUANG", this.baNgoaiTru.getBantSlxquang());
         tongsoluong = this.baNgoaiTru.getBantSlxquang().intValue();
       }
       if (this.baNgoaiTru.getBantSlscanner() != null)
       {
         params.put("SCANNER", this.baNgoaiTru.getBantSlscanner());
         tongsoluong += this.baNgoaiTru.getBantSlscanner().intValue();
       }
       if (this.baNgoaiTru.getBantSlsieuam() != null)
       {
         params.put("SIEUAM", this.baNgoaiTru.getBantSlsieuam());
         tongsoluong += this.baNgoaiTru.getBantSlsieuam().intValue();
       }
       if (this.baNgoaiTru.getBantSlkhac() != null)
       {
         params.put("HSKHAC", this.baNgoaiTru.getBantSlkhac());
         tongsoluong += this.baNgoaiTru.getBantSlkhac().intValue();
       }
       if (this.baNgoaiTru.getBantSlxetnghiem() != null)
       {
         params.put("XETNGHIEM", this.baNgoaiTru.getBantSlxetnghiem());
         tongsoluong += this.baNgoaiTru.getBantSlxetnghiem().intValue();
       }
       params.put("TONGHOSO", Integer.valueOf(tongsoluong));
       if (this.baNgoaiTru.getBantGiaiphaubenh() != null) {
         params.put("GIAIPHAUBENH", this.baNgoaiTru.getBantGiaiphaubenh());
       }
       DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
       String maChanDoan = "";
       String tenChanDoan = "";
       if ((this.thamkham.getBenhicd10() != null) && (this.thamkham.getBenhicd10().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
         }
       }
       String sBenhChinh = maChanDoan + " " + tenChanDoan;
       params.put("BENHCHINH", sBenhChinh);

       String sBenhPhu = "";
       if ((this.thamkham.getBenhicd10phu1() != null) && (this.thamkham.getBenhicd10phu1().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu1().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           sBenhPhu = sBenhPhu + maChanDoan + " " + tenChanDoan;
         }
       }
       if ((this.thamkham.getBenhicd10phu2() != null) && (this.thamkham.getBenhicd10phu2().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu2().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           sBenhPhu = sBenhPhu + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       if ((this.thamkham.getBenhicd10phu3() != null) && (this.thamkham.getBenhicd10phu3().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu3().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           sBenhPhu = sBenhPhu + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       if ((this.thamkham.getBenhicd10phu4() != null) && (this.thamkham.getBenhicd10phu4().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu4().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           sBenhPhu = sBenhPhu + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       if ((this.thamkham.getBenhicd10phu5() != null) && (this.thamkham.getBenhicd10phu5().getDmbenhicdMaso() != null))
       {
         DmBenhIcd benh = (DmBenhIcd)dieuTriUtilDelegate.findByMaSo(this.thamkham.getBenhicd10phu5().getDmbenhicdMaso(), "DmBenhIcd", "dmbenhicdMaso");
         if (benh != null)
         {
           maChanDoan = benh.getDmbenhicdMa();
           tenChanDoan = benh.getDmbenhicdTen();
           sBenhPhu = sBenhPhu + " , " + maChanDoan + " " + tenChanDoan;
         }
       }
       params.put("BENHPHU", sBenhPhu);
       if (!sBenhPhu.equals("")) {
         sBenhPhu = ", " + sBenhPhu;
       }
       String sChanDoanBD = sBenhChinh + sBenhPhu;

       params.put("CHANDOANBD", sChanDoanBD);
       if (this.thamkham.getTiepdonMa(true).getTiepdonDonvigoi() != null) {
         params.put("yte", "X");
       } else {
         params.put("tuden", "X");
       }
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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", "benhanvaovien");
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
   private String tuNgay;
   private String denNgay;

   public String getThoiGian()
   {
     return this.thoiGian;
   }

   public void setThoiGian(String thoiGian)
   {
     this.thoiGian = thoiGian;
   }

   private void setOtherValue()
   {
     SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
     if ((this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh() != null) && (!this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().equals(""))) {
       this.ngaySinh = formatter.format(Long.valueOf(this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh().getTime()));
     } else if (this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh() != null) {
       this.ngaySinh = this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh();
     }
     if ((this.thamkham.getThamkhamNgaygio() != null) && (!this.thamkham.getThamkhamNgaygio().equals("")))
     {
       this.thoiGian = formatter.format(Long.valueOf(this.thamkham.getThamkhamNgaygio().getTime()));
       this.ngay = formatter.format(Long.valueOf(this.thamkham.getThamkhamNgaygio().getTime()));
       this.gioThamKham = Utils.getGioPhut(this.thamkham.getThamkhamNgaygio());
     }
     if ((this.baNgoaiTru.getBantDttungay() != null) && (!this.baNgoaiTru.getBantDttungay().equals(""))) {
       this.tuNgay = formatter.format(Long.valueOf(this.baNgoaiTru.getBantDttungay().getTime()));
     }
     if ((this.baNgoaiTru.getBantDtdenngay() != null) && (!this.baNgoaiTru.getBantDtdenngay().equals(""))) {
       this.denNgay = formatter.format(Long.valueOf(this.baNgoaiTru.getBantDtdenngay().getTime()));
     }
   }

   private void setinfor(ClsKham nhapctSelected)
   {
     DtDmClsBangGia dmkythuat = nhapctSelected.getClskhamMahang();

     nhapctSelected.setClskhamMahang(dmkythuat);
     if ((this.thamkham.getThamkhamNgaygio() != null) && (!"".equals(this.thamkham.getThamkhamNgaygio()))) {
       nhapctSelected.setClskhamNgaygio(this.thamkham.getThamkhamNgaygio());
     }
   }

   public void get_thuoc_info()
   {
     this.ylenh = "";
     if ((this.ngay != null) && (!this.ngay.equals("")))
     {
       if (Utils.getDBDateWithHour(0, this.ngay, true) == null)
       {
         log.info("### Lap BA Ngoai tru -- get_thuoc_info(): ERROR Parse NGAY !! ");
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
           this.ylenh = (this.ylenh + "+ " + IConstantsRes.THUOC_QUAY_BV + "\n" + thuocBH);
         }
         if (!thuocBK.equals("")) {
           this.ylenh = (this.ylenh + "\n+ " + IConstantsRes.THUOC_BAN_KHAM + "\n" + thuocBK);
         }
         if (!thuocVe.equals("")) {
           this.ylenh = (this.ylenh + "\n+ " + IConstantsRes.THUOC_TOA_VE + "\n" + thuocVe);
         }
       }
       else
       {
         this.ylenh = IConstantsRes.KHONG_DUNG_THUOC;
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
     log.info("************************* destroy ThamKhamAction");
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

   public String getGioThamKham()
   {
     return this.gioThamKham;
   }

   public void setGioThamKham(String gioThamKham)
   {
     this.gioThamKham = gioThamKham;
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

   public BenhAnNgoaiTru getBaNgoaiTru()
   {
     return this.baNgoaiTru;
   }

   public void setBaNgoaiTru(BenhAnNgoaiTru baNgoaiTru)
   {
     this.baNgoaiTru = baNgoaiTru;
   }

   public String getTuNgay()
   {
     return this.tuNgay;
   }

   public void setTuNgay(String tuNgay)
   {
     this.tuNgay = tuNgay;
   }

   public String getDenNgay()
   {
     return this.denNgay;
   }

   public void setDenNgay(String denNgay)
   {
     this.denNgay = denNgay;
   }

   public String getNgay()
   {
     return this.ngay;
   }

   public void setNgay(String ngay)
   {
     this.ngay = ngay;
   }

   public String getDienbienbenh()
   {
     return this.dienbienbenh;
   }

   public void setDienbienbenh(String dienbienbenh)
   {
     this.dienbienbenh = dienbienbenh;
   }

   public String getYlenh()
   {
     return this.ylenh;
   }

   public void setYlenh(String ylenh)
   {
     this.ylenh = ylenh;
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

   public List<CtBenhAnNgoaiTru> getListCtBANT()
   {
     return this.listCtBANT;
   }

   public void setListCtBANT(List<CtBenhAnNgoaiTru> listCtBANT)
   {
     this.listCtBANT = listCtBANT;
   }

   public CtBenhAnNgoaiTru getCtbantSelection()
   {
     return this.ctbantSelection;
   }

   public void setCtbantSelection(CtBenhAnNgoaiTru ctbantSelection)
   {
     this.ctbantSelection = ctbantSelection;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.LapBANgoaiTruAction

 * JD-Core Version:    0.7.0.1

 */