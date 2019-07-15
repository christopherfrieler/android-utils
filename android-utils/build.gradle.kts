import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
	id("com.android.library")
	id("kotlin-android")
	id("org.jetbrains.dokka-android")
	id("maven-publish")
	id("com.jfrog.bintray")
}

android {
	compileSdkVersion(28)
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}

	defaultConfig {
		minSdkVersion(21)
		targetSdkVersion(28)

		versionCode = 1
		versionName = "$version"
		// append version to android build-artifacts:
		libraryVariants.all { outputs.all { this as BaseVariantOutputImpl
			outputFileName = outputFileName.replace(base.archivesBaseName, "${base.archivesBaseName}-${version}")
		}}
	}

	buildTypes {
		getByName("release") {
			isMinifyEnabled = false

			// strip "-release"-qualifier from artifact file-names, because release is the default:
			android.libraryVariants.matching { variant -> variant.name == "release" }.all { outputs.all { this as BaseVariantOutputImpl
				outputFileName = outputFileName.replace("-release", "") }
			}
		}
	}

	sourceSets {
		maybeCreate("main").java.srcDirs("src/main/kotlin/")
		maybeCreate("test").java.srcDirs("src/test/kotlin/")
	}
}

dependencies {
	api("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Dependencies.kotlin_version}")

	testImplementation("junit:junit:4.12")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit:${Dependencies.kotlin_version}")
	testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.17")

	// currently necessary to run tests from Android Studio:
	testImplementation("org.jetbrains.kotlin:kotlin-reflect:${Dependencies.kotlin_version}")
}

val sourcesJar = tasks.register("sourcesJar", Jar::class) {
	from(android.sourceSets["main"].java.srcDirs)
	archiveClassifier.set("sources")
}

val kdocJar = tasks.register("kdocJar", Jar::class) {
	dependsOn(tasks.dokka)
	from("${buildDir}/dokka")
	archiveClassifier.set("kdoc")
}

publishing {
	publications {
		create("maven", MavenPublication::class) {
			pom.withXml {
				asNode().appendNode("name", "android-utils")
				asNode().appendNode("description", "A collection of utility-functions for Android apps.")
				asNode().appendNode("licenses").appendNode("license")
						.appendNode("name", "MIT").parent()
						.appendNode("url", "https://opensource.org/licenses/MIT").parent()
				asNode().appendNode("scm")
						.appendNode("url", "https://github.com/christopherfrieler/android-utils").parent()
				asNode().appendNode("developers").appendNode("developer")
						.appendNode("name", "Christopher Frieler").parent()
				val dependenciesNode = asNode().appendNode("dependencies")
				configurations.getByName("api").allDependencies.configureEach {
					val dependencyNode = dependenciesNode.appendNode("dependency")
					dependencyNode.appendNode("groupId", this.group)
					dependencyNode.appendNode("artifactId", this.name)
					dependencyNode.appendNode("version", this.version)
				}
			}

			afterEvaluate {
				artifact(tasks.getByName("bundleReleaseAar"))
				artifact(sourcesJar.get())
				artifact(kdocJar.get())
			}
		}
	}
}

bintray {
	user = System.getenv("BINTRAY_USER")
	key = System.getenv("BINTRAY_API_KEY")

	with(pkg) {
		repo = "android"
		name = project.name
		userOrg = "christopherfrieler"
		setLicenses("MIT")
		desc = "Utility-Functions for Android Apps"
		vcsUrl = "https://github.com/christopherfrieler/android-utils.git"
		githubRepo = "christopherfrieler/android-utils"

		version.name = "${project.version}"
	}
	publish = true

	setPublications("maven")
}
