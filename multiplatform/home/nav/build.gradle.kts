plugins {
    id("multiplatform")
}

multiplatform {
    android {
        namespace("metal.diary.home.nav")
    }

    common {
        main {

        }

        test {
            dependencies {

            }
        }
    }
}