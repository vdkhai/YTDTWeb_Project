 package com.iesvn.yte.identity.extension;

 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.NguoiDungDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.entity.NguoiDung;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
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
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("changepassword")
 @Synchronized(timeout=6000000L)
 public class ChangePasswordAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static Logger log = Logger.getLogger(ChangePasswordAction.class);
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private Integer nhanVienMaso;
   private String nhanVienMa;
   private String password;
   private String passwordConfirm;
   private String currentUserName;
   private String loaiNguoiDung;
   private String maNguoiDung;

   @Begin(join=true)
   public String init(String loaiNguoiDung)
   {
     log.info("-----init()-----");
     reset();
     log.info("identity.getUsername():" + this.identity.getUsername());
     this.loaiNguoiDung = loaiNguoiDung;
     if ("admin".equals(loaiNguoiDung))
     {
       this.nhanVienMaso = null;
       this.nhanVienMa = null;
       this.maNguoiDung = "admin";
       return "ChangePassword";
     }
     DtDmNhanVien nhanvien = DtDmNhanVienDelegate.getInstance().findByNd(this.identity.getUsername());
     if (nhanvien == null) {
       return "";
     }
     this.nhanVienMaso = nhanvien.getDtdmnhanvienMaso();
     this.nhanVienMa = nhanvien.getDtdmnhanvienMa();

     this.maNguoiDung = nhanvien.getNdMaso(true).getNdTendangnhap();
     return "ChangePassword";
   }

   @End
   public void end() {}

   public String reset()
   {
     if ("admin".equals(this.loaiNguoiDung)) {
       this.maNguoiDung = "admin";
     }
     this.password = "";
     this.passwordConfirm = "";
     return null;
   }

   public String register()
   {
     log.info("-----register()-----");




















     NguoiDung nd = null;
     if ("admin".equals(this.loaiNguoiDung))
     {
       NguoiDungDelegate ndDelegate = NguoiDungDelegate.getInstance();
       nd = ndDelegate.findByMa("admin");
     }
     else
     {
       DtDmNhanVien nhanvien = DtDmNhanVienDelegate.getInstance().findByNd(this.identity.getUsername());
       if (nhanvien == null) {
         return "";
       }
       nd = nhanvien.getNdMaso();
     }
     log.info("-----nhan vien is not null");
     log.info("-----maNguoiDung: " + this.maNguoiDung);
     log.info("-----password: " + this.password);

     NguoiDungDelegate ndDelegate = NguoiDungDelegate.getInstance();

















     log.info("-----nguoi dung null");
     nd.setNdTendangnhap(this.maNguoiDung);
     nd.setNdMadangnhap(this.password);
     ndDelegate.edit(nd);
     reset();
     FacesMessages.instance().add(IConstantsRes.CHANGEPASS_SUCCESS, new Object[0]);




     return null;
   }

   public Integer getNhanVienMaso()
   {
     return this.nhanVienMaso;
   }

   public void setNhanVienMaso(Integer nhanVienMaso)
   {
     this.nhanVienMaso = nhanVienMaso;
   }

   public String getNhanVienMa()
   {
     return this.nhanVienMa;
   }

   public void setNhanVienMa(String nhanVienMa)
   {
     this.nhanVienMa = nhanVienMa;
   }

   public String getPassword()
   {
     return this.password;
   }

   public void setPassword(String password)
   {
     this.password = password;
   }

   public String getPasswordConfirm()
   {
     return this.passwordConfirm;
   }

   public void setPasswordConfirm(String passwordConfirm)
   {
     this.passwordConfirm = passwordConfirm;
   }

   public String getCurrentUserName()
   {
     return this.currentUserName;
   }

   public void setCurrentUserName(String currentUserName)
   {
     this.currentUserName = currentUserName;
   }

   public String getMaNguoiDung()
   {
     return this.maNguoiDung;
   }

   public void setMaNguoiDung(String maNguoiDung)
   {
     this.maNguoiDung = maNguoiDung;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.identity.extension.ChangePasswordAction

 * JD-Core Version:    0.7.0.1

 */