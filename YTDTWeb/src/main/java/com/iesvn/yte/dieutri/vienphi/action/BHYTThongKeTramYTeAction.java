 package com.iesvn.yte.dieutri.vienphi.action;

 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.delegate.DtDmNhanVienDelegate;
 import com.iesvn.yte.dieutri.delegate.ThongKeTramYTeBhytDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.DtDmNhomBhyt;
 import com.iesvn.yte.dieutri.entity.DtDmTramYTeBhyt;
 import com.iesvn.yte.dieutri.entity.ThongKeTramYTeBhyt;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;
 import org.jboss.seam.annotations.datamodel.DataModel;
 import org.jboss.seam.faces.FacesMessages;
 import org.jboss.seam.security.Identity;

 @Scope(ScopeType.CONVERSATION)
 @Name("bhyt_thongketramyte")
 @Synchronized(timeout=6000000L)
 public class BHYTThongKeTramYTeAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static Logger log = Logger.getLogger(BHYTThongKeTramYTeAction.class);
   @DataModel
   private List<ThongKeTramYTeBhyt> ctctTKTramYTes;
   @In(required=false)
   @Out(required=false)
   Identity identity;
   private DtDmNhanVien nhanVienCN = null;
   private String thoigian_thang = null;
   private String thoigian_nam = null;
   private Integer masonhomdoituong;
   private String manhomdoituong;
   private Integer masotramyte;
   private String matramyte;
   private List<DtDmNhomBhyt> lstDtDmNhomBhyt;
   @In(required=false)
   @Out(required=false)
   private String noitruorngoaitru;
   private String ngayhientai;

   @End
   public void end() {}

   @Begin(join=true)
   public String init(String noiorngoai)
   {
     this.noitruorngoaitru = noiorngoai;
     resetValue();

     this.ngayhientai = Utils.getCurrentDate();

     DtDmNhanVienDelegate nvDelegate = DtDmNhanVienDelegate.getInstance();
     this.nhanVienCN = nvDelegate.findByNd(this.identity.getUsername());

     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();

     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);

     this.ctctTKTramYTes = new ArrayList();















     log.info(this.ctctTKTramYTes);
     return "thongkechiphitramyte";
   }

   public void TimKiem()
   {
     DieuTriUtilDelegate dtUtil = DieuTriUtilDelegate.getInstance();
     DtDmTramYTeBhyt tramyte = (DtDmTramYTeBhyt)dtUtil.findByMa(this.matramyte, "DtDmTramYTeBhyt", "dtdmtramytebhytMa");


     ThongKeTramYTeBhytDelegate tkTramYTe = ThongKeTramYTeBhytDelegate.getInstance();
     Boolean noitru;
     if ("noitru".equals(this.noitruorngoaitru)) {
       noitru = Boolean.valueOf(true);
     } else {
       noitru = Boolean.valueOf(false);
     }
     this.ctctTKTramYTes = tkTramYTe.findByThangNamTramYTe(this.thoigian_thang, this.thoigian_nam, this.matramyte, noitru);
     if ((this.ctctTKTramYTes == null) || (this.ctctTKTramYTes.size() == 0))
     {
       this.ctctTKTramYTes = new ArrayList();
       DieuTriUtilDelegate dtUDele = DieuTriUtilDelegate.getInstance();
       this.lstDtDmNhomBhyt = dtUDele.findAll("DtDmNhomBhyt");
       log.info(this.lstDtDmNhomBhyt);
       if (this.lstDtDmNhomBhyt != null) {
         for (DtDmNhomBhyt nhom : this.lstDtDmNhomBhyt)
         {
           ThongKeTramYTeBhyt thongKeTramYTeBhyt = new ThongKeTramYTeBhyt();

           thongKeTramYTeBhyt.setDtdmnhombhytMaso(nhom);

           thongKeTramYTeBhyt.setTktramytebhytThang(this.thoigian_thang);
           thongKeTramYTeBhyt.setTktramytebhytNam(this.thoigian_nam);
           thongKeTramYTeBhyt.setDtdmtramytebhytMaso(tramyte);
           if ("noitru".equals(this.noitruorngoaitru)) {
             thongKeTramYTeBhyt.setDtdmtramytebhytNoiTru(Boolean.valueOf(true));
           } else {
             thongKeTramYTeBhyt.setDtdmtramytebhytNoiTru(Boolean.valueOf(false));
           }
           this.ctctTKTramYTes.add(thongKeTramYTeBhyt);
         }
       }
     }
   }

   public void resetValue()
   {
     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
     Date currentDate = new Date();

     this.thoigian_thang = String.valueOf(currentDate.getMonth() + 1);
     this.thoigian_nam = String.valueOf(currentDate.getYear() + 1900);
     this.ctctTKTramYTes = new ArrayList();
   }

   public String ghiNhanAjax()
     throws Exception
   {
     log.info("*****Begin ghiNhanAjax() *****");
     if ((this.ctctTKTramYTes == null) || (this.ctctTKTramYTes.size() == 0))
     {
       FacesMessages.instance().add(IConstantsRes.TRAM_Y_TE_CHON_THANG_NAM_TRAM_Y_TE, new Object[0]);
       return "";
     }
     try
     {
       log.info("----------------");
       for (ThongKeTramYTeBhyt tk : this.ctctTKTramYTes) {
         log.info(tk.getTktramytebhytSothebhyt());
       }
       log.info("----------------");


       ThongKeTramYTeBhytDelegate tkTramYTe = ThongKeTramYTeBhytDelegate.getInstance();
       tkTramYTe.luuTruTTThongKeTramYTeBHYT(this.ctctTKTramYTes);




       FacesMessages.instance().add(IConstantsRes.SUCCESS, new Object[0]);
     }
     catch (Exception e)
     {
       FacesMessages.instance().add(IConstantsRes.FAIL, new Object[0]);

       e.printStackTrace();
       log.error("*************loi***********" + e.toString());
     }
     this.ctctTKTramYTes = new ArrayList();

     log.info("*****End ghiNhanAjax() *****");


     return null;
   }

   public String getNgayhientai()
   {
     return this.ngayhientai;
   }

   public void setNgayhientai(String ngayhientai)
   {
     this.ngayhientai = ngayhientai;
   }

   public static Logger getLog()
   {
     return log;
   }

   public static void setLog(Logger log)
   {
     log = log;
   }

   public List<ThongKeTramYTeBhyt> getCtctTKTramYTes()
   {
     return this.ctctTKTramYTes;
   }

   public void setCtctTKTramYTes(List<ThongKeTramYTeBhyt> ctctTKTramYTes)
   {
     this.ctctTKTramYTes = ctctTKTramYTes;
   }

   public Identity getIdentity()
   {
     return this.identity;
   }

   public void setIdentity(Identity identity)
   {
     this.identity = identity;
   }

   public DtDmNhanVien getNhanVienCN()
   {
     return this.nhanVienCN;
   }

   public void setNhanVienCN(DtDmNhanVien nhanVienCN)
   {
     this.nhanVienCN = nhanVienCN;
   }

   public String getThoigian_thang()
   {
     return this.thoigian_thang;
   }

   public void setThoigian_thang(String thoigian_thang)
   {
     this.thoigian_thang = thoigian_thang;
   }

   public String getThoigian_nam()
   {
     return this.thoigian_nam;
   }

   public void setThoigian_nam(String thoigian_nam)
   {
     this.thoigian_nam = thoigian_nam;
   }

   public Integer getMasonhomdoituong()
   {
     return this.masonhomdoituong;
   }

   public void setMasonhomdoituong(Integer masonhomdoituong)
   {
     this.masonhomdoituong = masonhomdoituong;
   }

   public String getManhomdoituong()
   {
     return this.manhomdoituong;
   }

   public void setManhomdoituong(String manhomdoituong)
   {
     this.manhomdoituong = manhomdoituong;
   }

   public Integer getMasotramyte()
   {
     return this.masotramyte;
   }

   public void setMasotramyte(Integer masotramyte)
   {
     this.masotramyte = masotramyte;
   }

   public String getMatramyte()
   {
     return this.matramyte;
   }

   public void setMatramyte(String matramyte)
   {
     this.matramyte = matramyte;
   }

   public String getNoitruorngoaitru()
   {
     return this.noitruorngoaitru;
   }

   public void setNoitruorngoaitru(String noitruorngoaitru)
   {
     this.noitruorngoaitru = noitruorngoaitru;
   }

   public static long getSerialVersionUID()
   {
     return 10L;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.vienphi.action.BHYTThongKeTramYTeAction

 * JD-Core Version:    0.7.0.1

 */