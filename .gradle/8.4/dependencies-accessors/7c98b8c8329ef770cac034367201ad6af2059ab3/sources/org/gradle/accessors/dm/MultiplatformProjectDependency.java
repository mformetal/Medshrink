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
public class MultiplatformProjectDependency extends DelegatingProjectDependency {

    @Inject
    public MultiplatformProjectDependency(TypeSafeProjectDependencyFactory factory, ProjectDependencyInternal delegate) {
        super(factory, delegate);
    }

    /**
     * Creates a project dependency on the project at path ":multiplatform:auth"
     */
    public Multiplatform_AuthProjectDependency getAuth() { return new Multiplatform_AuthProjectDependency(getFactory(), create(":multiplatform:auth")); }

    /**
     * Creates a project dependency on the project at path ":multiplatform:network"
     */
    public Multiplatform_NetworkProjectDependency getNetwork() { return new Multiplatform_NetworkProjectDependency(getFactory(), create(":multiplatform:network")); }

    /**
     * Creates a project dependency on the project at path ":multiplatform:viewmodel"
     */
    public Multiplatform_ViewmodelProjectDependency getViewmodel() { return new Multiplatform_ViewmodelProjectDependency(getFactory(), create(":multiplatform:viewmodel")); }

}
