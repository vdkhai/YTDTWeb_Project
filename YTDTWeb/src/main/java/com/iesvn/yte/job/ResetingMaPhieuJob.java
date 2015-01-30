 package com.iesvn.yte.job;

 import com.iesvn.yte.dieutri.delegate.YteSequenceDelegate;
 import java.util.Date;
 import org.apache.log4j.Logger;
 import org.quartz.Job;
 import org.quartz.JobExecutionContext;
 import org.quartz.JobExecutionException;

 public class ResetingMaPhieuJob
   implements Job
 {
   private static Logger log = Logger.getLogger(ResetingMaPhieuJob.class);
   YteSequenceDelegate ytesequenceDel = YteSequenceDelegate.getInstance();

   public void execute(JobExecutionContext context)
     throws JobExecutionException
   {
     log.info("Excute ResetingMaPhieuJob! - ");
     try
     {
       log.info("### CALL ResetingMaPhieuJob AT: " + new Date().toString());

       String result = this.ytesequenceDel.resetMaPhieu();
       if (result.equals("-1")) {
         log.info("Loi reset ma phieu");
       } else {
         log.info("Reset ma phieu thanh cong");
       }
       log.info("### ResetingMaPhieuJob FINISHED");
     }
     catch (Exception e)
     {
       e.printStackTrace();
       log.info("### ERROR ResetingMaPhieuJob!!!!");
     }
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.job.ResetingMaPhieuJob

 * JD-Core Version:    0.7.0.1

 */