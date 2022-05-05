package ma.fst.tkhzn.sdsi.business;

import ma.fst.tkhzn.sdsi.entities.Message;
import ma.fst.tkhzn.sdsi.entities.Utilisateur;
import ma.fst.tkhzn.sdsi.repositories.MessageRepository;
import ma.fst.tkhzn.sdsi.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin
public class Chat {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receiveMessage(@Payload Message message){
        if(message.getStatus().equalsIgnoreCase("MESSAGE")) {
            for(Utilisateur user: utilisateurRepository.findAll()) {
                Message m = new Message(message);
                m.setReceivername(user.getLogin());
                messageRepository.save(m);
                System.out.println(m);
            }
        }
        System.err.println(message + " public");
        return message;
    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message){
        System.out.println(message.getStatus());
        if(message.getStatus().equalsIgnoreCase("MESSAGE")) {
            messageRepository.save(message);
            System.out.println(message + " private");
        }
        simpMessagingTemplate.convertAndSendToUser(message.getReceivername(), "/private", message);
        return message;
    }
}
