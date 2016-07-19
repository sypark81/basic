package com.maria.file.domain;

import lombok.Data;

@Data
public class File {

	private String fileId;
	private int    fileSeq;
	private String filePath;
	private String fileName;
	private String fileOrgName;
	private long fileSize;
	private String fileTypeNm;
	
}
