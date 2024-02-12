package edu.auburn.bmb0136.comp2210;

import java.io.File;

public class TempFile implements AutoCloseable {

    private final File f;

    public TempFile(File f) {
        this.f = f;
        if (f == null) {
            return;
        }
        if (!f.isFile()) {
            throw new IllegalArgumentException();
        }

    }

    public String getPath() {
        if (f == null) {
            return "";
        }
        return f.getAbsolutePath();
    }

    @Override
    public void close() throws Exception {
        if (f == null) {
            return;
        }
        if (!f.delete()) {
            throw new RuntimeException("Failed to delete temp file");
        }
    }
}
