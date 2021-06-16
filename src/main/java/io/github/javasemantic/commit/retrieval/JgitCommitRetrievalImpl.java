package io.github.javasemantic.commit.retrieval;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.FooterLine;
import org.eclipse.jgit.revwalk.RevCommit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import io.github.javasemantic.domain.model.DirtyCommit;
import io.github.javasemantic.logging.Log;

public class JgitCommitRetrievalImpl implements CommitRetrieval {

  public static final String GIT = ".git";

  private Git git;
  private Repository repository;

  @Override
  public List<DirtyCommit> getCommits() {
    init();
    return createCommits();
  }

  private List<DirtyCommit> createCommits() {
    List<DirtyCommit> commits = new ArrayList<>();
    for (var commit : getRevCommits()) {
      Log.info(JgitCommitRetrievalImpl.class, commit.toString());
      commits.add(DirtyCommit
          .builder()
          .message(commit.getShortMessage())
          .footers(getFooters(commit.getFooterLines()))
          .build()
      );
    }
    Collections.reverse(commits);
    return commits;
  }

  private List<String> getFooters(List<FooterLine> footerLines) {
    return footerLines
        .stream()
        .map(FooterLine::toString)
        .collect(Collectors.toList());
  }

  private void init() {
    repository = createRepository();
    git = new Git(createRepository());
  }

  private Repository createRepository() {
    try {
      return new FileRepository(GIT);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  private Iterable<RevCommit> getRevCommits() {
    try {
      return git.log().add(repository.resolve(repository.getFullBranch())).call();
    } catch (IOException | GitAPIException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
