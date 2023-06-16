class GradleKotlinExtensionsTests {

	@Test
	void gradle_wrapper() {
		var result = Request.builder() //
				.setTool(new GradleWrapper(Request.PROJECTS.resolve("gradle-kotlin-extensions"))) //
				.setProject("gradle-kotlin-extensions") //
				.addArguments("-Dmaven.repo=" + MavenRepo.dir()) //
				.addArguments("build", "--no-daemon", "--stacktrace") //
				.setTimeout(TOOL_TIMEOUT) //
				.setJavaHome(Helper.getJavaHome("8").orElseThrow(TestAbortedException::new)) //
				.build() //
				.run();

		assertFalse(result.isTimedOut(), () -> "tool timed out: " + result);

		assertEquals(0, result.getExitCode(), "result=" + result);
		assertTrue(result.getOutputLines("out").stream().anyMatch(line -> line.contains("BUILD SUCCESSFUL")));
	}
}