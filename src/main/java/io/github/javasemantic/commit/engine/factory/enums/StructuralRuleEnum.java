package io.github.javasemantic.commit.engine.factory.enums;

import io.github.javasemantic.commit.engine.framework.rule.StructuralValidationRule;
import io.github.javasemantic.commit.engine.rules.commit.rules.structure.OptionalStructuralRule;
import io.github.javasemantic.commit.engine.rules.commit.rules.structure.StructuralRule;
import io.github.javasemantic.domain.model.common.CommitComponents;
import java.util.Objects;
import java.util.function.Supplier;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StructuralRuleEnum {

  TYPE_RULE(
      () -> new StructuralRule((commitComponent) -> Objects.nonNull(commitComponent) && Objects.nonNull(commitComponent.getType()))),

  BODY_RULE(
      () -> new OptionalStructuralRule(
          (commitComponent) ->  Objects.nonNull(commitComponent) && Objects.nonNull(commitComponent.getBody()))),
  COLON_RULE(
      () -> new StructuralRule(CommitComponents::isColon)),
  DESCRIPTION_RULE(() -> new StructuralRule(
      (commitComponent) ->  Objects.nonNull(commitComponent) && Objects.nonNull(commitComponent.getDescription()))),
  OPTIONAL_SCOPE_RULE(() -> new OptionalStructuralRule(
      (commitComponent) ->  Objects.nonNull(commitComponent) && Objects.nonNull(commitComponent.getScope()))),
  SCOPE_RULE(() -> new StructuralRule(
      (commitComponent) -> commitComponent.getScope().matches("\\(.+\\)"))),
  OPTIONAL_EXCLAMATION_RULE(() -> new OptionalStructuralRule(
      CommitComponents::isExclamation)),
  EXCLAMATION_RULE(() -> new StructuralRule(CommitComponents::isExclamation));

  private final Supplier<StructuralValidationRule> constructor;
}
