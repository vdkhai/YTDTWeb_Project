 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.ClsMoDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaGiayTomTatDelegate;
 import com.iesvn.yte.dieutri.delegate.ThuocNoiTruDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.ClsMo;
 import com.iesvn.yte.dieutri.entity.DtDmCls;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaGiayTomTat;
 import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
 import com.iesvn.yte.entity.DmBenhIcd;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmThuoc;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
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
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.CONVERSATION)
 @Name("B233_Tomtatbenhan")
 @Synchronized(timeout=6000000L)
 public class TomTatBenhAnAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static Logger logger = Logger.getLogger(TomTatBenhAnAction.class);
   private String soVaoVien;
   private String ngayHt;
   private HsbaGiayTomTat hsbaTt;
   private Hsba hsba;
   private String maGiay;
   private String ngaySinh;
   private String ngayYc;
   private String gioVaoVien;
   private String ngayVaoVien;
   private String gioRaVien;
   private String ngayRaVien;
   @In(required=false)
   @Out(required=false)
   private String duongdanStrDT = null;
   @Out(required=false)
   @In(required=false)
   private String loaiBCDT = null;
   @In(required=false)
   @Out(required=false)
   JasperPrint jasperPrintDT = null;
   private int index = 0;
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport = "false";
   private Boolean hienThiInPhieu = Boolean.valueOf(false);
   @In(scope=ScopeType.PAGE, required=false)
   @Out(scope=ScopeType.PAGE, required=false)
   private String strTomtatBA;

   @Begin(join=true)
   public String init()
   {
     logger.info("-----init()-----");
     reset();
     return "DieuTri_LapVanBanTheoMau_TomTatBenhAn";
   }

   @End
   public void endFunc() {}

   public void reset()
   {
     logger.info("-----reset()-----");
     this.ngayHt = Utils.getCurrentDate();
     this.hsbaTt = new HsbaGiayTomTat();
     this.hsba = new Hsba();
     this.ngaySinh = "";
     this.ngayYc = "";
     this.gioVaoVien = "";
     this.ngayVaoVien = "";
     this.ngayRaVien = "";
     this.gioRaVien = "";
     this.soVaoVien = "";
     this.maGiay = "";
     this.hienThiInPhieu = Boolean.valueOf(false);
     this.strTomtatBA = "";
   }

   public void loadHsba()
   {
     logger.info("-----loadHsba()-----");
     if (!this.soVaoVien.equals(""))
     {
       HsbaDelegate hsbaDelegate = HsbaDelegate.getInstance();
       try
       {
         this.hsba = hsbaDelegate.find(this.soVaoVien);
         if (this.hsba != null)
         {
           this.soVaoVien = this.hsba.getHsbaSovaovien();
           Date dNgaySinh = this.hsba.getBenhnhanMa(true).getBenhnhanNgaysinh();
           if (dNgaySinh != null)
           {
             SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
             this.ngaySinh = df.format(dNgaySinh);
           }
           Date dNgayVv = this.hsba.getHsbaNgaygiovaov();
           if (dNgayVv != null)
           {
             SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
             this.ngayVaoVien = df.format(dNgayVv);
             this.gioVaoVien = Utils.getGioPhut(dNgayVv);
           }
           Date dNgayRv = this.hsba.getHsbaNgaygiorav();
           if (dNgayRv != null)
           {
             SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
             this.ngayRaVien = df.format(dNgayRv);
             this.gioRaVien = Utils.getGioPhut(dNgayRv);
           }
           List<ClsMo> listClsMo = ClsMoDelegate.getInstance().findBySoVaoVien(this.soVaoVien);


           List<ThuocNoiTru> listTnt = ThuocNoiTruDelegate.getInstance().findBySoVaoVien(this.soVaoVien);

           String strXnCls = "";
           if (listClsMo != null) {
             for (ClsMo eachClsMo : listClsMo)
             {
               String maCls = eachClsMo.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa();
               if ((maCls.equals("XN")) || (maCls.equals("CD")) || (maCls.equals("TT")) || (maCls.equals("PT"))) {
                 strXnCls = strXnCls + (strXnCls.trim().length() > 0 ? ", " + eachClsMo.getClsmoMahang().getDtdmclsbgDiengiai() : eachClsMo.getClsmoMahang().getDtdmclsbgDiengiai());
               }
             }
           }
           String strQtdt = "";
           String tenDvt;
           if (listTnt != null)
           {
             tenDvt = "";
             for (ThuocNoiTru eachTnt : listTnt)
             {
               tenDvt = eachTnt.getThuocnoitruMathuoc().getDmdonvitinhMaso() == null ? "" : eachTnt.getThuocnoitruMathuoc().getDmdonvitinhMaso().getDmdonvitinhTen();
               strQtdt = strQtdt + (strQtdt.trim().length() > 0 ? ", " + eachTnt.getThuocnoitruMathuoc().getDmthuocTen() + "(" + eachTnt.getThuocnoitruSoluong() + " " + tenDvt + ")" : new StringBuilder().append(eachTnt.getThuocnoitruMathuoc().getDmthuocTen()).append("(").append(eachTnt.getThuocnoitruSoluong()).append(" ").append(tenDvt).append(")").toString());
             }
           }
           if (listClsMo != null) {
             for (ClsMo eachClsMo : listClsMo)
             {
               String maCls = eachClsMo.getClsmoMahang().getDtdmclsbgMaloai().getDtdmclsMa();
               if (maCls.equals("MA")) {
                 strQtdt = strQtdt + (strQtdt.trim().length() > 0 ? ", " + eachClsMo.getClsmoMahang().getDtdmclsbgDiengiai() : eachClsMo.getClsmoMahang().getDtdmclsbgDiengiai());
               }
             }
           }
           HsbaGiayTomTat gtt_tmp = HsbaGiayTomTatDelegate.getInstance().findBySovaovien(this.hsba.getHsbaSovaovien());
           if (gtt_tmp != null) {
             this.hsbaTt = gtt_tmp;
           }
           this.hsbaTt.setHsbagttXncls(strXnCls);
           this.hsbaTt.setHsbagttQtdt(strQtdt);


           this.maGiay = this.hsbaTt.getHsbagiaytomtatMa();
         }
         else
         {
           FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.soVaoVien });
           this.ngaySinh = "";
           this.ngayYc = "";
           this.gioVaoVien = "";
           this.ngayVaoVien = "";
           this.ngayRaVien = "";
           this.gioRaVien = "";
           this.hsbaTt = new HsbaGiayTomTat();
         }
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.soVaoVien });
         e.printStackTrace();
         this.ngaySinh = "";
         this.ngayYc = "";
         this.gioVaoVien = "";
         this.ngayVaoVien = "";
         this.ngayRaVien = "";
         this.gioRaVien = "";
         this.hsbaTt = new HsbaGiayTomTat();
       }
     }
   }

   public void loadHsbaTomTat()
   {
     HsbaGiayTomTatDelegate hsbattDelegate = HsbaGiayTomTatDelegate.getInstance();
     try
     {
       this.hsbaTt = hsbattDelegate.findByMa(this.maGiay);
       if (this.hsbaTt == null)
       {
         FacesMessages.instance().add(IConstantsRes.no_found, new Object[] { this.maGiay });
         reset();
       }
       else
       {
         this.maGiay = this.hsbaTt.getHsbagiaytomtatMa();
         this.hsba = this.hsbaTt.getHsbaSovaovien(true);
         this.soVaoVien = this.hsba.getHsbaSovaovien();
         Date dNgaySinh = this.hsba.getBenhnhanMa(true).getBenhnhanNgaysinh();
         if (dNgaySinh != null)
         {
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           this.ngaySinh = df.format(dNgaySinh);
         }
         Date dNgayVv = this.hsba.getHsbaNgaygiovaov();
         if (dNgayVv != null)
         {
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           this.ngayVaoVien = df.format(dNgayVv);
           this.gioVaoVien = Utils.getGioPhut(dNgayVv);
         }
         Date dNgayRv = this.hsba.getHsbaNgaygiorav();
         if (dNgayRv != null)
         {
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           this.ngayRaVien = df.format(dNgayRv);
           this.gioRaVien = Utils.getGioPhut(dNgayRv);
         }
         Date dNgayYc = this.hsbaTt.getHsbagttNgayyc();
         if (dNgayYc != null)
         {
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           this.ngayYc = df.format(dNgayYc);
         }
         this.hienThiInPhieu = Boolean.valueOf(true);
       }
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.HSBA_NULL, new Object[] { this.maGiay });
       reset();
       e.printStackTrace();
     }
   }

   public void end()
   {
     logger.info("-----end()-----");
     HsbaGiayTomTatDelegate hsbattDelegate = HsbaGiayTomTatDelegate.getInstance();





















     this.hsbaTt.setHsbaSovaovien(this.hsba);
     try
     {
       this.hsbaTt.setHsbagttNgaygiocn(new Date());
       SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       if (!this.ngayYc.equals("")) {
         this.hsbaTt.setHsbagttNgayyc(df.parse(this.ngayYc));
       }
       if (this.maGiay.equals(""))
       {
         this.maGiay = hsbattDelegate.create(this.hsbaTt);
       }
       else
       {
         hsbattDelegate.edit(this.hsbaTt);
         this.maGiay = this.hsbaTt.getHsbagiaytomtatMa();
       }
       FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
       this.hienThiInPhieu = Boolean.valueOf(true);
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
       e.printStackTrace();
     }
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B260_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     HsbaGiayTomTatDelegate hsbattDelegate = HsbaGiayTomTatDelegate.getInstance();
     this.hsbaTt = hsbattDelegate.findByMa(this.maGiay);
     if (this.hsbaTt == null)
     {
       FacesMessages.instance().add(IConstantsRes.no_found, new Object[] { this.maGiay });
       reset();
       return;
     }
     this.loaiBCDT = "Giaytomtatbenhan";
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/Giaytomtatbenhan.jrxml";
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       String khoa = this.hsbaTt.getHsbaSovaovien().getHsbaKhoadangdt(true).getDmkhoaTen();
       String hoTen = this.hsbaTt.getHsbaSovaovien().getBenhnhanMa(true).getBenhnhanHoten();
       String tuoi = this.hsbaTt.getHsbaSovaovien().getBenhnhanMa(true).getBenhnhanTuoi().toString();
       String diaChi = "";
       if (this.hsbaTt.getHsbaSovaovien().getBenhnhanMa() != null)
       {
         diaChi = this.hsbaTt.getHsbaSovaovien().getBenhnhanMa().getBenhnhanDiachi() == null ? "" : this.hsbaTt.getHsbaSovaovien().getBenhnhanMa().getBenhnhanDiachi();
         if (this.hsbaTt.getHsbaSovaovien().getBenhnhanMa().getXaMa() != null) {
           diaChi = diaChi + (this.hsbaTt.getHsbaSovaovien().getBenhnhanMa().getXaMa().getDmxaTen() == null ? "" : new StringBuilder().append(" ").append(this.hsbaTt.getHsbaSovaovien().getBenhnhanMa().getXaMa().getDmxaTen()).toString());
         }
         if (this.hsbaTt.getHsbaSovaovien().getBenhnhanMa().getHuyenMa() != null) {
           diaChi = diaChi + (this.hsbaTt.getHsbaSovaovien().getBenhnhanMa().getHuyenMa().getDmhuyenTen() == null ? "" : new StringBuilder().append(" ").append(this.hsbaTt.getHsbaSovaovien().getBenhnhanMa().getHuyenMa().getDmhuyenTen()).toString());
         }
         if (this.hsbaTt.getHsbaSovaovien().getBenhnhanMa().getTinhMa() != null) {
           diaChi = diaChi + (this.hsbaTt.getHsbaSovaovien().getBenhnhanMa().getTinhMa().getDmtinhTen() == null ? "" : new StringBuilder().append(" ").append(this.hsbaTt.getHsbaSovaovien().getBenhnhanMa().getTinhMa().getDmtinhTen()).toString());
         }
       }
       String gioiTinh = this.hsbaTt.getHsbaSovaovien().getBenhnhanMa(true).getDmgtMaso(true).getDmgtTen();
       String chuanDoan = this.hsbaTt.getHsbaSovaovien().getHsbaMachdoanbd(true).getDmbenhicdTen();
       String soVaoVien = this.hsbaTt.getHsbaSovaovien().getHsbaSovaovien();

       Map<String, Object> params = new HashMap();
       params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);

       params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);

       params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);

       params.put("khoa", khoa);

       params.put("hoTen", hoTen);

       params.put("tuoi", tuoi);

       params.put("diaChi", diaChi);

       params.put("gioiTinh", gioiTinh);

       params.put("chuanDoan", chuanDoan);

       params.put("soVaoVien", soVaoVien);

       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);

       params.put("hsbatt_maso", this.hsbaTt.getHsbagiaytomtatMaso());
       logger.info("================= ");
       Class.forName("com.mysql.jdbc.Driver");
       logger.info("da thay driver mysql");
       Connection conn = null;
       try
       {
         conn = DriverManager.getConnection(IConstantsRes.DATABASE_URL, IConstantsRes.DATABASE_USER, IConstantsRes.DATABASE_PASS);
       }
       catch (Exception e)
       {
         logger.info(e.getMessage());
       }
       this.jasperPrintDT = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "Giaytomtatbenhan");
       this.duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       logger.info("duong dan file xuat report :" + baocao1);
       logger.info("duong dan -------------------- :" + this.duongdanStrDT);
       this.index += 1;
       logger.info("Index :" + getIndex());
       if (conn != null) {
         conn.close();
       }
     }
     catch (Exception e)
     {
       logger.info("ERROR Method XuatReport!!!");
       e.printStackTrace();
     }
     logger.info("Thoat Method XuatReport");
   }

   public String getNgayHt()
   {
     return this.ngayHt;
   }

   public void setNgayHt(String ngayHt)
   {
     this.ngayHt = ngayHt;
   }

   public HsbaGiayTomTat getHsbaTt()
   {
     return this.hsbaTt;
   }

   public void setHsbaTt(HsbaGiayTomTat hsbaTt)
   {
     this.hsbaTt = hsbaTt;
   }

   public Hsba getHsba()
   {
     return this.hsba;
   }

   public void setHsba(Hsba hsba)
   {
     this.hsba = hsba;
   }

   public String getMaGiay()
   {
     return this.maGiay;
   }

   public void setMaGiay(String maGiay)
   {
     this.maGiay = maGiay;
   }

   public String getNgaySinh()
   {
     return this.ngaySinh;
   }

   public void setNgaySinh(String ngaySinh)
   {
     this.ngaySinh = ngaySinh;
   }

   public String getGioVaoVien()
   {
     return this.gioVaoVien;
   }

   public void setGioVaoVien(String gioVaoVien)
   {
     this.gioVaoVien = gioVaoVien;
   }

   public String getNgayVaoVien()
   {
     return this.ngayVaoVien;
   }

   public void setNgayVaoVien(String ngayVaoVien)
   {
     this.ngayVaoVien = ngayVaoVien;
   }

   public String getNgayRaVien()
   {
     return this.ngayRaVien;
   }

   public void setNgayRaVien(String ngayRaVien)
   {
     this.ngayRaVien = ngayRaVien;
   }

   public String getGioRaVien()
   {
     return this.gioRaVien;
   }

   public void setGioRaVien(String gioRaVien)
   {
     this.gioRaVien = gioRaVien;
   }

   public String getNgayYc()
   {
     return this.ngayYc;
   }

   public void setNgayYc(String ngayYc)
   {
     this.ngayYc = ngayYc;
   }

   public String getSoVaoVien()
   {
     return this.soVaoVien;
   }

   public void setSoVaoVien(String soVaoVien)
   {
     this.soVaoVien = soVaoVien;
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

   public String getIsReport()
   {
     return this.isReport;
   }

   public void setIsReport(String isReport)
   {
     this.isReport = isReport;
   }

   public int getIndex()
   {
     return this.index;
   }

   public void setIndex(int index)
   {
     this.index = index;
   }

   public Boolean getHienThiInPhieu()
   {
     return this.hienThiInPhieu;
   }

   public void setHienThiInPhieu(Boolean hienThiInPhieu)
   {
     this.hienThiInPhieu = hienThiInPhieu;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.TomTatBenhAnAction

 * JD-Core Version:    0.7.0.1

 */