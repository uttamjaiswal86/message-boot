package in.co.vpb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.co.vpb.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
