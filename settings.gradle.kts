pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}
rootProject.name = "Clean Achitecture"
include(":app")
include(":domain")
include(":data")
include(":common")
include(":featureuser")
include(":featureConversation")
include(":companyinfo")
