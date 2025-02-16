//package top.johnnycse.flight;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import org.junit.jupiter.api.Test;
//import org.junit.platform.commons.util.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.CriteriaDefinition;
//import org.springframework.data.mongodb.core.query.Query;
//import top.johnnycse.flight.pojo.Seat;
//import top.johnnycse.flight.service.SeatService;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//@SpringBootTest
//public class MongoDBApplicationTests {
//    @Autowired
//    private MongoTemplate mongoTemplate;
//    private JSONArray pathwayArr;
//    @Autowired
//    private SeatService service;
//
//    @Test
//    public void findAll(){
//
////        Query query = new Query();
////        query.addCriteria(Criteria.where("flightId").is(1));
////        List<Seat> all = mongoTemplate.find(query,Seat.class);
////        ArrayList<String> objects = new ArrayList<>();
////        if(!all.isEmpty()){
////            Seat seat = all.get(0);
////            Map<String, List<Integer>> map = seat.getSeat();
////            for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
////                objects.add(entry.getValue().toString());
////            }
////        }
////
////        System.out.println(objects);
////        if(StringUtils.isNotBlank(objects.toString())){
////            JSONArray objects1 = JSON.parseArray(objects.toString());
////        }
//        System.out.println(service.findNumber("2"));
//
//
//
//
//    }
//}
