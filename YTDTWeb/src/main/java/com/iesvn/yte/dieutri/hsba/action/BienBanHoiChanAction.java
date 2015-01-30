 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.XuatReportUtil;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaBienBanHoiChanDelegate;
 import com.iesvn.yte.dieutri.delegate.HsbaChuyenMonDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.Hsba;
 import com.iesvn.yte.dieutri.entity.HsbaBienBanHoiChan;
 import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
 import com.iesvn.yte.entity.DmGioiTinh;
 import com.iesvn.yte.entity.DmHuyen;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmTinh;
 import com.iesvn.yte.entity.DmXa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import net.sf.jasperreports.engine.JasperCompileManager;
 import net.sf.jasperreports.engine.JasperFillManager;
 import net.sf.jasperreports.engine.JasperPrint;
 import net.sf.jasperreports.engine.JasperReport;
 import org.apache.commons.lang.StringUtils;
 import org.apache.commons.lang.time.DateUtils;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.Create;
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
 @Name("B239_Bienbanhoichan")
 @Synchronized(timeout=6000000L)
 public class BienBanHoiChanAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static transient Logger log = Logger.getLogger(BienBanHoiChanAction.class);
   private HsbaBienBanHoiChan hsbaBienBanHoiChan;
   private DtDmNhanVien newNhanVien;
   @DataModel
   private List<DtDmNhanVien> thanhVienList;
   @In(required=false)
   @Out(required=false)
   @DataModelSelection
   private DtDmNhanVien thanhVienSelected;
   private Integer gioHoichan;
   private String donViTuoi;
   private String ghiNhanException;
   private String maPhu;

   @Create
   @Begin(join=true)
   public String init()
     throws Exception
   {
     log.info("***Starting init ***");
     this.thanhVienList = new ArrayList();
     getHsbaBienBanHoiChan(true).setHsbabbhcNgaygiocn(new Date());
     log.info("***Finished init **");
     return "DieuTri_LapVanBanTheoMau_BienBanHoiChan";
   }

   @End
   public String back()
     throws Exception
   {
     log.info("***Starting back ***");

     log.info("***Finished back **");
     return "MyMainForm";
   }

   public void layTheoSoVaoVien()
     throws Exception
   {
     log.info("***Starting layTheoSoVaoVien ***");
     log.info("--hsbaBienBanHoiChan.getHsbacmMa()=" + this.hsbaBienBanHoiChan.getHsbacmMa());
     this.thanhVienList.clear();
     this.newNhanVien = null;
     if ((this.hsbaBienBanHoiChan != null) && (this.hsbaBienBanHoiChan.getHsbacmMa() != null))
     {
       log.info("hsbaBienBanHoiChan.getHsbacmMa().getKhoaMa()=" + this.hsbaBienBanHoiChan.getHsbacmMa().getKhoaMa());
       if (this.hsbaBienBanHoiChan.getHsbacmMa().getKhoaMa() != null)
       {
         String khoa = this.hsbaBienBanHoiChan.getHsbacmMa().getKhoaMa().getDmkhoaMa();
         log.info("khoa=" + khoa);
         if (StringUtils.isNotBlank(khoa))
         {
           log.info("hsbaBienBanHoiChan.getHsbacmMa().getHsbaSovaovien()=" + this.hsbaBienBanHoiChan.getHsbacmMa().getHsbaSovaovien());
           if (this.hsbaBienBanHoiChan.getHsbacmMa().getHsbaSovaovien() != null)
           {
             String soVaoVien = this.hsbaBienBanHoiChan.getHsbacmMa().getHsbaSovaovien().getHsbaSovaovien();
             if (StringUtils.isNotBlank(soVaoVien)) {
               try
               {
                 HsbaChuyenMon hsbaChuyenMon = HsbaChuyenMonDelegate.getInstance().findBySoVaoVien_MaKhoa(soVaoVien, khoa);
                 if (hsbaChuyenMon != null)
                 {
                   int dvt = hsbaChuyenMon.getHsbaSovaovien().getBenhnhanMa().getBenhnhanDonvituoi().shortValue();
                   this.donViTuoi = Utils.taoDonViTuoi(dvt);
                   this.hsbaBienBanHoiChan.setHsbacmMa(hsbaChuyenMon);
                 }
               }
               catch (Exception e)
               {
                 FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", new Object[] { IConstantsRes.KHOA_PHONG, khoa, IConstantsRes.SO_BENH_AN, soVaoVien });
                 log.error(e.toString());
               }
             }
           }
         }
       }
     }
     String khoa = this.hsbaBienBanHoiChan.getHsbacmMa().getKhoaMa().getDmkhoaMa();
     String soVaoVien = this.hsbaBienBanHoiChan.getHsbacmMa().getHsbaSovaovien().getHsbaSovaovien();
     if (getHsbaBienBanHoiChan().getHsbacmMa().getHsbaSovaovien().getBenhnhanMa().getBenhnhanHoten() == null) {
       FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", new Object[] { IConstantsRes.KHOA_PHONG, khoa, IConstantsRes.SO_BENH_AN, soVaoVien });
     }
     log.info("---- Ho ten=" + getHsbaBienBanHoiChan().getHsbacmMa().getHsbaSovaovien().getBenhnhanMa().getBenhnhanHoten());

     log.info("***Finished layTheoSoVaoVien **");
   }

   public void layTheoHsbabbhcMa()
     throws Exception
   {
     log.info("***Starting layTheoHsbabbhcMa ***");
     try
     {
       DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
       this.hsbaBienBanHoiChan = ((HsbaBienBanHoiChan)dieuTriUtilDelegate.findByMaWithFormatMaPhieu(this.hsbaBienBanHoiChan.getHsbabbhcMa(), "HsbaBienBanHoiChan", "hsbabbhcMa"));
       if (this.hsbaBienBanHoiChan == null)
       {
         FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " {0} {1}.", new Object[] { IConstantsRes.MA_BIEN_BAN, "null" });
         return;
       }
       int dvt = this.hsbaBienBanHoiChan.getHsbacmMa().getHsbaSovaovien().getBenhnhanMa().getBenhnhanDonvituoi().shortValue();
       this.donViTuoi = Utils.taoDonViTuoi(dvt);
       HsbaBienBanHoiChanDelegate hsbaBienBanHoiChanDelegate = HsbaBienBanHoiChanDelegate.getInstance();
       log.info("hsbaBienBanHoiChan=" + this.hsbaBienBanHoiChan);
       log.info("hsbaBienBanHoiChan.getHsbabbhcMa()=" + this.hsbaBienBanHoiChan.getHsbabbhcMa());
       this.thanhVienList = hsbaBienBanHoiChanDelegate.findThanhVienByHsbabbhcMa(this.hsbaBienBanHoiChan.getHsbabbhcMa());
       if ((getHsbaBienBanHoiChan() != null) && (getHsbaBienBanHoiChan().getHsbabbhcNgaygiohoichan() != null)) {
         this.gioHoichan = getSeconds(getHsbaBienBanHoiChan().getHsbabbhcNgaygiohoichan());
       } else {
         this.gioHoichan = Integer.valueOf(0);
       }
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " {0} {1}.", new Object[] { IConstantsRes.MA_BIEN_BAN, this.hsbaBienBanHoiChan.getHsbabbhcMa() });
       log.error(e.toString());
     }
     log.info("***Finished layTheoHsbabbhcMa **");
   }

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

   public String thuchienAction()
   {
     XuatReport();
     return "B260_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.loaiBCDT = "bienbanhoichanphauthuat";
     log.info("Vao Method XuatReport bienbanhoichan_phauthuat");
     String baocao1 = null;
     String pathTemplate = null;
     try
     {
       pathTemplate = IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI + "hsba/bienbanhoichan_phauthuat.jrxml";
       log.info("da thay file templete 5" + pathTemplate);
       JasperReport jasperReport = JasperCompileManager.compileReport(pathTemplate);
       log.info("da thay file template ");
       Map<String, Object> params = new HashMap();


       params.put("tenSo", IConstantsRes.REPORT_DIEUTRI_SO_Y_TE);
       params.put("tenDonVi", IConstantsRes.REPORT_DIEUTRI_TEN_DON_VI);
       params.put("dienThoaiDonVi", IConstantsRes.REPORT_DIEUTRI_DIEN_THOAI_DON_VI);
       params.put("TINH", IConstantsRes.REPORT_DIEUTRI_DIA_DIEM);

       params.put("hoTen", getHsbaBienBanHoiChan(true).getHsbacmMa(true).getHsbaSovaovien(true).getBenhnhanMa(true).getBenhnhanHoten());
       params.put("gioi", getHsbaBienBanHoiChan(true).getHsbacmMa(true).getHsbaSovaovien(true).getBenhnhanMa(true).getDmgtMaso(true).getDmgtTen());

       String tuoi = getHsbaBienBanHoiChan(true).getHsbacmMa(true).getHsbaSovaovien(true).getBenhnhanMa(true).getBenhnhanTuoi() + "";
       short dvt = getHsbaBienBanHoiChan(true).getHsbacmMa(true).getHsbaSovaovien(true).getBenhnhanMa(true).getBenhnhanDonvituoi().shortValue();
       if (dvt != 1) {
         if (dvt == 2) {
           tuoi = tuoi + "T";
         } else if (dvt == 3) {
           tuoi = tuoi + "N";
         }
       }
       params.put("tuoi", tuoi);

       StringBuffer strBuf = new StringBuffer("");
       BenhNhan bn = getHsbaBienBanHoiChan(true).getHsbacmMa(true).getHsbaSovaovien(true).getBenhnhanMa(true);
       if (bn != null)
       {
         strBuf.append(bn.getBenhnhanDiachi());
         strBuf.append(bn.getXaMa() != null ? ", " + bn.getXaMa().getDmxaTen() : "");
         strBuf.append(bn.getHuyenMa() != null ? ", " + bn.getHuyenMa().getDmhuyenTen() : "");
         strBuf.append(bn.getTinhMa() != null ? ", " + bn.getTinhMa().getDmtinhTen() : "");
       }
       params.put("diaChi", strBuf.toString());

       params.put("soVaoVien", getHsbaBienBanHoiChan(true).getHsbacmMa(true).getHsbaSovaovien(true).getHsbaSovaovien());

       DieuTriUtilDelegate dele = DieuTriUtilDelegate.getInstance();
       if ((getHsbaBienBanHoiChan(true).getHsbacmMa(true).getKhoaMa(true).getDmkhoaMa() != null) && (!getHsbaBienBanHoiChan(true).getHsbacmMa(true).getKhoaMa(true).getDmkhoaMa().equals("")))
       {
         DmKhoa khoa = (DmKhoa)dele.findByMa(getHsbaBienBanHoiChan(true).getHsbacmMa(true).getKhoaMa(true).getDmkhoaMa(), "DmKhoa", "dmkhoaMa");
         if (khoa != null) {
           params.put("tenkhoa", khoa.getDmkhoaTen());
         } else {
           params.put("tenkhoa", "");
         }
       }
       else
       {
         params.put("tenkhoa", "");
       }
       params.put("tomtat", getHsbaBienBanHoiChan(true).getHsbabbhcTomtat());

       Date dVaov = getHsbaBienBanHoiChan(true).getHsbacmMa(true).getHsbaSovaovien(true).getHsbaNgaygiovaov();

       params.put("ngaygiovaovien", dVaov);

       params.put("ngaygiohoichan", DateUtils.addSeconds(DateUtils.truncate(this.hsbaBienBanHoiChan.getHsbabbhcNgaygiohoichan(), 5), this.gioHoichan.intValue()));


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
       this.jasperPrintDT = JasperFillManager.fillReport(jasperReport, params, conn);
       baocao1 = XuatReportUtil.ReportUtil(this.jasperPrintDT, this.index, IConstantsRes.PATH_BASE + IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI + "hsba/", "pdf", "bienbanhoichan_phauthuat");
       this.duongdanStrDT = baocao1.replaceFirst(IConstantsRes.PATH_BASE, "");
       log.info("duong dan file xuat report :" + baocao1);
       log.info("duong dan -------------------- :" + this.duongdanStrDT);
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

   public void ghinhan()
   {
     log.info("***Starting ghinhan **");


     String khoa = this.hsbaBienBanHoiChan.getHsbacmMa().getKhoaMa().getDmkhoaMa();
     String soVaoVien = this.hsbaBienBanHoiChan.getHsbacmMa().getHsbaSovaovien().getHsbaSovaovien();
     if (getHsbaBienBanHoiChan().getHsbacmMa().getHsbaSovaovien().getBenhnhanMa().getBenhnhanHoten() == null)
     {
       FacesMessages.instance().add(IConstantsRes.NOT_FOUND_INFO + " #0 {1}, {2} {3}.", new Object[] { IConstantsRes.KHOA_PHONG, khoa, IConstantsRes.SO_BENH_AN, soVaoVien });
       return;
     }
     if (this.thanhVienList != null) {
       log.info("thanhVienList.size()=" + this.thanhVienList.size());
     }
     this.ghiNhanException = null;
     HsbaBienBanHoiChanDelegate hsbaBienBanHoiChanDelegate = HsbaBienBanHoiChanDelegate.getInstance();
     if (this.hsbaBienBanHoiChan != null)
     {
       if ((this.hsbaBienBanHoiChan.getHsbabbhcChutoa() != null) && (this.hsbaBienBanHoiChan.getHsbabbhcChutoa().getDtdmnhanvienMaso() == null)) {
         this.hsbaBienBanHoiChan.setHsbabbhcChutoa(null);
       }
       if ((this.hsbaBienBanHoiChan.getHsbabbhcThuky() != null) && (this.hsbaBienBanHoiChan.getHsbabbhcThuky().getDtdmnhanvienMaso() == null)) {
         this.hsbaBienBanHoiChan.setHsbabbhcThuky(null);
       }
       try
       {
         if ((this.hsbaBienBanHoiChan != null) && (this.hsbaBienBanHoiChan.getHsbabbhcNgaygiohoichan() != null)) {
           this.hsbaBienBanHoiChan.setHsbabbhcNgaygiohoichan(DateUtils.addSeconds(DateUtils.truncate(this.hsbaBienBanHoiChan.getHsbabbhcNgaygiohoichan(), 5), this.gioHoichan.intValue()));
         }
         if (StringUtils.isBlank(this.hsbaBienBanHoiChan.getHsbabbhcMa()))
         {
           log.info("createHsbaBienBanHoiChan ...");

           this.hsbaBienBanHoiChan = hsbaBienBanHoiChanDelegate.createHsbaBienBanHoiChan(this.hsbaBienBanHoiChan, this.thanhVienList);
           log.info("createHsbaBienBanHoiChan ... end.");
           FacesMessages.instance().add(IConstantsRes.SUCCESS + " #0 {1}", new Object[] { IConstantsRes.MA_BIEN_BAN, this.hsbaBienBanHoiChan.getHsbabbhcMa() });
         }
         else
         {
           log.info("editHsbaBienBanHoiChan ...");
           hsbaBienBanHoiChanDelegate.editHsbaBienBanHoiChan(this.hsbaBienBanHoiChan, this.thanhVienList);
           log.info("editHsbaBienBanHoiChan ... end");
           FacesMessages.instance().add(IConstantsRes.SUCCESS + " #0 {1}", new Object[] { IConstantsRes.MA_BIEN_BAN, this.hsbaBienBanHoiChan.getHsbabbhcMa() });
         }
       }
       catch (Exception e)
       {
         FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);
         this.ghiNhanException = e.getClass().getName();
         log.error("Ghi nhan khong thanh cong");
         log.error("Loi : " + e);
       }
     }
     log.info("***Finished ghinhan **");
   }

   public void nhaplai()
     throws Exception
   {
     try
     {
       resetForm();
     }
     catch (Exception e)
     {
       log.error("Loi reset form : " + e.toString());
     }
   }

   private void resetForm()
   {
     log.info("Begining ResetForm(): ");
     this.hsbaBienBanHoiChan = null;
     getHsbaBienBanHoiChan(true).setHsbabbhcNgaygiocn(new Date());
     this.thanhVienList.clear();
     this.newNhanVien = null;
     this.ghiNhanException = null;
     this.donViTuoi = null;
     log.info("End ResetForm(): ");
   }

   public Integer getSeconds(Date d)
   {
     long seconds = DateUtils.getFragmentInHours(d, 5) * 60L * 60L + DateUtils.getFragmentInMinutes(d, 11) * 60L;
     return Integer.valueOf(seconds + "");
   }

   public HsbaBienBanHoiChan getHsbaBienBanHoiChan(boolean create)
   {
     if ((create) && (this.hsbaBienBanHoiChan == null)) {
       this.hsbaBienBanHoiChan = new HsbaBienBanHoiChan();
     }
     return this.hsbaBienBanHoiChan;
   }

   public HsbaBienBanHoiChan getHsbaBienBanHoiChan()
   {
     return this.hsbaBienBanHoiChan;
   }

   public void setHsbaBienBanHoiChan(HsbaBienBanHoiChan hsbaBienBanHoiChan)
   {
     this.hsbaBienBanHoiChan = hsbaBienBanHoiChan;
   }

   public String getGhiNhanException()
   {
     return this.ghiNhanException;
   }

   public void setGhiNhanException(String ghiNhanException)
   {
     this.ghiNhanException = ghiNhanException;
   }

   public void themThanhVien()
   {
     if (this.thanhVienList == null) {
       this.thanhVienList = new ArrayList();
     }
     DieuTriUtilDelegate dieuTriUtilDelegate = DieuTriUtilDelegate.getInstance();
     DtDmNhanVien nv = (DtDmNhanVien)dieuTriUtilDelegate.findByMa(this.newNhanVien.getDtdmnhanvienMa(), "DtDmNhanVien", "dtdmnhanvienMa");


     log.info("newNhanVien.getDtdmnhanvienTen()=" + this.newNhanVien.getDtdmnhanvienTen());
     log.info("nv.getDtdmnhanvienTen()=" + nv.getDtdmnhanvienTen());
     log.info("thanhVienList.contains(nv)=" + this.thanhVienList.contains(nv));
     if (!this.thanhVienList.contains(nv)) {
       this.thanhVienList.add(nv);
     }
   }

   public void deleteCurrentRow()
   {
     if ((this.thanhVienList == null) || (this.thanhVienList.size() == 0)) {
       return;
     }
     this.thanhVienList.remove(this.thanhVienSelected);
     this.thanhVienSelected = null;
   }

   public DtDmNhanVien getNewNhanVien(boolean create)
   {
     if ((create) && (this.newNhanVien == null)) {
       this.newNhanVien = new DtDmNhanVien();
     }
     return this.newNhanVien;
   }

   public DtDmNhanVien getNewNhanVien()
   {
     return this.newNhanVien;
   }

   public void setNewNhanVien(DtDmNhanVien newNhanVien)
   {
     this.newNhanVien = newNhanVien;
   }

   public List<DtDmNhanVien> getThanhVienList()
   {
     return this.thanhVienList;
   }

   public void setThanhVienSet(List<DtDmNhanVien> thanhVienList)
   {
     this.thanhVienList = thanhVienList;
   }

   public String getDonViTuoi()
   {
     return this.donViTuoi;
   }

   public void setDonViTuoi(String donViTuoi)
   {
     this.donViTuoi = donViTuoi;
   }

   public Integer getGioHoichan()
   {
     return this.gioHoichan;
   }

   public void setGioHoichan(Integer gioHoichan)
   {
     this.gioHoichan = gioHoichan;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.BienBanHoiChanAction

 * JD-Core Version:    0.7.0.1

 */