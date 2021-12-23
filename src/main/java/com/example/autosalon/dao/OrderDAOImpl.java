package com.example.autosalon.dao;
import com.example.autosalon.model.Order;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Order> listOrders() {
            return entityManager.createQuery("from Order as order left join order.customer as customer " +
                    "left join order.cars as car ORDER BY order.getDate, customer.getName, order.getAmount").getResultList();
    }

    @Override
    public int getOrdersAmountOfPeriod(Date start, Date end) {
        return entityManager.createNativeQuery("SELECT SUM(amount) FROM orders WHERE date >= start AND date <= end").getFirstResult();
    }
}
