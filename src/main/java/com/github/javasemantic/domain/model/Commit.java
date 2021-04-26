package com.github.javasemantic.domain.model;

import com.github.javasemantic.domain.model.common.CommitComponents;
import com.github.javasemantic.domain.model.common.Version;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Commit {

  private final Version dirtyVersion;
  private final String rawCommit;
  private final CommitComponents commitComponents;
}
