package io.archiv;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class ArgZipTest {

    private String[] args;

    @Before
    public void initArgs() {
        args = new String[] {
            "-d=c:\\project\\job4j\\",
            "-e=class",
            "-o=project.zip"
        };
    }

    @Test
    public void whenValidSuccess() {
        ArgZip argZip = new ArgZip(args);
        assertThat(argZip.valid(), Matchers.is(true));
    }

    @Test
    public void whenDirectorySuccess() {
        ArgZip argZip = new ArgZip(args);
        assertThat(argZip.directory(), Matchers.is("c:\\project\\job4j\\"));
    }

    @Test
    public void whenExcludedSuccess() {
        ArgZip argZip = new ArgZip(args);
        assertThat(argZip.exclude(), Matchers.is("class"));
    }

    @Test
    public void whenOutputSuccess() {
        ArgZip argZip = new ArgZip(args);
        assertThat(argZip.output(), Matchers.is("project.zip"));
    }

}