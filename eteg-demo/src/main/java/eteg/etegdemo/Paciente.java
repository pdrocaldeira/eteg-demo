package eteg.etegdemo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Paciente{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String nome;
    
    @Column(unique=true)
    private String cpf;
    
    LocalDate dataNascimento;

    protected Paciente() {}

    public Paciente(String nome, String CPF, String dataNascimento) {
        this.nome = nome;
        this.cpf = CPF;
        this.dataNascimento = LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH));
    }

    @Override
    public String toString() {
        return String.format(
                "Paciente[id=%d, nome='%s', cpf='%s', dataNascimento='%s']",
		id,nome, cpf, dataNascimento.toString());
    }
    
    public String getNome() {
    	return this.nome;
    }

}
