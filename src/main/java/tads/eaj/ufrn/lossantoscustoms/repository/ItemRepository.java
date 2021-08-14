package tads.eaj.ufrn.lossantoscustoms.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import tads.eaj.ufrn.lossantoscustoms.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}