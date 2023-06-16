class ArchUnitTests {

	@ArchTest
	private final ArchRule allPublicTopLevelTypesHaveApiAnnotations = classes() //
			.that(have(modifier(PUBLIC))) //
			.and(TOP_LEVEL_CLASSES) //
			.and(not(ANONYMOUS_CLASSES)) //
			.and(not(describe("are Kotlin SAM type implementations", simpleName("")))) //
			.and(not(describe("are shadowed", resideInAnyPackage("..shadow..")))) //
			.should().beAnnotatedWith(API.class);

	@ArchTest
	void allAreIn(JavaClasses classes) {
		// about 928 classes found in all jars
		assertTrue(classes.size() > 800, "expected more than 800 classes, got: " + classes.size());
	}

	@ArchTest
	void freeOfCycles(JavaClasses classes) {
		slices().matching("org.junit.(*)..").should().beFreeOfCycles().check(classes);
	}

	@ArchTest
	void avoidJavaUtilLogging(JavaClasses classes) {
		// LoggerFactory.java:80 -> sets field LoggerFactory$DelegatingLogger.julLogger
		var subset = classes.that(are(not(name("org.junit.platform.commons.logging.LoggerFactory$DelegatingLogger"))));
		GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING.check(subset);
	}

	@ArchTest
	void avoidThrowingGenericExceptions(JavaClasses classes) {
		// LoggerFactory.java:155 -> new Throwable()
		var subset = classes.that(are(not(
			name("org.junit.platform.commons.logging.LoggerFactory$DelegatingLogger").or(nameContaining(".shadow.")))));
		GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS.check(subset);
	}

	@ArchTest
	void avoidAccessingStandardStreams(JavaClasses classes) {
		// ConsoleLauncher, StreamInterceptor, Picocli et al...
		var subset = classes //
				.that(are(not(name("org.junit.platform.console.ConsoleLauncher")))) //
				.that(are(not(name("org.junit.platform.launcher.core.StreamInterceptor")))) //
				.that(are(not(name("org.junit.platform.runner.JUnitPlatformRunnerListener")))) //
				.that(are(not(name("org.junit.platform.testkit.engine.Events")))) //
				.that(are(not(name("org.junit.platform.testkit.engine.Executions")))) //
				.that(are(not(resideInAPackage("org.junit.platform.console.shadow.picocli"))));
		GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS.check(subset);
	}

	static class AllJars implements LocationProvider {

		@Override
		public Set<Location> get(Class<?> testClass) {
			return loadJarFiles().stream().map(Location::of).collect(Collectors.toSet());
		}

	}

}