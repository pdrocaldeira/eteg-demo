package eteg.etegdemo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Receita{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String nomeMedico;
    private final LocalDate data;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private Set<Medicamento> medicamentos;

    protected Receita() {
    	this.data = LocalDate.now();
    }

    public Receita(String nomeMedico, Paciente paciente, Set<Medicamento> medicamentos) {
        this.nomeMedico = nomeMedico;
        this.paciente = paciente;
        this.data = LocalDate.now();
        this.medicamentos = medicamentos;
    }

    @Override
    public String toString() {
    	StringBuffer retorno = new StringBuffer(String.format(
                "Receita[id=%d, medico='%s', paciente='%s', data='%s', medicamentos='%s']",
		id,nomeMedico, paciente.getNome(), data.toString(),  medicamentos.toString()));
    	
        return retorno.toString();
    }
    
    public Set<Medicamento> getMedicamentos(){
    	return this.medicamentos;
    }

    
}
