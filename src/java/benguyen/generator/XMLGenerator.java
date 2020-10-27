/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benguyen.generator;

import benguyen.constants.PathConstant;
import benguyen.resolver.XMLHandler;

/**
 *
 * @author Gabriel Nguyen
 */
public class XMLGenerator {
    public static void main(String[] args) {
        System.out.println("----------------------------");
        System.out.println("GENERATE-PROCESS STARTS!");
        System.out.println("----------------------------");
        for (String schema : PathConstant.CONFIG_SCHEMAS_FOR_GENERATE) {
            System.out.println("Generating file " + schema + "...");
            try {
                XMLHandler.generateClassFromSchema(schema, "");
                System.out.println("Object is generated!");
            } catch (Exception e) {
                e.printStackTrace();
//                System.out.println("Error at XMLGenerator: " + e);
            }
            System.out.println("");
        }
        System.out.println("----------------------------");
        System.out.println("GENERATE-PROCESS FINISHES!");
        System.out.println("----------------------------");
    }
}
