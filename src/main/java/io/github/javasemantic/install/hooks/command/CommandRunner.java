package io.github.javasemantic.install.hooks.command;

import java.nio.file.Path;

public interface CommandRunner {
    void run(Path workingDir, String... command);
}
