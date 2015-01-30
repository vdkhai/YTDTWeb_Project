 package com.iesvn.yte.job;

 import com.iesvn.yte.dieutri.delegate.DmThuocDelegate;
 import org.apache.log4j.Logger;
 import org.quartz.Job;
 import org.quartz.JobExecutionContext;
 import org.quartz.JobExecutionException;

 public class UpdatingThuocJob
   implements Job
 {
   private static Logger log = Logger.getLogger(UpdatingThuocJob.class);
   private DmThuocDelegate dmThuocDel = DmThuocDelegate.getInstance();

   public void execute(JobExecutionContext context)
     throws JobExecutionException
   {
     log.info("Excute UpdatingThuocJob! - ");
     try
     {
       log.info("### CALL UpdatingThuocJob AT: ");
       this.dmThuocDel.updateFieldTonKho();
       log.info("### UpdatingThuocJob FINISHED");
     }
     catch (Exception e)
     {
       e.printStackTrace();
       log.info("### ERROR UpdatingThuocJob!!!!");
     }
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.job.UpdatingThuocJob

 * JD-Core Version:    0.7.0.1

 */