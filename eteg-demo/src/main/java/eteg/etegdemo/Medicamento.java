package eteg.etegdemo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Medicamento{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String nome;       

    protected Medicamento() {}

    public Medicamento(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return String.format(
                "Medicamento[id=%d, nome='%s']",
		id,nome);
    }

}
