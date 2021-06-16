package io.github.javasemantic.degenerator;

import org.junit.jupiter.api.Test;
import java.util.List;
import io.github.javasemantic.domain.model.DirtyCommit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DegeneratorTest {

  private Degenerator degenerator;

  @Test
  public void when_validTypeExclamationNoScopeColonWhitespaceDescription() {
    this.degenerator = DegeneratorFactory.get();

    var degeneratedCommit = this.degenerator
        .degenerate(DirtyCommit
            .builder()
            .message("chore!: some chicken actually did something")
            .build());

    assertEquals("chore", degeneratedCommit.getType());
    assertTrue( degeneratedCommit.isExclamation());
    assertEquals("", degeneratedCommit.getScope());
    assertTrue(degeneratedCommit.isColon());
    assertTrue(degeneratedCommit.isWhitespace());
    assertEquals("some chicken actually did something", degeneratedCommit.getDescription());
    assertEquals(0, degeneratedCommit.getBody().size());
  }

  @Test
  public void when_validTypeNoExclamationNoScopeColonWhitespaceDescription() {
    this.degenerator = DegeneratorFactory.get();

    var degeneratedCommit = this.degenerator
        .degenerate(DirtyCommit
            .builder()
            .message("chore: some chicken actually did something")
            .build());

    assertEquals("chore", degeneratedCommit.getType());
    assertFalse( degeneratedCommit.isExclamation());
    assertEquals("", degeneratedCommit.getScope());
    assertTrue(degeneratedCommit.isColon());
    assertTrue(degeneratedCommit.isWhitespace());
    assertEquals("some chicken actually did something", degeneratedCommit.getDescription());
    assertEquals(0, degeneratedCommit.getBody().size());
  }

  @Test
  public void when_validTypeNoExclamationScopeColonWhitespaceDescription() {
    this.degenerator = DegeneratorFactory.get();

    var degeneratedCommit = this.degenerator
        .degenerate(DirtyCommit
            .builder()
            .message("chore(something): some chicken actually did something")
            .build());

    assertEquals("chore", degeneratedCommit.getType());
    assertFalse( degeneratedCommit.isExclamation());
    assertEquals("something", degeneratedCommit.getScope());
    assertTrue(degeneratedCommit.isColon());
    assertTrue(degeneratedCommit.isWhitespace());
    assertEquals("some chicken actually did something", degeneratedCommit.getDescription());
    assertEquals(0, degeneratedCommit.getBody().size());
  }

  @Test
  public void when_validTypeExclamationScopeColonWhitespaceDescription() {
    this.degenerator = DegeneratorFactory.get();

    var degeneratedCommit = this.degenerator
        .degenerate(DirtyCommit
            .builder()
            .message("chore!(something): some chicken actually did something")
            .build());

    assertEquals("chore", degeneratedCommit.getType());
    assertTrue( degeneratedCommit.isExclamation());
    assertEquals("something", degeneratedCommit.getScope());
    assertTrue(degeneratedCommit.isColon());
    assertTrue(degeneratedCommit.isWhitespace());
    assertEquals("some chicken actually did something", degeneratedCommit.getDescription());
    assertEquals(0, degeneratedCommit.getBody().size());
  }

  @Test
  public void when_validTypeNoExclamationNoScopeNoColonWhitespaceDescription() {
    this.degenerator = DegeneratorFactory.get();

    var degeneratedCommit = this.degenerator
        .degenerate(DirtyCommit
            .builder()
            .message("chore!(something) some chicken actually did something")
            .build());

    assertEquals("", degeneratedCommit.getType());
    assertFalse( degeneratedCommit.isExclamation());
    assertEquals("", degeneratedCommit.getScope());
    assertFalse(degeneratedCommit.isColon());
    assertFalse(degeneratedCommit.isWhitespace());
    assertEquals("", degeneratedCommit.getDescription());
    assertEquals(0, degeneratedCommit.getBody().size());
  }

  @Test
  public void when_validTypeNoExclamationNoScopeBadColonWhitespaceDescription() {
    this.degenerator = DegeneratorFactory.get();

    var degeneratedCommit = this.degenerator
        .degenerate(DirtyCommit
            .builder()
            .message("chore!(something) some chicken: actually did something")
            .build());

    assertEquals("", degeneratedCommit.getType());
    assertFalse( degeneratedCommit.isExclamation());
    assertEquals("", degeneratedCommit.getScope());
    assertFalse(degeneratedCommit.isColon());
    assertFalse(degeneratedCommit.isWhitespace());
    assertEquals("", degeneratedCommit.getDescription());
    assertEquals(0, degeneratedCommit.getBody().size());
  }

  @Test
  public void when_validTypeNoExclamationNoScopeBadColonWhitespaceDescriptionREEEE() {
    this.degenerator = DegeneratorFactory.get();

    var degeneratedCommit = this.degenerator
        .degenerate(DirtyCommit
            .builder()
            .message("chL::DKE~~!!!8gt5euirdlxb564jtguicmasepoeslduf4vlg5itwbduhg7fl,(((((((o:! re!(something) some lgijgbhjfbkhdvskhuvds jdn0))@::::::: actually did something")
            .build());

    assertEquals("", degeneratedCommit.getType());
    assertFalse( degeneratedCommit.isExclamation());
    assertEquals("", degeneratedCommit.getScope());
    assertFalse(degeneratedCommit.isColon());
    assertFalse(degeneratedCommit.isWhitespace());
    assertEquals("", degeneratedCommit.getDescription());
    assertEquals(0, degeneratedCommit.getBody().size());
  }
}
