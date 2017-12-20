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
@RequestMapping("medicamento")
public class MedicamentoController {

	@PersistenceContext
	private EntityManager entityManager;
	
    @RequestMapping("/")
    public String index() {
    	StringBuffer buffer = new StringBuffer();
    	List<Medicamento> res = entityManager.createQuery("from Medicamento", Medicamento.class).getResultList();
    	for(Medicamento m : res) buffer.append(m);
        return buffer.toString();
    }
    
    @Transactional
    @PostMapping("/add")
	public ResponseEntity<Void> addMedicamento(@RequestParam("nome") String nomeMedicamento) {
    	entityManager.persist(new Medicamento(nomeMedicamento));	    
	    return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

    
    
    
    

}
