 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmLoaiThucPhamDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmLoaiThucPham;
 import com.iesvn.yte.entity.DmDonViTinh;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import org.apache.commons.beanutils.BeanUtils;
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
 @Name("B3153_ThemLoaiHang")
 public class B3153_ThemLoaiHangAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private DtDmLoaiThucPham loaiTp;
   private List<DtDmLoaiThucPham> listLoaiTp;
   @In(scope=ScopeType.PAGE, required=false)
   @Out(scope=ScopeType.PAGE, required=false)
   private String strLoaiTp;
   @Logger
   private Log log;

   @Factory("strLoaiTp")
   public void init()
   {
     this.loaiTp = new DtDmLoaiThucPham();
     this.loaiTp.setDmdonvitinhMaso(new DmDonViTinh());
     this.listLoaiTp = new ArrayList();
     this.listLoaiTp = DtDmLoaiThucPhamDelegate.getInstance().findAll();
     this.strLoaiTp = "";
   }

   public void saveLoaiTp()
   {
     this.log.info("begin save", new Object[0]);

     DtDmLoaiThucPham loaiTpTmp = new DtDmLoaiThucPham();
     DieuTriUtilDelegate utilDel = DieuTriUtilDelegate.getInstance();
     loaiTpTmp = (DtDmLoaiThucPham)utilDel.findByMa(this.loaiTp.getDtdmltpMa(), "DtDmLoaiThucPham", "dtdmltpMa");
     if (loaiTpTmp != null) {
       if (!loaiTpTmp.getDtdmltpMaso().equals(this.loaiTp.getDtdmltpMaso()))
       {
         FacesMessages.instance().add(IConstantsRes.MA_DA_TON_TAI, new Object[] { this.loaiTp.getDtdmltpMa() });
         return;
       }
     }
     this.loaiTp.setDtdmltpNgaygiocn(new Double(new Date().getTime()));
     DtDmLoaiThucPhamDelegate loaiTpDel = DtDmLoaiThucPhamDelegate.getInstance();
     if (this.loaiTp.getDtdmltpMaso() == null)
     {
       loaiTpDel.create(this.loaiTp);
       FacesMessages.instance().add(IConstantsRes.INSERT_SUCCESS, new Object[0]);
     }
     else
     {
       loaiTpDel.edit(this.loaiTp);
       FacesMessages.instance().add(IConstantsRes.UPDATE_SUCCESS, new Object[0]);
     }
     this.listLoaiTp = loaiTpDel.findAll();
     this.loaiTp = new DtDmLoaiThucPham();
     this.loaiTp.setDmdonvitinhMaso(new DmDonViTinh());
     this.log.info("End save, size = " + this.listLoaiTp.size(), new Object[0]);
   }

   public void edit(int index)
   {
     try
     {
       this.loaiTp = ((DtDmLoaiThucPham)BeanUtils.cloneBean(this.listLoaiTp.get(index)));
     }
     catch (Exception e)
     {
       this.loaiTp = new DtDmLoaiThucPham();
       this.loaiTp.setDmdonvitinhMaso(new DmDonViTinh());
       this.log.info(e.toString(), new Object[0]);
     }
     FacesMessages.instance().clear();
   }

   public DtDmLoaiThucPham getLoaiTp()
   {
     return this.loaiTp;
   }

   public void setLoaiTp(DtDmLoaiThucPham loaiTp)
   {
     this.loaiTp = loaiTp;
   }

   public List<DtDmLoaiThucPham> getListLoaiTp()
   {
     return this.listLoaiTp;
   }

   public void setListLoaiTp(List<DtDmLoaiThucPham> listLoaiTp)
   {
     this.listLoaiTp = listLoaiTp;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.B3153_ThemLoaiHangAction

 * JD-Core Version:    0.7.0.1

 */