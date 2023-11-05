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
public class RootProjectAccessor extends TypeSafeProjectDependencyFactory {


    @Inject
    public RootProjectAccessor(DefaultProjectDependencyFactory factory, ProjectFinder finder) {
        super(factory, finder);
    }

    /**
     * Creates a project dependency on the project at path ":"
     */
    public DiaryProjectDependency getDiary() { return new DiaryProjectDependency(getFactory(), create(":")); }

    /**
     * Creates a project dependency on the project at path ":android"
     */
    public AndroidProjectDependency getAndroid() { return new AndroidProjectDependency(getFactory(), create(":android")); }

    /**
     * Creates a project dependency on the project at path ":ios"
     */
    public IosProjectDependency getIos() { return new IosProjectDependency(getFactory(), create(":ios")); }

    /**
     * Creates a project dependency on the project at path ":multiplatform"
     */
    public MultiplatformProjectDependency getMultiplatform() { return new MultiplatformProjectDependency(getFactory(), create(":multiplatform")); }

}
