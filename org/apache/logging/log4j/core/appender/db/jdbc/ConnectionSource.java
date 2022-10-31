package org.apache.logging.log4j.core.appender.db.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.logging.log4j.core.LifeCycle;

public interface ConnectionSource extends LifeCycle {
  Connection getConnection() throws SQLException;
  
  String toString();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\appender\db\jdbc\ConnectionSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */