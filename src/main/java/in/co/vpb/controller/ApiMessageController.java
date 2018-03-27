package in.co.vpb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.vpb.exception.ResourceNotFoundException;
import in.co.vpb.model.Message;
import in.co.vpb.repository.MessageRepository;

@RestController
@RequestMapping("/api")
public class ApiMessageController {
	@Autowired
	MessageRepository messageRepository;

	@GetMapping("/messages")
	public List<Message> getAllMessage() {
		return messageRepository.findAll();
	}

	@PostMapping("/messages")
	public Message createMessage(@RequestBody Message message) {
		return messageRepository.save(message);
	}

	@GetMapping("/messages/{id}")
	public Message getMessageById(@PathVariable(value = "id") Long id) {
		return messageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("message", "id", id));
	}

	@PutMapping("/messages/{id}")
	public Message updateMessage(@PathVariable(value = "id") Long id, @RequestBody Message message) {
		Message msg = messageRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("message", "id", id));
		msg.setComment(message.getComment());
		msg.setEmail(message.getEmail());
		msg.setName(message.getName());
		Message m = messageRepository.save(msg);
		return m;
	}

	@DeleteMapping("/messages/{id}")
	public ResponseEntity<?> deleteMessage(@PathVariable("id") Long id) {
		Message msg = messageRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("message", "id", id));
		messageRepository.delete(msg);
		return ResponseEntity.ok().build();
	}

}
