package io.github.javasemantic.git.commit.retrieval;

import io.github.javasemantic.domain.model.DirtyCommit;
import java.util.List;

public interface CommitRetrieval {

  List<DirtyCommit> getCommits();

}
