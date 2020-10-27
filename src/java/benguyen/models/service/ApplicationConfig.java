/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benguyen.models.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Gabriel Nguyen
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(benguyen.models.service.BenefitsFacadeREST.class);
        resources.add(benguyen.models.service.BenefitsOfHouseFacadeREST.class);
        resources.add(benguyen.models.service.DistrictFacadeREST.class);
        resources.add(benguyen.models.service.HouseFacadeREST.class);
        resources.add(benguyen.models.service.HouseImageFacadeREST.class);
        resources.add(benguyen.models.service.SpaceFacadeREST.class);
    }
    
}
