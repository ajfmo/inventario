package ajfmo.inventario.DAO;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ajfmo.inventario.entities.Deposito;
import ajfmo.inventario.utils.HibernateUtil;

public class DepositoDAO {

	// Hibernate
	private Session session = HibernateUtil.getSessionFactory();
	private Transaction tx = null;
	private CriteriaBuilder builder = session.getCriteriaBuilder();

	// Objects
	private Deposito deposit;

	/*******************/
	/**				  **/
	/*** Source Code ***/
	/**				  **/
	/*******************/

	/**
	 * @param descripcionDeposito
	 */
	public void createDeposit(String descripcionDeposito) {
		deposit = new Deposito(descripcionDeposito);
		tx = session.beginTransaction();
		session.save(deposit);
		tx.commit();
	}

	/**
	 * @return
	 */
	public List<Deposito> depositCriteria() {
		CriteriaQuery<Deposito> criteria = builder.createQuery(Deposito.class);
		Root<Deposito> root = criteria.from(Deposito.class);
		criteria.select(root);
		List<Deposito> resultset = session.createQuery(criteria).getResultList();
		return resultset;
	}
}
