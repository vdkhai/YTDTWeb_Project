 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.CtXuatKhoDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuXuatKhoDelegate;
 import com.iesvn.yte.dieutri.entity.CtXuatKho;
 import com.iesvn.yte.dieutri.entity.PhieuXuatKho;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("Timkiemphieuxuat")
 public class TimKiemPhieuXuatAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static Logger logger = Logger.getLogger(TimKiemPhieuXuatAction.class);
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private PhieuXuatKho pxk;
   private Integer loaithuocMaso = Integer.valueOf(0);
   private String loaithuocMa;
   private String ngayHt;
   private String phieuDuTru = "";
   private Integer count;
   private String maPhieu;
   private String ngayLap;
   private String ngaygiocn;
   private String nhanvienLapphieuMa;
   private Integer nhanvienLapphieuMaso;
   private String nhanvienNhapphieuMa;
   private Integer nhanvienNhapphieuMaso;
   private String khoaNhanMa;
   private Integer khoaNhanMaSo;
   private DmKhoa dmKhoaXuat = new DmKhoa();
   @DataModel
   private List<PhieuXuatKho> listResultPhieuXuat;
   @DataModel
   private ArrayList<CtXuatKho> listCtXuatKho;
   DieuTriUtilDelegate dtDel = DieuTriUtilDelegate.getInstance();

   @Begin(join=true)
   public String init(String loaiKho)
   {
     logger.info("-----init()-----");
     reset();
     if (loaiKho.equals("KC"))
     {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
       this.dmKhoaXuat = ((DmKhoa)this.dtDel.findByMa(IConstantsRes.KHOA_KC_MA, "DmKhoa", "dmkhoaMa"));
     }
     else if (loaiKho.equals("NT"))
     {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
       this.dmKhoaXuat = ((DmKhoa)this.dtDel.findByMa(IConstantsRes.KHOA_NT_MA, "DmKhoa", "dmkhoaMa"));
     }
     else if (loaiKho.equals("BHYT"))
     {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoBHYT;
       this.dmKhoaXuat = ((DmKhoa)this.dtDel.findByMa(IConstantsRes.KHOA_BHYT_MA, "DmKhoa", "dmkhoaMa"));
     }
     else if (loaiKho.equals("TE"))
     {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoTE;
       this.dmKhoaXuat = ((DmKhoa)this.dtDel.findByMa(IConstantsRes.KHOA_TE_MA, "DmKhoa", "dmkhoaMa"));
     }
     return "QuanLyKhoChinh_NhapKhoChinh_TimKiemPhieuXuat";
   }

   public void reset()
   {
     this.pxk = new PhieuXuatKho();
     this.ngayHt = Utils.getCurrentDate();
     this.listResultPhieuXuat = new ArrayList();
     this.listCtXuatKho = new ArrayList();
     this.maPhieu = "";
     this.ngayLap = "";
     this.ngaygiocn = "";
   }

   public void loadPhieuXuat()
   {
     logger.info("-----loadPhieuXuat()-----");
     if (!this.maPhieu.equals(""))
     {
       PhieuXuatKhoDelegate pxkDelegate = PhieuXuatKhoDelegate.getInstance();
       CtXuatKhoDelegate ctDelegate = CtXuatKhoDelegate.getInstance();
       try
       {
         PhieuXuatKho pxk_temp = pxkDelegate.findPhieuXuatKhoByKhoXuat(this.maPhieu, this.dmKhoaXuat.getDmkhoaMaso());
         if (pxk_temp != null)
         {
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           if (pxk_temp.getPhieuxuatkhoNgaygiocn() != null) {
             this.ngaygiocn = df.format(pxk_temp.getPhieuxuatkhoNgaygiocn());
           } else {
             this.ngaygiocn = "";
           }
           this.listCtXuatKho = ((ArrayList)ctDelegate.findByphieuxuatkhoMa(this.maPhieu));
           this.count = Integer.valueOf(this.listCtXuatKho.size());
           this.pxk = pxk_temp;
           this.ngayLap = df.format(this.pxk.getPhieuxuatkhoNgaylap());
         }
         else
         {
           FacesMessages.instance().add(IConstantsRes.PHIEUNHAPKHO_NULL, new Object[] { this.maPhieu });
         }
       }
       catch (Exception e)
       {
         e.printStackTrace();
       }
     }
   }

   public void selectPhieuXuat(Integer index)
   {
     logger.info("-----selectPhieuXuat()-----");
     logger.info("-----index: " + index);
     this.pxk = ((PhieuXuatKho)this.listResultPhieuXuat.get(index.intValue()));
     this.maPhieu = this.pxk.getPhieuxuatkhoMa();
     loadPhieuXuat();
   }

   public void timkiem()
   {
     logger.info("-----timkiem()-----");
     this.listCtXuatKho = new ArrayList();
     this.pxk = new PhieuXuatKho();
     PhieuXuatKhoDelegate pxkDelegate = new PhieuXuatKhoDelegate();
     SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
     Date ngayHD = new Date();
     try
     {
       if (!this.ngayHt.equals("")) {
         ngayHD = df.parse(this.ngayHt);
       } else {
         ngayHD = null;
       }
     }
     catch (ParseException e)
     {
       FacesMessages.instance().add(IConstantsRes.PHIEUXUATKHO_FORMATDATE, new Object[] { this.ngayHt });
       e.printStackTrace();
     }
     if ((this.loaithuocMaso == null) || (this.loaithuocMaso.equals(null))) {
       this.loaithuocMaso = Integer.valueOf(0);
     }
     if ((this.nhanvienLapphieuMaso == null) || (this.nhanvienLapphieuMaso.equals(null))) {
       this.nhanvienLapphieuMaso = Integer.valueOf(0);
     }
     if ((this.nhanvienNhapphieuMaso == null) || (this.nhanvienNhapphieuMaso.equals(null))) {
       this.nhanvienNhapphieuMaso = Integer.valueOf(0);
     }
     if ((this.khoaNhanMaSo == null) || (this.khoaNhanMaSo.equals(null))) {
       this.khoaNhanMaSo = Integer.valueOf(0);
     }
     List<PhieuXuatKho> lstPhieuXuatKho = pxkDelegate.find(this.maPhieu, ngayHD, this.loaithuocMaso, this.nhanvienLapphieuMaso, this.nhanvienNhapphieuMaso, this.khoaNhanMaSo, this.dmKhoaXuat.getDmkhoaMaso());
     if (lstPhieuXuatKho != null)
     {
       logger.info("lstPhieuXuatKho = " + lstPhieuXuatKho.size());
       this.listResultPhieuXuat = lstPhieuXuatKho;
     }
   }

   public PhieuXuatKho getPxk()
   {
     return this.pxk;
   }

   public void setPxk(PhieuXuatKho pxk)
   {
     this.pxk = pxk;
   }

   public ArrayList<CtXuatKho> getListCtXuatKho()
   {
     return this.listCtXuatKho;
   }

   public void setListCtXuatKho(ArrayList<CtXuatKho> listCtXuatKho)
   {
     this.listCtXuatKho = listCtXuatKho;
   }

   public List<PhieuXuatKho> getListResultPhieuXuat()
   {
     return this.listResultPhieuXuat;
   }

   public void setListResultPhieuXuat(List<PhieuXuatKho> listResultPhieuXuat)
   {
     this.listResultPhieuXuat = listResultPhieuXuat;
   }

   public String getNgayHt()
   {
     return this.ngayHt;
   }

   public void setNgayHt(String ngayHt)
   {
     this.ngayHt = ngayHt;
   }

   public String getPhieuDuTru()
   {
     return this.phieuDuTru;
   }

   public void setPhieuDuTru(String phieuDuTru)
   {
     this.phieuDuTru = phieuDuTru;
   }

   public Integer getNhanvienNhapphieuMaso()
   {
     return this.nhanvienNhapphieuMaso;
   }

   public void setNhanvienNhapphieuMaso(Integer nhanvienNhapphieuMaso)
   {
     this.nhanvienNhapphieuMaso = nhanvienNhapphieuMaso;
   }

   public String getNhanvienNhapphieuMa()
   {
     return this.nhanvienNhapphieuMa;
   }

   public void setNhanvienNhapphieuMa(String nhanvienNhapphieuMa)
   {
     this.nhanvienNhapphieuMa = nhanvienNhapphieuMa;
   }

   public Integer getNhanvienLapphieuMaso()
   {
     return this.nhanvienLapphieuMaso;
   }

   public void setNhanvienLapphieuMaso(Integer nhanvienLapphieuMaso)
   {
     this.nhanvienLapphieuMaso = nhanvienLapphieuMaso;
   }

   public String getNhanvienLapphieuMa()
   {
     return this.nhanvienLapphieuMa;
   }

   public void setNhanvienLapphieuMa(String nhanvienLapphieuMa)
   {
     this.nhanvienLapphieuMa = nhanvienLapphieuMa;
   }

   public String getKhoaNhanMa()
   {
     return this.khoaNhanMa;
   }

   public void setKhoaNhanMa(String khoaNhanMa)
   {
     this.khoaNhanMa = khoaNhanMa;
   }

   public Integer getKhoaNhanMaSo()
   {
     return this.khoaNhanMaSo;
   }

   public void setKhoaNhanMaSo(Integer khoaNhanMaSo)
   {
     this.khoaNhanMaSo = khoaNhanMaSo;
   }

   public Integer getLoaiThuocMaSo()
   {
     return this.loaithuocMaso;
   }

   public void setLoaiThuocMaSo(Integer loaithuocMaso)
   {
     this.loaithuocMaso = loaithuocMaso;
   }

   public String getLoaiThuocMa()
   {
     return this.loaithuocMa;
   }

   public void setLoaiThuocMa(String loaithuocMa)
   {
     this.loaithuocMa = loaithuocMa;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }

   public Integer getCount()
   {
     return this.count;
   }

   public void setCount(Integer count)
   {
     this.count = count;
   }

   public String getMaPhieu()
   {
     return this.maPhieu;
   }

   public void setMaPhieu(String maPhieu)
   {
     this.maPhieu = maPhieu;
   }

   public String getNgayLap()
   {
     return this.ngayLap;
   }

   public void setNgayLap(String ngayLap)
   {
     this.ngayLap = ngayLap;
   }

   public String getNgaygiocn()
   {
     return this.ngaygiocn;
   }

   public void setNgaygiocn(String ngaygiocn)
   {
     this.ngaygiocn = ngaygiocn;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.TimKiemPhieuXuatAction

 * JD-Core Version:    0.7.0.1

 */