package metal.medshrink.network

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

object ApiClientQualifier : Qualifier {
    override val value: QualifierValue
        get() = "medshrink-api-client"
}
