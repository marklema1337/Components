package com.lbs.data.database;

public interface IDBCommandExecutor {
  void initializeCommand(IDBConnectionCommand paramIDBConnectionCommand);
  
  boolean canExecuteCommand(IDBConnectionCommand paramIDBConnectionCommand);
  
  Object executeCommand(IDBConnectionCommand paramIDBConnectionCommand);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\com\lbs\data\database\IDBCommandExecutor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */