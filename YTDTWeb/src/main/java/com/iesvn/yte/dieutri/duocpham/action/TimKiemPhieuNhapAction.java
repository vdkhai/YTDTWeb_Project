 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import com.iesvn.yte.dieutri.delegate.CtNhapKhoDelegate;
 import com.iesvn.yte.dieutri.delegate.PhieuNhapKhoDelegate;
 import com.iesvn.yte.dieutri.entity.CtNhapKho;
 import com.iesvn.yte.dieutri.entity.PhieuNhapKho;
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
 @Name("Timkiemphieunhap")
 public class TimKiemPhieuNhapAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static Logger logger = Logger.getLogger(TimKiemPhieuNhapAction.class);
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private PhieuNhapKho pnk;
   private Integer loaithuocMaso = Integer.valueOf(0);
   private String loaithuocMa;
   private String ngayHt;
   private String soChungTu = "";
   private String soHD = "";
   private Integer count;
   private String maPhieu;
   private Double thanhTienThue;
   private String ngayHoaDon;
   private String ngaygiocn;
   @DataModel
   private List<PhieuNhapKho> listResultPhieuNhap;
   @DataModel
   private ArrayList<CtNhapKho> listCtNhapKho;

   @Begin(join=true)
   public String init(String loaiKho)
   {
     logger.info("-----init()-----");
     reset();
     if (loaiKho.equals("KC")) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
     } else if (loaiKho.equals("KhoNoiTru")) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoNoiTru;
     } else if (loaiKho.equals("BHYT")) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoBHYT;
     } else if (loaiKho.equals("TE")) {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoTE;
     }
     return "QuanLyKhoChinh_NhapKhoChinh_TimKiemPhieuNhap";
   }

   public void reset()
   {
     this.pnk = new PhieuNhapKho();
     this.ngayHt = Utils.getCurrentDate();
     this.listResultPhieuNhap = new ArrayList();
     this.listCtNhapKho = new ArrayList();
     this.maPhieu = "";
     this.ngayHoaDon = "";
     this.ngaygiocn = "";
     this.thanhTienThue = new Double(0.0D);
   }

   public void loadPhieuNhap()
   {
     logger.info("-----loadPhieuNhap()-----");
     if (!this.maPhieu.equals(""))
     {
       PhieuNhapKhoDelegate pnkDelegate = PhieuNhapKhoDelegate.getInstance();
       CtNhapKhoDelegate ctDelegate = CtNhapKhoDelegate.getInstance();
       try
       {
         PhieuNhapKho pnk_temp = pnkDelegate.findByPhieunhapkhoMa(this.maPhieu);
         if (pnk_temp != null)
         {
           this.maPhieu = pnk_temp.getPhieunhapkhoMa();
           SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
           this.ngayHt = df.format(pnk_temp.getPhieunhapkhoNgaygiocn());
           this.listCtNhapKho = ((ArrayList)ctDelegate.findByPhieuNhapKho(this.maPhieu));
           this.count = Integer.valueOf(this.listCtNhapKho.size());
           this.pnk = pnk_temp;
           this.ngayHoaDon = df.format(this.pnk.getPhieunhapkhoNgayhoadon());
           this.ngaygiocn = df.format(this.pnk.getPhieunhapkhoNgaygiocn());
           tinhTienThue();
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

   public void selectPhieuNhap(Integer index)
   {
     logger.info("-----selectPhieuNhap()-----");
     logger.info("-----index: " + index);
     this.pnk = ((PhieuNhapKho)this.listResultPhieuNhap.get(index.intValue()));
     this.maPhieu = this.pnk.getPhieunhapkhoMa();
     loadPhieuNhap();
   }

   public void timkiem()
   {
     logger.info("-----timkiem()-----");
     this.listCtNhapKho = new ArrayList();
     this.pnk = new PhieuNhapKho();
     if (this.tenChuongTrinh.equals(MyMenuYTDTAction.quanLyKhoChinh))
     {
       PhieuNhapKhoDelegate pnkDelegate = new PhieuNhapKhoDelegate();
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
         FacesMessages.instance().add(IConstantsRes.PHIEUNHAPKHO_FORMATDATE, new Object[] { this.ngayHt });
         e.printStackTrace();
       }
       if ((this.loaithuocMaso == null) || (this.loaithuocMaso.equals(null))) {
         this.loaithuocMaso = Integer.valueOf(0);
       }
       List<PhieuNhapKho> lstPhieuNhapKho = pnkDelegate.find(this.maPhieu, ngayHD, this.loaithuocMaso, this.soChungTu, this.soHD);
       if ((lstPhieuNhapKho != null) || (!lstPhieuNhapKho.equals(null)))
       {
         logger.info("lstPhieuNhapKho = " + lstPhieuNhapKho.size());
         this.listResultPhieuNhap = lstPhieuNhapKho;
       }
       else
       {
         FacesMessages.instance().add(IConstantsRes.PHIEUNHAPKHO_NULL, null);
       }
     }
   }

   public void tinhTienThue()
   {
     if (this.pnk == null) {
       return;
     }
     logger.info("-----tinhTienThue()-----");
     Double mucThue = this.pnk.getPhieunhapkhoMucthue();
     if (mucThue == null) {
       mucThue = new Double("0");
     }
     Double thanhTien = this.pnk.getPhieunhapkhoThanhtien();
     Double thueGtgt = Double.valueOf(thanhTien.doubleValue() * mucThue.doubleValue() / 100.0D);
     this.thanhTienThue = Double.valueOf(thanhTien.doubleValue() + thueGtgt.doubleValue());
     logger.info("-----thueGtgt: " + thueGtgt);
     logger.info("-----thanhTienThue: " + this.thanhTienThue);
   }

   public PhieuNhapKho getPnk()
   {
     return this.pnk;
   }

   public void setPnk(PhieuNhapKho pnk)
   {
     this.pnk = pnk;
   }

   public ArrayList<CtNhapKho> getListCtNhapKho()
   {
     return this.listCtNhapKho;
   }

   public void setListCtNhapKho(ArrayList<CtNhapKho> listCtNhapKho)
   {
     this.listCtNhapKho = listCtNhapKho;
   }

   public List<PhieuNhapKho> getListResultPhieuNhap()
   {
     return this.listResultPhieuNhap;
   }

   public void setListResultPhieuNhap(List<PhieuNhapKho> listResultPhieuNhap)
   {
     this.listResultPhieuNhap = listResultPhieuNhap;
   }

   public String getNgayHt()
   {
     return this.ngayHt;
   }

   public void setNgayHt(String ngayHt)
   {
     this.ngayHt = ngayHt;
   }

   public String getSoChungTu()
   {
     return this.soChungTu;
   }

   public void setSoChungTu(String soChungTu)
   {
     this.soChungTu = soChungTu;
   }

   public String getSoHD()
   {
     return this.soChungTu;
   }

   public void setSoHD(String soHD)
   {
     this.soHD = soHD;
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

   public String getNgayHoaDon()
   {
     return this.ngayHoaDon;
   }

   public void setNgayHoaDon(String ngayHoaDon)
   {
     this.ngayHoaDon = ngayHoaDon;
   }

   public String getNgaygiocn()
   {
     return this.ngaygiocn;
   }

   public void setNgaygiocn(String ngaygiocn)
   {
     this.ngaygiocn = ngaygiocn;
   }

   public Double getThanhTienThue()
   {
     return this.thanhTienThue;
   }

   public void setThanhTienThue(Double thanhTienThue)
   {
     this.thanhTienThue = thanhTienThue;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.TimKiemPhieuNhapAction

 * JD-Core Version:    0.7.0.1

 */