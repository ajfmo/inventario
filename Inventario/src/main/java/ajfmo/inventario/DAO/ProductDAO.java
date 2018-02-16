package ajfmo.inventario.DAO;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ajfmo.inventario.entities.Deposito;
import ajfmo.inventario.entities.Productos;
import ajfmo.inventario.utils.HibernateUtil;
import javafx.beans.property.ObjectProperty;

public class ProductDAO {

	// Hibernate
	private Session session = HibernateUtil.getSessionFactory();
	private Transaction tx = null;
	private CriteriaBuilder builder = session.getCriteriaBuilder();

	// Objects
	private Productos product;

	/*******************/
	/**				  **/
	/*** Source Code ***/
	/**				  **/
	/*******************/

	/**
	 * @param idProductos
	 * @param descripcionProducto
	 * @param costoProducto
	 * @param precioProducto
	 * @param existenciaProducto
	 * @param deposito
	 */
	public void createProduct(String idProductos, String descripcionProducto, Double costoProducto,
			Double utilidadProducto, Double precioProducto, Double existenciaProducto,
			ObjectProperty<Deposito> deposito) {
		product = new Productos(idProductos, descripcionProducto, costoProducto, utilidadProducto, precioProducto,
				existenciaProducto, deposito);
		tx = session.beginTransaction();
		session.saveOrUpdate(product);
		tx.commit();
	}

	/**
	 * @param idProductos
	 * @param descripcionProducto
	 * @param costoProducto
	 * @param precioProducto
	 * @param existenciaProducto
	 * @param deposito
	 */
	public void updateProduct(String idProductos, String descripcionProducto, Double costoProducto,
			Double utilidadProducto, Double precioProducto, Double existenciaProducto,
			ObjectProperty<Deposito> deposito) {
		product = new Productos(idProductos, descripcionProducto, costoProducto, utilidadProducto, precioProducto,
				existenciaProducto, deposito);
		tx = session.beginTransaction();
		session.merge(product);
		tx.commit();
	}

	/**
	 * @return
	 */
	public List<Productos> depositCriteria() {
		CriteriaQuery<Productos> criteria = builder.createQuery(Productos.class);
		Root<Productos> root = criteria.from(Productos.class);
		criteria.select(root);
		List<Productos> resultset = session.createQuery(criteria).getResultList();
		return resultset;
	}

}
