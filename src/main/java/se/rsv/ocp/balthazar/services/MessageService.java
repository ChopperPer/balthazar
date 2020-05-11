package se.rsv.ocp.balthazar.services;

import se.rsv.ocp.balthazar.model.MessageModel;

import java.util.List;

public interface MessageService {
    List<MessageModel> getAll();
    MessageModel getMessageModel(Long id);
    MessageModel createMessageModel(MessageModel model);
    MessageModel upsertMessageModel(Long id, MessageModel model);
    void deleteMessage(Long id);

}
