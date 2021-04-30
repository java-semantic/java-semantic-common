package io.github.javasemantic.install.hooks.command;

import java.util.function.Supplier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandRunnerFactory {

  private static final Supplier<CommandRunner> constructor = CommandRunnerDefaultImpl::new;

  public static CommandRunner get() {
    return constructor.get();
  }
}
