package com.example.dio.service;

import java.io.IOException;
import java.util.Map;

public interface PdfService <K , V> {

    byte[] generatePdf(String templateName, Map<K , V> data) throws IOException;
}
