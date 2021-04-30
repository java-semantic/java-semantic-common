package io.github.javasemantic.install.hooks.executable.common;

import java.io.IOException;
import java.util.function.Supplier;

@FunctionalInterface
public interface SupplierWithIO<T> extends Supplier<T> {
  @Override
  @Deprecated
  default public T get() {
    throw new UnsupportedOperationException();
  }

  public T getWithIO() throws IOException;
}

