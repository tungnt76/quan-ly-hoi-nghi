import Handlers.ConferenceHandler;
import Handlers.DatabaseHandler;
import Handlers.PlaceHandler;
import Models.Conference;
import Models.Place;
import Utils.HibernateAnnotationUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Main extends Application{

    static {
        new Thread(() -> {
            DatabaseHandler.getInstance();
        }).start();

        try {
            Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
            Transaction transaction=session.beginTransaction();

            System.out.println("Create successfully!!");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Views/dashboard.fxml"));
        primaryStage.setTitle("Add conference");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(final String[] args) throws Exception {
//            launch(args);

        List<Place> placeList =  PlaceHandler.loadList();
        placeList.forEach(place -> {
            System.out.println(place.toString());
        });

         List<Conference> conferenceList = ConferenceHandler.loadList();
         conferenceList.forEach(conference -> {
             System.out.println(conference.toString());
         });

//        DiaDiem diaDiem = new DiaDiem("Hoi truong A", "HCMUS", 150);
//        Session session = null;
//        try {
//            session = HibernateAnnotationUtil.getSessionFactory().openSession();
//            Transaction transaction=session.beginTransaction();
//            List<DiaDiem> list  = DiaDiemHandler.loadList();
//            list.forEach(diaDiem -> {
//                System.out.println(diaDiem.toString());
//            });
//            transaction.commit();
//            System.out.println("Success");
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        session.close();

//        transaction = session.beginTransaction();
//
//        String hql;
//        Query query;
//        // sql
//
//        hql = "delete from DiaDiem ";
//        query = session.createQuery(hql);
//        query.executeUpdate();
//        // error: Transaction not successfully started
////        transaction.commit();
//
        
//        session.save(diaDiem);
//        hql = "from DiaDiem";
//        query = session.createQuery(hql);
//        List<DiaDiem> list = query.list();
//        for(DiaDiem dd : list)
//            System.out.println(dd.toString());
//        transaction.commit();
//
//        session.close();

    }
}