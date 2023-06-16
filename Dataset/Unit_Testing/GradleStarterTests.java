class GradleStarterTests {

	@Test
	void gradle_wrapper() {
		var request = Request.builder() //
				.setTool(new GradleWrapper(Paths.get(".."))) //
				.setProject("gradle-starter") //
				.addArguments("-Dmaven.repo=" + MavenRepo.dir()) //
				.addArguments("build", "--no-daemon", "--stacktrace") //
				.setTimeout(TOOL_TIMEOUT) //
				.setJavaHome(Helper.getJavaHome("8").orElseThrow(TestAbortedException::new)) //
				.build();

		var result = request.run();

		assertFalse(result.isTimedOut(), () -> "tool timed out: " + result);

		assertEquals(0, result.getExitCode());
		assertTrue(result.getOutputLines("out").stream().anyMatch(line -> line.contains("BUILD SUCCESSFUL")));
		assertThat(result.getOutput("out")).contains("Using Java version: 1.8");

		var testResultsDir = Request.WORKSPACE.resolve(request.getWorkspace()).resolve("build/test-results/test");
		verifyContainsExpectedStartedOpenTestReport(testResultsDir);
	}
}