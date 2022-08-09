package ai.ecma.warehouseexam.controller.attachment;

import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.payload.AttachmentDTO;
import ai.ecma.warehouseexam.service.interfaces.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class AttachmentControllerImpl implements AttachmentController {
    private final AttachmentService attachmentService;


    @Override
    public ApiResult<AttachmentDTO> upload(MultipartHttpServletRequest request) {
        return attachmentService.uploadDb(request);
    }

    @Override
    public void download(Integer id, boolean inline, HttpServletResponse response) {
        attachmentService.downloadDb(id, inline, response);
    }
}
