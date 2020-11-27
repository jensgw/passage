/*******************************************************************************
 * Copyright (c) 2020 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * https://www.eclipse.org/legal/epl-2.0/.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lbc.base.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.eclipse.passage.lbc.internal.api.FloatingResponse;
import org.eclipse.passage.lbc.internal.api.FloatingState;
import org.eclipse.passage.lbc.internal.base.EagerFloatingState;
import org.eclipse.passage.lbc.internal.base.Failure;
import org.eclipse.passage.lbc.internal.base.ProductUserRequest;
import org.eclipse.passage.lbc.internal.base.acquire.Acquisition;
import org.eclipse.passage.lic.floating.model.api.GrantAcqisition;
import org.eclipse.passage.lic.internal.api.LicensedProduct;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.conditions.ConditionAction;
import org.eclipse.passage.lic.internal.base.BaseLicensedProduct;
import org.junit.Test;

public final class ExtensiveAcquiringTest {
	private final LicensedProduct product = new BaseLicensedProduct("anti-human-magic.product", "0.2.1"); //$NON-NLS-1$ //$NON-NLS-2$
	private final String feature = "prince-to-frog"; //$NON-NLS-1$
	private final String user = "Albert_Rose@garden.ga"; //$NON-NLS-1$

	@Test
	public void concurrentAcquire() throws InterruptedException {
		int amount = 128;
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 10);
		Set<Future<FloatingResponse>> futures = runConcurrentAcquireRequest(pool, amount);
		int[] counts = countGainsAndLates(futures);
		assertEquals(4, counts[0]); // gain grant acquisition, we have only 4
		assertEquals(amount - 4, counts[1]); // all the rest has 'no available grants' response
		pool.shutdown();
	}

	private Set<Future<FloatingResponse>> runConcurrentAcquireRequest(ExecutorService pool, int amount)
			throws InterruptedException {
		FloatingState state = new EagerFloatingState(new LicFolder());
		Set<Future<FloatingResponse>> futures = IntStream.range(0, amount)//
				.mapToObj(i -> pool.submit(new Acq(state)))//
				.collect(Collectors.toSet());
		return futures;
	}

	private int[] countGainsAndLates(Set<Future<FloatingResponse>> futures) throws InterruptedException {
		int gains = 0;
		int lates = 0;
		int none = new Failure.NoGrantsAvailable(product, feature).error().code();
		for (Future<FloatingResponse> future : futures) {
			FloatingResponse response;
			try {
				response = future.get();
			} catch (ExecutionException e) {
				fail(e.getCause().getMessage());
				return new int[0];// unreachable
			}
			if (!response.failed()) {
				gains++;
			} else if (none == response.error().code()) {
				lates++;
			}
		}
		return new int[] { gains, lates };
	}

	private final class Acq implements Callable<FloatingResponse> {

		private final FloatingState state;

		Acq(FloatingState state) {
			this.state = state;
		}

		@Override
		public FloatingResponse call() throws Exception {
			return new Acquisition(request()).get();
		}

		private ProductUserRequest request() throws LicensingException {
			return new ProductUserRequest(new FeatureRequest(//
					new ConditionAction.Acquire(), product, feature, user, state).get());
		}

	}

	private final class Rel implements Callable<FloatingResponse> {

		private final FloatingState state;

		Rel(FloatingState state) {
			this.state = state;
		}

		private ProductUserRequest request(GrantAcqisition acquisition, FloatingState state) throws LicensingException {
			return new ProductUserRequest(new FeatureRequest(//
					new ConditionAction.Acquire(), product, feature, user, acquisition, state).get());
		}

		@Override
		public FloatingResponse call() throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

	}
}