package eteg.etegdemo;

import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.MediaType;


@RestController
@RequestMapping("receita")
public class ReceitaController {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	
    @RequestMapping("/")
    public String index() {
    	StringBuffer buffer = new StringBuffer();
    	List<Receita> res = entityManager.createQuery("from Receita", Receita.class).getResultList();
    	for(Receita r : res) buffer.append(r);
        return buffer.toString();
    }
    
    @Transactional
    @PostMapping("/add")
	public ResponseEntity<Void> addReceita(@RequestParam("medico") String medico, @RequestParam("cpf") String cpf, @RequestParam("medicamento") String[] medicamentos) {
    	Paciente paciente = pacienteRepository.findByCpf(cpf);
    	if(paciente == null) {
    		System.out.print("Paciente com CPF "+cpf+" não encontrado");
    		return new ResponseEntity<Void>(HttpStatus.CREATED);
    	}
    	LinkedHashSet<Medicamento> set_medicamentos = new LinkedHashSet<Medicamento>();
    	for(String med : medicamentos) set_medicamentos.add(medicamentoRepository.findByNome(med));
    	entityManager.persist(new Receita(medico, paciente, set_medicamentos));	    
	    return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

    
    
    
    

}
