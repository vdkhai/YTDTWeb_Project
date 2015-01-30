 package com.iesvn.yte.paging;

 import java.io.Serializable;
 import java.util.ArrayList;
 import javax.faces.event.ActionEvent;
 import org.ajax4jsf.component.html.HtmlAjaxCommandLink;
 import org.jboss.seam.ScopeType;
 import org.jboss.seam.annotations.Name;
 import org.jboss.seam.annotations.Scope;
 import org.richfaces.event.DataScrollerEvent;

 @Name("testBean")
 @Scope(ScopeType.SESSION)
 public class TestBean
   implements Serializable
 {
   private ArrayList data;
   private ArrayList data1;
   private ArrayList data2;
   private ArrayList data3;
   private ArrayList data4;
   private ArrayList data5;
   private ArrayList data6;
   public boolean renderIfSinglePage;
   public int rows;
   public int maxpage;
   private int actionCount;
   private int eventCount;

   public TestBean()
   {
     this.renderIfSinglePage = true;

     this.renderIfSinglePage = true;
     this.rows = 5;
     this.maxpage = 10;

     this.data = new ArrayList();
     for (int i = 0; i < 10; i++) {
       this.data.add(new Entry(null, i));
     }
     this.data1 = new ArrayList();
     for (int i = 0; i < 11; i++) {
       this.data1.add(new Entry(null, i));
     }
     this.data2 = new ArrayList();
     for (int i = 0; i < 12; i++) {
       this.data2.add(new Entry(null, i));
     }
     this.data3 = new ArrayList();
     for (int i = 0; i < 13; i++) {
       this.data3.add(new Entry(null, i));
     }
     this.data4 = new ArrayList();
     for (int i = 0; i < 14; i++) {
       this.data4.add(new Entry(null, i));
     }
     this.data5 = new ArrayList();
     for (int i = 0; i < 15; i++) {
       this.data5.add(new Entry(null, i));
     }
     this.data6 = new ArrayList();
     for (int i = 0; i < 16; i++) {
       this.data6.add(new Entry(null, i));
     }
   }

   public void doSort(ActionEvent e)
   {
     HtmlAjaxCommandLink a = (HtmlAjaxCommandLink)e.getComponent();
   }

   public void setRenderIfSinglePage(boolean renderIfSinglePage)
   {
     this.renderIfSinglePage = renderIfSinglePage;
   }

   public boolean isRenderIfSinglePage()
   {
     return this.renderIfSinglePage;
   }

   public void setRows(int rows)
   {
     this.rows = rows;
   }

   public int getRows()
   {
     return this.rows;
   }

   public void setMaxpage(int maxpage)
   {
     this.maxpage = maxpage;
   }

   public int getMaxpage()
   {
     return this.maxpage;
   }

   public void onAction(ActionEvent actionEvent)
   {
     this.actionCount += 1;
   }

   public void doScroll(DataScrollerEvent event)
   {
     String oldScrolVal = event.getOldScrolVal();
     String newScrolVal = event.getNewScrolVal();
     this.eventCount += 1;
   }

   public ArrayList getData()
   {
     return this.data;
   }

   public void setData(ArrayList data)
   {
     this.data = data;
   }

   public ArrayList getData1()
   {
     return this.data1;
   }

   public void setData1(ArrayList data)
   {
     this.data1 = data;
   }

   public ArrayList getData2()
   {
     return this.data2;
   }

   public void setData2(ArrayList data)
   {
     this.data2 = data;
   }

   public ArrayList getData3()
   {
     return this.data3;
   }

   public void setData3(ArrayList data)
   {
     this.data3 = data;
   }

   public ArrayList getData4()
   {
     return this.data4;
   }

   public void setData4(ArrayList data)
   {
     this.data4 = data;
   }

   public ArrayList getData5()
   {
     return this.data5;
   }

   public void setData5(ArrayList data)
   {
     this.data5 = data;
   }

   public ArrayList getData6()
   {
     return this.data6;
   }

   public void setData6(ArrayList data)
   {
     this.data6 = data;
   }

   public int getActionCount()
   {
     return this.actionCount;
   }

   public void setActionCount(int actionCount)
   {
     this.actionCount = actionCount;
   }

   public int getEventCount()
   {
     return this.eventCount;
   }

   public void setEventCount(int eventCount)
   {
     this.eventCount = eventCount;
   }

   public void addEntries()
   {
     for (int i = 0; i < 5; i++) {
       this.data.add(new Entry(null, this.data.size()));
     }
   }

   public void removeEntries()
   {
     if (this.data.size() >= 5) {
       for (int i = 0; i < 5; i++) {
         this.data.remove(this.data.size() - 1);
       }
     }
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.paging.TestBean

 * JD-Core Version:    0.7.0.1

 */