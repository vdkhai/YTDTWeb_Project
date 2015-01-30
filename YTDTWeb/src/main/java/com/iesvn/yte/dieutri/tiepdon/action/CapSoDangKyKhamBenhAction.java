 package com.iesvn.yte.dieutri.tiepdon.action;

 import com.iesvn.yte.ReportUtil;
 import com.iesvn.yte.dieutri.delegate.BenhNhanDelegate;
 import com.iesvn.yte.dieutri.delegate.DieuTriUtilDelegate;
 import com.iesvn.yte.dieutri.entity.BenhNhan;
 import com.iesvn.yte.dieutri.entity.DtDmBanKham;
 import com.iesvn.yte.entity.DmDoiTuong;
 import com.iesvn.yte.util.IConstantsRes;
 import com.iesvn.yte.util.Utils;
 import java.io.Serializable;
 import java.util.Date;
 import java.util.HashMap;
 import java.util.Map;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;

 @Scope(ScopeType.SESSION)
 @Name("B112_Capsodangkykhambenh")
 @Synchronized(timeout=6000000L)
 public class CapSoDangKyKhamBenhAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static Logger logger = Logger.getLogger(CapSoDangKyKhamBenhAction.class);
   private String ngayHt;
   private String gioHt;
   private String maBn;
   private BenhNhan bn;
   private DmDoiTuong dt;
   private DtDmBanKham banKham;
   private String stt;
   private String bnTra;
   private String resultReportFileName;
   private String resultReportName;
   private String loaiFileXuat;
   @Out(required=false)
   private String isReport = "false";

   @Create
   public void init()
   {
     logger.info("-----init()-----");
     reset();
   }

   public void reset()
   {
     logger.info("-----reset()-----");
     this.ngayHt = Utils.getCurrentDate();
     this.gioHt = Utils.getGioPhut(new Date());
     this.bn = new BenhNhan();
     this.dt = new DmDoiTuong();

     this.banKham = new DtDmBanKham();
     this.maBn = "";
     this.bnTra = "";
   }

   public void loadBenhNhan()
   {
     logger.info("-----loadBenhNhan()-----");
     if ((!this.maBn.equals("")) || (!this.banKham.getDtdmbankhamMa().equals("")))
     {
       BenhNhanDelegate bnDelegate = BenhNhanDelegate.getInstance();
       this.bn = bnDelegate.find(this.maBn);
       if (this.bn == null) {
         this.maBn = "";
       } else {
         this.maBn = this.bn.getBenhnhanMa();
       }
     }
   }

   public String inPhieu(String loaiFile)
   {
     logger.info(String.format("-----inPhieu()-----", new Object[0]));
     logger.info(String.format("-----loai file: %s", new Object[] { loaiFile }));
     String pathTemplate = "B112_Capsodangkykhambenh";
     if ((this.bn != null) || (!this.stt.equals(""))) {
       try
       {
         logger.info(String.format("-----pathTemplate: %s", new Object[] { pathTemplate }));
         Map<String, Object> params = new HashMap();

         DieuTriUtilDelegate dtDelegate = DieuTriUtilDelegate.getInstance();


         this.banKham = ((DtDmBanKham)dtDelegate.findByMa(this.banKham.getDtdmbankhamMa(), "DtDmBanKham", "dtdmbankhamMa"));


         params.put("doiTuong", this.dt.getDmdoituongMa());
         params.put("stt", this.stt);
         params.put("gioHt", this.gioHt);
         params.put("ngayHt", this.ngayHt);


         params.put("maBn", this.maBn);
         params.put("tenBn", this.bn.getBenhnhanHoten());
         params.put("banKham", this.banKham.getDtdmbankhamTen());

         Date now = new Date();
         String fileNameExt = String.valueOf(now.getTime());
         String fileName = ReportUtil.xuatReport(IConstantsRes.PATH_BASE, IConstantsRes.PATH_REPORT_TEMPLATE_DIEU_TRI, IConstantsRes.PATH_REPORT_RESULT_DIEU_TRI, "tiepdon/", pathTemplate, params, loaiFile, fileNameExt);
         if (loaiFile.equalsIgnoreCase("HTML")) {
           setResultReportName(fileName);
         } else {
           setResultReportFileName(fileName);
         }
         setIsReport("true");
       }
       catch (Exception ex)
       {
         logger.error(String.format("-----Error: %s", new Object[] { ex.toString() }));
       }
     }
     return "/B1_Tiepdon/B112_Chonmenuxuattaptin.xhtml";
   }

   public String troVe()
   {
     try
     {
       logger.info("***** troVe()");
       return "/B1_Tiepdon/B112_Capsodangkykhambenh.xhtml";
     }
     catch (Exception e)
     {
       logger.info("***** End exception = " + e);
     }
     return null;
   }

   public void setNgayHt(String ngayHt)
   {
     this.ngayHt = ngayHt;
   }

   public String getNgayHt()
   {
     return this.ngayHt;
   }

   public void setGioHt(String gioHt)
   {
     this.gioHt = gioHt;
   }

   public String getGioHt()
   {
     return this.gioHt;
   }

   public void setBn(BenhNhan bn)
   {
     this.bn = bn;
   }

   public BenhNhan getBn()
   {
     return this.bn;
   }

   public void setMaBn(String maBn)
   {
     this.maBn = maBn;
   }

   public String getMaBn()
   {
     return this.maBn;
   }

   public void setDt(DmDoiTuong dt)
   {
     this.dt = dt;
   }

   public DmDoiTuong getDt()
   {
     return this.dt;
   }

   public void setStt(String stt)
   {
     this.stt = stt;
   }

   public String getStt()
   {
     return this.stt;
   }

   public void setBanKham(DtDmBanKham banKham)
   {
     this.banKham = banKham;
   }

   public DtDmBanKham getBanKham()
   {
     return this.banKham;
   }

   public String getBnTra()
   {
     return this.bnTra;
   }

   public void setBnTra(String bnTra)
   {
     this.bnTra = bnTra;
   }

   public String getResultReportFileName()
   {
     return this.resultReportFileName;
   }

   public void setResultReportFileName(String resultReportFileName)
   {
     this.resultReportFileName = resultReportFileName;
   }

   public String getResultReportName()
   {
     return this.resultReportName;
   }

   public void setResultReportName(String resultReportName)
   {
     this.resultReportName = resultReportName;
   }

   public String getLoaiFileXuat()
   {
     return this.loaiFileXuat;
   }

   public void setLoaiFileXuat(String loaiFileXuat)
   {
     this.loaiFileXuat = loaiFileXuat;
   }

   public String getIsReport()
   {
     return this.isReport;
   }

   public void setIsReport(String isReport)
   {
     this.isReport = isReport;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.CapSoDangKyKhamBenhAction

 * JD-Core Version:    0.7.0.1

 */