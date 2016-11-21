package me.wonwoo.web;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * https://github.com/spring-io/initializr/blob/master/initializr-generator/src/test/groovy/io/spring/initializr/test/generator/PomAssert.groovy
 * <p>
 * Created by wonwoo on 2016. 11. 20..
 */
public class ProjectAssert {
  public static final String DEFAULT_PACKAGE_NAME = "com.example";

  public static final String DEFAULT_APPLICATION_NAME = "DemoApplication";

  private final File dir;
  private Boolean mavenProject;

  /**
   * Create a new instance with the directory holding the generated project.
   *
   * @param dir
   */
  public ProjectAssert(File dir) {
    this.dir = dir;
  }

  /**
   * Validate that the project contains a base directory with the specified name.
   * <p>When extracting such archive, a directory with the specified {@code name}
   * will be created with the content of the project instead of extracting it in
   * the directory itself.
   *
   * @param name the expected name of the base directory
   * @return an updated project assert on that base directory
   */
  public ProjectAssert hasBaseDir(String name) {
    File projectDir = file(name);
    assertTrue("No directory +" + name + " found in", projectDir.exists());
    assertTrue(name + " is not a directory", projectDir.isDirectory());
    return new ProjectAssert(projectDir); // Replacing the root dir so that other assertions match the root
  }

//  PomAssert pomAssert() {
//    return new PomAssert(file("pom.xml").text);
//  }
//
//  /**
//   * Return a {@link GradleBuildAssert} for this project.
//   */
//  GradleBuildAssert gradleBuildAssert() {
//    new GradleBuildAssert(file("build.gradle").text)
//  }
//
//  /**
//   * Return a {@link SourceCodeAssert} for the specified source code.
//   */
//  SourceCodeAssert sourceCodeAssert(String sourceCodePath) {
//    hasFile(sourceCodePath)
//    new SourceCodeAssert(sourceCodePath, file(sourceCodePath).text)
//  }

  public ProjectAssert isMavenProject() {
    hasFile("pom.xml").hasNoFile("build.gradle");
    hasFile("mvnw", "mvnw.cmd",
      ".mvn/wrapper/maven-wrapper.properties",
      ".mvn/wrapper/maven-wrapper.jar");
    mavenProject = true;
    return this;
  }

  public ProjectAssert isGradleProject() {
    hasFile("build.gradle").hasNoFile("pom.xml");
    hasFile("gradlew", "gradlew.bat",
      "gradle/wrapper/gradle-wrapper.properties",
      "gradle/wrapper/gradle-wrapper.jar");
    mavenProject = false;
    return this;
  }

  public ProjectAssert isJavaProject(String expectedPackageName, String expectedApplicationName) {
    return isGenericProject(expectedPackageName, expectedApplicationName, "java", "java");
  }

  public ProjectAssert isJavaProject() {
    return isJavaProject(DEFAULT_PACKAGE_NAME, DEFAULT_APPLICATION_NAME);
  }

  public ProjectAssert isGroovyProject(String expectedPackageName, String expectedApplicationName) {
    return isGenericProject(expectedPackageName, expectedApplicationName, "groovy", "groovy");
  }

  public ProjectAssert isKotlinProject(String expectedPackageName, String expectedApplicationName) {
    return isGenericProject(expectedPackageName, expectedApplicationName, "kotlin", "kt");
  }

  public ProjectAssert isGroovyProject() {
    return isGroovyProject(DEFAULT_PACKAGE_NAME, DEFAULT_APPLICATION_NAME);
  }

  public ProjectAssert isKotlinProject() {
    return isKotlinProject(DEFAULT_PACKAGE_NAME, DEFAULT_APPLICATION_NAME);
  }

  public ProjectAssert isGenericProject(String expectedPackageName, String expectedApplicationName,
                                 String codeLocation, String extension) {
    String packageName = expectedPackageName.replace(".", "/");

    hasFile("src/main/" + codeLocation + "/" + packageName + "/" + expectedApplicationName + "." + extension,
      "src/test/" + codeLocation + "/" + packageName + "/" + expectedApplicationName + "Tests." + extension + "",
      "src/main/resources/application.properties");
    return this;
  }

  public ProjectAssert isJavaWarProject(String expectedApplicationName) {
    return isGenericWarProject(DEFAULT_PACKAGE_NAME, expectedApplicationName, "java", "java");
  }

  public ProjectAssert isJavaWarProject() {
    return isJavaWarProject(DEFAULT_APPLICATION_NAME);
  }

  public ProjectAssert isGenericWarProject(String expectedPackageName, String expectedApplicationName,
                                    String codeLocation, String extension) {
    String packageName = expectedPackageName.replace(".", "/");
    isGenericProject(expectedPackageName, expectedApplicationName, codeLocation, extension)
      .hasStaticAndTemplatesResources(true)
      .hasFile("src/main/" + codeLocation + "/" + packageName + "/ServletInitializer.$" + extension);
    return this;
  }

  public ProjectAssert hasStaticAndTemplatesResources(boolean web) {
    assertFile("src/main/resources/templates", web);
    assertFile("src/main/resources/static", web);
    return this;
  }

  public ProjectAssert hasFile(String... localPaths) {
    for (String localPath : localPaths) {
      assertFile(localPath, true);
    }
    return this;
  }

  public ProjectAssert hasNoFile(String... localPaths) {
    for (String localPath : localPaths) {
      assertFile(localPath, false);
    }
    return this;
  }

  public ProjectAssert assertFile(String localPath, boolean exist) {
    final File candidate = file(localPath);
    assertEquals(exist, candidate.exists());
    return this;
  }

  private File file(String localPath) {
    return new File(dir, localPath);
  }
}
