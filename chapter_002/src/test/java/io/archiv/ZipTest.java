package io.archiv;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ZipTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenPackSingleFile() throws IOException {

        File source = folder.newFile("testPackage");
        File target = new File(folder.getRoot().getAbsolutePath() + "packed.zip");
        assertFalse(target.exists());

        new Zip().packSingleFile(source, target);
        assertTrue(target.exists());
    }

    @Test
    public void whenPackFiles()throws IOException {
        List<File> files = Arrays.asList(
            folder.newFile("test1"),
            folder.newFile("test2")
        );

        File target = new File(folder.getRoot().getAbsolutePath() + "packed.zip");
        assertFalse(target.exists());

        new Zip().packFiles(files, target);
        assertTrue(target.exists());
    }

    @Test
    public void whenGetFilteredFiles() throws IOException {
        folder.newFile("test1.class");
        folder.newFile("test2.class");
        folder.newFile("test2.bat");

        List<File> files = Zip.getFilteredFiles(Paths.get(folder.getRoot().getAbsolutePath()), "class");
        assertThat(files.size(), Matchers.is(2));
    }



}