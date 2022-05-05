package ma.fst.tkhzn.sdsi.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Entity @Data @ToString
public class Message implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="sendername")
    private String sendername;
    @Column(name="receivername")
    private String receivername;
    private String message;
    private Long date;
    private String status;
    private Boolean nouvelle = true;

    public Message() {
        LocalDateTime now = LocalDateTime.now();
        System.err.println(now);
        this.date = now.atZone(ZoneId.of("Africa/Casablanca")).toEpochSecond();
        System.err.println(now.atZone(ZoneId.of("Africa/Casablanca")));
        System.err.println(this.date);
    }

    public Message(Long temps) {
        LocalDateTime now = LocalDateTime.now();
        System.err.println(now);
        this.date = now.atZone(ZoneId.of("Africa/Casablanca")).toEpochSecond() - temps;
        System.err.println(now.atZone(ZoneId.of("Africa/Casablanca")));
        System.err.println(this.date);
    }
    public Message(Long id, String sendername, String receivername, String message, String status, Boolean nouvelle) {
        this.id = id;
        this.sendername = sendername;
        this.receivername = receivername;
        this.message = message;
        LocalDateTime now = LocalDateTime.now();
        this.date = Timestamp.valueOf(now).getTime();
        this.status = status;
        this.nouvelle = nouvelle;
    }

    public Message(Message m) {
        this.id = null;
        this.sendername = m.getSendername();
        this.receivername = m.getReceivername();
        this.message = m.getMessage();
        this.date = m.getDate();
        this.status = m.getStatus();
    }

    private Long getTimestamp(LocalDateTime d) {
        return Long.parseLong(Timestamp.valueOf(LocalDateTime.now()).toString());
    }
}
