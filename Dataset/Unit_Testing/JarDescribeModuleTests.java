class JarDescribeModuleTests {

	@ParameterizedTest
	@MethodSource("platform.tooling.support.Helper#loadModuleDirectoryNames")
	void describeModule(String module) throws Exception {
		var modulePath = MavenRepo.jar(module);
		var result = Request.builder() //
				.setTool(new Jar()) //
				.setProject("jar-describe-module") //
				.setProjectToWorkspaceCopyFileFilter(file -> file.getName().startsWith(module)) //
				.setWorkspace("jar-describe-module/" + module) //
				.addArguments("--describe-module", "--file", modulePath) //
				.build() //
				.run();

		assertFalse(result.isTimedOut(), () -> "tool timed out: " + result);

		assertEquals(0, result.getExitCode());
		assertEquals("", result.getOutput("err"), "error log isn't empty");
		var expected = Paths.get("build", "test-workspace", "jar-describe-module", module, module + ".expected.txt");
		if (Files.notExists(expected)) {
			result.getOutputLines("out").forEach(System.err::println);
			fail("No such file: " + expected);
		}
		var expectedLines = Files.lines(expected).map(Helper::replaceVersionPlaceholders).collect(Collectors.toList());
		var origin = Path.of("projects", "jar-describe-module", module + ".expected.txt").toUri();
		assertLinesMatch(expectedLines, result.getOutputLines("out"), () -> String.format("%s\nError", origin));
	}

	@ParameterizedTest
	@MethodSource("platform.tooling.support.Helper#loadModuleDirectoryNames")
	void packageNamesStartWithNameOfTheModule(String module) {
		var modulePath = MavenRepo.jar(module);
		var moduleDescriptor = ModuleFinder.of(modulePath).findAll().iterator().next().descriptor();
		var moduleName = moduleDescriptor.name();
		for (var packageName : moduleDescriptor.packages()) {
			assertTrue(packageName.startsWith(moduleName));
		}
	}

}