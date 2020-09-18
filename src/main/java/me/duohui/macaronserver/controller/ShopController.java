package me.duohui.macaronserver.controller;

import me.duohui.macaronserver.model.Customer;
import me.duohui.macaronserver.model.Shop;
import me.duohui.macaronserver.model.Showcase;
import me.duohui.macaronserver.service.CustomerService;
import me.duohui.macaronserver.service.LineupService;
import me.duohui.macaronserver.service.ShopService;
import me.duohui.macaronserver.util.DistanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController //
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    ShopService service;

    @Autowired
    CustomerService customerService;

    @Autowired
    LineupService lineupService;

    @RequestMapping(method=RequestMethod.GET)
    public Shop get(@RequestParam(value = "memberNumber") int memberNumber) {
        System.out.println("shop get(by memberNumber) 메서드 호출");
        try {
            return service.getShop(memberNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value="/{shopNumber}",method=RequestMethod.GET)
    public Shop getByShopNumber(@PathVariable int shopNumber) {
        System.out.println("shop get(by shopNumber) 메서드 호출");
        try {
            return service.getShopByShopNumber(shopNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value="/list",method=RequestMethod.GET)
    public List<Shop> getShopList() {
        System.out.println("shop getList 메서드 호출");
        try {
            return service.getList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value="/list/{customerNumber}",method=RequestMethod.GET)
    public List<Shop> shopGetListByDistance(@PathVariable(value="customerNumber") int customerNumber) {

        System.out.println("shop getList by distance 메서드 호출");

        try {
            Customer customer = customerService.getCustomerByCustomerNumber(customerNumber);
            List<Shop> list = service.getList();
            sort(list, customer);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value="/list/{customerNumber}/{gu}",method=RequestMethod.GET)
    public List<Shop> shopGetListInGuByDistance(@PathVariable(value="customerNumber") int customerNumber,
                                                @PathVariable(value="gu") String gu) {

        System.out.println("shop getListInGu 메서드 호출");

        try {
            Customer customer = customerService.getCustomerByCustomerNumber(customerNumber);
            List<Shop> list = service.getListInGu(gu);
            sort(list, customer);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value="/list/{customerNumber}/{gu}/{keyword}",method=RequestMethod.GET)
    public List<Shop> shopSearch(@PathVariable(value="customerNumber") int customerNumber,
                                 @PathVariable(value="gu") String gu,
                                 @PathVariable(value="keyword") String keyword) {

        System.out.println("shop search 메서드 호출");

        try {
            Customer customer = customerService.getCustomerByCustomerNumber(customerNumber);
            List<Shop> list = service.getListSearch(gu, keyword);
            sort(list, customer);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(method=RequestMethod.POST)
    public boolean create(@RequestBody Shop shop) {
        System.out.println("shop create 메서드 호출");
        try {
            return service.create(shop);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(method=RequestMethod.PUT)
    public boolean update(@RequestBody Shop shop) {
        System.out.println("shop update 메서드 호출");
        try {
            return service.update(shop);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/open",method=RequestMethod.PUT)
    public boolean open(@RequestBody int shopNumber) {
        System.out.println("shop updateOpen 메서드 호출");
        try {
            boolean result = service.open(shopNumber);
            if(result) lineupService.updateAllTo0(shopNumber);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/close",method=RequestMethod.PUT)
    public boolean close(@RequestBody int shopNumber) {
        System.out.println("shop updateClose 메서드 호출");
        try {
            boolean result = service.close(shopNumber);
            if(result) lineupService.updateAllTo2(shopNumber);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/showcase",method=RequestMethod.PUT)
    public boolean updateShowcase(@RequestBody Showcase showcase) {
        System.out.println("shop updateShowcase 메서드 호출");
        try {
            return service.updateShowcase(showcase);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    void sort(List<Shop> list, Customer customer) throws Exception {
        for(Shop shop:list) {
            //거리 계산해서 넣어줌
            int dist = (int)DistanceUtil.distance(customer.getLat(), shop.getLat(), customer.getLon(), shop.getLon(), 0.0, 0.0);
            shop.setDistance(dist);
            //북마크 여부 넣어줌
            if(service.isBookmarked(customer.getCustomerNumber(), shop.getShopNumber())) {
                shop.setBookmark(1);
            }
        }
        //즐겨찾기, 거리순으로 정렬
        if(customer.getLat()==0&&customer.getLon()==0) { //위치 등록 안했다면 즐겨찾기로만 정렬
            list.sort(new Comparator<Shop>() {

                @Override
                public int compare(Shop o1, Shop o2) {
                    return o2.getBookmark()-o1.getBookmark();
                }
            });
        }else {
            list.sort(new Comparator<Shop>() {

                @Override
                public int compare(Shop o1, Shop o2) {
                    if(o1.getBookmark()!=o2.getBookmark()) {
                        return o2.getBookmark()-o1.getBookmark();
                    }else {
                        return o1.getDistance()-o2.getDistance();
                    }
                }
            });
        }
    }
}
