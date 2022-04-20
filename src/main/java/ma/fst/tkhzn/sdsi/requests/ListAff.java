package ma.fst.tkhzn.sdsi.requests;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ListAff implements Serializable{
	private List<?> dep;
	private List<?> pers;
	public ListAff(List<?> dep, List<?> pers) {
		super();
		this.dep = dep;
		this.pers = pers;
	}
	
}
