package io.github.javasemantic.git.repository.retrieval;

import java.io.File;
import java.util.function.Supplier;
import io.github.javasemantic.git.commit.retrieval.CommitRetrieval;
import io.github.javasemantic.git.commit.retrieval.JgitCommitRetrievalImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RepositoryRetrievalFactory {
  private static final Supplier<RepositoryRetrieval> constructor = RepositoryRetrievalImpl::new;

  public static RepositoryRetrieval get() {
    return constructor.get();
  }
}
