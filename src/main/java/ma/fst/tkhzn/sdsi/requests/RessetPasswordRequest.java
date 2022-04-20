package ma.fst.tkhzn.sdsi.requests;

import lombok.Data;

@Data
public class RessetPasswordRequest {

    private String login;
    private String pwd;
    private String ressetpassword;

}
