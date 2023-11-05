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
public class DiaryFrontendProjectDependency extends DelegatingProjectDependency {

    @Inject
    public DiaryFrontendProjectDependency(TypeSafeProjectDependencyFactory factory, ProjectDependencyInternal delegate) {
        super(factory, delegate);
    }

    /**
     * Creates a project dependency on the project at path ":android"
     */
    public AndroidProjectDependency getAndroid() { return new AndroidProjectDependency(getFactory(), create(":android")); }

    /**
     * Creates a project dependency on the project at path ":ios"
     */
    public IosProjectDependency getIos() { return new IosProjectDependency(getFactory(), create(":ios")); }

}
