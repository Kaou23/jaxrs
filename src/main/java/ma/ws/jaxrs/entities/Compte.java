package ma.ws.jaxrs.entities;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Date;

@Entity
@XmlRootElement
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double solde;

    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @Enumerated(EnumType.ORDINAL) // (TP) stocke 0/1 ; en réel on préfère STRING
    private TypeCompte type;

    // ----- Constructeurs -----
    public Compte() { } // requis par JPA

    // Pratique pour l'initialisation (sans id)
    public Compte(double solde, Date dateCreation, TypeCompte type) {
        this.solde = solde;
        this.dateCreation = dateCreation;
        this.type = type;
    }

    // Optionnel : si tu veux appeler new Compte(null, …)
    public Compte(Long id, double solde, Date dateCreation, TypeCompte type) {
        this.id = id;
        this.solde = solde;
        this.dateCreation = dateCreation;
        this.type = type;
    }

    // ----- Getters / Setters -----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public double getSolde() { return solde; }
    public void setSolde(double solde) { this.solde = solde; }

    public Date getDateCreation() { return dateCreation; }
    public void setDateCreation(Date dateCreation) { this.dateCreation = dateCreation; }

    public TypeCompte getType() { return type; }
    public void setType(TypeCompte type) { this.type = type; }

    @PrePersist
    void prePersist() {
        if (dateCreation == null) dateCreation = new Date();
    }

    @Override
    public String toString() {
        return "Compte{id=" + id + ", solde=" + solde + ", dateCreation=" + dateCreation + ", type=" + type + "}";
    }
}
