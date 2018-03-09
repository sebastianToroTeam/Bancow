package  com.asesoftware.bancow.modelo.manejadores.utils;

import java.util.ArrayList;
import java.util.List;

public class SearchExpressionCriteria {
	
	private String field;
	private String method;
	private String compound;
	private List<String> values;
	
	private static String BLANK = "";
	
	public SearchExpressionCriteria() {
		super();
		this.field = BLANK;
		this.method = BLANK;
		this.compound = BLANK;
		this.values = new ArrayList<String>();
	}
	
	public SearchExpressionCriteria(String field, String method, String compound, List<String> values ) {
		super();
		this.field = field;
		this.method = method;
		this.compound = compound;
		this.values = values;
	}
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getCompound() {
		return compound;
	}
	public void setCompound(String compound) {
		this.compound = compound;
	}
	public List<String> getValues() {
		return values;
	}
	public void setValues(List<String> values) {
		this.values = values;
	}

}