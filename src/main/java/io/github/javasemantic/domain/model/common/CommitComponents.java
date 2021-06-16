package io.github.javasemantic.domain.model.common;


import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommitComponents {

  private String type;
  private boolean exclamation;
  private String scope;
  private boolean colon;
  private boolean whitespace;
  private String description;
  private List<String> body;

}
