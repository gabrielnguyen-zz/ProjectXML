package benguyen.models;

import benguyen.models.House;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-10-26T16:52:28")
@StaticMetamodel(Space.class)
public class Space_ { 

    public static volatile CollectionAttribute<Space, House> houseCollection;
    public static volatile SingularAttribute<Space, Integer> id;
    public static volatile SingularAttribute<Space, String> space;

}