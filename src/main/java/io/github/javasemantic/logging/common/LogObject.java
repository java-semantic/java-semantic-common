package io.github.javasemantic.logging.common;

import java.lang.reflect.Type;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class LogObject {

  private final LogLevel level;
  private final String message;
  private final Type type;

}

