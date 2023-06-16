class PackageCyclesDetectionTests {

	@ParameterizedTest
	@MethodSource("platform.tooling.support.Helper#loadModuleDirectoryNames")
	@Disabled("Need to pass --module-path...")
	void moduleDoesNotContainCyclicPackageReferences(String module) {
		var jar = MavenRepo.jar(module);
		var result = new CyclesDetector(jar, this::ignore).run(Configuration.of());
		assertEquals(0, result.getExitCode(), "result=" + result);
	}

	private boolean ignore(String source, String target) {
		if (source.equals(target)) {
			return true;
		}
		if (source.startsWith("org.junit.jupiter.params.shadow.com.univocity.parsers.")) {
			return true;
		}
		//noinspection RedundantIfStatement
		if (!target.startsWith("org.junit.")) {
			return true;
		}
		return false;
	}

}