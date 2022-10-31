package org.apache.logging.log4j.core.config.builder.api;

public interface LoggableComponentBuilder<T extends ComponentBuilder<T>> extends FilterableComponentBuilder<T> {
  T add(AppenderRefComponentBuilder paramAppenderRefComponentBuilder);
}


/* Location:              C:\Users\Furkan\Desktop\logo\Components.jar!\org\apache\logging\log4j\core\config\builder\api\LoggableComponentBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */