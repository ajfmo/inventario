package ajfmo.inventario.view;

import java.net.URL;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ajfmo.inventario.DAO.DepositoDAO;
import ajfmo.inventario.DAO.ProductDAO;
import ajfmo.inventario.core.MainApp;
import ajfmo.inventario.entities.Deposito;
import ajfmo.inventario.entities.Productos;
import ajfmo.inventario.utils.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

public class MainView implements Initializable {

	// FXML
	@FXML
	private TextField txtCode, txtDescription, txtCost, txtUtil, txtPrice, txtInventory;
	@FXML
	private Button createBtn, updateBtn, readBtn, deleteBtn, cancelBtn, exitBtn;
	@FXML
	private TableView<Productos> productsTbl;
	@FXML
	private TableColumn<Productos, String> codeCol;
	@FXML
	private TableColumn<Productos, String> descriptionCol;
	@FXML
	private TableColumn<Productos, Double> costCol;
	@FXML
	private TableColumn<Productos, Double> utilCol;
	@FXML
	private TableColumn<Productos, Double> priceCol;
	@FXML
	private TableColumn<Productos, Double> inventoryCol;
	@FXML
	private TableColumn<Productos, String> depositCol;
	@FXML
	private ComboBox<Deposito> depositCbo;

	// Collections
	private final ObservableList<Deposito> depositList = FXCollections.observableArrayList();
	private final ObservableList<Productos> productList = FXCollections.observableArrayList();

	// Objects
	private static final Logger log = LoggerFactory.getLogger(MainApp.class);
	private final ProductDAO product = new ProductDAO();
	private final DepositoDAO deposit = new DepositoDAO();

	/*******************/
	/**				  **/
	/*** Source Code ***/
	/**				  **/
	/*******************/

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		productsTbl.isEditable();

		// Make TableColumn Editable with setCellFactory method
		descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
		costCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		utilCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		inventoryCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

		fillDepositCbo(depositList);
		fillProductsTbl(productList);

		depositCbo.setItems(depositList);
		productsTbl.setItems(productList);

		// Bind the Column with the data
		codeCol.setCellValueFactory(data -> data.getValue().IdProductosProperty());
		descriptionCol.setCellValueFactory(data -> data.getValue().DescripcionProductoProperty());
		costCol.setCellValueFactory(data -> data.getValue().CostoProductoProperty().asObject());
		utilCol.setCellValueFactory(data -> data.getValue().UtilidadProductoProperty().asObject());
		priceCol.setCellValueFactory(data -> data.getValue().PrecioProductoProperty().asObject());
		inventoryCol.setCellValueFactory(data -> data.getValue().ExistenciaProductoProperty().asObject());
		depositCol.setCellValueFactory(dataDep -> dataDep.getValue().getDeposito().DescripcionDepositoProperty());

	}

	/**
	 * 
	 */
	@FXML
	private void create() {
//		product.createProduct(txtCode.getText(), txtDescription.getText(), Double.parseDouble(txtCost.getText()),
//				Double.parseDouble(txtUtil.getText()), Double.parseDouble(txtPrice.getText()),
//				Double.parseDouble(txtInventory.getText()), depositCbo.getValue().getDescripcionDeposito());
//		cancel();
	}

	/**
	 * Search a record according to the code or description in database
	 */
	@FXML
	private void read() {

	}

	/**
	 * Updates a record from database
	 */
	@FXML
	private void update() {
		// for (int i = 0; i < productsTbl.getItems().size(); i++) {
		// product.updateProduct(productsTbl.getItems().get(i).getIdProductos(),
		// productsTbl.getItems().get(i).getDescripcionProducto(),
		// productsTbl.getItems().get(i).getCostoProducto(),
		// productsTbl.getItems().get(i).getUtilidadProducto(),
		// productsTbl.getItems().get(i).getPrecioProducto(),
		// productsTbl.getItems().get(i).getExistenciaProducto(),
		// productsTbl.getItems().get(i).getDeposito());
		// }
	}

	/**
	 * Deletes a record from database
	 */
	@FXML
	private void delete() {

	}

	/**
	 * Fill Deposit ComboBox
	 * 
	 * @param productList
	 */
	private void fillDepositCbo(ObservableList<Deposito> depositList) {
		for (Deposito deposits : deposit.depositCriteria()) {
			depositList.add(deposits);
		}
	}

	/**
	 * Fill Product TableView
	 * 
	 * @param productList
	 */
	private void fillProductsTbl(ObservableList<Productos> productList) {
		for (Productos products : product.depositCriteria()) {
			productList.add(products);
		}
	}

	/**
	 * Clear all fields, table and populate table again.
	 */
	@FXML
	private void cancel() {
		productsTbl.getItems().clear();
		txtCode.setText(null);
		txtDescription.setText(null);
		txtCost.setText(null);
		txtPrice.setText(null);
		txtInventory.setText(null);
		depositCbo.setValue(null);
		fillProductsTbl(productList);
	}

	/**
	 * Close aplication
	 */
	@FXML
	private void exit() {
		log.debug("Closing and destructing connection!");
		if (HibernateUtil.getSessionFactory() != null) {
			HibernateUtil.getSessionFactory().close();
			HibernateUtil.shutdown();
		}
		log.debug("Closing stage!");
		Stage stage = (Stage) exitBtn.getScene().getWindow();
		stage.close();
		log.debug("Terminating application!");
		System.exit(0);
	}
}
