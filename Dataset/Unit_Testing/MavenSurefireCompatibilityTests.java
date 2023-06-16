class MavenSurefireCompatibilityTests {

	@ParameterizedTest
	@CsvSource(delimiter = '|', nullValues = "<none>", textBlock = """
			2.22.2   | --activate-profiles=manual-platform-dependency
			3.0.0-M4 | <none>
			""")
	void testMavenSurefireCompatibilityProject(String surefireVersion, String extraArg) throws IOException {
		var extraArgs = extraArg == null ? new Object[0] : new Object[] { extraArg };
		var request = Request.builder() //
				.setTool(Request.maven()) //
				.setProject("maven-surefire-compatibility") //
				.addArguments("-Dmaven.repo=" + MavenRepo.dir()) //
				.addArguments("-Dsurefire.version=" + surefireVersion) //
				.addArguments("--update-snapshots", "--batch-mode", "test") //
				.addArguments(extraArgs) //
				.setTimeout(TOOL_TIMEOUT) //
				.setJavaHome(Helper.getJavaHome("8").orElseThrow(TestAbortedException::new)) //
				.build();

		var result = request.run();

		assertFalse(result.isTimedOut(), () -> "tool timed out: " + result);

		assertEquals(0, result.getExitCode());
		assertEquals("", result.getOutput("err"));

		var output = result.getOutputLines("out");
		assertTrue(output.contains("[INFO] BUILD SUCCESS"));
		assertTrue(output.contains("[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0"));

		var targetDir = Request.WORKSPACE.resolve(request.getWorkspace()).resolve("target");
		try (var stream = Files.list(targetDir)) {
			assertThat(stream.filter(file -> file.getFileName().toString().startsWith("junit-platform-unique-ids"))) //
					.hasSize(1);
		}
	}
}