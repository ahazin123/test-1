package ma.fst.tkhzn.sdsi.business;

import ma.fst.tkhzn.sdsi.entities.Message;
import ma.fst.tkhzn.sdsi.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class Chat {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receiveMessage(@Payload Message message){
        System.err.println(message);
        return message;
    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message){
//        messageRepository.save(message);
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
        System.out.println(message);
        return message;
    }
}
