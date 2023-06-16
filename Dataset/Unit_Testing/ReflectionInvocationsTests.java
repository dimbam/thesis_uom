class ReflectionInvocationsTests {

	@Test
	void sampleTest() {
		RuntimeHints hints = new RuntimeHints();
		hints.reflection().registerType(String.class, MemberCategory.INTROSPECT_PUBLIC_METHODS);

		RuntimeHintsInvocations invocations = RuntimeHintsRecorder.record(() -> {
			SampleReflection sample = new SampleReflection();
			sample.sample(); // does Method[] methods = String.class.getMethods();
		});
		assertThat(invocations).match(hints);
	}

	@Test
	void multipleCallsTest() {
		RuntimeHints hints = new RuntimeHints();
		hints.reflection().registerType(String.class, MemberCategory.INTROSPECT_PUBLIC_METHODS);
		hints.reflection().registerType(Integer.class,MemberCategory.INTROSPECT_PUBLIC_METHODS);
		RuntimeHintsInvocations invocations = RuntimeHintsRecorder.record(() -> {
			SampleReflection sample = new SampleReflection();
			sample.multipleCalls(); // does Method[] methods = String.class.getMethods(); methods = Integer.class.getMethods();
		});
		assertThat(invocations).match(hints);
	}

}