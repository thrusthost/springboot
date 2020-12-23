package com.example.bevarage_service.Controller;

import com.example.bevarage_service.Model.OrderItem;
import com.example.bevarage_service.Model.Orders;
import com.example.bevarage_service.repository.OrderItemRepository;
import com.example.bevarage_service.repository.OrdersRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "orders")
public class OrdersController {

    public List<Orders> orders;
    final OrderItemRepository orderItemRepository;
    final OrdersRepository ordersRepository;
    public Map<Long, List<OrderItem>> orderitems = new HashMap<Long, List<OrderItem>>();
    public Map<Long, Integer> ordermap= new HashMap<Long, Integer>();

    public OrdersController(OrderItemRepository orderItemRepository, OrdersRepository ordersRepository) {
        this.orderItemRepository = orderItemRepository;
        this.ordersRepository = ordersRepository;
    }


    @GetMapping
    public String getOrdersByUserID(Model model) {
        orders=ordersRepository.findAllByUserID(1L);

        for(int i=0; i<orders.stream().count();i++) {
            orderitems.put(orders.get(i).getId(), orderItemRepository.findAllByOrderID(orders.get(i).getId()));
            ordermap.put(orders.get(i).getId(),(orders.get(i).getPrice()));
        }

        model.addAttribute("allordersmap", this.orderitems);
        model.addAttribute("ordermap", this.ordermap);

        System.out.println(orderitems);




        return "orders";
    }
}
