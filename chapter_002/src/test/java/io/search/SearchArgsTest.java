package io.search;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SearchArgsTest {

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
    public void whenValidateTrue() {
        SearchArgs arg = new SearchArgs(args);
        assertTrue(arg.validate());
    }

    @Test
    public void whenValidateFalse() {
        SearchArgs arg = new SearchArgs(new String[] {""});
        assertFalse(arg.validate());
    }

    @Test
    public void whenValidateNullFalse() {
        SearchArgs arg = new SearchArgs(new String[]{null });
        assertFalse(arg.validate());
    }

    @Test
    public void whenGetDirectorySuccess() {
        SearchArgs arg = new SearchArgs(new String[] {
            "-d=c:\\project\\job4j\\",
            "-e=class",
            "-o=project.zip"
        });
        assertThat(arg.getDirectory(), Matchers.is("c:\\project\\job4j\\"));
    }

    @Test
    public void whenGetDirectoryFalse() {
        SearchArgs arg = new SearchArgs(new String[] {});
        assertNull(arg.getDirectory());
    }

    @Test
    public void whenGetFileNameSuccess() {
        SearchArgs arg = new SearchArgs(new String[] {
            "-d=c:\\project\\job4j\\",
            "-n=filename.class",
            "-o=project.zip"
        });
        assertThat(arg.getFileName(), Matchers.is("filename.class"));
    }

    @Test
    public void whenGetFileNameFalse() {
        SearchArgs arg = new SearchArgs(new String[] {});
        assertNull(arg.getFileName());
    }

    @Test
    public void whenGetOutputSuccess() {
        SearchArgs arg = new SearchArgs(new String[] {
            "-d=c:\\project\\job4j\\",
            "-e=class",
            "-o=project.zip"
        });
        assertThat(arg.getOutputFile(), Matchers.is("project.zip"));
    }

    @Test
    public void whenGetOutputFalse() {
        SearchArgs arg = new SearchArgs(new String[] {});
        assertNull(arg.getOutputFile());
    }
}