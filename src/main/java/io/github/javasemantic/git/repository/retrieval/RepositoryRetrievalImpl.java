package io.github.javasemantic.git.repository.retrieval;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import java.io.File;
import java.io.IOException;

public class RepositoryRetrievalImpl implements RepositoryRetrieval{

  @Override
  public final Repository gitRepository(File file) {
    Repository gitRepository;
    try {
      FileRepositoryBuilder repositoryBuilder =
          new FileRepositoryBuilder().findGitDir(file);
      String gitIndexFileEnvVariable = System.getenv("GIT_INDEX_FILE");
      if (StringUtils.isNotBlank(gitIndexFileEnvVariable)) {
        repositoryBuilder = repositoryBuilder.setIndexFile(new File(gitIndexFileEnvVariable));
      }
      gitRepository = repositoryBuilder.build();
    } catch (IOException e) {
      throw new RuntimeException(
          "Could not find the git repository. Run 'git init' if you did not.", e);
    }
    return gitRepository;
  }

}
