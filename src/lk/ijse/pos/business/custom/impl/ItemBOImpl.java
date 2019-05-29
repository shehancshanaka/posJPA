package lk.ijse.pos.business.custom.impl;

import lk.ijse.pos.business.custom.ItemBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.DAOTypes;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.db.JPAUtil;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class ItemBOImpl implements ItemBO {

    private ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOTypes.ITEM);
    private EntityManager session = JPAUtil.getEntityManager();

    public List<ItemDTO> getAllItems() throws Exception {
        EntityManager session = JPAUtil.getEntityManager();

        itemDAO.setSession(session);
            List<ItemDTO> items = itemDAO.findAll().stream().map(item -> new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand())).collect(Collectors.toList());

            return items;


    }

    public void saveItem(ItemDTO item) throws Exception {

        EntityManager session = JPAUtil.getEntityManager();
            itemDAO.setSession(session);
            itemDAO.save(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));


    }

    public void updateItem(ItemDTO item) throws Exception {
        EntityManager session = JPAUtil.getEntityManager();

            itemDAO.setSession(session);
            itemDAO.update(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));


    }

    public void deleteItem(String code) throws Exception {
        EntityManager session = JPAUtil.getEntityManager();

            itemDAO.setSession(session);
            itemDAO.delete(code);


    }

}
