 package com.iesvn.yte.dieutri.tiepdon.action;

 import java.io.Serializable;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.Factory;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.Synchronized;

 @Name("B121_7_Giaychuyenviensotxuathuyet")
 @Scope(ScopeType.CONVERSATION)
 @Synchronized(timeout=6000000L)
 public class GiayChuyenVienSotXuatHuyetAction
   implements Serializable
 {
   private static Logger log = Logger.getLogger(GiayChuyenVienSotXuatHuyetAction.class);
   @In(required=false)
   @Out(required=false)
   private String goToGiayChuyenVienSotXuatHuyet;

   @Begin(nested=true)
   @Factory("goToGiayChuyenVienSotXuatHuyet")
   public void init()
     throws Exception
   {
     log.info("***Starting init ***");

     log.info("***Finished init ***");
   }

   @End
   public void end() {}
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.tiepdon.action.GiayChuyenVienSotXuatHuyetAction

 * JD-Core Version:    0.7.0.1

 */