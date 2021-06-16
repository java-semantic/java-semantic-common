package io.github.javasemantic.utility;

import java.util.regex.Pattern;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidConventionalCommitUtil {

  public static boolean isValid(final String message) {
    return Pattern.matches(
        "^(build|chore|ci|docs|feat|fix|perf|refactor|revert|style|test)?!?(\\([a-z ]+\\))?: [\\w ]+$",
        message);
  }

}
