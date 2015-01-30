 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.TonKhoDinhDuongDelegate;
 import com.iesvn.yte.dieutri.delegate.XuatKhoDinhDuongDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiAn;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiAn2;
 import com.iesvn.yte.dieutri.entity.DtDmNhaSxSpdd;
 import com.iesvn.yte.dieutri.entity.XuatKhoDinhDuong;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Scope(ScopeType.SESSION)
 @Name("B3146_XuatKhoDinhDuong")
 public class B3146_XuatKhoDinhDuongAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private String ngayxuat;
   private String soluong;
   private XuatKhoDinhDuong xuatKDD;
   private String ngayhientai;
   private boolean lockedSaveButton;
   private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
   @In(scope=ScopeType.PAGE, required=false)
   @Out(scope=ScopeType.PAGE, required=false)
   private String strXuatKDD;
   @Logger
   private Log log;

   @Factory("strXuatKDD")
   public void init()
   {
     reset();
     this.strXuatKDD = "";
   }

   public void reset()
   {
     this.ngayxuat = this.sdf.format(new Date());
     this.xuatKDD = new XuatKhoDinhDuong();
     this.xuatKDD.setDtdmlaMaso(new DtDmLoaiAn());
     this.xuatKDD.setDtdmla2Maso(new DtDmLoaiAn2());
     this.xuatKDD.setDtdmnsxMaso(new DtDmNhaSxSpdd());
     this.xuatKDD.setDmkhoaMaso(new DmKhoa());
     this.xuatKDD.getDtdmlaMaso().setDtdmlaMaso(new Integer(2));
     this.soluong = "";
     this.ngayhientai = this.sdf.format(new Date());
     this.lockedSaveButton = false;
     FacesMessages.instance().clear();
   }

   public void saveXuatKDD()
   {
     this.log.info("begin save, loai xuat ma so = " + this.xuatKDD.getDtdmlaMaso().getDtdmlaMaso(), new Object[0]);
     try
     {
       this.log.info("Ma so loai an = " + this.xuatKDD.getDtdmlaMaso().getDtdmlaMaso(), new Object[0]);
       int tonkho = TonKhoDinhDuongDelegate.getInstance().tinhSoTon(this.xuatKDD.getDtdmlaMaso().getDtdmlaMaso(), this.xuatKDD.getDtdmla2Maso().getDtdmla2Maso(), this.xuatKDD.getDtdmlaMaso().getDtdmlaMaso().intValue() == 3 ? null : this.xuatKDD.getDtdmnsxMaso().getDtdmnsxMaso(), this.sdf.parse(this.ngayxuat));



       this.log.info("So ton kho  = " + tonkho, new Object[0]);
       if (tonkho < new Integer(this.soluong).intValue())
       {
         this.log.info("So luong xuat lon hon so luong ton kho", new Object[0]);
         FacesMessages.instance().add(IConstantsRes.SL_XUAT_KHONG_HOP_LE, new Object[] { this.soluong, Integer.valueOf(tonkho) });
         return;
       }
       if (this.xuatKDD.getDtdmlaMaso().getDtdmlaMaso().intValue() == 3) {
         this.xuatKDD.setDtdmnsxMaso(null);
       }
       this.xuatKDD.setXkddNgayxuat(this.sdf.parse(this.ngayxuat));
       this.xuatKDD.setXkddSoluong(new Float(this.soluong));










       XuatKhoDinhDuongDelegate.getInstance().create(this.xuatKDD);



       this.lockedSaveButton = true;
       FacesMessages.instance().add(IConstantsRes.INSERT_SUCCESS, new Object[0]);
       this.xuatKDD.setDtdmnsxMaso(new DtDmNhaSxSpdd());
     }
     catch (Exception e)
     {
       this.log.info("ERROR : " + e.toString(), new Object[0]);
       e.printStackTrace();
       FacesMessages.instance().add(IConstantsRes.INSERT_FAIL, new Object[0]);
       this.lockedSaveButton = false;
     }
     this.log.info("end save", new Object[0]);
   }

   public String getNgayxuat()
   {
     return this.ngayxuat;
   }

   public void setNgayxuat(String ngayxuat)
   {
     this.ngayxuat = ngayxuat;
   }

   public String getSoluong()
   {
     return this.soluong;
   }

   public void setSoluong(String soluong)
   {
     this.soluong = soluong;
   }

   public XuatKhoDinhDuong getXuatKDD()
   {
     return this.xuatKDD;
   }

   public void setXuatKDD(XuatKhoDinhDuong xuatKDD)
   {
     this.xuatKDD = xuatKDD;
   }

   public boolean isLockedSaveButton()
   {
     return this.lockedSaveButton;
   }

   public void setLockedSaveButton(boolean lockedSaveButton)
   {
     this.lockedSaveButton = lockedSaveButton;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.B3146_XuatKhoDinhDuongAction

 * JD-Core Version:    0.7.0.1

 */