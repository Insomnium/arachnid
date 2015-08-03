package net.ins.arachnid.domain;

import java.io.Serializable;

/**
 * Created by ins on 5/27/15.
 */
public class FileInfo implements Serializable {
    private String path;
    private String extension;
    private boolean isDir;

    public FileInfo() {
    }

    public FileInfo(String path, String extension, boolean isDir) {
        this.path = path;
        this.extension = extension;
        this.isDir = isDir;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public boolean isDir() {
        return isDir;
    }

    public void setDir(boolean isDir) {
        this.isDir = isDir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileInfo fileInfo = (FileInfo) o;

        if (isDir != fileInfo.isDir) return false;
        if (path != null ? !path.equals(fileInfo.path) : fileInfo.path != null) return false;
        return !(extension != null ? !extension.equals(fileInfo.extension) : fileInfo.extension != null);

    }

    @Override
    public int hashCode() {
        int result = path != null ? path.hashCode() : 0;
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        result = 31 * result + (isDir ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "path='" + path + '\'' +
                ", extension='" + extension + '\'' +
                ", isDir=" + isDir +
                '}';
    }
}
