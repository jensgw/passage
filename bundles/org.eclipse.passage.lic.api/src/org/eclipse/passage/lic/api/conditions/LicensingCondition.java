/*******************************************************************************
 * Copyright (c) 2018-2019 ArSysOp
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     ArSysOp - initial API and implementation
 *******************************************************************************/
package org.eclipse.passage.lic.api.conditions;

import java.util.Date;

import org.eclipse.passage.lic.api.access.PermissionEmitter;

/**
 *
 * Defines the condition to be evaluated by {@link PermissionEmitter} <br/>
 * Obtained from {@link ConditionMiner}
 *
 * @since 0.5.0
 *
 */
public interface LicensingCondition {
	/**
	 * Returns unique identifier of a feature under licensing.
	 *
	 * @return feature identifier
	 */
	String getFeatureIdentifier();

	/**
     * Returns descriptor of the feature version allowed by this licensing condition.
     *
     * @return version descriptor
     * */
	String getMatchVersion();

	/**
     * Returns rule of version matching, like "perfect match" or "equal or greater".
     *
     * @return match rule
     * */
	String getMatchRule();

	/**
	 * Returns the validity period start date of this licensing condition. This is
	 * the value of its <code>"validFrom"</code> attribute.
	 *
	 * @return the valid from
	 */
	Date getValidFrom();

	/**
	 * Returns the validity period end date of this licensing condition. This is the
	 * value of its <code>"validUntil"</code> attribute.
	 *
	 * @return the valid until
	 */
	Date getValidUntil();

	/**
	 * The type of condition like "time" or "hardware".
	 *
	 * @return condition type
	 */
	String getConditionType();

	/**
     * Returns additional licensing requirements encoded in a single string value.
     *
     * @return description of additional demands of this licencing condition
     * */
	String getConditionExpression();

}
