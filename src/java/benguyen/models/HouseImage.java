/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benguyen.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Gabriel Nguyen
 */
@Entity
@Table(name = "HouseImage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HouseImage.findAll", query = "SELECT h FROM HouseImage h")
    , @NamedQuery(name = "HouseImage.findById", query = "SELECT h FROM HouseImage h WHERE h.id = :id")
    , @NamedQuery(name = "HouseImage.findByHouseImage", query = "SELECT h FROM HouseImage h WHERE h.houseImage = :houseImage")})
public class HouseImage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 1073741823)
    @Column(name = "house_image")
    private String houseImage;
    @JoinColumn(name = "house_id", referencedColumnName = "id")
    @ManyToOne
    private House houseId;

    public HouseImage() {
    }

    public HouseImage(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHouseImage() {
        return houseImage;
    }

    public void setHouseImage(String houseImage) {
        this.houseImage = houseImage;
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
        if (!(object instanceof HouseImage)) {
            return false;
        }
        HouseImage other = (HouseImage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "benguyen.models.HouseImage[ id=" + id + " ]";
    }
    
}
