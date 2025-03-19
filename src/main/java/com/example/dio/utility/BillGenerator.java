package com.example.dio.utility;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@AllArgsConstructor
@Component
public class BillGenerator {

    private final TemplateEngine templateEngine;

    public byte[] generateTopdf(String s , Map<String,Object> data) throws IOException{

        // Render HTML with Thymeleaf
        Context context = new Context();
        context.setVariables(data);
        String htmlContent = templateEngine.process("Bill.html", context);

        // Generate PDF with Flying Saucer
        try (
                ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(baos);
            return baos.toByteArray();
        }
    }
}
