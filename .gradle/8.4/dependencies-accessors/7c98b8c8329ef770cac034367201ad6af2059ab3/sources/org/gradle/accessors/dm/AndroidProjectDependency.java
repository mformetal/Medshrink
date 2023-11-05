package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.ProjectDependency;
import org.gradle.api.internal.artifacts.dependencies.ProjectDependencyInternal;
import org.gradle.api.internal.artifacts.DefaultProjectDependencyFactory;
import org.gradle.api.internal.artifacts.dsl.dependencies.ProjectFinder;
import org.gradle.api.internal.catalog.DelegatingProjectDependency;
import org.gradle.api.internal.catalog.TypeSafeProjectDependencyFactory;
import javax.inject.Inject;

@NonNullApi
public class AndroidProjectDependency extends DelegatingProjectDependency {

    @Inject
    public AndroidProjectDependency(TypeSafeProjectDependencyFactory factory, ProjectDependencyInternal delegate) {
        super(factory, delegate);
    }

    /**
     * Creates a project dependency on the project at path ":android:app"
     */
    public Android_AppProjectDependency getApp() { return new Android_AppProjectDependency(getFactory(), create(":android:app")); }

    /**
     * Creates a project dependency on the project at path ":android:auth"
     */
    public Android_AuthProjectDependency getAuth() { return new Android_AuthProjectDependency(getFactory(), create(":android:auth")); }

    /**
     * Creates a project dependency on the project at path ":android:theme"
     */
    public Android_ThemeProjectDependency getTheme() { return new Android_ThemeProjectDependency(getFactory(), create(":android:theme")); }

}
