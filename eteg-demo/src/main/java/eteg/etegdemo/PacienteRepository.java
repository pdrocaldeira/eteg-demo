package eteg.etegdemo;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
 
interface PacienteRepository extends CrudRepository<Paciente, Long> {
	
	Paciente findByCpf(String cpf);
 
}
