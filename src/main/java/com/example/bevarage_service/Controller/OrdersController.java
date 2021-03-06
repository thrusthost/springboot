package com.example.bevarage_service.Controller;

import com.example.bevarage_service.Model.OrderItem;
import com.example.bevarage_service.Model.Orders;
import com.example.bevarage_service.Model.Users;
import com.example.bevarage_service.repository.OrderItemRepository;
import com.example.bevarage_service.repository.OrdersRepository;
import com.example.bevarage_service.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping(value = {"orders"})

public class OrdersController {

    public List<Orders> orders= new ArrayList<>();
    final OrderItemRepository orderItemRepository;
    final OrdersRepository ordersRepository;
    final UserRepository userRepository;
    public Map<Long, List<OrderItem>> orderitems = new HashMap<Long, List<OrderItem>>();
    public Map<Long, Integer> ordermap= new HashMap<Long, Integer>();

    public OrdersController(OrderItemRepository orderItemRepository, OrdersRepository ordersRepository, UserRepository userRepository) {
        this.orderItemRepository = orderItemRepository;
        this.ordersRepository = ordersRepository;
        this.userRepository = userRepository;
    }


    @GetMapping
    public String getOrdersByUserID(Model model) throws IllegalAccessException, InstantiationException {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();

        } else {
            String username = principal.toString();
        }

//        orders.clear();
        Users user=(userRepository.findUserByUserName(((UserDetails)principal).getUsername()));
        System.out.println(user.getRoles());
        if (user.getRoles().equals("ROLE_ADMIN")){

           System.out.println(user.getRoles());
            return "redirect:ordersall";
        }
//
//            orders= ordersRepository.findAll();
//
//
//            model.addAttribute("orders",this.orders);
//
//            return "ordersAll";
//
//
//        } else
//        {
            orders = ordersRepository.findAllByusers(user);
            model.addAttribute("orders",this.orders);

            return "orders";


//        Session session= SessionFactory.class.newInstance().openSession();
//        Orders ord = session.find(Orders.class,1L, Collections.singletonMap("javax.persistence.fetchgraph",session.getEntityGraph("Orders.orderitems")));
//
//
//        if(orders.stream().count()>0){orders.clear();}
//
//        if (user.getRoles() =="ADMIN"){
//
//            orders= ordersRepository.findAll();
//
//
//            model.addAttribute("orders",this.orders);
//
//            return "ordersAll";
//
//
//        }
//        else {
//      //  List<Orders> orders=  ordersRepository.findAllByusers(user);
//
//
//
//
//



//        for(int i=0; i<orders.stream().count();i++) {
//            orderitems.put(orders.get(i).getId(), orderItemRepository.findAllByOrders(orders.get(i).getId()));
//            ordermap.put(orders.get(i).getId(),(orders.get(i).getPrice()));
//
//        }
//        System.out.println(ordermap.get(7L));
//        model.addAttribute("allordersmap", this.orderitems);
//        model.addAttribute("ordermap", this.ordermap);
//        model.addAttribute("orders",this.orders);

//        System.out.println(orderitems);




//        return "orders";
    }
}
