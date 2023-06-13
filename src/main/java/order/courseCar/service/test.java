package order.courseCar.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import core.util.HibernateUtil;
import order.productOrder.service.ProductOrderService;
import order.productOrder.service.ProductOrderServiceImpl;


public class test {
//	private static CourseCarService ccs=new CourseCarServiceImpl();
//	private static ProductCarService pcs=new ProductCarServiceImpl();
//	private static CourseOrderService cos=new CourseOrderServiceImpl();
	private static ProductOrderService pos=new ProductOrderServiceImpl();
	

	public static void main(String[] args) {
//		CourseCarDaoImpl a =new CourseCarDaoImpl();
//		CourseCarPK b=new CourseCarPK(2,2);
//		CourseCar c=new CourseCar(b,5);
//		ProductCarDaoImpl a1=new ProductCarDaoImpl();
//		ProductCarPK b1=new ProductCarPK(2,3);
//		ProductCar c1=new ProductCar(b1,5);
//		CourseOrderDaoImpl a2=new CourseOrderDaoImpl();
//		CourseOrder b2=new CourseOrder(null,1,500,Timestamp.from(Instant.now()));
		
		
		
		SessionFactory sessionFactory=HibernateUtil.getSessionFactory();
		Session session=sessionFactory.getCurrentSession();
		try {
			Transaction tr=session.beginTransaction();
//			a.insert(c);
//			a.deleteByID(b);
//			a.updateByID(c);
			
//			System.out.println("UserID:"+a.selectByID(b).getId().getUserID());
//			System.out.println("CourseID:"+a.selectByID(b).getId().getCourseID());
//			System.out.println("Quantity:"+a.selectByID(b).getQuantity());
//			System.out.println(a.selectAll());
//			System.out.println(a.selectAllByUserID(1).get(0).getQuantity());
//			System.out.println(ccs.ListCar(1));
//			ccs.addCar(c);
//			ccs.removeCar(b);
//			ccs.cleanCar(1);
//			System.out.println(pcs.ListCar(1));
//			System.out.println(pcs.subTotal(1));
//			System.out.println(ccs.subTotal(2));
//			ccs.carPush(2);
//			ccs.sendAply(2);
//			System.out.println(pcs.ListCar(1).get(0).getQuantity());
//			pcs.addCar(c1);
//			pcs.removeCar(b1);
//			pcs.sendAply(3,"桃園市");
//			System.out.println(cos.listOrderByID(2).get(1).getTotalPrice());
//			System.out.println(cos.getIdByUserName("Tony").get(0));
//			System.out.println(cos.listOrderDetail(5).get(0).getCourseOrderDetailStatus());
//			System.out.println(cos.listOrderByUserName("tony", 0).get(0).getTotalPrice());
//			System.out.println(pos.listOrderByID(2).get(1).getTotalPrice());
//			System.out.println(pos.getIdByUserName("Tony").get(0));
//			System.out.println(pos.listOrderDetail(2).get(0).getQuantity());
			System.out.println(pos.listOrderByUserName("tony", 0).get(0).getDeliveryAddr());
			
			tr.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			
		}
		
		
		
		
		

	}

}
