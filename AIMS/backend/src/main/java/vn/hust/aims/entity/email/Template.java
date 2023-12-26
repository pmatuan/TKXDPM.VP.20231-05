package vn.hust.aims.entity.email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.hust.aims.entity.TimedEntity;

@Entity
@Table(name = "template")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Template extends TimedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title", columnDefinition = "text", nullable = false)
  private String title;

  @Column(name = "content", columnDefinition = "text", nullable = false)
  private String content;

  @Column(name = "required_parameters", columnDefinition = "json", nullable = false)
  private String requiredParameters;

}

