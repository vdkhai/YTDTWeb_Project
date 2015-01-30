 package com.iesvn.yte.util;

 import com.iesvn.yte.dieutri.entity.KiemKeKho;
 import java.util.AbstractList;
 import java.util.List;

 public final class PagedList
   extends AbstractList<KiemKeKho>
 {
   private final List<KiemKeKho> list;
   private final int total;
   private final int pageLimit;

   public PagedList(List<KiemKeKho> list, int total, int pageLimit)
   {
     this.list = list;
     this.total = total;
     this.pageLimit = pageLimit;
   }

   public int size()
   {
     return this.total;
   }

   public KiemKeKho get(int i)
   {
     i %= this.pageLimit;
     return (KiemKeKho)this.list.get(i);
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.util.PagedList

 * JD-Core Version:    0.7.0.1

 */