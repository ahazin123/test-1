package ma.fst.tkhzn.sdsi.responses;

import lombok.Data;

@Data
public class LoginResponse {
	private String role;
	private String token;
}
