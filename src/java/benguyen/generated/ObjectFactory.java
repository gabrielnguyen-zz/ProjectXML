
package benguyen.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the benguyen.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Spaces_QNAME = new QName("", "spaces");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: benguyen.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Spaces }
     * 
     */
    public Spaces createSpaces() {
        return new Spaces();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Spaces }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "spaces")
    public JAXBElement<Spaces> createSpaces(Spaces value) {
        return new JAXBElement<Spaces>(_Spaces_QNAME, Spaces.class, null, value);
    }

}
