/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benguyen.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "Space")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Space.findAll", query = "SELECT s FROM Space s")
    , @NamedQuery(name = "Space.findById", query = "SELECT s FROM Space s WHERE s.id = :id")
    , @NamedQuery(name = "Space.findBySpace", query = "SELECT s FROM Space s WHERE s.space = :space")})
public class Space implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 50)
    @Column(name = "space")
    private String space;
    @OneToMany(mappedBy = "spaceID")
    private Collection<House> houseCollection;

    public Space() {
    }

    public Space(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
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
        if (!(object instanceof Space)) {
            return false;
        }
        Space other = (Space) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "benguyen.models.Space[ id=" + id + " ]";
    }
    
}
