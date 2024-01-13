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
@Table(name = "sender")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Sender extends TimedEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "provider", nullable = false, length = 100)
  private String provider;

  @Column(name = "config", columnDefinition = "json", nullable = false)
  private String config;
}

