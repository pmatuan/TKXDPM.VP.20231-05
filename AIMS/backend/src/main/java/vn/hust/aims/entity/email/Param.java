package vn.hust.aims.entity.email;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Param {

  @NotNull
  @NotBlank
  private String key;
  @NotNull
  @NotBlank
  private String value;
}
