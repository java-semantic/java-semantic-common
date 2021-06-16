package io.github.javasemantic.commit.engine.factory.enums;

import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.javasemantic.domain.model.Commit;
import io.github.javasemantic.domain.model.common.CommitComponents;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ConventionalRuleEnumTest {

  private static final List<String> types = List.of(
      "feat",
      "BREAKING CHANGE",
      "chore",
      "ci",
      "DEPRECATED",
      "build",
      "docs",
      "fix",
      "perf",
      "revert",
      "style",
      "test",
      "refactor"
  );

  @Test
  void when_getTypeRule_should_invalid_for_null() {

    //Given
    var conventionalValidationRule = ConventionalRuleEnum.TYPE_RULE.getConstructor().get();
    Commit commit = Commit.builder().commitComponents(CommitComponents.builder().build()).build();

    //When
    var actual = conventionalValidationRule.execute(commit);

    //Then
    assertTrue(actual.isInvalid());
  }

  @Test
  void when_getTypeRule_should_invalid_for_invalid_type() {

    //Given
    var conventionalValidationRule = ConventionalRuleEnum.TYPE_RULE.getConstructor().get();
    Commit commit = Commit.builder()
        .commitComponents(CommitComponents.builder().type("chicken").build()).build();

    //When
    var actual = conventionalValidationRule.execute(commit);

    //Then
    assertTrue(actual.isInvalid());
  }

  @Test
  void when_getTypeRule_should_valid_for_all_valid_types() {

    for (String type : types) {
      //Given
      var conventionalValidationRule = ConventionalRuleEnum.TYPE_RULE.getConstructor().get();
      Commit commit = Commit.builder()
          .commitComponents(CommitComponents.builder().type(type).build()).build();

      //When
      var actual = conventionalValidationRule.execute(commit);

      //Then
      assertTrue(actual.isAppliedOrValid());
    }
  }

  @Test
  void when_getBreaking_change_should_valid_for_exclamation() {

    //Given
    var conventionalValidationRule = ConventionalRuleEnum.BREAKING_EXCLAMATION_RULE.getConstructor()
        .get();
    Commit commit = Commit.builder()
        .commitComponents(CommitComponents.builder().exclamation(true).build()).build();

    //When
    var actual = conventionalValidationRule.execute(commit);

    //Then
    assertTrue(actual.isAppliedOrValid());
  }

  @Test
  void when_getBreaking_change_should_valid_for_body() {

    //Given
    var conventionalValidationRule = ConventionalRuleEnum.BREAKING_BODY_RULE.getConstructor()
        .get();
    Commit commit = Commit.builder().commitComponents(CommitComponents.builder().body(
        List.of("We are making these changes because I can", " BREAKING CHANGE for you!")).build()).build();

    //When
    var actual = conventionalValidationRule.execute(commit);

    //Then
    assertTrue(actual.isAppliedOrValid());
  }

  @Test
  void when_getBreaking_change_should_invalid_for_empty_body() {

    //Given
    var conventionalValidationRule = ConventionalRuleEnum.BREAKING_BODY_RULE.getConstructor()
        .get();
    Commit commit = Commit.builder().commitComponents(CommitComponents.builder().body(
        List.of()).build()).build();

    //When
    var actual = conventionalValidationRule.execute(commit);

    //Then
    assertTrue(actual.isNotApplicable());
  }

}
