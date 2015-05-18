package net.ins.arachnid.domain;

/**
 * Created by ins on 5/17/15.
 */
public class FIleInfo {
    private String name;
    private String path;
    private String extension;
    private boolean dir;

    public FIleInfo() {
    }

    public FIleInfo(String name, String path, String extension, boolean dir) {
        this.name = name;
        this.path = path;
        this.extension = extension;
        this.dir = dir;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return dir;
    }

    public void setDir(boolean dir) {
        this.dir = dir;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FIleInfo fIleInfo = (FIleInfo) o;

        if (dir != fIleInfo.dir) return false;
        if (name != null ? !name.equals(fIleInfo.name) : fIleInfo.name != null) return false;
        if (path != null ? !path.equals(fIleInfo.path) : fIleInfo.path != null) return false;
        return !(extension != null ? !extension.equals(fIleInfo.extension) : fIleInfo.extension != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        result = 31 * result + (dir ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FIleInfo{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", extension='" + extension + '\'' +
                ", dir=" + dir +
                '}';
    }
}
