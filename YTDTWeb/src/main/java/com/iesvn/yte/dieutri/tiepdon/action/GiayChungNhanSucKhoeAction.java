 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.GiayCnSucKhoeDelegate;
 import com.iesvn.yte.dieutri.delegate.ThamKhamDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.GiayCnSucKhoe;
 import com.iesvn.yte.dieutri.entity.ThamKham;
 import com.iesvn.yte.dieutri.entity.TiepDon;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmNgheNghiep;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.PrintStream;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.text.SimpleDateFormat;
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
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.CONVERSATION)
 @Name("B121_6_Giaychungnhansuckhoe")
 @Synchronized(timeout=6000000L)
 public class GiayChungNhanSucKhoeAction
   implements Serializable
 {
   private static String FORMAT_DATE = "dd/MM/yyyy";
   private static String FORMAT_DATE_TIME = "dd/MM/yyyy HH";
   private static final long serialVersionUID = 10L;
   private int stt;
   private String sMaPhieuKVV;
   private String ngaySinh = "";
   private String gioi = "";
   private GiayCnSucKhoe objGiayCnSucKhoe = new GiayCnSucKhoe();
   private String sShowDel = "";
   private String sShowPrint = "";
   private Integer iMaSoBacSi;
   private String sMaBacSi;
   private static Logger log = Logger.getLogger(GiayChungNhanSucKhoeAction.class);
   @In(required=false)
   @Out(required=false)
   private String maBanKhamOut;
   @In(required=false)
   @Out(required=false)
   private String maTiepDonOut;
   @In(required=false)
   @Out(required=false)
   private String goToGiayChungNhanSucKhoe;
   @Out(required=false)
   private String bacSiKCB;
   private BenhNhan benhNhan;
   private ThamKham thamkham;

   public void resetValue() {}

   @Begin(nested=true)
   @Factory("goToGiayChungNhanSucKhoe")
   public void init()
     throws Exception
   {
     log.info("***Starting init ***");
     try
     {
       ThamKhamDelegate tkDelegate = ThamKhamDelegate.getInstance();

       this.thamkham = tkDelegate.findByBanKhamVaMaTiepDon(this.maBanKhamOut, this.maTiepDonOut);
       if (this.thamkham != null)
       {
         this.benhNhan = this.thamkham.getTiepdonMa().getBenhnhanMa();
         this.objGiayCnSucKhoe = GiayCnSucKhoeDelegate.getInstance().findByMaThamKham(this.thamkham.getThamkhamMa());
         if (this.objGiayCnSucKhoe != null)
         {
           this.sMaPhieuKVV = this.objGiayCnSucKhoe.getGcnskMa().toString();

           this.sShowDel = "true";
           this.sShowPrint = "true";
           this.iMaSoBacSi = this.objGiayCnSucKhoe.getGcnskBacsikl(Boolean.valueOf(true)).getDtdmnhanvienMaso();
           this.sMaBacSi = this.objGiayCnSucKhoe.getGcnskBacsikl(Boolean.valueOf(true)).getDtdmnhanvienMa();
         }
         else
         {
           this.objGiayCnSucKhoe = new GiayCnSucKhoe();
           this.sMaPhieuKVV = "";
           this.sShowDel = "false";
           this.sShowPrint = "false";
           this.iMaSoBacSi = this.thamkham.getThamkhamBacsi(true).getDtdmnhanvienMaso();
           this.sMaBacSi = this.thamkham.getThamkhamBacsi(true).getDtdmnhanvienMa();
         }
       }
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
       log.info("***init Exception = " + e);
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
       GiayCnSucKhoeDelegate GiayCnSucKhoeDel = GiayCnSucKhoeDelegate.getInstance();

       this.objGiayCnSucKhoe.setGcnskThamkham(this.thamkham);
       this.objGiayCnSucKhoe.getGcnskBacsikl(Boolean.valueOf(true)).setDtdmnhanvienMaso(getMaSoBacSi());
       GiayCnSucKhoeDel.capNhatGiayCnSucKhoe(this.objGiayCnSucKhoe);
       FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS, new Object[0]);
       log.info("*****CAP NHAT THANH CONG  GiayCnSucKhoeAction *****");
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

   public void huyPhieu()
   {
     log.info("***** start  huyPhieu() *****");
     if ((this.sMaPhieuKVV == null) || (this.sMaPhieuKVV.equals(""))) {
       return;
     }
     GiayCnSucKhoeDelegate GiayCnSucKhoeDel = GiayCnSucKhoeDelegate.getInstance();
     GiayCnSucKhoe obj = GiayCnSucKhoeDel.find(this.objGiayCnSucKhoe.getGcnskMa());
     if (obj == null) {
       return;
     }
     GiayCnSucKhoeDel.remove(obj);
     FacesMessages.instance().add(IConstantsRes.HUY_PHIEU_THANH_CONG, new Object[] { this.sMaPhieuKVV });
     log.info("***** XOA THANH CONG *****");
     this.objGiayCnSucKhoe = new GiayCnSucKhoe();
     this.sMaPhieuKVV = "";
     this.sShowDel = "false";
     this.sShowPrint = "false";
     this.iMaSoBacSi = this.thamkham.getThamkhamBacsi(true).getDtdmnhanvienMaso();
     this.sMaBacSi = this.thamkham.getThamkhamBacsi(true).getDtdmnhanvienMa();
     log.info("***** end  huyPhieu() *****");
   }

   public String quayLai()
     throws Exception
   {
     log.info("*****Begin quayLai() *****");
     this.goToGiayChungNhanSucKhoe = null;
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
     this.reportTypeTD = "giaychungnhansuckhoe";
     log.info("Vao Method XuatReport giaychungnhansuckhoe");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/giaychungnhansuckhoe.jrxml";
       String sub1Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/giaychungnhansuckhoe_sub1.jrxml";
       String sub2Template = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "tiepdon/giaychungnhansuckhoe_sub2.jrxml";
       log.info("da thay file giaychungnhansuckhoe");
       log.info("da thay file giaychungnhansuckhoe_sub1");
       log.info("da thay file giaychungnhansuckhoe_sub2");

       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       JasperReport sub1Report = JasperCompileManager.compileReport(sub1Template);
       JasperReport sub2Report = JasperCompileManager.compileReport(sub2Template);

       log.info("----create JasperReport");
       log.info("----create JasperReport_Sub1");
       log.info("----create JasperReport_Sub2");

       Map<String, Object> params = new HashMap();

       params.put("sub1", sub1Report);
       params.put("sub2", sub2Report);

       params.put("SOYTE", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("DONVI", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);

       params.put("HOTEN", this.benhNhan.getBenhnhanHoten());
       Date nsDate = this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNgaysinh();
       SimpleDateFormat sdf = new SimpleDateFormat(Utils.FORMAT_DATE);
       if (nsDate != null) {
         params.put("NGAYSINH", sdf.format(nsDate));
       } else {
         params.put("NGAYSINH", this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh());
       }
       params.put("GIOI", this.benhNhan.getDmgtMaso(true).getDmgtTen());

       String diachistr = "";
       if (this.thamkham.getTiepdonMa().getBenhnhanMa(true).getBenhnhanDiachi() != null) {
         diachistr = diachistr + this.thamkham.getTiepdonMa(true).getBenhnhanMa().getBenhnhanDiachi() + ",";
       }
       if (this.thamkham.getTiepdonMa().getBenhnhanMa(true).getXaMa(true).getDmxaTen() != null) {
         diachistr = diachistr + this.thamkham.getTiepdonMa(true).getBenhnhanMa().getXaMa(true).getDmxaTen() + ",";
       }
       if (this.thamkham.getTiepdonMa().getBenhnhanMa(true).getHuyenMa(true).getDmhuyenTen() != null) {
         diachistr = diachistr + this.thamkham.getTiepdonMa(true).getBenhnhanMa().getHuyenMa(true).getDmhuyenTen() + ",";
       }
       if (this.thamkham.getTiepdonMa().getBenhnhanMa(true).getTinhMa(true).getDmtinhTen() != null) {
         diachistr = diachistr + this.thamkham.getTiepdonMa(true).getBenhnhanMa().getTinhMa(true).getDmtinhTen();
       }
       params.put("DIACHI", diachistr);

       params.put("BIDANH", this.objGiayCnSucKhoe.getGcnskBidanh() != null ? this.objGiayCnSucKhoe.getGcnskBidanh() : "");
       params.put("NGHENGHIEP", this.benhNhan.getBenhnhanNghe(true).getDmnghenghiepTen() != null ? this.benhNhan.getBenhnhanNghe(true).getDmnghenghiepTen() : "");
       params.put("COQUANCTAC", this.thamkham.getTiepdonMa(true).getTiepdonMacoquan() != null ? this.thamkham.getTiepdonMa(true).getTiepdonMacoquan() : "");
       params.put("QUEQUAN", this.objGiayCnSucKhoe.getGcnskQuequan() != null ? this.objGiayCnSucKhoe.getGcnskQuequan() : "");
       params.put("TIENSUBANTHAN", this.objGiayCnSucKhoe.getGcnskTiensubanthan() != null ? this.objGiayCnSucKhoe.getGcnskTiensubanthan() : "");
       if (this.thamkham.getTiepdonMa(true).getTiepdonChieucao() != null) {
         params.put("CHIEUCAO", this.thamkham.getTiepdonMa(true).getTiepdonChieucao());
       }
       if (this.thamkham.getTiepdonMa(true).getTiepdonTrluong() != null) {
         params.put("CANNANG", this.thamkham.getTiepdonMa(true).getTiepdonTrluong());
       }
       if (this.objGiayCnSucKhoe.getGcnskVongnguctb() != null) {
         params.put("VONGNGUC", this.objGiayCnSucKhoe.getGcnskVongnguctb());
       }
       if (this.objGiayCnSucKhoe.getGcnskLucboptaythuan() != null) {
         params.put("LUCBOPTAYTHUAN", this.objGiayCnSucKhoe.getGcnskLucboptaythuan());
       }
       if (this.objGiayCnSucKhoe.getGcnskLucboptaykhongthuan() != null) {
         params.put("LUCBOPTAYKHONGTHUAN", this.objGiayCnSucKhoe.getGcnskLucboptaykhongthuan());
       }
       params.put("MATPHAIKHONGKINH", this.objGiayCnSucKhoe.getGcnskMatphaikhongkieng() != null ? this.objGiayCnSucKhoe.getGcnskMatphaikhongkieng() : "");
       params.put("MATPHAICOKINH", this.objGiayCnSucKhoe.getGcnskMatphaicokieng() != null ? this.objGiayCnSucKhoe.getGcnskMatphaicokieng() : "");
       params.put("MATTRAIKHONGKINH", this.objGiayCnSucKhoe.getGcnskMattraikhongkieng() != null ? this.objGiayCnSucKhoe.getGcnskMattraikhongkieng() : "");
       params.put("MATTRAICOKINH", this.objGiayCnSucKhoe.getGcnskMattraicokieng() != null ? this.objGiayCnSucKhoe.getGcnskMattraicokieng() : "");
       params.put("KINHLOAI", this.objGiayCnSucKhoe.getGcnskKiengloai() != null ? this.objGiayCnSucKhoe.getGcnskKiengloai() : "");
       params.put("SO", this.objGiayCnSucKhoe.getGcnskSo() != null ? this.objGiayCnSucKhoe.getGcnskSo() : "");
       params.put("SACGIAC", this.objGiayCnSucKhoe.getGcnskSacgiac() != null ? this.objGiayCnSucKhoe.getGcnskSacgiac() : "");
       params.put("BENHOMAT", this.objGiayCnSucKhoe.getGcnskBenhomat() != null ? this.objGiayCnSucKhoe.getGcnskBenhomat() : "");
       if (this.objGiayCnSucKhoe.getGcnskTaiphainghenoibt() != null) {
         params.put("TAIPHAINGHENOITHUONG", this.objGiayCnSucKhoe.getGcnskTaiphainghenoibt());
       }
       if (this.objGiayCnSucKhoe.getGcnskTaiphainghenoitham() != null) {
         params.put("TAIPHAINGHENOITHAM", this.objGiayCnSucKhoe.getGcnskTaiphainghenoitham());
       }
       if (this.objGiayCnSucKhoe.getGcnskTaitrainghenoitham() != null) {
         params.put("TAITRAINGHENOITHAM", this.objGiayCnSucKhoe.getGcnskTaitrainghenoitham());
       }
       if (this.objGiayCnSucKhoe.getGcnskTaitrainghenoibt() != null) {
         params.put("TAITRAINGHENOITHUONG", this.objGiayCnSucKhoe.getGcnskTaitrainghenoibt());
       }
       if (this.objGiayCnSucKhoe.getGcnskLuckeothan() != null) {
         params.put("LUCKEO", this.objGiayCnSucKhoe.getGcnskLuckeothan());
       }
       params.put("BENHTAI", this.objGiayCnSucKhoe.getGcnskBenhtai() != null ? this.objGiayCnSucKhoe.getGcnskBenhtai() : "");
       params.put("BENHMUI", this.objGiayCnSucKhoe.getGcnskBenhmui() != null ? this.objGiayCnSucKhoe.getGcnskBenhmui() : "");
       params.put("BENHHONG", this.objGiayCnSucKhoe.getGcnskBenhhong() != null ? this.objGiayCnSucKhoe.getGcnskBenhhong() : "");
       params.put("HAMTREN", this.objGiayCnSucKhoe.getGcnskHamtren() != null ? this.objGiayCnSucKhoe.getGcnskHamtren() : "");
       params.put("HAMDUOI", this.objGiayCnSucKhoe.getGcnskHamduoi() != null ? this.objGiayCnSucKhoe.getGcnskHamduoi() : "");
       params.put("DONGKINH", this.objGiayCnSucKhoe.getGcnskDongkinh() != null ? this.objGiayCnSucKhoe.getGcnskDongkinh() : "");
       params.put("TELIET", this.objGiayCnSucKhoe.getGcnskTeliet() != null ? this.objGiayCnSucKhoe.getGcnskTeliet() : "");
       params.put("PXTAY", this.objGiayCnSucKhoe.getGcnskPxtay() != null ? this.objGiayCnSucKhoe.getGcnskPxtay() : "");
       params.put("PXCHAN", this.objGiayCnSucKhoe.getGcnskPxchan() != null ? this.objGiayCnSucKhoe.getGcnskPxchan() : "");
       params.put("BENHTHANKINH", this.objGiayCnSucKhoe.getGcnskBenhthankinh() != null ? this.objGiayCnSucKhoe.getGcnskBenhthankinh() : "");
       params.put("BENHTAMTHAN", this.objGiayCnSucKhoe.getGcnskBenhtamthan() != null ? this.objGiayCnSucKhoe.getGcnskBenhtamthan() : "");
       if (this.thamkham.getTiepdonMa(true).getTiepdonHamin() != null) {
         params.put("HUYETAPMIN", this.thamkham.getTiepdonMa(true).getTiepdonHamin());
       }
       if (this.thamkham.getTiepdonMa(true).getTiepdonHamax() != null) {
         params.put("HUYETAPMAX", this.thamkham.getTiepdonMa(true).getTiepdonHamax());
       }
       if (this.thamkham.getTiepdonMa(true).getTiepdonMach() != null) {
         params.put("MACH", this.thamkham.getTiepdonMa(true).getTiepdonMach());
       }
       params.put("BENHTIM", this.objGiayCnSucKhoe.getGcnskBenhtim() != null ? this.objGiayCnSucKhoe.getGcnskBenhtim() : "");
       params.put("BENHMACHMAU", this.objGiayCnSucKhoe.getGcnskBenhmachmau() != null ? this.objGiayCnSucKhoe.getGcnskBenhmachmau() : "");
       params.put("KHOP", this.objGiayCnSucKhoe.getGcnskKhop() != null ? this.objGiayCnSucKhoe.getGcnskKhop() : "");
       params.put("XUONGCO", this.objGiayCnSucKhoe.getGcnskXuongco() != null ? this.objGiayCnSucKhoe.getGcnskXuongco() : "");
       params.put("HOHAP", this.objGiayCnSucKhoe.getGcnskHohap() != null ? this.objGiayCnSucKhoe.getGcnskHohap() : "");
       params.put("TIEUHOA", this.objGiayCnSucKhoe.getGcnskTieuhoa() != null ? this.objGiayCnSucKhoe.getGcnskTieuhoa() : "");
       params.put("TIETNIEUSD", this.objGiayCnSucKhoe.getGcnskTietnieusd() != null ? this.objGiayCnSucKhoe.getGcnskTietnieusd() : "");
       params.put("BENHNGOAIDA", this.objGiayCnSucKhoe.getGcnskBenhngoaidadlhl() != null ? this.objGiayCnSucKhoe.getGcnskBenhngoaidadlhl() : "");
       params.put("CACBPKHAC", this.objGiayCnSucKhoe.getGcnskCacbophankhac() != null ? this.objGiayCnSucKhoe.getGcnskCacbophankhac() : "");
       params.put("XETNGHIEM", this.objGiayCnSucKhoe.getGcnskXncanlamsang() != null ? this.objGiayCnSucKhoe.getGcnskXncanlamsang() : "");
       params.put("KETLUAN", this.objGiayCnSucKhoe.getGcnskKetluan() != null ? this.objGiayCnSucKhoe.getGcnskKetluan() : "");
       params.put("BACSIKL", this.objGiayCnSucKhoe.getGcnskBacsikl(Boolean.valueOf(true)).getDtdmnhanvienTen() != null ? this.objGiayCnSucKhoe.getGcnskBacsikl(Boolean.valueOf(true)).getDtdmnhanvienTen() : "");

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
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintTD, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "tiepdon/", "pdf", "giaychungnhansuckhoe");
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
     } else if (this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh() != null) {
       this.ngaySinh = this.thamkham.getTiepdonMa().getBenhnhanMa().getBenhnhanNamsinh();
     }
   }

   public void displayInfor()
     throws Exception
   {
     try
     {
       if ((this.sMaPhieuKVV == null) || (this.sMaPhieuKVV.equals(""))) {
         return;
       }
       GiayCnSucKhoeDelegate GiayCnSucKhoeDel = GiayCnSucKhoeDelegate.getInstance();
       this.objGiayCnSucKhoe = new GiayCnSucKhoe();
       List<GiayCnSucKhoe> ls = GiayCnSucKhoeDel.findByGiayCnSucKhoe(getMaPhieuKhamBenhVaoVien());
       if ((ls == null) || (ls.size() == 0))
       {
         FacesMessages.instance().add(IConstantsRes.RPPKBVV_NOT_EXIST, new Object[0]);
         this.sMaPhieuKVV = "";
         this.sShowDel = "false";
         this.sShowPrint = "false";
         return;
       }
       this.sShowDel = "true";
       this.sShowPrint = "true";
       this.objGiayCnSucKhoe = ((GiayCnSucKhoe)ls.get(0));
       if (this.objGiayCnSucKhoe.getGcnskMa() != null) {
         this.sMaPhieuKVV = this.objGiayCnSucKhoe.getGcnskMa().toString();
       } else {
         this.sMaPhieuKVV = "";
       }
       log.info("*****sMaPhieuKVV: " + this.sMaPhieuKVV);
     }
     catch (Exception e)
     {
       System.out.println("error on function displayInfor" + e);
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
     log.info("************************* destroy PhieuKbVaoVien");
   }

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

   public String getGoToGiayChungNhanSucKhoe()
   {
     return this.goToGiayChungNhanSucKhoe;
   }

   public void setGoToGiayChungNhanSucKhoe(String str)
   {
     this.goToGiayChungNhanSucKhoe = str;
   }

   public String getMaPhieuKhamBenhVaoVien()
   {
     return this.sMaPhieuKVV;
   }

   public void setMaPhieuKhamBenhVaoVien(String maGiayChuyenVien)
   {
     this.sMaPhieuKVV = maGiayChuyenVien;
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

   public GiayCnSucKhoe getObjGiayCnSucKhoe()
   {
     return this.objGiayCnSucKhoe;
   }

   public void setObjGiayCnSucKhoe(GiayCnSucKhoe objGiayCnSucKhoe)
   {
     this.objGiayCnSucKhoe = objGiayCnSucKhoe;
   }

   public Integer getMaSoBacSi()
   {
     return this.iMaSoBacSi;
   }

   public void setMaSoBacSi(Integer maSoBacSi)
   {
     this.iMaSoBacSi = maSoBacSi;
   }

   public String getMaBacSi()
   {
     return this.sMaBacSi;
   }

   public void setMaBacSi(String maBacSi)
   {
     this.sMaBacSi = maBacSi;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.GiayChungNhanSucKhoeAction

 * JD-Core Version:    0.7.0.1

 */