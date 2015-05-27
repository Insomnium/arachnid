package net.ins.arachnid.engine;

import org.springframework.beans.factory.annotation.Value;

import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;

/**
 * Created by ins on 5/25/15.
 */
public abstract class Scanner extends SimpleFileVisitor<Path> {
    @Value("${crawler.rootfolder}")
    protected String root;

    public abstract void scan();
}
