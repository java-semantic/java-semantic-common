package io.github.javasemantic.commit.engine.factory.enums;

import io.github.javasemantic.domain.model.Commit;
import io.github.javasemantic.domain.model.common.CommitComponents;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class StructuralRuleEnumTest {

  @Test
  void when_getTypeRule_should_invalid_for_null() {

    //Given
    var structuralValidationRule = StructuralRuleEnum.TYPE_RULE.getConstructor().get();
    Commit commit = Commit.builder().commitComponents(CommitComponents.builder().build()).build();

    //When
    var actual = structuralValidationRule.execute(commit);

    //Then
    assertTrue(actual.isInvalid());
  }

  @Test
  void when_getTypeRule_should_invalid_for_valid() {

    //Given
    var structuralValidationRule = StructuralRuleEnum.TYPE_RULE.getConstructor().get();
    Commit commit = Commit.builder().commitComponents(CommitComponents
        .builder()
        .type("this is not right")
        .build()).build();

    //When
    var actual = structuralValidationRule.execute(commit);

    //Then
    assertTrue(actual.isAppliedOrValid());
  }

  @Test
  void when_getBodyRule_should_notApplicable_for_null() {

    //Given
    var structuralValidationRule = StructuralRuleEnum.BODY_RULE.getConstructor().get();
    Commit commit = Commit.builder().commitComponents(CommitComponents
        .builder()
        .body(null)
        .build()).build();

    //When
    var actual = structuralValidationRule.execute(commit);

    //Then
    assertTrue(actual.isNotApplicable());
  }

  @Test
  void when_getBodyRule_should_valid() {

    //Given
    var structuralValidationRule = StructuralRuleEnum.BODY_RULE.getConstructor().get();
    Commit commit = Commit.builder().commitComponents(CommitComponents
        .builder()
        .body(List.of("Pig are going to fly one day"))
        .build()).build();

    //When
    var actual = structuralValidationRule.execute(commit);

    //Then
    assertTrue(actual.isAppliedOrValid());
  }


  @Test
  void when_getColonRule_should_invalid_for_null() {

    //Given
    var structuralValidationRule = StructuralRuleEnum.COLON_RULE.getConstructor().get();
    Commit commit = Commit.builder().commitComponents(CommitComponents.builder().build()).build();

    //When
    var actual = structuralValidationRule.execute(commit);

    //Then
    assertTrue(actual.isInvalid());
  }

  @Test
  void when_getColonRule_should_valid() {

    //Given
    var structuralValidationRule = StructuralRuleEnum.COLON_RULE.getConstructor().get();
    Commit commit = Commit.builder().commitComponents(CommitComponents
        .builder()
        .colon(true)
        .build()).build();

    //When
    var actual = structuralValidationRule.execute(commit);

    //Then
    assertTrue(actual.isAppliedOrValid());
  }


  @Test
  void when_getDescriptionRule_should_invalid_for_null() {

    //Given
    var structuralValidationRule = StructuralRuleEnum.DESCRIPTION_RULE.getConstructor().get();
    Commit commit = Commit.builder().commitComponents(CommitComponents.builder().build()).build();

    //When
    var actual = structuralValidationRule.execute(commit);

    //Then
    assertTrue(actual.isInvalid());
  }

  @Test
  void when_getDescriptionRule_should_valid() {

    //Given
    var structuralValidationRule = StructuralRuleEnum.DESCRIPTION_RULE.getConstructor().get();
    Commit commit = Commit.builder().commitComponents(CommitComponents
        .builder()
        .description("This project is going to explode and end the world. Wahahahaha!!!!")
        .build()).build();

    //When
    var actual = structuralValidationRule.execute(commit);

    //Then
    assertTrue(actual.isAppliedOrValid());
  }

  @Test
  void when_getOptionalExclamationRule_should_notApplicable_for_null() {

    //Given
    var structuralValidationRule = StructuralRuleEnum.OPTIONAL_EXCLAMATION_RULE.getConstructor().get();
    Commit commit = Commit.builder().commitComponents(CommitComponents.builder().build()).build();

    //When
    var actual = structuralValidationRule.execute(commit);

    //Then
    assertTrue(actual.isNotApplicable());
  }

  @Test
  void when_getOptionalExclamationRule_should_valid() {

    //Given
    var structuralValidationRule = StructuralRuleEnum.OPTIONAL_EXCLAMATION_RULE.getConstructor().get();
    Commit commit = Commit.builder().commitComponents(CommitComponents
        .builder()
        .exclamation(true)
        .build()).build();

    //When
    var actual = structuralValidationRule.execute(commit);

    //Then
    assertTrue(actual.isAppliedOrValid());
  }


  @Test
  void when_getExclamationRule_should_invalid_for_null() {

    //Given
    var structuralValidationRule = StructuralRuleEnum.EXCLAMATION_RULE.getConstructor().get();
    Commit commit = Commit.builder().commitComponents(CommitComponents.builder().build()).build();

    //When
    var actual = structuralValidationRule.execute(commit);

    //Then
    assertTrue(actual.isInvalid());
  }

  @Test
  void when_getExclamationRule_should_valid() {

    //Given
    var structuralValidationRule = StructuralRuleEnum.EXCLAMATION_RULE.getConstructor().get();
    Commit commit = Commit.builder().commitComponents(CommitComponents
        .builder()
        .exclamation(true)
        .build()).build();

    //When
    var actual = structuralValidationRule.execute(commit);

    //Then
    assertTrue(actual.isAppliedOrValid());
  }

  @Test
  void when_getOptionalScopeRule_should_invalid_for_null() {

    //Given
    var structuralValidationRule = StructuralRuleEnum.OPTIONAL_SCOPE_RULE.getConstructor().get();
    Commit commit = Commit.builder().commitComponents(CommitComponents.builder().build()).build();

    //When
    var actual = structuralValidationRule.execute(commit);

    //Then
    assertTrue(actual.isNotApplicable());
  }

  @Test
  void when_OptionalScopeRule_should_valid() {

    //Given
    var structuralValidationRule = StructuralRuleEnum.OPTIONAL_SCOPE_RULE.getConstructor().get();
    Commit commit = Commit.builder().commitComponents(CommitComponents
        .builder()
        .scope("Pizza")
        .build()).build();

    //When
    var actual = structuralValidationRule.execute(commit);

    //Then
    assertTrue(actual.isAppliedOrValid());
  }

  @Test
  void when_getScopeRule_should_invalid_for_badInput() {

    //Given
    var structuralValidationRule = StructuralRuleEnum.SCOPE_RULE.getConstructor().get();
    Commit commit = Commit.builder().commitComponents(CommitComponents.builder()
        .scope("()")
        .build()).build();

    //When
    var actual = structuralValidationRule.execute(commit);

    //Then
    assertTrue(actual.isInvalid());
  }

  @Test
  void when_getScopeRule_should_valid() {

    //Given
    var structuralValidationRule = StructuralRuleEnum.SCOPE_RULE.getConstructor().get();
    Commit commit = Commit.builder().commitComponents(CommitComponents
        .builder()
        .scope("(pizza)")
        .build()).build();

    //When
    var actual = structuralValidationRule.execute(commit);

    //Then
    assertTrue(actual.isAppliedOrValid());
  }
}
