package metal.medshrink.network

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

object GeneralClientQualifier : Qualifier {

    override val value: QualifierValue
        get() = "medshrink-general-client"
}
