 package com.iesvn.yte.identity.extension;

 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.NguoiDungVaiTroDelegate;
 import com.iesvn.yte.dieutri.delegate.VaiTroDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.entity.NguoiDung;
 import com.iesvn.yte.entity.NguoiDungVaiTro;
 import com.iesvn.yte.entity.VaiTro;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Iterator;
 import java.util.List;
 import javax.faces.model.SelectItem;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.SESSION)
 @Name("PhanQuyenAction")
 public class PhanQuyenAction
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @DataModel
   private List<QuyenExt> ctQuyenExts;
   private static Logger log = Logger.getLogger(PhanQuyenAction.class);
   private Integer nhanVienMaso;
   private String nhanVienMa;
   private String nhanVienTen;
   private DtDmNhanVienDelegate dtDmNhanVienDelegate;

   public void displayInfor()
   {
     log.info("-----displayInfor()-----");
     NguoiDungVaiTroDelegate ndVtDelegate = NguoiDungVaiTroDelegate.getInstance();
     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     DtDmNhanVien nv = nvDelegate.find(this.nhanVienMaso);

     log.info("-----nhanVienMaso:" + this.nhanVienMaso);
     NguoiDung nd = nv.getNdMaso();
     Iterator i$;
     if (nd != null)
     {
       List<NguoiDungVaiTro> lstNdVt = ndVtDelegate.findByNguoiDung(nd.getNdMaso());
        NguoiDungVaiTro ndvt;
       if (lstNdVt != null)
       {
         log.info("-----lstNdVt is not null");
         log.info("-----lstNdVt.size(): " + lstNdVt.size());
         for (i$ = lstNdVt.iterator(); i$.hasNext();)
         {
           ndvt = (NguoiDungVaiTro)i$.next();
           if (this.ctQuyenExts != null)
           {
             log.info("-----ctQuyenExts is not null--ctQuyenExts:" + ndvt.getNdvaitroMaso());
             for (QuyenExt quyen : this.ctQuyenExts)
             {
               log.info("-----ctQuyenExts is not null");
               if ((quyen != null) && (quyen.getVaitro() != null)) {
                 if (quyen.getVaitro().getVaitroMaso().intValue() == ndvt.getVaitroMaso().getVaitroMaso().intValue()) {
                   quyen.setChon(new Boolean(true));
                 }
               }
             }
           }
         }
       }
     }

   }

   @Create
   public void init()
   {
     log.info("begin init()");
     this.ctQuyenExts = new ArrayList();
     VaiTroDelegate vtDelegate = VaiTroDelegate.getInstance();
     List<VaiTro> lstVaiTro = vtDelegate.findAll();
     if (lstVaiTro != null) {
       for (VaiTro vt : lstVaiTro)
       {
         QuyenExt quyenext = new QuyenExt();
         quyenext.setVaitro(vt);
         quyenext.setChon(new Boolean(false));
         this.ctQuyenExts.add(quyenext);
       }
     }
     this.dtDmNhanVienDelegate = DtDmNhanVienDelegate.getInstance();
     for (DtDmNhanVien each : this.dtDmNhanVienDelegate.findAll()) {
       this.listDtDmNhanViens.add(new SelectItem(each.getDtdmnhanvienTen()));
     }
     log.info("end init()");
   }

   public List<QuyenExt> getCtQuyenExts()
   {
     return this.ctQuyenExts;
   }

   public void setCtQuyenExts(List<QuyenExt> ctQuyenExts)
   {
     this.ctQuyenExts = ctQuyenExts;
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

   public void ghiNhan()
   {
     log.info("-----ghiNhan()-----");
     VaiTroDelegate vtDelegate = VaiTroDelegate.getInstance();
     DtDmNhanVienDelegate dtdmNVDelegate = DtDmNhanVienDelegate.getInstance();
     DtDmNhanVien nhanvien = dtdmNVDelegate.find(this.nhanVienMaso);
     NguoiDung nd = null;
     if (nhanvien != null)
     {
       log.info("-----nhanvien is not null: " + nhanvien);
       if (nhanvien.getNdMaso() != null)
       {
         nd = nhanvien.getNdMaso();
       }
       else
       {
         FacesMessages.instance().add(IConstantsRes.CAP_QUYEN_THAT_BAI, new Object[0]);
         return;
       }
     }
     List<VaiTro> lstVaiTro = new ArrayList();
     if (this.ctQuyenExts != null)
     {
       log.info("-----ctQuyenExts is not null: " + this.ctQuyenExts);
       for (QuyenExt quyen : this.ctQuyenExts)
       {
         log.info("-----quyen: " + quyen);
         if ((quyen.getChon() != null) && (quyen.getChon().booleanValue() == true)) {
           lstVaiTro.add(quyen.getVaitro());
         }
       }
     }
     vtDelegate.capNhatVaiTro(this.nhanVienMaso, nd, lstVaiTro);
     FacesMessages.instance().add(IConstantsRes.CAP_QUYEN_THANH_CONG, new Object[0]);
   }

   public void reset()
   {
     log.info("-----reset()-----");
     this.nhanVienMaso = new Integer("0");
     this.nhanVienMa = "";
     this.nhanVienTen = "";
     for (QuyenExt quyen : this.ctQuyenExts) {
       quyen.setChon(Boolean.valueOf(false));
     }
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

 * Qualified Name:     com.iesvn.yte.identity.extension.PhanQuyenAction

 * JD-Core Version:    0.7.0.1

 */