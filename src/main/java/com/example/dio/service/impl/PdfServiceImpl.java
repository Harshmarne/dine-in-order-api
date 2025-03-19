package com.example.dio.service.impl;

import com.example.dio.service.PdfService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class PdfServiceImpl implements PdfService {

    private final TemplateEngine templateEngine;

    /**
     * @param templateName
     * @param data
     * @return
     */
    @Override
    public byte[] generatePdf(String templateName, Map data) throws IOException {
        // Render HTML with Thymeleaf
        Context context = new Context();
        context.setVariables(data);
        String htmlContent = templateEngine.process("Bill.html", context);

        // Generate PDF with Flying Saucer
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(baos);
            return baos.toByteArray();
        }
    }
}
