 package com.iesvn.yte.dieutri.hsba.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.entity.DmLoaiKhoa;
 import com.iesvn.yte.entity.DmNhomKhoa;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import javax.faces.model.SelectItem;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Logger;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("DmKhoaPhong_add")
 @Scope(ScopeType.SESSION)
 public class DmKhoaPhongExt_add
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private static final int emptyValueCbo = -1;
   private static final String emptyLabelCbo = "...";
   @In(required=false)
   @Out(required=false)
   private DmKhoa khoaphong;
   private List<SelectItem> listLoaiKhoa;
   private List<SelectItem> listNhomKhoa;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmKhoaPhongExt_add....", new Object[0]);

     resetValue();
     initComboxLoaiKhoa();
     initComboxNhomKhoa();

     return "/B2_Dieutri/B262_Khoaphong_add";
   }

   public void save(int loaiKhoaId, int nhomKhoaId)
   {
     this.log.info("Save  DmKhoaPhongExt_add....", new Object[0]);

     String ma = "";
     Date date = new Date();

     ma = this.khoaphong.getDmkhoaMa();
     this.khoaphong.setDmkhoaNgaygiocn(Double.valueOf(date.getTime()));
     this.khoaphong.setDmkhoaDt(Boolean.valueOf(true));
     if (loaiKhoaId == -1) {
       this.khoaphong.setDmloaikhoaMa(null);
     }
     if (nhomKhoaId == -1) {
       this.khoaphong.setDmnhomkhoaMaso(null);
     }
     try
     {
       DieuTriUtilDelegate.getInstance().create(this.khoaphong);
       FacesMessages.instance().add(IConstantsRes.khoaphong_cr_su, new Object[] { ma });
       reset();
     }
     catch (Exception e)
     {
       this.log.error(e.toString(), new Object[0]);
       FacesMessages.instance().add(IConstantsRes.khoaphong_cr_fa, new Object[] { ma });
     }
   }

   public String goBack()
   {
     this.log.info("GoBack  DmKhoaPhongExt_add....", new Object[0]);

     return "/B2_Dieutri/B262_Khoaphong";
   }

   public void reset()
   {
     this.log.info("Reset  DmKhoaPhongExt_add....", new Object[0]);

     resetValue();
   }

   private void resetValue()
   {
     this.khoaphong = new DmKhoa();
     this.khoaphong.setDmloaikhoaMa(new DmLoaiKhoa());
     this.khoaphong.setDmnhomkhoaMaso(new DmNhomKhoa());
   }

   private void initComboxLoaiKhoa()
   {
     this.listLoaiKhoa = new ArrayList();

     SelectItem item = new SelectItem(Integer.valueOf(-1), "...");
     this.listLoaiKhoa.add(item);

     List<DmLoaiKhoa> lstempLoaiKhoa = DieuTriUtilDelegate.getInstance().findAll("DmLoaiKhoa", "dmloaikhoaDt", true);
     for (DmLoaiKhoa dmLoaiKhoa : lstempLoaiKhoa)
     {
       item = new SelectItem(dmLoaiKhoa.getDmloaikhoaMaso(), dmLoaiKhoa.getDmloaikhoaTen());
       this.listLoaiKhoa.add(item);
     }
   }

   private void initComboxNhomKhoa()
   {
     this.listNhomKhoa = new ArrayList();

     SelectItem item = new SelectItem(Integer.valueOf(-1), "...");
     this.listNhomKhoa.add(item);

     List<DmNhomKhoa> lstempNhomKhoa = DieuTriUtilDelegate.getInstance().findAll("DmNhomKhoa", "dmnhomkhoaDt", true);
     for (DmNhomKhoa dmNhomKhoa : lstempNhomKhoa)
     {
       item = new SelectItem(dmNhomKhoa.getDmnhomkhoaMaso(), dmNhomKhoa.getDmnhomkhoaTen());
       this.listNhomKhoa.add(item);
     }
   }

   public DmKhoa getKhoaphong()
   {
     return this.khoaphong;
   }

   public void setKhoaphong(DmKhoa khoaphong)
   {
     this.khoaphong = khoaphong;
   }

   public List<SelectItem> getListLoaiKhoa()
   {
     return this.listLoaiKhoa;
   }

   public void setListLoaiKhoa(List<SelectItem> listLoaiKhoa)
   {
     this.listLoaiKhoa = listLoaiKhoa;
   }

   public List<SelectItem> getListNhomKhoa()
   {
     return this.listNhomKhoa;
   }

   public void setListNhomKhoa(List<SelectItem> listNhomKhoa)
   {
     this.listNhomKhoa = listNhomKhoa;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.hsba.action.DmKhoaPhongExt_add

 * JD-Core Version:    0.7.0.1

 */