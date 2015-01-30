package com.iesvn.yte.extendeddatamodel;

import java.util.List;

public abstract interface BaseDataProvider<T>
{
  public abstract void update();
  
  public abstract boolean hasItemByPk(Integer paramInteger);
  
  public abstract T getItemByPk(Integer paramInteger);
  
  public abstract int getRowCount();
  
  public abstract List<T> getItemsByrange(Integer paramInteger, int paramInt, String paramString, boolean paramBoolean);
}


/* Location:           E:\my workspace\QLBV\apache-tomcat-7\apache-tomcat-7\webapps\YTDTWeb\WEB-INF\classes\
 * Qualified Name:     com.iesvn.yte.extendeddatamodel.BaseDataProvider
 * JD-Core Version:    0.7.0.1
 */