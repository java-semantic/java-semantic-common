package io.github.javasemantic.install.hooks.executable;

import java.io.IOException;
import java.nio.file.Path;
import io.github.javasemantic.install.hooks.executable.common.SupplierWithIO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExecutableFactory {

  public static Executable get(Path file) throws IOException {
    SupplierWithIO<Executable> constructor = () -> new ExecutableDefaultImpl(file);
    return constructor.getWithIO();
  }
}
