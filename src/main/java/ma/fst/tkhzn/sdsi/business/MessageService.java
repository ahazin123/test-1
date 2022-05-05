package ma.fst.tkhzn.sdsi.business;

import ma.fst.tkhzn.sdsi.entities.Message;
import ma.fst.tkhzn.sdsi.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping(value = {"api/messageservice"})
@CrossOrigin("*")
public class MessageService {
	@Autowired
	private MessageRepository messageRepository;


	@RequestMapping(path = "/messagerecu", method = RequestMethod.GET)
	public List<Message> messageRecu(Principal user) {
		return messageRepository.messageRecus(user.getName());
	}
}
