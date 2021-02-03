package ua.aser.capshop.repository;

//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.aser.capshop.domain.Customer;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{
/*    @Query(value = "SELECT c FROM Customer c WHERE c.name LIKE '%' || :keyword || '%'"
            + " OR c.email LIKE '%' || :keyword || '%'"
            + " OR c.address LIKE '%' || :keyword || '%'")
    public List<Customer> search(@Param("keyword") String keyword);*/

    List<Customer> findByName (String name);
}
