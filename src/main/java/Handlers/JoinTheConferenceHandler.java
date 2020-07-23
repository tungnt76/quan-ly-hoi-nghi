package Handlers;

import Models.Conference;
import Models.JoinTheConference;
import Models.Place;
import Utils.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class JoinTheConferenceHandler {
    public static List<JoinTheConference> loadList()
    {
        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        // lenh hql
        String hql="from Models.JoinTheConference";
        Query query=session.createQuery(hql);
        List<JoinTheConference> list =query.list();
        transaction.commit();
        session.close();
        return list;
    }

    public static List<JoinTheConference> loadUnregisterUserList(){
        {
            Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();
            // lenh hql
            String hql="from Models.JoinTheConference as i where i.status=0";
            Query query=session.createQuery(hql);
            List<JoinTheConference> list =query.list();
            transaction.commit();
            session.close();
            return list;
        }
    }

    public static boolean update(JoinTheConference joinTheConference) {
        try {
            Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
            Transaction transaction =session.beginTransaction();
            session.update(joinTheConference);
            transaction.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }
    public static boolean delete(JoinTheConference joinTheConference) {
        Session session =HibernateAnnotationUtil.getSessionFactory().openSession();
        try {
            Transaction transaction=session.beginTransaction();
            session.delete(joinTheConference);
            transaction.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            session.close();
            e.printStackTrace();
            return false;
        }
    }

    public static boolean add(JoinTheConference joinTheConference) {
        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        try {
            Transaction transaction =session.beginTransaction();
            session.save(joinTheConference);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return false;
        }
    }
}
