package io.github.javasemantic.commit.engine.factory.enums;

import io.github.javasemantic.commit.engine.framework.rule.ConventionalValidationRule;
import io.github.javasemantic.commit.engine.rules.commit.rules.convetional.OptionalConventionalRule;
import io.github.javasemantic.commit.engine.rules.commit.rules.convetional.TypeConventionalRule;
import java.util.function.Supplier;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ConventionalRuleEnum {

  TYPE_RULE(TypeConventionalRule::new),

  BREAKING_CHANGE_RULE(() -> new OptionalConventionalRule((commitComponent) ->
      commitComponent.isExclamation() || (commitComponent.getBody() != null
          && commitComponent.getBody().contains("BREAKING CHANGE"))
  ));

  @Getter
  private final Supplier<ConventionalValidationRule> constructor;

}
