package org.komlev.hf.tools;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.io.File;
import java.io.FileInputStream;

/**
 * Description.
 *
 * @author <a href="mailto:AlexKomlev@rambler.ru">Aleksey Komlev</a>
 * @version 28.11.2014
 */
public class DBHelper extends HibernateDaoSupport {
    private File sql;

    /**
     * Getter for property 'sql'.
     *
     * @return Value for property 'sql'.
     */
    public File getSql() {
        return sql;
    }

    /**
     * Setter for property 'sql'.
     *
     * @param sql Value to set for property 'sql'.
     */
    public void setSql(File sql) {
        this.sql = sql;
    }

    public void initDB(){
        Session session = getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            FileInputStream fis = new FileInputStream(sql);
            int fileSize = fis.available();
            byte bSql[] = new byte[fileSize];
            fis.read(bSql);
            String sSql = new String(bSql);
            String[] statements = sSql.split(";");
            for(String statement: statements){
                SQLQuery query = session.createSQLQuery(statement);
                query.executeUpdate();
            }

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("/spring/appContext.xml");
        DBHelper dbHelper = (DBHelper) context.getBean("dbHelper");
        dbHelper.initDB();
    }
}
