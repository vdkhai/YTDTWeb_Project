 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienKhoaDelegate;
 import com.iesvn.yte.dieutri.delegate.NguoiDungDelegate;
 import com.iesvn.yte.dieutri.delegate.NguoiDungVaiTroDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.DtDmNhanvienKhoa;
 import com.iesvn.yte.entity.NguoiDung;
 import com.iesvn.yte.entity.NguoiDungVaiTro;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("B263_NhanVien")
 @Scope(ScopeType.SESSION)
 public class B263_Nhanvien
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DtDmNhanVien nhanvien;
   @DataModel
   private List<DtDmNhanVien> listNhanvien;
   @Logger
   private Log log;

   public DtDmNhanVien getNhanvien()
   {
     return this.nhanvien;
   }

   public void setNhanvien(DtDmNhanVien nhanvien)
   {
     this.nhanvien = nhanvien;
   }

   public List<DtDmNhanVien> getListNhanvien()
   {
     return this.listNhanvien;
   }

   public void setListNhanvien(List<DtDmNhanVien> listNhanvien)
   {
     this.listNhanvien = listNhanvien;
   }

   @Create
   public String init()
   {
     this.log.info("Init nhanvien...", new Object[0]);

     resetValue();

     return "/B2_Dieutri/B263_Nhanvien";
   }

   public void search()
   {
     this.log.info("Search nhanvien...", new Object[0]);

     this.listNhanvien = DieuTriUtilDelegate.getInstance().findByAllConditions("DtDmNhanVien", "dtdmnhanvienMa", "dtdmnhanvienTen", "dtdmnhanvienChon", this.nhanvien.getDtdmnhanvienMa(), this.nhanvien.getDtdmnhanvienTen(), true);

     this.log.info("list****************:" + this.listNhanvien.size(), new Object[0]);
     if (this.listNhanvien.size() == 0)
     {
       this.log.info("*********IConstantsRes.no_found*******:" + IConstantsRes.no_found, new Object[0]);
       FacesMessages.instance().add(IConstantsRes.no_found, new Object[0]);
     }
   }

   public void delete(int rowIndex)
   {
     this.log.info("Delete nhanvien...", new Object[0]);
     if (rowIndex != -1)
     {
       String ma = "";
       ma = ((DtDmNhanVien)this.listNhanvien.get(rowIndex)).getDtdmnhanvienMa();
       try
       {
         DtDmNhanVien be = (DtDmNhanVien)this.listNhanvien.get(rowIndex);

         be.setDtdmnhanvienChon(Boolean.valueOf(false));

         Date date = new Date();
         be.setDtdmnhanvienNgaygiocn(Double.valueOf(date.getTime()));
         if (be != null)
         {
           DtDmNhanvienKhoa nvkhoa = new DtDmNhanvienKhoa();
           nvkhoa = DtDmNhanVienKhoaDelegate.getInstance().findByMaNguoiDung(be);
           if (nvkhoa != null)
           {
             Date date1 = new Date();
             nvkhoa.setDtdmnhanvienKhoaChon(Boolean.valueOf(false));
             nvkhoa.setDtdmnhanvienKhoaNgaygiocn(Double.valueOf(date1.getTime()));
             DtDmNhanVienKhoaDelegate.getInstance().edit(nvkhoa);
           }
           if (be.getNdMaso() != null)
           {
             NguoiDung nguoidung = be.getNdMaso();
             List<NguoiDungVaiTro> lstvaitro = NguoiDungVaiTroDelegate.getInstance().findByNguoiDung(nguoidung.getNdMaso());
             for (NguoiDungVaiTro nguoiDungVaiTro : lstvaitro) {
               NguoiDungVaiTroDelegate.getInstance().remove(nguoiDungVaiTro);
             }
             be.setNdMaso(null);
             DieuTriUtilDelegate.getInstance().edit(be);
             NguoiDungDelegate.getInstance().remove(nguoidung);
           }
           else
           {
             DieuTriUtilDelegate.getInstance().edit(be);
           }
           this.listNhanvien.remove(rowIndex);
           FacesMessages.instance().add(IConstantsRes.nhanvien_de_su, new Object[] { ma });
         }
         else
         {
           FacesMessages.instance().add(IConstantsRes.nhanvien_de_su, new Object[] { ma });
         }
       }
       catch (Exception e)
       {
         this.log.error(e.toString(), new Object[0]);
         FacesMessages.instance().add(IConstantsRes.nhanvien_de_fa, new Object[] { ma });
       }
     }
   }

   public void reset()
   {
     this.log.info("Reset nhanvien....", new Object[0]);

     resetValue();
   }

   public String goMainPage()
   {
     this.log.info("Go main page...", new Object[0]);

     return "/MyMainForm";
   }

   private void resetValue()
   {
     this.nhanvien = new DtDmNhanVien();
     this.listNhanvien = new ArrayList();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.B263_Nhanvien

 * JD-Core Version:    0.7.0.1

 */