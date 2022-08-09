package ai.ecma.warehouseexam.service;

import ai.ecma.warehouseexam.controller.attachment.AttachmentController;
import ai.ecma.warehouseexam.entity.attachment.Attachment;
import ai.ecma.warehouseexam.entity.attachment.AttachmentContent;
import ai.ecma.warehouseexam.exception.ApiResult;
import ai.ecma.warehouseexam.exception.RestException;
import ai.ecma.warehouseexam.payload.AttachmentDTO;
import ai.ecma.warehouseexam.repository.AttachmentContentRepository;
import ai.ecma.warehouseexam.repository.AttachmentRepository;
import ai.ecma.warehouseexam.service.interfaces.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    @Override
    public ApiResult<AttachmentDTO> uploadDb(MultipartHttpServletRequest request) {

        try {

            Iterator<String> fileNames = request.getFileNames();

            MultipartFile multipartFile = request.getFile(fileNames.next());

            String contentType = multipartFile.getContentType();//  = image/png

            String originalFilename = multipartFile.getOriginalFilename();

            long size = multipartFile.getSize();

            byte[] bytes = multipartFile.getBytes();

            Attachment attachment = new Attachment(
                    originalFilename,
                    size,
                    contentType
            );

            attachmentRepository.save(attachment);

            AttachmentContent attachmentContent = new AttachmentContent(
                    bytes,
                    attachment
            );

            attachmentContentRepository.save(attachmentContent);

            String url = AttachmentController.ATTACHMENT_CONTROLLER_PATH
                    + AttachmentController.DOWNLOAD_PATH +
                    "/" + attachment.getId();

            AttachmentDTO attachmentDTO = new AttachmentDTO(
                    attachment.getId(),
                    attachment.getOriginalName(),
                    attachment.getSize(),
                    attachment.getContentType(),
                    url
            );

            return ApiResult.successResponse(attachmentDTO);

        } catch (IOException e) {
            e.printStackTrace();
            throw RestException.maker("Fayl yuklashda xatolik", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void downloadDb(Integer id, boolean inline, HttpServletResponse response) {
        long millis = System.currentTimeMillis();
        try {
            Attachment attachment = getAttachmentByIdOrElseThrow(id);

            AttachmentContent attachmentContent = getAttachmentContentByIdOrElseThrow(attachment.getId());

            String disposition = inline ? "inline" : "attachment";

            response.setHeader("Content-Disposition",
                    disposition + "; filename=\"" + attachment.getOriginalName() + "\"");
            response.setContentType(attachment.getContentType());
            response.setHeader("Cache-Control", "max-age=8640000");
            response.setContentLength((int) attachment.getSize());

            FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());

            System.err.println("Download db => "+(System.currentTimeMillis() - millis));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*@Override
    public void downloadSystem(Integer id, boolean inline, HttpServletResponse response) {
        long millis = System.currentTimeMillis();
        try {
            Attachment attachment = getAttachmentByIdOrElseThrow(id);

            String name = attachment.getName();

            String disposition = inline ? "inline" : "attachment";

            response.setHeader("Content-Disposition",
                    disposition + "; filename=\"" + attachment.getOriginalName() + "\"");
            response.setContentType(attachment.getContentType());
            response.setHeader("Cache-Control", "max-age=8640000");
            response.setContentLength((int) attachment.getSize());

            File outFile = CommonUtils.getOutFile(attachment.getName());

            InputStream inputStream = new FileInputStream(outFile);

            FileCopyUtils.copy(inputStream, response.getOutputStream());
            System.err.println("System download = > "+(System.currentTimeMillis() - millis));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public Attachment getByOrElseThrow(Integer attachmentId) {
        return attachmentRepository.findById(attachmentId).orElseThrow(
                () -> RestException.notFound("Attachment")
        );
    }

    private Attachment getAttachmentByIdOrElseThrow(Integer id) {
        return attachmentRepository.findById(id).orElseThrow(() -> RestException.notFound("Attachment"));
    }

    private AttachmentContent getAttachmentContentByIdOrElseThrow(Integer id) {
        return attachmentContentRepository.findByAttachmentId(id).orElseThrow(() -> RestException.notFound("Attachment content"));
    }


}
