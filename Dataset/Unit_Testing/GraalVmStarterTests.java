class GraalVmStarterTests {

	@Test
	void runsTestsInNativeImage() {
		var request = Request.builder() //
				.setTool(new GradleWrapper(Paths.get(".."))) //
				.setProject("graalvm-starter") //
				.addArguments("-Dmaven.repo=" + MavenRepo.dir()) //
				.addArguments("javaToolchains", "nativeTest", "--no-daemon", "--stacktrace") //
				.setTimeout(Duration.ofMinutes(5)) //
				.build();

		var result = request.run();

		assertFalse(result.isTimedOut(), () -> "tool timed out: " + result);

		assumeFalse(
			result.getOutputLines("err").stream().anyMatch(line -> line.contains("No matching toolchains found")),
			"Abort test if GraalVM is not installed");

		assertEquals(0, result.getExitCode());
		assertTrue(result.getOutputLines("out").stream().anyMatch(line -> line.contains("BUILD SUCCESSFUL")));

		var testResultsDir = Request.WORKSPACE.resolve(request.getWorkspace()).resolve("build/test-results/test");
		verifyContainsExpectedStartedOpenTestReport(testResultsDir);
	}
}