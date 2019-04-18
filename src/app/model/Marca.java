package app.model;
import javafx.beans.property.SimpleStringProperty;

public class Marca {
	public SimpleStringProperty name;
	public SimpleStringProperty fipe_name;
	public SimpleStringProperty order;
	public SimpleStringProperty key;
	public SimpleStringProperty id;

	/**
	 * @param name
	 * @param fipe_name
	 * @param order
	 * @param key
	 * @param id
	 */
	public Marca(String name, String fipe_name, String order, String key, String id) {
		this.name = new SimpleStringProperty(name);
		this.fipe_name = new SimpleStringProperty(fipe_name);
		this.order = new SimpleStringProperty(order);
		this.key = new SimpleStringProperty(key);
		this.id = new SimpleStringProperty(id);
	}
	public Marca() {
		this.name = new SimpleStringProperty("");
		this.fipe_name = new SimpleStringProperty("");
		this.order = new SimpleStringProperty("");
		this.key = new SimpleStringProperty("");
		this.id = new SimpleStringProperty("");
	}

	public String toJson() {
		String json = "{name: '" + this.name + "', " + " fipe_name: '" + this.fipe_name + "', " + "order: '"
				+ this.order + "', " + "key: '" + this.key + "'," + "id: '" + this.id + "'}";		
		return json;
	}

	@Override
	public String toString() {
		return "Marca :" + name + ", fipe_name=" + fipe_name + ", order=" + order + ", key=" + key + ", id=" + id + "";
	}

	public String getName() {
		return name.get();
	}
	public SimpleStringProperty nameProperty() {
		return name;
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getFipe_name() {
		return fipe_name.get();
	}
	public SimpleStringProperty fipe_nameProperty() {
		return fipe_name;
	}

	public void setFipe_name(String fipe_name) {
		this.fipe_name.set(fipe_name); 
	}

	public String getOrder() {
		return order.get();
	}
	
	public SimpleStringProperty orderProperty() {
		return order;
	}

	public void setOrder(String order) {
		this.order.set(order); 
	}

	public String getKey() {
		return key.get();
	}
	
	public SimpleStringProperty keyProperty() {
		return key;
	}
	
	public void setKey(String key) {
		this.key.set(key);
	}

	public String getId() {
		return id.get();
	}
	
	public SimpleStringProperty idProperty() {
		return id;
	}
	
	public void setId(String id) {
		this.id.set(id);
	}

}
