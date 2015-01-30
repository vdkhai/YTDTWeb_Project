 package com.iesvn.yte.identity.extension;

 import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmCum;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.List;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.SESSION)
 @Name("phancongcumthuphi")
 @Synchronized(timeout=6000000L)
 public class PCCumThuPhiAction
   implements Serializable
 {
   private static final long serialVersionUID = -460623918265282727L;
   private static Logger log = Logger.getLogger(PCCumThuPhiAction.class);
   private String tungay;
   private String denngay;
   private Integer nhanvienMaso;
   private String nhanvienMa;
   private String nhanvienTen;
   private Integer cumMaso;
   private String cumMa;
   private String cumTen;
   private Integer quyen;
   @DataModel
   private List<PcCumThuPhi> listPCCTP;
   private String nosuccess;

   @Create
   public void init()
   {
     resetValue();
   }

   public String getNosuccess()
   {
     return this.nosuccess;
   }

   public void setNosuccess(String nosuccess)
   {
     this.nosuccess = nosuccess;
   }

   public void resetValue()
   {
     log.info("---resetValue");
     this.listPCCTP = new ArrayList();
     this.tungay = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
     Calendar cal = Calendar.getInstance();
     cal.add(5, 5);
     this.denngay = new SimpleDateFormat("dd/MM/yyyy").format(cal.getTime());
     this.nhanvienMa = (this.nhanvienTen = this.cumMa = this.cumTen = "");
     this.quyen = (this.nhanvienMaso = this.cumMaso = Integer.valueOf(0));
     this.nosuccess = "false";
   }

   public void enter()
   {
     log.info("---enter");
     DtDmCum cum = new DtDmCum();
     cum.setDtdmcumMaso(this.cumMaso);
     cum.setDtdmcumMa(this.cumMa);
     cum.setDtdmcumTen(this.cumTen);
     DtDmNhanVien nv = new DtDmNhanVien();
     nv.setDtdmnhanvienMaso(this.nhanvienMaso);
     nv.setDtdmnhanvienMa(this.nhanvienMa);
     nv.setDtdmnhanvienTen(this.nhanvienTen);
     PcCumThuPhi chitiet = new PcCumThuPhi();
     chitiet.setDtdmnhanvienMa(nv);
     chitiet.setDtdmcumMa(cum);
     chitiet.setQuyen(Short.valueOf(String.valueOf(this.quyen)).shortValue());
     chitiet.setPcctpTungay(Utils.getDBDate("00:00", this.tungay).getTime());
     chitiet.setPcctpDenngay(Utils.getDBDate("00:00", this.denngay).getTime());
     this.listPCCTP.add(chitiet);
   }

   public void delete(int index)
   {
     log.info("---delete");
     this.listPCCTP.remove(index);
   }

   public void ghinhan()
   {
     log.info("---ghinhan");
     if (this.listPCCTP.size() > 0)
     {
       int rs = PcCumThuPhiDelegate.getInstance().ghinhan(this.listPCCTP);
       if (rs == 1)
       {
         FacesMessages.instance().add(IConstantsRes.INSERT_SUCCESS, new Object[0]);
       }
       else if (rs == 0)
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.INSERT_FAIL, new Object[0]);
       }
       else if (rs == -1)
       {
         this.nosuccess = "true";
         FacesMessages.instance().add(IConstantsRes.PCCTP_INS_FAIL, new Object[0]);
       }
     }
     else
     {
       this.nosuccess = "true";
       FacesMessages.instance().add(IConstantsRes.PCCTP_NULL, new Object[0]);
     }
   }

   public String getTungay()
   {
     return this.tungay;
   }

   public void setTungay(String tungay)
   {
     this.tungay = tungay;
   }

   public String getDenngay()
   {
     return this.denngay;
   }

   public void setDenngay(String denngay)
   {
     this.denngay = denngay;
   }

   public List<PcCumThuPhi> getListPCCTP()
   {
     return this.listPCCTP;
   }

   public void setListPCCTP(List<PcCumThuPhi> listPCCTP)
   {
     this.listPCCTP = listPCCTP;
   }

   public Integer getNhanvienMaso()
   {
     return this.nhanvienMaso;
   }

   public void setNhanvienMaso(Integer nhanvienMaso)
   {
     this.nhanvienMaso = nhanvienMaso;
   }

   public String getNhanvienMa()
   {
     return this.nhanvienMa;
   }

   public void setNhanvienMa(String nhanvienMa)
   {
     this.nhanvienMa = nhanvienMa;
   }

   public String getNhanvienTen()
   {
     return this.nhanvienTen;
   }

   public void setNhanvienTen(String nhanvienTen)
   {
     this.nhanvienTen = nhanvienTen;
   }

   public Integer getCumMaso()
   {
     return this.cumMaso;
   }

   public void setCumMaso(Integer cumMaso)
   {
     this.cumMaso = cumMaso;
   }

   public String getCumMa()
   {
     return this.cumMa;
   }

   public void setCumMa(String cumMa)
   {
     this.cumMa = cumMa;
   }

   public String getCumTen()
   {
     return this.cumTen;
   }

   public void setCumTen(String cumTen)
   {
     this.cumTen = cumTen;
   }

   public Integer getQuyen()
   {
     return this.quyen;
   }

   public void setQuyen(Integer quyen)
   {
     this.quyen = quyen;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.identity.extension.PCCumThuPhiAction

 * JD-Core Version:    0.7.0.1

 */