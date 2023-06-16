class GradleMissingEngineTests {

	@Test
	void gradle_wrapper() {
		test(new GradleWrapper(Paths.get("..")));
	}

	private void test(Tool gradle) {
		var project = "gradle-missing-engine";
		var result = Request.builder() //
				.setProject(project) //
				.setWorkspace(project + "-wrapper") //
				.setTool(gradle) //
				.addArguments("-Dmaven.repo=" + MavenRepo.dir()) //
				.addArguments("build", "--no-daemon", "--debug", "--stacktrace") //
				.setJavaHome(Helper.getJavaHome("8").orElseThrow(TestAbortedException::new)) //
				.setTimeout(TOOL_TIMEOUT).build() //
				.run();

		assertFalse(result.isTimedOut(), () -> "tool timed out: " + result);

		assertEquals(1, result.getExitCode());
		assertLinesMatch(List.of( //
			">> HEAD >>", //
			".+DEBUG.+Cannot create Launcher without at least one TestEngine.+", //
			">> TAIL >>"), //
			result.getOutputLines("out"));
		assertLinesMatch(List.of( //
			">> HEAD >>", //
			".+ERROR.+FAILURE: Build failed with an exception.", //
			">> TAIL >>"), //
			result.getOutputLines("err"));
	}
}