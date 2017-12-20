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
@RequestMapping("paciente")
public class PacienteController {

	@PersistenceContext
	private EntityManager entityManager;
	
    @RequestMapping("/")
    public String index() {
    	StringBuffer buffer = new StringBuffer();
    	List<Paciente> res = entityManager.createQuery("from Paciente", Paciente.class).getResultList();
    	for(Paciente p : res) buffer.append(p);
        return buffer.toString();
    }
    
    @Transactional
    @PostMapping("/add")
	public ResponseEntity<Void> addPaciente(@RequestParam("nome") String nome, @RequestParam("cpf") String cpf, @RequestParam("data") String dataNascimento) {
    	entityManager.persist(new Paciente(nome, cpf, dataNascimento));	    
	    return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

    
    
    
    

}
