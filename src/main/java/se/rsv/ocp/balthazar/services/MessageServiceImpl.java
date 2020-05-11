package se.rsv.ocp.balthazar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.rsv.ocp.balthazar.model.MessageModel;
import se.rsv.ocp.balthazar.repository.MessageRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override public List<MessageModel> getAll() {
        return messageRepository.findAll();
    }

    @Override public MessageModel getMessageModel(Long id) {
        return entityManager.find(MessageModel.class, id);
        //return messageRepository.getOne(id);
    }

    @Override public MessageModel createMessageModel(MessageModel model) {

        return messageRepository.save(model);

    }

    @Override public MessageModel upsertMessageModel(Long id, MessageModel model) {
        return messageRepository.save(model);
        //TODO (Per) Create an upsert method
    }

    @Override public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
