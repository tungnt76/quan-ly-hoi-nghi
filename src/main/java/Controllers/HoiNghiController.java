package Controllers;

import Models.HoiNghi;
import Utils.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HoiNghiController {
    public boolean add(HoiNghi hoiNghi) {
        try {

            Session session= HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
            Transaction transacsion=session.beginTransaction();
            session.save(hoiNghi);
            transacsion.commit();
            return  true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean update(HoiNghi hoiNghi) {
        try {
            Session session = HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
            Transaction transaction =session.beginTransaction();
            session.update(hoiNghi);
            transaction.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }


    public boolean delete(HoiNghi hoiNghi) {
        try {

            Session session =HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
            Transaction transacsion=session.beginTransaction();
            session.delete(hoiNghi);
            transacsion.commit();
            return true;
        } catch (HibernateException e) {
            return false;
        }
    }

    public HoiNghi load(HoiNghi maHN)
    {
        Session session=HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
        Transaction transaction=session.beginTransaction();
        HoiNghi bd =(HoiNghi) session.get(Models.HoiNghi.class, maHN);
        transaction.commit();
        return bd;
    }


    public List<HoiNghi> loadList()
    {
        Session session =HibernateAnnotationUtil.getSessionFactory().getCurrentSession();
        Transaction transacsion=session.beginTransaction();
        // lenh hql
        String hql="from DiaDiem ";
        Query query=session.createQuery(hql);
        List<HoiNghi > list =query.list();
        transacsion.commit();
        return list;
    }
}
