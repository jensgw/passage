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
package org.eclipse.passage.lic.internal.hc.tests.remote;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.util.Collection;
import java.util.function.Supplier;

import org.eclipse.passage.lbc.base.tests.TestData;
import org.eclipse.passage.lbc.internal.base.EagerFloatingState;
import org.eclipse.passage.lbc.internal.base.ProductUserRequest;
import org.eclipse.passage.lbc.internal.base.api.FloatingState;
import org.eclipse.passage.lbc.internal.base.api.RawRequest;
import org.eclipse.passage.lbc.internal.base.mine.Conditions;
import org.eclipse.passage.lic.internal.api.LicensingException;
import org.eclipse.passage.lic.internal.api.ServiceInvocationResult;
import org.eclipse.passage.lic.internal.api.conditions.ConditionPack;
import org.eclipse.passage.lic.internal.api.conditions.mining.ConditionTransportRegistry;
import org.eclipse.passage.lic.internal.api.io.KeyKeeperRegistry;
import org.eclipse.passage.lic.internal.api.io.StreamCodecRegistry;
import org.eclipse.passage.lic.internal.base.io.PathKeyKeeper;
import org.eclipse.passage.lic.internal.base.registry.ReadOnlyRegistry;
import org.eclipse.passage.lic.internal.bc.BcStreamCodec;
import org.eclipse.passage.lic.internal.hc.remote.Client;
import org.eclipse.passage.lic.internal.hc.remote.impl.mine.RemoteConditions;
import org.eclipse.passage.lic.internal.licenses.migration.tobemoved.XmiConditionTransport;
import org.eclipse.passage.lic.internal.net.handle.NetResponse;
import org.junit.Test;

@SuppressWarnings("restriction")
public final class MineTest {

	private final TestData data = new TestData();
	private final Supplier<Path> source = new TestLicFolder();

	@Test
	public void mine() {
		ServiceInvocationResult<Collection<ConditionPack>> all = //
				new RemoteConditions<>(keys(), codecs(), transports(), this::client, source)//
						.all(data.product());
		assertTrue(all.data().isPresent());
		assertEquals(2, all.data().get().size());
	}

	private ConditionTransportRegistry transports() {
		return () -> new ReadOnlyRegistry<>(new XmiConditionTransport());
	}

	private StreamCodecRegistry codecs() {
		return () -> new ReadOnlyRegistry<>(new BcStreamCodec(data::product));
	}

	private KeyKeeperRegistry keys() {
		return () -> new ReadOnlyRegistry<>(new PathKeyKeeper(data.product(), source));
	}

	private Client<ShortcutConnection, Collection<ConditionPack>> client() {
		return new ShortcutClient<Collection<ConditionPack>>(new AskMiner());
	}

	private final class AskMiner implements ShortcutClient.Remote {

		@Override
		public NetResponse invoke(RawRequest raw) throws LicensingException {
			return new Conditions(new ProductUserRequest(raw), source).get();
		}

		@Override
		public FloatingState state() {
			return new EagerFloatingState();
		}

	}

}
