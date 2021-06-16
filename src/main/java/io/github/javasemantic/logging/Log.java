package io.github.javasemantic.logging;

import java.lang.reflect.Type;
import io.github.javasemantic.logging.common.LogLevel;
import io.github.javasemantic.logging.common.LogObject;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Log {

  @Setter
  private static LogLevel assignedLevel = LogLevel.INFO;

  public static void warning(Type type, String message) {
    print(create(LogLevel.WARNING, type, message));
  }

  public static void trace(Type type, String message) {
    print(create(LogLevel.TRACE, type, message));
  }

  public static void debug(Type type, String message) {
    print(create(LogLevel.DEBUG, type, message));
  }

  public static void info(Type type, String message) {
    print(create(LogLevel.INFO, type, message));
  }

  public static void error(Type type, String message) {
    print(create(LogLevel.ERROR, type, message));
  }

  private static LogObject create(LogLevel level, Type type, String message) {
    return LogObject
        .builder()
        .level(LogLevel.DEBUG)
        .message(message)
        .type(type)
        .build();
  }

  private static void print(LogObject logObject) {
    if (logObject.getLevel().equals(assignedLevel)) {
      System.out.printf("[%s] %s: %s%n", logObject.getLevel(), logObject.getType(),
          logObject.getMessage());
    }
  }
}
