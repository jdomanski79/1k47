package pl.jdomanski.k47.meter;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingRepository extends CrudRepository<Reading, Long>{

}
