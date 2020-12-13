package springframework.bluemoonrestspring.repository.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import springframework.bluemoonrestspring.model.Customer;
import springframework.bluemoonrestspring.repository.CustomerRepository;

import java.util.Collection;

@Profile("spring-data-jpa")
public interface SpringDataCustomerRepository extends CustomerRepository,Repository<Customer, Integer>

    {

        @Override
        @Query("SELECT DISTINCT customer FROM Customer customer left join fetch customer.appliances WHERE customer.lastName LIKE :lastName%")
        Collection<Customer> findByLastName(@Param("lastName") String lastName);

        @Override
        @Query("SELECT customer FROM Customer customer left join fetch customer.appliances WHERE customer.id =:id")
        Customer findById(@Param("id") int id);
    }