package io.quarkus.gradle;

import static org.junit.jupiter.api.Assertions.*;

import org.gradle.api.Project;
import org.gradle.api.tasks.TaskContainer;
import org.gradle.testfixtures.ProjectBuilder;
import org.junit.jupiter.api.Test;

public class QuarkusPluginTest {

    @Test
    public void shouldCreateTasks() {
        Project project = ProjectBuilder.builder().build();
        project.getPluginManager().apply("io.quarkus.gradle.plugin");

        assertTrue(project.getPluginManager().hasPlugin("io.quarkus.gradle.plugin"));

        TaskContainer tasks = project.getTasks();
        assertNotNull(tasks.getByName("quarkusBuild"));
        assertNotNull(tasks.getByName("quarkusDev"));
        assertNotNull(tasks.getByName("quarkusNative"));
        assertNotNull(tasks.getByName("listExtensions"));
        assertNotNull(tasks.getByName("addExtension"));
    }

    @Test
    public void shouldMakeAssembleDependOnQuarkusBuild() {
        Project project = ProjectBuilder.builder().build();
        project.getPluginManager().apply("io.quarkus.gradle.plugin");
        project.getPluginManager().apply("base");

        TaskContainer tasks = project.getTasks();

        assertTrue(tasks.getByName("assemble").getDependsOn().contains(tasks.getByName("quarkusBuild")));
    }

    @Test
    public void shouldMakeQuarkusDevAndQuarkusBuildDependOnClassesTask() {
        Project project = ProjectBuilder.builder().build();
        project.getPluginManager().apply("io.quarkus.gradle.plugin");
        project.getPluginManager().apply("java");

        TaskContainer tasks = project.getTasks();

        assertTrue(tasks.getByName("quarkusBuild").getDependsOn().contains(tasks.getByName("classes")));
        assertTrue(tasks.getByName("quarkusDev").getDependsOn().contains(tasks.getByName("classes")));
    }
}
