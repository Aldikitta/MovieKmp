import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project

internal fun Project.configureKotlinAndroid(
    extension: CommonExtension<*, *, *, *, *, *>
) = extension.apply {

    //get module name from module path
    val moduleName = path.split(":").drop(2).joinToString(".")
    namespace = if(moduleName.isNotEmpty()) "com.aldikitta.moviekmp.$moduleName" else "com.aldikitta.moviekmp"

    compileSdk = pluginLibs.findVersion("android-compileSdk").get().requiredVersion.toInt()

    defaultConfig {
        minSdk = pluginLibs.findVersion("android-minSdk").get().requiredVersion.toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}