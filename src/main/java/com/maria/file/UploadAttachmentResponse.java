package com.maria.file;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UploadAttachmentResponse {
	
	private String fileName;
	private long fileSize;
	private String fileContentType;
	private String attachmentUrl;
}
