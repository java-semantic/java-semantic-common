package io.github.javasemantic.git.repository.retrieval;

import org.eclipse.jgit.lib.Repository;
import java.io.File;

public interface RepositoryRetrieval {

  Repository gitRepository(File file) ;

}
