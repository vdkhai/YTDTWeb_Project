 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.CtTraKhoDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuTraKhoDelegate;
 import com.iesvn.yte.dieutri.delegate.TonKhoDelegate;
 import com.iesvn.yte.dieutri.entity.CtTraKho;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.PhieuTraKho;
 import com.iesvn.yte.dieutri.entity.TonKho;
 import com.iesvn.yte.dieutri.remove.RemoveUtil;
 import com.iesvn.yte.dieutri.setinfor.SetInforUtil;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.util.IConstantsRes;
 import com.sun.org.apache.commons.beanutils.BeanUtils;
 import java.io.Serializable;
 import java.lang.reflect.InvocationTargetException;
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
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.CONVERSATION)
 @Name("B4114_Phieutrahangkhoaphong")
 @Synchronized(timeout=6000000L)
 public class PhieuTraHangKhoaPhongAction
   implements Serializable
 {
   private static final long serialVersionUID = 6247559999639195297L;
   private static Logger log = Logger.getLogger(PhieuTraHangKhoaPhongAction.class);
   private PhieuTraKho phieuTraKho;
   private String ngayXuat;
   private CtTraKhoExt ctTraKhoExt;
   @DataModel
   private List<CtTraKhoExt> listCtTraKhoExt;
   @DataModelSelection
   private CtTraKhoExt selectedCtTraKhoExt;
   private boolean isUpdate;
   private String nofound;
   private String nosuccess;
   private String noinphieu;
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport;
   @In(required=false)
   @Out(required=false)
   private String ten4112_4114;
   String dmKhoXuat;
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   int index;
   @Out(required=false)
   @In(required=false)
   private String reportPathKC;
   @Out(required=false)
   @In(required=false)
   private String reportTypeKC;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintKC;

   @Begin(join=true)
   public String init(String loaikho, String kho, String formid)
   {
     log.info("---init");
     resetValue();
     if ("KhoNoiTru".equals(loaikho)) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
     } else if ("BHYT".equals(loaikho)) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoBHYT;
     } else if ("KC".equals(loaikho)) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
     }
     this.dmKhoXuat = kho;
     this.ten4112_4114 = formid;
     return "QuanLyKhoChinh_NhapKhoChinh_CacKhoaPhongTraLaiHangTuTuTruc";
   }

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }

   public boolean isUpdate()
   {
     return this.isUpdate;
   }

   public void setUpdate(boolean isUpdate)
   {
     this.isUpdate = isUpdate;
   }

   public String getDmKhoXuat()
   {
     return this.dmKhoXuat;
   }

   public void setDmKhoXuat(String dmKhoXuat)
   {
     this.dmKhoXuat = dmKhoXuat;
   }

   public static long getSerialVersionUID()
   {
     return 6247559999639195297L;
   }

   public String getTen4112_4114()
   {
     return this.ten4112_4114;
   }

   public void setTen4112_4114(String ten4112_4114)
   {
     this.ten4112_4114 = ten4112_4114;
   }

   public String getIsReport()
   {
     return this.isReport;
   }

   public void setIsReport(String isReport)
   {
     this.isReport = isReport;
   }

   public String getResultReportFileName()
   {
     return this.resultReportFileName;
   }

   public void setResultReportFileName(String resultReportFileName)
   {
     this.resultReportFileName = resultReportFileName;
   }

   public String getResultReportName()
   {
     return this.resultReportName;
   }

   public void setResultReportName(String resultReportName)
   {
     this.resultReportName = resultReportName;
   }

   public String getLoaiFileXuat()
   {
     return this.loaiFileXuat;
   }

   public void setLoaiFileXuat(String loaiFileXuat)
   {
     this.loaiFileXuat = loaiFileXuat;
   }

   public String getNofound()
   {
     return this.nofound;
   }

   public void setNofound(String nofound)
   {
     this.nofound = nofound;
   }

   public String getNosuccess()
   {
     return this.nosuccess;
   }

   public void setNosuccess(String nosuccess)
   {
     this.nosuccess = nosuccess;
   }

   public String getNoinphieu()
   {
     return this.noinphieu;
   }

   public void setNoinphieu(String noinphieu)
   {
     this.noinphieu = noinphieu;
   }

   private void resetValue()
   {
     log.info("---resetValue");
     this.ngayXuat = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
     this.phieuTraKho = new PhieuTraKho();
     SetInforUtil.setInforIfNullForPhieuTraKho(this.phieuTraKho);
     this.listCtTraKhoExt = new ArrayList();
     this.ctTraKhoExt = new CtTraKhoExt();
     this.isUpdate = false;
     this.nofound = "false";
     this.nosuccess = "false";
     this.noinphieu = "false";
   }

   public void enter()
     throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException
   {
     log.info("---enter");
     updateTonKho(this.ctTraKhoExt);
     if (this.isUpdate) {
       this.isUpdate = false;
     } else {
       this.listCtTraKhoExt.add(this.ctTraKhoExt);
     }
     this.ctTraKhoExt = new CtTraKhoExt();
   }

   public void delete()
   {
     log.info("---delete");
     this.listCtTraKhoExt.remove(this.selectedCtTraKhoExt);
     this.ctTraKhoExt = new CtTraKhoExt();
   }

   public void select()
   {
     log.info("---delete");
     this.ctTraKhoExt = this.selectedCtTraKhoExt;
     this.isUpdate = true;
   }

   private void updateTonKho(CtTraKhoExt _ctTraKhoExt)
     throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException
   {
     Integer matonkho = _ctTraKhoExt.getTonKho().getTonkhoMa();
     if (matonkho == null) {
       return;
     }
     TonKhoDelegate tkDelegate = TonKhoDelegate.getInstance();
     TonKho tk = tkDelegate.find(matonkho);
     CtTraKho cttk = _ctTraKhoExt.getCtTraKho();
     TonKho tkTra = duplicateTk(tk);
     if (tkTra != null)
     {
       tkTra.setTonkhoXuat(cttk.getCttrakhoSoluong());
       tkTra.setTonkhoNhap(null);
       tkTra.setTonkhoTra(null);
     }
     TonKho tkNhan = duplicateTk(tk);
     if (tkNhan != null)
     {
       tkNhan.setTonkhoNhap(cttk.getCttrakhoSoluong());
       tkNhan.setTonkhoXuat(null);
       tkNhan.setTonkhoTra(null);
     }
     _ctTraKhoExt.setTonKhoTra(tkTra);
     _ctTraKhoExt.setTonKhoNhan(tkNhan);
     cttk.setDmnguonkinhphiMaso(tk.getDmnguonkinhphiMaso());
     cttk.setDmquocgiaMaso(tk.getDmquocgiaMaso());
     cttk.setDmthuocMaso(tk.getDmthuocMaso());
     cttk.setDmnhasanxuatMaso(tk.getDmnhasanxuatMaso());
     cttk.setDmnctMaso(tk.getDmnctMaso());
     cttk.setCttrakhoDongia(tk.getTonkhoDongia());
     cttk.setCttrakhoDongiaban(tk.getTonkhoDongiaban());
     cttk.setCttrakhoLo(tk.getTonkhoLo());
     cttk.setCttrakhoMalk(tk.getTonkhoMalienket());
     cttk.setCttrakhoNamhandung(tk.getTonkhoNamhandung());
     cttk.setCttrakhoNamnhap(tk.getTonkhoNamnhap());
     cttk.setCttrakhoNgaygiocn(new Date());
     cttk.setCttrakhoNgayhandung(tk.getTonkhoNgayhandung());
     cttk.setCttrakhoThanghandung(tk.getTonkhoThanghandung());
     cttk.setPhieutrakhoMa(this.phieuTraKho);
     cttk.setCttrakhoNgaygiocn(new Date());
   }

   private TonKho duplicateTk(TonKho tk)
     throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException
   {
     if (tk != null)
     {
       TonKho temp = (TonKho)BeanUtils.cloneBean(tk);
       temp.setTonkhoMa(null);
       temp.setTonkhoNgaygiocn(new Date());























       return temp;
     }
     return null;
   }

   public void displayPhieuTraKho()
     throws Exception
   {
     log.info("---displayPhieuTraKho");
     this.ctTraKhoExt = new CtTraKhoExt();
     String maPhieuTra = this.phieuTraKho.getPhieutrakhoMa();
     if ((maPhieuTra != null) && (!maPhieuTra.equals("")))
     {
       PhieuTraKhoDelegate delegate = PhieuTraKhoDelegate.getInstance();
       PhieuTraKho ptk_tmp = delegate.findByPhieutrakhoMa(maPhieuTra);
       if (ptk_tmp != null)
       {
         this.phieuTraKho = ptk_tmp;
         SetInforUtil.setInforIfNullForPhieuTraKho(this.phieuTraKho);
         this.ngayXuat = new SimpleDateFormat("dd/MM/yyyy").format(this.phieuTraKho.getPhieutrakhoNgay());
         CtTraKhoDelegate delegateCt = CtTraKhoDelegate.getInstance();
         for (CtTraKho obj : delegateCt.findByphieutrakhoMa(this.phieuTraKho.getPhieutrakhoMa()))
         {
           CtTraKhoExt ct = new CtTraKhoExt();
           ct.setCtTraKho(obj);
           this.listCtTraKhoExt.add(ct);
         }
       }
       else
       {
         this.nofound = "true";
       }
     }
   }

   public void ghiNhan()
     throws Exception
   {
     log.info("---ghiNhan");
     if (this.listCtTraKhoExt.size() > 0)
     {
       List<CtTraKho> list = new ArrayList();
       List<TonKho> listTonKhoTra = new ArrayList();
       List<TonKho> listTonKhoNhan = new ArrayList();
       for (CtTraKhoExt obj : this.listCtTraKhoExt)
       {
         list.add(obj.getCtTraKho());
         listTonKhoTra.add(obj.getTonKhoTra());
         listTonKhoNhan.add(obj.getTonKhoNhan());
       }
       this.phieuTraKho.setPhieutrakhoNgaygiocn(new Date());
       if (!this.ngayXuat.equals("")) {
         this.phieuTraKho.setPhieutrakhoNgay(new SimpleDateFormat("dd/MM/yyyy").parse(this.ngayXuat));
       }
       RemoveUtil.removeIfNullForPhieuTraKho(this.phieuTraKho);
       PhieuTraKhoDelegate delegate = PhieuTraKhoDelegate.getInstance();
       String result = delegate.createPhieuTra(this.phieuTraKho, list, listTonKhoNhan, listTonKhoTra);
       if (result.equals(""))
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THATBAI, new Object[0]);
       }
       else
       {
         this.phieuTraKho.setPhieutrakhoMa(result);
         SetInforUtil.setInforIfNullForPhieuTraKho(this.phieuTraKho);
         FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_THANHCONG, new Object[] { result });
       }
     }
     else
     {
       this.nosuccess = "true";
       FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_DMT_NULL, new Object[0]);
     }
   }

   public void nhapMoi()
     throws Exception
   {
     log.info("---nhapMoi");
     resetValue();
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B4160_Chonmenuxuattaptin";
   }

   public PhieuTraHangKhoaPhongAction()
   {
     this.isUpdate = false;







     this.isReport = "false";






     this.dmKhoXuat = "";















































































































































































































































































































     this.index = 0;





     this.reportPathKC = null;



     this.reportTypeKC = null;



     this.jasperPrintKC = null;
   }

   public void XuatReport()
   {
     this.reportTypeKC = "D03_phieuxuatkho";
     log.info("Vao Method XuatReport D03_phieuxuatkho");
     String baocao1 = null;
     Date currentDate = new Date();
     if (!this.phieuTraKho.getPhieutrakhoMa().equals("")) {
       try
       {
         PhieuTraKhoDelegate ptkWS = PhieuTraKhoDelegate.getInstance();
         PhieuTraKho px = ptkWS.find(this.phieuTraKho.getPhieutrakhoMa());


         String pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "duocpham/phieutrakho_01.jrxml";
         log.info(String.format("-----pathTemplate: %s", new Object[] { pathTemplate }));

         JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);


         Map<String, Object> params = new HashMap();
         params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
         log.info(String.format("-----tenSo: %s", new Object[] { params.get("tenSo") }));
         params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
         log.info(String.format("-----tenDonVi: %s", new Object[] { params.get("tenDonVi") }));
         params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

         params.put("DONVI", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);

         log.info(String.format("-----dienThoaiDonVi: %s", new Object[] { params.get("dienThoaiDonVi") }));
         Calendar cal = Calendar.getInstance();
         cal.setTime(px.getPhieutrakhoNgay());
         if (cal != null)
         {
           log.info(String.format("-----ngay lap: %s", new Object[] { cal.getTime() }));
           params.put("ngayHt", "" + cal.get(5));
           params.put("thangHt", "" + (cal.get(2) + 1));
           params.put("namHt", "" + cal.get(1));
         }
         else
         {
           log.info("-----ngay lap is null");
           params.put("ngayHt", "");
           params.put("thangHt", "");
           params.put("namHt", "");
         }
         SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
         String ngayGioHt = df.format(Calendar.getInstance().getTime());
         log.info(String.format("-----ngay gio hien tai: %s", new Object[] { ngayGioHt }));
         params.put("gioHt", ngayGioHt);

         params.put("pxMa", px.getPhieutrakhoMa());
         if (px.getDmkhoaNhan() != null) {
           params.put("khoaNhan", px.getDmkhoaNhan().getDmkhoaTen());
         } else {
           params.put("khoaNhan", "");
         }
         log.info(String.format("-----khoaNhan: %s", new Object[] { params.get("khoaNhan") }));
         if (px.getDmkhoaTra() != null) {
           params.put("khoaXuat", px.getDmkhoaTra().getDmkhoaTen());
         } else {
           params.put("khoaXuat", "");
         }
         log.info(String.format("-----khoaXuat: %s", new Object[] { params.get("khoaXuat") }));

         params.put("tongTien", px.getPhieutrakhoThanhtien());
         log.info(String.format("-----tongTien: %s", new Object[] { params.get("tongTien") }));
         params.put("nhanvienCn", px.getDtdmnhanvienCn().getDtdmnhanvienMa());


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
         this.jasperPrintKC = JasperFillManager.fillReport(jasperReport, params, conn);
         baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintKC, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "duocpham/", "pdf", "D03_phieuxuatkho");
         this.reportPathKC = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
         log.info("duong dan file xuat report :" + baocao1);
         log.info("duong dan -------------------- :" + this.reportPathKC);
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
     }
     log.info("Thoat Method XuatReport");
   }

   public String troVe()
   {
     try
     {
       log.info("***** troVe()");
       return "B4114_Phieutrahangkhoaphong";
     }
     catch (Exception e)
     {
       log.info("***** End exception = " + e);
     }
     return null;
   }

   public class CtTraKhoExt
   {
     private CtTraKho ctTraKho;
     private TonKho tonKho;
     private TonKho tonKhoTra;
     private TonKho tonKhoNhan;
     private Double thanhTien;

     public Double getThanhTien()
     {
       return this.thanhTien;
     }

     public void setThanhTien(Double thanhTien)
     {
       this.thanhTien = thanhTien;
     }

     public CtTraKhoExt()
     {
       this.ctTraKho = new CtTraKho();
       this.tonKho = new TonKho();
       SetInforUtil.setInforIfNullForTonKho(this.tonKho);
       if (this.tonKho.getDmthuocMaso().getDmdonvitinhMaso() == null) {
         this.tonKho.getDmthuocMaso().setDmdonvitinhMaso(new DmDonViTinh());
       }
       this.thanhTien = new Double(0.0D);
     }

     public CtTraKho getCtTraKho()
     {
       return this.ctTraKho;
     }

     public void setCtTraKho(CtTraKho ctTraKho)
     {
       this.ctTraKho = ctTraKho;
     }

     public TonKho getTonKho()
     {
       return this.tonKho;
     }

     public void setTonKho(TonKho tonKho)
     {
       this.tonKho = tonKho;
     }

     public TonKho getTonKhoTra()
     {
       return this.tonKhoTra;
     }

     public void setTonKhoTra(TonKho tonKhoTra)
     {
       this.tonKhoTra = tonKhoTra;
     }

     public TonKho getTonKhoNhan()
     {
       return this.tonKhoNhan;
     }

     public void setTonKhoNhan(TonKho tonKhoNhan)
     {
       this.tonKhoNhan = tonKhoNhan;
     }
   }

   public String getNgayXuat()
   {
     return this.ngayXuat;
   }

   public void setNgayXuat(String ngayXuat)
   {
     this.ngayXuat = ngayXuat;
   }

   public CtTraKhoExt getCtTraKhoExt()
   {
     return this.ctTraKhoExt;
   }

   public void setCtTraKhoExt(CtTraKhoExt ctTraKhoExt)
   {
     this.ctTraKhoExt = ctTraKhoExt;
   }

   public List<CtTraKhoExt> getListCtTraKhoExt()
   {
     return this.listCtTraKhoExt;
   }

   public void setListCtTraKhoExt(List<CtTraKhoExt> listCtTraKhoExt)
   {
     this.listCtTraKhoExt = listCtTraKhoExt;
   }

   public CtTraKhoExt getSelectedCtTraKhoExt()
   {
     return this.selectedCtTraKhoExt;
   }

   public void setSelectedCtTraKhoExt(CtTraKhoExt selectedCtTraKhoExt)
   {
     this.selectedCtTraKhoExt = selectedCtTraKhoExt;
   }

   public PhieuTraKho getPhieuTraKho()
   {
     return this.phieuTraKho;
   }

   public void setPhieuTraKho(PhieuTraKho phieuTraKho)
   {
     this.phieuTraKho = phieuTraKho;
   }

   @End
   public void endConversation() {}
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.PhieuTraHangKhoaPhongAction

 * JD-Core Version:    0.7.0.1

 */