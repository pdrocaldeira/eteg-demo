package eteg.etegdemo;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Dispensa{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @OneToOne(cascade = CascadeType.ALL)
    private Receita receita;
    
    
    private final LocalDate data;

    protected Dispensa() {
    	data = LocalDate.now();
    }

    public Dispensa(Receita receita) {
        this.receita = receita;
        this.data = LocalDate.now();
    }

    @Override
    public String toString() {
        return String.format(
                "Dispensa[id=%d, receita='%s', data='%s']",
		id, this.receita.toString(), this.data.toString());
    }

}
