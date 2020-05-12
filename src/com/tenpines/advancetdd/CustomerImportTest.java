package com.tenpines.advancetdd;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import static org.junit.Assert.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.List;

public class CustomerImportTest {

    private Session session;

    @Test
    public void test1() throws IOException {
        importCustomers(new FileReader("resources/input.txt"));

        List<Customer> customers = session.createCriteria(Customer.class).list();
        assertEquals(2, customers.size());

    }

    @Test
    public void test2() throws IOException {
        importCustomers(new FileReader("resources/input.txt"));

        List<Customer> customers = session.createCriteria(Customer.class).add(
                Restrictions.eq("identificationType", "D"))
                .add(Restrictions.eq("identificationNumber", "22333444"))
                .list();
        assertEquals(1, customers.size());
        assertEquals("Pepe", customers.get(0).getFirstName());
        assertEquals("Sanchez", customers.get(0).getLastName());

    }


    public void importCustomers(Reader reader) throws IOException{

        LineNumberReader lineReader = new LineNumberReader(reader);

        Customer newCustomer = null;
        String line = lineReader.readLine();
        while (line!=null) {
            if (line.startsWith("C")){
                String[] customerData = line.split(",");
                newCustomer = new Customer();
                newCustomer.setFirstName(customerData[1]);
                newCustomer.setLastName(customerData[2]);
                newCustomer.setIdentificationType(customerData[3]);
                newCustomer.setIdentificationNumber(customerData[4]);
                session.persist(newCustomer);
            }
            else if (line.startsWith("A")) {
                String[] addressData = line.split(",");
                Address newAddress = new Address();

                newCustomer.addAddress(newAddress);
                newAddress.setStreetName(addressData[1]);
                newAddress.setStreetNumber(Integer.parseInt(addressData[2]));
                newAddress.setTown(addressData[3]);
                newAddress.setZipCode(Integer.parseInt(addressData[4]));
                newAddress.setProvince(addressData[3]);
            }

            line = lineReader.readLine();
        }

        reader.close();
    }

    @BeforeEach
    private void setup() {
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @AfterEach
    private void tearDown() {
        session.getTransaction().commit();
        session.close();
    }

}
