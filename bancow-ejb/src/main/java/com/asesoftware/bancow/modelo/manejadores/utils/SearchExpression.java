package  com.asesoftware.bancow.modelo.manejadores.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SearchExpression {
	
	private Map<String, Object> values;
	
	public SearchExpression(List<String> fields) {
		this.values = new HashMap<String, Object>();
		
		Iterator<String> iterator = fields.iterator();
		while (iterator.hasNext()) {
			this.values.put(iterator.next(), null);
        }
	}
	
	public void setObject(String key, Object object) {
		this.values.put(key, object);
	}
	public Map<String, Object> getValues() {
		return values;
	}

		
}