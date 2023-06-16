class MavenStarterTests {

	@Test
	void verifyMavenStarterProject() {
		var request = Request.builder() //
				.setTool(Request.maven()) //
				.setProject("maven-starter") //
				.addArguments("-Dmaven.repo=" + MavenRepo.dir()) //
				.addArguments("--update-snapshots", "--batch-mode", "verify") //
				.setTimeout(TOOL_TIMEOUT) //
				.setJavaHome(Helper.getJavaHome("8").orElseThrow(TestAbortedException::new)) //
				.build();

		var result = request.run();

		assertFalse(result.isTimedOut(), () -> "tool timed out: " + result);

		assertEquals(0, result.getExitCode());
		assertEquals("", result.getOutput("err"));
		assertTrue(result.getOutputLines("out").contains("[INFO] BUILD SUCCESS"));
		assertTrue(result.getOutputLines("out").contains("[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0"));
		assertThat(result.getOutput("out")).contains("Using Java version: 1.8");

		var testResultsDir = Request.WORKSPACE.resolve(request.getWorkspace()).resolve("target/surefire-reports");
		verifyContainsExpectedStartedOpenTestReport(testResultsDir);
	}
}