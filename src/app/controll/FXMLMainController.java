package app.controll;
import app.model.Marca;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FXMLMainController implements Initializable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label labelMarca;

	@FXML
	private Label labelVeiculo;

	@FXML
	private Label labelAdminPanel;

	@FXML
	private Button btn_Inserir;

	@FXML
	private HBox btn_Inserir2;

	@FXML
	private HBox btn_Inserir1;

	@FXML
	private HBox btn_Cadastrar;

	@FXML
	private TextField txtField_Buscar;

	@FXML
	private TableView<Marca> table_Marcas;

	@FXML
	private TableColumn<Marca, String> nameCol;

	@FXML
	private TableColumn<Marca, String> fipe_nameCol;

	@FXML
	private TableColumn<Marca, String> orderCol;

	@FXML
	private TableColumn<Marca, String> keyCol;

	@FXML
	private TableColumn<Marca, String> idCol;

	@FXML
	private Label labelModelo;

	@FXML
	private Button btn_Alterar;

	@FXML
	private Label labelAno;

	@FXML
	private Button btn_Remover;

	@FXML
	void initialize() {
		assert labelMarca != null : "fx:id=\"labelMarca\" was not injected: check your FXML file 'FXMLMain.fxml'.";
		assert labelVeiculo != null : "fx:id=\"labelVeiculo\" was not injected: check your FXML file 'FXMLMain.fxml'.";
		assert labelAdminPanel != null : "fx:id=\"labelAdminPanel\" was not injected: check your FXML file 'FXMLMain.fxml'.";
		assert btn_Inserir != null : "fx:id=\"btn_Inserir\" was not injected: check your FXML file 'FXMLMain.fxml'.";
		assert btn_Inserir2 != null : "fx:id=\"btn_Inserir2\" was not injected: check your FXML file 'FXMLMain.fxml'.";
		assert btn_Inserir1 != null : "fx:id=\"btn_Inserir1\" was not injected: check your FXML file 'FXMLMain.fxml'.";
		assert btn_Cadastrar != null : "fx:id=\"btn_Cadastrar\" was not injected: check your FXML file 'FXMLMain.fxml'.";
		assert txtField_Buscar != null : "fx:id=\"txtField_Buscar\" was not injected: check your FXML file 'FXMLMain.fxml'.";
		assert table_Marcas != null : "fx:id=\"table_Marcas\" was not injected: check your FXML file 'FXMLMain.fxml'.";
		assert labelModelo != null : "fx:id=\"labelModelo\" was not injected: check your FXML file 'FXMLMain.fxml'.";
		assert btn_Alterar != null : "fx:id=\"btn_Alterar\" was not injected: check your FXML file 'FXMLMain.fxml'.";
		assert labelAno != null : "fx:id=\"labelAno\" was not injected: check your FXML file 'FXMLMain.fxml'.";
		assert btn_Remover != null : "fx:id=\"btn_Remover\" was not injected: check your FXML file 'FXMLMain.fxml'.";

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// inicialização tabela
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		fipe_nameCol.setCellValueFactory(new PropertyValueFactory<>("fipe_name"));
		orderCol.setCellValueFactory(new PropertyValueFactory<>("order"));
		keyCol.setCellValueFactory(new PropertyValueFactory<>("key"));
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		//
		table_Marcas.setItems(listaDeMarcas());
		FilteredList<Marca> filteredData = new FilteredList<>(listaDeMarcas(), p -> true);
		txtField_Buscar.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(marca -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compare
				String lowerCaseFilter = newValue.toLowerCase();

				if (marca.getName().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches name.
				}
				if (marca.getFipe_name().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches fipe name.
				}
				if (marca.getId().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches id.
				}
				if (marca.getOrder().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches order.
				}
				if (marca.getKey().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches key.
				}
				return false; // Does not match.
			});
		});
		
		SortedList<Marca> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(table_Marcas.comparatorProperty());
		table_Marcas.setItems(sortedData);
	}

	private ObservableList<Marca> listaDeMarcas() {
		try {
			return getJsonMarcas();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private ObservableList<Marca> getJsonMarcas() throws JSONException {
		String URI = "http://fipeapi.appspot.com/api/1/carros/marcas.json"; // retorna marcas;

		ArrayList<Marca> listaMarcas = new ArrayList<>();
		try {
			URL url = new URL(URI);
			InputStreamReader in = new InputStreamReader(url.openStream());
			BufferedReader br = new BufferedReader(in);
			StringBuilder s = new StringBuilder("{\"data\":");
			s.append(br.readLine() + "}");

			JSONObject jso = new JSONObject(s.toString());
			JSONArray jsa = jso.getJSONArray("data");
			for (int i = 0; i < jsa.length(); i++) {
				JSONObject j = (JSONObject) jsa.get(i);
				Marca newMarca = new Marca();
				System.out.println(j.toString());
				newMarca.setFipe_name(j.getString("fipe_name"));
				newMarca.setName(j.getString("name"));
				newMarca.setId("" + j.getInt("id"));
				newMarca.setKey(j.getString("key"));
				newMarca.setOrder("" + j.getInt("order"));
				listaMarcas.add(newMarca);
				//System.out.println("adicionado: " + newMarca.toString());
			}
			ObservableList<Marca> olistaMarcas = FXCollections.observableArrayList(listaMarcas);
			for (Marca marca : olistaMarcas) {
				System.out.println(marca.toString());
			}
			return olistaMarcas;
		} catch (IOException e) {
			System.err.println(e + "Erro");
		}
		return null;
	}
}
