package ai.ecma.warehouseexam.repository;

import ai.ecma.warehouseexam.entity.attachment.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
}
