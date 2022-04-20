package ma.fst.tkhzn.sdsi.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Validation implements Serializable {
    private long code;
    private Boolean check;
}
