class JavaVersionsTests {

	@Test
	void java_8() {
		var java8Home = Helper.getJavaHome("8");
		assumeTrue(java8Home.isPresent(), "Java 8 installation directory not found!");
		var actualLines = execute("8", java8Home.get());

		assertTrue(actualLines.contains("[WARNING] Tests run: 2, Failures: 0, Errors: 0, Skipped: 1"));
	}

	@Test
	void java_default() {
		var actualLines = execute("default", new Java().getHome());

		assertTrue(actualLines.contains("[WARNING] Tests run: 2, Failures: 0, Errors: 0, Skipped: 1"));
	}

	List<String> execute(String version, Path javaHome) {
		var result = Request.builder() //
				.setTool(Request.maven()) //
				.setProject("java-versions") //
				.setWorkspace("java-versions-" + version) //
				.addArguments("-Dmaven.repo=" + MavenRepo.dir()) //
				.addArguments("--update-snapshots", "--batch-mode", "verify") //
				.setTimeout(TOOL_TIMEOUT) //
				.setJavaHome(javaHome) //
				.build().run();
		assertFalse(result.isTimedOut(), () -> "tool timed out: " + result);
		assertEquals(0, result.getExitCode());
		assertEquals("", result.getOutput("err"));
		return result.getOutputLines("out");
	}

}