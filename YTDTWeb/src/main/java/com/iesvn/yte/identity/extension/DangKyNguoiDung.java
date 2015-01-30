 package com.iesvn.yte.identity.extension;

 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.NguoiDungDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.entity.NguoiDung;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.List;
 import javax.faces.model.SelectItem;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.SESSION)
 @Name("Dangkynguoidung")
 @Synchronized(timeout=6000000L)
 public class DangKyNguoiDung
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static Logger log = Logger.getLogger(DangKyNguoiDung.class);
   private String username;
   private Integer nhanVienMaso;
   private String nhanVienMa;
   private String password;
   private String passwordConfirm;
   private String currentUserName;
   private String nhanVienTen;
   private DtDmNhanVienDelegate dtDmNhanVienDelegate;

   @Create
   public void init()
   {
     log.info("-----init()-----");

     this.dtDmNhanVienDelegate = DtDmNhanVienDelegate.getInstance();
     this.listDtDmNhanViens = new ArrayList();
     for (DtDmNhanVien each : this.dtDmNhanVienDelegate.findAll()) {
       this.listDtDmNhanViens.add(new SelectItem(each.getDtdmnhanvienTen()));
     }
     reset();
   }

   public String reset()
   {
     this.nhanVienMaso = null;
     this.nhanVienMa = "";
     this.nhanVienTen = "";
     this.password = "";
     this.passwordConfirm = "";

     return null;
   }

   public void displayInfor()
   {
     log.info("-----displayInfor()-----");
     this.username = this.nhanVienMa;
     DtDmNhanVienDelegate delegate = DtDmNhanVienDelegate.getInstance();
     DtDmNhanVien nhanvien = null;
     if (this.nhanVienMaso != null) {
       try
       {
         nhanvien = delegate.find(this.nhanVienMaso);
       }
       catch (Exception e)
       {
         e.printStackTrace();
       }
     }
     log.info("-----nhanvien: " + nhanvien);
     NguoiDung nd = null;
     if (nhanvien == null)
     {
       reset();
       FacesMessages.instance().add(IConstantsRes.NHAN_VIEN_KHONG_TIM_THAY, new Object[] { this.username });
     }
     else
     {
       nd = nhanvien.getNdMaso();
       if (nd != null)
       {
         this.username = nd.getNdTendangnhap();
         this.currentUserName = this.username;
         this.password = nd.getNdMadangnhap();
       }
     }
   }

   public String register()
   {
     log.info("-----register()-----");


     DtDmNhanVienDelegate delegate = DtDmNhanVienDelegate.getInstance();
     DtDmNhanVien nhanvien = null;
     if (this.nhanVienMaso != null) {
       try
       {
         nhanvien = delegate.find(this.nhanVienMaso);
       }
       catch (Exception e)
       {
         e.printStackTrace();
       }
     }
     log.info("-----nhanvien: " + nhanvien);
     if (nhanvien == null)
     {
       log.info("-----nhan vien is null");
       reset();
       FacesMessages.instance().add(IConstantsRes.NHAN_VIEN_KHONG_TIM_THAY, new Object[] { this.username });
       return null;
     }
     log.info("-----nhan vien is not null");
     log.info("-----username: " + this.username);
     NguoiDungDelegate ndDelegate = NguoiDungDelegate.getInstance();

     NguoiDung nd = new NguoiDung();
     nd = ndDelegate.findByMa(this.username);
     if (nd == null)
     {
       log.info("-----nguoi dung is null");
       nd = new NguoiDung();
       nd.setNdTendangnhap(this.username.toUpperCase());
       nd.setNdMadangnhap(this.password);
       ndDelegate.createNguoiDung(nd, nhanvien);
       reset();
       FacesMessages.instance().add(IConstantsRes.REGISTER_SUCCESS, new Object[0]);
     }
     else
     {
       log.info("-----nguoi dung null");
       nd.setNdTendangnhap(this.username.toUpperCase());
       nd.setNdMadangnhap(this.password);
       ndDelegate.edit(nd);
       reset();
       FacesMessages.instance().add(IConstantsRes.REGISTER_SUCCESS, new Object[0]);
     }
     return null;
   }

   public String getUsername()
   {
     return this.username;
   }

   public void setUsername(String username)
   {
     this.username = username;
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

   private List<SelectItem> listDtDmNhanViens = new ArrayList();

   public List<SelectItem> getListDtDmNhanViens()
   {
     return this.listDtDmNhanViens;
   }

   public void setListDtDmNhanViens(List<SelectItem> listDtDmNhanViens)
   {
     this.listDtDmNhanViens = listDtDmNhanViens;
   }

   public String getNhanVienTen()
   {
     return this.nhanVienTen;
   }

   public void setNhanVienTen(String nhanVienTen)
   {
     this.nhanVienTen = nhanVienTen;
   }

   public void onblurTenNhanVienAction()
   {
     log.info("-----BEGIN onblurTenNhanVienAction()-----");
     DtDmNhanVien nhanvien = this.dtDmNhanVienDelegate.findByTenNhanVien(this.nhanVienTen);
     if (nhanvien != null)
     {
       this.nhanVienMaso = nhanvien.getDtdmnhanvienMaso();
       this.nhanVienMa = nhanvien.getDtdmnhanvienMa();
       log.info("-----DA THAY DTDMNHANVIEN-----");
     }
   }

   public void onblurMaNhanVienAction()
   {
     log.info("-----BEGIN onblurMaNhanVienAction()-----");
     DtDmNhanVien nhanvien = this.dtDmNhanVienDelegate.findByMaNhanVien(this.nhanVienMa);
     if (nhanvien != null)
     {
       this.nhanVienMaso = nhanvien.getDtdmnhanvienMaso();
       this.nhanVienTen = nhanvien.getDtdmnhanvienTen();
       log.info("-----DA THAY DTDMNHANVIEN-----");
     }
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.identity.extension.DangKyNguoiDung

 * JD-Core Version:    0.7.0.1

 */