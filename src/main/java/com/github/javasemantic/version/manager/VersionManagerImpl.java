package com.github.javasemantic.version.manager;

import com.github.javasemantic.domain.DomainFactory;
import com.github.javasemantic.domain.model.Commit;
import com.github.javasemantic.domain.model.ProjectData;
import com.github.javasemantic.domain.model.common.Version;

public class VersionManagerImpl implements VersionManager {

  private final Version finalProjectVersion = DomainFactory.getVersion();

  @Override
  public Version calculateProjectVersion(ProjectData projectData) {
    for (var commit: projectData.getCommits()) {
      addVersionToProject(normaliseCommit(commit));
    }
    return finalProjectVersion;
  }

  private Version normaliseCommit(Commit commit){
    var commitVersion = commit.getDirtyVersion();
    commitVersion.setMajor(commitVersion.getMajor() >= 1 ? 1 : 0);
    commitVersion.setMinor(commitVersion.getMinor() >= 1
        && commitVersion.getMajor() <= 0 ? 1 : 0);
    commitVersion.setPatch(commitVersion.getPatch() >= 1
        && commitVersion.getMajor() <= 0
        && commitVersion.getMinor() <= 0 ? 1 : 0);
    return commitVersion;
  }

  private void addVersionToProject(Version commitVersion){
    finalProjectVersion.setMajor(finalProjectVersion.getMajor() + commitVersion.getMajor());
    finalProjectVersion.setMinor(finalProjectVersion.getMinor() + commitVersion.getMinor());
    finalProjectVersion.setPatch(finalProjectVersion.getPatch() + commitVersion.getPatch());
  }

}
