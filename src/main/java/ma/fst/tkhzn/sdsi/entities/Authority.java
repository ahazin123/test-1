package ma.fst.tkhzn.sdsi.entities;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String role;
	
	public Authority(String role) {
		// TODO Auto-generated constructor stub
		this.role = role;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
