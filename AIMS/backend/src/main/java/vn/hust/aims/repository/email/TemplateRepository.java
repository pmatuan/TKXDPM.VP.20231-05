package vn.hust.aims.repository.email;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hust.aims.entity.email.Template;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {
  Template findByTitle(String title);
}
