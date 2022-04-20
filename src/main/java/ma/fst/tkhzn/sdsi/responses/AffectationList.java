package ma.fst.tkhzn.sdsi.responses;

import lombok.Data;

import java.util.Date;

@Data
public class AffectationList {

    String code;
    String personnel;
    Date date;

    public AffectationList(String code, String personnel, Date date) {
        this.code = code;
        this.personnel = personnel;
        this.date = date;
    }
}
