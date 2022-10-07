package pan.leszeczek;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.List;

@Mojo(name = "my-goal", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class MyMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    MavenProject project;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        List<?> pluginList = project.getBuildPlugins();

        /*
        Here is simple stream processing, that started my investigation.
        When using "default" maven-plugin-plugin version (3.2) It was not able to build
        plugin that contained any use of Stream (I haven't check but I guess
        any lambda might be the problem?)
         */
        pluginList.forEach(o -> {
            getLog().info(o.toString());
        });

    }
}

