package io.github.javasemantic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import io.github.javasemantic.commit.engine.CommitEngine;
import io.github.javasemantic.commit.engine.CommitEngineFactory;
import io.github.javasemantic.commit.retrieval.CommitRetrieval;
import io.github.javasemantic.commit.retrieval.CommitRetrievalFactory;
import io.github.javasemantic.degenerator.Degenerator;
import io.github.javasemantic.degenerator.DegeneratorFactory;
import io.github.javasemantic.domain.model.Commit;
import io.github.javasemantic.version.manager.VersionManager;
import io.github.javasemantic.version.manager.VersionManagerFactory;

@ExtendWith(MockitoExtension.class)
public class JavaSemanticServiceTest {

  private final Degenerator degenerator = DegeneratorFactory.get();
  private final CommitEngine<Commit> commitEngine = CommitEngineFactory.get();
  private final VersionManager versionManager = VersionManagerFactory.get();
  private JavaSemanticService javaSemanticService;
  private CommitRetrieval commitRetrieval;

  @BeforeEach
  public void setup() {
    commitRetrieval = CommitRetrievalFactory.get();//mock(CommitRetrieval.class);

    javaSemanticService = new JavaSemanticServiceImpl(
        degenerator, commitEngine, versionManager, commitRetrieval
    );
  }

  @Test
  void versionSpitOut() {
    var version = javaSemanticService.execute();

    commitRetrieval.getCommits().forEach(x -> System.out.println(x.getMessage() + " " + x.getFooters()));

    System.out.println(version);
  }

//  public void when_executeWithNoData_should_executeWithoutExceptions() {
//    when(commitRetrieval.getCommits())
//        .thenReturn(Collections.emptyList());
//
//    var version = javaSemanticService.execute();
//
//    assertEquals("0.0.0", version.toString());
//  }
//
//  public void when_executeWithData_should_executeWithoutExceptions() {
//    when(commitRetrieval.getCommits())
//        .thenReturn(List.of(DirtyCommit
//            .builder()
//            .message("feat: this should be picked up when code is implemented")
//            .build()));
//
//    var version = javaSemanticService.execute();
//
//    assertEquals("0.0.0", version.toString());
//  }

}
