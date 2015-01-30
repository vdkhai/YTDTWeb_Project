 package com.iesvn.yte.identity.extension;

 import com.iesvn.yte.dieutri.delegate.PcCumThuPhiDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmCum;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.PcCumThuPhi;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.text.ParseException;
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
 import org.jboss.seam.annotations.datamodel.DataModelSelection;
 import org.jboss.seam.faces.FacesMessages;

 @Scope(ScopeType.SESSION)
 @Name("quanlycumthuphi")
 @Synchronized(timeout=6000000L)
 public class QLCumThuPhiAction
   implements Serializable
 {
   private static final long serialVersionUID = -142440506671926180L;
   private static Logger log = Logger.getLogger(QLCumThuPhiAction.class);
   private String tungay;
   private String denngay;
   private Integer cumMaso;
   private String cumMa;
   private String cumTen;
   private Integer nhanvienMaso;
   private String nhanvienMa;
   private String nhanvienTen;
   private Integer quyen;
   private String update;
   @DataModel
   private List<PcCumThuPhi> listPCCTP;
   @DataModelSelection
   private PcCumThuPhi chitiet;
   private String nosuccess;

   @Create
   public void init()
   {
     resetValue();
   }

   public void resetValue()
   {
     log.info("---resetValue");
     this.listPCCTP = new ArrayList();
     this.nosuccess = (this.tungay = this.denngay = this.cumMa = this.nhanvienMa = this.nhanvienTen = this.cumTen = "");
     this.update = "false";
     this.nhanvienMaso = (this.cumMaso = this.quyen = Integer.valueOf(0));
   }

   public void displayCT(int index)
   {
     log.info("---displayCT");
     this.chitiet = ((PcCumThuPhi)this.listPCCTP.get(index));
     this.tungay = formatDate(this.chitiet.getPcctpTungay());
     this.denngay = formatDate(this.chitiet.getPcctpDenngay());
     this.cumMa = this.chitiet.getDtdmcumMa().getDtdmcumMa();
     this.nhanvienMa = this.chitiet.getDtdmnhanvienMa().getDtdmnhanvienMa();
     this.quyen = Integer.valueOf(String.valueOf(this.chitiet.getQuyen()));
     this.update = "true";
   }

   public void updateCT()
   {
     log.info("---updateCT");
     DtDmNhanVien nv = new DtDmNhanVien();
     nv.setDtdmnhanvienMaso(this.nhanvienMaso);
     nv.setDtdmnhanvienMa(this.nhanvienMa);
     nv.setDtdmnhanvienTen(this.nhanvienTen);
     DtDmCum cum = new DtDmCum();
     cum.setDtdmcumMaso(this.cumMaso);
     cum.setDtdmcumMa(this.cumMa);
     cum.setDtdmcumTen(this.cumTen);
     if (this.update.equals("true"))
     {
       this.chitiet.setQuyen(Short.valueOf(String.valueOf(this.quyen)).shortValue());
       this.chitiet.setDtdmnhanvienMa(nv);
       PcCumThuPhiDelegate.getInstance().edit(this.chitiet);
       this.update = "false";
       this.nosuccess = (this.tungay = this.denngay = this.cumMa = this.nhanvienMa = "");
       this.nhanvienMaso = (this.cumMaso = this.quyen = Integer.valueOf(0));
     }
     else
     {
       PcCumThuPhi obj = new PcCumThuPhi();
       obj.setQuyen(Short.valueOf(String.valueOf(this.quyen)).shortValue());
       obj.setDtdmnhanvienMa(nv);
       obj.setDtdmcumMa(cum);
       obj.setPcctpTungay(Utils.getDBDate("00:00", this.tungay).getTime());
       obj.setPcctpDenngay(Utils.getDBDate("00:00", this.denngay).getTime());
       PcCumThuPhiDelegate.getInstance().create(obj);
       this.listPCCTP.add(obj);
       this.nosuccess = (this.tungay = this.denngay = this.cumMa = this.nhanvienMa = "");
       this.nhanvienMaso = (this.cumMaso = this.quyen = Integer.valueOf(0));
     }
   }

   public void search()
   {
     log.info("---search");
     this.listPCCTP = new ArrayList();
     Date tn = null;Date dn = null;
     String cum = null;
     if (!this.tungay.equals("")) {
       try
       {
         tn = new SimpleDateFormat("dd/MM/yyyy").parse(this.tungay);
       }
       catch (ParseException e)
       {
         e.printStackTrace();
       }
     }
     if (!this.denngay.equals("")) {
       try
       {
         dn = new SimpleDateFormat("dd/MM/yyyy").parse(this.denngay);
       }
       catch (ParseException e)
       {
         e.printStackTrace();
       }
     }
     if (!this.cumMa.equals("")) {
       cum = this.cumMa;
     }
     List<PcCumThuPhi> list = PcCumThuPhiDelegate.getInstance().findPcCumThuPhi(tn, dn, cum);
     if ((list != null) && (list.size() > 0)) {
       this.listPCCTP = list;
     } else {
       FacesMessages.instance().add(IConstantsRes.PCCTP_SEARCH_NULL, new Object[0]);
     }
   }

   public void delete(int index)
   {
     log.info("---delete");
     PcCumThuPhi del = (PcCumThuPhi)this.listPCCTP.get(index);
     this.listPCCTP.remove(index);
     try
     {
       PcCumThuPhiDelegate.getInstance().remove(del);
       FacesMessages.instance().add(IConstantsRes.PCCTP_DEL_SUC, new Object[0]);
     }
     catch (Exception ex)
     {
       FacesMessages.instance().add(IConstantsRes.PCCTP_DEL_FAIL, new Object[0]);
     }
   }

   public String formatDate(Date date)
   {
     return date == null ? "" : new SimpleDateFormat("dd/MM/yyyy").format(date);
   }

   public Integer getNhanvienMaso()
   {
     return this.nhanvienMaso;
   }

   public void setNhanvienMaso(Integer nhanvienMaso)
   {
     this.nhanvienMaso = nhanvienMaso;
   }

   public Integer getQuyen()
   {
     return this.quyen;
   }

   public void setQuyen(Integer quyen)
   {
     this.quyen = quyen;
   }

   public String getNhanvienMa()
   {
     return this.nhanvienMa;
   }

   public void setNhanvienMa(String nhanvienMa)
   {
     this.nhanvienMa = nhanvienMa;
   }

   public PcCumThuPhi getChitiet()
   {
     return this.chitiet;
   }

   public void setChitiet(PcCumThuPhi chitiet)
   {
     this.chitiet = chitiet;
   }

   public String getUpdate()
   {
     return this.update;
   }

   public void setUpdate(String update)
   {
     this.update = update;
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

   public String getCumTen()
   {
     return this.cumTen;
   }

   public void setCumTen(String cumTen)
   {
     this.cumTen = cumTen;
   }

   public String getNosuccess()
   {
     return this.nosuccess;
   }

   public void setNosuccess(String nosuccess)
   {
     this.nosuccess = nosuccess;
   }

   public List<PcCumThuPhi> getListPCCTP()
   {
     return this.listPCCTP;
   }

   public void setListPCCTP(List<PcCumThuPhi> listPCCTP)
   {
     this.listPCCTP = listPCCTP;
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

   public String getCumMa()
   {
     return this.cumMa;
   }

   public void setCumMa(String cumMa)
   {
     this.cumMa = cumMa;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.identity.extension.QLCumThuPhiAction

 * JD-Core Version:    0.7.0.1

 */