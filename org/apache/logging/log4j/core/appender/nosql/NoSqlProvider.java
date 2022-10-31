package org.apache.logging.log4j.core.appender.nosql;

public interface NoSqlProvider<C extends NoSqlConnection<?, ? extends NoSqlObject<?>>> {
  C getConnection();
  
  String toString();
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\appender\nosql\NoSqlProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */