 package com.iesvn.yte.job;

 import com.iesvn.yte.dieutri.delegate.KiemKeDelegate;
 import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
 import com.iesvn.yte.dieutri.entity.KiemKe;
 import com.iesvn.yte.util.IConstantsRes;
 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 import org.apache.log4j.Logger;
 import org.quartz.Job;
 import org.quartz.JobExecutionContext;
 import org.quartz.JobExecutionException;

 public class KiemKeJob
   implements Job
 {
   private static Logger log = Logger.getLogger(KiemKeJob.class);
   private KiemKeDelegate kiemkeDel = KiemKeDelegate.getInstance();
   private List<KiemKe> lstKiemKe = new ArrayList();

   public void execute(JobExecutionContext context)
     throws JobExecutionException
   {
     log.info("Excute KiemKeJob! - ");
     try
     {
       System.out.println("### CALL KiemKeJob AT: " + new Date().toGMTString());
       int thoihandongKiemKe = Integer.parseInt(IConstantsRes.THOI_HAN_DONG_KIEM_KE);
       this.lstKiemKe = this.kiemkeDel.findByKiemKeJob(thoihandongKiemKe);
       if ((this.lstKiemKe != null) && (this.lstKiemKe.size() > 0))
       {
         for (KiemKe kiemke : this.lstKiemKe)
         {
           kiemke.setTrangthai("CLOSE");
           kiemke.setDtdmnhanvienCn(new DtDmNhanVien(Integer.valueOf(804)));
           kiemke.setNgaygiocn(new Date());
           String result = this.kiemkeDel.hoantatKiemKe(kiemke);
           if (result.indexOf("ERROR") > 0) {
             log.info(result + " at " + kiemke.getMaphieukiem());
           } else {
             log.info("Dong kiem ke " + kiemke.getMaphieukiem() + " thanh cong");
           }
         }
         this.lstKiemKe.clear();
       }
       System.out.println("### KiemKeJob FINISHED");
     }
     catch (Exception e)
     {
       e.printStackTrace();
       System.out.println("### ERROR KiemKeJob!!!!");
     }
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.job.KiemKeJob

 * JD-Core Version:    0.7.0.1

 */