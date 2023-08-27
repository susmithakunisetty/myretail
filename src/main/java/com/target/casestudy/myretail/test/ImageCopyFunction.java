package com.target.casestudy.myretail.test;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageCopyFunction {
    @FunctionName("ImageCopyFunction")
    public void run(
            @BlobTrigger(name = "sourceBlob", dataType = "binary", path = "source_container/{name}") byte[] content,
            @BlobOutput(name = "destinationBlob", dataType = "binary", path = "destination_container/{name}") OutputBinding<byte[]> outputBlob,
            final ExecutionContext context
    ) {
        String sourceBlobUrl = "https://source_storage_account.blob.core.windows.net/source_container/{name}";
        String destinationBlobUrl = "https://destination_storage_account.blob.core.windows.net/destination_container/{name}";

        try {
            // Open connections to source and destination blob URLs
            String sourceBlobName = context.getTriggeredBlobName();
            String destinationBlobName = sourceBlobName; // Keep the same name for the destination blob

            sourceBlobUrl = sourceBlobUrl.replace("{name}", sourceBlobName);
            destinationBlobUrl = destinationBlobUrl.replace("{name}", destinationBlobName);

            URL sourceUrl = new URL(sourceBlobUrl);
            HttpURLConnection sourceConnection = (HttpURLConnection) sourceUrl.openConnection();

            URL destinationUrl = new URL(destinationBlobUrl);
            HttpURLConnection destinationConnection = (HttpURLConnection) destinationUrl.openConnection();
            destinationConnection.setRequestMethod("PUT");
            destinationConnection.setDoOutput(true);

            // Copy data from source to destination
            try (InputStream inputStream = sourceConnection.getInputStream();
                 OutputStream outputStream = destinationConnection.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            // Get response code from destination connection
            int responseCode = destinationConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                context.getLogger().info("Image copied successfully.");
            } else {
                context.getLogger().warning("Image copy failed. Response code: " + responseCode);
            }

            // Close connections
            sourceConnection.disconnect();
            destinationConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
