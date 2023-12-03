package vn.hust.aims.entity;

import javax.persistence.*;
import java.time.Instant;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "example")
@SuperBuilder
public class Example {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

//  Example to join table Example and Example1
//  @ManyToOne
//  @JoinColumn(name = "example1_id")
//  private Example1 example1;

  @Column(name = "description")
  private String description;

  // For time, using Instant
  @Column(name = "created_at")
  private Instant createdAt;

  @Column(name = "created_by")
  private String createdBy;
}
