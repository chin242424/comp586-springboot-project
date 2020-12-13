package springframework.bluemoonrestspring.repository.springdatajpa;

import org.springframework.context.annotation.Profile;
import springframework.bluemoonrestspring.model.Drop;

@Profile("spring-data-jpa")
public interface DropRepositoryOverride {

    void delete(Drop drop);
}
