
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

    private final static QName _Benefits_QNAME = new QName("", "benefits");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: benguyen.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Benefits }
     * 
     */
    public Benefits createBenefits() {
        return new Benefits();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Benefits }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "benefits")
    public JAXBElement<Benefits> createBenefits(Benefits value) {
        return new JAXBElement<Benefits>(_Benefits_QNAME, Benefits.class, null, value);
    }

}
