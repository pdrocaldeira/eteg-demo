package eteg.etegdemo;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.MediaType;


@RestController
@RequestMapping("dispensa")
public class DispensaController {

	@PersistenceContext
	private EntityManager entityManager;
	
    @RequestMapping("/")
    public String index() {
    	StringBuffer buffer = new StringBuffer();
    	List<Dispensa> res = entityManager.createQuery("from Dispensa", Dispensa.class).getResultList();
    	for(Dispensa d : res) buffer.append(d);
        return buffer.toString();
    }
    
    @Transactional
    @PostMapping("/add")
	public ResponseEntity<Void> addDispensa(@RequestParam("id") String id) {    	
    	entityManager.persist(new Dispensa(entityManager.find(Receita.class, Long.valueOf(id))));	    
	    return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

    
    
    
    

}
