class VintageGradleIntegrationTests {

	@Test
	void unsupportedVersion() {
		var result = run("4.11");

		assertThat(result.getExitCode()).isGreaterThan(0);
		assertThat(result.getOutput("out")) //
				.doesNotContain("STARTED") //
				.contains("Unsupported version of junit:junit: 4.11");
	}

	@ParameterizedTest(name = "{0}")
	@ValueSource(strings = { "4.12", "4.13.2" })
	void supportedVersions(String version) {
		var result = run(version);

		assertThat(result.getExitCode()).isGreaterThan(0);
		assertThat(result.getOutput("out")) //
				.contains("VintageTest > success PASSED") //
				.contains("VintageTest > failure FAILED");

		var testResultsDir = Request.WORKSPACE.resolve("vintage-gradle-" + version).resolve("build/test-results/test");
		assertThat(testResultsDir.resolve("TEST-com.example.vintage.VintageTest.xml")).isRegularFile();
	}

	private Result run(String version) {
		var result = Request.builder() //
				.setTool(new GradleWrapper(Paths.get(".."))) //
				.setJavaHome(Helper.getJavaHome("8").orElseThrow(TestAbortedException::new)) //
				.setProject("vintage") //
				.setWorkspace("vintage-gradle-" + version) //
				.addArguments("build", "--no-daemon", "--stacktrace") //
				.addArguments("-Dmaven.repo=" + MavenRepo.dir()) //
				.addArguments("-Djunit4Version=" + version) //
				.setTimeout(TOOL_TIMEOUT) //
				.build() //
				.run();
		assertFalse(result.isTimedOut(), () -> "tool timed out: " + result);
		return result;
	}

}