package metal.diary.network

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

object ApiClientQualifier : Qualifier {
    override val value: QualifierValue
        get() = "diary-api-client"
}