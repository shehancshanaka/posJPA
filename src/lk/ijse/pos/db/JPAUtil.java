package lk.ijse.pos.db;



import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class JPAUtil {
    public static EntityManager getEntityManager(){



            File file = new File("resources/application.properties");
            Properties properties = new Properties();


            FileReader fileReader;

            {
                try {
                    fileReader = new FileReader(file);
                    properties.load(fileReader);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            EntityManagerFactory emf = Persistence.
                    createEntityManagerFactory("unit1", properties);

            EntityManager em = emf.createEntityManager();
       return em;

        }




}
