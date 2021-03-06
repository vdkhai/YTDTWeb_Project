 package com.iesvn.yte.dieutri.duocpham.action;

 import com.iesvn.yte.dieutri.ajax.MyMenuYTDTAction;
 import java.io.Serializable;
 import org.apache.log4j.Logger;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Begin;
 import org.jboss.seam.annotations.End;
 import org.jboss.seam.annotations.In;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Out;
 import org.jboss.seam.annotations.Scope;
 import org.jboss.seam.annotations.security.Restrict;

 @Scope(ScopeType.CONVERSATION)
 @Name("Xacnhanphieuxuathang")
 public class XacNhapPhieuXuatHangAction
   implements Serializable
 {
   private static final long serialVersionUID = 10L;
   private static Logger logger = Logger.getLogger(XacNhapPhieuXuatHangAction.class);
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinh;
   @In(required=false)
   @Out(required=false)
   private String tenChuongTrinhCon;

   @Restrict("#{s:hasRole('NV_KhoaDuoc') or s:hasRole('QT_HT_Duoc')}")
   @Begin(join=true)
   public String init(String tumenu, String menucon)
   {
     logger.info("-----init()-----");
     reset();
     if ("KC".equals(tumenu))
     {
       this.tenChuongTrinh = MyMenuYTDTAction.quanLyKhoChinh;
       this.tenChuongTrinhCon = menucon;
     }
     return "Xacnhanphieuxuathang";
   }

   @End
   public void endFunc() {}

   public void reset() {}
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.dieutri.duocpham.action.XacNhapPhieuXuatHangAction

 * JD-Core Version:    0.7.0.1

 */