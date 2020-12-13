package springframework.bluemoonrestspring.repository.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.Repository;
import springframework.bluemoonrestspring.model.Drop;
import springframework.bluemoonrestspring.repository.DropRepository;
import springframework.bluemoonrestspring.repository.springdatajpa.DropRepositoryOverride;

@Profile("spring-data-jpa")
public interface SpringDataDropRepository extends DropRepository, Repository<Drop, Integer>, DropRepositoryOverride {


}
