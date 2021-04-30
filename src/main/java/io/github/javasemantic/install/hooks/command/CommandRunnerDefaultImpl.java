package io.github.javasemantic.install.hooks.command;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import io.github.javasemantic.primitive.logging.Log;

public class CommandRunnerDefaultImpl implements CommandRunner {

    @Override
    public void run(Path workingDir, String... command) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            if (workingDir != null) {
                processBuilder.directory(workingDir.toFile());
            }
            processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT);

            System.out.println();
            Log.debug(this.getClass(),"Executing '" + StringUtils.join(command, StringUtils.SPACE) + "'");
            Process process = processBuilder.start();

            String output =
                    IOUtils.toString(process.getInputStream(), String.valueOf(StandardCharsets.UTF_8)).trim()
                            + IOUtils.toString(process.getErrorStream(), String.valueOf(StandardCharsets.UTF_8)).trim();

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException(String.valueOf(exitCode));
            }

            Log.debug(this.getClass(), output);
            StringUtils.defaultIfBlank(output, null);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
