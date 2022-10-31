/*     */ package com.lbs.recording;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JLbsEventConstants
/*     */   implements ILbsEventConstants
/*     */ {
/*     */   public static final String POSTFIX_DATA_POOL = "_DP";
/*     */   public static final String POSTFIX_FORM_SNAPSHOT = "_FS";
/*     */   public static final String POSTFIX_ERROR_CONFIG = "_EC";
/*     */   public static final String POSTFIX_TEST_CONTENT = "_TC";
/*     */   public static final String POSTFIX_PERFORMANCE_DURATIONS = "_PD";
/*     */   public static final String XML_FORM = "Form";
/*     */   public static final String XML_FORM_CONTROL = "Control";
/*     */   public static final String XML_FORM_GRID = "Grid";
/*     */   public static final String XML_FORM_GRID_DATA = "DataGrid";
/*     */   public static final String XML_FORM_GRID_FILTER = "FilterGrid";
/*     */   public static final String XML_FORM_GRID_COLUMN = "Column";
/*     */   public static final String XML_FORM_GRID_COLUMNS = "Columns";
/*     */   public static final String XML_FORM_GRID_ROW = "Row";
/*     */   public static final String XML_FORM_GRID_ROWS = "Rows";
/*     */   public static final String XML_FORM_FILTER = "Filter";
/*     */   public static final String XML_FORM_FILTERS = "Filters";
/*     */   public static final String XML_FORM_MESSAGES = "Messages";
/*     */   public static final String XML_FORM_MESSAGE = "Message";
/*     */   public static final String XML_ATTRIBUTE_TAG = "tag";
/*     */   public static final String XML_ATTRIBUTE_ID = "id";
/*     */   public static final String XML_ATTRIBUTE_TITLE = "title";
/*     */   public static final String XML_ATTRIBUTE_TYPE = "type";
/*     */   public static final String XML_ATTRIBUTE_ITEM_LIST = "itemList";
/*     */   public static final String XML_ATTRIBUTE_DEFAULT_VALUE = "defaultValue";
/*     */   public static final String XML_ATTRIBUTE_MANDATORY = "mandatory";
/*     */   public static final String XML_ATTRIBUTE_HASLOOKUP = "hasLookup";
/*     */   public static final String XML_ATTRIBUTE_VALUE = "value";
/*     */   public static final String XML_ATTRIBUTE_TEXT = "text";
/*     */   public static final String XML_ATTRIBUTE_WIDTH = "width";
/*     */   public static final String XML_ATTRIBUTE_HEIGHT = "height";
/*     */   public static final String XML_ATTRIBUTE_LOCATIONX = "locationX";
/*     */   public static final String XML_ATTRIBUTE_LOCATIONY = "locationY";
/*     */   public static final String XML_ATTRIBUTE_ENABLED = "enabled";
/*     */   public static final String XML_ATTRIBUTE_VISIBLE = "visible";
/*     */   public static final String XML_ATTRIBUTE_ATTRIBUTE = "attribute";
/*     */   public static final String XML_ATTRIBUTE_SPLITTER_INDEX = "splitterIndex";
/*     */   public static final String XML_ATTRIBUTE_SELECTED_LIST = "selectedList";
/*     */   public static final String XML_ATTRIBUTE_XUI_MESSAGE_TYPE = "type";
/*     */   public static final String XML_ATTRIBUTE_XUI_MESSAGE_ID = "id";
/*     */   public static final String XML_ATTRIBUTE_XUI_MESSAGE_MESSAGE = "message";
/*     */   public static final String XML_ATTRIBUTE_XUI_MESSAGE_CONTROL_TAG = "controlTag";
/*     */   public static final String XML_ATTRIBUTE_XUI_MESSAGE_ITEM_TAG = "itemTag";
/*     */   public static final String XML_ATTRIBUTE_XUI_MESSAGE_ROW_INDEX = "rowIndex";
/*     */   public static final String XML_ATTRIBUTE_XUI_MESSAGE_COLUMN_INDEX = "columnIndex";
/*     */   public static final String XML_ATTRIBUTE_XUI_MESSAGE_RESGROUP = "message-resource-group";
/*     */   public static final String XML_ATTRIBUTE_XUI_MESSAGE_LISTID = "message-list-id";
/*     */   public static final String XML_ATTRIBUTE_XUI_MESSAGE_STRTAG = "message-string-tag";
/*     */   public static final String XML_ATTRIBUTE_FORM_NAME = "name";
/*     */   public static final String XML_ATTRIBUTE_FORM_TITLE = "title";
/*     */   public static final String XML_ATTRIBUTE_VERSION = "version";
/*     */   public static final String XML_ATTRIBUTE_ROW_STATE = "state";
/*     */   public static final String XML_ATTRIBUTE_COLUMN_ALIGNMENT = "align";
/*     */   public static final String XML_ATTRIBUTE_COLUMN_HEADER_ALIGNMENT = "headerAlign";
/*     */   public static final String XML_ATTRIBUTE_COLUMN_HEADER_CAPTION = "caption";
/*     */   public static final String XML_ATTRIBUTE_COLUMN_PROPERTY = "property";
/*     */   public static final String XML_ATTRIBUTE_COLUMN_WIDTH = "width";
/*     */   public static final String XML_ATTRIBUTE_COLUMN_VISIBLE = "visible";
/*     */   public static final String XML_ATTRIBUTE_COLUMN_MANDATORY = "mandatory";
/*     */   public static final String XML_ATTRIBUTE_COLUMN_HASLOOKUP = "hasLookup";
/*     */   public static final String XML_ATTRIBUTE_COLUMN_EDITOR_TYPE = "type";
/*     */   public static final String XML_ATTRIBUTE_COLUMN_EXCLUDED = "excluded";
/*     */   public static final String XML_ATTRIBUTE_FILTER_DESCRIPTION = "description";
/*     */   public static final String XML_ATTRIBUTE_FILTER_ID = "id";
/*     */   public static final String XML_ATTRIBUTE_FILTER_TYPE = "type";
/*     */   public static final String XML_ATTRIBUTE_FILTER_ITEM_LIST = "itemList";
/*     */   public static final String XML_ATTRIBUTE_FILTER_VALUE_ITEM_LIST = "valueList";
/*     */   public static final String XML_ATTRIBUTE_FILTER_LIST_ID = "listId";
/*     */   public static final String XML_ATTRIBUTE_FILTER_STRING_TAG = "stringTag";
/*     */   public static final String XML_ATTRIBUTE_FILTER_VALUE = "value";
/*     */   public static final String XML_ATTRIBUTE_FILTER_LOW_VALUE = "lowValue";
/*     */   public static final String XML_ATTRIBUTE_FILTER_HIGH_VALUE = "highValue";
/*     */   public static final String XML_ATTRIBUTE_FILTER_GRP_VALUE = "groupValue";
/*     */   public static final String XML_ATTRIBUTE_FILTER_EXCLUDED_VALUE = "excludedValue";
/*     */   public static final String XML_ATTRIBUTE_FILTER_HASLOOKUP = "hasLookup";
/*     */   public static final String XML_EMPTY_STRING = "%EMPTY_STRING%";
/*     */   public static final String COLUMN_ALIGN_CENTER = "CENTER";
/*     */   public static final String COLUMN_ALIGN_TOP = "TOP";
/*     */   public static final String COLUMN_ALIGN_LEFT = "LEFT";
/*     */   public static final String COLUMN_ALIGN_BOTTOM = "BOTTOM";
/*     */   public static final String COLUMN_ALIGN_RIGHT = "RIGHT";
/*     */   public static final int TEST_VARIABLE_SCOPE_LOCAL = 1;
/*     */   public static final int TEST_VARIABLE_SCOPE_PACKAGE = 2;
/*     */   public static final int TEST_VARIABLE_SCOPE_GLOBAL = 3;
/*     */   public static final String TEST_VARIABLE_NAME_PREFIX = "Variable";
/*     */   public static final String TEST_VARIABLE_SCOPE_PREFIX_LOCAL = "LCL";
/*     */   public static final String TEST_VARIABLE_SCOPE_PREFIX_PACKAGE = "PCK";
/*     */   public static final String TEST_VARIABLE_SCOPE_PREFIX_GLOBAL = "GLB";
/*     */   public static final String TEST_VARIABLE_CLONE_OBJECT_SEC_KEY = "SEC_KEY";
/*     */   public static final String REPLACEMENT_CUST = "_CST_";
/*     */   public static final String REPLACEMENT_SLASH = "/";
/*     */   public static final String EXCLUDED_SNAPSHOT_VALUE = "-1";
/*     */   public static final String COLUMN_ACTION = "ACTION";
/*     */   public static final String COLUMN_TAG = "TAG";
/*     */   public static final String COLUMN_PARENT_TAG = "PARENT TAG";
/*     */   public static final String COLUMN_TYPE = "COMPONENT TYPE";
/*     */   public static final String COLUMN_VALUE = "VALUE";
/*     */   public static final String COLUMN_TITLE = "TITLE";
/*     */   public static final String CMD_FILE_LIST_SEPARATOR = ";";
/*     */   public static final String CMD_SEPARATOR = "|";
/*     */   public static final String CMD_REPORT_ITEM_UNIT_SEPARATOR = "\t";
/*     */   public static final String CMD_REPORT_ITEM_SEPARATOR = "\n";
/*     */   public static final String CMD_UNIQUE_SEPERATOR = "@_@";
/*     */   public static final String CONTROL_TAG_PROPERTY = "EVENT_TAG";
/*     */   public static final String CONTROL_PARENT_TAG_PROPERTY = "EVENT_PARENT_TAG";
/*     */   public static final String CONTROL_VALUE_PROPERTY = "EVENT_VALUE";
/*     */   public static final String CONTROL_TYPE_PROPERTY = "EVENT_TYPE";
/*     */   public static final String CONTROL_TITLE_GRID_REGEXP_ROW = "%r";
/*     */   public static final String CONTROL_TITLE_GRID_REGEXP_COLUMN = "%c";
/*     */   public static final String CONTROL_TITLE_UNKNOWN = "Unknown";
/*     */   public static final String CONTROL_TITLE_TABBED_PANE = "Tabbed Pane - Selected Index";
/*     */   public static final String CONTROL_TITLE_GRID_COORDINATE = "Grid [Row : %r ; Column : %c]";
/*     */   public static final int CONTROL_TAG_MAINMENUTREE = -1000;
/*     */   public static final int CONTROL_TAG_USERMENUTREE = -1001;
/*     */   public static final int CONTROL_TAG_RECORDER_MENU = -1002;
/*     */   public static final String MAIN_WORKPANE_FORM = "MAIN_WORKPANE_FORM";
/*     */   public static final int INCOMPATIBLE_ROW_INDEX = -100;
/*     */   public static final int INCOMPATIBLE_COL_INDEX = -100;
/*     */   public static final int FORM_TYPE_BROWSER = -10;
/*     */   public static final int FORM_TYPE_EDITOR = -11;
/*     */   public static final int FORM_TYPE_DIALOG = -12;
/*     */   public static final int FORM_TYPE_MESSAGE_DIALOG = -13;
/*     */   public static final int INVOKER_TYPE_GENERAL = -1;
/*     */   public static final int INVOKER_TYPE_INPLACE_EDITOR = 1;
/*     */   public static final int iNVOKER_TYPE_GRID = 2;
/*     */   public static final int STATUS_ERROR = 1;
/*     */   public static final int STATUS_FATAL = 2;
/*     */   public static final int STATUS_REPEAT = 3;
/*     */   public static final int STATUS_COMPLETED = 4;
/*     */   public static final int STATUS_PROCESS_MESSAGE_DIALOGS = 5;
/*     */   public static final int STATUS_UNPROCESS_MESSAGE_DIALOGS = 6;
/*     */   public static final int STATUS_CHECK_MESSAGE_DIALOG = 7;
/*     */   public static final int STATUS_INITIALIZED = 10;
/*     */   public static final int STATUS_TEST_REPORTED = 11;
/*     */   public static final int STATUS_TEST_TIMEOUT = 12;
/*     */   public static final int STATUS_EXTERNAL = 21;
/*     */   public static final String STATUS_TITLE_TEST = "%t";
/*     */   public static final String STATUS_TITLE_DATA_POOL = "%dp";
/*     */   public static final String STATUS_TITLE_ACTION = "%a";
/*     */   public static final String STATUS_LABEL_FAILED_TEXT = "Test (%t) failed for data pool # %dp and action # %a.";
/*     */   public static final String STATUS_LABEL_COMPLETED_TEXT = "Playing Test %t for data pool # %dp. Completed action # %a.";
/*     */   public static final String STATUS_LABEL_REPEATING_TEXT = "Playing Test %t for data pool # %dp. Repeating action # %a.";
/*     */   public static final String STATUS_LABEL_ERROR_TEXT = "Playing Test %t for data pool # %dp. Error on action # %a.";
/*     */   public static boolean AUTO_GENERATE_VPS = false;
/*     */   public static boolean AUTO_GENERATE_DATAPOOL = true;
/*     */   public static boolean DATAPOOL_UNIQUE_BY_TAG = false;
/*     */   public static boolean RECORD_GRID_SECONDARY_KEY = true;
/*     */   public static final int REPORT_ITEM_HEADER = 1;
/*     */   public static final int REPORT_ITEM_LINE = 2;
/*     */   public static final int REPORT_ITEM_DATAPOOL = 3;
/*     */   public static final int REPORT_ITEM_TEST = 4;
/*     */   public static final String REPLACE_TEST_INDEX = "%testInd%";
/*     */   public static final String REPLACE_TEST_NAME = "%testName%";
/*     */   public static final String REPLACE_TEST_DESC = "%testDesc%";
/*     */   public static final String REPLACE_TEST_STATUS = "%stat%";
/*     */   public static final String REPORT_HTML_TITLE = "j-Platform - Test Run Report";
/*     */   public static final String REPORT_HEADER_START = "TEST STARTED";
/*     */   public static final String REPORT_DATAPOOL_START = "DATA POOL";
/*     */   public static final String REPORT_ITEM_ACTION_ID = "ACTION ID";
/*     */   public static final String REPORT_ITEM_ACTION_DESC = "ACTION DESCRIPTION";
/*     */   public static final String REPORT_ITEM_ACTION = "ACTION";
/*     */   public static final String REPORT_ITEM_STATUS = "STATUS";
/*     */   public static final String REPORT_ITEM_TEST_CASE = "TEST CASE # %testInd%";
/*     */   public static final String REPORT_ITEM_TEST_DESC = "Description";
/*     */   public static final String REPORT_ITEM_TEST_NAME = "Test Name";
/*     */   public static final String REPORT_ITEM_TEST_STATUS = "Status";
/*     */   public static final String REPORT_ITEM_DATAPOOL_INDEX = "Data Pool Index";
/*     */   public static final String REPORT_ITEM_TIME = "TIME";
/*     */   public static final String REPORT_ITEM_ELAPSED_TIME = "ELAPSED TIME (ms)";
/*     */   public static final String REPORT_ITEM_EXTRA_DATA = "EXTRA DATA";
/*     */   public static final String REPORT_ITEM_SCREENSHOT_IMAGE = "IMAGE";
/*     */   public static final String REPORT_ITEM_SCREENSHOT_NOT_AVAILABLE = "Not Available";
/*     */   public static final String REPORT_ITEM_ERROR_TYPE = "ERROR/WARNING TYPE";
/*     */   public static final String REPORT_ITEM_ERROR_SNAPSHOT_INDEX = "SNAPSHOT INDEX";
/*     */   public static final String REPORT_ITEM_ERROR_SEVERITY = "ERROR/WARNING LEVEL";
/*     */   public static final String REPORT_ITEM_ERROR_TAG = "ERRONEOUS CONTROL TAG";
/*     */   public static final String REPORT_ITEM_ERROR_EXPLANATION = "ERROR/WARNING EXPLANATION";
/*     */   public static final String REPORT_ITEM_SCRIPT_FILE_PATH = "Script File Path";
/*     */   public static final String REPORT_ITEM_ERROR_COUNT_DETAILS = "Error Count Details";
/*     */   public static final String REPORT_ITEM_PERFORMANCE_DURATION_PATH = "PD File Path";
/*     */   public static final String REPORT_ITEM_SNAPSHOT_DURATION = "Snapshot Duration";
/*     */   public static final String REPORT_ITEM_CURRENT_DURATION = "Current Duration";
/*     */   public static final String REPORT_ITEM_TOTAL_SNAPSHOT_DURATION = "Total Duration (Snapshot)";
/*     */   public static final String REPORT_ITEM_TOTAL_CURRENT_DURATION = "Total Duration (Current)";
/*     */   public static final String REPORT_ITEM_SUCCESS_COUNT = "Success Count (Action Dur.)";
/*     */   public static final String REPORT_ITEM_FAILURE_COUNT = "Failure Count (Action Dur.)";
/*     */   public static final int NUMBER_COL_ACTION_ID = 0;
/*     */   public static final int NUMBER_COL_ACTION = 1;
/*     */   public static final int NUMBER_COL_STATUS = 2;
/*     */   public static final int NUMBER_COL_SCREENSHOT_IMAGE = 3;
/*     */   public static final int NUMBER_COL_ELAPSED_TIME = 4;
/*     */   public static final int NUMBER_COL_EXTRA_DATA = 5;
/*     */   public static final int NUMBER_COL_ERROR_TYPE = 6;
/*     */   public static final int NUMBER_COL_ERROR_SNAPSHOT_INDEX = 7;
/*     */   public static final int NUMBER_COL_ERROR_SEVERITY = 8;
/*     */   public static final int NUMBER_COL_ERROR_TAG = 9;
/*     */   public static final int NUMBER_COL_ERROR_EXPLANATION = 10;
/*     */   public static final int NUMBER_COL_ORIGINAL_REPORT = 11;
/*     */   public static final int NUMBER_COL_CURRENT_REPORT = 12;
/*     */   public static final int NUMBER_COL_OVERALL_RESULT_SHEET_TEST_NAME = 0;
/*     */   public static final int NUMBER_COL_OVERALL_RESULT_SHEET_DATAPOOL_INDEX = 1;
/*     */   public static final int NUMBER_COL_OVERALL_RESULT_SHEET_ERROR_COUNT_DETAILS = 2;
/*     */   public static final int NUMBER_COL_OVERALL_RESULT_SHEET_TEST_STATUS = 3;
/*     */   public static final int NUMBER_COL_PERFORMANCE_DURATIONS_TEST_NAME = 0;
/*     */   public static final int NUMBER_COL_PERFORMANCE_DURATIONS_ACTION_ID = 1;
/*     */   public static final int NUMBER_COL_PERFORMANCE_DURATIONS_ACTION_DESC = 2;
/*     */   public static final int NUMBER_COL_SNAPSHOT_DURATION = 3;
/*     */   public static final int NUMBER_COL_CURRENT_DURATION = 4;
/*     */   public static final int NUMBER_ROW_STARTING = 0;
/*     */   public static final String REPORT_UNDERLINE_STATUS = "---------";
/*     */   public static final String REPORT_UNDERLINE_ID = "--";
/*     */   public static final int REPORT_FILE_TYPE_EXCEL = 1;
/*     */   public static final int REPORT_FILE_TYPE_HTML = 2;
/*     */   public static final int REPORT_FILE_TYPE_TXT = 3;
/*     */   public static final int TEST_STOP_REASON_TIMEOUT = 1;
/*     */   public static final int TEST_STOP_REASON_INCONSISTENT_DATAPOOL_FILE = 2;
/*     */   public static final int TEST_STOP_REASON_INCONSISTENT_RECORDING_FILE = 3;
/*     */   public static final int TEST_STOP_REASON_FINISH = 4;
/*     */   public static final int TEST_STOP_REASON_EXCEPTION = 5;
/*     */   public static final int BATCH_WAITING_TIME = 30;
/*     */   public static final int RES_ID_MAIN_MENU_DESC = 12900;
/*     */   public static Hashtable fileFilterMaps;
/* 306 */   public static Hashtable statusMaps = new Hashtable<>(); static {
/* 307 */     statusMaps.put(Integer.valueOf(4), "OK");
/* 308 */     statusMaps.put(Integer.valueOf(1), "ERROR");
/* 309 */     statusMaps.put(Integer.valueOf(2), "FATAL");
/* 310 */     statusMaps.put(Integer.valueOf(10), "INITIALIZED");
/* 311 */     statusMaps.put(Integer.valueOf(3), "REPEAT");
/* 312 */     statusMaps.put(Integer.valueOf(12), "TIMEOUT");
/* 313 */     statusMaps.put(Integer.valueOf(21), "EXTERNAL");
/*     */     
/* 315 */     fileFilterMaps = new Hashtable<>();
/* 316 */     fileFilterMaps.put(Integer.valueOf(3), "txt");
/* 317 */     fileFilterMaps.put(Integer.valueOf(2), "htm");
/* 318 */     fileFilterMaps.put(Integer.valueOf(1), "xls");
/*     */     
/* 320 */     reportedStatusList = new Hashtable<>();
/* 321 */     reportedStatusList.put(Integer.valueOf(1), Integer.valueOf(1));
/* 322 */     reportedStatusList.put(Integer.valueOf(2), Integer.valueOf(2));
/* 323 */     reportedStatusList.put(Integer.valueOf(12), Integer.valueOf(12));
/* 324 */     reportedStatusList.put(Integer.valueOf(21), Integer.valueOf(21));
/*     */     
/* 326 */     snapshotStatusList = new Hashtable<>();
/* 327 */     snapshotStatusList.put(Integer.valueOf(2), Integer.valueOf(2));
/* 328 */     snapshotStatusList.put(Integer.valueOf(12), Integer.valueOf(12));
/*     */     
/* 330 */     defaultReportItemList = new Hashtable<>();
/* 331 */     defaultReportItemList.put(Integer.valueOf(4), Integer.valueOf(4));
/* 332 */     defaultReportItemList.put(Integer.valueOf(3), Integer.valueOf(3));
/* 333 */     defaultReportItemList.put(Integer.valueOf(2), Integer.valueOf(2));
/* 334 */     defaultReportItemList.put(Integer.valueOf(1), Integer.valueOf(1));
/* 335 */     defaultReportItemList.put(Integer.valueOf(12), Integer.valueOf(12));
/* 336 */     defaultReportItemList.put(Integer.valueOf(21), Integer.valueOf(21));
/*     */     
/* 338 */     methodMaps = new Hashtable<>();
/* 339 */     methodMaps.put("close", "CLOSE");
/* 340 */     methodMaps.put("keyPressed", "KEY_PRESSED");
/* 341 */     methodMaps.put("mouseClicked", "MOUSE_CLICKED");
/* 342 */     methodMaps.put("open", "OPEN");
/* 343 */     methodMaps.put("setDate", "SET_DATE");
/* 344 */     methodMaps.put("setNumber", "SET_NUMBER");
/* 345 */     methodMaps.put("setText", "SET_TEXT");
/* 346 */     methodMaps.put("setTime", "SET_TIME");
/* 347 */     methodMaps.put("setValue", "VERIFY");
/* 348 */     methodMaps.put("lookup", "LOOKUP");
/* 349 */     methodMaps.put("clickGridCell", "GRID_CELL_CLICK");
/* 350 */     methodMaps.put("setSelected", "SET_SELECTED");
/* 351 */     methodMaps.put("clickButton", "BUTTON_CLICK");
/* 352 */     methodMaps.put("activateContainer", "ACTIVATE_CONTAINER");
/* 353 */     methodMaps.put("closeContainer", "CLOSE_CONTAINER");
/* 354 */     methodMaps.put("setComboIndex", "CMB_SET_SELECTED_INDEX");
/* 355 */     methodMaps.put("setTabbedPaneIndex", "TABBEDPANE_SET_SELECTED_INDEX");
/* 356 */     methodMaps.put("setPageIndex", "SET_PAGE_INDEX");
/* 357 */     methodMaps.put("clickMenuButtonItem", "MENU_BUTTON_ITEM_CLICK");
/* 358 */     methodMaps.put("selectComponent", "REQUEST_FOCUS");
/* 359 */     methodMaps.put("deselectComponent", "LOST_FOCUS");
/* 360 */     methodMaps.put("showPopUpMenu", "POPUPMENU_WILL_BECOME_VISIBLE");
/* 361 */     methodMaps.put("selectPopUpMenuItem", "POPUPMENU_ITEM_SELECTED");
/* 362 */     methodMaps.put("selectMainMenuItem", "MAINMENU_ITEM_SELECTED");
/* 363 */     methodMaps.put("performVerification", "VERIFICATION_POINT");
/* 364 */     methodMaps.put("selectGridRow", "GRID_ROW_SELECTED");
/* 365 */     methodMaps.put("doubleClickGridCell", "GRID_DOUBLE_CLICK");
/* 366 */     methodMaps.put("editGridCell", "GRID_CELL_EDIT");
/* 367 */     methodMaps.put("insertGridRow", "GRID_INSERT_ROW");
/* 368 */     methodMaps.put("appendGridRow", "GRID_APPEND_ROW");
/* 369 */     methodMaps.put("deleteGridRow", "GRID_DELETE_ROW");
/* 370 */     methodMaps.put("lookupGrid", "GRID_LOOKUP");
/* 371 */     methodMaps.put("selectGrid", "GRID_REQUEST_FOCUS");
/* 372 */     methodMaps.put("toggleFilterGridEntry", "FILTERGRID_TOGGLE_ENTRY");
/* 373 */     methodMaps.put("setComboItemStr", "CMB_SET_SELECTED_ITEM_STR");
/* 374 */     methodMaps.put("setEditorPaneText", "EDITORPANE_SET_TEXT");
/* 375 */     methodMaps.put("performMsgDialogAction", "MSG_DLG_ACTION_PERFORMED");
/* 376 */     methodMaps.put("keyPressedMsgDialog", "MSG_DLG_KEY_PRESSED");
/* 377 */     methodMaps.put("selectGridCell", "GRID_CELL_SELECTED");
/* 378 */     methodMaps.put("finalizeTest", "FINALIZE_TEST");
/* 379 */     methodMaps.put("addExternalReportItem", "EXTERNAL_REPORT_ITEM");
/* 380 */     methodMaps.put("generateFormContentSnapshot", "GENERATE_FORM_SNAPSHOT");
/* 381 */     methodMaps.put("gridDoubleClickLastEditedRow", "GRID_DBL_CLCK_LAST_ROW");
/* 382 */     methodMaps.put("gridEditLastEditedRow", "GRID_EDIT_LAST_ROW");
/* 383 */     methodMaps.put("loadPaneMessages", "LOAD_PANE_MESSAGES");
/* 384 */     methodMaps.put("loadMessageDialog", "LOAD_MESSAGE_DIALOG");
/* 385 */     methodMaps.put("showMessageDialog", "SHOW_MESSAGE_DIALOG");
/* 386 */     methodMaps.put("confirm", "CONFIRM");
/* 387 */     methodMaps.put("clickMenuButton", "MENU_BUTTON_CLICK");
/* 388 */     methodMaps.put("performFilterSaveDialogAction", "FLTR_SAVE_DLG_ACTION_PERFORMED");
/* 389 */     methodMaps.put("loadFilterSaveDialog", "LOAD_FILTER_SAVE_DIALOG");
/* 390 */     methodMaps.put("selectFilterSaveDialogComp", "FLTR_SAVE_DLG_REQUEST_FOCUS");
/* 391 */     methodMaps.put("verifyFilterSaveDialogCompValue", "FLTR_SAVE_DLG_VERIFY_CONTENT");
/* 392 */     methodMaps.put("expandGridNode", "GRID_EXPAND_NODE");
/* 393 */     methodMaps.put("doubleClickFilterGridCell", "FILTER_GRID_DOUBLE_CLICK");
/* 394 */     methodMaps.put("toggleFilterGridSubgroup", "FILTERGRID_TOGGLE_SUBGROUP");
/* 395 */     methodMaps.put("performReportRunDialogAction", "REPORT_RUN_DLG_ACTION_PERFORMED");
/* 396 */     methodMaps.put("loadReportRunDialog", "LOAD_REPORT_RUN_DIALOG");
/* 397 */     methodMaps.put("checkColumnExistance", "CHECK_COLUMN_EXISTANCE");
/* 398 */     methodMaps.put("checkColumnHeaderCaptions", "CHECK_COLUMN_HEADER_CAPTIONS");
/* 399 */     methodMaps.put("checkColumnPropertyFields", "CHECK_COLUMN_PROPERTY_FIELDS");
/* 400 */     methodMaps.put("checkColumnWidths", "CHECK_COLUMN_WIDTHS");
/* 401 */     methodMaps.put("checkColumnMandatories", "CHECK_COLUMN_MANDATORY");
/* 402 */     methodMaps.put("checkColumnHasLookups", "CHECK_COLUMN_HAS_LOOK_UP");
/* 403 */     methodMaps.put("checkColumnEditTypes", "CHECK_COLUMN_EDIT_TYPE");
/* 404 */     methodMaps.put("checkColumnOrders", "CHECK_COLUMN_ORDERS");
/* 405 */     methodMaps.put("checkFilterExistance", "CHECK_FILTER_EXISTANCE");
/* 406 */     methodMaps.put("checkFilterDescriptions", "CHECK_FILTER_DESCRIPTIONS");
/* 407 */     methodMaps.put("checkFilterOrders", "CHECK_FILTER_ORDERS");
/* 408 */     methodMaps.put("checkControlTitles", "CHECK_CONTROL_TITLE");
/* 409 */     methodMaps.put("checkControlValues", "CHECK_CONTROL_VALUE");
/* 410 */     methodMaps.put("checkControlItemListValues", "CHECK_CONTROL_ITEM_LIST");
/* 411 */     methodMaps.put("checkGridData", "CHECK_GRID_DATA");
/* 412 */     methodMaps.put("sortGridData", "SORT_GRID_DATA");
/* 413 */     methodMaps.put("showSearchRow", "SET_SEARCH_ROW_VISIBLE");
/* 414 */     methodMaps.put("hideSearchRow", "SET_SEARCH_ROW_INVISIBLE");
/* 415 */     methodMaps.put("resize", "RESIZE_CONTAINER");
/* 416 */     methodMaps.put("checkControlMandatoryStatus", "CHECK_CONTROL_MANDATORY");
/* 417 */     methodMaps.put("checkControlEnablementStatus", "CHECK_CONTROL_ENABLED");
/* 418 */     methodMaps.put("checkControlVisibilities", "CHECK_CONTROL_VISIBLE");
/* 419 */     methodMaps.put("checkControlSizes", "CHECK_CONTROL_SIZE");
/* 420 */     methodMaps.put("checkControlLocations", "CHECK_CONTROL_LOCATION");
/* 421 */     methodMaps.put("checkColumnHeaderAlignments", "CHECK_COLUMN_HEADER_ALIGNMENTS");
/* 422 */     methodMaps.put("checkColumnCellAlignments", "CHECK_COLUMN_CELL_ALIGNMENTS");
/* 423 */     methodMaps.put("checkRowStates", "CHECK_ROW_STATES");
/* 424 */     methodMaps.put("checkFilterItemListValues", "CHECK_FILTER_ITEM_LIST");
/* 425 */     methodMaps.put("checkSelectedFilterItemListValues", "CHECK_FILTER_SELECTED_ITEM_LIST");
/* 426 */     methodMaps.put("applyHeaderFilters", "APPLY_GRID_HEADER_FILTERS");
/* 427 */     methodMaps.put("selectRowBySecondaryKey", "GRID_ROW_SELECTED_BY_SECONDARY_KEY");
/* 428 */     methodMaps.put("selectAll", "POP_UP_SELECT_ALL");
/* 429 */     methodMaps.put("deselectAll", "POP_UP_DESELECT_ALL");
/* 430 */     methodMaps.put("invertSelection", "POP_UP_INVERT_SELECTION");
/* 431 */     methodMaps.put("selectTreeGridCell", "TREE_GRID_CELL_SELECTED");
/* 432 */     methodMaps.put("checkFormTitle", "CHECK_FORM_TITLE");
/* 433 */     methodMaps.put("checkMessageListContent", "CHECK_MESSAGE_LIST_CONTENT");
/* 434 */     methodMaps.put("scrollPageUp", "GRID_SCROLL_PAGE_UP");
/* 435 */     methodMaps.put("scrollPageDown", "GRID_SCROLL_PAGE_DOWN");
/* 436 */     methodMaps.put("scrollLineUp", "GRID_SCROLL_LINE_UP");
/* 437 */     methodMaps.put("scrollLineDown", "GRID_SCROLL_LINE_DOWN");
/* 438 */     methodMaps.put("scrollTop", "GRID_SCROLL_TOP");
/* 439 */     methodMaps.put("scrollBottom", "GRID_SCROLL_BOTTOM");
/* 440 */     methodMaps.put("executeReport", "EXECUTE_REPORT");
/* 441 */     methodMaps.put("executePDFReport", "EXECUTE_PDF_REPORT");
/* 442 */     methodMaps.put("takeScreenshot", "TAKE_SCREENSHOT");
/* 443 */     methodMaps.put("compareWithCurrentDate", "CHECK_CURRENT_DATE");
/* 444 */     methodMaps.put("compareWithPeriodStartDate", "CHECK_PERIOD_START_DATE");
/* 445 */     methodMaps.put("checkControlExistance", "CHECK_CONTROL_EXISTANCE");
/* 446 */     methodMaps.put("verifyGridCellValue", "VERIFY_GRID_CELL_VALUE");
/* 447 */     methodMaps.put("gridCellValueVerify", "GRID_CELL_VALUE_VERIFY");
/* 448 */     methodMaps.put("expandGroupNode", "GRID_EXPAND_GROUP_NODE");
/* 449 */     methodMaps.put("setFormula", "SET_SYNTAX_EDIT_FORMULA");
/* 450 */     methodMaps.put("setLocalVariable", "SET_LOCAL_VARIABLE");
/* 451 */     methodMaps.put("setPackageVariable", "SET_PACKAGE_VARIABLE");
/* 452 */     methodMaps.put("setGlobalVariable", "SET_GLOBAL_VARIABLE");
/* 453 */     methodMaps.put("checkValue", "CHECK_VALUE");
/* 454 */     methodMaps.put("waitForBatchOperation", "BATCH_OPERATION");
/* 455 */     methodMaps.put("checkMsgDialogContent", "MSG_DLG_CHECK_CONTENT");
/* 456 */     methodMaps.put("doSetWorkDate", "SET_WORK_DATE");
/* 457 */     methodMaps.put("doExchangeRates", "EXCHANGE_RATES");
/* 458 */     methodMaps.put("grabGridFocus", "GRID_GRAB_FOCUS");
/* 459 */     methodMaps.put("breadcrumbItemSelect", "ACTION_BREADCRUMB_ITEM_SELECTED");
/*     */     
/* 461 */     actionMaps = new Hashtable<>();
/* 462 */     actionMaps.put("CLOSE", "close");
/* 463 */     actionMaps.put("KEY_PRESSED", "keyPressed");
/* 464 */     actionMaps.put("MOUSE_CLICKED", "mouseClicked");
/* 465 */     actionMaps.put("OPEN", "open");
/* 466 */     actionMaps.put("SET_DATE", "setDate");
/* 467 */     actionMaps.put("SET_NUMBER", "setNumber");
/* 468 */     actionMaps.put("SET_TEXT", "setText");
/* 469 */     actionMaps.put("SET_TIME", "setTime");
/* 470 */     actionMaps.put("VERIFY", "setValue");
/* 471 */     actionMaps.put("LOOKUP", "lookup");
/* 472 */     actionMaps.put("GRID_CELL_CLICK", "clickGridCell");
/* 473 */     actionMaps.put("SET_SELECTED", "setSelected");
/* 474 */     actionMaps.put("BUTTON_CLICK", "clickButton");
/* 475 */     actionMaps.put("ACTIVATE_CONTAINER", "activateContainer");
/* 476 */     actionMaps.put("CLOSE_CONTAINER", "closeContainer");
/* 477 */     actionMaps.put("CMB_SET_SELECTED_INDEX", "setComboIndex");
/* 478 */     actionMaps.put("TABBEDPANE_SET_SELECTED_INDEX", "setTabbedPaneIndex");
/* 479 */     actionMaps.put("SET_PAGE_INDEX", "setPageIndex");
/* 480 */     actionMaps.put("MENU_BUTTON_ITEM_CLICK", "clickMenuButtonItem");
/* 481 */     actionMaps.put("REQUEST_FOCUS", "selectComponent");
/* 482 */     actionMaps.put("POPUPMENU_WILL_BECOME_VISIBLE", "showPopUpMenu");
/* 483 */     actionMaps.put("POPUPMENU_ITEM_SELECTED", "selectPopUpMenuItem");
/* 484 */     actionMaps.put("MAINMENU_ITEM_SELECTED", "selectMainMenuItem");
/* 485 */     actionMaps.put("VERIFICATION_POINT", "performVerification");
/* 486 */     actionMaps.put("GRID_ROW_SELECTED", "selectGridRow");
/* 487 */     actionMaps.put("GRID_DOUBLE_CLICK", "doubleClickGridCell");
/* 488 */     actionMaps.put("GRID_CELL_EDIT", "editGridCell");
/* 489 */     actionMaps.put("GRID_INSERT_ROW", "insertGridRow");
/* 490 */     actionMaps.put("GRID_APPEND_ROW", "appendGridRow");
/* 491 */     actionMaps.put("GRID_DELETE_ROW", "deleteGridRow");
/* 492 */     actionMaps.put("GRID_LOOKUP", "lookupGrid");
/* 493 */     actionMaps.put("GRID_REQUEST_FOCUS", "selectGrid");
/* 494 */     actionMaps.put("FILTERGRID_TOGGLE_ENTRY", "toggleFilterGridEntry");
/* 495 */     actionMaps.put("CMB_SET_SELECTED_ITEM_STR", "setComboItemStr");
/* 496 */     actionMaps.put("EDITORPANE_SET_TEXT", "setEditorPaneText");
/* 497 */     actionMaps.put("MSG_DLG_ACTION_PERFORMED", "performMsgDialogAction");
/* 498 */     actionMaps.put("MSG_DLG_KEY_PRESSED", "keyPressedMsgDialog");
/* 499 */     actionMaps.put("GRID_CELL_SELECTED", "selectGridCell");
/* 500 */     actionMaps.put("FINALIZE_TEST", "finalizeTest");
/* 501 */     actionMaps.put("EXTERNAL_REPORT_ITEM", "addExternalReportItem");
/* 502 */     actionMaps.put("GENERATE_FORM_SNAPSHOT", "generateFormContentSnapshot");
/* 503 */     actionMaps.put("GRID_DBL_CLCK_LAST_ROW", "gridDoubleClickLastEditedRow");
/* 504 */     actionMaps.put("GRID_EDIT_LAST_ROW", "gridEditLastEditedRow");
/* 505 */     actionMaps.put("LOAD_PANE_MESSAGES", "loadPaneMessages");
/* 506 */     actionMaps.put("LOAD_MESSAGE_DIALOG", "loadMessageDialog");
/* 507 */     actionMaps.put("SHOW_MESSAGE_DIALOG", "showMessageDialog");
/* 508 */     actionMaps.put("CONFIRM", "confirm");
/* 509 */     actionMaps.put("MENU_BUTTON_CLICK", "clickMenuButton");
/* 510 */     actionMaps.put("FLTR_SAVE_DLG_ACTION_PERFORMED", "performFilterSaveDialogAction");
/* 511 */     actionMaps.put("LOAD_FILTER_SAVE_DIALOG", "loadFilterSaveDialog");
/* 512 */     actionMaps.put("FLTR_SAVE_DLG_REQUEST_FOCUS", "selectFilterSaveDialogComp");
/* 513 */     actionMaps.put("FLTR_SAVE_DLG_VERIFY_CONTENT", "verifyFilterSaveDialogCompValue");
/* 514 */     actionMaps.put("GRID_EXPAND_NODE", "expandGridNode");
/* 515 */     actionMaps.put("FILTER_GRID_DOUBLE_CLICK", "doubleClickFilterGridCell");
/* 516 */     actionMaps.put("FILTERGRID_TOGGLE_SUBGROUP", "toggleFilterGridSubgroup");
/* 517 */     actionMaps.put("REPORT_RUN_DLG_ACTION_PERFORMED", "performReportRunDialogAction");
/* 518 */     actionMaps.put("LOAD_REPORT_RUN_DIALOG", "loadReportRunDialog");
/* 519 */     actionMaps.put("CHECK_COLUMN_EXISTANCE", "checkColumnExistance");
/* 520 */     actionMaps.put("CHECK_COLUMN_HEADER_CAPTIONS", "checkColumnHeaderCaptions");
/* 521 */     actionMaps.put("CHECK_COLUMN_WIDTHS", "checkColumnWidths");
/* 522 */     actionMaps.put("CHECK_COLUMN_MANDATORY", "checkColumnMandatories");
/* 523 */     actionMaps.put("CHECK_COLUMN_HAS_LOOK_UP", "checkColumnHasLookups");
/* 524 */     actionMaps.put("CHECK_COLUMN_EDIT_TYPE", "checkColumnEditTypes");
/* 525 */     actionMaps.put("CHECK_COLUMN_PROPERTY_FIELDS", "checkColumnPropertyFields");
/* 526 */     actionMaps.put("CHECK_COLUMN_ORDERS", "checkColumnOrders");
/* 527 */     actionMaps.put("CHECK_FILTER_EXISTANCE", "checkFilterExistance");
/* 528 */     actionMaps.put("CHECK_FILTER_DESCRIPTIONS", "checkFilterDescriptions");
/* 529 */     actionMaps.put("CHECK_FILTER_ORDERS", "checkFilterOrders");
/* 530 */     actionMaps.put("CHECK_CONTROL_TITLE", "checkControlTitles");
/* 531 */     actionMaps.put("CHECK_CONTROL_VALUE", "checkControlValues");
/* 532 */     actionMaps.put("CHECK_CONTROL_TEXT", "checkControlTexts");
/* 533 */     actionMaps.put("CHECK_CONTROL_ITEM_LIST", "checkControlItemListValues");
/* 534 */     actionMaps.put("CHECK_GRID_DATA", "checkGridData");
/* 535 */     actionMaps.put("SORT_GRID_DATA", "sortGridData");
/* 536 */     actionMaps.put("SET_SEARCH_ROW_VISIBLE", "showSearchRow");
/* 537 */     actionMaps.put("SET_SEARCH_ROW_INVISIBLE", "hideSearchRow");
/* 538 */     actionMaps.put("RESIZE_CONTAINER", "resize");
/* 539 */     actionMaps.put("CHECK_CONTROL_MANDATORY", "checkControlMandatoryStatus");
/* 540 */     actionMaps.put("CHECK_CONTROL_ENABLED", "checkControlEnablementStatus");
/* 541 */     actionMaps.put("CHECK_CONTROL_VISIBLE", "checkControlVisibilities");
/* 542 */     actionMaps.put("CHECK_CONTROL_SIZE", "checkControlSizes");
/* 543 */     actionMaps.put("CHECK_CONTROL_LOCATION", "checkControlLocations");
/* 544 */     actionMaps.put("CHECK_COLUMN_HEADER_ALIGNMENTS", "checkColumnHeaderAlignments");
/* 545 */     actionMaps.put("CHECK_COLUMN_CELL_ALIGNMENTS", "checkColumnCellAlignments");
/* 546 */     actionMaps.put("CHECK_ROW_STATES", "checkRowStates");
/* 547 */     actionMaps.put("CHECK_FILTER_ITEM_LIST", "checkFilterItemListValues");
/* 548 */     actionMaps.put("CHECK_FILTER_SELECTED_ITEM_LIST", "checkSelectedFilterItemListValues");
/* 549 */     actionMaps.put("APPLY_GRID_HEADER_FILTERS", "applyHeaderFilters");
/* 550 */     actionMaps.put("GRID_ROW_SELECTED_BY_SECONDARY_KEY", "selectRowBySecondaryKey");
/* 551 */     actionMaps.put("POP_UP_SELECT_ALL", "selectAll");
/* 552 */     actionMaps.put("POP_UP_DESELECT_ALL", "deselectAll");
/* 553 */     actionMaps.put("POP_UP_INVERT_SELECTION", "invertSelection");
/* 554 */     actionMaps.put("TREE_GRID_CELL_SELECTED", "selectTreeGridCell");
/* 555 */     actionMaps.put("CHECK_FORM_TITLE", "checkFormTitle");
/* 556 */     actionMaps.put("CHECK_MESSAGE_LIST_CONTENT", "checkMessageListContent");
/* 557 */     actionMaps.put("GRID_SCROLL_PAGE_UP", "scrollPageUp");
/* 558 */     actionMaps.put("GRID_SCROLL_PAGE_DOWN", "scrollPageDown");
/* 559 */     actionMaps.put("GRID_SCROLL_LINE_UP", "scrollLineUp");
/* 560 */     actionMaps.put("GRID_SCROLL_LINE_DOWN", "scrollLineDown");
/* 561 */     actionMaps.put("GRID_SCROLL_TOP", "scrollTop");
/* 562 */     actionMaps.put("GRID_SCROLL_BOTTOM", "scrollBottom");
/* 563 */     actionMaps.put("EXECUTE_REPORT", "executeReport");
/* 564 */     actionMaps.put("EXECUTE_PDF_REPORT", "executePDFReport");
/* 565 */     actionMaps.put("TAKE_SCREENSHOT", "takeScreenshot");
/* 566 */     actionMaps.put("CHECK_CURRENT_DATE", "compareWithCurrentDate");
/* 567 */     actionMaps.put("CHECK_PERIOD_START_DATE", "compareWithPeriodStartDate");
/* 568 */     actionMaps.put("CHECK_CONTROL_EXISTANCE", "checkControlExistance");
/* 569 */     actionMaps.put("VERIFY_GRID_CELL_VALUE", "verifyGridCellValue");
/* 570 */     actionMaps.put("GRID_CELL_VALUE_VERIFY", "gridCellValueVerify");
/* 571 */     actionMaps.put("GRID_EXPAND_GROUP_NODE", "expandGroupNode");
/* 572 */     actionMaps.put("SET_SYNTAX_EDIT_FORMULA", "setFormula");
/* 573 */     actionMaps.put("SET_LOCAL_VARIABLE", "setLocalVariable");
/* 574 */     actionMaps.put("SET_PACKAGE_VARIABLE", "setPackageVariable");
/* 575 */     actionMaps.put("SET_GLOBAL_VARIABLE", "setGlobalVariable");
/* 576 */     actionMaps.put("CHECK_VALUE", "checkValue");
/* 577 */     actionMaps.put("BATCH_OPERATION", "waitForBatchOperation");
/* 578 */     actionMaps.put("MSG_DLG_CHECK_CONTENT", "checkMsgDialogContent");
/* 579 */     actionMaps.put("SET_WORK_DATE", "doSetWorkDate");
/* 580 */     actionMaps.put("EXCHANGE_RATES", "doExchangeRates");
/* 581 */     actionMaps.put("GRID_GRAB_FOCUS", "grabGridFocus");
/* 582 */     actionMaps.put("ACTION_BREADCRUMB_ITEM_SELECTED", "breadcrumbItemSelect");
/*     */     
/* 584 */     columnAlignMaps = new Hashtable<>();
/* 585 */     columnAlignMaps.put(Integer.valueOf(0), "CENTER");
/* 586 */     columnAlignMaps.put(Integer.valueOf(1), "TOP");
/* 587 */     columnAlignMaps.put(Integer.valueOf(2), "LEFT");
/* 588 */     columnAlignMaps.put(Integer.valueOf(3), "BOTTOM");
/* 589 */     columnAlignMaps.put(Integer.valueOf(4), "RIGHT");
/*     */   }
/*     */   
/*     */   public static Hashtable reportedStatusList;
/*     */   public static Hashtable snapshotStatusList;
/*     */   public static Hashtable defaultReportItemList;
/*     */   public static Hashtable methodMaps;
/*     */   public static Hashtable actionMaps;
/*     */   public static Hashtable columnAlignMaps;
/*     */ }


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\recording\JLbsEventConstants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */