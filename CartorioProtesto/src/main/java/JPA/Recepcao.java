package JPA;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_RECEPCAO")
public class Recepcao implements Serializable{
    @Id
    @Column(name = "ID_RECEPCAO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "NUM_NUMERO", nullable = false)
    private long numero;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_DATA", nullable = false)
    private Date data;
    
    //@OneToMany(mappedBy = "recepcao", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@JoinColumn(name = "ID_TITULO")
    private List<Titulo> titulos;
    
    //@ManyToMany
    //@JoinTable(name="TB_RECEPCOES_GUIAS", joinColumns=
    //{@JoinColumn(name="ID_RECEPCAO")}, inverseJoinColumns=
     // {@JoinColumn(name="ID_GUIA")})
    private List<Guia> guias;
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "ID_CREDOR", referencedColumnName = "ID_CREDOR")
    private Credor credor;
    
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "ID_DEVEDOR", referencedColumnName = "ID_DEVEDOR")
    private Devedor devedor;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }
    
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    public Credor getCredor() {
        return credor;
    }

    public void setCredor(Credor credor) {
        this.credor = credor;
    }
    
    public Devedor getDevedor() {
        return devedor;
    }

    public void setDevedor(Devedor devedor) {
        this.devedor = devedor;
    }
    
    public List<Titulo> getTitulo() {
        return titulos;
    }

    public void setTitulo(List<Titulo> titulos) {
        this.titulos = titulos;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Recepcao)) {
            return false;
        }
        Recepcao other = (Recepcao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Tag[ id=" + id + ":" + numero + " ]";
    }
}