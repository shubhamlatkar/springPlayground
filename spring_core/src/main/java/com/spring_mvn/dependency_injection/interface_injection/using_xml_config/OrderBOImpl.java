package com.spring_mvn.dependency_injection.interface_injection.using_xml_config;

public class OrderBOImpl implements OrderBO{

    private OrderDAO orderDAO;

    public OrderBOImpl() {
    }

    @Override
    public void placeOrder() {
        System.out.println("Insider orderBO impl");
        orderDAO.createOrder();
    }

    public OrderBOImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

    public void setOrderDAO(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public String  toString() {
        return "OrderBOImpl{" +
                "orderDAO=" + orderDAO +
                '}';
    }
}
