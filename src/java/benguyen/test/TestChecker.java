/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benguyen.test;

import benguyen.clients.BenefitsClient;
import benguyen.clients.BenefitsOfHouseClient;
import benguyen.clients.DistrictClient;
import benguyen.clients.HouseClient;
import benguyen.clients.HouseImageClient;
import benguyen.clients.SpaceClient;
import benguyen.crawl.AloNhaTroHousesCrawling;
import benguyen.crawl.NhaTroTotHousesCrawling;
import benguyen.generated.HouseList;
import benguyen.generated.Houses;
import benguyen.models.Benefits;
import benguyen.models.District;
import benguyen.models.House;
import benguyen.models.Space;
import benguyen.utils.StringUtil;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel Nguyen
 */
public class TestChecker {

    public static void main(String[] args) throws IOException {
        //Cào nhà
        Houses crawledHouses = new Houses();
        NhaTroTotHousesCrawling nhaTroTotCrawl = new NhaTroTotHousesCrawling();
        crawledHouses.getHouse().addAll(nhaTroTotCrawl.crawlNhaTroTotHouses().getHouse());
        System.out.println("Nha Tro Tot Done!!!");
        AloNhaTroHousesCrawling aloNhaTroCrawl = new AloNhaTroHousesCrawling();
        crawledHouses.getHouse().addAll(aloNhaTroCrawl.crawlAloNhaTroHouses().getHouse());
        System.out.println("Alo Nha Tot Done!!!");

        //Khởi tạo API
        DistrictClient districtClient = new DistrictClient();
        HouseClient houseClient = new HouseClient();
        SpaceClient spaceClient = new SpaceClient();
        HouseImageClient imageClient = new HouseImageClient();
        BenefitsClient benefitsClient = new BenefitsClient();
        BenefitsOfHouseClient bohClient = new BenefitsOfHouseClient();
        //Lấy dữ liệu từ database
        List<District> listDistrict = districtClient.findAll_XML();
        List<Space> listSpace = spaceClient.findAll_XML();
        List<Benefits> listBenefit = benefitsClient.findAll_XML();
        List<HouseList> listHouse = houseClient.findAll_XML();
        StringUtil util = new StringUtil();

        for (HouseList eachHouse : crawledHouses.getHouse()) {
            House house = new House();
            // Tìm id của Quận để set vào House
            for (District dis : listDistrict) {
                System.out.println(util.getDistrict(eachHouse.getAddress()));
                System.out.println(dis.getDistrictName());
                if (dis.getDistrictName().contains(util.getDistrict(eachHouse.getAddress()))) {
                    house.setDistrictID(dis);
                    break;
                }
            }//end loop for district
            System.out.println("District ID " + house.getDistrictID());
            // Tìm ID của Diện tích để set vào House
            for (Space space : listSpace) {
                int num = Integer.parseInt(util.getSpace(eachHouse.getSpace()));
                if (num < 20) {
                    if (space.getSpace().equals("Dưới 20 m2")) {
                        house.setSpaceID(space);
                        break;
                    }
                } else if (20 < num && num < 30) {
                    if (space.getSpace().equals("20 m2 - 30 m2")) {
                        house.setSpaceID(space);
                        break;
                    }
                } else if (30 < num && num < 40) {
                    if (space.getSpace().equals("30 m2 - 40 m2")) {
                        house.setSpaceID(space);
                        break;
                    }
                } else if (40 < num && num < 50) {
                    if (space.getSpace().equals("40 m2 - 50 m2")) {
                        house.setSpaceID(space);
                        break;
                    }
                } else if (50 < num && num < 60) {
                    if (space.getSpace().equals("50 m2 - 60 m2")) {
                        house.setSpaceID(space);
                        break;
                    }
                } else if (60 < num && num < 70) {
                    if (space.getSpace().equals("60 m2 - 70 m2")) {
                        house.setSpaceID(space);
                        break;
                    }
                } else if (70 < num && num < 80) {
                    if (space.getSpace().equals("70 m2 - 80 m2")) {
                        house.setSpaceID(space);
                        break;
                    }
                } else if (80 < num && num < 90) {
                    if (space.getSpace().equals("80 m2 - 90 m2")) {
                        house.setSpaceID(space);
                        break;
                    }
                } else if (90 < num) {
                    if (space.getSpace().equals("Trên 90 m2")) {
                        house.setSpaceID(space);
                        break;
                    }
                } else {
                    if (space.getSpace().equals("Không rõ")) {
                        house.setSpaceID(space);
                        break;
                    }
                }
            }//end loop for space
            System.out.println("Space ID " + house.getSpaceID());
            //Map benefit vào bảng trung gian
            for (Benefits benefits : listBenefit) {
                List<String> houseBenefit = new ArrayList<>(eachHouse.getBenefits());
                
            }

        }

    }

    public static void testWellformed(String urlString) throws MalformedURLException, IOException {

    }
}
