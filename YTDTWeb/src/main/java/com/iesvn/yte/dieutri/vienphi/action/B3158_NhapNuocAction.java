 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmBuongDelegate;
 import com.iesvn.yte.dieutri.delegate.NhapNuocDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmBuong;
 import com.iesvn.yte.dieutri.entity.NhapNuoc;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import net.sf.jasperreports.engine.JasperPrint;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Scope(ScopeType.SESSION)
 @Name("B3158_NhapNuoc")
 @Synchronized(timeout=6000000L)
 public class B3158_NhapNuocAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private String khoaMaso;
   private String buongMaso;
   private String khoaMa;
   private String buongMa;
   private String ngaynhap;
   private String ngayhientai;
   private String soluong;
   private NhapNuoc nhapnuoc;
   private int selectedIndex;
   private NhapNuocDelegate nnDel;
   private List<NhapNuoc> listNhapNuoc;
   @Out(required=false)
   @In(required=false)
   private String reportTypeVP = null;
   @Out(required=false)
   @In(required=false)
   JasperPrint jasperPrintVP = null;
   @Out(required=false)
   @In(required=false)
   private String reportPathVP = null;
   @In(scope=ScopeType.PAGE, required=false)
   @Out(scope=ScopeType.PAGE, required=false)
   private String strNhapNuoc;
   @Logger
   private Log log;
   private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

   @Factory("strNhapNuoc")
   public void init()
   {
     this.log.info("begin init, nnDel = " + this.nnDel, new Object[0]);
     this.ngayhientai = this.sdf.format(new Date());
     this.ngaynhap = this.sdf.format(new Date());
     this.listNhapNuoc = new ArrayList();
     this.nhapnuoc = new NhapNuoc();

     this.strNhapNuoc = "";

     reset();
     if (this.nnDel == null) {
       this.nnDel = NhapNuocDelegate.getInstance();
     }
     this.log.info("end init, nnDel = " + this.nnDel, new Object[0]);
   }

   private void reset()
   {
     this.khoaMaso = (this.khoaMa = this.buongMaso = this.buongMa = "");
     this.soluong = "1";
     this.selectedIndex = -1;
   }

   public void searchNhapnuoc()
   {
     this.log.info("--enter searchNhapnuoc()---", new Object[0]);
     try
     {
       NhapNuocDelegate nnDel = NhapNuocDelegate.getInstance();

       this.listNhapNuoc = nnDel.findByNgayNhap(this.sdf.parse(this.ngaynhap));
       if (this.listNhapNuoc != null)
       {
         FacesMessages.instance().clear();
       }
       else
       {
         this.listNhapNuoc = new ArrayList();

         FacesMessages.instance().add(IConstantsRes.no_found, new Object[0]);
       }
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     this.selectedIndex = -1;
     this.log.info("--exit searchNhapnuoc()---", new Object[0]);
   }

   public void edit(int index)
   {
     this.log.info("*****Begin edit(),index = " + index, new Object[0]);
     this.nhapnuoc = ((NhapNuoc)this.listNhapNuoc.get(index));
     this.khoaMaso = this.nhapnuoc.getDmkhoaMaso().getDmkhoaMaso().toString();
     this.khoaMa = this.nhapnuoc.getDmkhoaMaso().getDmkhoaMa();
     this.buongMaso = this.nhapnuoc.getDtdmbMaso().getDtdmbMaso().toString();
     this.buongMa = this.nhapnuoc.getDtdmbMaso().getDtdmbMa();
     this.soluong = this.nhapnuoc.getNhapnuocSoluong().toString();
     this.ngaynhap = this.sdf.format(this.nhapnuoc.getNhapnuocNgay());
     this.selectedIndex = index;
     this.log.info("*****End edit(),nhapnuoc = " + this.nhapnuoc, new Object[0]);
   }

   public void delete(int index)
     throws Exception
   {
     this.log.info("*****Begin delete() *****, index = " + index + ", nhapnuoc = " + this.listNhapNuoc.get(index), new Object[0]);

     this.nnDel.remove((NhapNuoc)this.listNhapNuoc.get(index));

     this.listNhapNuoc.remove(index);

     this.nhapnuoc = new NhapNuoc();
     reset();
     this.log.info("*****End delete() *****, listNhapNuoc.size = " + this.listNhapNuoc.size(), new Object[0]);
   }

   public String ghinhan()
     throws Exception
   {
     this.log.info("*****Begin Ghi nhan() *****, buongMaso = " + this.buongMaso + ", ngaynhap = " + this.ngaynhap + ", nhapnuoc = " + this.nhapnuoc, new Object[0]);

     NhapNuoc nnTmp = this.nnDel.findByBuongNgayNhap(new Integer(this.buongMaso), this.sdf.parse(this.ngaynhap));
     if ((nnTmp != null) && ((this.nhapnuoc.getNhapnuocMaso() == null) || ((this.nhapnuoc.getNhapnuocMaso() != null) && (this.nhapnuoc.getNhapnuocMaso() != nnTmp.getNhapnuocMaso()))))
     {
       FacesMessages.instance().add(IConstantsRes.NHAPNUOC_EXISTS, new Object[] { this.ngaynhap, nnTmp.getDtdmbMaso().getDtdmbTen() });
     }
     else
     {
       if (this.nhapnuoc.getNhapnuocMaso() == null) {
         FacesMessages.instance().add(IConstantsRes.INSERT_SUCCESS, new Object[0]);
       } else {
         FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS, new Object[0]);
       }
       DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
       if (this.khoaMaso.trim().length() > 0)
       {
         DmKhoa dmKhoa = (DmKhoa)dtUtil.findByMaSo(new Integer(this.khoaMaso), "DmKhoa", "dmkhoaMaso");
         this.nhapnuoc.setDmkhoaMaso(dmKhoa);
       }
       else
       {
         this.nhapnuoc.setDmkhoaMaso(null);
       }
       if (this.buongMaso.trim().length() > 0) {
         this.nhapnuoc.setDtdmbMaso(DtDmBuongDelegate.getInstance().find(new Integer(this.buongMaso)));
       } else {
         this.nhapnuoc.setDtdmbMaso(null);
       }
       if (this.soluong.trim().length() > 0) {
         this.nhapnuoc.setNhapnuocSoluong(new Integer(this.soluong));
       } else {
         this.nhapnuoc.setNhapnuocSoluong(null);
       }
       this.nhapnuoc.setNhapnuocNgay(this.sdf.parse(this.ngaynhap));
       nnTmp = this.nnDel.myCreate(this.nhapnuoc);
       if (this.selectedIndex < 0) {
         this.listNhapNuoc.add(nnTmp);
       } else {
         this.listNhapNuoc.set(this.selectedIndex, nnTmp);
       }
       this.nhapnuoc = new NhapNuoc();
       reset();
     }
     this.log.info("*****End Ghi nhan() *****", new Object[0]);
     return null;
   }

   public String thuchienAction()
   {
     XuatReport();
     return "B3360_Chonmenuxuattaptin";
   }

   public void XuatReport()
   {
     this.log.info("Thoat Method XuatReport", new Object[0]);
   }

   public int getSelectedIndex()
   {
     return this.selectedIndex;
   }

   public void setSelectedIndex(int selectedIndex)
   {
     this.selectedIndex = selectedIndex;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public String getKhoaMaso()
   {
     return this.khoaMaso;
   }

   public void setKhoaMaso(String khoaMaso)
   {
     this.khoaMaso = khoaMaso;
   }

   public String getReportTypeVP()
   {
     return this.reportTypeVP;
   }

   public void setReportTypeVP(String reportTypeVP)
   {
     this.reportTypeVP = reportTypeVP;
   }

   public String getBuongMaso()
   {
     return this.buongMaso;
   }

   public void setBuongMaso(String buongMaso)
   {
     this.buongMaso = buongMaso;
   }

   public String getNgaynhap()
   {
     return this.ngaynhap;
   }

   public void setNgaynhap(String ngaynhap)
   {
     this.ngaynhap = ngaynhap;
   }

   public String getSoluong()
   {
     return this.soluong;
   }

   public void setSoluong(String soluong)
   {
     this.soluong = soluong;
   }

   public List<NhapNuoc> getListNhapNuoc()
   {
     return this.listNhapNuoc;
   }

   public void setListNhapNuoc(List<NhapNuoc> listNhapNuoc)
   {
     this.listNhapNuoc = listNhapNuoc;
   }

   public String getKhoaMa()
   {
     return this.khoaMa;
   }

   public void setKhoaMa(String khoaMa)
   {
     this.khoaMa = khoaMa;
   }

   public String getBuongMa()
   {
     return this.buongMa;
   }

   public void setBuongMa(String buongMa)
   {
     this.buongMa = buongMa;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.B3158_NhapNuocAction

 * JD-Core Version:    0.7.0.1

 */