package com.example.autosalon.dao;
import com.example.autosalon.model.OrdersEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<OrdersEntity> listOrders() {
    return entityManager.createNativeQuery("SELECT o.instant, " +
            "car.model, o.quantity, o.amount, c.name, c.phone_number " +
            "FROM orders o LEFT JOIN customer c ON (o.customer_id = c.id) " +
            "LEFT JOIN orders_car oc ON (o.id = oc.orders_id) " +
            "LEFT JOIN car car ON (oc.car_id = car.id) " +
            "ORDER BY o.instant, c.name, o.amount DESC").getResultList();
    }

    @Override
    public int getOrdersAmountOfPeriod(String ins1, String ins2) {
        String query = "SELECT SUM(amount) FROM orders WHERE instant >= '" + ins1 + "' AND instant <= '" + ins2 +"'";
        System.out.println(query);
        return ((Number) entityManager.createNativeQuery(query).getSingleResult()).intValue();
    }
}
