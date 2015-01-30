 package com.iesvn.yte.job;

 import com.iesvn.yte.util.IConstantsRes;
 import java.io.IOException;
 import javax.servlet.ServletConfig;
 import javax.servlet.ServletContext;
 import javax.servlet.ServletException;
 import javax.servlet.http.HttpServlet;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import org.apache.log4j.Logger;
 import org.quartz.CronScheduleBuilder;
 import org.quartz.CronTrigger;
 import org.quartz.JobBuilder;
 import org.quartz.JobDetail;
 import org.quartz.Scheduler;
 import org.quartz.TriggerBuilder;
 import org.quartz.impl.StdSchedulerFactory;

 public class ResetingMaPhieuAutoServlet
   extends HttpServlet
 {
   private static Logger log = Logger.getLogger(ResetingMaPhieuAutoServlet.class);
   private boolean performShutdown = true;
   private Scheduler scheduler = null;
   public static final String QUARTZ_FACTORY_KEY = "org.quartz.impl.StdSchedulerFactory.KEY";

   public void init(ServletConfig config)
     throws ServletException
   {
     if (IConstantsRes.RESET_MAPHIEU_HOUR.equals("NO"))
     {
       log.info("Not run Reset MaPhieu AutoServlet");
       return;
     }
     log.info("Initializing Scheduler PlugIn for Reseting Ma Phieu Jobs!");
     super.init(config);
     ServletContext ctx = config.getServletContext();

     StdSchedulerFactory factory = (StdSchedulerFactory)ctx.getAttribute("org.quartz.impl.StdSchedulerFactory.KEY");
     try
     {
       String configFile = config.getInitParameter("config-file");
       String shutdownPref = config.getInitParameter("shutdown-on-unload");
       if (shutdownPref != null) {
         this.performShutdown = Boolean.valueOf(shutdownPref).booleanValue();
       }
       if (configFile != null) {
         factory = new StdSchedulerFactory(configFile);
       } else {
         factory = new StdSchedulerFactory();
       }
       String startOnLoad = config.getInitParameter("start-scheduler-on-load");
       if ((startOnLoad == null) || (Boolean.getBoolean(startOnLoad)))
       {
         this.scheduler = factory.getScheduler();
         JobDetail job = JobBuilder.newJob(ResetingMaPhieuJob.class).withIdentity("ResetingMaPhieuJob", "RESETINGMAPHIEU_GROUP").storeDurably().requestRecovery().build();





         String cron_HrsExpr = IConstantsRes.RESET_MAPHIEU_HOUR;
         String cron_MinuteExpr = IConstantsRes.RESET_MAPHIEU_MINUTE;
         String cron_SecExpr = IConstantsRes.RESET_MAPHIEU_SEC;
         String cronExpr = cron_SecExpr + " " + cron_MinuteExpr + " " + cron_HrsExpr + " * * ?";
         CronTrigger trigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("ResetingMaPhieuTrigger", "RESETINGMAPHIEU_GROUP").withSchedule(CronScheduleBuilder.cronSchedule(cronExpr)).build();




         this.scheduler.scheduleJob(job, trigger);
         log.info("ResetingMaPhieu Job scheduled now ..");
         this.scheduler.start();
         log.info("Scheduler has been started...");
       }
       else
       {
         log.info("Scheduler started. Use scheduler.start()");
       }
       log.info("Job scheduled now ..");
       String factoryKey = config.getInitParameter("servlet-context-factory-key");
       if (factoryKey == null) {
         factoryKey = "org.quartz.impl.StdSchedulerFactory.KEY";
       }
       log.info("Storing SchedulerFactory at servlet context key: " + factoryKey);

       config.getServletContext().setAttribute(factoryKey, factory);
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
   }

   public void destroy()
   {
     if (!this.performShutdown) {
       return;
     }
     try
     {
       if (this.scheduler != null)
       {
         this.scheduler.shutdown();
         Thread.sleep(1000L);
       }
     }
     catch (Exception e)
     {
       log.info("Quartz Scheduler failed to shutdown cleanly: " + e.toString());
       e.printStackTrace();
     }
     log("Quartz Scheduler successful shutdown.");
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException
   {
     response.sendError(403);
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
     throws ServletException, IOException
   {
     response.sendError(403);
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.job.ResetingMaPhieuAutoServlet

 * JD-Core Version:    0.7.0.1

 */