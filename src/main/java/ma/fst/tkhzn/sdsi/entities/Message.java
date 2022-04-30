package ma.fst.tkhzn.sdsi.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@NoArgsConstructor @AllArgsConstructor @Data @ToString
public class Message implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="sendername")
    private String senderName;
    @Column(name="receivername")
    private String receiverName;
    private String message;
    private String date;
    private Status status;
}
