package se.rsv.ocp.balthazar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.rsv.ocp.balthazar.model.MessageModel;

public interface MessageRepository extends JpaRepository<MessageModel, Long> {
}
