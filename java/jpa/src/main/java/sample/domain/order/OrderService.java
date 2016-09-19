package sample.domain.order;

import sample.domain.order.form.OrderDetailForm;
import sample.domain.order.form.OrderForm;

public class OrderService {

    private GenerateOrderNumberService generateOrderNumberService;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private ItemRepository itemRepository;

    public void register(OrderForm orderForm) {
        OrderNumber nextOrderNumber = this.generateOrderNumberService.generateNextNumber();
        Customer customer = this.customerRepository.find(orderForm.customerCode);
        Order order = new Order(nextOrderNumber, customer, orderForm.dueDate);

        for (OrderDetailForm orderDetailForm : orderForm.orderDetailForms) {
            Item item = this.itemRepository.find(orderDetailForm.itemCode);
            order.addOrderDetail(item, orderDetailForm.quantity);
        }

        this.orderRepository.register(order);
    }

    public void setGenerateOrderNumberService(GenerateOrderNumberService generateOrderNumberService) {
        this.generateOrderNumberService = generateOrderNumberService;
    }

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
}
