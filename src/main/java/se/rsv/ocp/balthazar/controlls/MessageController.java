package se.rsv.ocp.balthazar.controlls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import se.rsv.ocp.balthazar.model.MessageModel;
import se.rsv.ocp.balthazar.services.MessageService;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/ping")
    public String ping(){
        return "Pong";
    }

    @GetMapping("/")
    public List<MessageModel> getAll(){
        return messageService.getAll();
    }

    @GetMapping("/{id}")
    public MessageModel getMessageModel(@PathVariable("id") Long id){
        return messageService.getMessageModel(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable("id") Long id) {messageService.deleteMessage(id);};

/*    @PutMapping("/{id}"){
        public MessageModel upsertMessageModel(@RequestBody MessageModel model);
    }*/


    @PostMapping("/")
    @Secured("ADMIN")
    public MessageModel createMessageModel(@RequestBody MessageModel model){
        return messageService.createMessageModel(model);
    }

}
