package io.github.javasemantic;

import io.github.javasemantic.commit.engine.CommitEngine;
import io.github.javasemantic.git.commit.retrieval.CommitRetrieval;
import io.github.javasemantic.degenerator.Degenerator;
import io.github.javasemantic.domain.DomainFactory;
import io.github.javasemantic.domain.model.Commit;
import io.github.javasemantic.domain.model.DirtyCommit;
import io.github.javasemantic.domain.model.common.Version;
import io.github.javasemantic.version.manager.VersionManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JavaSemanticServiceImpl implements JavaSemanticService {

  private final Degenerator degenerator;
  private final CommitEngine<Commit> commitEngine;
  private final VersionManager versionManager;
  private final CommitRetrieval commitRetrieval;

  public Version execute() {
    var projectData = DomainFactory.getProjectData();

    for (var dirtyCommit : commitRetrieval.getCommits()) {
      var commit = createCommit(dirtyCommit);
      var result = commitEngine.execute(commit);

      if (result.isValid()) {
        projectData.getCommits().add(commit);
      }
    }

    return versionManager.calculateProjectVersion(projectData);
  }

  private Commit createCommit(DirtyCommit dirtyCommit) {
    var components = degenerator.degenerate(dirtyCommit);

    return Commit
        .builder()
        .commitComponents(components)
        .dirtyVersion(DomainFactory.getVersion())
        .dirtyCommit(dirtyCommit)
        .build();
  }

}
