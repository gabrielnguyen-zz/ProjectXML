/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benguyen.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Gabriel Nguyen
 */
@Entity
@Table(name = "House")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "House.findAll", query = "SELECT h FROM House h")
    , @NamedQuery(name = "House.findById", query = "SELECT h FROM House h WHERE h.id = :id")
    , @NamedQuery(name = "House.findByName", query = "SELECT h FROM House h WHERE h.name = :name")
    , @NamedQuery(name = "House.findByPrice", query = "SELECT h FROM House h WHERE h.price = :price")
    , @NamedQuery(name = "House.findByTime", query = "SELECT h FROM House h WHERE h.time = :time")})
public class House implements Serializable {

    @Size(max = 50)
    @Column(name = "time")
    private String time;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "houseId")
    private Collection<BenefitsOfHouse> benefitsOfHouseCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 500)
    @Column(name = "name")
    private String name;
    @Size(max = 50)
    @Column(name = "price")
    private String price;
    @ManyToMany(mappedBy = "houseCollection")
    private Collection<Benefits> benefitsCollection;
    @OneToMany(mappedBy = "houseId")
    private Collection<HouseImage> houseImageCollection;
    @JoinColumn(name = "districtID", referencedColumnName = "id")
    @ManyToOne
    private District districtID;
    @JoinColumn(name = "spaceID", referencedColumnName = "id")
    @ManyToOne
    private Space spaceID;

    public House() {
    }

    public House(Integer id) {
        this.id = id;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    @XmlTransient
    public Collection<Benefits> getBenefitsCollection() {
        return benefitsCollection;
    }

    public void setBenefitsCollection(Collection<Benefits> benefitsCollection) {
        this.benefitsCollection = benefitsCollection;
    }

    @XmlTransient
    public Collection<HouseImage> getHouseImageCollection() {
        return houseImageCollection;
    }

    public void setHouseImageCollection(Collection<HouseImage> houseImageCollection) {
        this.houseImageCollection = houseImageCollection;
    }

    public District getDistrictID() {
        return districtID;
    }

    public void setDistrictID(District districtID) {
        this.districtID = districtID;
    }

    public Space getSpaceID() {
        return spaceID;
    }

    public void setSpaceID(Space spaceID) {
        this.spaceID = spaceID;
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
        if (!(object instanceof House)) {
            return false;
        }
        House other = (House) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "benguyen.models.House[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<BenefitsOfHouse> getBenefitsOfHouseCollection() {
        return benefitsOfHouseCollection;
    }

    public void setBenefitsOfHouseCollection(Collection<BenefitsOfHouse> benefitsOfHouseCollection) {
        this.benefitsOfHouseCollection = benefitsOfHouseCollection;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
}
