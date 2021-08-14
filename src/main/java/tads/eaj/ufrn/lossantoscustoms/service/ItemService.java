package tads.eaj.ufrn.lossantoscustoms.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tads.eaj.ufrn.lossantoscustoms.model.Item;
import tads.eaj.ufrn.lossantoscustoms.repository.ItemRepository;

import java.util.Date;
import java.util.List;

@Service
public class ItemService {
    ItemRepository repository;
    @Autowired
    public void setRepository(ItemRepository repository){
        this.repository=repository;
    }
    public List<Item> findAll(){
       return repository.findAll();
    }
    public void save(Item a){
       repository.save(a);
    }
    public void delete(Long id){
       Item item = repository.getById(id);
       Date date = new Date();
       item.setDeleted(date);
       repository.save(item);
    }

    public Item findById(Long id){
        return repository.getById(id);
    }

}


