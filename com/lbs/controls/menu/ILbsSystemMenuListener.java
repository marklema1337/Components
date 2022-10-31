package com.lbs.controls.menu;

import com.lbs.control.interfaces.ILbsPopupMenu;

public interface ILbsSystemMenuListener {
  public static final int ITEM_RECORD_COUNT = 0;
  
  public static final int ITEM_CUSTOMIZE_GRID = 1;
  
  public static final int ITEM_PREFERENCES = 2;
  
  public static final int ITEM_EDIT_MENU = 3;
  
  public static final int ITEM_DATA_TEMPLATE = 4;
  
  public static final int ITEM_LIST_REPORT = 5;
  
  public static final int ITEM_SWITCH_PERIOD = 6;
  
  public static final int ITEM_GROUPED_LIST_REPORT = 7;
  
  public static final int ITEM_OPEN_WINDOWS = 8;
  
  public static final int ITEM_LOCK_RECORD = 9;
  
  public static final int ITEM_DEFINE_SHORTCUT = 10;
  
  public static final int ITEM_BOOKMARK_URL = 11;
  
  public static final int ITEM_EXCHANGE = 12;
  
  public static final int ITEM_REVISION_HISTORY = 13;
  
  public static final int ITEM_RECORD_INFO = 14;
  
  public static final int ITEM_EXCLUSIVE_VIEW = 15;
  
  public static final int ITEM_EDIT_OBJECTLISTGRID = 16;
  
  public static final int ITEM_FILTERGRID = 17;
  
  public static final int ITEM_WORKFLOW_STATUS = 18;
  
  public static final int ITEM_CUSTOMIZE = 19;
  
  public static final int ITEM_SELECTED_RECORD_COUNT = 20;
  
  public static final int SUBITEM_EDIT_OBJECTLISTGRID_APPEND = 100;
  
  public static final int SUBITEM_EDIT_OBJECTLISTGRID_INSERT = 101;
  
  public static final int SUBITEM_EDIT_OBJECTLISTGRID_DELETE = 102;
  
  public static final int SUBITEM_EXCHANGE_EXPORT = 103;
  
  public static final int SUBITEM_EXCHANGE_IMPORT = 104;
  
  public static final int SUBITEM_EXCHANGE_EXCEL = 105;
  
  public static final int SUBITEM_EXCHANGE_FIRM = 106;
  
  public static final int SUBITEM_EDIT_MENU_CUT = 107;
  
  public static final int SUBITEM_EDIT_MENU_COPY = 108;
  
  public static final int SUBITEM_EDIT_MENU_PASTE = 109;
  
  public static final int SUBITEM_EDIT_MENU_SELECTALL = 110;
  
  public static final int SUBITEM_PREFERENCES_DBREFRESH = 111;
  
  public static final int SUBITEM_PREFERENCES_ROWCOLORING = 112;
  
  public static final int SUBITEM_PREFERENCES_RESET_TO_DEFAULTS = 113;
  
  public static final int SUBITEM_PREFERENCES_SAVE_PREFS = 114;
  
  public static final int SUBITEM_LIST_REPORT_SELECTALL = 115;
  
  public static final int SUBITEM_LIST_REPORT_DESELECTALL = 116;
  
  public static final int SUBITEM_LIST_REPORT_SELECTINVERT = 117;
  
  public static final int SUBITEM_LIST_REPORT_SELECT_WITHCOUNT = 118;
  
  boolean canAppendSystemMenuItem(int paramInt, ILbsPopupMenu paramILbsPopupMenu);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\controls\menu\ILbsSystemMenuListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */