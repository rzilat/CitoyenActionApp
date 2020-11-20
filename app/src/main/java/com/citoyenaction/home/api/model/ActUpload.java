package com.citoyenaction.home.api.model;

public class ActUpload {
   private long actUploadId;
    private String fileData;
    private long fileId;
    private String fileName;
    private long actNonCiviqueId;

    public ActUpload(String fileData, String fileName) {
        this.fileData = fileData;
        this.fileName = fileName;
    }

    public long getActUploadId() {
        return actUploadId;
    }

    public void setActUploadId(long actUploadId) {
        this.actUploadId = actUploadId;
    }

    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getActNonCiviqueId() {
        return actNonCiviqueId;
    }

    public void setActNonCiviqueId(long actNonCiviqueId) {
        this.actNonCiviqueId = actNonCiviqueId;
    }
}
