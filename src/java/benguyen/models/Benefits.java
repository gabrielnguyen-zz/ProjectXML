/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benguyen.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gabriel Nguyen
 */
@Entity
@Table(name = "Benefits")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Benefits.findAll", query = "SELECT b FROM Benefits b")
    , @NamedQuery(name = "Benefits.findById", query = "SELECT b FROM Benefits b WHERE b.id = :id")
    , @NamedQuery(name = "Benefits.findByName", query = "SELECT b FROM Benefits b WHERE b.name = :name")})
public class Benefits implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "benefitId")
    private Collection<BenefitsOfHouse> benefitsOfHouseCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @JoinTable(name = "BenefitsOfHouse", joinColumns = {
        @JoinColumn(name = "benefit_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "house_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<House> houseCollection;

    public Benefits() {
    }

    public Benefits(Integer id) {
        this.id = id;
    }

    public Benefits(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<House> getHouseCollection() {
        return houseCollection;
    }

    public void setHouseCollection(Collection<House> houseCollection) {
        this.houseCollection = houseCollection;
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
        if (!(object instanceof Benefits)) {
            return false;
        }
        Benefits other = (Benefits) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "benguyen.models.Benefits[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<BenefitsOfHouse> getBenefitsOfHouseCollection() {
        return benefitsOfHouseCollection;
    }

    public void setBenefitsOfHouseCollection(Collection<BenefitsOfHouse> benefitsOfHouseCollection) {
        this.benefitsOfHouseCollection = benefitsOfHouseCollection;
    }
    
}
