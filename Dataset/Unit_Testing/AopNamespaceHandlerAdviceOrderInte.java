class AopNamespaceHandlerAdviceOrderIntegrationTests {

	@Nested
	@SpringJUnitConfig(locations = "AopNamespaceHandlerAdviceOrderIntegrationTests-afterFirst.xml")
	@DirtiesContext
	class AfterAdviceFirstTests {

		@Test
		void afterAdviceIsInvokedFirst(@Autowired Echo echo, @Autowired InvocationTrackingAspect aspect) throws Exception {
			assertThat(aspect.invocations).isEmpty();
			assertThat(echo.echo(42)).isEqualTo(42);
			assertThat(aspect.invocations).containsExactly("around - start", "before", "around - end", "after", "after returning");

			aspect.invocations.clear();
			assertThatException().isThrownBy(() -> echo.echo(new Exception()));
			assertThat(aspect.invocations).containsExactly("around - start", "before", "around - end", "after", "after throwing");
		}
	}

	@Nested
	@SpringJUnitConfig(locations = "AopNamespaceHandlerAdviceOrderIntegrationTests-afterLast.xml")
	@DirtiesContext
	class AfterAdviceLastTests {

		@Test
		void afterAdviceIsInvokedLast(@Autowired Echo echo, @Autowired InvocationTrackingAspect aspect) throws Exception {
			assertThat(aspect.invocations).isEmpty();
			assertThat(echo.echo(42)).isEqualTo(42);
			assertThat(aspect.invocations).containsExactly("around - start", "before", "around - end", "after returning", "after");

			aspect.invocations.clear();
			assertThatException().isThrownBy(() -> echo.echo(new Exception()));
			assertThat(aspect.invocations).containsExactly("around - start", "before", "around - end", "after throwing", "after");
		}
	}


	static class Echo {

		Object echo(Object obj) throws Exception {
			if (obj instanceof Exception) {
				throw (Exception) obj;
			}
			return obj;
		}
	}

	static class InvocationTrackingAspect {

		List<String> invocations = new ArrayList<>();

		Object around(ProceedingJoinPoint joinPoint) throws Throwable {
			invocations.add("around - start");
			try {
				return joinPoint.proceed();
			}
			finally {
				invocations.add("around - end");
			}
		}

		void before() {
			invocations.add("before");
		}

		void afterReturning() {
			invocations.add("after returning");
		}

		void afterThrowing() {
			invocations.add("after throwing");
		}

		void after() {
			invocations.add("after");
		}
	}

}