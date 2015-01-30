 package com.iesvn.yte;

 import com.iesvn.yte.dieutri.delegate.DtDmBanKhamSequenceDelegate;
 import java.io.Serializable;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Create;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;

 @Scope(ScopeType.SESSION)
 @Name("CauHinhStt")
 @Synchronized(timeout=6000000L)
 public class CauHinhSttAction
   implements Serializable
 {
   private static Logger logger = Logger.getLogger(CauHinhSttAction.class);
   private static final long serialVersionUID = 10L;

   @Create
   public void init()
   {
     logger.debug("-----init()-----");
   }

   public void reset()
   {
     DtDmBanKhamSequenceDelegate bkSeq = DtDmBanKhamSequenceDelegate.getInstance();
     bkSeq.reset();
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.CauHinhSttAction

 * JD-Core Version:    0.7.0.1

 */