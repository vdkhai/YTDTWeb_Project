 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.NhapKhoDinhDuongDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiAn;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiAn2;
 import com.iesvn.yte.dieutri.entity.DtDmNhaSxSpdd;
 import com.iesvn.yte.dieutri.entity.NhapKhoDinhDuong;
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
 @Name("B3145_NhapKhoDinhDuong")
 public class B3145_NhapKhoDinhDuongAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private String ngaynhap;
   private String soluong;
   private NhapKhoDinhDuong nhapKDD;
   private String ngayhientai;
   private boolean lockedSaveButton;
   private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
   @In(scope=ScopeType.PAGE, required=false)
   @Out(scope=ScopeType.PAGE, required=false)
   private String strNhapKDD;
   @Logger
   private Log log;

   @Factory("strNhapKDD")
   public void init()
   {
     reset();
     this.strNhapKDD = "";
   }

   public void reset()
   {
     this.ngaynhap = this.sdf.format(new Date());
     this.nhapKDD = new NhapKhoDinhDuong();
     this.nhapKDD.setDtdmlaMaso(new DtDmLoaiAn());
     this.nhapKDD.setDtdmla2Maso(new DtDmLoaiAn2());
     this.nhapKDD.setDtdmnsxMaso(new DtDmNhaSxSpdd());
     this.nhapKDD.getDtdmlaMaso().setDtdmlaMaso(new Integer(2));
     this.soluong = "";
     this.ngayhientai = this.sdf.format(new Date());
     this.lockedSaveButton = false;
     FacesMessages.instance().clear();
   }

   public void saveNhapKDD()
   {
     this.log.info("begin save, loai nhap ma so = " + this.nhapKDD.getDtdmlaMaso().getDtdmlaMaso(), new Object[0]);
     try
     {
       if (this.nhapKDD.getDtdmlaMaso().getDtdmlaMaso().intValue() == 3) {
         this.nhapKDD.setDtdmnsxMaso(null);
       }
       this.nhapKDD.setNkddNgaynhap(this.sdf.parse(this.ngaynhap));
       this.nhapKDD.setNkddSoluong(new Float(this.soluong));





















       NhapKhoDinhDuongDelegate.getInstance().create(this.nhapKDD);



       this.lockedSaveButton = true;
       FacesMessages.instance().add(IConstantsRes.INSERT_SUCCESS, new Object[0]);
       this.nhapKDD.setDtdmnsxMaso(new DtDmNhaSxSpdd());
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

   public NhapKhoDinhDuong getNhapKDD()
   {
     return this.nhapKDD;
   }

   public void setNhapKDD(NhapKhoDinhDuong nhapKDD)
   {
     this.nhapKDD = nhapKDD;
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

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.B3145_NhapKhoDinhDuongAction

 * JD-Core Version:    0.7.0.1

 */