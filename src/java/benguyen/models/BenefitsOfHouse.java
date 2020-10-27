/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benguyen.models;

import benguyen.models.Benefits;
import benguyen.models.House;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gabriel Nguyen
 */
@Entity
@Table(name = "BenefitsOfHouse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BenefitsOfHouse.findAll", query = "SELECT b FROM BenefitsOfHouse b")
    , @NamedQuery(name = "BenefitsOfHouse.findById", query = "SELECT b FROM BenefitsOfHouse b WHERE b.id = :id")})
public class BenefitsOfHouse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JoinColumn(name = "benefit_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Benefits benefitId;
    @JoinColumn(name = "house_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private House houseId;

    public BenefitsOfHouse() {
    }

    public BenefitsOfHouse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Benefits getBenefitId() {
        return benefitId;
    }

    public void setBenefitId(Benefits benefitId) {
        this.benefitId = benefitId;
    }

    public House getHouseId() {
        return houseId;
    }

    public void setHouseId(House houseId) {
        this.houseId = houseId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BenefitsOfHouse)) {
            return false;
        }
        BenefitsOfHouse other = (BenefitsOfHouse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "benguyen.clients.BenefitsOfHouse[ id=" + id + " ]";
    }
    
}
