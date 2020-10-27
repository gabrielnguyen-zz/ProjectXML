package benguyen.models;

import benguyen.models.Benefits;
import benguyen.models.District;
import benguyen.models.HouseImage;
import benguyen.models.Space;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-26T16:52:28")
@StaticMetamodel(House.class)
public class House_ { 

    public static volatile CollectionAttribute<House, Benefits> benefitsCollection;
    public static volatile CollectionAttribute<House, HouseImage> houseImageCollection;
    public static volatile SingularAttribute<House, Space> spaceID;
    public static volatile SingularAttribute<House, District> districtID;
    public static volatile SingularAttribute<House, String> price;
    public static volatile SingularAttribute<House, String> name;
    public static volatile SingularAttribute<House, Integer> id;
    public static volatile SingularAttribute<House, Date> time;

}