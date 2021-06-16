package io.github.javasemantic.commit.retrieval;

import org.apache.log4j.BasicConfigurator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommitRetrievalTest {

  private CommitRetrieval commitRetrieval = null;


  @BeforeEach
  void init() {

    BasicConfigurator.configure();
    commitRetrieval = CommitRetrievalFactory.get();

  }

  @Test
  void getListOfCommits() {

    var listOfCommits = commitRetrieval.getCommits();
    listOfCommits.stream()
        .forEach((commit) -> System.out.println(commit.getMessage() + " " + commit.getFooters()));

  }

}
