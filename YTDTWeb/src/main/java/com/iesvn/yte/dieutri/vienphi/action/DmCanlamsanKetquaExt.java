 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmClsBangGiaDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmClsKetQuaDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmClsBangGia;
 import com.iesvn.yte.dieutri.entity.DtDmClsKetQua;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.entity.DmHocVi;
 import com.iesvn.yte.entity.DmKhoa;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.Serializable;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.PreparedStatement;
 import java.sql.SQLException;
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
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.log.Log;

 @Name("DmCanlamsanKetqua")
 @Scope(ScopeType.SESSION)
 public class DmCanlamsanKetquaExt
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   @In(required=false)
   @Out(required=false)
   private DtDmClsKetQua dtDmClsKetQua;
   @DataModel
   private List<DtDmClsKetQua> listDtDmClsKetQua;
   private List<SelectItem> listKhoaCLS;
   private List<SelectItem> listClsBanggia;
   private Integer dmkhoaMaso;
   private Integer dtdmclsbgMaso;
   private List<String> dmSynchronize;
   @Logger
   private Log log;

   @Create
   public String init()
   {
     this.log.info("Init DmCanlamsanKetquaExt....", new Object[0]);

     resetValue();

     return "/B3_Vienphi/ThuVienPhi/B3254_Canlamsanketqua";
   }

   public void displayCLS()
   {
     this.log.info("Init displayCLS....", new Object[0]);
     this.listClsBanggia = new ArrayList();
     DtDmClsBangGiaDelegate dtDmClsBangGiaDelegate = DtDmClsBangGiaDelegate.getInstance();
     List<DtDmClsBangGia> temp = (ArrayList)dtDmClsBangGiaDelegate.getDtDmClsBangGiaByMaSoKhoa(this.dmkhoaMaso);
     SelectItem itemTemp = new SelectItem(null, "---");
     this.listClsBanggia.add(itemTemp);
     if (temp != null) {
       for (DtDmClsBangGia dtDmClsBangGia : temp) {
         if ((dtDmClsBangGia.getDtdmclsbgXn() != null) && (dtDmClsBangGia.getDtdmclsbgXn().booleanValue() == true))
         {
           SelectItem item = new SelectItem(dtDmClsBangGia.getDtdmclsbgMaso(), dtDmClsBangGia.getDtdmclsbgDiengiai());
           this.listClsBanggia.add(item);
         }
       }
     }
   }

   public void synchronizeDataDmLabconn()
     throws ClassNotFoundException, SQLException
   {
     Connection conn;
     try
     {
       if (IConstantsRes.KET_NOI_MAY_XET_NGHIEM.equals("YES"))
       {
         this.log.info("Init synchronizeDataDmLabconn....", new Object[0]);
         String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
         String url = "jdbc:sqlserver://" + IConstantsRes.LABCONN_SERVER + ":" + IConstantsRes.LABCONN_PORT + ";useUnicode=true;characterEncoding=UTF-8;" + ";databaseName=" + IConstantsRes.LABCONN_DATABASE;
         String username = IConstantsRes.LABCONN_USERNAME;
         String password = IConstantsRes.LABCONN_PASSWORD;
         Class.forName(driver);
         conn = DriverManager.getConnection(url, username, password);
         String query1 = " DELETE FROM [" + IConstantsRes.LABCONN_DATABASE + "].[dbo].[tbl_Location_HIS]; ";
         query1 = query1 + " DELETE FROM [" + IConstantsRes.LABCONN_DATABASE + "].[dbo].[tbl_Doctor_HIS]; ";
         query1 = query1 + " DELETE FROM [" + IConstantsRes.LABCONN_DATABASE + "].[dbo].[tbl_Object_HIS]; ";
         query1 = query1 + " DELETE FROM [" + IConstantsRes.LABCONN_DATABASE + "].[dbo].[tbl_TestCode_HIS]; ";
         PreparedStatement stmt1 = conn.prepareStatement(query1);
         stmt1.executeUpdate();
         conn.close();
         conn = DriverManager.getConnection(url, username, password);
         for (String temp : this.dmSynchronize)
         {
           this.log.info("dm " + temp, new Object[0]);
           if (temp.equals("DmKhoa"))
           {
             List<DmKhoa> list = DieuTriUtilDelegate.getInstance().findAll("DmKhoa", "dmkhoaDt", true);
             for (DmKhoa dmKhoa : list) {
               if ((dmKhoa.getDmkhoaCls() != null) && (dmKhoa.getDmkhoaCls().booleanValue() == true))
               {
                 String query = " INSERT INTO [" + IConstantsRes.LABCONN_DATABASE + "].[dbo].[tbl_Location_HIS]([LocationID],[LocationName]) VALUES ";
                 query = query + "('" + dmKhoa.getDmkhoaMa() + "',N'" + dmKhoa.getDmkhoaTen() + "')";
                 PreparedStatement stmt = conn.prepareStatement(query);
                 stmt.executeUpdate();
               }
             }
           }
           if (temp.equals("DtDmNhanVien"))
           {
             List<DtDmNhanVien> list = DieuTriUtilDelegate.getInstance().findAll("DtDmNhanVien");
             for (DtDmNhanVien dtDmNhanVien : list) {
               if ((dtDmNhanVien.getDmhocviMaso() != null) && ((dtDmNhanVien.getDmhocviMaso().getDmhocviMaso().equals(Integer.valueOf(2))) || (dtDmNhanVien.getDmhocviMaso().getDmhocviMaso().equals(Integer.valueOf(3))) || (dtDmNhanVien.getDmhocviMaso().getDmhocviMaso().equals(Integer.valueOf(4)))))
               {
                 String query = " INSERT INTO [" + IConstantsRes.LABCONN_DATABASE + "].[dbo].[tbl_Doctor_HIS]([DoctorID],[DoctorName]) VALUES ";
                 query = query + "('" + dtDmNhanVien.getDtdmnhanvienMa() + "',N'" + dtDmNhanVien.getDtdmnhanvienTen() + "')";
                 PreparedStatement stmt = conn.prepareStatement(query);
                 stmt.executeUpdate();
               }
             }
           }
           if (temp.equals("DmDoiTuong"))
           {
             List<DmDoiTuong> list = DieuTriUtilDelegate.getInstance().findAll("DmDoiTuong", "dmdoituongDt", true);
             for (DmDoiTuong dmDoiTuong : list)
             {
               String query = " INSERT INTO [" + IConstantsRes.LABCONN_DATABASE + "].[dbo].[tbl_Object_HIS]([ObjectID],[ObjectName]) VALUES ";
               query = query + "('" + dmDoiTuong.getDmdoituongMa() + "',N'" + dmDoiTuong.getDmdoituongTen() + "')";
               PreparedStatement stmt = conn.prepareStatement(query);
               stmt.executeUpdate();
             }
           }
           if (temp.equals("DtDmClsKetQua"))
           {
             List<DtDmClsKetQua> list = DieuTriUtilDelegate.getInstance().findAll("DtDmClsKetQua");
             for (DtDmClsKetQua dtDmClsKetQua : list)
             {
               if (dtDmClsKetQua.getDtdmclskqMa().equals("XN141_013")) {
                 this.log.info("dm XN141_013", new Object[0]);
               }
               String query = " INSERT INTO [" + IConstantsRes.LABCONN_DATABASE + "].[dbo].[tbl_TestCode_HIS]([TestCodeHIS],[TestName]) VALUES ";
               query = query + "('" + dtDmClsKetQua.getDtdmclskqMa() + "',N'" + dtDmClsKetQua.getDtdmclskqTen() + "')";
               PreparedStatement stmt = conn.prepareStatement(query);
               stmt.executeUpdate();
             }
           }
         }
       }
     }
     catch (Exception e) {}
   }

   public void search()
   {
     this.log.info("Search DmCanlamsanKetquaExt....", new Object[0]);

     this.listDtDmClsKetQua = DtDmClsKetQuaDelegate.getInstance().findByAllConditions(this.dtDmClsKetQua.getDtdmclskqMa(), this.dtDmClsKetQua.getDtdmclskqTen(), this.dtdmclsbgMaso);
     if (this.listDtDmClsKetQua.size() == 0) {
       FacesMessages.instance().add(IConstantsRes.no_found, new Object[0]);
     }
   }

   public void delete(int rowIndex)
   {
     this.log.info("Delete DmCanlamsanKetquaExt....", new Object[0]);
     if (rowIndex != -1)
     {
       String ma = "";
       Date date = new Date();

       ma = ((DtDmClsKetQua)this.listDtDmClsKetQua.get(rowIndex)).getDtdmclskqMa();
       try
       {
         DtDmClsKetQua be = (DtDmClsKetQua)this.listDtDmClsKetQua.get(rowIndex);

         DieuTriUtilDelegate.getInstance().remove(be);
         this.listDtDmClsKetQua.remove(rowIndex);

         FacesMessages.instance().add(IConstantsRes.dtdmclskq_de_su, new Object[] { ma });
       }
       catch (Exception e)
       {
         this.log.error(e.toString(), new Object[0]);
         FacesMessages.instance().add(IConstantsRes.dtdmclskq_de_fa, new Object[] { ma });
       }
     }
   }

   public void reset()
   {
     this.log.info("Reset DmCanlamsanKetquaExt....", new Object[0]);

     resetValue();
   }

   public String goback()
   {
     this.log.info("Goback DmCanlamsanKetquaExt....", new Object[0]);

     return "/MyMainForm";
   }

   private void resetValue()
   {
     this.dtDmClsKetQua = new DtDmClsKetQua();
     this.listDtDmClsKetQua = new ArrayList();
     this.listKhoaCLS = new ArrayList();
     this.listClsBanggia = new ArrayList();
     SelectItem itemTemp = new SelectItem(null, "---");
     this.listKhoaCLS.add(itemTemp);
     List<DmKhoa> listKhoaCLSTemp = DieuTriUtilDelegate.getInstance().findAll("DmKhoa", "dmkhoaDt", true);
     for (DmKhoa dmKhoa : listKhoaCLSTemp) {
       if ((dmKhoa.getDmkhoaCls() != null) && (dmKhoa.getDmkhoaCls().booleanValue() == true))
       {
         SelectItem item = new SelectItem(dmKhoa.getDmkhoaMaso(), dmKhoa.getDmkhoaTen());
         this.listKhoaCLS.add(item);
       }
     }
     this.dmkhoaMaso = null;
     this.dtdmclsbgMaso = null;
     this.dmSynchronize = new ArrayList();
   }

   public DtDmClsKetQua getDtDmClsKetQua()
   {
     return this.dtDmClsKetQua;
   }

   public void setDtDmClsKetQua(DtDmClsKetQua dtDmClsKetQua)
   {
     this.dtDmClsKetQua = dtDmClsKetQua;
   }

   public List<DtDmClsKetQua> getListDtDmClsKetQua()
   {
     return this.listDtDmClsKetQua;
   }

   public void setListDtDmClsKetQua(List<DtDmClsKetQua> listDtDmClsKetQua)
   {
     this.listDtDmClsKetQua = listDtDmClsKetQua;
   }

   public List<SelectItem> getListKhoaCLS()
   {
     return this.listKhoaCLS;
   }

   public void setListKhoaCLS(List<SelectItem> listKhoaCLS)
   {
     this.listKhoaCLS = listKhoaCLS;
   }

   public List<SelectItem> getListClsBanggia()
   {
     return this.listClsBanggia;
   }

   public void setListClsBanggia(List<SelectItem> listClsBanggia)
   {
     this.listClsBanggia = listClsBanggia;
   }

   public Integer getDmkhoaMaso()
   {
     return this.dmkhoaMaso;
   }

   public void setDmkhoaMaso(Integer dmkhoaMaso)
   {
     this.dmkhoaMaso = dmkhoaMaso;
   }

   public Integer getDtdmclsbgMaso()
   {
     return this.dtdmclsbgMaso;
   }

   public void setDtdmclsbgMaso(Integer dtdmclsbgMaso)
   {
     this.dtdmclsbgMaso = dtdmclsbgMaso;
   }

   public List<String> getDmSynchronize()
   {
     return this.dmSynchronize;
   }

   public void setDmSynchronize(List<String> dmSynchronize)
   {
     this.dmSynchronize = dmSynchronize;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.DmCanlamsanKetquaExt

 * JD-Core Version:    0.7.0.1

 */