package io.github.javasemantic.install.hooks.executable;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import io.github.javasemantic.primitive.logging.Log;

import static java.util.Objects.requireNonNull;

public class ExecutableDefaultImpl implements Executable {

    private static final String SHEBANG = "#!/bin/bash";

    private final Path file;

    public ExecutableDefaultImpl(Path file) throws IOException {
        requireNonNull(file);

        this.file = file;

        if (!Files.exists(file)) {
            Log.info(this.getClass(), "Creating " + file);
            Files.createFile(file);
            truncate();
        } else {
            Log.info(this.getClass(),file + " already exists");
        }

        Log.info(this.getClass(), "Marking '" + file + "' as executable");
        Set<PosixFilePermission> permissions;
        try {
            permissions = Files.getPosixFilePermissions(file);
        } catch (UnsupportedOperationException ignored) {
            return;
        }

        permissions.add(PosixFilePermission.OWNER_EXECUTE);
        permissions.add(PosixFilePermission.GROUP_EXECUTE);
        permissions.add(PosixFilePermission.OTHERS_EXECUTE);

        Files.setPosixFilePermissions(file, permissions);
    }

    @Override
    public void truncate() throws IOException {
        Log.info(this.getClass(), "Truncating '" + file + "'");
        Files.write(file, Collections.singleton(SHEBANG), StandardOpenOption.TRUNCATE_EXISTING);
    }

    @Override
    public Executable truncateWithTemplate(
            Supplier<InputStream> template, String sourceEncoding, Object... values) throws IOException {
        try (InputStream inputStream = template.get()) {
            String rawContent = IOUtils.toString(inputStream, sourceEncoding);
            Object[] refinedValues = Stream.of(values).map(this::unixifyPath).toArray();
            String content = String.format(rawContent, refinedValues);
            Files.write(file, content.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        }
        return this;
    }

    @Override
    public Executable appendCommandCall(String commandCall) throws IOException {
        String unixCommandCall = unixifyPath(commandCall, true);
        boolean callExists =
                Files.readAllLines(file).stream().anyMatch(s -> s.contains(unixCommandCall));
        if (callExists) {
            Log.info(this.getClass(), "Command call already exists in " + file);
        } else {
            Log.info(this.getClass(), "No command call found in " + file);
            Log.info(this.getClass(), "Appending the command call to " + file);
            Files.write(file, Collections.singletonList(unixCommandCall), StandardOpenOption.APPEND);
            Log.info(this.getClass(), "Appended the command call to " + file);
        }
        return this;
    }

    @Override
    public Executable removeCommandCall(String commandCall) {
        String unixCommandCall = unixifyPath(commandCall, true);
        try {
            List<String> linesToKeep =
                    Files.readAllLines(file).stream()
                            .filter(line -> !unixCommandCall.equals(line))
                            .collect(Collectors.toList());
            Files.write(file, linesToKeep, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    private String unixifyPath(Object o) {
        return unixifyPath(o, false);
    }

    private String unixifyPath(Object o, boolean force) {
        if (!force && !(o instanceof Path)) {
            return String.valueOf(o);
        }

        String result;
        if (o instanceof Path) {
            Path path = (Path) o;
            result = path.toAbsolutePath().toString();
        } else {
            result = String.valueOf(o);
        }
        return "\"" + StringUtils.replace(result, "\\", "/") + "\"";
    }

    @Override
    public String toString() {
        return file.toString();
    }
}
