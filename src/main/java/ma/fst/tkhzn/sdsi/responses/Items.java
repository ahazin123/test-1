package ma.fst.tkhzn.sdsi.responses;

import java.io.Serializable;
import java.util.List;

public class Items implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<?> items;
	
	public Items(List<?> items) {
		super();
		this.items = items;
	}
	public List<?> getItems() {
		return items;
	}
	public void setItems(List<?> items) {
		this.items = items;
	}
}
