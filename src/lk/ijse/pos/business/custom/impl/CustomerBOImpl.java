package lk.ijse.pos.business.custom.impl;

import lk.ijse.pos.business.custom.CustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.DAOTypes;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.db.JPAUtil;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerBOImpl implements CustomerBO {

    private CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOTypes.CUSTOMER);
    private EntityManager session = JPAUtil.getEntityManager();
//    public CustomerBOImpl(){
//        String dao = DAOFactory.getInstance().<String>getDAO(DAOTypes.CUSTOMER);
//    }

    @Override
    public CustomerDTO getCustomerById(String id) throws Exception {
        EntityManager session = JPAUtil.getEntityManager();

            customerDAO.setSession(session);
            Customer customer = customerDAO.find(id);
            CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());

            return customerDTO;

    }

    public List<CustomerDTO> getAllCustomers() throws Exception {
        EntityManager session = JPAUtil.getEntityManager();
            customerDAO.setSession(session);
            List<CustomerDTO> customers = customerDAO.findAll().stream().map(customer -> new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress())).collect(Collectors.toList());

            return customers;

    }

    public void saveCustomer(CustomerDTO dto) throws Exception {
        EntityManager session = JPAUtil.getEntityManager();

            customerDAO.setSession(session);
            customerDAO.save(new Customer(dto.getId(), dto.getName(), dto.getAddress()));


    }

    public void updateCustomer(CustomerDTO dto) throws Exception {

        EntityManager session = JPAUtil.getEntityManager();
            customerDAO.setSession(session);
            customerDAO.update(new Customer(dto.getId(), dto.getName(), dto.getAddress()));


    }

    public void removeCustomer(String id) throws Exception {
        EntityManager session = JPAUtil.getEntityManager();

            customerDAO.setSession(session);
            customerDAO.delete(id);


    }

}
