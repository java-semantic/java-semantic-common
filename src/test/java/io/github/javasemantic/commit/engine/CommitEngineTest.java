package io.github.javasemantic.commit.engine;

import org.junit.jupiter.api.Test;
import io.github.javasemantic.commit.engine.framework.rule.CommitPartRule;
import io.github.javasemantic.commit.engine.rules.common.TypeEnum;
import io.github.javasemantic.domain.model.Commit;
import io.github.javasemantic.domain.model.common.CommitComponents;
import io.github.javasemantic.domain.model.common.Version;

public class CommitEngineTest {

  private CommitEngine<Commit> commitEngine = CommitEngineFactory.get();


  @Test
  void when_version_should_not_change(){

    Commit commit =  Commit.builder()
        .commitComponents(CommitComponents.builder()
            .description("BOOOO")
            .whitespace(true)
            .colon(true)
            .scope("Cake")
            .exclamation(false)
            .type("depr")
            .build())
        .dirtyVersion(new Version())
        .build();
    var result = commitEngine.execute(commit);


    commit.getDirtyVersion();
    result.isValid();

  }





}
