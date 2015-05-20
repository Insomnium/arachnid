package net.ins.arachnid.engine;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by ins on 5/20/15.
 */
public class Ant extends SimpleFileVisitor<Path> {

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (attrs.isSymbolicLink()) {
            System.out.println(file.toFile().getAbsolutePath() + " <- Symbolic link");
        } else if (attrs.isRegularFile()) {
            System.out.println(file.toFile().getAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.out.println(String.format("======= FAILED AT FILE: %s =======", file.toFile().getAbsolutePath()));
        return FileVisitResult.CONTINUE;
    }
}
