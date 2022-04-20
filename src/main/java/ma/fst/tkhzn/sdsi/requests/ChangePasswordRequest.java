package ma.fst.tkhzn.sdsi.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data @AllArgsConstructor @ToString
public class ChangePasswordRequest {
	private String login;
	private String ordPassword;
	private String newPassword;
}
