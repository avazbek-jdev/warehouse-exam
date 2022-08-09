package ai.ecma.warehouseexam.service.interfaces;


import ai.ecma.warehouseexam.entity.attachment.Attachment;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.AttachmentDTO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

public interface AttachmentService {
    ApiResult<AttachmentDTO> uploadDb(MultipartHttpServletRequest request);

    void downloadDb(Integer id, boolean inline, HttpServletResponse response);

    Attachment getByOrElseThrow(Integer attachmentId);

}
