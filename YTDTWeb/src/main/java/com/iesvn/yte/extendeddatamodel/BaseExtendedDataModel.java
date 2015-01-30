 package com.iesvn.yte.extendeddatamodel;

 import java.io.IOException;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import javax.faces.context.FacesContext;
 import org.ajax4jsf.model.DataVisitor;
 import org.ajax4jsf.model.Range;
 import org.ajax4jsf.model.SequenceRange;
 import org.ajax4jsf.model.SerializableDataModel;

 public abstract class BaseExtendedDataModel<T extends BaseBean, T1 extends BaseDataProvider>
   extends SerializableDataModel
 {
   private int firstRow;
   private Integer currentPk;
   private Map<Integer, T> wrappedData = new HashMap();
   private List<Integer> wrappedKeys = null;
   private boolean detached = false;
   private static final long serialVersionUID = -1956179896877538628L;
   private Integer rowCount;

   public Object getRowKey()
   {
     return this.currentPk;
   }

   public void setRowKey(Object key)
   {
     this.currentPk = ((Integer)key);
   }

   public void walk(FacesContext context, DataVisitor visitor, Range range, Object argument)
     throws IOException
   {
     this.firstRow = ((SequenceRange)range).getFirstRow();
     int numberOfRows = ((SequenceRange)range).getRows();
     if (this.detached)
     {
       for (Integer key : this.wrappedKeys)
       {
         setRowKey(key);
         visitor.process(context, key, argument);
       }
     }
     else
     {
       this.wrappedKeys = new ArrayList();

       List<T> list = getDataProvider().getItemsByrange(new Integer(this.firstRow), numberOfRows, null, true);
       for (int i = 0; i < list.size(); i++)
       {
           T item = list.get(i);
         this.wrappedKeys.add(item.getPK());
         this.wrappedData.put(item.getPK(), item);
         visitor.process(context, item.getPK(), argument);
       }
     }
   }

   public int getRowCount()
   {
     if (this.rowCount == null)
     {
       this.rowCount = new Integer(getDataProvider().getRowCount());
       return this.rowCount.intValue();
     }
     return this.rowCount.intValue();
   }

   public Object getRowData()
   {
     if (this.currentPk == null) {
       return null;
     }
       T ret = (T)this.wrappedData.get(this.currentPk);
     if (ret == null)
     {
       ret = (T)getDataProvider().getItemByPk(this.currentPk);

       this.wrappedData.put(this.currentPk,ret);
       return ret;
     }
     return ret;
   }

   public int getRowIndex()
   {
     throw new UnsupportedOperationException();
   }

   public Object getWrappedData()
   {
     return this.wrappedData;
   }

   public boolean isRowAvailable()
   {
     if (this.currentPk == null) {
       return false;
     }
     return getDataProvider().hasItemByPk(this.currentPk);
   }

   public void setRowIndex(int rowIndex)
   {
     throw new UnsupportedOperationException();
   }

   public void setWrappedData(Object data)
   {
     throw new UnsupportedOperationException();
   }

   public SerializableDataModel getSerializableModel(Range range)
   {
     if (this.wrappedKeys != null)
     {
       this.detached = true;


       return this;
     }
     return null;
   }

   public void update()
   {
     getDataProvider().update();
   }

   public abstract T1 getDataProvider();

   public int getFirstRow()
   {
     return ++this.firstRow;
   }

   public void setFirstRow(int firstRow)
   {
     this.firstRow = firstRow;
   }
 }



/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\

 * Qualified Name:     com.iesvn.yte.extendeddatamodel.BaseExtendedDataModel

 * JD-Core Version:    0.7.0.1

 */